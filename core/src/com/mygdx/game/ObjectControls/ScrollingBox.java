package com.mygdx.game.ObjectControls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.mygdx.game.Constants.URL;
import com.mygdx.game.GameClass;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 05.02.17.
 */

public class ScrollingBox extends ObjectControl {

    private OrthographicCamera camera;
    private List<ObjectControl> objects;
    private Texture background;
    private float maxY;
    private float minY;

    private Rectangle scissors;


    public ScrollingBox(float x,float y)
    {
        super(new Rectangle(x,y,700,290));
        object.setX(object.x-700/2);
        background = new Texture(URL.scrool_box_border);
        scissors = new Rectangle();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,GameClass.WIDTH,GameClass.HEIGTH);
        objects= new ArrayList<ObjectControl>();
        Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        ScissorStack.calculateScissors(camera,sb.getTransformMatrix(),object,scissors);
        ScissorStack.pushScissors(scissors);
        for(ObjectControl objectControl:objects)
        {
            objectControl.render(sb);
        }
        sb.flush();
        ScissorStack.popScissors();
        sb.draw(background,object.x,object.y-10);
        sb.end();


    }

    @Override
    public void dispose() {

        for(ObjectControl objectControl:objects)
        {
            objectControl.dispose();
        }

        background.dispose();



    }
    public void addObject(ObjectControl objectControl)
    {
        objectControl.moveTo(object.x+object.getWidth()/2,object.y+object.getHeight()-(objects.size()+1)*170);
        minY=object.y+object.getHeight()-(objects.size()+1)*170;
        maxY=object.y+object.getHeight()-170;
        objects.add(objectControl);
    }





    class MyGestureListener implements GestureDetector.GestureListener {

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            return false;
        }

        @Override
        public boolean longPress(float x, float y) {
            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {

            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {

            if(objects.get(objects.size()-1).object.y-deltaY<maxY && objects.get(0).object.y-deltaY>minY) {
                for (ObjectControl objectControl : objects) {
                        objectControl.moveTo(0, -deltaY);
                    }

                }
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom (float originalDistance, float currentDistance) {
            return false;
        }

        @Override
        public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {
            return false;
        }

        @Override
        public void pinchStop() {

        }
    }

}

