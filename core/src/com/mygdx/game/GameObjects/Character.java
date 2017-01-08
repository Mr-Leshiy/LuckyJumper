package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by alexey on 08.01.17.
 */

public class Character extends GameObject {

    private int object_state;
    private float time;
    Texture[] objectTextures;

    private final float change_time=0.2f;

    public Character(Texture[] textures,int x,int y)
    {
        super(new Rectangle(x,y,textures[0].getWidth(),textures[0].getHeight()));
        object_state=0;
        time=0;


    }

    @Override
    public void update(float delta) {

        time+=delta;
        if(time>change_time)
        {

            object_state++;
            time=0;
            if(object_state>objectTextures.length-1)
            {
                object_state=0;

            }



        }

    }

    @Override
    public void redner(SpriteBatch sb) {

            sb.draw(objectTextures[object_state],object.getX(),object.getY(),object.getWidth(),object.getHeight());




    }

    @Override
    public void dispose() {

        for(Texture texture:objectTextures)
        {
            texture.dispose();

        }

    }

    public void jump()
    {



    }


}
