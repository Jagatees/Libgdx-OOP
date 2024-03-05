package com.mygdx.game.Engine.Canvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImageRenderer {
    private SpriteBatch batch;
    private Texture backgroundTexture;

    public ImageRenderer(SpriteBatch batch) {
        this.batch = batch;
    }

    public void setBackgroundImage(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }


    public void drawImage(Texture texture, float x, float y, float width, float height) {
        batch.begin();

        // Draw the background first if it exists
        if (backgroundTexture != null) {
            drawBackground();
        }

        // Then draw the image on top
        batch.draw(texture, x, y, width, height);

        batch.end();
    }

    private void drawBackground() {
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
