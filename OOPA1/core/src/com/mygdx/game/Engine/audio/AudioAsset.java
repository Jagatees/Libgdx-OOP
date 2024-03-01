package com.mygdx.game.Engine.audio;

public interface AudioAsset {
    void play();
    void stop();
    void dispose();
    void setVolume(float volume);

    float getVolume();
}
