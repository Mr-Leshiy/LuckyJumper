package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Backgrounds.PlayStateBackgound;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.Button;
import com.mygdx.game.GameObjects.ButtonListener;
import com.mygdx.game.GameObjects.Points;


/**
 * Created by alexey on 05.01.17.
 */

public class PlayState extends State{

     private PlayStateBackgound background;
     private Button button_pause;
     public Points score;


    public PlayState(final GameStateManager gsm)
    {
        super(gsm);
        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);

        background = new PlayStateBackgound();


        Texture[] mas = {new Texture(URL.button_pause), new Texture(URL.button_pause_pressed)} ;
        score = new Points(GameClass.WIDTH/2-80,GameClass.HEIGTH-20);
        button_pause = new Button(mas,10,GameClass.HEIGTH-50);

        button_pause.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                pause();

                gsm.push(new PauseState(gsm,gsm.peek(),score.points));

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

        }

    }

    @Override
    public void update(float delta) {
        handleInput();
        background.update(delta);
        score.update(delta);


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        background.render(sb);
        button_pause.render(sb);
        score.render(sb);
        sb.end();

    }

    @Override
    public void dispose() {

        background.dispose();
        button_pause.dispose();
        score.dispose();



    }

    @Override
    public void pause() {

        isPaused=true;

        background.pause();
        button_pause.pause();
        score.pause();

    }

    @Override
    public void resume() {

        isPaused=false;
        background.resume();
        button_pause.resume();
        score.resume();

    }

}
