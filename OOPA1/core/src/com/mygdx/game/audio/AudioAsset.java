package com.mygdx.game.audio;

public interface AudioAsset {
    void play();
    void stop();
    void dispose();
    void setVolume(float volume);
}
