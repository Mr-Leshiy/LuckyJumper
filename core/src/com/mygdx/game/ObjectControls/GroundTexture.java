package com.mygdx.game.ObjectControls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.Platform;
import com.mygdx.game.states.PlayState;

/**
 * Created by alexey on 17.01.17.
 */

public class GroundTexture extends ObjectControl {


    private Texture ground;
    private Platform platform;

    public GroundTexture(Platform platform)
    {
        super(new Rectangle(platform.getBox().getPosition().x*PlayState.RATE,platform.getBox().getPosition().y*PlayState.RATE,0,0));
        this.platform=platform;
        ground = new Texture("ground.png");

    }
    @Override
    public void update(float delta) {
        object.setPosition((platform.getBox().getPosition().x-platform.getWeight())*PlayState.RATE,(platform.getBox().getPosition().y-platform.getHeight())*PlayState.RATE);
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
