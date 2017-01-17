package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Constants.URL;

/**
 * Created by alexey on 08.01.17.
 */

public class Character extends GameObject {

    public static final int GRAVITY = -10;


    private Texture[] run_frames;
    private Animation run_animation;
    private Vector2 velosity;
    private float state_time;
    private static final float change_time = 0.25f;
    private boolean onGround;


    public Character(int x, int y) {
        super(new Rectangle(x, y, 0, 0));

        velosity = new Vector2(0, 0);
        state_time = 0;
        onGround = false;

        run_frames = new Texture[]{new Texture("player_walk1.png"), new Texture("player_walk2.png")};
        object.setHeight(new Texture("player_walk1.png").getHeight());
        object.setWidth(new Texture("player_walk1.png").getWidth());
        run_animation = new Animation(change_time, run_frames);
    }

    @Override
    public void update(float delta) {

        state_time += delta;
        if (!onGround && object.getY()>0) {
            velosity.add(0, GRAVITY);
        }
        else
        {
            object.setY(0);
        }

            velosity.scl(delta);
            object.setY(velosity.y + object.getY());
            velosity.scl(1 / delta);




    }

    @Override
    public void render(SpriteBatch sb) {

        sb.draw((Texture) run_animation.getKeyFrame(state_time, true), object.getX(), object.getY());

    }

    @Override
    public void dispose() {

        for (Texture texture : run_frames) {
            texture.dispose();
        }


    }

    public void onGround() {
        onGround = true;
    }

    public void jump() {
        velosity.add(0, 500);

    }

    public Rectangle getObject()
    {
        return object;

    }


}
