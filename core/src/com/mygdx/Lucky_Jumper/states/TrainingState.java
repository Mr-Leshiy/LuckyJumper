package com.mygdx.Lucky_Jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Lucky_Jumper.GameClass;
import com.mygdx.Lucky_Jumper.Resources.TexturesResources;

/**
 * Created by alexey on 12.02.17.
 */

public class TrainingState extends State {

    private Texture background;
    private State state;


    public TrainingState(GameStateManager gsm, State state, TexturesResources resources)
    {
        super(gsm,resources);
        background = resources.training_background;
        this.state=state;
        camera.setToOrtho(false, GameClass.WIDTH, GameClass.HEIGTH);
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

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
