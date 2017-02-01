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
    private List<Platform> platform1s;
    private Body endWorld;
    private float time;
    boolean playerDead;
    private List<Neurons> neurons;
    public boolean isDelete=false;
    public boolean isContact=false;
    private final static float T=-1.35f;

    public Player getPlayer() {
        return player;
    }

    public MyWorld()
    {
        world = new World(new Vector2(0,-10f),true);
        random = new Random();
        world.setContactListener(new MyContactListener(this));
        platform1s = new ArrayList<Platform>();
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
        bodyp.setTransform(3.5f,2f,0);
        player = new Player(bodyp);

        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(3,1.5f,0);
        body.setUserData(true);
        StartPlatform platform1 = new StartPlatform(body);
        platform1s.add(platform1);

        def.type= BodyDef.BodyType.StaticBody;
        EdgeShape end= new EdgeShape();
        end.set(0,0,8,0);
        endWorld=world.createBody(def);
        endWorld.setTransform(0,0,0);
        endWorld.createFixture(end,0);
        endWorld.setUserData('e');

    }

    public List<Platform> getPlatform1s()
    {
        return platform1s;
    }

    public void update(float delta)
    {

        player.update(delta);
        deletePlatforms();
        if (platform1s.size()<10)
        {

            if(random.nextBoolean()) {
                addPlatform(platform1s.get(platform1s.size() - 1).getBox().getPosition().x +
                                platform1s.get(platform1s.size() - 1).getWeight()+ Platform1.speed*T,
                        platform1s.get(platform1s.size() - 1).getBox().getPosition().y + 0.8f);
            }
            else {
                if((platform1s.get(platform1s.size() - 1).getBox().getPosition().y - 0.8f)>0) {
                    addPlatform(platform1s.get(platform1s.size() - 1).getBox().getPosition().x +
                                    platform1s.get(platform1s.size() - 1).getWeight()+Platform1.speed*T,
                            platform1s.get(platform1s.size() - 1).getBox().getPosition().y - 0.8f);
                }
                else
                {
                    addPlatform(platform1s.get(platform1s.size() - 1).getBox().getPosition().x+
                            platform1s.get(platform1s.size() - 1).getWeight() + Platform1.speed*T,
                            platform1s.get(platform1s.size() - 1).getBox().getPosition().y + 0.8f);
                }
            }
            if(random.nextBoolean())
            {
                addNeuron(platform1s.get(platform1s.size()-1).getBox().getPosition().x+0.15f
                        , platform1s.get(platform1s.size()-1).getBox().getPosition().y+0.5f);

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
       for(int i = 0; i< platform1s.size(); i++)
        {
            if(platform1s.get(i).getBox().getPosition().x<-5)
            {
                platform1s.get(i).getBox().setActive(false);
                world.destroyBody(platform1s.get(i).getBox());
                platform1s.remove(i);

            }
            if(platform1s.get(i).getBox().getFixtureList().size==2 && platform1s.get(i).getBox().getFixtureList().get(1).getUserData().equals('d'))
            {
                platform1s.get(i).destroyContactFixture();
                isContact=true;

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
        Platform1 platform1 = new Platform1(body);
        platform1s.add(platform1);

    }

    public void changeActivelatforms()
    {

        for(Platform pl: platform1s)
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


}
