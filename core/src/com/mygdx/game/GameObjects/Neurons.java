package com.mygdx.game.GameObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by alexey on 31.01.17.
 */

public class Neurons {

    private Body body;
    private final float weight=0.17f;
    private final float height=0.17f;
    private Fixture neuronFixture;



    public Neurons(Body box)
    {
        this.body=box;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(weight,height);
        neuronFixture=box.createFixture(poly,0);
        poly.dispose();
        body.setLinearVelocity(Platform.speed,0);
    }

    public Body getBody() {
        return body;
    }
    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public Fixture getNeuronFixture() {
        return neuronFixture;
    }
}
