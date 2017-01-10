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

    BitmapFont font_points;

    public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";


    public Points(int x,int y)
    {
        super(new Rectangle(x,y,0,0));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(URL.font_Free_mono_bold));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.characters=FONT_CHARACTERS;
        parameter.size=30;
        parameter.color= Color.BROWN;
        font_points=generator.generateFont(parameter);
        generator.dispose();








    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void redner(SpriteBatch sb) {
        font_points.draw(sb,"123",object.getX(),object.getY());

    }

    @Override
    public void dispose() {

        font_points.dispose();

    }
}
