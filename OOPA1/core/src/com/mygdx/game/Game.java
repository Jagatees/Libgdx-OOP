package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
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




	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		pacman = new Player("assets/entity/pacman.png", 300, 100, 10, Entity.EntityState.NULL, false, 0, 3, 50, 50);
		wall = new nonPlayer("assets/wall.jpg", 900, 100,0 , Entity.EntityState.NULL, false, true, 100, 100);

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

		shapeRenderer.rect(pacman.getxCords(), pacman.getyCords(), pacman.getWidth(), pacman.getHeight());
		shapeRenderer.rect(wall.getxCords(), wall.getyCords(), wall.getWidth(), wall.getHeight());

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
		float futureX = pacman.getxCords();
		float futureY = pacman.getyCords();
		float speed = pacman.getSpeed(); // Assuming there's a method to get Pacman's speed

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
		return checkCollision(futureX, futureY, pacman.getWidth(), pacman.getHeight(), wall.getxCords(), wall.getyCords(), wall.getWidth(), wall.getHeight());
	}

	private boolean checkCollision(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
		return x1 < x2 + width2 &&
				x1 + width1 > x2 &&
				y1 < y2 + height2 &&
				y1 + height1 > y2;
	}


	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose(); // Dispose of the ShapeRenderer

	}
}
