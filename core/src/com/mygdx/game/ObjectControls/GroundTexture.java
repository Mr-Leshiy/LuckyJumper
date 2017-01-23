package com.mygdx.game.ObjectControls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameObjects.Platform;

/**
 * Created by alexey on 17.01.17.
 */

public class GroundTexture extends ObjectControl {


    private Texture ground;
    private Platform platform;


    public GroundTexture(Platform platform)
    {
        super(new Rectangle(platform.getBox().getPosition().x,platform.getBox().getPosition().y,0,0));
        this.platform=platform;
        ground = new Texture("ground.png");
        object.setWidth(ground.getWidth());
        object.setHeight(ground.getHeight());



    }
    @Override
    public void update(float delta) {
        object.setPosition(platform.getBox().getPosition().x,platform.getBox().getPosition().y);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.draw(ground,object.getX(),object.getY());

    }

    @Override
    public void dispose() {

        ground.dispose();

    }

}
