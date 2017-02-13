package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;

/**
 * Created by alexey on 12.02.17.
 */

public class TrainingState extends State {

    private Texture background;
    private State state;


    public TrainingState(GameStateManager gsm,State state)
    {
        super(gsm);
        background = new Texture(URL.training_background);
        this.state=state;
        camera.setToOrtho(false, GameClass.WIDTH,GameClass.HEIGTH);
    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched())
        {
            gsm.set(state);
        }
    }

    @Override
    public void update(float delta) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        state.render(sb);
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background,0,0);
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
