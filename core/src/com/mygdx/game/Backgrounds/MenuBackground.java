package com.mygdx.game.Backgrounds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Constants.URL;

/**
 * Created by alexey on 15.01.17.
 */

public class MenuBackground {

    private RotatingQuad[] quads;
    private Texture texture_quad;
    private Texture background;

    public MenuBackground()
    {
        texture_quad = new Texture(URL.quad);
        background = new Texture(URL.menu_state_bg);
        TextureRegion tr = new TextureRegion(texture_quad,texture_quad.getWidth(),texture_quad.getHeight());

        quads = new RotatingQuad[]{new RotatingQuad(tr,5,600,150,1),
                new RotatingQuad(tr,7,50,250,1),
                new RotatingQuad(tr,6,350,50,-1),
                new RotatingQuad(tr,3,50,50,-1),
                new RotatingQuad(tr,4,400,350,-1)
        };

    }


    public void update(float delta) {

        for(RotatingQuad quad:quads)
        {
            quad.rotateQuad();
        }



    }

    public void render (SpriteBatch sb)
    {

        sb.draw(background,0,0);
        for(RotatingQuad quad:quads)
        {
            quad.render(sb);
        }
    }

    public void dispose()
    {
        texture_quad.dispose();
        background.dispose();



    }

    static class RotatingQuad
    {
        private TextureRegion quad;
        private float angle;
        private float multiplier;
        private float X;
        private float Y;
        private static final float speed=0.5f;

        public RotatingQuad(TextureRegion quad, float multiplier,int X,int Y,float angle)
        {
            this.quad=quad;
            this.angle=angle;
            this.multiplier=multiplier;
            this.X=X;
            this.Y=Y;
        }

        public void rotateQuad()
        {
            if(angle>0) {
                angle+=speed;
            }
            else
            {
                angle-=speed;

            }

        }

        public void render(SpriteBatch sb)
        {
            sb.draw(quad,X,Y,quad.getRegionWidth()*multiplier/2,quad.getRegionHeight()*multiplier/2,
                    quad.getRegionWidth()*multiplier,quad.getRegionHeight()*multiplier,
                    1,1,angle);

        }




    }



}









