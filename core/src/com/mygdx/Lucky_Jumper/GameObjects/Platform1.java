package com.mygdx.Lucky_Jumper.GameObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by alexey on 23.01.17.
 */

public class Platform1 extends Platform {

    private Fixture platformPhysicsFixture;
    private final float weight=0.64f;
    private final float height=0.16f;
    private Fixture platformContactFixture;


    public Platform1(Body box)
    {
        super(box);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(weight,height);
        platformPhysicsFixture=box.createFixture(poly,0);
        poly.dispose();
        box.setLinearVelocity(speed,0);


        EdgeShape pe= new EdgeShape();
        pe.set(-weight,height,weight,height);
        platformContactFixture=box.createFixture(pe,0);
        platformContactFixture.setUserData('c');
        pe.dispose();


    }

    public Body getBox()
    {
        return box;

    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void destroyContactFixture()
    {
        box.destroyFixture(platformContactFixture);
    }


}
