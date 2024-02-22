package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Canvas.CanvasManager;
import com.mygdx.game.Canvas.GameCanvas;
import com.mygdx.game.Canvas.OptionsCanvas;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Controller.AIControlManagement;
import com.mygdx.game.Controller.PlayerControllerManagement;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.nonPlayer;
import com.mygdx.game.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Input.InputOutputManager;
import com.mygdx.game.audio.AudioAssetKey;


/**
 * The GameScene file a like clone of the TemplateScene as that is the Base version
 */
public class OptionScene extends TemplateScene {


    private CanvasManager canvasManager;



    /**
     * Constructor for GameScene, initializes game components, entities, and managers.
     */
    public OptionScene() {
        canvasManager = new CanvasManager();
        canvasManager.setCanvas(new OptionsCanvas());
    }



    /**
     * Render method called every frame to draw the scene's entities and background.
     */
    @Override
    public void render() {

    	ScreenUtils.clear((float) 0.8, (float) 0.8, (float) 0.8, 1);

        float delta = Gdx.graphics.getDeltaTime();
        canvasManager.render(delta);
        canvasManager.update(delta);

    }

    /**
     * Update method to process game logic updates based on the time since the last frame.
     * @param delta Time passed since the last frame, in seconds.
     */
    @Override
    public void update(float delta) {

    }
    
    /**
     * Dispose method to clean up resources when the scene is no longer in use.
     */
    @Override
    public void dispose() {

        canvasManager.dispose();
    }

    @Override
    public void create() {

    }

}
