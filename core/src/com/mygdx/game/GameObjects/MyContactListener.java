package com.mygdx.game.GameObjects;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by alexey on 23.01.17.
 */

public class MyContactListener implements ContactListener {

    MyWorld world;

    public MyContactListener(MyWorld world)
    {
        this.world=world;

    }

    @Override
    public void beginContact(Contact contact) {

        world.isGrounded=true;

    }

    @Override
    public void endContact(Contact contact) {

        world.isGrounded=false;

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
