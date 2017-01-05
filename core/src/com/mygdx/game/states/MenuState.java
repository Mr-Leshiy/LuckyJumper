package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.Button;
import com.mygdx.game.GameObjects.ButtonListener;


/**
 * Created by alexey on 05.01.17.
 */

public class MenuState extends State {  // Класс меню

    private Texture background;
    private Button button_play;



    public MenuState(final GameStateManager gsm) {
        super(gsm);
<<<<<<< HEAD
        background = new Texture("bg.png");
        button_play =new Button(new Texture("Button.png"),150,150);
=======
        background = new Texture("bground.png");
        button_play =new Button(new Texture("playbtn.png"),150,150);
>>>>>>> bd26467649f3ddb9b8391236cc6fef02f78354fc
        button_play.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.set(new PlayState(gsm));
            }
        });

    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {

            if(button_play.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_play.OnClick();
            }
        }


    }

    @Override
    public void update(float delta) {
        handleInput();

    }

    @Override
    public void redner(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, GameClass.WIDTH, GameClass.HEIGTH);
        sb.end();
        button_play.redner(sb);

    }

    @Override
    public void dispose() {

        background.dispose();
        button_play.dispose();

    }


}
