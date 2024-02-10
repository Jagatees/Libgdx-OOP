package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.PlayerController;
import com.mygdx.game.Entity.nonPlayer;
import com.mygdx.game.Input.InputManager;


public class Game extends ApplicationAdapter {
	SpriteBatch batch;

	// Entity Folder
	private Player pacman;
	private nonPlayer redGhost;
	private nonPlayer blueGhost;
	private nonPlayer yellowGhost;
	private nonPlayer greenGhost;
	private nonPlayer normalPellet;
	private nonPlayer powerPellet;
	private nonPlayer wall;

	float xMin = 0.0f;
	float xMax = 500.0f;
	float yMin = 2.0f;
	float yMax = 400.0f;

	// Entity Manger
	EntityManager entityManager = new EntityManager();
	PlayerController playerController;
	InputManager inputHandler;

	ShapeRenderer shapeRenderer; // Add this line




	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer(); // Initialize the ShapeRenderer



		// Creation of PacMan object
		pacman = new Player("assets/entity/pacman.png", 300, 100, 10, Entity.EntityState.CHASE, false, 0, 3, 50, 50);
		redGhost = new nonPlayer("assets/entity/redGhost.png", 500, 100, 10, Entity.EntityState.CHASE, true, false, 50, 50);
		blueGhost = new nonPlayer("assets/entity/blueGhost.png", 600, 100, 10, Entity.EntityState.CHASE, true, false, 50, 50);
		yellowGhost = new nonPlayer("assets/entity/yellowGhost.png", 700, 100, 10, Entity.EntityState.CHASE, true, false, 50, 50);
		greenGhost = new nonPlayer("assets/entity/greenGhost.png", 800, 100, 10, Entity.EntityState.CHASE, true, false, 50, 50);
		wall = new nonPlayer("assets/wall.jpg", 900, 100,0 , Entity.EntityState.CHASE, false, true, 100, 100);

		normalPellet = new nonPlayer("assets/entity/normalPellet.png", 400, 100, 0, Entity.EntityState.PRESENT, false, false, 50, 50);
		powerPellet = new nonPlayer("assets/entity/powerPellet.png", 430, 100, 0, Entity.EntityState.PRESENT, false, false, 50, 50);

		entityManager.addEntity(pacman);
		entityManager.addEntity(wall);


		playerController = new PlayerController(entityManager.getEntity(Player.class));
		inputHandler = new InputManager(playerController);
		Gdx.input.setInputProcessor( inputHandler);




	}


	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);


		batch.begin();
		entityManager.render(batch);
		batch.end();

		if (inputHandler.isMovingLeft()) {
			playerController.left();
		}
		if (inputHandler.isMovingRight()) {
			playerController.right();
		}
		if (inputHandler.isMovingUp()) {
			playerController.up();
		}
		if (inputHandler.isMovingDown()) {
			playerController.down();
		}


		shapeRenderer.begin(ShapeRenderer.ShapeType.Line); // Start drawing lines
		shapeRenderer.setColor(Color.RED); // Set the color of the bounding box to red

		shapeRenderer.rect(pacman.getxCords(), pacman.getyCords(), pacman.getWidth(), pacman.getHeight());
		shapeRenderer.rect(wall.getxCords(), wall.getyCords(), wall.getWidth(), wall.getHeight());

		shapeRenderer.end();


	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose(); // Dispose of the ShapeRenderer

	}
}
