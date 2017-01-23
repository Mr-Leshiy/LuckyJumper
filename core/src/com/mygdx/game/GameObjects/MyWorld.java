package com.mygdx.game.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;


public class MyWorld {

    private World world;
    private Player player;
    private List<Platform> platforms;
    boolean isGrounded=true;


    public Player getPlayer() {
        return player;
    }

    public MyWorld()
    {
        world = new World(new Vector2(0,-50),true);
        world.setContactListener(new MyContactListener(this));

        platforms = new ArrayList<Platform>();

        createWorld();

    }

    private void createWorld()
    {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.DynamicBody;

        Body bodyp = world.createBody(def);
        bodyp.setTransform(100,150,-1);
        player = new Player(bodyp);

        def.type= BodyDef.BodyType.KinematicBody;

        for(int i=0;i<20;i++)
        {
            Body body =world.createBody(def);
            body.setTransform(i*64,100,0);

            platforms.add(new Platform(body));

        }
    }

    public List<Platform> getPlatforms()
    {
        return platforms;

    }

    public void update(float delta)
    {
        player.update(delta);
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


}
