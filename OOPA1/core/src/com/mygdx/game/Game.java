package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
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

	// Entity Manger
	EntityManager entityManager = new EntityManager();




	@Override
	public void create () {
		batch = new SpriteBatch();
		audioManager.loadSoundEffect(MOUSE_CLICK, "assets/SoundEffect/mouseClick.mp3");
		// audioManager.play(MOUSE_CLICK);

		// Creation of PacMan object
		pacman = new Player("assets/entity/pacman.png", 300, 100, 10, Entity.EntityState.CHASE, false, 0, 3);
		redGhost = new nonPlayer("assets/entity/redGhost.png", 500, 100, 10, Entity.EntityState.CHASE, true, false);
		blueGhost = new nonPlayer("assets/entity/blueGhost.png", 600, 100, 10, Entity.EntityState.CHASE, true, false);
		yellowGhost = new nonPlayer("assets/entity/yellowGhost.png", 700, 100, 10, Entity.EntityState.CHASE, true, false);
		greenGhost = new nonPlayer("assets/entity/greenGhost.png", 800, 100, 10, Entity.EntityState.CHASE, true, false);


		normalPellet = new nonPlayer("assets/entity/normalPellet.png", 400, 100, 0, Entity.EntityState.PRESENT, false, false);
		powerPellet = new nonPlayer("assets/entity/powerPellet.png", 430, 100, 0, Entity.EntityState.PRESENT, false, false);

		entityManager.addEntity(pacman);
		entityManager.addEntity(redGhost);
		entityManager.addEntity(blueGhost);
		entityManager.addEntity(yellowGhost);
		entityManager.addEntity(greenGhost);
		entityManager.addEntity(normalPellet);
		entityManager.addEntity(powerPellet);


	}


	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();

		entityManager.render(batch);

		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
