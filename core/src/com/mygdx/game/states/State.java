package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by alexey on 05.01.17.
 */

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse ;
    protected GameStateManager gsm;

    public State(GameStateManager gsm)
    {
        this.gsm=gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();

    }

    protected abstract void handleInput(); // Следит за нажатием клавишь
    public abstract void update(float delta); // обновление картинки
    public abstract void redner(SpriteBatch sb); // Отрисовка
    public abstract void dispose();


}