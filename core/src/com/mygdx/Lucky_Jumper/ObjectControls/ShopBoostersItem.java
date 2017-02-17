package com.mygdx.Lucky_Jumper.ObjectControls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Lucky_Jumper.Constants.URL;
import com.mygdx.Lucky_Jumper.GameClass;

/**
 * Created by alexey on 05.02.17.
 */

public class ShopBoostersItem extends ObjectControl {


    private com.mygdx.Lucky_Jumper.ObjectControls.Button button;

    private Texture background;
    private int price;
    private BitmapFont price_font;
    private BitmapFont font;
    private GlyphLayout layout;
    private Texture neuron_texture;
    private int current_level;

    private Texture booster_level_enaible;
    private Texture booster_level_desaible;


    public ShopBoostersItem(float x,float y,Texture[] mas)
    {
        super(new Rectangle(x,y,0,0));
        background = new Texture(URL.shop_item_background);
        object.setWidth(background.getWidth());
        object.setHeight(background.getHeight());
        object.setX(object.x-background.getWidth()/2);
        button= new com.mygdx.Lucky_Jumper.ObjectControls.Button(mas,object.getX()+20,object.getY()+background.getHeight()/2-mas[0].getHeight()/2);

        this.current_level=0;


        neuron_texture = new Texture(URL.neuron_points);

        booster_level_enaible = new Texture(URL.shop_booster_level_non_active);
        booster_level_desaible = new Texture(URL.shop_booster_level_active);

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
        for(int i=0;i<current_level;i++)
        {
            sb.draw(booster_level_desaible,object.getX()+150+i*42,object.getY()+background.getHeight()/2-booster_level_desaible.getHeight()/2);
            font.draw(sb,Integer.toString(25*(i+1))+"%",object.getX()+150+i*42,object.y+20);
        }
        for(int i=current_level;i<4;i++)
        {
            sb.draw(booster_level_enaible,object.getX()+150+i*42,object.getY()+background.getHeight()/2-booster_level_enaible.getHeight()/2);
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
        booster_level_desaible.dispose();
        booster_level_enaible.dispose();
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

    public void setOnClickBuuttonListener(com.mygdx.Lucky_Jumper.ObjectControls.ButtonListener listener)
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

        this.current_level=current_level;



    }

    public int getPrice() {
        return price;
    }
}
