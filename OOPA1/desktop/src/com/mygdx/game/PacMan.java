package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PacMan extends ApplicationAdapter {
	
	private SpriteBatch batch;
	private SceneManager sm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		sm = new SceneManager();
		sm.push(new MenuScreen(sm));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
}
