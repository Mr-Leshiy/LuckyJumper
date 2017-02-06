package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.game.Backgrounds.PlayStateBackgound;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameInformationFileHandler;
import com.mygdx.game.GameObjects.MyWorld;
import com.mygdx.game.GameObjects.Neurons;
import com.mygdx.game.GameObjects.Platform;
import com.mygdx.game.GameObjects.Platform1;
import com.mygdx.game.GameObjects.StartPlatform;
import com.mygdx.game.ObjectControls.Button;
import com.mygdx.game.ObjectControls.ButtonListener;
import com.mygdx.game.ObjectControls.NeuronPoints;
import com.mygdx.game.ObjectControls.PlayerAnimation;
import com.mygdx.game.ObjectControls.Points;


/**
 * Created by alexey on 05.01.17.
 */

public class PlayState extends State{


     private MyWorld world;
     private PlayStateBackgound background;
     private Button button_pause;
     private Points score;
     private NeuronPoints neuronPoints;
     private PlayerAnimation player_animation;
     private Texture platform1;
     private Texture neuron;
     private Texture start_platform;
     private Texture clock;
     private Texture time_line;
     private Texture time_line_frame;
     public static final float RATE=100F;
     private SpriteBatch staticbatch;
     private OrthographicCamera static_camera;
     private Box2DDebugRenderer b2rd;
     private FPSLogger fpslog;
     private boolean isIncressed=false;
     public static float clock_time=10;

    public PlayState(final GameStateManager gsm)
    {

        super(gsm);
        GameInformationFileHandler info = new GameInformationFileHandler();
        clock_time=(info.getLevel("clockItem")*0.25f+1)*clock_time;

        staticbatch = new SpriteBatch();
        static_camera=new OrthographicCamera();
        static_camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);
        world = new MyWorld();
        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);
        background = new PlayStateBackgound();
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
        start_platform = new Texture(URL.start_platform);
        neuron= new Texture(URL.neuron);
        clock = new Texture(URL.clock);
        time_line = new Texture(URL.time_line);
        time_line_frame = new Texture(URL.time_line_frame);
        staticbatch.setProjectionMatrix(static_camera.combined);
        b2rd = new Box2DDebugRenderer();
        fpslog = new FPSLogger();


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
                if(Gdx.input.getX()<GameClass.WIDTH/2) {
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
            neuronPoints.addPoints();
            world.isDelete=false;
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

        if(world.isTimeActive())
        {
            staticbatch.draw(time_line,GameClass.WIDTH/2-time_line.getWidth()/2,GameClass.HEIGTH-70,world.getTime()*time_line.getWidth(),time_line.getHeight());
            staticbatch.draw(time_line_frame,GameClass.WIDTH/2-time_line.getWidth()/2,GameClass.HEIGTH-70,time_line_frame.getWidth(),time_line_frame.getHeight());
        }


        staticbatch.end();

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        for(Platform pl:world.getPlatform1s())
        {
            if((Boolean) pl.getBox().getUserData()) {
                if (pl instanceof Platform1)
                    sb.draw(platform1, (pl.getBox().getPosition().x - pl.getWeight()) * RATE, (pl.getBox().getPosition().y - pl.getHeight()) * RATE);
                if (pl instanceof StartPlatform)
                    sb.draw(start_platform, (pl.getBox().getPosition().x - pl.getWeight()) * RATE, (pl.getBox().getPosition().y - pl.getHeight()) * RATE);
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

        player_animation.render(sb);
        sb.end();
      //  b2rd.render(world.getWorld(),camera.combined.cpy().scale(RATE,RATE,0));
        fpslog.log();

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
        time_line.dispose();
        time_line_frame.dispose();

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
