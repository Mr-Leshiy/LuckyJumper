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
 * Created by alexey on 02.02.17.
 */

public class BustersState extends State {

    private MenuBackground background;
    private Button button_back;
    private NeuronPoints  neuronPoints;

    public BustersState(final GameStateManager gsm,final MenuBackground background)
    {
        super(gsm);

        this.background=background;
        Texture[] mas = {new Texture(URL.button_back),new Texture(URL.button_back_pressed)};
        button_back = new Button(mas,GameClass.WIDTH/2-mas[0].getWidth()/2,40);

        button_back.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.set(new ShopState(gsm,background));
            }
        });

        GameInformationFileHandler info = new GameInformationFileHandler();
        neuronPoints = new NeuronPoints(GameClass.WIDTH-60,GameClass.HEIGTH-20,info.getNeuronsPoints());
    }

    @Override
    protected void handleInput() {


        if(!Gdx.input.isTouched() && button_back.isTouched)
        {
            button_back.isTouched=false;
            button_back.OnClick();
        }



        if(Gdx.input.justTouched())
        {

            if(button_back.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_back.isTouched=true;

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
        button_back.render(sb);
        neuronPoints.render(sb);
        sb.end();

    }

    @Override
    public void dispose() {

        button_back.dispose();
        neuronPoints.dispose();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
