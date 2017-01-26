package com.mygdx.game.ObjectControls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by alexey on 05.01.17.
 */

public class Button extends ObjectControl {

    private com.mygdx.game.ObjectControls.ButtonListener listener;
    public boolean isTouched;
    private Texture[] objectTextures;

    public Button(Texture[] texture, float x, float y)
    {

        super(new Rectangle(x,y,texture[0].getWidth(),texture[0].getHeight()));
        objectTextures=texture;
        isTouched=false;


    }
    @Override
    public void update(float delta) {


    }

    @Override
    public void render(SpriteBatch sb) {

        if(!isTouched) {
            sb.draw(objectTextures[0], object.getX(), object.getY(), object.getWidth(), object.getHeight());
        }
        else
        {
            sb.draw(objectTextures[1], object.getX(), object.getY(), object.getWidth(), object.getHeight());
        }

    }

    @Override
    public void dispose() {

        for(Texture objecttexture: objectTextures)
        {
            objecttexture.dispose();

        }

    }



    public void setOnClickListener(com.mygdx.game.ObjectControls.ButtonListener listener)
    {
        this.listener=listener;


    }
    public void OnClick() // Метод вызываемы йпри нажатии кнопки
    {
        listener.onClickListener();

    }

    public boolean isClick(float x,float y) // Если клик осуществляется не посредственно на кнопку
    {
       return object.contains(x,y);
    }



}
