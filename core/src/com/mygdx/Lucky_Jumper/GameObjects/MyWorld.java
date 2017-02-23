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
    private PlatformBoost platformBoost;
    private DoubleNeuronBoost doubleNeuronBoost;
    boolean isTimeClockActive;
    boolean isTimePlatformBoostActive;
    boolean isTimeDoubleNeuronBoostActive;

    float time;
    
    public MyWorld()
    {
        Box2D.init();
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
        bodyp.setTransform(2.7f,2.5f,0);
        player = new Player(bodyp);

        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(2,1.5f,0);
        body.setUserData(new PlatformData(true,false));
        StartPlatform platform1 = new StartPlatform(body);
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
        isTimeDoubleNeuronBoostActive=false;
    }

    public boolean isTimePlatformBoostActive() {
        return isTimePlatformBoostActive;
    }

    public List<Platform> getPlatforms()
    {
        return platforms;
    }

    public Clock getClock() {
        return clock;
    }
    public PlatformBoost getPlatformBoost() {
        return platformBoost;
    }

    public List<Neurons> getNeurons() {
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
       if(isTimeClockActive()) {
           return time / PlayState.clock_time;
       }
        if(isTimeDoubleNeuronBoostActive)
        {
            return time/PlayState.double_neuron_boost_time;

        }

        return 0;
    }


    public DoubleNeuronBoost getDoubleNeuronBoost() {
        return doubleNeuronBoost;
    }

    public boolean isTimeDoubleNeuronBoostActive() {
        return isTimeDoubleNeuronBoostActive;
    }

    public void update(float delta)
    {

        player.update(delta);
        deletePlatforms();
        if (platforms.size()<20)
        {

            if(random.nextBoolean())
            {
                addPlatform(platformSpawn_X- Platform1.speed,platformSpawn_Y +1f,true);
                platformSpawn_Y+=0.8f;

            }
            else
            {
                if((platformSpawn_Y-0.8f)>0) {
                    addPlatform(platformSpawn_X - Platform1.speed , platformSpawn_Y - 1f,true);
                    platformSpawn_Y -= 0.8f;
                }
                else
                {
                    addPlatform(platformSpawn_X - Platform1.speed , platformSpawn_Y + 1f,true);
                    platformSpawn_Y += 0.8f;

                }

            }
            if(random.nextBoolean())
            {
                addPlatform(platformSpawn_X - Platform1.speed , platformSpawn_Y + 2f,false);

            }
            if (random.nextBoolean())
            {
                if((platformSpawn_Y)-2f>0)
                addPlatform(platformSpawn_X - Platform1.speed , platformSpawn_Y - 2f,false);


            }


            if(random.nextInt(100)<50)
            {
                addNeuron(platforms.get(platforms.size()-1).getBox().getPosition().x+0.15f
                        , platforms.get(platforms.size()-1).getBox().getPosition().y+0.5f);

            }
            else {

                switch (random.nextInt(4)) {
                    case 1:
                        addClock(platforms.get(platforms.size() - 1).getBox().getPosition().x + 0.15f
                                , platforms.get(platforms.size() - 1).getBox().getPosition().y + 0.5f);
                        break;
                    case 2:
                        addPlatformBoost(platforms.get(platforms.size() - 1).getBox().getPosition().x + 0.05f
                                , platforms.get(platforms.size() - 1).getBox().getPosition().y + 0.5f);
                        break;
                    case 3:
                        addDoubleNeuronBoost(platforms.get(platforms.size() - 1).getBox().getPosition().x + 0.05f
                                , platforms.get(platforms.size() - 1).getBox().getPosition().y + 0.5f);
                        break;
                }

            }



        }

        platformSpawn_X=platforms.get(platforms.size()-1).getBox().getPosition().x+platforms.get(platforms.size() - 1).getWeight();
        deleteNeurons();
        deleteCLock();
        deletePlatformBoost();
        deleteDoubleNeuronPoints();

        if(isTimeClockActive)
        {

            time-=delta;
            world.step(delta/2,4,4);

        }
        else
        {
            world.step(delta,4,4);

        }

        if(isTimePlatformBoostActive) {

            time -= delta;
        }

        if(isTimeDoubleNeuronBoostActive)
        {
            time-=delta;
        }
        if(time<0)
        {
            isTimeDoubleNeuronBoostActive=false;
            isTimeClockActive=false;
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

    public void deleteDoubleNeuronPoints()
    {
        if(doubleNeuronBoost!=null && (isTimeDoubleNeuronBoostActive || doubleNeuronBoost.getBody().getPosition().x<-5))
        {
            doubleNeuronBoost.getBody().setActive(false);
            world.destroyBody(doubleNeuronBoost.getBody());
            doubleNeuronBoost=null;

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
        if (doubleNeuronBoost ==null && !isTimePlatformBoostActive && !isTimeClockActive && clock==null && platformBoost ==null && !isTimeDoubleNeuronBoostActive)
        {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.KinematicBody;
        Body body = world.createBody(def);
        body.setTransform(x, y, 0);
        body.setUserData('t');
        clock = new Clock(body);
        }

    }

    public void addPlatformBoost(float x, float y)
    {
        if (doubleNeuronBoost ==null && !isTimePlatformBoostActive && !isTimeClockActive && clock==null && platformBoost ==null && !isTimeDoubleNeuronBoostActive)
        {
            BodyDef def = new BodyDef();
            def.type = BodyDef.BodyType.KinematicBody;
            Body body = world.createBody(def);
            body.setTransform(x, y, 0);
            body.setUserData('b');
            platformBoost = new PlatformBoost(body);
        }

    }

    public void addDoubleNeuronBoost(float x,float y)
    {
        if(doubleNeuronBoost ==null && !isTimePlatformBoostActive && !isTimeClockActive && clock==null && platformBoost ==null && !isTimeDoubleNeuronBoostActive)
        {
            BodyDef def = new BodyDef();
            def.type = BodyDef.BodyType.KinematicBody;
            Body body = world.createBody(def);
            body.setTransform(x, y, 0);
            body.setUserData('2');
            doubleNeuronBoost = new DoubleNeuronBoost(body);

        }



    }
    public void addPlatform(float x, float y,boolean isBoost)
    {

        BodyDef def = new BodyDef();
        PlatformData data = new PlatformData(random.nextBoolean(),isBoost);
        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(x,y,0);
        body.setUserData(data);
        Platform1 platform1 = new Platform1(body);
        platforms.add(platform1);

    }

    public void changeActivelatforms()
    {
        Body body=null;
        Array<Contact> contacts =world.getContactList();
        for(Contact contact:contacts)
        {
            if(contact.isTouching() && contact.getFixtureA()==player.getPlayerPhysicsFixture())
            {
                body=contact.getFixtureB().getBody();
            }
            if(contact.isTouching() && contact.getFixtureB()==player.getPlayerPhysicsFixture())
            {
                body=contact.getFixtureA().getBody();
            }
        }



        if(!isTimePlatformBoostActive) {
            for (Platform pl : platforms) {
                PlatformData data = (PlatformData) pl.getBox().getUserData();
                if (data.isActive() && pl.getBox() != body) {
                    data.isActive = false;

                } else {
                    data.isActive = true;

                }
                if (pl instanceof StartPlatform) {
                    data.isActive = true;

                }

            }
        }
        else
        {
            for (Platform pl : platforms) {
                PlatformData data = (PlatformData) pl.getBox().getUserData();
                if(!data.isBoost) {
                    if (data.isActive() && pl.getBox() != body) {
                        data.isActive = false;

                    } else {
                        data.isActive = true;

                    }
                }
                else
                {
                    data.isActive=true;
                }
                if (pl instanceof StartPlatform) {
                    data.isActive = true;

                }

            }



        }

    }

    public boolean isGrounded()
    {
        Array<Contact> contacts = world.getContactList();
        for(Contact contact:contacts)
        {
            if(contact.isTouching() && (contact.getFixtureA()==player.getPlayerPhysicsFixture()
                    || contact.getFixtureB()==player.getPlayerPhysicsFixture()) && contact.isEnabled())
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

        if(clock!=null) {
            clock.increaseSpeed();
        }
        if(platformBoost!=null)
        {
            platformBoost.increaseSpeed();

        }

    }


}


