package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.SceneManager;


public class Game extends ApplicationAdapter  {

	private SceneManager sceneManager;

	@Override
	public void create() {
		sceneManager = new SceneManager();
		sceneManager.setScene(new MainScene());

	}

	private void handleInput() {
		// Check for input to switch scenes
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			sceneManager.setScene(new MainScene()); // Switch to MainScene when 1 is pressed
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			sceneManager.setScene(new GameScene()); // Switch to GameOverScene when 2 is pressed
		}
	}
	@Override
	public void render() {


		sceneManager.update(Gdx.graphics.getDeltaTime());

		handleInput();

		sceneManager.render();

	}

	@Override
	public void dispose() {
		sceneManager.dispose();
	}
}
