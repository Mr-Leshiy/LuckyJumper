package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by alexey on 17.01.17.
 */

public class Ground extends GameObject {


    private Texture ground;


    public Ground(int x,int y)
    {
        super(new Rectangle(x,y,0,0));

        ground = new Texture("ground.png");



    }
    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.draw(ground,object.getX(),object.getY());

    }

    @Override
    public void dispose() {

        ground.dispose();

    }



    public boolean isTouchedGround(Character character)
    {
        return object.contains(character.getObject());

    }
}
