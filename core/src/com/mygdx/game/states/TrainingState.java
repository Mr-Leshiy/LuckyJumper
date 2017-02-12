package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Backgrounds.PauseStateBackground;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;

/**
 * Created by alexey on 12.02.17.
 */

public class TrainingState extends State {

    private PauseStateBackground background;
    private PlayState playState;


    public TrainingState(GameStateManager gsm)
    {
        super(gsm);
        background = new PauseStateBackground();
        playState = new PlayState(gsm);
        camera.setToOrtho(false, GameClass.WIDTH,GameClass.HEIGTH);


    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched())
        {
            gsm.set(playState);
        }



    }

    @Override
    public void update(float delta) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        playState.render(sb);

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        background.render(sb);
        sb.end();



    }

    @Override
    public void dispose() {

        background.dispose();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
