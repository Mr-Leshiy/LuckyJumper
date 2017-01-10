package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.BackGround;
import com.mygdx.game.GameObjects.Button;
import com.mygdx.game.GameObjects.ButtonListener;



/**
 * Created by alexey on 05.01.17.
 */

public class MenuState extends State {  // Класс меню

    private BackGround background;
    private Button button_play;
    private Button button_settings;


    public MenuState(final GameStateManager gsm) {
        super(gsm);

        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);

        background = new BackGround(new Texture(URL.menu_state_background),0,0,false);

        Texture[] mas = {new Texture(URL.button_play),new Texture(URL.button_play_pressed)};
        button_play =new Button(mas,GameClass.WIDTH/2-140,GameClass.HEIGTH/2+50);
        button_play.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener()
            {
                gsm.set(new PlayState(gsm));
            }
        });

        Texture[] mas2 = {new Texture(URL.button_setting),new Texture(URL.button_setting_pressed)};
        button_settings =new Button(mas2,GameClass.WIDTH/2-140,GameClass.HEIGTH/2-50);
        button_settings.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener()
            {
                //gsm.set(new SettingsState(gsm));
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


        if(Gdx.input.justTouched())
        {


            if(button_play.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_play.isTouched=true;
            }
            if(button_settings.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_settings.isTouched=true;

            }

        }



    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void redner(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        background.redner(sb);
        button_play.redner(sb);
        button_settings.redner(sb);
        sb.end();
    }

    @Override
    public void dispose() {

        background.dispose();
        button_play.dispose();
        button_settings.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

}





