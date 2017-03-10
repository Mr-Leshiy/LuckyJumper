package com.mygdx.Lucky_Jumper.ObjectControls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Lucky_Jumper.Resources.TexturesResources;
import com.mygdx.Lucky_Jumper.Resources.URL;
import com.mygdx.Lucky_Jumper.GameClass;

/**
 * Created by alexey on 31.01.17.
 */

public class NeuronPoints extends ObjectControl {

    private BitmapFont font_points;
    private int points;
    private GlyphLayout layout;


    public NeuronPoints(float x, float y, TexturesResources resources)
    {
        super(new Rectangle(x,y,0,0),resources);
        layout=new GlyphLayout();
        font_points=initializeFontStyle();
        this.points=0;

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sb) {

        layout.setText(font_points,Integer.toString(points));
        font_points.draw(sb,Integer.toString(points),object.getX()-layout.width,object.getY());
        sb.draw(resources.neuron_points,object.getX()+10,object.getY()-30);


    }

    @Override
    public void dispose() {

        font_points.dispose();

    }

    public int getPoints() {
        return points;
    }

    public void addPoints()
    {
        points++;
    }
    private BitmapFont initializeFontStyle()
    {
        BitmapFont font;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(URL.font_Free_mono_bold));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters= GameClass.FONT_CHARACTERS;
        parameter.size=20;
        parameter.color= Color.BROWN;
        font=generator.generateFont(parameter);
        generator.dispose();
        return font;

    }
    public void setPoint(int points)
    {
        this.points=points;

    }

}
