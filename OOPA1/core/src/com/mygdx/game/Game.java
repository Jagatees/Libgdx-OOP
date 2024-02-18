package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.SceneManager;


public class Game extends ApplicationAdapter  {

	private SceneManager sceneManager;

	@Override
	public void create() {
		sceneManager = new SceneManager();
		sceneManager.setScene(new GameScene());
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sceneManager.render();
		sceneManager.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		sceneManager.dispose();
	}
}
