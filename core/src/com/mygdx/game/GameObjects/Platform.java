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

    public static final float SPEED = 10f;

    public  Platform(Body box)
    {
        this.box=box;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(64f,8f);
        platformPhysicsFixture=box.createFixture(poly,1);
        poly.dispose();

    }

    public Body getBox()
    {
        return box;

    }

}
