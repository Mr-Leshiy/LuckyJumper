package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants.Constants;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.Button;
import com.mygdx.game.GameObjects.ButtonListener;



/**
 * Created by alexey on 05.01.17.
 */

public class MenuState extends State {  // Класс меню

    private Texture background;
    private Button button_play;
    private Button button_settings;



    public MenuState(final GameStateManager gsm) {
        super(gsm);

        background = new Texture(Constants.menu_state_background);

        button_play =new Button(new Texture(Constants.button_play),GameClass.WIDTH/2-140,GameClass.HEIGTH/2+50);
        button_play.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener()
            {
                gsm.set(new PlayState(gsm));
            }
        });

        button_settings =new Button(new Texture(Constants.button_setting),GameClass.WIDTH/2-140,GameClass.HEIGTH/2-50);
        button_settings.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener()
            {
                gsm.set(new SettingsState(gsm));
            }
        });


    }

    @Override
    public void handleInput()
    {
        if(!Gdx.input.isTouched() && button_play.isTouched)
        {
            button_play.setTexture(new Texture(Constants.button_play));
            button_play.isTouched=false;
            button_play.OnClick();

        }
        if(!Gdx.input.isTouched() && button_settings.isTouched)
        {
            button_settings.setTexture(new Texture(Constants.button_setting));
            button_settings.isTouched=false;
            button_settings.OnClick();

        }


        if(Gdx.input.justTouched())
        {


            if(button_play.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_play.setTexture(new Texture(Constants.button_play_pressed));
                button_play.isTouched=true;


            }
            if(button_settings.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_settings.setTexture(new Texture(Constants.button_setting_pressed));
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
        sb.begin();
        sb.draw(background,0,0, GameClass.WIDTH, GameClass.HEIGTH);
        sb.end();
        button_play.redner(sb);
        button_settings.redner(sb);

    }

    @Override
    public void dispose() {

        background.dispose();
        button_play.dispose();
        button_settings.dispose();
    }


}
