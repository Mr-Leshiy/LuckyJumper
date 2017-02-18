package com.mygdx.Lucky_Jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.Lucky_Jumper.Backgrounds.PauseStateBackground;
import com.mygdx.Lucky_Jumper.Constants.URL;
import com.mygdx.Lucky_Jumper.GameClass;
import com.mygdx.Lucky_Jumper.GameInformationFileHandler;
import com.mygdx.Lucky_Jumper.ObjectControls.Button;
import com.mygdx.Lucky_Jumper.ObjectControls.ButtonListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by alexey on 29.01.17.
 */

public class GameOverState extends State {

    private com.mygdx.Lucky_Jumper.states.PlayState state;
    private BitmapFont message;
    private PauseStateBackground backgound;
    private Button button_to_menu;
    private GlyphLayout layout1;
    private GlyphLayout layout2;
    private GlyphLayout layout3;
    private Button button_retry;



    public GameOverState(final com.mygdx.Lucky_Jumper.states.GameStateManager gsm, final com.mygdx.Lucky_Jumper.states.PlayState state) {
        super(gsm);
        this.state=state;
        message=initializeFontStyle();
        backgound = new PauseStateBackground();
        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);
        Texture[] mas2 = {new Texture(URL.button_to_main_menu),new Texture(URL.button_to_main_menu_pressed)};
        button_to_menu = new Button(mas2,GameClass.WIDTH/2-mas2[0].getWidth()/2,GameClass.HEIGTH/2-140);
        button_to_menu.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.pop();
                gsm.set(new MenuState(gsm,null));
                GameClass.handler.showAds(true);

            }
        });

        Texture[] mas3= {new Texture(URL.button_retry), new Texture(URL.button_retry_pressed)};
        button_retry =new Button(mas3, GameClass.WIDTH/2-mas3[0].getWidth()/2,GameClass.HEIGTH/2-40);
        button_retry.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {

                gsm.set(new PlayState(gsm));
            }
        });


        final GameInformationFileHandler info = new GameInformationFileHandler();

        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(new Runnable() {
            @Override
            public void run() {
                if(state.getScore()>info.getPoints())
                info.setPoints(state.getScore());

                info.addNeuronPoints(state.getNeuronPoints());
            }
        });

        exec.shutdown();

        layout1= new GlyphLayout();
        layout1.setText(message,"GAME OVER");
        layout2= new GlyphLayout();
        layout2.setText(message,"Your score");
        layout3 = new GlyphLayout();
        layout3.setText(message,Integer.toString(state.getScore()));
        GameClass.handler.showAdActivity();



    }

    @Override
    protected void handleInput() {

        if(!Gdx.input.isTouched() && button_to_menu.isTouched)
        {
            button_to_menu.isTouched=false;
            button_to_menu.OnClick();
        }
        if(!Gdx.input.isTouched() && button_retry.isTouched)
        {
            button_retry.isTouched=false;
            button_retry.OnClick();


        }



        if(Gdx.input.justTouched())
        {

            if(button_to_menu.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_to_menu.isTouched=true;

            }
            if(button_retry.isClick(Gdx.input.getX()* com.mygdx.Lucky_Jumper.GameClass.CONST_WIDTH, com.mygdx.Lucky_Jumper.GameClass.HEIGTH-Gdx.input.getY()* com.mygdx.Lucky_Jumper.GameClass.CONST_HEIGHT))
            {
                button_retry.isTouched=true;

            }

        }


    }

    @Override
    public void update(float delta) {

        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {

        state.render(sb);
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        backgound.render(sb);
        button_to_menu.render(sb);
        button_retry.render(sb);
        message.draw(sb,"GAME OVER",GameClass.WIDTH/2-layout1.width/2,GameClass.HEIGTH-20);
        message.draw(sb,"Your score",GameClass.WIDTH/2-layout2.width/2,GameClass.HEIGTH-80);
        message.draw(sb,Integer.toString(state.getScore()),GameClass.WIDTH/2-layout3.width/2,GameClass.HEIGTH-110);
        sb.end();

    }

    @Override
    public void dispose() {

        message.dispose();
        backgound.dispose();
        button_to_menu.dispose();
        button_to_menu.dispose();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    private BitmapFont initializeFontStyle()
    {
        BitmapFont font;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(URL.font_Free_mono_bold));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters= GameClass.FONT_CHARACTERS;
        parameter.size=30;
        parameter.color= Color.WHITE;
        font=generator.generateFont(parameter);
        generator.dispose();
        return font;


    }
}
