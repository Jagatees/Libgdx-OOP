package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Canvas.TextRendererScreen;
import com.mygdx.game.Canvas.TextRendererScreenTwo;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.SceneManager;

import org.w3c.dom.Text;


public class Game extends ApplicationAdapter {

	private SceneManager sceneManager;
	private TextRendererScreen textRendererScreen;
	private TextRendererScreenTwo textRendererScreenTwo;

	@Override
	public void create() {
		sceneManager = new SceneManager();
		sceneManager.setScene(new MainScene());
		textRendererScreen = new TextRendererScreen();
		textRendererScreenTwo = new TextRendererScreenTwo();

	}

	@Override
	public void render() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			sceneManager.setScene(new MainScene());
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			sceneManager.setScene(new GameScene());
		}

		sceneManager.render();
		sceneManager.update(Gdx.graphics.getDeltaTime());

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			textRendererScreen.draw();
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			textRendererScreenTwo.draw();
		}




	}

	@Override
	public void dispose() {
		sceneManager.dispose();
	}
}
