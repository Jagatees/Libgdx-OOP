package com.mygdx.game;

// Import statements for libGDX framework and game scene management
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Scenes.SceneManager;



/**
 * The Game.java file server the purpose as like a main() is
 * We Call the Simulation lifescycle Manage (SLM) here
 */
public class Game extends ApplicationAdapter {

	/** Manages the SLM, including initialization, execution, and termination. */
	private SimulationLifecycleManagement simulationLifecycleManagement;

	/** Init the simulation lifecycle manger and pass in the SceneManager */
	@Override
	public void create() {
		simulationLifecycleManagement = SimulationLifecycleManagement.getInstance();
		simulationLifecycleManagement.startGame(new SceneManager());
	}

	/** Render the SLM */
	@Override
	public void render() {
		simulationLifecycleManagement.render();
		update(Gdx.graphics.getDeltaTime());
	}

	/** Update SLM */
	private void update(float deltaTime) {
		simulationLifecycleManagement.update(deltaTime);
	}

	/** Dispose SLM */
	@Override
	public void dispose() {
		simulationLifecycleManagement.dispose();
	}
}
