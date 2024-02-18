package com.mygdx.game;

import com.mygdx.game.Input.Keyboard;
import com.mygdx.game.audio.AudioManager;

public class InputOutputManager {

    private Keyboard keyboard;
    private AudioManager audioManager;

    public InputOutputManager(Keyboard keyboard, AudioManager audioManager) {
        this.keyboard = keyboard;
        this.audioManager = audioManager;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

}
