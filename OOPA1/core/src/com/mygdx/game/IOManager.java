package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class IOManager extends Game {
    private Texture PacManImage;
    private SpriteBatch batch;
    private Rectangle PacMan;
    private BitmapFont font;
    private String gameState;
    private final int BORDER_WIDTH = 10; // Width of the border

    @Override
    public void create() {
        PacManImage = new Texture(Gdx.files.internal("PacMan.png"));
        batch = new SpriteBatch();
        font = new BitmapFont();
        gameState = "START_SCREEN";

        PacMan = new Rectangle();
        PacMan.x = 0; 
        PacMan.y = 400;
        PacMan.height = 50;
        PacMan.width = 50;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        batch.draw(PacManImage, PacMan.x, PacMan.y, PacMan.width, PacMan.height);

        if (gameState.equals("START_SCREEN")) {
            font.draw(batch, "PRESS SPACE TO START THE GAME", Gdx.graphics.getWidth() / 2 - 120, Gdx.graphics.getHeight() / 2);
        } else if (gameState.equals("START")) {
            font.draw(batch, "GAME START", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
        } else if (gameState.equals("PAUSE")) {
            font.draw(batch, "GAME PAUSE", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
            font.draw(batch, "PRESS SPACE TO CONTINUE", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 50);
        } else if (gameState.equals("RESUME")) {
            font.draw(batch, "GAME RESUME", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
        } else if (gameState.equals("WIN")) {
            font.draw(batch, "YOU WIN", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
            font.draw(batch, "PRESS SPACE TO RESTART", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 50);
        } else if (gameState.equals("QUIT") ) {
        	font.draw(batch, "PRESS SPACE TO START THE GAME", Gdx.graphics.getWidth() / 2 - 120, Gdx.graphics.getHeight() / 2);
        }

        batch.end();

        handleInput();
    }

    private void handleInput() {
        if (gameState.equals("START_SCREEN") || gameState.equals("QUIT")) {
            if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
                gameState = "START";
            }
        }

        if (gameState.equals("START")) {
            if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
                gameState = "PAUSE";
            } else {
                // Handle game logic during gameplay
                if (Gdx.input.isKeyPressed(Keys.LEFT) && PacMan.x > BORDER_WIDTH) PacMan.x -= 200 * Gdx.graphics.getDeltaTime();  
                if (Gdx.input.isKeyPressed(Keys.RIGHT) && PacMan.x < Gdx.graphics.getWidth() - PacMan.width - BORDER_WIDTH) PacMan.x += 200 * Gdx.graphics.getDeltaTime();
                if (Gdx.input.isKeyPressed(Keys.UP) && PacMan.y < Gdx.graphics.getHeight() - PacMan.height - BORDER_WIDTH) PacMan.y += 200 * Gdx.graphics.getDeltaTime();
                if (Gdx.input.isKeyPressed(Keys.DOWN) && PacMan.y > BORDER_WIDTH) PacMan.y -= 200 * Gdx.graphics.getDeltaTime();
            
                // Check if PacMan reaches the bottom right corner
                if (PacMan.x >= Gdx.graphics.getWidth() - PacMan.width - BORDER_WIDTH && PacMan.y <= BORDER_WIDTH) {
                    gameState = "WIN";
                }
            }
        }

        if (gameState.equals("PAUSE")) {
            if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
                gameState = "RESUME";
            }
        }

        if (gameState.equals("RESUME")) {
            if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
                gameState = "PAUSE";
            } else {
                // Handle game logic during gameplay
                if (Gdx.input.isKeyPressed(Keys.LEFT) && PacMan.x > BORDER_WIDTH) PacMan.x -= 200 * Gdx.graphics.getDeltaTime();  
                if (Gdx.input.isKeyPressed(Keys.RIGHT) && PacMan.x < Gdx.graphics.getWidth() - PacMan.width - BORDER_WIDTH) PacMan.x += 200 * Gdx.graphics.getDeltaTime();
                if (Gdx.input.isKeyPressed(Keys.UP) && PacMan.y < Gdx.graphics.getHeight() - PacMan.height - BORDER_WIDTH) PacMan.y += 200 * Gdx.graphics.getDeltaTime();
                if (Gdx.input.isKeyPressed(Keys.DOWN) && PacMan.y > BORDER_WIDTH) PacMan.y -= 200 * Gdx.graphics.getDeltaTime();
            
                // Check if PacMan reaches the bottom right corner
                if (PacMan.x >= Gdx.graphics.getWidth() - PacMan.width - BORDER_WIDTH && PacMan.y <= BORDER_WIDTH) {
                    gameState = "WIN";
                }
            }
        }

        if (gameState.equals("WIN")) {
            if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
                // Reset the game state and PacMan position
                gameState = "START_SCREEN";
                PacMan.x = 0;
                PacMan.y = 400;
            }
        }

        if (Gdx.input.isKeyJustPressed(Keys.Q)) {
            // Reset the game state and PacMan position
            gameState = "START_SCREEN";
            PacMan.x = 0;
            PacMan.y = 400;
        }
    }


    @Override
    public void dispose() {
        batch.dispose();
        PacManImage.dispose();
        font.dispose();
    }
}
