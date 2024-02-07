package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.audio.AudioManager;
import static com.mygdx.game.audio.AudioAssetKey.*;


public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	AudioManager audioManager = new AudioManager();


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		audioManager.loadSoundEffect(JUMP, "SoundEffect/mouseClick.mp3");
		audioManager.play(JUMP);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
