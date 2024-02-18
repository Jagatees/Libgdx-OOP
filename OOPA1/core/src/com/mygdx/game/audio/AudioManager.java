package com.mygdx.game.audio;


import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private Map<AudioAssetKey, AudioAssest> audioAssets;

    public AudioManager() {
        audioAssets = new HashMap<>();
    }

    public void loadSoundEffect(AudioAssetKey key, String path) {
        audioAssets.put(key, new SoundEffect(path));
    }

    public void loadMusicTrack(AudioAssetKey key, String path, boolean looping) {
        MusicTrack musicTrack = new MusicTrack(path);
        musicTrack.setLooping(looping);
        audioAssets.put(key, musicTrack);
    }

    public void play(AudioAssetKey key) {
        if (audioAssets.containsKey(key)) {
            audioAssets.get(key).play();
        }
    }

    public void stop(AudioAssetKey key) {
        if (audioAssets.containsKey(key)) {
            audioAssets.get(key).stop();
        }
    }

    public void setVolume(String key, float volume) {
        if (audioAssets.containsKey(key)) {
            audioAssets.get(key).setVolume(volume);
        }
    }

    public void dispose() {
        for (AudioAssest asset : audioAssets.values()) {
            asset.dispose();
        }
    }
}
