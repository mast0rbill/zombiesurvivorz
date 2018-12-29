package ca.live.yuxuanliu.zombiesurvivorz.src.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 300, HEIGHT = 168, SCALE = 3;

	public ErrorScreen(String e) {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setLayout(null);
		JLabel label = new JLabel("Error");
		label.setForeground(Color.RED);
		JLabel point = new JLabel(e);
		point.setForeground(Color.RED);
		label.setFont(new Font("Monospaced", Font.BOLD, 24));
		point.setFont(new Font("Monospaced", Font.ITALIC, 24));
		label.setBounds(new Rectangle(375, 150, 10000, 100));
		point.setBounds(new Rectangle(375, 200, 10000, 100));
		label.setVisible(true);
		add(label);
		add(point);
		JFrame frame = new JFrame("Death Screen");
		frame.setResizable(false);
		frame.setTitle("ZombieSurvivorZ - ErrorScreen");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
