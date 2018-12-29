package ca.live.yuxuanliu.zombiesurvivorz.src.entity;

import java.awt.Rectangle;

import ca.live.yuxuanliu.zombiesurvivorz.src.Display;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;
import ca.live.yuxuanliu.zombiesurvivorz.src.level.Level;

public class EntityBullet extends MovableEntity {
	public int dy = 0, dx = 0;
	MobPlayer shooter;
	public boolean exist = false;

	public EntityBullet(MobPlayer shooter, Level level) {
		this.level = level;
		this.shooter = shooter;
		exist = true;
		dir = shooter.dir;
		if (dir == 0) {
			x = shooter.x + 8;
			y = shooter.y;
		} else if (dir == 1) {
			x = shooter.x + 8;
			y = shooter.y + 8;
		} else if (dir == 2) {
			x = shooter.x + 8;
			y = shooter.y + 8;
		} else if (dir == 3) {
			x = shooter.x;
			y = shooter.y + 8;
		} else {
			x = shooter.x;
			y = shooter.y;
		}
		sprite = Sprite.bulletHorizontalSprite;
		if (dir == 0)
			bounds = new Rectangle( (this.x - 16) + 11,  (this.y - 16) + 3, 2, 3);
		else if (dir == 1)
			bounds = new Rectangle( (this.x - 16) + 12,  (this.y - 16) + 11, 3, 2);
		else if (dir == 2)
			bounds = new Rectangle( (this.x - 16) + 11,  (this.y - 16) + 5, 2, 3);
		else if (dir == 3)
			bounds = new Rectangle( (this.x - 16) + 3,  (this.y - 16) + 11, 3, 2);
		else
			bounds = new Rectangle( (this.x - 16),  (this.y - 16), 3, 3);
	}

	public void update() {
		dir = shooter.dir;
		if (dir == 0)
			bounds = new Rectangle( (this.x - 16) + 11,  (this.y - 16) + 3, 2, 3);
		else if (dir == 1)
			bounds = new Rectangle( (this.x - 16) + 12,  (this.y - 16) + 11, 3, 2);
		else if (dir == 2)
			bounds = new Rectangle( (this.x - 16) + 11,  (this.y - 16) + 5, 2, 3);
		else if (dir == 3)
			bounds = new Rectangle( (this.x - 16) + 3,  (this.y - 16) + 11, 3, 2);
		else
			bounds = new Rectangle( (this.x - 16),  (this.y - 16), 3, 3);
		if (dir == 0) {
			dx = 0;
			dy = -10;
		} else if (dir == 2) {
			dx = 0;
			dy = 10;
		} else if (dir == 1) {
			dx = 10;
			dy = 0;
		} else if (dir == 3) {
			dx = -10;
			dy = 0;
		}
		if (dx != 0 || dy != 0)
			move(dx, dy);
		if (x >= (Display.WIDTH * Display.SCALE) || x <= 0
				|| y >= (Display.HEIGHT * Display.SCALE)
				|| y <= 0 || collided)
			exist = false;
	}

	public void reset() {
		dir = shooter.dir;
		if (dir == 0) {
			x = shooter.x + 8;
			y = shooter.y;
		} else if (dir == 1) {
			x = shooter.x + 8;
			y = shooter.y + 8;
		} else if (dir == 2) {
			x = shooter.x + 8;
			y = shooter.y + 8;
		} else if (dir == 3) {
			x = shooter.x;
			y = shooter.y + 8;
		} else {
			x = shooter.x;
			y = shooter.y;
		}
		if (dir == 0)
			bounds = new Rectangle( (this.x - 16) + 11,  (this.y - 16) + 3, 2, 3);
		else if (dir == 1)
			bounds = new Rectangle( (this.x - 16) + 12,  (this.y - 16) + 11, 3, 2);
		else if (dir == 2)
			bounds = new Rectangle( (this.x - 16) + 11,  (this.y - 16) + 5, 2, 3);
		else if (dir == 3)
			bounds = new Rectangle( (this.x - 16) + 3,  (this.y - 16) + 11, 3, 2);
		else
			bounds = new Rectangle( (this.x - 16),  (this.y - 16), 3, 3);
	}

	public void render(Screen screen) {
		int flip = 0;
		if (dir == 0) {
			flip = 0;
			sprite = Sprite.bulletVerticalSprite;
			screen.renderMob(x - 16, y - 16, sprite, flip);
		}
		if (dir == 1) {
			flip = 0;
			sprite = Sprite.bulletHorizontalSprite;
			screen.renderMob(x - 16, y - 16, sprite, flip);
		}
		if (dir == 2) {
			flip = 2;
			sprite = Sprite.bulletVerticalSprite;
			screen.renderMob(x - 16, y - 16, sprite, flip);
		}
		if (dir == 3) {
			flip = 1;
			sprite = Sprite.bulletHorizontalSprite;
			screen.renderMob(x - 16, y - 16, sprite, flip);
		}
	}

}
