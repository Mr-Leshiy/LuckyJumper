package com.mygdx.Lucky_Jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Lucky_Jumper.Backgrounds.PlayStateBackgound;
import com.mygdx.Lucky_Jumper.Resources.TexturesResources;
import com.mygdx.Lucky_Jumper.Resources.URL;
import com.mygdx.Lucky_Jumper.GameClass;
import com.mygdx.Lucky_Jumper.GameInformationFileHandler;
import com.mygdx.Lucky_Jumper.GameObjects.MyWorld;
import com.mygdx.Lucky_Jumper.GameObjects.Neurons;
import com.mygdx.Lucky_Jumper.GameObjects.Platform;
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
     public static final float RATE=100F;
     private SpriteBatch staticbatch;
     private OrthographicCamera static_camera;
     public static float clock_time;
     public static float platform_boost_time;
     public static float double_neuron_boost_time;

    public PlayState(final GameStateManager gsm,TexturesResources resources)
    {

        super(gsm,resources);
        GameInformationFileHandler info = new GameInformationFileHandler();
        clock_time=(info.getLevel("clock_item")*0.25f+1)*10;
        platform_boost_time=(info.getLevel("platform_booster_item")*0.25f+1)*10;
        double_neuron_boost_time=(info.getLevel("double_neuron_points")*0.25f+1)*10;

        staticbatch = new SpriteBatch();
        static_camera=new OrthographicCamera();
        static_camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);
        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);
        background = new PlayStateBackgound(resources);
        Texture[] mas = {resources.button_pause, resources.button_pause_pressed} ;
        score = new Points(GameClass.WIDTH/2,GameClass.HEIGTH-20);
        neuronPoints = new NeuronPoints(GameClass.WIDTH-60,GameClass.HEIGTH-20,resources);
        button_pause = new Button(mas,10,GameClass.HEIGTH-50);
        button_pause.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                pause();

            }
        });
        staticbatch.setProjectionMatrix(static_camera.combined);

        world = new MyWorld();
        player_animation = new PlayerAnimation(world.getPlayer(),resources);



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

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            world.changeActivelatforms();

        }
         if(world.isPlayerDead())
        {
            gsm.push(new GameOverState(gsm,this,resources));
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
            staticbatch.draw(resources.time_line,GameClass.WIDTH/2-resources.time_line.getWidth()/2,GameClass.HEIGTH-70,world.getTime()*resources.time_line.getWidth(),resources.time_line.getHeight());
            staticbatch.draw(resources.time_line_frame,GameClass.WIDTH/2-resources.time_line.getWidth()/2,GameClass.HEIGTH-70,resources.time_line_frame.getWidth(),resources.time_line_frame.getHeight());
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
                        sb.draw(resources.platfomr_1, (pl.getBox().getPosition().x - pl.getWeight()) * RATE, (pl.getBox().getPosition().y - pl.getHeight()) * RATE);

                }
                else
                {
                        sb.draw(resources.platform_bright, (pl.getBox().getPosition().x - pl.getWeight()-0.15f) * RATE, (pl.getBox().getPosition().y - pl.getHeight()-0.05f) * RATE);
                }

            }
            else
            {
                if(!data.isBoost())
                {
                    if(data.isActive())
                    {
                        sb.draw(resources.platfomr_1, (pl.getBox().getPosition().x - pl.getWeight()) * RATE, (pl.getBox().getPosition().y - pl.getHeight()) * RATE);
                    }
                    else
                    {
                        sb.draw(resources.platform_bright, (pl.getBox().getPosition().x - pl.getWeight()-0.15f) * RATE, (pl.getBox().getPosition().y - pl.getHeight()-0.05f)*RATE);
                    }
                }
                else
                {
                    sb.draw(resources.platfomr_1_boost, (pl.getBox().getPosition().x - pl.getWeight()) * RATE, (pl.getBox().getPosition().y - pl.getHeight()) * RATE);

                }

            }
            if (pl instanceof StartPlatform)
                {
                    sb.draw(resources.start_platform, (pl.getBox().getPosition().x - pl.getWeight()) * RATE, (pl.getBox().getPosition().y - pl.getHeight()) * RATE);
             }
        }


        for(Neurons n:world.getNeurons())
        {
            sb.draw(resources.neuron,(n.getBody().getPosition().x-n.getWeight())*RATE,(n.getBody().getPosition().y-n.getHeight())*RATE);

        }
        if(world.getClock()!=null)
        {
            sb.draw(resources.clock,(world.getClock().getBody().getPosition().x-world.getClock().getWeight())*RATE,
                    (world.getClock().getBody().getPosition().y-world.getClock().getHeight())*RATE);

        }
        if(world.getPlatformBoost()!=null)
        {
            sb.draw(resources.platformBoost,(world.getPlatformBoost().getBody().getPosition().x-world.getPlatformBoost().getWeight())*RATE,
                    (world.getPlatformBoost().getBody().getPosition().y-world.getPlatformBoost().getHeight())*RATE);

        }
        if(world.getDoubleNeuronBoost()!=null)
        {
            sb.draw(resources.double_neuron_points,(world.getDoubleNeuronBoost().getBody().getPosition().x-world.getDoubleNeuronBoost().getWeight())*RATE,
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
        neuronPoints.dispose();

    }

    @Override
    public void pause() {

        gsm.push(new PauseState(gsm,this,resources));

    }

    @Override
    public void resume() {


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
