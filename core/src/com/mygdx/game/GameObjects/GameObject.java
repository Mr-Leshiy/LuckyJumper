package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by alexey on 05.01.17.
 */

public abstract class GameObject {

    protected Texture objectTexture;
    protected Rectangle object;

    public GameObject(Texture objectTexture,Rectangle object)
    {
        this.objectTexture=objectTexture;
        this.object=object;
    }

    public abstract void update(float delta); // обновление картинки
    public abstract void redner(SpriteBatch sb); // Отрисовка
    public abstract void dispose();

}
