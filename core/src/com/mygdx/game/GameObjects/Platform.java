package com.mygdx.game.GameObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by alexey on 23.01.17.
 */

public class Platform {

    private Body box;
    private Fixture platformPhysicsFixture;
    private final float weight=0.64f;
    private final float height=0.04f;

    public  Platform(Body box)
    {
        this.box=box;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(weight,height);
        platformPhysicsFixture=box.createFixture(poly,0);
        poly.dispose();
        box.setLinearVelocity(MyWorld.SPEED,0);


    }

    public Body getBox()
    {
        return box;

    }
    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

}
