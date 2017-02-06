package com.mygdx.game.GameObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by alexey on 01.02.17.
 */

public class StartPlatform extends Platform {

    private Fixture platformPhysicsFixture;
    private final float weight=3.2f;
    private final float height=0.16f;


    public StartPlatform(Body box)
    {
        super(box);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(weight,height);
        platformPhysicsFixture=box.createFixture(poly,0);
        poly.dispose();
        box.setLinearVelocity(speed,0);



    }

    @Override
    public void destroyContactFixture() {
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public float getHeight() {
        return height;
    }


}
