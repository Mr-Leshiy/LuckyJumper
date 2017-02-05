package com.mygdx.game.ObjectControls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public ShopBoostersItem(float x,float y,Texture[] mas)
    {
        super(new Rectangle(x,y,0,0));
        background = new Texture(URL.shop_item_background);
        object.setWidth(background.getWidth());
        object.setHeight(background.getHeight());
        object.setX(object.x-background.getWidth()/2);
        button= new Button(mas,object.getX()+20,object.getY()+background.getHeight()/2-mas[0].getHeight()/2);

        button.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {

            }
        });

        for(int i=0;i<booster_level.length;i++)
        {
            booster_level[i]= new Texture(URL.shop_booster_level_non_active);
        }

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

    @Override
    public void moveTo(float deltaX,float deltaY)
    {
        object.setX(object.x+deltaX);
        object.setY(object.y+deltaY);
        button.moveTo(deltaX,deltaY);

    }

}
