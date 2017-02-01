package com.mygdx.game.GameObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by alexey on 23.01.17.
 */

public class Platform {

    private Body box;
    private Fixture platformPhysicsFixture;
    private final float weight=0.64f;
    private final float height=0.16f;
    public static float speed=-1.2f;;
    private Fixture platformContactFixture;


    public  Platform(Body box)
    {
        this.box=box;
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
    public Fixture getPlatformContactFixture() {
        return platformContactFixture;
    }
    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public void increaseSpeed()
    {
        speed-=0.3f;
        box.setLinearVelocity(speed,0);

    }

    public void destroyContactixture()
    {
        box.destroyFixture(platformContactFixture);
    }


}
