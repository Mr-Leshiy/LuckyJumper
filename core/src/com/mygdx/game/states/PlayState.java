package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.BackGround;
import com.mygdx.game.GameObjects.Button;
import com.mygdx.game.GameObjects.ButtonListener;
import com.mygdx.game.GameObjects.Character;
import com.mygdx.game.GameObjects.GameObject;
import com.mygdx.game.GameObjects.Points;

import java.util.List;

/**
 * Created by alexey on 05.01.17.
 */

public class PlayState extends State{

    BackGround[] background;
    Button button_pause;
    Points points;
    Texture fon;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);

        background = new BackGround[2];
        background[0]=new BackGround(new Texture(URL.play_state_background1),0,0,true);
        background[1]= new BackGround(new Texture(URL.play_state_background2),GameClass.WIDTH,0,true);
        fon = new Texture("backgrounds/darker.png");


        Texture[] mas = {new Texture(URL.button_pause), new Texture(URL.button_pause_pressed)} ;
        points = new Points(GameClass.WIDTH/2-50,GameClass.HEIGTH-20);
        button_pause = new Button(mas,10,GameClass.HEIGTH-50);

        button_pause.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                pause();
            }
        });



    }

    @Override
    protected void handleInput() {

        if(!Gdx.input.isTouched() && button_pause.isTouched)
        {

            button_pause.isTouched=false;
            button_pause.OnClick();


        }

        if(Gdx.input.isTouched())
        {

            if(button_pause.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_pause.isTouched=true;
            }
            if(isPaused)
            {
                resume();
            }

        }

    }

    @Override
    public void update(float delta) {
        handleInput();
        background[0].update(delta);
        background[1].update(delta);
        points.update(delta);


    }

    @Override
    public void redner(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        background[0].redner(sb);
        background[1].redner(sb);
        button_pause.redner(sb);
        points.redner(sb);
        if(isPaused) {
            sb.draw(fon, 0, 0, fon.getWidth(), fon.getHeight());
        }
        sb.end();

    }

    @Override
    public void dispose() {

        background[0].dispose();
        background[1].dispose();
        button_pause.dispose();
        points.dispose();
        fon.dispose();


    }

    @Override
    public void pause() {

        isPaused=true;

        background[0].pause();
        background[1].pause();
        button_pause.pause();
        points.pause();

    }

    @Override
    public void resume() {

        isPaused=false;
        background[0].resume();
        background[1].resume();
        button_pause.resume();
        points.resume();

    }

}
