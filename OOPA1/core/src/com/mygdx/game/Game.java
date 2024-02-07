package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
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

	testaudio testaudio = new testaudio();
	
	
	// Test random X & Y coordinates for nonPlayer entities
	
	float xMin = 0.0f;
	float xMax = 500.0f;
	float yMin = 2.0f;
	float yMax = 400.0f;

	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// Create a Label with text
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = new BitmapFont(); // Use a BitmapFont or any other font
		labelStyle.fontColor = Color.WHITE; // Set font color

		label = new Label("Hello, LibGDX!", labelStyle);
		label.setPosition(100, 100); // Set the position of the label
		label.setAlignment(Align.center);

		// Add the label to the stage
		stage.addActor(label);

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		testaudio.playSoundEffect();
		
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
	public void render () {
		ScreenUtils.clear(0,0,0.2f,1);

		batch.begin();
		batch.draw(img, 0, 0);
		
		// Drawing of PacMan
		pacman.draw(batch);
		
		// Drawing of Ghosts - to be added to entityList later
		redGhost.draw(batch);
		blueGhost.draw(batch);
		yellowGhost.draw(batch);
		greenGhost.draw(batch);
		
		// Drawing of pellet
		normalPellet.draw(batch);
		powerPellet.draw(batch);
		
		
		batch.end();

		// Update and draw the stage
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		stage.dispose();
		testaudio.dispose();

	}
}
