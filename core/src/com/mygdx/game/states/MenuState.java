package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.*;
import java.util.List;


import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Constants.Constants;
import com.mygdx.game.GameClass;
import com.mygdx.game.GameObjects.Button;
import com.mygdx.game.GameObjects.ButtonListener;



/**
 * Created by alexey on 05.01.17.
 */

public class MenuState extends State {  // Класс меню

    private Texture background;
    private Button button_play;
    private Button button_settings;
    private Pixmap background_pixmap;
    private List<Circle> circles;
    private Random random;





    public MenuState(final GameStateManager gsm) {
        super(gsm);

        background = new Texture(Constants.menu_state_background);
        TextureData data=background.getTextureData();
        data.prepare();
        background_pixmap=data.consumePixmap();

        random =new Random();
        circles = new ArrayList<Circle>();

        generateCircle();



        button_play =new Button(new Texture(Constants.button_play),GameClass.WIDTH/2-140,GameClass.HEIGTH/2+50);
        button_play.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener()
            {
                gsm.set(new PlayState(gsm));
            }
        });

        button_settings =new Button(new Texture(Constants.button_setting),GameClass.WIDTH/2-140,GameClass.HEIGTH/2-50);
        button_settings.setOnClickListener(new ButtonListener() {
            @Override
            public void onClickListener()
            {
                gsm.set(new SettingsState(gsm));
            }
        });


    }

    @Override
    public void handleInput()
    {
        if(!Gdx.input.isTouched() && button_play.isTouched)
        {
            button_play.setTexture(new Texture(Constants.button_play));
            button_play.isTouched=false;
            button_play.OnClick();

        }
        if(!Gdx.input.isTouched() && button_settings.isTouched)
        {
            button_settings.setTexture(new Texture(Constants.button_setting));
            button_settings.isTouched=false;
            button_settings.OnClick();

        }


        if(Gdx.input.justTouched())
        {


            if(button_play.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_play.setTexture(new Texture(Constants.button_play_pressed));
                button_play.isTouched=true;


            }
            if(button_settings.isClick(Gdx.input.getX(),GameClass.HEIGTH-Gdx.input.getY()))
            {
                button_settings.setTexture(new Texture(Constants.button_setting_pressed));
                button_settings.isTouched=true;

            }

        }



    }

    @Override
    public void update(float delta) {
        handleInput();
        animationBackground();
        generateCircle();


    }

    @Override
    public void redner(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, GameClass.WIDTH, GameClass.HEIGTH);

        circles.get(0).sprite.draw(sb);
        for(Circle cl:circles)
        {
            cl.sprite.draw(sb);


        }

        button_play.redner(sb);
        button_settings.redner(sb);

        sb.end();


    }

    @Override
    public void dispose() {

        background.dispose();
        button_play.dispose();
        button_settings.dispose();

    }


    private void generateCircle()
    {

        int radius=8;
        Pixmap pixels =new Pixmap(2*radius+1,2*radius+1,Pixmap.Format.RGBA8888);
        pixels.setColor(Color.BLUE);
        pixels.fillCircle(radius,radius,radius);
        Circle cl = new Circle();
        cl.sprite =new Sprite(new Texture(pixels),0,0,2*radius+1,2*radius+1);

        if(random.nextBoolean())
        {
            if(random.nextBoolean()) // с левого края
            {
                cl.sprite.setPosition(10,random.nextInt(GameClass.HEIGTH));
                cl.vector = new Vector2(random.nextFloat(),random.nextFloat());
            }
            else // с правого края
            {
                cl.sprite.setPosition(GameClass.WIDTH-10,random.nextInt(GameClass.HEIGTH));
                cl.vector = new Vector2(-1*random.nextFloat(),random.nextFloat());

            }

        }
        else
        {
            if(random.nextBoolean()) // сверху
            {
                cl.sprite.setPosition(random.nextInt(GameClass.WIDTH),GameClass.HEIGTH-10);
                cl.vector = new Vector2(random.nextFloat(),-1*random.nextFloat());

            }
            else // снизу
            {
                cl.sprite.setPosition(random.nextInt(GameClass.WIDTH),10);
                cl.vector = new Vector2(random.nextFloat(),random.nextFloat());
            }


        }




        circles.add(cl);

        pixels.dispose();



    }
    private void animationBackground()
    {

        for(int i=0;i<circles.size();i++)
        {


            circles.get(i).sprite.setPosition(circles.get(i).sprite.getX() + circles.get(i).vector.x, circles.get(i).sprite.getY() + circles.get(i).vector.y);
            if(circles.get(i).sprite.getX()>GameClass.WIDTH || circles.get(i).sprite.getY()>GameClass.HEIGTH)
            {
                circles.remove(circles.get(i));
            }
        }

    }

    class Circle
    {
        public Sprite sprite;
        public Vector2 vector;

    }

}



