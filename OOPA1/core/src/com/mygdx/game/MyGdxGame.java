package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	
	SpriteBatch batch;
	private Texture background;
	private Texture playBtn;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("background.jpg"));
		playBtn = new Texture(Gdx.files.internal("playbtn.png"));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
			batch.draw(background, 0, 0, 640, 480);
			batch.draw(playBtn, 220, 200, 200, 150);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		playBtn.dispose();
		
	}
}
