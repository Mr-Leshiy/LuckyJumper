package com.mygdx.Lucky_Jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.Lucky_Jumper.Backgrounds.PlayStateBackgound;
import com.mygdx.Lucky_Jumper.Constants.URL;
import com.mygdx.Lucky_Jumper.GameClass;
import com.mygdx.Lucky_Jumper.GameInformationFileHandler;
import com.mygdx.Lucky_Jumper.GameObjects.MyWorld;
import com.mygdx.Lucky_Jumper.GameObjects.Neurons;
import com.mygdx.Lucky_Jumper.GameObjects.Platform;
import com.mygdx.Lucky_Jumper.GameObjects.Platform1;
import com.mygdx.Lucky_Jumper.GameObjects.PlatformData;
import com.mygdx.Lucky_Jumper.GameObjects.StartPlatform;
import com.mygdx.Lucky_Jumper.ObjectControls.Button;
import com.mygdx.Lucky_Jumper.ObjectControls.ButtonListener;
import com.mygdx.Lucky_Jumper.ObjectControls.NeuronPoints;
import com.mygdx.Lucky_Jumper.ObjectControls.PlayerAnimation;
import com.mygdx.Lucky_Jumper.ObjectControls.Points;


/**
 * Created by alexey on 05.01.17.
 */

public class PlayState extends State {


     private MyWorld world;
     private PlayStateBackgound background;
     private Button button_pause;
     private Points score;
     private NeuronPoints neuronPoints;
     private PlayerAnimation player_animation;
     private Texture platform1;
     private Texture platform1_boost;
     private Texture neuron;
     private Texture start_platform;
     private Texture clock;
     private Texture platformBoost;
     private Texture time_line;
     private Texture time_line_frame;
     private Texture double_neuron_boost;
     public static final float RATE=100F;
     private SpriteBatch staticbatch;
     private OrthographicCamera static_camera;


     public static float clock_time;
     public static float platform_boost_time;
     public static float double_neuron_boost_time;

