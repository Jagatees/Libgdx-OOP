package com.mygdx.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Represents a sound effect in the game. This class provides methods to play,
 * stop, and manage a sound resource, following the AudioAsset interface to ensure
 * consistent audio behavior across the game.
 */
public class SoundEffect implements AudioAsset {

    private Sound sound;
    private float volume;

    /**
     * Creates a SoundEffect instance by loading an audio file from the specified path.
     *
     * @param path The path to the audio file to be used as the sound effect.
     */
    public SoundEffect(String path) {
        this.sound = Gdx.audio.newSound(Gdx.files.internal(path));
        this.volume = 1.0f; // Default volume (max)
    }

    /**
     * Plays the sound effect at the currently set volume level.
     */
    @Override
    public void play() {
        sound.play(volume);

    }

    /**
     * Stops playback of the sound effect. This stops all instances of the sound effect.
     */
    @Override
    public void stop() {
        sound.stop();
    }

    /**
     * Disposes of the sound effect resource. This should be called when the sound effect
     * is no longer needed, to free up system resources.
     */
    @Override
    public void dispose() {
        sound.dispose();
    }


    /**
     * Sets the volume for the sound effect.
     *
     * @param volume A float value for the desired volume level, where 0.0 is silent and 1.0 is the maximum volume.
     */
    @Override
    public void setVolume(float volume) {
        this.volume = volume;
    }
}
