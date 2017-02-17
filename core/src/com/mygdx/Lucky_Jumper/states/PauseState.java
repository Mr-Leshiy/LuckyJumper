package com.mygdx.Lucky_Jumper.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.Lucky_Jumper.Constants.URL;
import com.mygdx.Lucky_Jumper.GameInformationFileHandler;
import com.mygdx.Lucky_Jumper.states.MenuState;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alexey on 10.01.17.
 */

public class PauseState extends State {

    private BitmapFont font_score;
    private com.mygdx.Lucky_Jumper.Backgrounds.PauseStateBackground backGround;
    private PlayState state;
    private com.mygdx.Lucky_Jumper.ObjectControls.Button button_resume;
    private com.mygdx.Lucky_Jumper.ObjectControls.Button button_to_menu;
    private int score;
    private GlyphLayout layout1;
    private GlyphLayout layout2;


    public PauseState(final GameStateManager gsm,final PlayState state)
    {
        super(gsm);
        font_score=initializeFontStyle();
        camera.setToOrtho(false, com.mygdx.Lucky_Jumper.GameClass.WIDTH, com.mygdx.Lucky_Jumper.GameClass.HEIGTH);
        this.state=state;
        this.score=state.getScore();
        backGround= new com.mygdx.Lucky_Jumper.Backgrounds.PauseStateBackground();

        Texture[] mas = {new Texture(URL.button_resume), new Texture(URL.button_resume_pressed)};
        button_resume = new com.mygdx.Lucky_Jumper.ObjectControls.Button(mas, com.mygdx.Lucky_Jumper.GameClass.WIDTH/2-mas[0].getWidth()/2, com.mygdx.Lucky_Jumper.GameClass.HEIGTH/2+50);
        button_resume.setOnClickListener(new com.mygdx.Lucky_Jumper.ObjectControls.ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.pop();
                state.resume();
            }
        });

        Texture[] mas2 = {new Texture(URL.button_to_main_menu),new Texture(URL.button_to_main_menu_pressed)};
        button_to_menu = new com.mygdx.Lucky_Jumper.ObjectControls.Button(mas2, com.mygdx.Lucky_Jumper.GameClass.WIDTH/2-mas2[0].getWidth()/2, com.mygdx.Lucky_Jumper.GameClass.HEIGTH/2-50);
        button_to_menu.setOnClickListener(new com.mygdx.Lucky_Jumper.ObjectControls.ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.pop();
                gsm.set(new MenuState(gsm,null));
                GameInformationFileHandler info = new GameInformationFileHandler();
                info.setPoints(state.getScore());
                info.setPoints(state.getNeuronPoints());
                com.mygdx.Lucky_Jumper.GameClass.handler.showAds(true);

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


            if(button_resume.isClick(Gdx.input.getX()* com.mygdx.Lucky_Jumper.GameClass.CONST_WIDTH, com.mygdx.Lucky_Jumper.GameClass.HEIGTH-Gdx.input.getY()* com.mygdx.Lucky_Jumper.GameClass.CONST_HEIGHT))
            {
                button_resume.isTouched=true;
            }
            if(button_to_menu.isClick(Gdx.input.getX()* com.mygdx.Lucky_Jumper.GameClass.CONST_WIDTH, com.mygdx.Lucky_Jumper.GameClass.HEIGTH-Gdx.input.getY()* com.mygdx.Lucky_Jumper.GameClass.CONST_HEIGHT))
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
        font_score.draw(sb,"Your score", com.mygdx.Lucky_Jumper.GameClass.WIDTH/2-layout1.width/2, com.mygdx.Lucky_Jumper.GameClass.HEIGTH-20);
        font_score.draw(sb,Integer.toString(score), com.mygdx.Lucky_Jumper.GameClass.WIDTH/2-layout2.width/2, com.mygdx.Lucky_Jumper.GameClass.HEIGTH-50);
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
        parameter.characters= com.mygdx.Lucky_Jumper.GameClass.FONT_CHARACTERS;
        parameter.size=30;
        parameter.color= Color.WHITE;
        font=generator.generateFont(parameter);
        generator.dispose();
        return font;


    }

}
