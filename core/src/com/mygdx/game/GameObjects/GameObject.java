package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;


/**
 * Created by alexey on 05.01.17.
 */

public abstract class GameObject {


    protected Rectangle object;
    protected boolean isPaused;

    public GameObject(Rectangle object)
    {

        this.object=object;
        isPaused=false;
    }

    public abstract void update(float delta); // обновление картинки
    public abstract void render(SpriteBatch sb); // Отрисовка
    public abstract void dispose();
    public abstract void pause();
    public abstract void resume();

}
