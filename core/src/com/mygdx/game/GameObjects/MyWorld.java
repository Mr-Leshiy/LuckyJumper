package com.mygdx.game.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyWorld {

    private Random random;
    private World world;
    private Player player;
    private List<Platform> platforms;
    boolean isGrounded=true;
    public static final float SPEED=-1.2F;
    private Body endWorld;




    public Player getPlayer() {
        return player;
    }

    public MyWorld()
    {
        world = new World(new Vector2(0,-10f),true);
        random = new Random();
        world.setContactListener(new MyContactListener(this));
        platforms = new ArrayList<Platform>();
        createWorld();

    }

    private void createWorld()
    {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.DynamicBody;
        Body bodyp = world.createBody(def);
        bodyp.setTransform(3.5f,3f,0);
        player = new Player(bodyp);
        for(int i=0;i<20;i++) {

            addPlatform(i*2.3f,1);

        }

        def.type= BodyDef.BodyType.StaticBody;

        EdgeShape end= new EdgeShape();
        end.set(0,0,8,0);
        endWorld=world.createBody(def);
        endWorld.setTransform(0,0,0);
        endWorld.createFixture(end,0);

    }

    public List<Platform> getPlatforms()
    {
        return platforms;
    }

    public void update(float delta)
    {
        player.update(delta);
        deletePlatforms();
        world.step(delta,4,4);

    }

    public World getWorld()
    {
        return world;
    }

    public boolean isGrounded()
    {
        return isGrounded;
    }

    public void deletePlatforms()
    {
       for(int i=0;i<platforms.size();i++)
        {
            if(platforms.get(i).getBox().getPosition().x<-100)
            {
                platforms.get(i).getBox().setActive(false);
                world.destroyBody(platforms.get(i).getBox());
                platforms.remove(i);

            }


        }



    }

    public Platform addPlatform(float x, float y)
    {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.KinematicBody;
        Body body =world.createBody(def);
        body.setTransform(x,y,0);
        body.setUserData(random.nextBoolean());
        Platform platform = new Platform(body);
        platforms.add(platform);
        return platform;
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


}
