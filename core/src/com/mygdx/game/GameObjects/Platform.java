package com.mygdx.game.GameObjects;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by alexey on 01.02.17.
 */

public abstract class Platform {

    protected Body box;
    public static float speed=-1.8f;

    public Platform(Body box)
    {
        this.box=box;
    }

    public Body getBox() {
        return box;
    }

    abstract public void destroyContactFixture();
    abstract public float getWeight();
    abstract public float getHeight();

}
