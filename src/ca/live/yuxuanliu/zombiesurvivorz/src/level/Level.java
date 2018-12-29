package ca.live.yuxuanliu.zombiesurvivorz.src.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ca.live.yuxuanliu.zombiesurvivorz.src.entity.Entity;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.level.tile.Tile;

//Class that manages which tile that needs to be rendered
public class Level {

	// Width and height for level
	protected int WIDTH, HEIGHT;
	protected int[] tilesInt;
	// Tiles of the level
	protected int[] tiles;
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Integer> spawnX = new ArrayList<Integer>();
	public List<Integer> spawnY = new ArrayList<Integer>();

	// Constructor that generates a random level
	public Level(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		tilesInt = new int[WIDTH * HEIGHT];
		generateLevel();
	}

	// Constructor to load level from file
	public Level(String path) {
		loadLevelFromFile(path);
		generateLevel();
	}

	// Method for generating a random level
	protected void generateLevel() {
	}

	// Method to load level from a path
	protected void loadLevelFromFile(String path) {
		try {
			BufferedImage image = ImageIO.read(LevelSpawn.class
					.getResource(path));
			// Width and height of the image
			int w = WIDTH = image.getWidth();
			int h = HEIGHT = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, WIDTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method for updating level/entities in the level
	public void update() {
	}

	// Method for managing System time
	@SuppressWarnings("unused")
	private void time() {
	}

	// Method for rendering level
	public void render(double xScroll, double yScroll, Screen screen) {
		// Setting offset
		screen.setOffset((int) xScroll, (int) yScroll);
		// Need to tell the computer which tiles need to be rendered
		// Need to find the corners so we can do it
		// >> 4 is same as / 16 and 16 is size of tiles so we can deal with the
		// tiles not the pixels
		double x0 = xScroll / 16;
		double x1 = (xScroll + screen.WIDTH + 16) / 16;
		double y0 = yScroll / 16;
		double y1 = (yScroll + screen.HEIGHT + 16) / 16;

		// Getting the tiles on the screen
		for (double y = y0; y < y1; y++) {
			for (double x = x0; x < x1; x++) {
				// Rendering the tile onto the screen
				getTile((int) x, (int) y).render((int) x, (int) y, screen);
			}
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
		e.init(this);
	}

	public void addEntity(Entity e, Level level) {
		entities.add(e);
		e.init(level);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	// Metal = 0xFFD2D2D2
	// Wall = 0xFFADADAD
	// Zombie spawn = 0xFF8C6239
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT)
			return Tile.voidTile;
		if (tiles[x + y * WIDTH] == 0xFFD2D2D2)
			return Tile.metalTile;
		if (tiles[x + y * WIDTH] == 0xFFADADAD)
			return Tile.wallTile;
		if(tiles[x + y * WIDTH] == 0xFFFF0008)
			return Tile.playerSpawnTile;
		if(tiles[x + y * WIDTH] == 0xFFFFD800)
			return Tile.mysteryBoxTile;
		if (tiles[x + y * WIDTH] == 0xFF8C6239) {
			spawnX.add(x);
			spawnY.add(y);
			return Tile.zombieSpawnTile;
		}
		return Tile.voidTile;
	}

}
