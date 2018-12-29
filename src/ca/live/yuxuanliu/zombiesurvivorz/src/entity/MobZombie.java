package ca.live.yuxuanliu.zombiesurvivorz.src.entity;

import java.awt.Rectangle;
import java.util.Random;

import ca.live.yuxuanliu.zombiesurvivorz.src.Display;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;
import ca.live.yuxuanliu.zombiesurvivorz.src.level.Level;

public class MobZombie extends Mob {

	public int animate = 0;
	public MobPlayer targetPlayer;
	int dy = 0;
	int dx = 0;
	public int pointsAdded = 15;
	

	public MobZombie(int x, int y, MobPlayer target, Level level) {
		this.level = level;
		this.targetPlayer = target;
		this.x = x * 16 + 8;
		this.y = y * 16 + 8;
		this.sprite = Sprite.zombieVerticalSprite;
		health = 100;
		bounds = new Rectangle((int) this.x - 16, (int) this.y - 16, sprite.SIZE,
				sprite.SIZE);
	}
	
	public void update() {
		if (health <= 0)
			dead = true;
		if (!dead) {
			bounds.x = (int) x - 16;
			bounds.y = (int) y - 16;
		if (animate < 7500)
			animate++;
		else
			animate = 0;
		
		
		
		doAI();
		if(collided)
			checkCollisions();
		updateMove();
		
		
		
		} else {
			Display.points += pointsAdded;
		}
	}
	public void doAI() {
		if(new Random().nextInt(10) > 5 && (
			(targetPlayer.x - x != 0 && targetPlayer.y - y == 0) 
		||
			(targetPlayer.y - y != 0 && targetPlayer.x - x != 0)
		)){
			if(targetPlayer.x > x) dx = 1;
			else if(targetPlayer.x < x) dx = -1;
			else dx = 0;
			if(targetPlayer.y > y) dy = 1;
			else if(targetPlayer.y < y) dy = -1;
			else dy = 0;
		} else if(new Random().nextInt(10) > 8 && 
			(targetPlayer.x - x != 0 && targetPlayer.y - y != 0)
		){
			if(targetPlayer.x > x) dx = 1;
			else if(targetPlayer.x < x) dx = -1;
			if(targetPlayer.y > y) dy = 1;
			else if(targetPlayer.y < y) dy = -1;
		}
		else {
			dx = 0;
			dy = 0;
		}	
	}
	
	public void updateMove(){
		if(dx != 0 || dy != 0){
			moving = true;
			move(dx, dy);
		} else {
			moving = false;
		}
	}
	
	public void checkCollisions(){
		int oldX = x, oldY = y;
		if(dir == 0){
			dx = 1;
		} else if(dir == 2){
			dx = 1;
		} else if(dir == 1){
			dy = 1;
		} else if(dir == 3){
			dy = -1;
		}
		if(dx == 1 && oldX == x){
			dx = -1;
		} else if(dx == -1 && oldX == x){
			dx = 1;
		} else if(dy == 1 && oldY == y){
			dy = -1;
		} else if(dy == -1 && oldY == y){
			dy = 1;
		}
		collided = false;
	}
	
	public void render(Screen screen) {
		if (!dead) {
			int flip = 0;
			if (dir == 0) {
				flip = 0;
				sprite = Sprite.zombieVerticalSprite;
				screen.renderMob(x - 16, y - 16, sprite, flip);
				if (moving == true) {
					if (animate % 20 > 10) {
						sprite = Sprite.zombieVerticalSprite1;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					} else {
						sprite = Sprite.zombieVerticalSprite2;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					}
				}
			}
			if (dir == 1) {
				flip = 0;
				sprite = Sprite.zombieHorizontalSprite;
				screen.renderMob(x - 16, y - 16, sprite, flip);
				if (moving == true) {
					if (animate % 20 > 10) {
						sprite = Sprite.zombieHorizontalSprite1;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					} else {
						sprite = Sprite.zombieHorizontalSprite2;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					}
				}
			}
			if (dir == 2) {
				flip = 2;
				sprite = Sprite.zombieVerticalSprite;
				screen.renderMob(x - 16, y - 16, sprite, flip);
				if (moving == true) {
					if (animate % 20 > 10) {
						sprite = Sprite.zombieVerticalSprite1;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					} else {
						sprite = Sprite.zombieVerticalSprite2;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					}
				}
			}
			if (dir == 3) {
				flip = 1;
				sprite = Sprite.zombieHorizontalSprite;
				screen.renderMob(x - 16, y - 16, sprite, flip);
				if (moving == true) {
					if (animate % 20 > 10) {
						sprite = Sprite.zombieHorizontalSprite1;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					} else {
						sprite = Sprite.zombieHorizontalSprite2;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					}
				}
			}
		} else {
			die();
		}
	}

	public void die() {
		dead = true;
		setX(-32);
		setY(-32);
		setDX(0);
		setDY(0);
		dir = 0;
		bounds = new Rectangle(0, 0, 0, 0);
		sprite = Sprite.nullSprite32;
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