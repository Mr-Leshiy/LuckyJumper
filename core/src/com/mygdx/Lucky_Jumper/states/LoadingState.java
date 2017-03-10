package com.mygdx.Lucky_Jumper.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Lucky_Jumper.GameClass;
import com.mygdx.Lucky_Jumper.Resources.TexturesResources;
import com.mygdx.Lucky_Jumper.Resources.URL;

/**
 * Created by alexey on 09.03.17.
 */

public class LoadingState extends State {

    private Texture background;
    private TexturesResources resources;
    private Texture time_line;
    private Texture time_line_frame;


    public LoadingState(GameStateManager gsm)
    {
        super(gsm,null);

        background = new Texture(URL.loading_background);
        time_line = new Texture(URL.time_line);
        time_line_frame = new Texture(URL.time_line_frame);
        resources = new TexturesResources();
        resources.LoadingTextures();

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float delta) {

        if(resources.isLoaded())
        {
            resources.InitializeTextures();
            gsm.set(new MenuState(gsm,null,resources));
        }

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(background,0,0);

        sb.draw(time_line, GameClass.WIDTH/2-time_line.getWidth()/2,GameClass.HEIGTH-70,resources.getProgress()*time_line.getWidth(),time_line.getHeight());
        sb.draw(time_line_frame,GameClass.WIDTH/2-time_line.getWidth()/2,GameClass.HEIGTH-70,time_line_frame.getWidth(),time_line_frame.getHeight());
        sb.end();

    }

    @Override
    public void dispose() {

        background.dispose();
        time_line_frame.dispose();
        time_line.dispose();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
