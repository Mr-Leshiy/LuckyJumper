package com.mygdx.Lucky_Jumper.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.Lucky_Jumper.Resources.TexturesResources;

/**
 * Created by alexey on 05.01.17.
 */

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse ;
    protected GameStateManager gsm;
    protected TexturesResources resources;

    public State(GameStateManager gsm, TexturesResources resources)
    {
        this.gsm=gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
        this.resources=resources;


    }

    protected abstract void handleInput(); // Следит за нажатием клавишь
    public abstract void update(float delta); // обновление картинки
    public abstract void render(SpriteBatch sb); // Отрисовка
    public abstract void dispose();
    public abstract void pause();
    public abstract void resume();




}
