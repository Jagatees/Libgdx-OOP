package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.nonPlayer;
import com.mygdx.game.audio.AudioManager;
import static com.mygdx.game.audio.AudioAssetKey.*;


public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	AudioManager audioManager = new AudioManager();

	// Entity Folder
	private Player pacman;
	private nonPlayer redGhost;
	private nonPlayer blueGhost;
	private nonPlayer yellowGhost;
	private nonPlayer greenGhost;
	private nonPlayer normalPellet;
	private nonPlayer powerPellet;
	float xMin = 0.0f;
	float xMax = 500.0f;
	float yMin = 2.0f;
	float yMax = 400.0f;



	@Override
	public void create () {
		batch = new SpriteBatch();
		audioManager.loadSoundEffect(MOUSE_CLICK, "assets/SoundEffect/mouseClick.mp3");
		// audioManager.play(MOUSE_CLICK);

		// Creation of PacMan object
		pacman = new Player("assets/entity/pacman.png", 300, 100, 10, "Normal", false, 0, 3);
		redGhost = new nonPlayer("assets/entity/redGhost.png", 500, 100, 10, "Chase", true, false);
		blueGhost = new nonPlayer("assets/entity/blueGhost.png", 600, 100, 10, "Chase", true, false);
		yellowGhost = new nonPlayer("assets/entity/yellowGhost.png", 700, 100, 10, "Chase", true, false);
		greenGhost = new nonPlayer("assets/entity/greenGhost.png", 800, 100, 10, "Chase", true, false);


		normalPellet = new nonPlayer("assets/entity/normalPellet.png", 400, 100, 0, "Present", false, false);
		powerPellet = new nonPlayer("assets/entity/powerPellet.png", 430, 100, 0, "Present", false, false);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		// Render //
		batch.begin();

		pacman.draw(batch);
		redGhost.draw(batch);
		blueGhost.draw(batch);
		yellowGhost.draw(batch);
		greenGhost.draw(batch);
		normalPellet.draw(batch);
		powerPellet.draw(batch);


		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
