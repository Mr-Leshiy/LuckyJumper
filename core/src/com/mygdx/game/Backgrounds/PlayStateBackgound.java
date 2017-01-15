package com.mygdx.game.Backgrounds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;

import java.awt.Point;


/**
 * Created by alexey on 15.01.17.
 */

public class PlayStateBackgound {

    private Texture[] backgroundTextures;
    private Rectangle[] coordinates;
    private static final float speed_scrolling=0.5f;
    private boolean isScrool;

    public PlayStateBackgound()
    {
        backgroundTextures=new Texture[]{new Texture(URL.play_state_background1),new Texture(URL.play_state_background2)};
        isScrool=true;
        coordinates= new Rectangle[]{new Rectangle(0,0,0,0), new Rectangle(GameClass.WIDTH,0,0,0)};

    }

    public void update(float delta)
    {
        if(isScrool)
        {
            scrolling();
        }
    }

    public void render(SpriteBatch sb)
    {

        sb.draw(backgroundTextures[0],(int)coordinates[0].getX(),(int)coordinates[0].getY(),backgroundTextures[0].getWidth(),backgroundTextures[0].getHeight());
        sb.draw(backgroundTextures[1],(int)coordinates[1].getX(),(int)coordinates[1].getY(),backgroundTextures[1].getWidth(),backgroundTextures[1].getHeight());

    }

    public void dispose()
    {
        backgroundTextures[0].dispose();
        backgroundTextures[1].dispose();
    }

    public void pause()
    {
        isScrool=false;
    }
    public void resume()
    {
        isScrool=true;

    }


    private void scrolling()
    {
        for (Rectangle object:coordinates) {
            object.setX(object.getX() - speed_scrolling);
            if (object.getX() < -backgroundTextures[0].getWidth()) {
                object.setX(backgroundTextures[0].getWidth() - speed_scrolling);

            }
        }


    }



}
