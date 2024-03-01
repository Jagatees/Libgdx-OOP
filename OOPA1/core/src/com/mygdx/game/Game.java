package com.mygdx.game;

// Import statements for libGDX framework and game scene management
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Engine.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Engine.Input.InputOutputManager;
import com.mygdx.game.Engine.Scenes.SceneManager;
import com.mygdx.game.Engine.audio.AudioAssetKey;


/**
 * The Game.java file server the purpose as like a main() is
 * We Call the Simulation lifescycle Manage (SLM) here
 */
public class Game extends ApplicationAdapter {


	/** Init the simulation lifecycle manger and pass in the SceneManager */
	@Override
	public void create() {
		try {
			SimulationLifecycleManagement.getInstance().startGame();
		} catch (Exception e) {
			Gdx.app.error("Game", "Error initializing game", e);
			// Handle initialization error (e.g., log the error, attempt a safe fallback, etc.)
		}
	}

	/** Render the SLM */
	@Override
	public void render() {
		try {
			SimulationLifecycleManagement.getInstance().render();
			update(Gdx.graphics.getDeltaTime());
		} catch (Exception e) {
			Gdx.app.error("Game", "Error during rendering", e);
		}
	}

	/** Update SLM */
	private void update(float deltaTime) {
		try {
			SimulationLifecycleManagement.getInstance().update(deltaTime);
		} catch (Exception e) {
			Gdx.app.error("Game", "Error during update", e);
			// Handle update error (e.g., log the error, try to recover if possible, etc.)
		}
	}

	/** Dispose SLM */
	@Override
	public void dispose() {
		try {
			SimulationLifecycleManagement.getInstance().dispose();
		} catch (Exception e) {
			Gdx.app.error("Game", "Error disposing resources", e);
			// Handle disposal error (e.g., log the error, clean up as much as possible, etc.)
		}
	}
}
