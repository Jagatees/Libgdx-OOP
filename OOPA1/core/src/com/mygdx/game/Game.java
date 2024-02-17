package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.PlayerController;
import com.mygdx.game.Entity.nonPlayer;


public class Game extends ApplicationAdapter {
	SpriteBatch batch;

	private Player pacman;
	private nonPlayer wall;
	private nonPlayer wall2;

	EntityManager entityManager = new EntityManager();
	PlayerController playerController;
	ShapeRenderer shapeRenderer;
	CollisionManager collisionManager = new CollisionManager();


	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		pacman = new Player("pacman.png", 300, 100, 10, Entity.EntityState.NULL, false, 0, 3, 50, 50, Entity.EntityType.PLAYER);
		wall = new nonPlayer("wall.jpg", 900, 100,0 , Entity.EntityState.NULL, false, true, 100, 100, Entity.EntityType.WALL);
		wall2 = new nonPlayer("wall.jpg", 900, 300,0 , Entity.EntityState.NULL, false, true, 100, 100, Entity.EntityType.EMPTY);

		entityManager.addEntity(pacman);
		entityManager.addEntity(wall);
		entityManager.addEntity(wall2);


		playerController = new PlayerController(pacman, entityManager, collisionManager);
		pacman.setPlayerController(playerController); // Assuming you add a setter for playerController in Player

	}


	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();
		entityManager.render(batch);
		batch.end();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line); // Start drawing lines
		shapeRenderer.setColor(Color.RED); // Set the color of the bounding box to red

		shapeRenderer.rect(entityManager.getxCords(pacman), entityManager.getyCords(pacman), entityManager.getWidth(pacman), entityManager.getHeight(pacman));
		shapeRenderer.rect(entityManager.getxCords(wall), entityManager.getyCords(wall), entityManager.getWidth(wall), entityManager.getHeight(wall));
		shapeRenderer.rect(entityManager.getxCords(wall2), entityManager.getyCords(wall2), entityManager.getWidth(wall2), entityManager.getHeight(wall2));


		shapeRenderer.end();
		pacman.userControlledMovement();

//		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//			playerController.move(Input.Keys.RIGHT);
//		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//			playerController.move(Input.Keys.LEFT);
//		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//			playerController.move(Input.Keys.UP);
//		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//			playerController.move(Input.Keys.DOWN);
//		}


	}





	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
	}
}
