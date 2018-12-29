package ca.live.yuxuanliu.zombiesurvivorz.src;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ca.live.yuxuanliu.zombiesurvivorz.src.entity.Entity;
import ca.live.yuxuanliu.zombiesurvivorz.src.entity.EntityBullet;
import ca.live.yuxuanliu.zombiesurvivorz.src.entity.EntityPlayerProtection;
import ca.live.yuxuanliu.zombiesurvivorz.src.entity.MobPlayer;
import ca.live.yuxuanliu.zombiesurvivorz.src.entity.MobZombie;
import ca.live.yuxuanliu.zombiesurvivorz.src.entity.MobZombieFat;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.gun.Gun;
import ca.live.yuxuanliu.zombiesurvivorz.src.gun.GunAK47;
import ca.live.yuxuanliu.zombiesurvivorz.src.gun.GunBarrett50;
import ca.live.yuxuanliu.zombiesurvivorz.src.gun.GunDragunov;
import ca.live.yuxuanliu.zombiesurvivorz.src.gun.GunG36C;
import ca.live.yuxuanliu.zombiesurvivorz.src.gun.GunM1911;
import ca.live.yuxuanliu.zombiesurvivorz.src.gun.GunM4A1;
import ca.live.yuxuanliu.zombiesurvivorz.src.gun.GunMG42;
import ca.live.yuxuanliu.zombiesurvivorz.src.gun.GunMP40;
import ca.live.yuxuanliu.zombiesurvivorz.src.gun.GunSTG44;
import ca.live.yuxuanliu.zombiesurvivorz.src.input.Keyboard;
import ca.live.yuxuanliu.zombiesurvivorz.src.level.Level;
import ca.live.yuxuanliu.zombiesurvivorz.src.level.LevelSpawn;
import ca.live.yuxuanliu.zombiesurvivorz.src.screen.DeathScreen;
import ca.live.yuxuanliu.zombiesurvivorz.src.screen.ErrorScreen;

