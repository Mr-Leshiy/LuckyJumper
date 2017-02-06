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
import com.mygdx.game.ObjectControls.ScrollingBox;
import com.mygdx.game.ObjectControls.ShopBoostersItem;


/**
 * Created by alexey on 02.02.17.
 */

public class BustersState extends State {

    private MenuBackground background;
    private Button button_back;
    private NeuronPoints  neuronPoints;
    private ShopBoostersItem clock_item;
    private ScrollingBox scbox;

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
        scbox= new ScrollingBox(GameClass.WIDTH/2,120);
        final GameInformationFileHandler info = new GameInformationFileHandler();
        neuronPoints = new NeuronPoints(GameClass.WIDTH-60,GameClass.HEIGTH-20);
        neuronPoints.setPoint(info.getNeuronsPoints());

        Texture[] m = {new Texture(URL.shop_button_clock_boosters),new Texture(URL.shop_button_clock_boosters_pressed)};
        clock_item= new ShopBoostersItem(0,0, m,info.getPrice("clockItem"),info.getLevel("clockItem"));

        clock_item.setOnClickBuuttonListener(new ButtonListener() {
            @Override
            public void onClickListener() {

                if(neuronPoints.getPoints()>=clock_item.getPrice() && clock_item.getCurrent_level()<4)
                {
                    neuronPoints.setPoint(neuronPoints.getPoints()-clock_item.getPrice());
                    info.spentNeronPoints(clock_item.getPrice());

                    clock_item.setPrice(2*clock_item.getPrice());
                    clock_item.setLevel(clock_item.getCurrent_level()+1);


                    info.setPrice("clockItem",clock_item.getPrice());
                    info.setLevel("clockItem",clock_item.getCurrent_level());

                }


            }
        });


        scbox.addObject(clock_item);
        scbox.addObject(new ShopBoostersItem(0,0,m,750,3));
    }

    @Override
    protected void handleInput() {


        if(!Gdx.input.isTouched() && button_back.isTouched)
        {
            button_back.isTouched=false;
            button_back.OnClick();
        }


        if(Gdx.input.justTouched()) {

            if (button_back.isClick(Gdx.input.getX() * GameClass.CONST_WIDTH, GameClass.HEIGTH - Gdx.input.getY() * GameClass.CONST_HEIGHT)) {
                button_back.isTouched = true;

            }

        }

        clock_item.ButtonClickHandle();

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
        scbox.render(sb);



    }

    @Override
    public void dispose() {

        button_back.dispose();
        neuronPoints.dispose();
        scbox.dispose();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

}
