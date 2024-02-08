package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class aimanager extends ApplicationAdapter {
    SpriteBatch batch;
    Texture ghostTexture, wallTexture;
    Vector2 ghostPosition;
    Vector2 ghostVelocity;
    Random random = new Random();
    Maze maze; 
    float wallScale, ghostScale;
    ShapeRenderer shapeRenderer;
    private float timeToNextDirChange = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        ghostTexture = new Texture("realghost.jpg");
        wallTexture = new Texture("wall.jpg");
        
        maze = new Maze();
        
        // Calculate scale factors to fit the screen
        wallScale = Math.min(Gdx.graphics.getWidth() / (float)(maze.getWidth() * wallTexture.getWidth()),
                             Gdx.graphics.getHeight() / (float)(maze.getHeight() * wallTexture.getHeight()));
        ghostScale = 0.5f * wallScale; // Adjust for smaller ghost

        ghostPosition = new Vector2(100, 100);
        ghostVelocity = new Vector2(60, 60); // Initial random velocity
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateGhostPosition(Gdx.graphics.getDeltaTime());

        batch.begin();
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (maze.isWall(x, y)) {
                    batch.draw(wallTexture, x * wallTexture.getWidth() * wallScale, 
                               Gdx.graphics.getHeight() - (y + 1) * wallTexture.getHeight() * wallScale, // Flip Y-axis
                               wallTexture.getWidth() * wallScale, 
                               wallTexture.getHeight() * wallScale);
                }
            }
        }
        batch.draw(ghostTexture, ghostPosition.x, ghostPosition.y, 
                   ghostTexture.getWidth() * ghostScale, 
                   ghostTexture.getHeight() * ghostScale);
        batch.end();
    }

    private void updateGhostPosition(float deltaTime) {
        // Calculate the new position based on velocity
        Vector2 newPos = new Vector2(ghostPosition.x + ghostVelocity.x * deltaTime, 
                                     ghostPosition.y + ghostVelocity.y * deltaTime);
        
        // Check if the new position would be outside the screen bounds or collide with a wall
        if (isOutOfBounds(newPos) || isCollidingWithWall(newPos)) {
            // Randomly choose a new direction when hitting the edge or a wall
            chooseNewDirection();
        } else {
            // If no collision or out of bounds, update the position
            ghostPosition.set(newPos);
        }
    }

    private boolean isOutOfBounds(Vector2 pos) {
        // Check if the position is outside the screen bounds
        return pos.x < 0 || pos.x > Gdx.graphics.getWidth() - ghostTexture.getWidth() * ghostScale ||
               pos.y < 0 || pos.y > Gdx.graphics.getHeight() - ghostTexture.getHeight() * ghostScale;
    }

    private boolean isCollidingWithWall(Vector2 pos) {
        // Convert the position to cell coordinates in the maze
        int cellX = (int)(pos.x / (wallTexture.getWidth() * wallScale));
        int cellY = (int)((Gdx.graphics.getHeight() - pos.y) / (wallTexture.getHeight() * wallScale)); // Adjust for Y-axis inversion

        // Ensure the cell coordinates are within the bounds of the maze layout
        if (cellX < 0 || cellX >= maze.getWidth() || cellY < 0 || cellY >= maze.getHeight()) {
            // If out of bounds, treat it as not colliding for this context
            return false;
        }

        // Check if the cell is a wall
        return maze.isWall(cellX, cellY);
    }


    private void chooseNewDirection() {
        // Randomly change direction by picking a new velocity vector
        float angle = random.nextFloat() * 2f * (float)Math.PI; // Random angle in radians
        float speed = 100; // Set a constant speed
        ghostVelocity.set(speed * (float)Math.cos(angle), speed * (float)Math.sin(angle));
    }

    @Override
    public void dispose() {
        batch.dispose();
        ghostTexture.dispose();
        wallTexture.dispose();
        shapeRenderer.dispose();
    }
}
