package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Backgrounds.PauseStateBackground;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import com.mygdx.game.ObjectControls.Button;
import com.mygdx.game.ObjectControls.ButtonListener;


/**
 * Created by alexey on 29.01.17.
 */

public class GameOverState extends State {

    private PlayState state;
    private BitmapFont message;
    private PauseStateBackground backgound;
    private Button button_to_menu;
    private FileHandle file;


    public GameOverState(final GameStateManager gsm,final PlayState state) {
        super(gsm);
        this.state=state;
        message=initializeFontStyle();
        file = Gdx.files.local("ScoreFile.txt");


        backgound = new PauseStateBackground();
        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);
        Texture[] mas2 = {new Texture(URL.button_to_main_menu),new Texture(URL.button_to_main_menu_pressed)};
        button_to_menu = new Button(mas2,GameClass.WIDTH/2-mas2[0].getWidth()/2,GameClass.HEIGTH/2-40);
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

        if(!Gdx.input.isTouched() && button_to_menu.isTouched)
        {
            button_to_menu.isTouched=false;
            button_to_menu.OnClick();
        }


        if(Gdx.input.justTouched())
        {

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
        backgound.render(sb);
        button_to_menu.render(sb);
        message.draw(sb,"GAME OVER",GameClass.WIDTH/2-100,GameClass.HEIGTH-20);
        message.draw(sb,"Your score",GameClass.WIDTH/2-100,GameClass.HEIGTH-80);
        message.draw(sb,Integer.toString(state.getScore()),GameClass.WIDTH/2-30,GameClass.HEIGTH-110);
        sb.end();

    }

    @Override
    public void dispose() {

        message.dispose();
        backgound.dispose();
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
