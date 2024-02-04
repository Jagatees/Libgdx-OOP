package com.mygdx.game;

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

	testaudio testaudio = new testaudio();


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
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		batch.begin();
		batch.draw(img, 0, 0);
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
