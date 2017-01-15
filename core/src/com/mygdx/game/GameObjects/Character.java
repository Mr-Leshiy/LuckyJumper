package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by alexey on 08.01.17.
 */

public class Character extends GameObject {


    Texture[] objectTextures;



    private final float change_time=0.2f;

    public Character(Texture[] textures,int x,int y)
    {
        super(new Rectangle(x,y,textures[0].getWidth(),textures[0].getHeight()));




    }

    @Override
    public void update(float delta) {





    }

    @Override
    public void render(SpriteBatch sb) {






    }

    @Override
    public void dispose() {

        for(Texture texture:objectTextures)
        {
            texture.dispose();

        }

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }



}
