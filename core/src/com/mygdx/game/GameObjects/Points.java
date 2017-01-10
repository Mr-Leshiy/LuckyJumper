package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Constants.URL;



/**
 * Created by alexey on 09.01.17.
 */

public class Points extends GameObject {

    private BitmapFont font_points;
    private float speed=5f;
    private float time;
    private static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    public int points;



    public Points(int x,int y)
    {
        super(new Rectangle(x,y,0,0));

       initializeFontStyle();
        points=0;

    }


    @Override
    public void update(float delta) {
        if(!isPaused) {
            time+=delta;
            if(time>1) {
                addPoints();
                time=0;
            }
        }




    }

    @Override
    public void redner(SpriteBatch sb) {
        font_points.draw(sb, Integer.toString(points),object.getX(),object.getY());

    }

    @Override
    public void dispose() {

        font_points.dispose();

    }

    @Override
    public void pause() {
        isPaused=true;

    }

    @Override
    public void resume() {
        isPaused=false;

    }

    private void initializeFontStyle()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(URL.font_Free_mono_bold));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.characters=FONT_CHARACTERS;
        parameter.size=30;
        parameter.color= Color.BROWN;
        font_points=generator.generateFont(parameter);
        generator.dispose();


    }

    private void addPoints()
    {

       points+=speed;



    }
}
