package com.mygdx.game.GameObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import javax.swing.text.StyledEditorKit;

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

       if(contact.getFixtureA().getBody().getUserData() instanceof Boolean && contact.getFixtureA().getBody().getUserData()!=null)
       {
           if(!(Boolean) contact.getFixtureA().getBody().getUserData())
           {
               contact.setEnabled(false);

           }


       }
        if(contact.getFixtureB().getBody().getUserData() instanceof Boolean && contact.getFixtureB().getBody().getUserData()!=null)
        {
            if(!(Boolean) contact.getFixtureB().getBody().getUserData())
            {
                contact.setEnabled(false);

            }


        }


    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

        Body body=null;

        if(contact.getFixtureA()==world.getPlayer().getPlayerPhysicsFixture() || contact.getFixtureB()==world.getPlayer().getPlayerPhysicsFixture()) {

            if (contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureA().getBody().getUserData()!=null &&
                    contact.getFixtureA().getBody().getUserData().equals('n'))
            {

                body=contact.getFixtureA().getBody();

            }
            if (contact.getFixtureB().getBody().getUserData() != null && contact.getFixtureB().getBody().getUserData()!=null
                    && contact.getFixtureB().getBody().getUserData().equals('n')) {

                body=contact.getFixtureB().getBody();

            }


            if(body!=null)
            {
              body.setUserData('d');

            }
        }

    }
}
