package com.mygdx.game.GameObjects;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by alexey on 22.01.17.
 */

public class Player {



    private boolean isJump=false;
    private Body box;
    private Fixture playerPhysicsFixture;
    private Fixture playerContactFixture;
    private final float weight=0.35f;
    private final float height=0.35f;


    public Player(Body box)
    {
        this.box=box;

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(weight,height);
        playerPhysicsFixture=box.createFixture(poly,1);
        playerPhysicsFixture.setFriction(0f);
        poly.dispose();

        CircleShape circle = new CircleShape();
        circle.setRadius(weight);
        circle.setPosition(new Vector2(0,0f));
        playerContactFixture=box.createFixture(circle,0);
        playerContactFixture.setFriction(0);
        circle.dispose();



        box.setBullet(true);
    }

    public Body getBox() {
        return box;
    }

    public void update(float delta)
    {
        box.getPosition().x=2;


    }

    public Fixture getPlayerPhysicsFixture() {
        return playerPhysicsFixture;
    }

    public void jump()
    {
        box.applyLinearImpulse(0.1f,2.5F,box.getPosition().x,box.getPosition().y,true);
    }
    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

}
