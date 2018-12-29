package ca.live.yuxuanliu.zombiesurvivorz.src.graphics;

//Class that will cover the individual sprites
public class Sprite {

	// Size of the individual sprite
	public final int SIZE;

	// Location of spritesssss
	private int x, y;

	// Pixels of sprite
	public int[] pixels;

	// SpriteSheet this is in
	private SpriteSheet sheet;

	// New sprite object for the sprite that other classes can use, and this
	// will equal grass.
	public static Sprite metalSprite = new Sprite(16, 0, 0,
			SpriteSheet.textures);
	public static Sprite voidSprite = new Sprite(16, 1, 0, SpriteSheet.textures);
	public static Sprite wallSprite = new Sprite(16, 2, 0, SpriteSheet.textures);
	public static Sprite zombieSpawnSprite = new Sprite(16, 3, 0,
			SpriteSheet.textures);
	public static Sprite playerSpawnSprite = new Sprite(16, 4, 0, SpriteSheet.textures);
	public static Sprite mysteryBoxSprite = new Sprite(16, 5, 0, SpriteSheet.textures);

	public static Sprite bulletVerticalSprite = new Sprite(16, 0, 1,
			SpriteSheet.textures);
	public static Sprite bulletHorizontalSprite = new Sprite(16, 1, 1,
			SpriteSheet.textures);

	public static Sprite nullSprite32 = new Sprite(32, 0, 4,
			SpriteSheet.textures);

	public static Sprite playerVerticalSprite = new Sprite(32, 0, 5,
			SpriteSheet.textures);
	public static Sprite playerHorizontalSprite = new Sprite(32, 1, 5,
			SpriteSheet.textures);
	public static Sprite playerVertical1 = new Sprite(32, 0, 6,
			SpriteSheet.textures);
	public static Sprite playerHorizontal1 = new Sprite(32, 1, 6,
			SpriteSheet.textures);
	public static Sprite playerVertical2 = new Sprite(32, 0, 7,
			SpriteSheet.textures);
	public static Sprite playerHorizontal2 = new Sprite(32, 1, 7,
			SpriteSheet.textures);

	public static Sprite zombieVerticalSprite = new Sprite(32, 2, 5,
			SpriteSheet.textures);
	public static Sprite zombieHorizontalSprite = new Sprite(32, 3, 5,
			SpriteSheet.textures);
	public static Sprite zombieVerticalSprite1 = new Sprite(32, 2, 6,
			SpriteSheet.textures);
	public static Sprite zombieHorizontalSprite1 = new Sprite(32, 3, 6,
			SpriteSheet.textures);
	public static Sprite zombieVerticalSprite2 = new Sprite(32, 2, 7,
			SpriteSheet.textures);
	public static Sprite zombieHorizontalSprite2 = new Sprite(32, 3, 7,
			SpriteSheet.textures);
	
	public static Sprite playerProtectionSprite = new Sprite(32, 0, 1,
			SpriteSheet.textures);
	public static Sprite playerProtectionSprite1 = new Sprite(32, 0, 2,
			SpriteSheet.textures);
	
	public static Sprite zombieFatVerticalSprite = new Sprite(32, 4, 5,
			SpriteSheet.textures);
	public static Sprite zombieFatHorizontalSprite = new Sprite(32, 5, 5,
			SpriteSheet.textures);
	public static Sprite zombieFatVerticalSprite1 = new Sprite(32, 4, 6,
			SpriteSheet.textures);
	public static Sprite zombieFatHorizontalSprite1 = new Sprite(32, 5, 6,
			SpriteSheet.textures);
	public static Sprite zombieFatVerticalSprite2 = new Sprite(32, 4, 7,
			SpriteSheet.textures);
	public static Sprite zombieFatHorizontalSprite2 = new Sprite(32, 5, 7,
			SpriteSheet.textures);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		// Setting pixels to the size of the individual sprite
		pixels = new int[SIZE * SIZE];
		// Setting the coords of the sprite to x * size and same for y
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	// Method for all-1 color sprites
	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	// Method to fill the pixel array with a color
	public void setColor(int color) {
		for (int i = 0; i < SIZE; i++) {
			pixels[i] = color;
		}
	}

	// Method for extracting a single sprite out of the spritesheet
	private void load() {
		// Going thru all the pixels in the sprite
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				// Cycle through the pixels and set them to the ones in the
				// spritesheet
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y)
						* sheet.SIZE];
			}
		}
	}

}
