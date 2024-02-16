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

	EntityManager entityManager = new EntityManager();
	PlayerController playerController;
	ShapeRenderer shapeRenderer;
	CollisionManager collisionManager = new CollisionManager();





	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		pacman = new Player("pacman.png", 300, 100, 10, Entity.EntityState.NULL, false, 0, 3, 50, 50);
		wall = new nonPlayer("wall.jpg", 900, 100,0 , Entity.EntityState.NULL, false, true, 100, 100);

		entityManager.addEntity(pacman);
		entityManager.addEntity(wall);

		playerController = new PlayerController(entityManager.getEntity(Player.class));


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

		shapeRenderer.end();

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (!checkFutureCollision(Input.Keys.RIGHT)) {
				playerController.right();
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (!checkFutureCollision(Input.Keys.LEFT)) {
				playerController.left();
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if (!checkFutureCollision(Input.Keys.UP)) {
				playerController.up();
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			if (!checkFutureCollision(Input.Keys.DOWN)) {
				playerController.down();
			}
		}
	}


	private boolean checkFutureCollision(int direction) {
		float futureX = entityManager.getxCords(pacman);
		float futureY = entityManager.getyCords(pacman);
		float speed = entityManager.getSpeed(pacman); // Assuming there's a method to get Pacman's speed

		switch (direction) {
			case Input.Keys.LEFT:
				futureX -= speed;
				break;
			case Input.Keys.RIGHT:
				futureX += speed;
				break;
			case Input.Keys.UP:
				futureY += speed;
				break;
			case Input.Keys.DOWN:
				futureY -= speed;
				break;
		}

		// Assuming the checkCollision method checks if two entities would overlap
		return collisionManager.checkCollision(futureX, futureY, entityManager.getWidth(pacman), entityManager.getHeight(pacman), entityManager.getxCords(wall), entityManager.getyCords(wall), entityManager.getWidth(wall), entityManager.getHeight(wall));

	}


	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose(); // Dispose of the ShapeRenderer

	}
}