//Implement Runnable because thread needs Runnable parameter so it can be attached to this class
//Extending Canvas so Frame can include this class as a container, also controlling input values
public class Display extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	// Points of the player
	public static int points = 0;

	// Variables controlling size of game
	// Scale so it will render 3 time less stuff but bigger screen
	public static int WIDTH = 300, HEIGHT = 168, SCALE = 3;

	public static int playerHealth = 10;

	long oldSpawnTime = System.currentTimeMillis();

	private String fps = "";
	private String debug = "";

	// Thread = a process inside a process, so we can do multiple things
	// simultaneously
	// This will be the thread of the actual game
	private Thread thread;

	// Indicator that program is running or not
	private boolean isRunning = false;

	// Keyboard class for keyboard listening
	private static Keyboard key;

	// Instance of level so we can use methods and such
	private static Level level = new LevelSpawn();

	// Instance of the player
	public static MobPlayer player;

	boolean shot = false;

	// BufferedImage = image with accessible buffer, not using SCALE so we will
	// get better performance
	// Using an int array so we can manipulate the pixels in the BufferedImage
	// Raster = data structure that represents rectangular array of pixels
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();

	// Making an object of Screen
	public static Screen screen;
	public String ammo;

	public List<EntityBullet> bullets = new ArrayList<EntityBullet>();
	public Gun gun, gunPrimary, gunSecondary;
	
	public Map<String, Integer> gunPrimaryClip = new HashMap<String, Integer>();
	public Map<String, Integer> gunPrimaryAmmo = new HashMap<String, Integer>();
	
	public Map<String, Integer> gunSecondaryClip = new HashMap<String, Integer>();
	public Map<String, Integer> gunSecondaryAmmo = new HashMap<String, Integer>();
	
	boolean reload = false;
	
	EntityPlayerProtection playerProtection;

	// Constructor runs when new instance of this object is created
	public Display() {
		// Creating a dimension for the size of the frame
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		// Initializing objects
		screen = new Screen(WIDTH, HEIGHT);
		key = new Keyboard();
		gunPrimary = new GunM4A1();
		gunSecondary = new GunM1911();
		gun = gunPrimary;
		gunPrimaryClip.put(gunPrimary.name, gunPrimary.clipSize);
		gunPrimaryAmmo.put(gunPrimary.name, gunPrimary.maxClips);
		gunSecondaryClip.put(gunSecondary.name, gunSecondary.clipSize);
		gunSecondaryAmmo.put(gunSecondary.name, gunSecondary.maxClips);
		player = new MobPlayer(12, 9, key, level);
		playerProtection = new EntityPlayerProtection(player);
		ammo = "Ammo: " + gunPrimaryClip.get(gun.name) + " | " + gunPrimaryAmmo.get(gun.name);
		// addKeyListener = method in one of the classes we're extending that
		// adds the key listener
		addKeyListener(key);
	}

	// Synchronized ensures there is no overlap of threads since we have
	// multiple threads
	// Starting the thread
	public synchronized void start() {
		// Setting isRunning to true, starting the game
		isRunning = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	// Stopping the thread
	public synchronized void stop() {
		// Setting isRunning to false, stopping the game
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// When thread starts, run starts
	public void run() {
		// Timer
		long timer = System.currentTimeMillis();
		// Variable to store time, storing when the game just started
		long lastTime = System.nanoTime();
		// Conversion to nanoseconds, divide by the highest FPS
		final double nanoSeconds = 1000000000.0 / 60.0;
		double delta = 0;
		// this variable can count how many frames we can render per second
		int frames = 0;
		// count how many updates per seconds
		int updates = 0;
		// Making computer focuse on the canvas
		requestFocus();
		// While the game is running, do the things in the while loop
		while (isRunning == true) {
			// Storing the time now since it is a loop so it does this every
			// time it loops
			long now = System.nanoTime();
			// Adding to delta the time it took and dividing by nanoSeconds
			delta += (now - lastTime) / nanoSeconds;
			// Setting lastTime to now so we can find out the difference again.
			lastTime = now;
			// While delta is bigger or equal to 1, which will only happen 60
			// times a second
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			// Render can go as fast as your computer can do it and will handle
			// rendering
			render();
			frames++;
			// If the current time - timer is greater than 1 second
			if (System.currentTimeMillis() - timer > 1000) {
				// Add 1 second to timer
				timer += 1000;
				fps = "FPS: " + frames + ", Updates: " + updates;
				// Resetting variables
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	int bulletCounter = 0;

	public void errorScreen(String error) {
		new ErrorScreen(error);
	}

	long sysTime = System.currentTimeMillis();

	public boolean coolDown() {
		while (System.currentTimeMillis() - sysTime < gun.cooldown) {
			return false;
		}
		if (System.currentTimeMillis() - sysTime >= gun.cooldown)
			sysTime = System.currentTimeMillis();
		return true;
	}

	public void updateBullets() {
		if (player.shoot) {
			if (!shot)
				shot = true;
			if (coolDown()){
				if(gun.equals(gunPrimary)){
					if(gunPrimaryClip.get(gun.name) > 0){
						bullets.add(new EntityBullet(player, level));
						gunPrimaryClip.put(gun.name, gunPrimaryClip.get(gun.name) - 1);
					}
				} else if(gun.equals(gunSecondary)){
					if(gunSecondaryClip.get(gun.name) > 0){
						bullets.add(new EntityBullet(player, level));
						gunSecondaryClip.put(gun.name, gunSecondaryClip.get(gun.name) - 1);
					}
				} else errorScreen("Unknown Gun");
			}
		}
		for (int w = 0; w < bullets.size(); w++) {
			if (bullets.get(w).exist) {
				EntityBullet bullet = bullets.get(w);
				if (bullet == null) {
					bullet = new EntityBullet(player, level);
				}
				if (shot)
					bullet.update();
			} else {
				bullets.get(w).exist = false;
				bullets.remove(w);
				;
				break;
			}
		}
	}

	long sysTime2 = System.currentTimeMillis();

	public boolean coolDownSwitchGun() {
		while (System.currentTimeMillis() - sysTime2 < 300) {
			return false;
		}
		if (System.currentTimeMillis() - sysTime2 >= 300)
			sysTime2 = System.currentTimeMillis();
		return true;
	}
	
	long sysTime4 = System.currentTimeMillis();
	public boolean coolDownMysteryBox(){
		while(System.currentTimeMillis() - sysTime4 < 1000){
			return false;
		}
		if(System.currentTimeMillis() - sysTime4 >= 1000)
			sysTime4 = System.currentTimeMillis();
		return true;
	}

	// Can go 60 FPS, will do all the logic
	public void update() {
		// Update method in the key, checking if a key is pressed
		key.update();
		// Update method in the player
		player.update();
		playerProtection.update();
		if(player.input.reload){
			if(gun.equals(gunPrimary)){
				if(gunPrimaryClip.get(gun.name) <= 0 && gunPrimaryAmmo.get(gun.name) > 0){
					reload = true;
					gunPrimaryClip.put(gun.name, gunPrimary.clipSize);
					gunPrimaryAmmo.put(gun.name, gunPrimaryAmmo.get(gun.name) - 1);
					reload = false;
				}
			} else if(gun.equals(gunSecondary)){
				if(gunSecondaryClip.get(gun.name) <= 0 && gunSecondaryAmmo.get(gun.name) > 0){
					reload = true;
					gunSecondaryClip.put(gun.name, gunSecondary.clipSize);
					gunSecondaryAmmo.put(gun.name, gunSecondaryAmmo.get(gun.name) - 1);
					reload = false;
				}
			}
		}
		level.addEntity(player, level);
		spawnMobs();
		for (int i = 0; i < level.entities.size(); i++) {
			if (level.entities.get(i) == null)
				break;
			if (level.entities.get(i) instanceof MobZombie) {
				MobZombie zombie = (MobZombie) level.entities.get(i);
				if (!zombie.dead)
					zombie.update();
				else
					level.entities.remove(i);
			}
		}
		
		if(player.mysteryBox && player.input.use && coolDownMysteryBox()){
			Random rand = new Random();
			int n = rand.nextInt(7);
			Gun newGun = new GunM1911();
			if(n == 0) newGun = new GunG36C();
			else if(n == 1) newGun = new GunAK47();
			else if(n == 2) newGun = new GunBarrett50();
			else if(n == 3) newGun = new GunDragunov();
			else if(n == 4) newGun = new GunMP40();
			else if(n == 5) newGun = new GunSTG44();
			else if(n == 6) newGun = new GunMG42();
			if(gun.equals(gunPrimary)){
				gunPrimary = newGun;
				gun = gunPrimary;
				gunPrimaryClip.put(gun.name, gun.clipSize);
				gunPrimaryAmmo.put(gun.name, gun.maxClips);
			} else if(gun.equals(gunSecondary)){
				gunSecondary = newGun;
				gun = gunSecondary;
				gunSecondaryClip.put(gun.name, gun.clipSize);
				gunSecondaryAmmo.put(gun.name, gun.maxClips);
			}
			points -= 950;
		}
		
		if (player.input.switchWeapon) {
			if (gun.equals(gunPrimary) && coolDownSwitchGun())
				gun = gunSecondary;
			else if (gun.equals(gunSecondary) && coolDownSwitchGun())
				gun = gunPrimary;

		}
		if(gun.equals(gunPrimary)) 
			ammo = "Ammo: " + gunPrimaryClip.get(gun.name) + " | " + gunPrimaryAmmo.get(gun.name);
		else if(gun.equals(gunSecondary))
			ammo = "Ammo: " + gunSecondaryClip.get(gun.name) + " | " + gunSecondaryAmmo.get(gun.name);
		else errorScreen("Unknown Gun");
		playerHealth = player.health;
		updateBullets();
		checkCollisions();
		if (player.dead) {
			die();
		}
	}

	public void die(){
		new DeathScreen(points);
		isRunning = false;
	}
	
	int spawnCounter = 0;

	public void spawnMobs() {
		try {
			Random rand = new Random();
			Random rand2 = new Random();
			if (spawnCounter >= 0)
				spawnCounter = rand.nextInt(level.spawnX.size());
			long newSpawnTime = System.currentTimeMillis();
			if (newSpawnTime - oldSpawnTime >= 1300 && rand.nextInt(10) <= 5
					&& spawnCounter >= 0) {
				if(rand2.nextInt(10) > 0){
					level.entities.add(new MobZombie(
							level.spawnX.get(spawnCounter), level.spawnY
									.get(spawnCounter), player, level));
				} else {
					level.entities.add(new MobZombieFat(
							level.spawnX.get(spawnCounter), level.spawnY
									.get(spawnCounter), player, level));
				}
				oldSpawnTime = System.currentTimeMillis();
			}
		} catch (IllegalArgumentException e) {
			return;
		}
	}

	long sysTime3 = System.currentTimeMillis();

	public boolean coolDownPlayer() {
		while (System.currentTimeMillis() - sysTime3 < player.invincibilityTime) {
			return false;
		}
		if (System.currentTimeMillis() - sysTime3 >= player.invincibilityTime)
			sysTime3 = System.currentTimeMillis();
		return true;
	}
	
	boolean actualHit = true;
	
	public void checkCollisions() {
		for (int w = 0; w < bullets.size(); w++) {
			try {
				if (bullets.get(w).exist) {
					EntityBullet bullet = bullets.get(w);
					if (bullet == null) {
						bullet = new EntityBullet(player, level);
					}
					for (int i = 0; i < level.entities.size(); i++) {
						if (level.entities.get(i) == null)
							break;
						if (level.entities.get(i) instanceof MobZombie) {
							MobZombie zombie = (MobZombie) level.entities
									.get(i);
							if (bullet.bounds.intersects(zombie.bounds) && shot) {
								zombie.health -= gun.damage;
								bullet.exist = false;
								bullets.remove(bullet);
							}
						}
					}
				}
			} catch (IndexOutOfBoundsException e) {
				errorScreen("IndexOutOfBoundsException");
				break;
			}
		}
		for(Entity ent : level.entities){
			try{
				if(ent instanceof MobZombie){
					MobZombie zombie = (MobZombie) ent;
					if(zombie.bounds.intersects(player.bounds) && !player.invincible && actualHit){
						player.health--;
						player.invincible = true;
						actualHit = false;
						break;
					}
					if(player.invincible && coolDownPlayer()){
						actualHit = true;
						player.invincible = false;
					}
				}
			} catch (Exception e){
				errorScreen("Unknown Exception");
			}
		}
		for(Entity ent : level.entities){
			try{
				if(ent instanceof MobZombie){
					MobZombie zombie = (MobZombie) ent;
					if(zombie.bounds.intersects(playerProtection.bounds)){
						zombie.dead = true;
						points += 1;
					}
				}
			} catch (Exception e){
				errorScreen("Unknown Exception");
			}
		}
	}

	public void renderBullets() {
		for (int w = 0; w < bullets.size(); w++) {
			try {
				if (bullets.get(w).exist) {
					EntityBullet bullet = bullets.get(w);
					if (bullet == null) {
						bullet = new EntityBullet(player, level);
					}
					if (shot) {
						bullet.render(screen);
					}
				} else {
					bullets.get(w).exist = false;
					bullets.remove(w);
					break;
				}
			} catch (IndexOutOfBoundsException e) {
				errorScreen("IndexOutOfBoundsException");
				break;
			}
		}
	}

	// Can go unlimited FPS, will render the screen
	public void render() {
		// Buffer = temporary storage, so we can put calculations on a 'ready
		// list' so we can execute it later
		BufferStrategy bs = getBufferStrategy();
		// Creating 1 BufferStrategy, not every time it renders
		if (bs == null) {
			try {
				createBufferStrategy(3);
			} catch (IllegalStateException e) {
				return;
			}
			return;
		}
		// Clearing the screen so no trail is left
		screen.clear();
		// variables for player location
		double xScroll = player.x - screen.WIDTH / 2;
		double yScroll = player.y - screen.HEIGHT / 2;
		// Rendering by the method in screen
		level.render(xScroll, yScroll, screen);
		// Rendering the player
		player.render(screen);
		if(player.invincible)
			playerProtection.render(screen);
		for (int i = 0; i < level.entities.size(); i++) {
			if (level.entities.get(i) == null)
				break;
			if (level.entities.get(i) instanceof MobZombie) {
				MobZombie zombie = (MobZombie) level.entities.get(i);
				if (!zombie.dead)
					zombie.render(screen);
				else
					level.entities.remove(i);
			}
		}
		renderBullets();
		// Setting this.pixels to screen.pixels
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = (int) screen.pixels[i];
		}
		debug = "Debug: ";
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.BOLD, 12));
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawString(fps, 3, 10);
		g.drawString(debug, 3, 22);
		g.drawString(
				"X: "
						+ ((String) (String.valueOf(player.x / 16))), 3, 34);
		g.drawString(
				"Y: "
						+ ((String) (String.valueOf(player.y / 16))), 3, 46);
		g.setFont(new Font("SansSerif", Font.BOLD, 24));
		g.drawString("Weapon: " + gun.name, 350, 30);
		g.drawString(ammo, 350, 60);
		g.setColor(new Color(255, 213, 46));
		g.setFont(new Font("SansSerif", Font.BOLD, 36));
		if(player.mysteryBox){
			g.drawString("Press F to Use Mystery Box", 250, 200);
			g.drawString("(950 points)", 250, 250);
		}
		if(player.mysteryBox && player.input.use && points < 950)	
			g.drawString("Not enough points! (950 points)", 250, 200);
		else if(player.mysteryBox && player.input.use && !coolDownMysteryBox())
			g.drawString("In Cooldown!", 250, 200);
		else if(player.mysteryBox && player.input.use && points < 950 && !coolDownMysteryBox()){
			g.drawString("Not enough points! (950 points)", 250, 200);
			g.drawString("In Cooldown!", 250, 250);
		}
		if((gun.equals(gunPrimary) && gunPrimaryAmmo.get(gun.name) > 0 && gunPrimaryClip.get(gun.name) <= 0) && !reload)
			g.drawString("Press R to Reload", 300, 200);
		else if((gun.equals(gunSecondary) && gunSecondaryAmmo.get(gun.name) > 0 && gunSecondaryClip.get(gun.name) <= 0) && !reload)
			g.drawString("Press R to Reload", 300, 200);
		if(gun.equals(gunPrimary) && gunPrimaryAmmo.get(gun.name) <= 0 && gunPrimaryClip.get(gun.name) <= 0)
			g.drawString("Out of Ammo", 300, 200);
		else if(gun.equals(gunSecondary) && gunSecondaryAmmo.get(gun.name) <= 0 && gunSecondaryClip.get(gun.name) <= 0)
			g.drawString("Out of Ammo", 300, 200);
		g.setColor(Color.RED);
		g.setFont(new Font("SansSerif", Font.ITALIC, 48));
		g.drawString("Points: " + points, 50, HEIGHT * SCALE - 50);
		g.drawString("Health: " + playerHealth, 50, HEIGHT * SCALE - 50 - 50);
		// Disposes the graphics, releasing RAM
		g.dispose();
		// VERY IMPORTANT: make the next available buffer 'visible' by clearing
		// it
		bs.show();
	}

}