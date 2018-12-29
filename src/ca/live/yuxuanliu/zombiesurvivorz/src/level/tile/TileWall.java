package ca.live.yuxuanliu.zombiesurvivorz.src.level.tile;

import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;

public class TileWall extends Tile {

	public TileWall(Sprite sprite) {
		super(sprite);
	}

	// Overriding render method to render the tile
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	// Overriding isSolid method for solid tile
	public boolean solid() {
		return true;
	}

}
