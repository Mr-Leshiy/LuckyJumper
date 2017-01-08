package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by alexey on 08.01.17.
 */

public class BackGround extends GameObject {

    public static final float speed=1f;
    public boolean isScroll;
    private Texture objectTextures;


    public BackGround(Texture textures,int x,int y,boolean isScroll)
    {

        super(new Rectangle(x,y,textures.getWidth(),textures.getHeight()));
        objectTextures=textures;
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

    private void scrolling()
    {
        object.setX(object.getX()-speed);
       if(object.getX()<-objectTextures.getWidth())
        {
            object.setX(objectTextures.getWidth()-speed);

        }


    }


}
