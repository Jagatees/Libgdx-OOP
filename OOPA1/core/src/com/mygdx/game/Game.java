package com.mygdx.game;

// Import statements for libGDX framework and game scene management
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.SceneManager;


/**
 * The Game class serves as the core of the application, managing the lifecycle
 * and transitions between different scenes within the game. It extends
 * ApplicationAdapter, utilizing libGDX's lifecycle methods to manage game states.
 */
public class Game extends ApplicationAdapter {

	/** SceneManager instance to manage transitions and states of various scenes */
	private final SceneManager sceneManager = new SceneManager();

	/**
	 * @Description :
	 * @param :
	 * @return :
	 */
	@Override
	public void create() {
		sceneManager.setScene(new MainScene(sceneManager));
	}

	/**
	 * @Description :
	 * @param :
	 * @return :
	 */
	@Override
	public void render() {
		sceneManager.render();
		update(Gdx.graphics.getDeltaTime());
	}

	/**
	 * @Description :
	 * @param :
	 * @return :
	 */
	private void update(float deltaTime) {
		sceneManager.update(deltaTime);
	}

	/**
	 * @Description :
	 * @param :
	 * @return :
	 */
	@Override
	public void dispose() {
		sceneManager.dispose();
	}
}
