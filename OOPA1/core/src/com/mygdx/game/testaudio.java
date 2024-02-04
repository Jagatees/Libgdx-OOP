package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public class testaudio {

    private Sound soundEffect;
    private float volume; // Range from 0 (Mute) to 1(Full Volume) , Default is 1
    private float pitch; // Range from 0.5 (on octave down) to 2 (one octave up). Default is 1
    private float pan; // Range from -1 (full left) to 1(full right) , Default is 0 (Centered)
    private String filepath; // "SoundEffect/XXX.mp3"
    // When i create the function the set(string filename) inside set the SoundEffect


    public void playSoundEffect(){
        soundEffect = Gdx.audio.newSound(
                Gdx.files.internal("SoundEffect/mouseClick.mp3"));
        soundEffect.play(1, 1, 0);
    }

    public void dispose() {
        soundEffect.dispose();
    }
}
