package com.mygdx.Lucky_Jumper.ObjectControls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Lucky_Jumper.Resources.TexturesResources;

import java.util.List;


/**
 * Created by alexey on 05.01.17.
 */

public abstract class ObjectControl {


    protected Rectangle object;
    protected TexturesResources resources;



    public ObjectControl(Rectangle object,TexturesResources resources)
    {
        this.object=object;
        this.resources=resources;

    }

    public abstract void update(float delta); // обновление картинки
    public abstract void render(SpriteBatch sb); // Отрисовка
    public abstract void dispose();

    public void moveTo(float deltaX,float deltaY)
    {
        object.setX(object.x+deltaX);
        object.setY(object.y+deltaY);

    }


}
