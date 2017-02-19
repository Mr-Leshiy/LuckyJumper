package com.mygdx.Lucky_Jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Lucky_Jumper.Backgrounds.MenuBackground;
import com.mygdx.Lucky_Jumper.Constants.URL;
import com.mygdx.Lucky_Jumper.GameClass;
import com.mygdx.Lucky_Jumper.GameInformationFileHandler;
import com.mygdx.Lucky_Jumper.ObjectControls.Button;
import com.mygdx.Lucky_Jumper.ObjectControls.ButtonListener;
import com.mygdx.Lucky_Jumper.ObjectControls.NeuronPoints;
import com.mygdx.Lucky_Jumper.ObjectControls.ScrollingBox;
import com.mygdx.Lucky_Jumper.ObjectControls.ShopBoostersItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by alexey on 02.02.17.
 */

public class BustersState extends State {

    private MenuBackground background;
    private Button button_back;
    private NeuronPoints neuronPoints;
    private ShopBoostersItem clock_item;
    private ShopBoostersItem platform_booster;
    private ShopBoostersItem double_neuron_points;
    private ScrollingBox scbox;

    public BustersState(final GameStateManager gsm,final MenuBackground background)
    {
        super(gsm);

        this.background=background;
        Texture[] mas = {new Texture(URL.button_back),new Texture(URL.button_back_pressed)};
        button_back = new com.mygdx.Lucky_Jumper.ObjectControls.Button(mas,GameClass.WIDTH/2-mas[0].getWidth()/2,40);

        button_back.setOnClickListener(new com.mygdx.Lucky_Jumper.ObjectControls.ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.set(new MenuState(gsm,background));
                GameClass.handler.showAds(true);
            }
        });
        scbox= new com.mygdx.Lucky_Jumper.ObjectControls.ScrollingBox(GameClass.WIDTH/2,140);
        GameInformationFileHandler info = new GameInformationFileHandler();
        neuronPoints = new com.mygdx.Lucky_Jumper.ObjectControls.NeuronPoints(GameClass.WIDTH-60,GameClass.HEIGTH-20);
        neuronPoints.setPoint(info.getNeuronsPoints());
        Texture[] m = {new Texture(URL.shop_button_clock_boosters),new Texture(URL.shop_button_clock_boosters_pressed)};
        Texture[] m2 = {new Texture(URL.shop_button_platform_booster),new Texture(URL.shop_button_platform_booster_pressed)};
        Texture[] m3 ={new Texture(URL.shop_button_double_neuron_points), new Texture(URL.shop_button_double_neuron_points_pressed)};

        platform_booster= new ShopBoostersItem(0,0,m2);
        clock_item = new ShopBoostersItem(0,0,m);
        double_neuron_points= new ShopBoostersItem(0,0,m3);

        initializeItems(info);


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

        if(clock_item!=null)
        clock_item.ButtonClickHandle();
        if(platform_booster!=null)
        platform_booster.ButtonClickHandle();
        if(double_neuron_points!=null)
        double_neuron_points.ButtonClickHandle();

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

    public void initializeItems(final GameInformationFileHandler info)
    {
        ExecutorService exec =Executors.newCachedThreadPool();

        exec.execute(new Runnable() {
            @Override
            public void run() {
                clock_item.setOnClickBuuttonListener(new com.mygdx.Lucky_Jumper.ObjectControls.ButtonListener() {
                    @Override
                    public void onClickListener() {

                        if(neuronPoints.getPoints()>=clock_item.getPrice() && clock_item.getCurrent_level()<4)
                        {
                            neuronPoints.setPoint(neuronPoints.getPoints()-clock_item.getPrice());
                            info.spentNeronPoints(clock_item.getPrice());

                            clock_item.setPrice(2*clock_item.getPrice());
                            clock_item.setLevel(clock_item.getCurrent_level()+1);

                            info.setPrice("clock_item",clock_item.getPrice());
                            info.setLevel("clock_item",clock_item.getCurrent_level());

                        }


                    }
                });

                platform_booster.setOnClickBuuttonListener(new com.mygdx.Lucky_Jumper.ObjectControls.ButtonListener() {
                    @Override
                    public void onClickListener() {
                        if(neuronPoints.getPoints()>= platform_booster.getPrice() &&  platform_booster.getCurrent_level()<4) {
                            neuronPoints.setPoint(neuronPoints.getPoints() - platform_booster.getPrice());
                            info.spentNeronPoints(platform_booster.getPrice());

                            platform_booster.setPrice(2 * platform_booster.getPrice());
                            platform_booster.setLevel(platform_booster.getCurrent_level() + 1);

                            info.setPrice("platform_booster_item", platform_booster.getPrice());
                            info.setLevel("platform_booster_item", platform_booster.getCurrent_level());
                        }

                    }
                });

                double_neuron_points.setOnClickBuuttonListener(new ButtonListener() {
                    @Override
                    public void onClickListener() {
                        if(neuronPoints.getPoints()>=double_neuron_points.getPrice() && double_neuron_points.getCurrent_level()<4)
                        {
                            neuronPoints.setPoint(neuronPoints.getPoints()-double_neuron_points.getPrice());
                            info.spentNeronPoints(double_neuron_points.getPrice());

                            double_neuron_points.setPrice(2*double_neuron_points.getPrice());
                            double_neuron_points.setLevel(double_neuron_points.getCurrent_level()+1);

                            info.setPrice("double_neuron_points",double_neuron_points.getPrice());
                            info.setLevel("double_neuron_points",double_neuron_points.getCurrent_level());

                        }


                    }
                });

                scbox.addObject(clock_item);
                scbox.addObject(platform_booster);
                scbox.addObject(double_neuron_points);

                clock_item.setPrice(info.getPrice("clock_item"));
                clock_item.setLevel(info.getLevel("clock_item"));
                platform_booster.setPrice(info.getPrice("platform_booster_item"));
                platform_booster.setLevel(info.getLevel("platform_booster_item"));
                double_neuron_points.setPrice(info.getPrice("double_neuron_points"));
                double_neuron_points.setLevel(info.getLevel("double_neuron_points"));


            }
        });
       exec.shutdown();



    }

}
