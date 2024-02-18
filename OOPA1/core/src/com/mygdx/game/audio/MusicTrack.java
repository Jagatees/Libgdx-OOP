package com.mygdx.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.audio.AudioAssest;

public class MusicTrack implements AudioAssest {
    private Music music;
    private float volume;

    public MusicTrack(String path) {
        this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
        this.volume = 1.0f; // Default volume (max)
    }

    @Override
    public void play() {
        if (!music.isPlaying()) {
            music.setVolume(volume);
            music.play();
        }
    }

    @Override
    public void stop() {
        music.stop();
    }

    @Override
    public void dispose() {
        music.dispose();
    }

    @Override
    public void setVolume(float volume) {
        this.volume = volume;
        music.setVolume(volume);
    }

    public void setLooping(boolean looping) {
        music.setLooping(looping);
    }
}
