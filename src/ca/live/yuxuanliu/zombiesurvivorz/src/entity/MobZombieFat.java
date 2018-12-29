package ca.live.yuxuanliu.zombiesurvivorz.src.entity;

import java.util.Random;

import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;
import ca.live.yuxuanliu.zombiesurvivorz.src.level.Level;

public class MobZombieFat extends MobZombie{

	public MobZombieFat(int x, int y, MobPlayer target, Level level) {
		super(x, y, target, level);
		health = 500;
		pointsAdded = 100;
	}
	
	@Override
	public void doAI(){
		if(new Random().nextInt(10) > 8 && (
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
	
	@Override
	public void render(Screen screen) {
		if (!dead) {
			int flip = 0;
			if (dir == 0) {
				flip = 0;
				sprite = Sprite.zombieFatVerticalSprite;
				screen.renderMob(x - 16, y - 16, sprite, flip);
				if (moving == true) {
					if (animate % 20 > 10) {
						sprite = Sprite.zombieFatVerticalSprite1;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					} else {
						sprite = Sprite.zombieFatVerticalSprite2;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					}
				}
			}
			if (dir == 1) {
				flip = 0;
				sprite = Sprite.zombieFatHorizontalSprite;
				screen.renderMob(x - 16, y - 16, sprite, flip);
				if (moving == true) {
					if (animate % 20 > 10) {
						sprite = Sprite.zombieFatHorizontalSprite1;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					} else {
						sprite = Sprite.zombieFatHorizontalSprite2;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					}
				}
			}
			if (dir == 2) {
				flip = 2;
				sprite = Sprite.zombieFatVerticalSprite;
				screen.renderMob(x - 16, y - 16, sprite, flip);
				if (moving == true) {
					if (animate % 20 > 10) {
						sprite = Sprite.zombieFatVerticalSprite1;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					} else {
						sprite = Sprite.zombieFatVerticalSprite2;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					}
				}
			}
			if (dir == 3) {
				flip = 1;
				sprite = Sprite.zombieFatHorizontalSprite;
				screen.renderMob(x - 16, y - 16, sprite, flip);
				if (moving == true) {
					if (animate % 20 > 10) {
						sprite = Sprite.zombieFatHorizontalSprite1;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					} else {
						sprite = Sprite.zombieFatHorizontalSprite2;
						screen.renderMob(x - 16, y - 16, sprite, flip);
					}
				}
			}
		} else {
			die();
		}
	}

}
