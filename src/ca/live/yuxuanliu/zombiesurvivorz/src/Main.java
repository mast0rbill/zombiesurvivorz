package ca.live.yuxuanliu.zombiesurvivorz.src;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ca.live.yuxuanliu.zombiesurvivorz.src.menu.MenuAbout;
import ca.live.yuxuanliu.zombiesurvivorz.src.menu.MenuHelp;
import ca.live.yuxuanliu.zombiesurvivorz.src.menu.MenuMain;

public class Main {
	
	JFrame frame;
	MenuMain main = new MenuMain();
	Display game = new Display();
	MenuHelp help = new MenuHelp();
	MenuAbout about = new MenuAbout();
	public static String version = "v0.5";
	
	public Main(){
		main.version = version;
		frame = new JFrame("ZombieSurvivorZ");
		frame.setResizable(false);
		frame.getContentPane().add(main);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		JButton play = new JButton("Play");
		play.setBounds(703, 81, 151, 100);
		play.setForeground(Color.RED);
		play.setBackground(Color.ORANGE);
		play.setVisible(true);
		main.add(play);
		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				startGame();
			}	
		});
		JButton help = new JButton("Help");
		help.setBounds(703, 181, 151, 100);
		help.setForeground(Color.RED);
		help.setBackground(Color.ORANGE);
		help.setVisible(true);
		main.add(help);
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				toHelpMenu();
			}			
		});
		JButton about = new JButton("About");
		about.setBounds(703, 281, 151, 100);
		about.setForeground(Color.RED);
		about.setBackground(Color.ORANGE);
		about.setVisible(true);
		main.add(about);
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				toAboutMenu();
			}
		});
	}
	
	public void toHelpMenu(){
		resetFrame(help);
		JButton back = new JButton("Back");
		back.setBounds(459, 358, 100, 100);
		back.setBackground(Color.ORANGE);
		back.setVisible(true);
		help.add(back);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				toMainMenu();
			}
		});
	}
	
	public void toAboutMenu(){
		resetFrame(about);
		JButton back = new JButton("Back");
		back.setBounds(459, 358, 100, 100);
		back.setBackground(Color.ORANGE);
		back.setVisible(true);
		about.add(back);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				toMainMenu();
			}
		});
	}
	
	public void toMainMenu(){
		resetFrame(main);
		JButton play = new JButton("Play");
		play.setBounds(703, 81, 151, 100);
		play.setForeground(Color.RED);
		play.setBackground(Color.ORANGE);
		play.setVisible(true);
		main.add(play);
		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				startGame();
			}	
		});
		JButton help = new JButton("Help");
		help.setBounds(703, 181, 151, 100);
		help.setForeground(Color.RED);
		help.setBackground(Color.ORANGE);
		help.setVisible(true);
		main.add(help);
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				toHelpMenu();
			}			
		});
		JButton about = new JButton("About");
		about.setBounds(703, 281, 151, 100);
		about.setForeground(Color.RED);
		about.setBackground(Color.ORANGE);
		about.setVisible(true);
		main.add(about);
	}
	
	public void chooseLevel(){
	
	}
	
	public void startGame(){
		resetFrame(game);
		game.start();
	}
	
	public void resetFrame(Component add){
		frame.dispose();
		frame = new JFrame("ZombieSurvivorZ");
		frame.setResizable(false);
		frame.getContentPane().add(add);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		new Main();
	}
	
}
