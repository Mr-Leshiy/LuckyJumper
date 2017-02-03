package com.mygdx.game.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.states.PlayState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyWorld {

    private Random random;
    private World world;
    private Player player;
    private List<Platform> platforms;
    private Body endWorld;
    boolean playerDead;
    private List<Neurons> neurons;
    public boolean isDelete=false;
    public boolean isContact=false;
    private final static float T=0;
    private float platformSpawn_X;
    private float platformSpawn_Y;
    private Clock clock;
    boolean isTimeActive;

    private float time= PlayState.time;


    public MyWorld()
    {
        world = new World(new Vector2(0,-10f),true);
        Platform.resetsSpeed();
        random = new Random();
        world.setContactListener(new MyContactListener(this));
        platforms = new ArrayList<Platform>();
        neurons= new ArrayList<Neurons>();
        createWorld();
    }

    private void createWorld()
    {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.DynamicBody;
        Body bodyp = world.createBody(def);
        bodyp.setTransform(2f,2.5f,0);
        player = new Player(bodyp);

        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(4,1.5f,0);
        body.setUserData(true);
        StartPlatform platform1 = new StartPlatform(body);
        platforms.add(platform1);

        def.type= BodyDef.BodyType.StaticBody;
        EdgeShape end= new EdgeShape();
        end.set(0,0,8,0);
        endWorld=world.createBody(def);
        endWorld.setTransform(0,0,0);
        endWorld.createFixture(end,0);
        endWorld.setUserData('e');

        platformSpawn_X=platform1.getWeight()+platform1.getBox().getPosition().x;
        platformSpawn_Y=platform1.getBox().getPosition().y;

        isTimeActive=false;
    }

    public List<Platform> getPlatform1s()
    {
        return platforms;
    }

    public Clock getClock() {
        return clock;
    }

    public List<Neurons> getNeurons() {
        return neurons;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isTimeActive() {
        return isTimeActive;
    }

    public float getTime() {
        return time/PlayState.time;
    }

    public void update(float delta)
    {

        player.update(delta);
        deletePlatforms();
        if (platforms.size()<20)
        {

            if(random.nextBoolean())
            {
                addPlatform(platformSpawn_X- Platform1.speed+T,platformSpawn_Y +0.8f);
                platformSpawn_Y+=0.8f;

            }
            else
            {
                if((platformSpawn_Y-0.8f)>0) {
                    addPlatform(platformSpawn_X - Platform1.speed + T, platformSpawn_Y - 0.8f);
                    platformSpawn_Y -= 0.8f;
                }
                else
                {
                    addPlatform(platformSpawn_X - Platform1.speed + T, platformSpawn_Y + 0.8f);
                    platformSpawn_Y += 0.8f;

                }

            }
            if(random.nextBoolean())
            {
                addPlatform(platformSpawn_X - Platform1.speed + T, platformSpawn_Y + 1.6f);

            }
            if (random.nextBoolean())
            {
                if((platformSpawn_Y)-1.6f>0)
                addPlatform(platformSpawn_X - Platform1.speed + T, platformSpawn_Y - 1.6f);


            }


            if(random.nextInt(100)<50)
            {
                addNeuron(platforms.get(platforms.size()-1).getBox().getPosition().x+0.15f
                        , platforms.get(platforms.size()-1).getBox().getPosition().y+0.5f);

            }
            else {

                if (random.nextInt()<20) {

                    addClock(platforms.get(platforms.size()-1).getBox().getPosition().x+0.15f
                            , platforms.get(platforms.size()-1).getBox().getPosition().y+0.5f);

                }
            }
        }

        platformSpawn_X=platforms.get(platforms.size()-1).getBox().getPosition().x+platforms.get(platforms.size() - 1).getWeight();
        deleteNeurons();
        deleteCLock();

        if(isTimeActive)
        {

            time-=delta;
            world.step(delta/2,4,4);

        }
        else
        {
            world.step(delta,4,4);

        }
        if(time<0)
        {
            time=PlayState.time;
            isTimeActive=false;
        }


    }

    public World getWorld()
    {
        return world;
    }

    public void deletePlatforms()
    {
       for(int i = 0; i< platforms.size(); i++)
        {
            if(platforms.get(i).getBox().getPosition().x<-5)
            {
                platforms.get(i).getBox().setActive(false);
                world.destroyBody(platforms.get(i).getBox());
                platforms.remove(i);

            }
            if(platforms.get(i).getBox().getFixtureList().size==2 && platforms.get(i).getBox().getFixtureList().get(1).getUserData().equals('d'))
            {
                platforms.get(i).destroyContactFixture();
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

    public void deleteCLock()
    {
        if(clock!=null && (isTimeActive || clock.getBody().getPosition().x<-5))
        {
            clock.getBody().setActive(false);
            world.destroyBody(clock.getBody());
            clock=null;

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

    public void addClock(float x, float y)
    {
        if (clock == null && !isTimeActive)
        {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.KinematicBody;
        Body body = world.createBody(def);
        body.setTransform(x, y, 0);
        body.setUserData('t');
        clock = new Clock(body);
        }

    }
    public void addPlatform(float x, float y)
    {

        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(x,y,0);
        body.setUserData(random.nextBoolean());
        Platform1 platform1 = new Platform1(body);
        platforms.add(platform1);

    }

    public void changeActivelatforms()
    {

        for(Platform pl: platforms)
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

    public void inncreaseSpeed()
    {
        Platform.increaseSpeed();
        for(Platform pl:platforms)
        {
            pl.setSpeed();
        }
        for(Neurons n:neurons)
        {
            n.increaseSpeed();
        }

        clock.increaseSpeed();

    }


}
