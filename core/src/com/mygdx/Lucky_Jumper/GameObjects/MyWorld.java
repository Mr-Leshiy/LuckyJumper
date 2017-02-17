package com.mygdx.Lucky_Jumper.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Lucky_Jumper.states.PlayState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyWorld {

    private Random random;
    private World world;
    private Player player;
    private List<com.mygdx.Lucky_Jumper.GameObjects.Platform> platforms;
    private Body endWorld;
    boolean playerDead;
    private List<com.mygdx.Lucky_Jumper.GameObjects.Neurons> neurons;
    public boolean isDelete=false;
    public boolean isContact=false;
    private final static float T=0;
    private float platformSpawn_X;
    private float platformSpawn_Y;
    private com.mygdx.Lucky_Jumper.GameObjects.Clock clock;
    private com.mygdx.Lucky_Jumper.GameObjects.PlatformBoost platformBoost;
    boolean isTimeClockActive;
    boolean isTimePlatformBoostActive;

    float time;
    
    public MyWorld()
    {
        Box2D.init();
        world = new World(new Vector2(0,-10f),true);
        com.mygdx.Lucky_Jumper.GameObjects.Platform.resetsSpeed();
        random = new Random();
        world.setContactListener(new MyContactListener(this));
        platforms = new ArrayList<com.mygdx.Lucky_Jumper.GameObjects.Platform>();
        neurons= new ArrayList<com.mygdx.Lucky_Jumper.GameObjects.Neurons>();
        createWorld();
    }

    private void createWorld()
    {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.DynamicBody;
        Body bodyp = world.createBody(def);
        bodyp.setTransform(2.7f,2.5f,0);
        player = new Player(bodyp);

        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(2,1.5f,0);
        body.setUserData(new com.mygdx.Lucky_Jumper.GameObjects.PlatformData(true,false));
        com.mygdx.Lucky_Jumper.GameObjects.StartPlatform platform1 = new com.mygdx.Lucky_Jumper.GameObjects.StartPlatform(body);
        platforms.add(platform1);

        def.type= BodyDef.BodyType.StaticBody;
        EdgeShape end= new EdgeShape();
        end.set(0,0,8,0);
        endWorld=world.createBody(def);
        endWorld.setTransform(0,-2,0);
        endWorld.createFixture(end,0);
        endWorld.setUserData('e');

        platformSpawn_X=platform1.getWeight()+platform1.getBox().getPosition().x;
        platformSpawn_Y=platform1.getBox().getPosition().y;

        isTimeClockActive=false;
        isTimePlatformBoostActive=false;
    }

    public boolean isTimePlatformBoostActive() {
        return isTimePlatformBoostActive;
    }

    public List<com.mygdx.Lucky_Jumper.GameObjects.Platform> getPlatforms()
    {
        return platforms;
    }

    public com.mygdx.Lucky_Jumper.GameObjects.Clock getClock() {
        return clock;
    }
    public com.mygdx.Lucky_Jumper.GameObjects.PlatformBoost getPlatformBoost() {
        return platformBoost;
    }

    public List<com.mygdx.Lucky_Jumper.GameObjects.Neurons> getNeurons() {
        return neurons;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isTimeClockActive() {
        return isTimeClockActive;
    }

    public float getTime() {
        if(isTimePlatformBoostActive()) {
            return time / PlayState.platform_boost_time;
        }
        else
        {
            return time / PlayState.clock_time;


        }
    }


    public void update(float delta)
    {

        player.update(delta);
        deletePlatforms();
        if (platforms.size()<20)
        {

            if(random.nextBoolean())
            {
                addPlatform(platformSpawn_X- com.mygdx.Lucky_Jumper.GameObjects.Platform1.speed,platformSpawn_Y +1f,true);
                platformSpawn_Y+=0.8f;

            }
            else
            {
                if((platformSpawn_Y-0.8f)>0) {
                    addPlatform(platformSpawn_X - com.mygdx.Lucky_Jumper.GameObjects.Platform1.speed , platformSpawn_Y - 1f,true);
                    platformSpawn_Y -= 0.8f;
                }
                else
                {
                    addPlatform(platformSpawn_X - com.mygdx.Lucky_Jumper.GameObjects.Platform1.speed , platformSpawn_Y + 1f,true);
                    platformSpawn_Y += 0.8f;

                }

            }
            if(random.nextBoolean())
            {
                addPlatform(platformSpawn_X - com.mygdx.Lucky_Jumper.GameObjects.Platform1.speed , platformSpawn_Y + 2f,false);

            }
            if (random.nextBoolean())
            {
                if((platformSpawn_Y)-2f>0)
                addPlatform(platformSpawn_X - com.mygdx.Lucky_Jumper.GameObjects.Platform1.speed , platformSpawn_Y - 2f,false);


            }


            if(random.nextInt(100)<50)
            {
                addNeuron(platforms.get(platforms.size()-1).getBox().getPosition().x+0.15f
                        , platforms.get(platforms.size()-1).getBox().getPosition().y+0.5f);

            }
            else {
             if (random.nextInt(100)<15) {

                addClock(platforms.get(platforms.size()-1).getBox().getPosition().x+0.15f
                        , platforms.get(platforms.size()-1).getBox().getPosition().y+0.5f);
            }
            if(random.nextInt(100)<15) {
                addPlatformBoost(platforms.get(platforms.size()-1).getBox().getPosition().x+0.05f
                        , platforms.get(platforms.size()-1).getBox().getPosition().y+0.5f);
            }

            }



        }

        platformSpawn_X=platforms.get(platforms.size()-1).getBox().getPosition().x+platforms.get(platforms.size() - 1).getWeight();
        deleteNeurons();
        deleteCLock();
        deletePlatformBoost();

        if(isTimeClockActive)
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
            time=PlayState.clock_time;
            isTimeClockActive=false;
        }

        if(isTimePlatformBoostActive) {

            time -= delta;
        }
        if(time<0)
        {
            time=PlayState.clock_time;
            isTimePlatformBoostActive=false;
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
        if(neurons.size()>0) {
            for (int i = 0; i < neurons.size(); i++) {
                if (neurons.get(i).getBody().getPosition().x < -5) {
                    neurons.get(i).getBody().setActive(false);
                    world.destroyBody(neurons.get(i).getBody());
                    neurons.remove(i);

                }
                if (neurons.get(i).getBody().getUserData().equals('d')) {
                    neurons.get(i).getBody().setActive(false);
                    world.destroyBody(neurons.get(i).getBody());
                    neurons.remove(i);
                    isDelete = true;
                }

            }
        }

    }

    public void deleteCLock()
    {
        if(clock!=null && (isTimeClockActive || clock.getBody().getPosition().x<-5))
        {
            clock.getBody().setActive(false);
            world.destroyBody(clock.getBody());
            clock=null;

        }

    }
    public void deletePlatformBoost()
    {
        if(platformBoost!=null && (isTimePlatformBoostActive || platformBoost.getBody().getPosition().x<-5))
        {
            platformBoost.getBody().setActive(false);
            world.destroyBody(platformBoost.getBody());
            platformBoost=null;

        }

    }


    public void addNeuron(float x,float y)
    {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(x,y,0);
        body.setUserData('n');
        com.mygdx.Lucky_Jumper.GameObjects.Neurons neuron = new com.mygdx.Lucky_Jumper.GameObjects.Neurons(body);
        neurons.add(neuron);
    }

    public void addClock(float x, float y)
    {
        if (clock == null && !isTimeClockActive && !isTimePlatformBoostActive && platformBoost==null)
        {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.KinematicBody;
        Body body = world.createBody(def);
        body.setTransform(x, y, 0);
        body.setUserData('t');
        clock = new com.mygdx.Lucky_Jumper.GameObjects.Clock(body);
        }

    }

    public void addPlatformBoost(float x, float y)
    {
        if (platformBoost == null && !isTimePlatformBoostActive && !isTimeClockActive && clock==null)
        {
            BodyDef def = new BodyDef();
            def.type = BodyDef.BodyType.KinematicBody;
            Body body = world.createBody(def);
            body.setTransform(x, y, 0);
            body.setUserData('b');
            platformBoost = new com.mygdx.Lucky_Jumper.GameObjects.PlatformBoost(body);
        }

    }
    public void addPlatform(float x, float y,boolean isBoost)
    {

        BodyDef def = new BodyDef();
        com.mygdx.Lucky_Jumper.GameObjects.PlatformData data = new com.mygdx.Lucky_Jumper.GameObjects.PlatformData(random.nextBoolean(),isBoost);
        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(x,y,0);
        body.setUserData(data);
        com.mygdx.Lucky_Jumper.GameObjects.Platform1 platform1 = new com.mygdx.Lucky_Jumper.GameObjects.Platform1(body);
        platforms.add(platform1);

    }

    public void changeActivelatforms()
    {

        for(com.mygdx.Lucky_Jumper.GameObjects.Platform pl: platforms)
        {
            com.mygdx.Lucky_Jumper.GameObjects.PlatformData data = (com.mygdx.Lucky_Jumper.GameObjects.PlatformData) pl.getBox().getUserData();
         if ( data.isActive())
         {
             data.isActive=false;

         }
         else
         {
             data.isActive=true;

         }
            if(pl instanceof com.mygdx.Lucky_Jumper.GameObjects.StartPlatform)
            {
                data.isActive=true;

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
        com.mygdx.Lucky_Jumper.GameObjects.Platform.increaseSpeed();
        for(com.mygdx.Lucky_Jumper.GameObjects.Platform pl:platforms)
        {
            pl.setSpeed();
        }
        for(com.mygdx.Lucky_Jumper.GameObjects.Neurons n:neurons)
        {
            n.increaseSpeed();
        }

        if(clock!=null) {
            clock.increaseSpeed();
        }
        if(platformBoost!=null)
        {
            platformBoost.increaseSpeed();

        }

    }


}


