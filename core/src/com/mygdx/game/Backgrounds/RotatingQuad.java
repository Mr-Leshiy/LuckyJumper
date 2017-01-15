package com.mygdx.game.Backgrounds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by alexey on 15.01.17.
 */
class RotatingQuad
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


