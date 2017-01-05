package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.Button;
import com.mygdx.game.GameObjects.ButtonListener;

import static javax.swing.text.html.HTML.Tag.HEAD;


/**
 * Created by alexey on 05.01.17.
 */

public class MenuState extends State {  // Класс меню

    private Texture background;
    private Button button_play;
    private Button button_settings;



    public MenuState(final GameStateManager gsm) {
        super(gsm);

        background = new Texture("bground.png");
        button_play =new Button(new Texture("button_play.png"),200,300);
        button_play.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.set(new PlayState(gsm));
            }
        });
        button_settings =new Button(new Texture("button_play.png"),100,300);
        button_settings.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.set(new SettingsState(gsm));
            }
        });

    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {

            if(button_play.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_play.OnClick();
            }
            if(button_settings.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_settings.OnClick();
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
