package com.mygdx.Lucky_Jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Lucky_Jumper.Constants.URL;

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
        camera.setToOrtho(false, com.mygdx.Lucky_Jumper.GameClass.WIDTH, com.mygdx.Lucky_Jumper.GameClass.HEIGTH);
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
