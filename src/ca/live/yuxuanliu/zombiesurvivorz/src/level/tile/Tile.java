package ca.live.yuxuanliu.zombiesurvivorz.src.level.tile;

import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;

//Base class to contain tiles
public class Tile {

	// Objects for all the tiles
	public static Tile metalTile = new TileMetal(Sprite.metalSprite);
	public static Tile voidTile = new TileVoid(Sprite.voidSprite);
	public static Tile wallTile = new TileWall(Sprite.wallSprite);
	public static Tile playerSpawnTile = new TilePlayerSpawn(Sprite.playerSpawnSprite);
	public static Tile zombieSpawnTile = new TileZombieSpawn(
			Sprite.zombieSpawnSprite);
	public static Tile mysteryBoxTile = new TileMysteryBox(Sprite.mysteryBoxSprite);

	// x and y coordinates
	public int x, y;

	// sprite for the tile
	public Sprite sprite;

	// Constructor to make a tile
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	// Method to render
	public void render(int x, int y, Screen screen) {
	}

	// Boolean is solid or not
	public boolean solid() {
		return false;
	}
	
	public boolean isMysteryBox(){
		return false;
	}

}
