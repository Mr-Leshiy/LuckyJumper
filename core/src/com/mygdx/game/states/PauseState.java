package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Backgrounds.PauseStateBackground;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameInformationFileHandler;
import com.mygdx.game.ObjectControls.Button;
import com.mygdx.game.ObjectControls.ButtonListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alexey on 10.01.17.
 */

public class PauseState extends State {

    private BitmapFont font_score;
    private PauseStateBackground backGround;
    private PlayState state;
    private Button button_resume;
    private Button button_to_menu;
    private int score;
    private GlyphLayout layout1;
    private GlyphLayout layout2;


    public PauseState(final GameStateManager gsm,final PlayState state)
    {
        super(gsm);
        font_score=initializeFontStyle();
        camera.setToOrtho(false, GameClass.WIDTH,GameClass.HEIGTH);
        this.state=state;
        this.score=state.getScore();
        backGround= new PauseStateBackground();

        Texture[] mas = {new Texture(URL.button_resume), new Texture(URL.button_resume_pressed)};
        button_resume = new Button(mas,GameClass.WIDTH/2-mas[0].getWidth()/2,GameClass.HEIGTH/2+50);
        button_resume.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.pop();
                state.resume();
            }
        });

        Texture[] mas2 = {new Texture(URL.button_to_main_menu),new Texture(URL.button_to_main_menu_pressed)};
        button_to_menu = new Button(mas2,GameClass.WIDTH/2-mas2[0].getWidth()/2,GameClass.HEIGTH/2-50);
        button_to_menu.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.pop();
                gsm.set(new MenuState(gsm,null));
                GameInformationFileHandler info = new GameInformationFileHandler();
                info.setPoints(state.getScore());
                info.setPoints(state.getNeuronPoints());

            }
        });

        layout1 = new GlyphLayout();
        layout1.setText(font_score,"Your score");
        layout2 = new GlyphLayout();
        layout2.setText(font_score,Integer.toString(score));


    }

    @Override
    protected void handleInput() {

        if(!Gdx.input.isTouched() && button_resume.isTouched)
        {
            button_resume.isTouched=false;
            button_resume.OnClick();

        }
        if(!Gdx.input.isTouched() && button_to_menu.isTouched)
        {
            button_to_menu.isTouched=false;
            button_to_menu.OnClick();
            final GameInformationFileHandler info = new GameInformationFileHandler();

            ExecutorService exec = Executors.newCachedThreadPool();

            exec.execute(new Runnable() {
                @Override
                public void run() {
                    info.setPoints(state.getScore());
                    info.addNeuronPoints(state.getNeuronPoints());
                }
            });

            exec.shutdown();


        }


        if(Gdx.input.justTouched())
        {


            if(button_resume.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_resume.isTouched=true;
            }
            if(button_to_menu.isClick(Gdx.input.getX()*GameClass.CONST_WIDTH,GameClass.HEIGTH-Gdx.input.getY()*GameClass.CONST_HEIGHT))
            {
                button_to_menu.isTouched=true;

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
        backGround.render(sb);
        button_to_menu.render(sb);
        button_resume.render(sb);
        font_score.draw(sb,"Your score",GameClass.WIDTH/2-layout1.width/2,GameClass.HEIGTH-20);
        font_score.draw(sb,Integer.toString(score),GameClass.WIDTH/2-layout2.width/2,GameClass.HEIGTH-50);
        sb.end();


    }

    @Override
    public void dispose() {


        backGround.dispose();
        button_resume.dispose();
        button_to_menu.dispose();
        font_score.dispose();


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
