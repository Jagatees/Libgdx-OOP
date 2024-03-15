package com.mygdx.game.Engine.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Entity implements objectMovement {

    protected float xCords, yCords, speed;
    protected EntityState entityState;
    protected EntityType entityType;
    protected RenderType renderType;

    protected boolean isAI;
    protected float width, height;
    
    protected Texture objectTexture;
    protected Color color;

    private boolean isRemoved = false;
    
    public enum EntityType {
        NULL,
        PLAYER,
        ENEMY,
        OBJECT,
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H,
        I,
        J,
        K,
        L,
        M,
        N,
        O,
        P,
        Q,
        R,
        S,
        T,
        U,
        V,
        W,
        X,
        Y,
        Z,;

        // Method to check if the entity type is a letter
        public boolean isLetter() {
            return this.ordinal() >= A.ordinal() && this.ordinal() <= Z.ordinal();
        }

        public String getLetter() {
            if (this.isLetter()) {
                return this.name(); // This returns the name of the enum constant, which is the letter itself
            } else {
                return null; // Or any other appropriate value or action for non-letter types
            }
        }

    }


    public enum EntityState {
        NULL,
    }

    public enum RenderType {
        SHAPE, SPRITE
    }
    
    // Constructor for Entity
    // Method overloading
    
    Entity(float xCords, float yCords, float speed, EntityState state,
           boolean isAI, float width, float height, EntityType entityType,
           RenderType renderType, Color color) {
        setxCords(xCords);
        setyCords(yCords);
        setSpeed(speed);
        setState(state);
        setisAI(isAI);
        setWidth(width);
        setHeight(height);
        setEntityType(entityType);
        setRenderType(renderType);
        setColor(color);
    }

    Entity(float xCords, float yCords, float speed, EntityState state,
           boolean isAI, float width, float height, EntityType entityType,
           RenderType renderType, String fileName) {
        setxCords(xCords);
        setyCords(yCords);
        setSpeed(speed);
        setState(state);
        setisAI(isAI);
        setWidth(width);
        setHeight(height);
        setEntityType(entityType);
        setRenderType(renderType);
        textureErrorHandling(fileName);
    }
    
    // If speed not used for creating object with Color
    Entity(float xCords, float yCords, EntityState state,
            boolean isAI, float width, float height, EntityType entityType,
            RenderType renderType, Color color) {
         setxCords(xCords);
         setyCords(yCords);
         setSpeed(10); // Default to Speed 10
         setState(state);
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
         setEntityType(entityType);
         setRenderType(renderType);
         setColor(color);
     }
    
    // If speed not used for creating object with fileName
    Entity(float xCords, float yCords, EntityState state,
            boolean isAI, float width, float height, EntityType entityType,
            RenderType renderType, String fileName) {
         setxCords(xCords);
         setyCords(yCords);
         setSpeed(10); // Default to Speed 10
         setState(state);
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
         setEntityType(entityType);
         setRenderType(renderType);
         textureErrorHandling(fileName);
     }
    
    // If x and y not used for creating object with Color
    Entity(float speed, EntityState state,
            boolean isAI, float width, float height, EntityType entityType,
            RenderType renderType, Color color) {
         setxCords((float) 500); // Default to x = 500
         setyCords((float) 500); // Default to y = 500
         setSpeed(speed);
         setState(state);
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
         setEntityType(entityType);
         setRenderType(renderType);
         setColor(color);
     }
    
    // If x and y not used for creating object with Sprite
    Entity(float speed, EntityState state,
            boolean isAI, float width, float height, EntityType entityType,
            RenderType renderType, String fileName) {
         setxCords((float) 500);
         setyCords((float) 500);
         setSpeed(10); // Default to Speed 10
         setState(state);
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
         setEntityType(entityType);
         setRenderType(renderType);
         textureErrorHandling(fileName);
     }
    
    // If state not defined with Color
    Entity(float xCords, float yCords, float speed,
            boolean isAI, float width, float height, EntityType entityType,
            RenderType renderType, Color color) {
         setxCords(xCords);
         setyCords(yCords);
         setSpeed(speed);
         setState(EntityState.NULL); // Default to EntityState.NULL
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
         setEntityType(entityType);
         setRenderType(renderType);
         setColor(color);
     }
    
    // If state not defined with fileName  
    Entity(float xCords, float yCords, float speed,
            boolean isAI, float width, float height, EntityType entityType,
            RenderType renderType, String fileName) {
         setxCords(xCords);
         setyCords(yCords);
         setSpeed(speed);
         setState(EntityState.NULL); // Default to EntityState.NULL
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
         setEntityType(entityType);
         setRenderType(renderType);
         textureErrorHandling(fileName);
     }
    
    
    // If isAI not used for creating object with Color
    Entity(float xCords, float yCords, float speed, EntityState state, 
    		float width, float height, EntityType entityType, 
    		RenderType renderType, Color color) {
        setxCords(xCords);
        setyCords(yCords);
        setSpeed(speed);
        setState(state);
        setisAI(true); // Default to true if not set
        setWidth(width);
        setHeight(height);
        setEntityType(entityType);
        setRenderType(renderType);
        setColor(color);
    }
    
    // If isAI not used for creating object with fileName
    Entity(float xCords, float yCords, float speed, EntityState state, 
    		float width, float height, EntityType entityType, 
    		RenderType renderType, String fileName) {
        setxCords(xCords);
        setyCords(yCords);
        setSpeed(speed);
        setState(state);
        setisAI(true); // Default to true if not set
        setWidth(width);
        setHeight(height);
        setEntityType(entityType);
        setRenderType(renderType);
        textureErrorHandling(fileName); 
    }
    
    // If width and height not used for creating object with Color
    Entity(float xCords, float yCords, float speed, EntityState state,
           boolean isAI, EntityType entityType, RenderType renderType, Color color) {
        setxCords(xCords);
        setyCords(yCords);
        setSpeed(speed);
        setState(state);
        setisAI(isAI);
        setWidth(5); // Default to width of 5
        setHeight(5); // Default to height of 5
        setEntityType(entityType);
        setRenderType(renderType);
        setColor(color);
    }
    
    // If width and height not used for creating object with Color
    Entity(float xCords, float yCords, float speed, EntityState state,
           boolean isAI, EntityType entityType, RenderType renderType, String fileName) {
        setxCords(xCords);
        setyCords(yCords);
        setSpeed(speed);
        setState(state);
        setisAI(isAI);
        setWidth(5); // Default to width of 5
        setHeight(5); // Default to height of 5
        setEntityType(entityType);
        setRenderType(renderType);
        textureErrorHandling(fileName); 
    }

    // If EntityType not used when creating object with Color    
    Entity(float xCords, float yCords, float speed, EntityState state,
            boolean isAI, float width, float height, RenderType renderType, Color color) {
         setxCords(xCords);
         setyCords(yCords);
         setSpeed(speed);
         setState(state);
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
         setEntityType(Entity.EntityType.OBJECT); // Default to Entity.EntityType.OBJECT
         setRenderType(renderType);
         setColor(color);
     }
    
    // If EntityType not used when creating object with fileName
    Entity(float xCords, float yCords, float speed, EntityState state,
            boolean isAI, float width, float height, RenderType renderType, String fileName) {
         setxCords(xCords);
         setyCords(yCords);
         setSpeed(speed);
         setState(state);
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
         setEntityType(Entity.EntityType.OBJECT); // Default to Entity.EntityType.OBJECT
         setRenderType(renderType);
         textureErrorHandling(fileName);
     }
    
    // If EntityType not used when creating object with Color   
    Entity(float xCords, float yCords, float speed, EntityState state,
            boolean isAI, float width, float height, EntityType entityType,
            Color color) {
         setxCords(xCords);
         setyCords(yCords);
         setSpeed(speed);
         setState(state);
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
         setEntityType(entityType);
         setRenderType(Entity.RenderType.SHAPE); // Default to SHAPE RenderType (logically correct)
         setColor(color);
     }

     // If EntityType not used when creating object with fileName
     Entity(float xCords, float yCords, float speed, EntityState state,
            boolean isAI, float width, float height, EntityType entityType,
           String fileName) {
         setxCords(xCords);
         setyCords(yCords);
         setSpeed(speed);
         setState(state);
         setisAI(isAI);
         setWidth(width);
         setHeight(height);
         setEntityType(entityType);
         setRenderType(Entity.RenderType.SPRITE); // Default to SPRITE RenderType (logically correct)
         textureErrorHandling(fileName);
     }

    protected void textureErrorHandling(String fileName) {
    	try {
        	setTexture(new Texture(Gdx.files.internal(fileName)));
        } catch (Exception e) {
        	e.printStackTrace();
        	
        	String errorMessage = "Failed to load texture: " + fileName + ", a default texture will be loaded";
        	System.out.println(errorMessage);
        	
        	setTexture(new Texture(Gdx.files.internal("badlogic.jpg")));
        }
    }
    
    // Setter to change the RenderType
    protected void setRenderType(Entity.RenderType renderType) {
        this.renderType = renderType;
    }
    
    // Getter to retrieve RenderType (to be used in rendering of objects)
    protected RenderType getRenderType() {
       return renderType;
    }

    protected void update(float deltaTime) {
        // Subclasses to take over
    }

    protected void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(getTexture(), getxCords(), getyCords(), getWidth(), getHeight());
        batch.end();
    }

    protected void render(ShapeRenderer shape) {
    	shape.begin(ShapeRenderer.ShapeType.Filled); // Start drawing lines
        shape.setColor(getColor()); // Set the color for rendering
        shape.rect(getxCords(),getyCords(),getWidth(),getHeight());
        shape.end();
    }
    
    // Getter & Setter for Texture
    
    protected Texture getTexture() {
    	return objectTexture;
    }
    
    void setTexture(Texture newTexture) {
    	objectTexture = newTexture;
    }
    
    // Getter & Setters for (x,y) coordinates

    protected float getxCords() {
        return xCords;
    }

    void setxCords(float x) {
        xCords = x;
    }

    protected float getyCords() {
        return yCords;
    }

    void setyCords(float y) {
        yCords = y;
    }

    // Getter & Setters for Speed

    protected float getSpeed() {
        return speed;
    }
    
    void setSpeed(float newSpeed) {
        speed = newSpeed;
    }
    
    // Getter & Setter to retrieve Entity's Width

    protected float getWidth() {
        return width;
    }

    protected void setWidth(float width) {
        this.width = width;
    }
    
    // Getter & Setter to retrieve Entity's Height
    
    protected float getHeight() {
        return height;
    }

    protected void setHeight(float height) {
        this.height = height;
    }
    
    
    // Getter & Setter for Entity Type (for identification and checking)
    protected EntityType getEntityType() {
        return entityType;
    }

    protected void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    // Getter & Setters for Entity State (to be used for game implementation)

    protected EntityState getState() {
        return entityState;
    }

    void setState(EntityState state) {
        this.entityState = state;
    }

    // Getter & Setters for isAI, to be used for differentiating whether object is an AI or not

    protected boolean getisAI() {
        return isAI;
    }
    
    void setisAI(boolean newisAI) {
        isAI = newisAI;
    }
    
    // Movement Method (handled by subclasses own implementation and checks)
    protected void movement() {
    	
    }


    protected Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
    }
    
    // Getter to retrieve whether entity is removed
    protected boolean getisRemoved() {
    	return isRemoved;
    }
    
    protected void setisRemoved(boolean removed) {
    	isRemoved = removed;
    }
}