package com.mygdx.game.Engine.audio;


import java.util.HashMap;
import java.util.Map;

/**
 * Manages audio assets for the game, including sound effects and music tracks.
 * Allows for loading, playing, stopping, and disposing of audio assets.
 */
public class AudioManager {
    private static AudioManager instance; // Singleton instance
    private Map<AudioAssetKey, AudioAsset> audioAssets;


    /**
     * Constructor initializes the map for storing audio assets.
     */
    public AudioManager() {
        audioAssets = new HashMap<>();
    }

    /**
     * Loads a sound effect and associates it with a given key.
     *
     * @param key The key to associate with the sound effect.
     * @param path The file path to the sound effect resource.
     */
    public void loadSoundEffect(AudioAssetKey key, String path) {
        audioAssets.put(key, new SoundEffect(path));
    }

    /**
     * Loads a music track, sets its looping preference, and associates it with a given key.
     *
     * @param key The key to associate with the music track.
     * @param path The file path to the music resource.
     * @param looping Whether the music track should loop when played.
     */
    public void loadMusicTrack(AudioAssetKey key, String path, boolean looping) {
        MusicTrack musicTrack = new MusicTrack(path);
        musicTrack.setLooping(looping);
        audioAssets.put(key, musicTrack);
    }

    /**
     * Plays the audio asset associated with the given key.
     *
     * @param key The key of the audio asset to play.
     */
    public void play(AudioAssetKey key) {
        if (audioAssets.containsKey(key)) {
            audioAssets.get(key).play();
        }
    }

    /**
     * Stops the audio asset associated with the given key.
     *
     * @param key The key of the audio asset to stop.
     */
    public void stop(AudioAssetKey key) {
        if (audioAssets.containsKey(key)) {
            audioAssets.get(key).stop();
        }
    }


    /**
     * Sets the volume for the audio asset associated with the given key.
     *
     * @param key The key of the audio asset for which to set the volume.
     * @param volume The volume level to set, typically between 0 (silent) and 1 (maximum volume).
     */
    public void setVolume(AudioAssetKey key, float volume) {
        if (audioAssets.containsKey(key)) {
            audioAssets.get(key).setVolume(volume);
        }
    }

    /**
     * Disposes of all loaded audio assets to free up resources. Should be called when the game is closing
     * or when the assets are no longer needed.
     */
    public void dispose() {
        for (AudioAsset asset : audioAssets.values()) {
            asset.dispose();
        }
    }

    // Static method to get the singleton instance
    public static synchronized AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }


    public float getVolume(AudioAssetKey key) {
        if (audioAssets.containsKey(key)) {
            return audioAssets.get(key).getVolume();
        }
        return -1; // Indicates that the asset was not found or does not support volume control
    }





}
