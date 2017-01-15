package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Backgrounds.MenuBackground;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;

/**
 * Created by alexey on 05.01.17.
 */

public class SettingsState extends State {

    private MenuBackground background;

    public SettingsState(GameStateManager gsm)
    {
        super(gsm);
        background = new MenuBackground();
    }
    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float delta) {

        background.update(delta);

    }

    @Override
    public void render(SpriteBatch sb) {
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
