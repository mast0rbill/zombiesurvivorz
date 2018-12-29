package ca.live.yuxuanliu.zombiesurvivorz.src.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KeyListener is an interface that listens to the keyboard
public class Keyboard implements KeyListener {

	// Boolean that holds all the keys pressed or released, pressed = true,
	// released = false
	private boolean[] keys = new boolean[222];

	// Keeps tracks of whether we pressed it or not
	public boolean up, down, left, right, shoot, up2, down2, left2, right2,
			switchWeapon, reload, use;

	// Will check if the keys are pressed or released
	public void update() {
		// Setting each boolean to each key
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		up2 = keys[KeyEvent.VK_W];
		down2 = keys[KeyEvent.VK_S];
		left2 = keys[KeyEvent.VK_A];
		right2 = keys[KeyEvent.VK_D];
		switchWeapon = keys[KeyEvent.VK_E];
		reload = keys[KeyEvent.VK_R];
		shoot = keys[KeyEvent.VK_SPACE];
		use = keys[KeyEvent.VK_F];
	}

	public void keyPressed(KeyEvent e) {
		// Get the ID of the key pressed to true
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		// Get the ID of the key released to false
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}

}
