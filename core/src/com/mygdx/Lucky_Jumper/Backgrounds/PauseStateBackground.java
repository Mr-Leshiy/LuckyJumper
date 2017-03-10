package com.mygdx.Lucky_Jumper.Backgrounds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Lucky_Jumper.Resources.TexturesResources;
import com.mygdx.Lucky_Jumper.Resources.URL;

/**
 * Created by alexey on 15.01.17.
 */

public class PauseStateBackground {

    private Texture objectTexture;

    public PauseStateBackground(TexturesResources resources)
    {
        objectTexture= resources.pause_state_background;

    }

    public void render(SpriteBatch sb)
    {
        sb.draw(objectTexture,0,0,objectTexture.getWidth(),objectTexture.getHeight());
    }

    public void dispose()
    {

    }




}
