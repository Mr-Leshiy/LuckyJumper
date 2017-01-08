package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.BackGround;
import com.mygdx.game.GameObjects.Character;
import com.mygdx.game.GameObjects.GameObject;

import java.util.List;

/**
 * Created by alexey on 05.01.17.
 */

public class PlayState extends State{

    BackGround[] background;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);


        background = new BackGround[2];
        background[0]=new BackGround(new Texture(URL.play_state_background1),0,0,true);
        background[1]= new BackGround(new Texture(URL.play_state_background2),GameClass.WIDTH,0,true);
        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);






    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float delta) {

        background[0].update(delta);
        background[1].update(delta);


    }

    @Override
    public void redner(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        background[0].redner(sb);
        background[1].redner(sb);
        sb.end();

    }

    @Override
    public void dispose() {

        background[0].dispose();
        background[1].dispose();



    }
}
