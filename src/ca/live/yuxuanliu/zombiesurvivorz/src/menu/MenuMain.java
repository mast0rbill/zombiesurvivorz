package ca.live.yuxuanliu.zombiesurvivorz.src.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuMain extends Menu{
	private static final long serialVersionUID = 1L;
	public String version = "";
	
	public MenuMain(){
		super(MenuHelp.class.getResource("/textures/mainmenu.png"));
		setLayout(null);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setFont(new Font("SansSerif", Font.PLAIN, 36));
		g.setColor(Color.BLACK);
		g.drawString(version, 60, 450);
	}
}
