package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Backgrounds.MenuBackground;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameInformationFileHandler;
import com.mygdx.game.ObjectControls.Button;
import com.mygdx.game.ObjectControls.ButtonListener;
import com.mygdx.game.ObjectControls.NeuronPoints;

/**
 * Created by alexey on 05.01.17.
 */

public class SettingsState extends State {

    private MenuBackground background;
    private Button button_to_menu;
    private NeuronPoints neuronPoints;

    public SettingsState(final GameStateManager gsm)
    {
        super(gsm);
        background = new MenuBackground();
        Texture[] mas2 = {new Texture(URL.button_to_main_menu),new Texture(URL.button_to_main_menu_pressed)};
        button_to_menu = new Button(mas2, GameClass.WIDTH/2-mas2[0].getWidth()/2,GameClass.HEIGTH/2-40);
        button_to_menu.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.set(new MenuState(gsm));

            }
        });

        GameInformationFileHandler info = new GameInformationFileHandler();
        neuronPoints = new NeuronPoints(GameClass.WIDTH-60,GameClass.HEIGTH-20,info.getNeuronsPoints());
    }
    @Override
    protected void handleInput() {
        if(!Gdx.input.isTouched() && button_to_menu.isTouched)
        {
            button_to_menu.isTouched=false;
            button_to_menu.OnClick();
        }


        if(Gdx.input.justTouched())
        {

            if(button_to_menu.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_to_menu.isTouched=true;

            }

        }


    }

    @Override
    public void update(float delta) {

        handleInput();
        background.update(delta);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        background.render(sb);
        button_to_menu.render(sb);
        neuronPoints.render(sb);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        button_to_menu.dispose();
        neuronPoints.dispose();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

}
