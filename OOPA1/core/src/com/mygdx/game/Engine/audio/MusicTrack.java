package com.mygdx.game.Engine.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Represents a music track within the game, encapsulating functionality to play,
 * stop, and manage the music resource. Implements the AudioAsset interface to standardize
 * audio operations.
 */
public class MusicTrack implements AudioAsset {
    private Music music;
    private float volume;

    /**
     * Constructs a MusicTrack object and loads the music resource from the specified path.
     *
     * @param path The filesystem path to the music file.
     */
    public MusicTrack(String path) {
        this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
        this.volume = 1.0f; // Default volume (max)
    }

    /**
     * Starts playback of the music track, if it's not already playing. Sets the volume before playing.
     */
    @Override
    public void play() {
        if (!music.isPlaying()) {
            music.setVolume(volume);
            music.play();
        }
    }

    /**
     * Stops playback of the music track.
     */
    @Override
    public void stop() {
        music.stop();
    }

    /**
     * Disposes of the music resource. Should be called when the music track is no longer needed,
     * to free up system resources.
     */
    @Override
    public void dispose() {
        music.dispose();
    }

    /**
     * Sets the volume for the music track.
     *
     * @param volume A float value representing the desired volume level, where 0.0 is silent and 1.0 is the maximum volume.
     */
    @Override
    public void setVolume(float volume) {
        this.volume = volume;
        music.setVolume(volume);
    }

    @Override
    public float getVolume() {
        return this.volume;
    }

    /**
     * Sets whether the music track should loop continuously.
     *
     * @param looping A boolean value, where true indicates the track should loop, and false indicates it should play only once.
     */
    public void setLooping(boolean looping) {
        music.setLooping(looping);
    }
}
