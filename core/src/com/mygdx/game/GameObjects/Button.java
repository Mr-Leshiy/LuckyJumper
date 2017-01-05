package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by alexey on 05.01.17.
 */

public class Button extends GameObject{

    private ButtonListener listener;

    public Button(Texture texture,int x, int y)
    {

        super(texture,new Rectangle(x,y,texture.getWidth(),texture.getHeight()));


    }
    @Override
    public void update(float delta) {

    }

    @Override
    public void redner(SpriteBatch sb) {
        sb.begin();
        sb.draw(objectTexture,object.getX(),object.getX(),objectTexture.getWidth(),objectTexture.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {

        objectTexture.dispose();

    }

    public void setOnClickListener(ButtonListener listener)
    {
        this.listener=listener;


    }
    public void OnClick() // Метод вызываемы йпри нажатии кнопки
    {
        listener.onClickListener();

    }

    public boolean isClick(int x,int y) // Если клик осуществляется не посредственно на кнопку
    {

       return object.contains(x,y);
    }


}
