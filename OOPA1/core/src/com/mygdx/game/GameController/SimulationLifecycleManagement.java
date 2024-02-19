package com.mygdx.game.GameController;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Scenes.MainScene;
import com.mygdx.game.Scenes.SceneManager;

public class SimulationLifecycleManagement {

    private static SimulationLifecycleManagement instance;
    private SceneManager sceneManager;


    public SimulationLifecycleManagement() {

    }
    public static SimulationLifecycleManagement getInstance() {
        if (instance == null) {
            instance = new SimulationLifecycleManagement();
        }
        return instance;
    }

    public void render(){
        this.sceneManager.render();
    }

    public void update(float dt){
        sceneManager.update(dt);
    }

    public void startGame(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        sceneManager.setScene(new MainScene(this.sceneManager));
    }
    public void closeGame() {
        Gdx.app.exit();
    }

    public void dispose(){
        sceneManager.dispose();
    }
}
