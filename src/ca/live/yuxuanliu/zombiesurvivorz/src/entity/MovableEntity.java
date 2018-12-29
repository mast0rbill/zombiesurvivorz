package ca.live.yuxuanliu.zombiesurvivorz.src.entity;

import java.awt.Rectangle;

import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;

public abstract class MovableEntity extends Entity {

	// Sprite for the mob
	protected Sprite sprite;

	// Direction for the mob
	public int dir = 0;

	// If the mob is moving
	protected boolean moving = false;

	public Rectangle bounds;

	public boolean collided = false;

	// Method for moving
	public void move(int xa, int ya) {
		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		if (ya > 0)
			dir = 2;
		if (ya < 0)
			dir = 0;
		// if its not colliding with a block
		if (collision((int) xa, (int) ya)) {
			collided = true;
		}
		x = x + xa;
		y = y + ya;
	}

	// Updating method
	public void update() {
	}

	// rendering method
	public void render() {
	}

	public boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int i = 0; i < 4; i++) {
			int xt = (int) (((x + xa) + (i % 2 * 2 - 1) * 4) / 16);
			int yt = (int) (((y + ya) + (i / 2 * 2 - 1) * 4) / 16);
			if (level.getTile(xt, yt).solid())
				solid = true;
		}
		return solid;
	}

}