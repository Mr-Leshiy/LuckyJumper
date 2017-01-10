package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by alexey on 08.01.17.
 */

public class BackGround extends GameObject {

    public static final float speed=0.5f;
    public boolean isScroll;
    private Texture objectTextures;


    public BackGround(Texture texture,int x,int y,boolean isScroll)
    {

        super(new Rectangle(x,y,texture.getWidth(),texture.getHeight()));
        objectTextures=texture;
        this.isScroll=isScroll;

    }
    @Override
    public void update(float delta) {
        if(isScroll) {
            scrolling();
        }
    }

    @Override
    public void redner(SpriteBatch sb) {
            sb.draw(objectTextures, object.getX(), object.getY(), object.getWidth(), object.getHeight());
    }

    @Override
    public void dispose() {
            objectTextures.dispose();
    }

    @Override
    public void pause() {
        isScroll=false;

    }

    @Override
    public void resume() {
        isScroll=true;

    }

    private void scrolling()
    {
        object.setX(object.getX()-speed);
       if(object.getX()<-objectTextures.getWidth())
        {
            object.setX(objectTextures.getWidth()-speed);

        }


    }


}
