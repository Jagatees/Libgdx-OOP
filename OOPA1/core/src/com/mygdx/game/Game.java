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

import java.util.List;


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
		entityManager.addEntity(wall);
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
			if (!checkFutureCollision(Input.Keys.RIGHT, pacman, entityManager.getEntitiesOfType(nonPlayer.class))) {
				playerController.right();
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (!checkFutureCollision(Input.Keys.LEFT , pacman, entityManager.getEntitiesOfType(nonPlayer.class))) {
				playerController.left();
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if (!checkFutureCollision(Input.Keys.UP , pacman, entityManager.getEntitiesOfType(nonPlayer.class))) {
				playerController.up();
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			if (!checkFutureCollision(Input.Keys.DOWN , pacman, entityManager.getEntitiesOfType(nonPlayer.class))) {
				playerController.down();
			}
		}
	}



	private boolean checkFutureCollision(int direction, Player pacman, List<nonPlayer> entities) {
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

		for (Entity entity : entities) {
			if (collisionManager.checkCollision(futureX, futureY, entityManager.getWidth(pacman), entityManager.getHeight(pacman), entityManager.getxCords(entity), entityManager.getyCords(entity), entityManager.getWidth(entity), entityManager.getHeight(entity))) {
				return true; // Collision detected
			}
		}

		return false; // No collision detected
	}


	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose(); // Dispose of the ShapeRenderer

	}
}
