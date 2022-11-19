package code;

import javax.swing.JFrame;

// Program Start class
public class Run {

	// Class for creating a window
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		
		window.setTitle("Daily Quests");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		MainPanel MP = new MainPanel();
		window.add(MP);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		MP.startProgramThread();
	}

}
