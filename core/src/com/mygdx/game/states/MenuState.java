package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Backgrounds.MenuBackground;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.ObjectControls.Button;
import com.mygdx.game.ObjectControls.ButtonListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by alexey on 05.01.17.
 */

public class MenuState extends State {  // Класс меню

    private MenuBackground background;
    private Button button_play;
    private Button button_settings;
    private Button button_shop;



    public MenuState(final GameStateManager gsm,MenuBackground b) {
        super(gsm);

        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);

        if(b==null)
        {
            background = new MenuBackground();
        }
        else
        {
            background=b;

        }
        Texture[] mas = {new Texture(URL.button_play),new Texture(URL.button_play_pressed)};
        button_play =new Button(mas,GameClass.WIDTH/2-mas[0].getWidth()/2,GameClass.HEIGTH/2+50);
        button_play.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener()
            {
                if(GameClass.FirstTimeRunning) {
                    gsm.set(new TrainingState(gsm, new PlayState(gsm) ));
                    background.dispose();
                    GameClass.FirstTimeRunning=false;
                }
                else
                {
                    gsm.set(new PlayState(gsm));
                    background.dispose();



                }
            }
        });

        Texture[] mas2 = {new Texture(URL.button_setting),new Texture(URL.button_setting_pressed)};
        button_settings =new Button(mas2,GameClass.WIDTH/2-mas2[0].getWidth()/2,GameClass.HEIGTH/2-150);
        button_settings.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener()
            {
                gsm.set(new SettingsState(gsm,background));
            }
        });

        Texture[] mas3 = {new Texture(URL.button_shop),new Texture(URL.button_shop_pressed)};
        button_shop = new Button(mas3,GameClass.WIDTH/2-mas2[0].getWidth()/2,GameClass.HEIGTH/2-50);
        button_shop.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.set(new ShopState(gsm,background));

            }
        });

    }

    @Override
    public void handleInput()
    {
        if(!Gdx.input.isTouched() && button_play.isTouched)
        {
            button_play.isTouched=false;
            button_play.OnClick();

        }
        if(!Gdx.input.isTouched() && button_settings.isTouched)
        {
            button_settings.isTouched=false;
            button_settings.OnClick();
        }
        if(!Gdx.input.isTouched() && button_shop.isTouched)
        {
            button_shop.isTouched=false;
            button_shop.OnClick();
        }


        if(Gdx.input.justTouched())
        {


            if(button_play.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_play.isTouched=true;
            }
            if(button_settings.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_settings.isTouched=true;

            }
            if(button_shop.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_shop.isTouched=true;

            }


        }



    }

    @Override
    public void update(float delta) {
        handleInput();
        background.update(delta);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        background.render(sb);
        button_play.render(sb);
        button_settings.render(sb);
        button_shop.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {

        button_play.dispose();
        button_settings.dispose();
        button_shop.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

}





