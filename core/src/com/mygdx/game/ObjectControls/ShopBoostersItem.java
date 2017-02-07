package com.mygdx.game.ObjectControls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;

/**
 * Created by alexey on 05.02.17.
 */

public class ShopBoostersItem extends ObjectControl {


    private Button button;

    private Texture background;
    private Texture[] booster_level = new Texture[4];
    private int price;
    private BitmapFont price_font;
    private BitmapFont font;
    private GlyphLayout layout;
    private Texture neuron_texture;
    private int current_level;

    public ShopBoostersItem(float x,float y,Texture[] mas)
    {
        super(new Rectangle(x,y,0,0));
        background = new Texture(URL.shop_item_background);
        object.setWidth(background.getWidth());
        object.setHeight(background.getHeight());
        object.setX(object.x-background.getWidth()/2);
        button= new Button(mas,object.getX()+20,object.getY()+background.getHeight()/2-mas[0].getHeight()/2);
        for(int i=0;i<current_level;i++)
        {
            booster_level[i]= new Texture(URL.shop_booster_level_active);
        }
        for(int i=current_level;i<booster_level.length;i++)
        {
            booster_level[i]= new Texture(URL.shop_booster_level_non_active);
        }

        this.current_level=0;


        neuron_texture = new Texture(URL.neuron_points);


        this.price=0;
        price_font=initializeFontStyle(50);
        font=initializeFontStyle(20);

        layout=new GlyphLayout();
        layout.setText(price_font,Integer.toString(price));

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.draw(background,object.getX(),object.getY());
        button.render(sb);
        for (int i=0;i<booster_level.length;i++)
        {
            sb.draw(booster_level[i],object.getX()+150+i*42,object.getY()+background.getHeight()/2-booster_level[i].getHeight()/2);
            font.draw(sb,Integer.toString(25*(i+1))+"%",object.getX()+150+i*42,object.y+20);
        }

        if(current_level<4) {
            price_font.draw(sb, Integer.toString(price), object.x + object.getWidth() / 2 + 100, object.y + object.getHeight() / 2 + layout.height / 2);
            sb.draw(neuron_texture, object.x + object.getWidth() / 2 + 100 + layout.width + 10, object.y + object.getHeight() / 2 - neuron_texture.getHeight() / 2);
        }
        else
        {

            price_font.draw(sb, "Sold", object.x + object.getWidth() / 2 + 100, object.y + object.getHeight() / 2 + layout.height / 2);
        }



    }

    @Override
    public void dispose() {

        button.dispose();
        background.dispose();
        for (int i=0;i<booster_level.length;i++)
        {
            booster_level[i].dispose();
        }
        price_font.dispose();
        neuron_texture.dispose();
        font.dispose();

    }

    @Override
    public void moveTo(float deltaX,float deltaY)
    {
        object.setX(object.x+deltaX);
        object.setY(object.y+deltaY);
        button.moveTo(deltaX,deltaY);

    }

    public void ButtonClickHandle()
    {

        if(!Gdx.input.isTouched() && button.isTouched)
        {
            button.isTouched=false;
            button.OnClick();

        }
        if(Gdx.input.justTouched())
        {


            if(button.isClick(Gdx.input.getX()* GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button.isTouched=true;
            }


        }


    }

    public void setOnClickBuuttonListener(ButtonListener listener)
    {
        button.setOnClickListener(listener);

    }


    public void setPrice(int price) {
        this.price = price;
        layout.setText(price_font,Integer.toString(price));
    }

    private BitmapFont initializeFontStyle(int size)
    {
        BitmapFont font;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(URL.font_Free_mono_bold));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters= GameClass.FONT_CHARACTERS;
        parameter.size=size;
        parameter.color= Color.BLACK;
        font=generator.generateFont(parameter);
        generator.dispose();
        return font;

    }

    public int getCurrent_level() {
        return current_level;
    }

    public void setLevel(int current_level)
    {
        for(int i=0;i<current_level;i++)
        {
            booster_level[i]= new Texture(URL.shop_booster_level_active);
        }
        for(int i=current_level;i<booster_level.length;i++)
        {
            booster_level[i]= new Texture(URL.shop_booster_level_non_active);
        }

        this.current_level=current_level;



    }

    public int getPrice() {
        return price;
    }
}
