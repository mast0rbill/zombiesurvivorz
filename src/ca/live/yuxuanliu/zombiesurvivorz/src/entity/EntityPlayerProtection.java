package ca.live.yuxuanliu.zombiesurvivorz.src.entity;

import java.awt.Rectangle;

import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Screen;
import ca.live.yuxuanliu.zombiesurvivorz.src.graphics.Sprite;

public class EntityPlayerProtection extends MovableEntity{

	public MobPlayer targetPlayer;
	private int animate = 0;
	
	public EntityPlayerProtection(MobPlayer targetPlayer){
		this.targetPlayer = targetPlayer;
		this.x = targetPlayer.x;
		this.y = targetPlayer.y;
		this.bounds = new Rectangle(targetPlayer.bounds.x - 3, targetPlayer.bounds.y - 3, targetPlayer.bounds.width + 6, targetPlayer.bounds.height + 6);
		sprite = Sprite.playerProtectionSprite;
	}
	
	public void update(){
		if (animate < 7500)
			animate++;
		else
			animate = 0;
		if(targetPlayer.invincible){
			this.x = targetPlayer.x;
			this.y = targetPlayer.y;
			bounds = new Rectangle(targetPlayer.bounds.x - 1, targetPlayer.bounds.y - 1, targetPlayer.bounds.width + 2, targetPlayer.bounds.height + 2);
		} else {
			this.x = 0;
			this.y = 0;
			bounds = new Rectangle(0, 0, 0, 0);
		}
	}
	
	public void render(Screen screen){
		if (animate % 20 > 10) {
			sprite = Sprite.playerProtectionSprite;
			screen.renderMob(x - 16, y- 16, sprite, 0);
		} else {
			sprite = Sprite.playerProtectionSprite1;
			screen.renderMob(x - 16, y - 16, sprite, 0);
		}
	}
	
}
