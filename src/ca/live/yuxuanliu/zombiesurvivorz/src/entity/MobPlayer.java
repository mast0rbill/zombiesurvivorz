package ca.live.yuxuanliu.zombiesurvivorz.src.entity;

import java.awt.Rectangle;

import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;
import ca.live.yuxuanliu.zombiesurvivorz.src.input.Keyboard;
import ca.live.yuxuanliu.zombiesurvivorz.src.level.Level;

public class MobPlayer extends Mob {

	public Keyboard input;
	// Animation variable
	private int animate = 0;
	// Variable for walking
	private boolean walking = false;
	public boolean shoot = false;
	int dx = 0, dy = 0;
	public int invincibilityTime = 5000;
	public boolean invincible = false;
	public boolean mysteryBox = false;

	// Create player in default location
	public MobPlayer(Keyboard input) {
		this.input = input;
		this.sprite = Sprite.playerVerticalSprite;
		bounds = new Rectangle(this.x - 16, this.y - 16, sprite.SIZE,
				sprite.SIZE);
	}

	// Create player in a set location
	public MobPlayer(int x, int y, Keyboard input, Level level) {
		this.level = level;
		// x and y from entity
		this.x = x * 15;
		this.y = y * 15;
		this.input = input;
		this.dir = 0;
		this.sprite = Sprite.playerVerticalSprite;
		health = 10;
		bounds = new Rectangle(x - 16, y - 16, sprite.SIZE,
				sprite.SIZE);
	}

	// update method
	public void update() {
		if(health <= 0) dead = true;
		bounds.x = (int) x - 16;
		bounds.y = (int) y - 16;
		// dynamic x and y for the player
		if (animate < 7500)
			animate++;
		else
			animate = 0;
		if (input.up2)
			setDY(-1);
		else if (input.down2)
			setDY(1);
		else
			setDY(0);
		if (input.left2)
			setDX(-1);
		else if (input.right2)
			setDX(1);
		else
			setDX(0);
		if ((input.up2) && getDX() != 0) {
			setDY(-1);
			setDX(0);
		} else if ((input.down2) && getDX() != 0) {
			setDY(1);
			setDX(0);
		}
		if ((input.right2) && getDY() != 0) {
			setDX(1);
			setDY(0);
		} else if ((input.left2) && getDY() != 0) {
			setDX(-1);
			setDY(0);
		}
		if (input.up){
			dir = 0;
			shoot = true;
		} else if(input.down){
			dir = 2;
			shoot = true;
		} else if(input.right){
			dir = 1;
			shoot = true;
		} else if(input.left){
			dir = 3;
			shoot = true;
		} else {
			shoot = false;
		}
		// Using the move method
		if (dx != 0 || dy != 0) {
			move(dx, dy);
			walking = true;
		} else {
			walking = false;
		}
		if(mysteryBox(dx, dy)){
			mysteryBox = true;
		} else {
			mysteryBox = false;
		}
	}

	// render method
	public void render(Screen screen) {
		int flip = 0;
		if (dir == 0) {
			flip = 0;
			sprite = Sprite.playerVerticalSprite;
			screen.renderMob(x - 16, y - 16, sprite, flip);
			if (walking == true) {
				if (animate % 20 > 10) {
					sprite = Sprite.playerVertical1;
					screen.renderMob(x - 16, y - 16, sprite, flip);
				} else {
					sprite = Sprite.playerVertical2;
					screen.renderMob(x - 16, y - 16, sprite, flip);
				}
			}
		}
		if (dir == 1) {
			flip = 0;
			sprite = Sprite.playerHorizontalSprite;
			screen.renderMob(x - 16, y - 16, sprite, flip);
			if (walking == true) {
				if (animate % 20 > 10) {
					sprite = Sprite.playerHorizontal1;
					screen.renderMob(x - 16, y - 16, sprite, flip);
				} else {
					sprite = Sprite.playerHorizontal2;
					screen.renderMob(x - 16, y - 16, sprite, flip);
				}
			}
		}
		if (dir == 2) {
			flip = 2;
			sprite = Sprite.playerVerticalSprite;
			screen.renderMob(x - 16, y - 16, sprite, flip);
			if (walking == true) {
				if (animate % 20 > 10) {
					sprite = Sprite.playerVertical1;
					screen.renderMob(x - 16, y - 16, sprite, flip);
				} else {
					sprite = Sprite.playerVertical2;
					screen.renderMob(x - 16, y - 16, sprite, flip);
				}
			}
		}
		if (dir == 3) {
			flip = 1;
			sprite = Sprite.playerHorizontalSprite;
			screen.renderMob(x - 16, y - 16, sprite, flip);
			if (walking == true) {
				if (animate % 20 > 10) {
					sprite = Sprite.playerHorizontal1;
					screen.renderMob(x - 16, y - 16, sprite, flip);
				} else {
					sprite = Sprite.playerHorizontal2;
					screen.renderMob(x - 16, y - 16, sprite, flip);
				}
			}
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDX() {
		return dx;
	}

	public void setDX(int dx) {
		this.dx = dx;
	}

	public int getDY() {
		return dy;
	}

	public void setDY(int dy) {
		this.dy = dy;
	}

}