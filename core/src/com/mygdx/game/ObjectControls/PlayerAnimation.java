package com.mygdx.game.ObjectControls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.states.PlayState;


/**
 * Created by alexey on 08.01.17.
 */

public class PlayerAnimation extends ObjectControl {

    private Player player;
    private Texture[] run_frames;
    private Animation run_animation;
    private float state_time;
    private static final float change_time = 0.25f;
    private boolean onGround;

    public PlayerAnimation(Player player) {
        super(new Rectangle(player.getBox().getPosition().x,player.getBox().getPosition().y,0, 0));
        this.player=player;
        state_time = 0;
        onGround = false;

        run_frames = new Texture[]{new Texture(URL.player_walk_1), new Texture(URL.player_walk_2)};
        object.setHeight(run_frames[0].getHeight());
        object.setWidth(run_frames[0].getWidth());
        run_animation = new Animation(change_time, run_frames);



    }

    @Override
    public void update(float delta) {

        state_time += delta;
        if(state_time>1)
            state_time=0;

        object.setPosition((player.getBox().getPosition().x-player.getWeight())* PlayState.RATE,(player.getBox().getPosition().y-player.getHeight())*PlayState.RATE);

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


}
