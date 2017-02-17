package com.mygdx.Lucky_Jumper.ObjectControls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Lucky_Jumper.Constants.URL;
import com.mygdx.Lucky_Jumper.GameClass;


/**
 * Created by alexey on 09.01.17.
 */

public class Points extends ObjectControl {

    private BitmapFont font_points;
    private int points;
    private GlyphLayout layout;

    public Points(float x,float y)
    {
        super(new Rectangle(x,y,0,0));

        layout =new GlyphLayout();
        font_points=initializeFontStyle();
        points=0;
        layout.setText(font_points,"score: "+Integer.toString(points));


    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sb) {

        font_points.draw(sb, "score: "+Integer.toString(points),object.getX()-layout.width/2,object.getY());

    }

    @Override
    public void dispose() {
        font_points.dispose();
    }


    private BitmapFont initializeFontStyle()
    {
        BitmapFont font;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(URL.font_Free_mono_bold));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.characters= GameClass.FONT_CHARACTERS;
        parameter.size=20;
        parameter.color= Color.BROWN;
        font=generator.generateFont(parameter);
        generator.dispose();

        return font;




    }

    public void addPoints()
    {
        points++;
        layout.setText(font_points,"score: "+Integer.toString(points));
    }

    public int getPoints() {
        return points;
    }
}
