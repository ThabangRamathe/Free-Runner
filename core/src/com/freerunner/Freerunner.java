package com.freerunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.freerunner.states.GameStateManager;
import com.freerunner.states.MenuState;

public class Freerunner extends ApplicationAdapter {
	public static final int WIDTH=800;
	public static final int HEIGHT=400;
	public static final String TITLE="Freerunner";

	private GameStateManager gsm;

	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm=new GameStateManager();
		gsm.push(new MenuState(gsm));
		Gdx.gl.glClearColor(1, 1, 1, 1);
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
