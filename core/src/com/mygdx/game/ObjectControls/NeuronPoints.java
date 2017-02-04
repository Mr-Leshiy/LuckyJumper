package com.mygdx.game.ObjectControls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;

/**
 * Created by alexey on 31.01.17.
 */

public class NeuronPoints extends ObjectControl {

    private Texture neuron_texture;
    private BitmapFont font_points;
    private int points;
    private GlyphLayout layout;

    public NeuronPoints(float x, float y)
    {
        super(new Rectangle(x,y,0,0));
        neuron_texture = new Texture(URL.neuron_points);
        layout=new GlyphLayout();
        initializeFontStyle();
        this.points=0;

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sb) {

        layout.setText(font_points,Integer.toString(points));
        font_points.draw(sb,Integer.toString(points),object.getX()-layout.width,object.getY());
        sb.draw(neuron_texture,object.getX()+10,object.getY()-30);


    }

    @Override
    public void dispose() {

        neuron_texture.dispose();
        font_points.dispose();

    }

    public int getPoints() {
        return points;
    }

    public void addPoints()
    {
        points++;
    }
    private void initializeFontStyle()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(URL.font_Free_mono_bold));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters= GameClass.FONT_CHARACTERS;
        parameter.size=20;
        parameter.color= Color.BROWN;
        font_points=generator.generateFont(parameter);
        generator.dispose();


    }

    public void setPoint(int points)
    {
        this.points=points;

    }

}
