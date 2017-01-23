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


    public Player(Body box)
    {
        this.box=box;

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(1f,1f);
        playerPhysicsFixture=box.createFixture(poly,1);
        poly.dispose();

        box.setBullet(true);
    }

    public Body getBox() {
        return box;
    }

    public void update(float delta)
    {
        if(isJump)
        {
            box.applyLinearImpulse(0,10,box.getPosition().x,box.getPosition().y,true);
            isJump=false;


        }


    }

    public void jump()
    {
        isJump=true;

    }

}
