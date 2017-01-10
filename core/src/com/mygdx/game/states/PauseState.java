package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.BackGround;
import com.mygdx.game.GameObjects.Button;

/**
 * Created by alexey on 10.01.17.
 */

public class PauseState extends State {

    BackGround backGround;
    State state;
    Button button_resume;
    Button button_to_menu;


    public PauseState(GameStateManager gsm, State state)
    {
        super(gsm);
        camera.setToOrtho(false, GameClass.WIDTH,GameClass.HEIGTH);
        this.state=state;
        backGround= new BackGround(new Texture(URL.pause_state_background),0,0,false);




    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void redner(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        state.redner(sb);

        sb.begin();
        backGround.redner(sb);
        sb.end();


    }

    @Override
    public void dispose() {

        state.dispose();
        backGround.dispose();


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
