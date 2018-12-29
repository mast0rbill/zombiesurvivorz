package ca.live.yuxuanliu.zombiesurvivorz.src.graphics;

import ca.live.yuxuanliu.zombiesurvivorz.src.level.tile.Tile;

//This class displays and controls/renders the pixels
public class Screen {

	// Map size
	public final int MAP_SIZE = 64;

	// For bitwise operations
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	// Width and height variables
	public int WIDTH, HEIGHT;

	// Array that contains pixel data
	public int[] pixels;

	// Array that will contain tiles in the game
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	// store offset values
	public int xOffset, yOffset;

	// Constructor
	public Screen(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		// Initializing pixels to have an amount of WIDTH * HEIGHT so we can
		// have the amount of pixels in the screen
		pixels = new int[WIDTH * HEIGHT];
	}

	// Screen needs to be cleared every time it renders so no trail is left
	// behind
	public void clear() {
		// Setting every pixel to black/0
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	// Method to render tiles with parameters of xposition and yposition and the
	// tile to render
	public void renderTile(int xp, int yp, Tile tile) {
		xp = xp - xOffset;
		yp = yp - yOffset;
		// Nested for loop for rendering tiles and getting the position
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			// Absolute position(ya, xa), relative to the world
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				// If tile gets off the screen then break out of the loop
				if (xa < -tile.sprite.SIZE || xa >= WIDTH || ya < 0
						|| ya >= HEIGHT)
					break;
				if (xa < 0)
					xa = 0;
				// Using absolute values for the tiles but for each sprite just
				// use the normal values
				pixels[(int) (xa + ya * WIDTH)] = tile.sprite.pixels[x + y
						* tile.sprite.SIZE];
			}
		}
	}

	// Method to render the player
	public void renderMob(int xp, int yp, Sprite sprite, int flip) {
		xp = xp - xOffset;
		yp = yp - yOffset;
		// Nested for loop for rendering tiles and getting the position
		for (int y = 0; y < sprite.SIZE; y++) {
			// Absolute position(ya, xa), relative to the world
			int ya = y + yp;
			// Flipping
			int ys = y;
			if (flip == 2 || flip == 3) {
				ys = (sprite.SIZE - 1) - y;
			}
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) {
					xs = (sprite.SIZE - 1) - x;
				}
				// If tile gets off the screen then break out of the loop
				if (xa < -sprite.SIZE || xa >= WIDTH || ya < 0 || ya >= HEIGHT)
					break;
				if (xa < 0)
					xa = 0;
				// Color
				int col = sprite.pixels[(int) (xs + ys * sprite.SIZE)];
				if (col != 0xFFFF00FF) {
					pixels[xa + ya * WIDTH] = col;
				}
			}
		}
	}

	// Method to set the offset
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
