package com.mygdx.game.Engine.audio;

// This interface represents an audio asset that can be played, stopped, disposed, and have its volume controlled.
public interface AudioAsset {

    // Plays the audio asset.
    void play();

    // Stops the playback of the audio asset.
    void stop();

    // Disposes of any resources associated with the audio asset.
    void dispose();

    // Sets the volume level for the audio asset.
    void setVolume(float volume);

    // Retrieves the current volume level of the audio asset.
    float getVolume();
}
