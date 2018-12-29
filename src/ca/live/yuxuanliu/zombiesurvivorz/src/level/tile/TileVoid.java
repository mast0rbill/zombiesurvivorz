package ca.live.yuxuanliu.zombiesurvivorz.src.level.tile;

import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;

public class TileVoid extends Tile {

	public TileVoid(Sprite sprite) {
		super(sprite);
	}

	// Overriding render method to render the tile
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}
