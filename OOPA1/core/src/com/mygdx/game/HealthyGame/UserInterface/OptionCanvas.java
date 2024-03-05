package com.mygdx.game.HealthyGame.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Engine.Canvas.Canvas;
import com.mygdx.game.Engine.Canvas.CanvasManager;
import com.mygdx.game.Engine.Canvas.UIElements;
import com.mygdx.game.Engine.Config.LauncherConfig;
import com.mygdx.game.Engine.GameController.SimulationLifecycleManagement;
import com.mygdx.game.Engine.Input.InputOutputManager;
import com.mygdx.game.Engine.audio.AudioAssetKey;

/**
 * Defines the canvas for the main menu, including UI elements like buttons
 * for starting the game and exiting the application.
 */
public class OptionCanvas implements Canvas {

    private Stage stage = new Stage(new ScreenViewport());
    String[] keyboardKeys = new String[26]; // 26 letters in the English alphabet

    private SpriteBatch batch;
    private Texture background;


    public OptionCanvas() {
        Gdx.input.setInputProcessor(stage);


        for (int i = 0; i < 26; i++) {
            keyboardKeys[i] = String.valueOf((char)(i + 65));
        }


        UIElements.createLabel(stage, "OPTIONS", 600, 650, Color.RED);
        UIElements.createTextButton(stage, "Back", 1100, 100, 100, 50, Color.RED , new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // When in Game
                if (SimulationLifecycleManagement.getInstance().isPaused() && CanvasManager.getInstance().getCurrentCanvas() instanceof OptionCanvas) {
                    SimulationLifecycleManagement.getInstance().togglePause();
                    CanvasManager.getInstance().setCanvas(new GameCanvas());

                }

                // Outside Game Screen
                if (!SimulationLifecycleManagement.getInstance().isPaused() && CanvasManager.getInstance().getCurrentCanvas() instanceof OptionCanvas) {
                    CanvasManager.getInstance().setCanvas(new MainMenuCanvas());
                }
            }
        });


        UIElements.createLabel(stage, "Music", 100, 600, Color.RED);
        UIElements.SimpleSlider(stage, 0, 1, 200, 600, InputOutputManager.getInstance().getAudioManager().getVolume(AudioAssetKey.BG_1), new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                System.out.println("Slider Value: " + slider.getValue());
                InputOutputManager.getInstance().getAudioManager().setVolume(AudioAssetKey.BG_1, slider.getValue());
            }
        });


        UIElements.createCheckBox(stage, "Turn BG_1 On/Off", 420, 600, true, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("CheckBox is " + ((CheckBox)actor).isChecked());
                if (((CheckBox)actor).isChecked() == false) {
                    InputOutputManager.getInstance().getAudioManager().setVolume(AudioAssetKey.BG_1, 0f);
                }else if (((CheckBox)actor).isChecked() == true) {
                    InputOutputManager.getInstance().getAudioManager().setVolume(AudioAssetKey.BG_1, 0.1f);
                }
            }
        });


        UIElements.createLabel(stage, "Sound Effect", 100, 500, Color.RED);
        UIElements.SimpleSlider(stage, 0, 1, 200, 500, InputOutputManager.getInstance().getAudioManager().getVolume(AudioAssetKey.BG_1), new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        UIElements.createCheckBox(stage, "Turn Sound Effect On/Off", 420, 500, true, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });



        // Make Sure can not bind to the same key more then ones &  add a save button to save the keybinding

        UIElements.createLabel(stage, "MOVE_RIGHT", 100, 400, Color.RED);

        UIElements.createDropdown(stage, keyboardKeys, 200, 400, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SelectBox<String> selectBox = (SelectBox<String>) actor;
                String selectedKey = selectBox.getSelected();
                int keycode = InputOutputManager.getInstance().getKeyboard().convertLetterToLibGDXKeycode(selectedKey.charAt(0)); // Convert the selected letter to LibGDX keycode
                InputOutputManager.getInstance().getKeyboard().setKeyBinding("MOVE_RIGHT", keycode);
                InputOutputManager.getInstance().getKeyboard().printKeyBindings();
            }
        });


        UIElements.createLabel(stage, "MOVE_LEFT", 100, 300, Color.RED);
        UIElements.createDropdown(stage, keyboardKeys, 200, 300, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SelectBox<String> selectBox = (SelectBox<String>) actor;
                String selectedKey = selectBox.getSelected();
                int keycode = InputOutputManager.getInstance().getKeyboard().convertLetterToLibGDXKeycode(selectedKey.charAt(0)); // Convert the selected letter to LibGDX keycode
                InputOutputManager.getInstance().getKeyboard().setKeyBinding("MOVE_LEFT", keycode);
                InputOutputManager.getInstance().getKeyboard().printKeyBindings();
            }
        });


        UIElements.createLabel(stage, "MOVE_UP", 100, 200, Color.RED);
        UIElements.createDropdown(stage, keyboardKeys, 200, 200, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SelectBox<String> selectBox = (SelectBox<String>) actor;
                String selectedKey = selectBox.getSelected();
                int keycode = InputOutputManager.getInstance().getKeyboard().convertLetterToLibGDXKeycode(selectedKey.charAt(0)); // Convert the selected letter to LibGDX keycode
                InputOutputManager.getInstance().getKeyboard().setKeyBinding("MOVE_UP", keycode);
                System.out.println("MOVE_LEFT bound to: " + selectedKey);
                InputOutputManager.getInstance().getKeyboard().printKeyBindings();
            }
        });



        UIElements.createLabel(stage, "MOVE_DOWN", 100, 100, Color.RED);
        UIElements.createDropdown(stage, keyboardKeys, 200, 100, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SelectBox<String> selectBox = (SelectBox<String>) actor;
                String selectedKey = selectBox.getSelected();
                int keycode = InputOutputManager.getInstance().getKeyboard().convertLetterToLibGDXKeycode(selectedKey.charAt(0)); // Convert the selected letter to LibGDX keycode
                InputOutputManager.getInstance().getKeyboard().setKeyBinding("MOVE_DOWN", keycode);
                System.out.println("MOVE_LEFT bound to: " + selectedKey);
                InputOutputManager.getInstance().getKeyboard().printKeyBindings();
            }
        });

        batch = new SpriteBatch();
        background = new Texture("background/bg-1.jpg");


    }





    /**
     * Renders the current canvas if it is not null, passing along the delta time for frame-rate-independent rendering.
     *
     * @param delta The time in seconds since the last render call.
     */
    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Update the state of the canvas. Currently, this method does not contain logic,
     * as the stage handles updates in the render method.
     * @param delta The time in seconds since the last update.
     */
    @Override
    public void update(float delta) {

    }

    /**
     * Dispose of the resources used by the stage to free up memory.
     */
    @Override
    public void dispose() {
        background.dispose();

    }
}
