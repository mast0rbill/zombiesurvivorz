package ca.live.yuxuanliu.zombiesurvivorz.src.level.tile;

import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;

public class TileMysteryBox extends Tile{

	public TileMysteryBox(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
	
	public boolean isMysteryBox(){
		return true;
	}

}
