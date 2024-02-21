package com.mygdx.game;

// Import statements for libGDX framework and game scene management
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.SceneManager;



/**
 * The Game.java file server the purpose as like a main() is
 * We Call the Simulation lifescycle Manage (SLM) here
 */
public class Game extends ApplicationAdapter {

	/** Manages the SLM, including initialization, execution, and termination. */
	private SimulationLifecycleManagement simulationLifecycleManagement;
	private SceneManager sceneManager = SceneManager.getInstance();

	/** Init the simulation lifecycle manger and pass in the SceneManager */
	@Override
	public void create() {
		try {
			simulationLifecycleManagement = SimulationLifecycleManagement.getInstance();
			simulationLifecycleManagement.startGame(new MainScene());
		} catch (Exception e) {
			Gdx.app.error("Game", "Error initializing game", e);
			// Handle initialization error (e.g., log the error, attempt a safe fallback, etc.)
		}
	}

	/** Render the SLM */
	@Override
	public void render() {
		try {
			simulationLifecycleManagement.render();
			update(Gdx.graphics.getDeltaTime());
		} catch (Exception e) {
			Gdx.app.error("Game", "Error during rendering", e);
			// Handle rendering error (e.g., log the error, attempt to recover, etc.)
		}
	}

	/** Update SLM */
	private void update(float deltaTime) {
		try {
			simulationLifecycleManagement.update(deltaTime);
		} catch (Exception e) {
			Gdx.app.error("Game", "Error during update", e);
			// Handle update error (e.g., log the error, try to recover if possible, etc.)
		}
	}

	/** Dispose SLM */
	@Override
	public void dispose() {
		try {
			simulationLifecycleManagement.dispose();
		} catch (Exception e) {
			Gdx.app.error("Game", "Error disposing resources", e);
			// Handle disposal error (e.g., log the error, clean up as much as possible, etc.)
		}
	}
}
