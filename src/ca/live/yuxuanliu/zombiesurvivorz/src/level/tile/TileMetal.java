package ca.live.yuxuanliu.zombiesurvivorz.src.level.tile;

import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;

//Class for the metal tile which extends Tile
public class TileMetal extends Tile {

	public TileMetal(Sprite sprite) {
		super(sprite);
	}

	// Overriding render method to render the tile
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}
