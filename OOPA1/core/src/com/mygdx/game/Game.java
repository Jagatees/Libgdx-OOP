package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Input.Keyboard;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.SceneManager;

import org.w3c.dom.Text;


public class Game extends ApplicationAdapter {

	private SceneManager sceneManager;
	private Keyboard keyboard;

	@Override
	public void create() {
		sceneManager = new SceneManager();
		keyboard = new Keyboard();
		sceneManager.setScene(new MainScene());
	}

	@Override
	public void render() {
		if (keyboard.isKeyPressedBool(Input.Keys.NUM_1)) {
			sceneManager.setScene(new MainScene());
		} else if (keyboard.isKeyPressedBool(Input.Keys.NUM_2)) {
			sceneManager.setScene(new GameScene());
		}

		sceneManager.render();
		sceneManager.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		sceneManager.dispose();
	}
}
