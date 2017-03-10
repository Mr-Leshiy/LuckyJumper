package com.mygdx.Lucky_Jumper.ObjectControls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Lucky_Jumper.GameObjects.Player;
import com.mygdx.Lucky_Jumper.Resources.TexturesResources;
import com.mygdx.Lucky_Jumper.Resources.URL;


/**
 * Created by alexey on 08.01.17.
 */

public class PlayerAnimation extends ObjectControl {

    private Player player;
    private Texture[] run_frames;
    private Animation run_animation;
    private float state_time;
    private static final float change_time = 0.25f;

    public PlayerAnimation(Player player, TexturesResources resources) {
        super(new Rectangle((player.getBox().getPosition().x-player.getWeight())* com.mygdx.Lucky_Jumper.states.PlayState.RATE,
                (player.getBox().getPosition().y-player.getHeight())* com.mygdx.Lucky_Jumper.states.PlayState.RATE,0, 0),resources);
        this.player=player;
        state_time = 0;
        run_frames = new Texture[]{resources.player_walk_1, resources.player_walk_2};

        object.setHeight(run_frames[0].getHeight());
        object.setWidth(run_frames[0].getWidth());
        run_animation = new Animation(change_time, run_frames);

    }

    @Override
    public void update(float delta) {

        state_time += delta;
        if(state_time>1)
            state_time=0;

        object.setPosition((player.getBox().getPosition().x-player.getWeight())* com.mygdx.Lucky_Jumper.states.PlayState.RATE,(player.getBox().getPosition().y-player.getHeight())* com.mygdx.Lucky_Jumper.states.PlayState.RATE);

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.draw((Texture) run_animation.getKeyFrame(state_time, true), object.x, object.y);

    }

    @Override
    public void dispose() {

    }


}
