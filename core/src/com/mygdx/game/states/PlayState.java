package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants.Constants;
import com.mygdx.game.GameClass;

/**
 * Created by alexey on 05.01.17.
 */

public class PlayState extends State{

    Texture background;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        background = new Texture(Constants.play_state_background);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void redner(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, GameClass.WIDTH,GameClass.HEIGTH);
        sb.end();

    }

    @Override
    public void dispose() {

        background.dispose();

    }
}
