package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayScreen extends Scene{
	private Texture pacman;
//scene
	public PlayScreen(SceneManager sm)
	{
		super(sm);
		pacman = new Texture("pacman.png");
	}
	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.begin();
			batch.draw(pacman, 50, 50);
		batch.end();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
