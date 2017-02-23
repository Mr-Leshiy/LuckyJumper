package com.mygdx.Lucky_Jumper.GameObjects;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.Lucky_Jumper.states.PlayState;

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

        if(contact.getFixtureA()==world.getPlayer().getPlayerPhysicsFixture() || contact.getFixtureB()==world.getPlayer().getPlayerPhysicsFixture())
        {
            if((contact.getFixtureA().getBody().getUserData()!=null && contact.getFixtureA().getBody().getUserData().equals('e')) ||
                    (contact.getFixtureB().getBody().getUserData()!=null && contact.getFixtureB().getBody().getUserData().equals('e'))
                    )
            {
                world.playerDead=true;

            }

        }
    }

    @Override
    public void endContact(Contact contact) {


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

            if (contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureA().getBody().getUserData() instanceof PlatformData) {
                PlatformData data = (PlatformData) contact.getFixtureA().getBody().getUserData();

                if (!data.isActive()) {

                    contact.setEnabled(false);

                }
            }
            if (contact.getFixtureB().getBody().getUserData() != null && contact.getFixtureB().getBody().getUserData() instanceof PlatformData) {
                PlatformData data = (PlatformData) contact.getFixtureB().getBody().getUserData();
                if (!data.isActive()) {
                    contact.setEnabled(false);

                }
            }


        if (contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureA().getBody().getUserData().equals('n'))
        {
            contact.setEnabled(false);
            contact.getFixtureA().getBody().setUserData('d');

        }
        if (contact.getFixtureB().getBody().getUserData() != null  && contact.getFixtureB().getBody().getUserData().equals('n')) {

            contact.setEnabled(false);
            contact.getFixtureB().getBody().setUserData('d');
        }

        if (contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureA().getBody().getUserData().equals('t'))
        {
            contact.setEnabled(false);
            world.isTimeClockActive=true;
            world.time= PlayState.clock_time;


        }
        if (contact.getFixtureB().getBody().getUserData() != null  && contact.getFixtureB().getBody().getUserData().equals('t')) {

            contact.setEnabled(false);
            world.isTimeClockActive=true;
            world.time= PlayState.clock_time;
        }
        if (contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureA().getBody().getUserData().equals('b'))
        {
            contact.setEnabled(false);
            world.isTimePlatformBoostActive=true;
            world.time=PlayState.platform_boost_time;


        }
        if (contact.getFixtureB().getBody().getUserData() != null  && contact.getFixtureB().getBody().getUserData().equals('b')) {

            contact.setEnabled(false);
            world.isTimePlatformBoostActive=true;
            world.time=PlayState.platform_boost_time;
        }
        if (contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureA().getBody().getUserData().equals('2'))
        {
            contact.setEnabled(false);
            world.isTimeDoubleNeuronBoostActive=true;
            world.time=PlayState.double_neuron_boost_time;


        }
        if (contact.getFixtureB().getBody().getUserData() != null  && contact.getFixtureB().getBody().getUserData().equals('2')) {

            contact.setEnabled(false);
            world.isTimeDoubleNeuronBoostActive=true;
            world.time=PlayState.double_neuron_boost_time;
        }


        if (contact.isEnabled() && (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals('c')))
        {
            contact.setEnabled(false);
            contact.getFixtureA().setUserData('d');

        }
        if (contact.isEnabled() && (contact.getFixtureB().getUserData() != null  && contact.getFixtureB().getUserData().equals('c'))) {

            contact.setEnabled(false);
            contact.getFixtureB().setUserData('d');

        }


    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {





    }
}
