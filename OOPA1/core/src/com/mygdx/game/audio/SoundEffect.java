package com.mygdx.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect implements  AudioAssest{

    private Sound sound;
    private float volume;

    public SoundEffect(String path) {
        this.sound = Gdx.audio.newSound(Gdx.files.internal(path));
        this.volume = 1.0f; // Default volume (max)
    }

    @Override
    public void play() {
        sound.play(volume);

    }

    @Override
    public void stop() {
        sound.stop();
    }

    @Override
    public void dispose() {
        sound.dispose();
    }

    @Override
    public void setVolume(float volume) {
        this.volume = volume;
    }
}
