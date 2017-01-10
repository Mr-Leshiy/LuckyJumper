package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.BackGround;
import com.mygdx.game.GameObjects.Button;
import com.mygdx.game.GameObjects.ButtonListener;

/**
 * Created by alexey on 10.01.17.
 */

public class PauseState extends State {

    private BitmapFont font_score;
    private BackGround backGround;
    private State state;
    private Button button_resume;
    private Button button_to_menu;
    private int score;


    public PauseState(final GameStateManager gsm, final State state, int score)
    {
        super(gsm);
        initializeFontStyle();
        camera.setToOrtho(false, GameClass.WIDTH,GameClass.HEIGTH);
        this.state=state;
        this.score=score;
        backGround= new BackGround(new Texture(URL.pause_state_background),0,0,false);

        Texture[] mas = {new Texture(URL.button_resume), new Texture(URL.button_resume_pressed)};
        button_resume = new Button(mas,GameClass.WIDTH/2-140,GameClass.HEIGTH/2+50);
        button_resume.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.pop();
                state.resume();
            }
        });

        Texture[] mas2 = {new Texture(URL.button_to_main_menu),new Texture(URL.button_to_main_menu_pressed)};
        button_to_menu = new Button(mas2,GameClass.WIDTH/2-140,GameClass.HEIGTH/2-50);
        button_to_menu.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener() {
                gsm.pop();
                gsm.set(new MenuState(gsm));

            }
        });




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
        }


        if(Gdx.input.justTouched())
        {


            if(button_resume.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_resume.isTouched=true;
            }
            if(button_to_menu.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
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
    public void redner(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        state.redner(sb);

        sb.begin();
        backGround.redner(sb);

        button_to_menu.redner(sb);
        button_resume.redner(sb);
        font_score.draw(sb,"Your score",GameClass.WIDTH/2-100,GameClass.HEIGTH-20);
        font_score.draw(sb,Integer.toString(score),GameClass.WIDTH/2-30,GameClass.HEIGTH-50);

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
    private void initializeFontStyle()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(URL.font_Free_mono_bold));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters= GameClass.FONT_CHARACTERS;
        parameter.size=30;
        parameter.color= Color.WHITE;
        font_score=generator.generateFont(parameter);
        generator.dispose();


    }

}
