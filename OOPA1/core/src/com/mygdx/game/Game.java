package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Canvas.UIManager;
import com.mygdx.game.Collision.CollisionManager;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.Player;
import com.mygdx.game.Entity.PlayerController;
import com.mygdx.game.Entity.nonPlayer;


public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	private ShapeRenderer shapeRenderer;

	// Entity
	private Player pacman;
	private Player enemy;

	private nonPlayer wall;

	// Manager
	private EntityManager entityManager ;
	private CollisionManager collisionManager;

	// Not suppose to be here
	private PlayerController playerController;

	private UIManager uiManager;



	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		collisionManager = new CollisionManager();
		entityManager = new EntityManager();

		pacman = new Player("pacman.png", 100, 100, 10, Entity.EntityState.NULL, false,  50, 50, Entity.EntityType.NULL);
		wall = new nonPlayer("wall.jpg", 400, 100,0 , Entity.EntityState.NULL, false, true, 100, 100, Entity.EntityType.NULL);

		enemy = new Player("pacman.png", 300, 100, 10, Entity.EntityState.NULL, true,  50, 50, Entity.EntityType.NULL);

		entityManager.addEntity(pacman);
		entityManager.addEntity(enemy);
		entityManager.addEntity(wall);

		playerController = new PlayerController(pacman, entityManager, collisionManager);
		pacman.setPlayerController(playerController);

		uiManager = new UIManager();

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
		shapeRenderer.rect(entityManager.getxCords(enemy), entityManager.getyCords(enemy), entityManager.getWidth(enemy), entityManager.getHeight(enemy));

		shapeRenderer.end();
		entityManager.movement(pacman);
		entityManager.movement(enemy);

		uiManager.draw();

	}


	public void resize(int width, int height) {
		uiManager.resize(width, height);
	}


	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		uiManager.dispose();

	}
}
