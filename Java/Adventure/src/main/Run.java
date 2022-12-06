package main;

import javax.swing.JFrame;

public class Run {

	public static void main(String[] args) {
		
		JFrame window= new JFrame();
		
		window.setTitle("Adventure");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		window.setUndecorated(true);
		
		window.add(new Engine());
		window.pack();
		window.setVisible(true);
		
	}

}
