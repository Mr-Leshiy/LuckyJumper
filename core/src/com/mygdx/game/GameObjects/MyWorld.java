package com.mygdx.game.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyWorld {

    private Random random;
    private World world;
    private Player player;
    private List<Platform> platforms;
    private Body endWorld;
    private float time;
    boolean playerDead;
    private List<Neurons> neurons;
    public boolean isDelete=false;

    public Player getPlayer() {
        return player;
    }

    public MyWorld()
    {
        world = new World(new Vector2(0,-10f),true);
        random = new Random();
        world.setContactListener(new MyContactListener(this));
        platforms = new ArrayList<Platform>();
        neurons= new ArrayList<Neurons>();
        createWorld();
        time=0;

    }

    public List<Neurons> getNeurons() {
        return neurons;
    }

    private void createWorld()
    {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.DynamicBody;
        Body bodyp = world.createBody(def);
        bodyp.setTransform(3.5f,3f,0);
        player = new Player(bodyp);
        for(int i=0;i<5;i++) {

            addPlatform(i * 2.5f, 1);

        }

        def.type= BodyDef.BodyType.StaticBody;
        EdgeShape end= new EdgeShape();
        end.set(0,0,8,0);
        endWorld=world.createBody(def);
        endWorld.setTransform(0,0,0);
        endWorld.createFixture(end,0);
        endWorld.setUserData('e');

    }

    public List<Platform> getPlatforms()
    {
        return platforms;
    }

    public void update(float delta)
    {

        player.update(delta);
        deletePlatforms();
        if (platforms.size()<10)
        {

            if(random.nextBoolean()) {
                addPlatform(platforms.get(platforms.size() - 1).getBox().getPosition().x + 2.5f, platforms.get(platforms.size() - 1).getBox().getPosition().y + 0.8f);
            }
            else {
                if((platforms.get(platforms.size() - 1).getBox().getPosition().y - 0.8f)>0) {
                    addPlatform(platforms.get(platforms.size() - 1).getBox().getPosition().x + 2.5f, platforms.get(platforms.size() - 1).getBox().getPosition().y - 0.8f);
                }
                else
                {
                    addPlatform(platforms.get(platforms.size() - 1).getBox().getPosition().x + 2.5f, platforms.get(platforms.size() - 1).getBox().getPosition().y + 0.8f);
                }
            }
            if(random.nextBoolean())
            {
                addNeuron(platforms.get(platforms.size()-1).getBox().getPosition().x+0.15f
                        ,platforms.get(platforms.size()-1).getBox().getPosition().y+0.5f);

            }
        }

        deleteNeurons();
        world.step(delta,4,4);

    }

    public World getWorld()
    {
        return world;
    }

    public void deletePlatforms()
    {
       for(int i=0;i<platforms.size();i++)
        {
            if(platforms.get(i).getBox().getPosition().x<-5)
            {
                platforms.get(i).getBox().setActive(false);
                world.destroyBody(platforms.get(i).getBox());
                platforms.remove(i);

            }


        }



    }

    public void deleteNeurons()
    {
        for(int i=0;i<neurons.size();i++)
        {
            if(neurons.get(i).getBody().getPosition().x<-5)
            {
                neurons.get(i).getBody().setActive(false);
                world.destroyBody(neurons.get(i).getBody());
                neurons.remove(i);

            }
            if( neurons.get(i).getBody().getUserData().equals('d'))
            {
                neurons.get(i).getBody().setActive(false);
                world.destroyBody(neurons.get(i).getBody());
                neurons.remove(i);
                isDelete=true;
            }

        }



    }


    public void addNeuron(float x,float y)
    {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(x,y,0);
        body.setUserData('n');
        Neurons neuron = new Neurons(body);
        neurons.add(neuron);
    }
    public void addPlatform(float x, float y)
    {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(x,y,0);
        body.setUserData(random.nextBoolean());
        Platform platform = new Platform(body);
        platforms.add(platform);

    }

    public void changeActivelatforms()
    {

        for(Platform pl:platforms)
        {
            if ((Boolean)pl.getBox().getUserData())
            {
                pl.getBox().setUserData(false);

            }
            else
            {
                pl.getBox().setUserData(true);

            }

        }


    }

    public boolean isGrounded()
    {
        Array<Contact> contacts = world.getContactList();
        for(Contact contact:contacts)
        {
            if(contact.isTouching() && (contact.getFixtureA()==player.getPlayerPhysicsFixture()
                    || contact.getFixtureB()==player.getPlayerPhysicsFixture()))
            {
              return true;

            }
        }

        return false;

    }

    public boolean isPlayerDead()
    {

        return playerDead;

    }

    public void increaseSpeed()
    {
        for(Platform pl:platforms)
        {
            pl.increaseSpeed();
        }



    }





}