    public PlayState(final com.mygdx.Lucky_Jumper.states.GameStateManager gsm)
    {

        super(gsm);
        GameInformationFileHandler info = new GameInformationFileHandler();
        clock_time=(info.getLevel("clock_item")*0.25f+1)*10;
        platform_boost_time=(info.getLevel("platform_booster_item")*0.25f+1)*10;
        double_neuron_boost_time=(info.getLevel("double_neuron_points")*0.25f+1)*10;

        staticbatch = new SpriteBatch();
        static_camera=new OrthographicCamera();
        static_camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);
        world = new com.mygdx.Lucky_Jumper.GameObjects.MyWorld();
        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);
        background = new com.mygdx.Lucky_Jumper.Backgrounds.PlayStateBackgound();
        player_animation = new PlayerAnimation(world.getPlayer());
        Texture[] mas = {new Texture(URL.button_pause), new Texture(URL.button_pause_pressed)} ;
        score = new Points(GameClass.WIDTH/2,GameClass.HEIGTH-20);
        neuronPoints = new NeuronPoints(GameClass.WIDTH-60,GameClass.HEIGTH-20);
        button_pause = new Button(mas,10,GameClass.HEIGTH-50);
        button_pause.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                pause();

            }
        });
        platform1 = new Texture(URL.platfomr_1);
        platform1_boost = new Texture(URL.platfomr_1_boost);
        start_platform = new Texture(URL.start_platform);
        neuron= new Texture(URL.neuron);
        clock = new Texture(URL.clock);
        platformBoost = new Texture(URL.platformBoost);
        double_neuron_boost= new Texture(URL.double_neuron_points);
        time_line = new Texture(URL.time_line);
        time_line_frame = new Texture(URL.time_line_frame);
        staticbatch.setProjectionMatrix(static_camera.combined);


    }

    @Override
    protected void handleInput() {

        if(!Gdx.input.isTouched() && button_pause.isTouched)
        {
            button_pause.isTouched=false;
            button_pause.OnClick();
        }

        if(Gdx.input.justTouched())
        {

            if(button_pause.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_pause.isTouched=true;
            }
            else
            {

                if(Gdx.input.getX()*GameClass.CONST_WIDTH<GameClass.WIDTH/2)
                {
                    if (world.isGrounded())
                        world.getPlayer().jump();
                }
                else
                {
                    world.changeActivelatforms();
                }
            }
        }
         if(world.isPlayerDead())
        {
            gsm.push(new GameOverState(gsm,this));
        }

    }

    @Override
    public void update(float delta) {

        handleInput();
        world.update(delta);
        background.update(delta);
        score.update(delta);
        player_animation.update(delta);

        camera.translate(0, world.getPlayer().getDeltaY() * RATE);
        if(camera.position.y>240) {
            camera.update();
        }
        if(world.isDelete)
        {
            if(world.isTimeDoubleNeuronBoostActive())
            {
                neuronPoints.addPoints();
                neuronPoints.addPoints();
                world.isDelete=false;

            }
            else {
                neuronPoints.addPoints();
                world.isDelete = false;
            }
        }

        if(world.isContact)
        {
            score.addPoints();
            if(score.getPoints()%20==0)
            {
                world.inncreaseSpeed();
            }
            world.isContact=false;
        }

    }

    @Override
    public void render(SpriteBatch sb) {


        staticbatch.begin();
        background.render(staticbatch);
        button_pause.render(staticbatch);
        neuronPoints.render(staticbatch);
        score.render(staticbatch);

        if(world.isTimeClockActive() || world.isTimePlatformBoostActive() || world.isTimeDoubleNeuronBoostActive())
        {
            staticbatch.draw(time_line,GameClass.WIDTH/2-time_line.getWidth()/2,GameClass.HEIGTH-70,world.getTime()*time_line.getWidth(),time_line.getHeight());
            staticbatch.draw(time_line_frame,GameClass.WIDTH/2-time_line.getWidth()/2,GameClass.HEIGTH-70,time_line_frame.getWidth(),time_line_frame.getHeight());
        }


        staticbatch.end();

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        for(Platform pl:world.getPlatforms())
        {
            PlatformData data = (PlatformData) pl.getBox().getUserData();

            if(!world.isTimePlatformBoostActive())
            {

                if (data.isActive())
                {
                    if (pl instanceof Platform1)
                    {
                        sb.draw(platform1, (pl.getBox().getPosition().x - pl.getWeight()) * RATE, (pl.getBox().getPosition().y - pl.getHeight()) * RATE);
                    }
                }

            }
            else
            {
                if(!data.isBoost())
                {
                    if(data.isActive())
                    {
                        sb.draw(platform1, (pl.getBox().getPosition().x - pl.getWeight()) * RATE, (pl.getBox().getPosition().y - pl.getHeight()) * RATE);
                    }
                }
                else
                {
                    sb.draw(platform1_boost, (pl.getBox().getPosition().x - pl.getWeight()) * RATE, (pl.getBox().getPosition().y - pl.getHeight()) * RATE);

                }

            }
         if (data.isActive())
                    {
                        if (pl instanceof StartPlatform)
                        {
                            sb.draw(start_platform, (pl.getBox().getPosition().x - pl.getWeight()) * RATE, (pl.getBox().getPosition().y - pl.getHeight()) * RATE);
                        }
                    }
        }


        for(Neurons n:world.getNeurons())
        {
            sb.draw(neuron,(n.getBody().getPosition().x-n.getWeight())*RATE,(n.getBody().getPosition().y-n.getHeight())*RATE);

        }
        if(world.getClock()!=null)
        {
            sb.draw(clock,(world.getClock().getBody().getPosition().x-world.getClock().getWeight())*RATE,
                    (world.getClock().getBody().getPosition().y-world.getClock().getHeight())*RATE);

        }
        if(world.getPlatformBoost()!=null)
        {
            sb.draw(platformBoost,(world.getPlatformBoost().getBody().getPosition().x-world.getPlatformBoost().getWeight())*RATE,
                    (world.getPlatformBoost().getBody().getPosition().y-world.getPlatformBoost().getHeight())*RATE);

        }
        if(world.getDoubleNeuronBoost()!=null)
        {
            sb.draw(double_neuron_boost,(world.getDoubleNeuronBoost().getBody().getPosition().x-world.getDoubleNeuronBoost().getWeight())*RATE,
                    (world.getDoubleNeuronBoost().getBody().getPosition().y-world.getDoubleNeuronBoost().getHeight())*RATE);

        }


        player_animation.render(sb);
        sb.end();

    }

    @Override
    public void dispose() {

        background.dispose();
        button_pause.dispose();
        score.dispose();
        player_animation.dispose();
        world.getWorld().dispose();
        staticbatch.dispose();
        neuron.dispose();
        platform1.dispose();
        neuronPoints.dispose();
        start_platform.dispose();
        clock.dispose();
        platform1_boost.dispose();
        time_line.dispose();
        time_line_frame.dispose();
        platform1_boost.dispose();
        platformBoost.dispose();
        double_neuron_boost.dispose();

    }

    @Override
    public void pause() {

        gsm.push(new PauseState(gsm,this));
        isPaused=true;

    }

    @Override
    public void resume() {

        isPaused=false;

    }

    public int getScore()
    {
        return score.getPoints();

    }

    public int getNeuronPoints()
    {
        return neuronPoints.getPoints();

    }



}
