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

public class ShopState extends State {

    private MenuBackground background;
    private NeuronPoints neuronPoints;
    private Button button_to_menu;
    private Button button_busters;

    public ShopState(final GameStateManager gsm, final MenuBackground background)
    {
        super(gsm);

        this.background=background;

        Texture[] mas = {new Texture(URL.button_to_main_menu),new Texture(URL.button_to_main_menu_pressed)};
        button_to_menu = new Button(mas, GameClass.WIDTH/2-mas[0].getWidth()/2,40);
        button_to_menu.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.set(new MenuState(gsm,background));

            }
        });

        Texture[] mas2 = {new Texture(URL.shop_button_busters),new Texture(URL.shop_button_busters_pressed)};
        button_busters = new Button(mas2, 150,GameClass.HEIGTH-200);
        button_busters.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {

                gsm.set(new BustersState(gsm,background));


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
        if(!Gdx.input.isTouched() && button_busters.isTouched)
        {
            button_busters.isTouched=false;
            button_busters.OnClick();
        }



        if(Gdx.input.justTouched())
        {

            if(button_to_menu.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_to_menu.isTouched=true;

            }
            if(button_busters.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_busters.isTouched=true;

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
        neuronPoints.render(sb);
        button_to_menu.render(sb);
        button_busters.render(sb);
        sb.end();

    }

    @Override
    public void dispose() {

        neuronPoints.dispose();
        button_busters.dispose();
        button_to_menu.dispose();


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
