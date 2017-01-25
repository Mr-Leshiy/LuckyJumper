package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.game.Backgrounds.PlayStateBackgound;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.MyWorld;
import com.mygdx.game.GameObjects.Platform;
import com.mygdx.game.ObjectControls.Button;
import com.mygdx.game.ObjectControls.ButtonListener;
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
     private PlayerAnimation player_animation;
     private Texture platform;
     private Texture platform2;
     private Box2DDebugRenderer b2rd;
     public static final float RATE=100F;




    public PlayState(final GameStateManager gsm)
    {
        super(gsm);
        b2rd = new Box2DDebugRenderer();
        world = new MyWorld();
        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);
        background = new PlayStateBackgound();
        player_animation = new PlayerAnimation(world.getPlayer());
        Texture[] mas = {new Texture(URL.button_pause), new Texture(URL.button_pause_pressed)} ;
        score = new Points(GameClass.WIDTH/2-80,GameClass.HEIGTH-20);
        button_pause = new Button(mas,10,GameClass.HEIGTH-50);
        button_pause.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                pause();

            }
        });
        platform = new Texture(URL.platform_1);
        platform2 = new Texture(URL.platfomr_2);

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
                world.getPlayer().jump();

            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F4))
        {


            world.changeActivelatforms();

        }


    }

    @Override
    public void update(float delta) {

        camera.update();
        handleInput();
        world.update(delta);
        background.update(delta);
        score.update(delta);
        player_animation.update(delta);




    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        background.render(sb);
        button_pause.render(sb);
        score.render(sb);
        for(Platform pl:world.getPlatforms())
        {
            if((Boolean) pl.getBox().getUserData())
            sb.draw(platform2,(pl.getBox().getPosition().x-pl.getWeight())*PlayState.RATE,(pl.getBox().getPosition().y-pl.getHeight())*PlayState.RATE);

        }

        player_animation.render(sb);
        sb.end();

        b2rd.render(world.getWorld(),camera.combined.cpy().scale(RATE,RATE,0));

    }

    @Override
    public void dispose() {

        background.dispose();
        button_pause.dispose();
        platform.dispose();
        score.dispose();
        player_animation.dispose();
        world.getWorld().dispose();




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
        return score.points;

    }



}
