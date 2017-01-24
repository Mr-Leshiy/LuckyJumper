package com.mygdx.game.GameObjects;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by alexey on 22.01.17.
 */

public class Player {



    private boolean isJump=false;
    private Body box;
    private Fixture playerPhysicsFixture;
    private final float weight=0.4f;
    private final float height=0.4f;


    public Player(Body box)
    {
        this.box=box;

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(weight,height);
        playerPhysicsFixture=box.createFixture(poly,1);
        playerPhysicsFixture.setFriction(0);
        poly.dispose();
    }

    public Body getBox() {
        return box;
    }

    public void update(float delta)
    {


    }

    public Fixture getPlayerPhysicsFixture() {
        return playerPhysicsFixture;
    }

    public void jump()
    {
        box.applyLinearImpulse(0,5F,box.getPosition().x,box.getPosition().y,true);
    }
    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

}
