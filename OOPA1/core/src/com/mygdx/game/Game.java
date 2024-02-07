package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;


public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	private Stage stage;
	private Label label;
	
	// Test (jun)
	private Player pacman;
	private nonPlayer redGhost;
	private nonPlayer blueGhost;
	private nonPlayer yellowGhost;
	private nonPlayer greenGhost;
	
	// To be added to List<Entity> later
	private nonPlayer normalPellet;
	private nonPlayer powerPellet;

	private ShapeRenderer shapeRenderer;
	private RectangleEntity rectangleEntity;




	// Test random X & Y coordinates for nonPlayer entities
	float xMin = 0.0f;
	float xMax = 500.0f;
	float yMin = 2.0f;
	float yMax = 400.0f;

	private OrthographicCamera camera;
	//private MapRenderer mapRenderer;
	private TiledMap map;
	private SpriteBatch spriteBatch;

	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		camera.update();

		// Load your TMX map
		map = new TmxMapLoader().load("map1.tmx");
		rectangleEntity = new RectangleEntity(100, 100, 50, 50, Color.RED);
		shapeRenderer = new ShapeRenderer(); // Initialize the ShapeRenderer


		batch = new SpriteBatch();

		// Creation of PacMan object
		pacman = new Player("pacman.png", 300, 100, 10, "Normal", false);
		
		
		// Random position (commented out first for testing)
		
		/*
		Random random = new Random();
		
		redGhost = new nonPlayer("redGhost.png", random.nextFloat() * (xMax - xMin) + xMin, random.nextFloat() * (yMax - yMin) + yMin, 10, "Chase", true);
		blueGhost = new nonPlayer("blueGhost.png", random.nextFloat() * (xMax - xMin) + xMin, random.nextFloat() * (yMax - yMin) + yMin, 10, "Chase", true);
		yellowGhost = new nonPlayer("yellowGhost.png", random.nextFloat() * (xMax - xMin) + xMin, random.nextFloat() * (yMax - yMin) + yMin, 10, "Chase", true);
		greenGhost = new nonPlayer("greenGhost.png", random.nextFloat() * (xMax - xMin) + xMin, random.nextFloat() * (yMax - yMin) + yMin, 10, "Chase", true);
		
		*/
		
		// Test creation 1 by 1 - to be added into EntityList later
		
		redGhost = new nonPlayer("redGhost.png", 500, 100, 10, "Chase", true);
		blueGhost = new nonPlayer("blueGhost.png", 600, 100, 10, "Chase", true);
		yellowGhost = new nonPlayer("yellowGhost.png", 700, 100, 10, "Chase", true);
		greenGhost = new nonPlayer("greenGhost.png", 800, 100, 10, "Chase", true);
		
		normalPellet = new nonPlayer("normalPellet.png", 400, 100, 0, "Present", false);
		powerPellet = new nonPlayer("powerPellet.png", 430, 100, 0, "Present", false);
	}


	@Override
	public void render() {
		// Check for collision between PacMan and the rectangle
		if (checkCollision(pacman.getxCoords(), pacman.getyCoords(), pacman.getWidth(), pacman.getHeight(),
				rectangleEntity.x, rectangleEntity.y, rectangleEntity.width, rectangleEntity.height)) {
			// Handle collision
			handleCollision();
		}

		ScreenUtils.clear(0,0,0.2f,1);

		// Update the camera
		camera.update();

		batch.begin();

		// Your existing drawing code for PacMan, ghosts, and pellets
		handlePlayerInput();
		pacman.draw(batch);
		redGhost.draw(batch);
		blueGhost.draw(batch);
		yellowGhost.draw(batch);
		greenGhost.draw(batch);
		normalPellet.draw(batch);
		powerPellet.draw(batch);

		batch.end(); // End SpriteBatch before starting to draw with ShapeRenderer

		// Render the rectangle with filled color
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(rectangleEntity.color); // Use the rectangle's color for filling
		shapeRenderer.rect(rectangleEntity.x, rectangleEntity.y, rectangleEntity.width, rectangleEntity.height);
		shapeRenderer.end();

		// Begin a new ShapeRenderer batch for drawing outlines in Line mode
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.GREEN); // Set color to green for outlines

		// Draw outlines around PacMan and ghosts for debugging
		shapeRenderer.rect(pacman.getxCoords(), pacman.getyCoords(), pacman.getWidth(), pacman.getHeight());
		// Assuming getxCoords(), getyCoords(), getWidth(), and getHeight() methods exist for ghosts
		shapeRenderer.rect(redGhost.getxCoords(), redGhost.getyCoords(), redGhost.getWidth(), redGhost.getHeight());
		shapeRenderer.rect(blueGhost.getxCoords(), blueGhost.getyCoords(), blueGhost.getWidth(), blueGhost.getHeight());
		shapeRenderer.rect(yellowGhost.getxCoords(), yellowGhost.getyCoords(), yellowGhost.getWidth(), yellowGhost.getHeight());
		shapeRenderer.rect(greenGhost.getxCoords(), greenGhost.getyCoords(), greenGhost.getWidth(), greenGhost.getHeight());
		// Optionally, draw outlines around pellets or any other entities as needed

		shapeRenderer.end();

		// If you have more SpriteBatch rendering to do after this, remember to start it again with batch.begin()
	}


	private void handlePlayerInput() {
		float moveAmount = 200 * Gdx.graphics.getDeltaTime(); // Adjust speed as necessary

		float newX = pacman.getxCoords();
		float newY = pacman.getyCoords();

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			newY += moveAmount;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			newY -= moveAmount;
		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			newX -= moveAmount;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			newX += moveAmount;
		}

		// Check for potential collision with the rectangle before moving
		if (!checkCollision(newX, newY, pacman.getWidth(), pacman.getHeight(),
				rectangleEntity.x, rectangleEntity.y, rectangleEntity.width, rectangleEntity.height)) {
			// Only gitupdate PacMan's position if no collision is detected
			pacman.setxCoords(newX);
			pacman.setyCoords(newY);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		map.dispose();
		stage.dispose();
	}


	private void handleCollision() {
		// Collision handling logic here
		System.out.println("Collision detected between PacMan and the rectangle!");
	}

	private boolean checkCollision(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
//		System.out.println("Checking collision");

		return x1 < x2 + width2 && x1 + width1 > x2 && y1 < y2 + height2 && y1 + height1 > y2;
	}
}
