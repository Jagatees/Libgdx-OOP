package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen extends Scene{

	private Texture background;
	private Texture playBtn;
	
	public MenuScreen(SceneManager sm) {
		super(sm);
		background = new Texture(Gdx.files.internal("background.jpg"));
		playBtn = new Texture(Gdx.files.internal("playbtn.png"));
		
	}
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched())
		{
			sm.set(new PlayScreen(sm));
			dispose();
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.begin();
			batch.draw(background, 0, 0, 640, 480);
			batch.draw(playBtn, 220, 200, 200, 150);
		batch.end();
		
	}
	public void dispose()
	{
		background.dispose();
		playBtn.dispose();
	}

}
