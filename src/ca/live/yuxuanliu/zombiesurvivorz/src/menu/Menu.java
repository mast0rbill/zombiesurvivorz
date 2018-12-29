package ca.live.yuxuanliu.zombiesurvivorz.src.menu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public abstract class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 300, HEIGHT = 168, SCALE = 3;
	ImageIcon icon;
	Image img;
	
	public Menu(URL path){
		icon = new ImageIcon(path);
		img = icon.getImage();
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
	}
	
}
