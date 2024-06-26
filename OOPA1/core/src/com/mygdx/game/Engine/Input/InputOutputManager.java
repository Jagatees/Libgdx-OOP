package com.mygdx.game.Engine.Input;

import com.mygdx.game.Engine.audio.AudioManager;

/**
 * Manages input and output resources for the game, encapsulating keyboard input handling
 * and audio management into a single cohesive unit. This class provides a centralized
 * point of access for managing these resources and facilitates communication between
 * the game's input handling and audio systems.
 */
public class InputOutputManager {

    private static InputOutputManager instance; // Singleton instance

    /** Instance of Keyboard for managing keyboard input */
    private Keyboard keyboard;
    /** Instance of AudioManager for managing audio output. */
    private AudioManager audioManager;


    public static synchronized InputOutputManager getInstance() {
        if (instance == null) {
            instance = new InputOutputManager();
        }
        return instance;
    }

    /**
     * Constructs an InputOutputManager with specified keyboard and audio manager instances.
     */
    public InputOutputManager() {
        this.keyboard =  Keyboard.getInstance();
        this.audioManager = AudioManager.getInstance();
    }


    public int getIntKey(int x){
        return this.keyboard.isKeyPressedInt(x);
    }

    public boolean getIntKey2(int x){
        return this.keyboard.isKeyPressedBool(x);
    }


    /**
     * Returns the audio manager associated with this InputOutputManager.
     *
     * @return The AudioManager instance used for managing audio within the game.
     */
    public AudioManager getAudioManager() {
        return audioManager;
    }

    /**
     * Returns the keyboard handler associated with this InputOutputManager.
     *
     * @return The Keyboard instance used for handling keyboard input within the game.
     */
    public Keyboard getKeyboard() {
        return keyboard;
    }



}
