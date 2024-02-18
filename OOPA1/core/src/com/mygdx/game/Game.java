package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Canvas.GameScreen;
import com.mygdx.game.Canvas.MainScreen;
import com.mygdx.game.Canvas.UIManager;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.SceneManager;


public class Game extends ApplicationAdapter  {

//	private SceneManager sceneManager;

	private UIManager uiManager;

	@Override
	public void create() {
		uiManager = new UIManager();
		uiManager.addScreen("main", new MainScreen());
		uiManager.addScreen("game", new GameScreen());

		uiManager.setScreen("game");

//		sceneManager = new SceneManager();
//		sceneManager.setScene(new GameScene());
	}


	@Override
	public void render() {
//		sceneManager.update(Gdx.graphics.getDeltaTime());
//		sceneManager.render();
		// Switch screens when SPACE is pressed
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			if (uiManager.getCurrentScreen().equals("main")) {
				uiManager.setScreen("game");
			} else {
				uiManager.setScreen("main");
			}
		}

		// Switch screens when ENTER is pressed
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			if (uiManager.getCurrentScreen().equals("main")) {
				uiManager.setScreen("game");
			} else {
				uiManager.setScreen("main");
			}
		}

		// Render the current screen
		uiManager.render();

	}

	@Override
	public void dispose() {
//		sceneManager.dispose();
		uiManager.dispose();
	}
}
