/*

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Entity.Entity.EntityState;
import com.mygdx.game.Entity.Entity.EntityType;

public class Rectangle extends Entity {

	private ShapeRenderer Rectangle;
	

    public Rectangle(String color, float xCords, float yCords, float speed,
					 EntityState state, boolean isAI, float width, float height, EntityType entityType) {
		super(color, xCords, yCords, speed, state, isAI, width, height, entityType);

	}

	
	protected void render(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Line);
		shape.setColor(color);
		
		shape.rect(xCords, yCords, width, height);
		
		shape.end();
	}
	
	protected Color getColor() {
		return color;
	}
	
	void setColor(String rectColor) {
		color = Color.valueOf(rectColor);;
	}

	@Override
	public void AIControlledMovement() {
		// Do nothing
	}

	@Override
	public void userControlledMovement() {
		// Do nothing
	}
	
}

*/
