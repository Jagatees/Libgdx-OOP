package com.mygdx.game;

// Import statements for libGDX framework and game scene management
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameController.GameController;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.SceneManager;


/**
 * The Game class serves as the core of the application, managing the lifecycle
 * and transitions between different scenes within the game. It extends
 * ApplicationAdapter, utilizing libGDX's lifecycle methods to manage game states.
 */
public class Game extends ApplicationAdapter {

	private GameController gameController;

	@Override
	public void create() {
		gameController = GameController.getInstance();
		gameController.startGame(new SceneManager());
	}

	@Override
	public void render() {
		gameController.render();
		update(Gdx.graphics.getDeltaTime());
	}

	private void update(float deltaTime) {
		gameController.update(deltaTime);
	}

	@Override
	public void dispose() {
		gameController.dispose();
	}
}
