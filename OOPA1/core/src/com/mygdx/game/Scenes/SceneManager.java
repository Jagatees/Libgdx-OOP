package com.mygdx.game.Scenes;


/**
 * @Description :
 */
public class SceneManager {

    /**  */
    private Scene currentScene;

    /**
     * @Description :
     * @Param :
     * @Return :
     */
    public void setScene(Scene scene) {
        if (currentScene != null) {
            currentScene.dispose();
        }
        currentScene = scene;
        currentScene.create();
    }

    /**
     * @Description :
     * @Param :
     * @Return :
     */
    public Scene getScene() {
        return currentScene;
    }

    /**
     * @Description :
     * @Param :
     * @Return :
     */
    public void render() {
        if (currentScene != null) {
            currentScene.render();
        }
    }

    /**
     * @Description :
     * @Param :
     * @Return :
     */
    public void update(float delta) {
        if (currentScene != null) {
            currentScene.update(delta);
        }
    }


    /**
     * @Description :
     * @Param :
     * @Return :
     */
    public void dispose() {
        if (currentScene != null) {
            currentScene.dispose();
        }
    }
}
