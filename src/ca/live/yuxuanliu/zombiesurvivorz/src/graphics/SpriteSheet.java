package ca.live.yuxuanliu.zombiesurvivorz.src.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//This class will be in charge of any sprite we have and casheing them to a variable
public class SpriteSheet {

	// This string will contain the path to the spritesheet
	private String path;

	// Size of spritesheet
	public final int SIZE;

	// Every pixel of the spritesheet
	public int[] pixels;

	// new object of spritesheet that other classes can use
	public static SpriteSheet textures = new SpriteSheet(
			"/textures/textures.png", 256);

	// Setting the variables to the parameters
	public SpriteSheet(String p, int size) {
		this.path = p;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	// Method for loading spritesheet
	private void load() {
		// new bufferedImage that loads the image from path
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class
					.getResource(path));
			// Need to deal with image in pixels
			int width = image.getWidth();
			int height = image.getHeight();
			// Translates the image into the pixels[]
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
