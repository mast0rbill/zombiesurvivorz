package ca.live.yuxuanliu.zombiesurvivorz.src.entity;

import java.util.Random;

import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.level.Level;

//Class to handle entities
public abstract class Entity {

	// x and y
	public int x, y;

	// if the entity is removed from the level or not
	private boolean removed = false;

	// the level it's in
	protected Level level;

	// Random for AIs and such
	protected final Random random = new Random();

	// Method for updating
	public void update() {
	}

	// Method for rendering
	public void render(Screen screen) {
	}

	// Method for removing entity
	public void remove() {
		level.removeEntity(this);
		removed = true;
	}

	// Method for getting is removed
	public boolean isRemoved() {
		return removed;
	}

	public final void init(Level level) {
		this.level = level;
	}

}
