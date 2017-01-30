package com.mygdx.game.ObjectControls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;


/**
 * Created by alexey on 09.01.17.
 */

public class Points extends ObjectControl {

    private BitmapFont font_points;
    private float speed=1.2f;
    private float time;
    public int points;

    public Points(float x,float y)
    {
        super(new Rectangle(x,y,0,0));

       initializeFontStyle();
        points=0;

    }


    @Override
    public void update(float delta) {

            time+=delta;
            if(time>2.5f/speed) {
                points++;
                time=0;

        }





    }

    @Override
    public void render(SpriteBatch sb) {
        font_points.draw(sb, "score: "+Integer.toString(points),object.getX(),object.getY());

    }

    @Override
    public void dispose() {

        font_points.dispose();

    }


    private void initializeFontStyle()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(URL.font_Free_mono_bold));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.characters= GameClass.FONT_CHARACTERS;
        parameter.size=20;
        parameter.color= Color.BROWN;
        font_points=generator.generateFont(parameter);
        generator.dispose();


    }

    public void increaseSpeed()
    {
        speed+=0.3f;

    }
}
