package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class GameClass extends ApplicationAdapter {

	public static final float WIDTH=800;
	public static final float HEIGTH=480;
	public static float CONST_WIDTH;
	public static float CONST_HEIGHT;
	public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	public static boolean FirstTimeRunning=false;

	SpriteBatch batch;
	GameStateManager gsm;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm=new GameStateManager();

		if(!Gdx.files.local("GameInformation.xml").exists()) {
			GameInformationFileHandler.createDocument();
			FirstTimeRunning=true;
		}

		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm,null));
		CONST_HEIGHT=HEIGTH/Gdx.graphics.getHeight();
		CONST_WIDTH=WIDTH/Gdx.graphics.getWidth();

	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
