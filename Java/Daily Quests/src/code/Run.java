package code;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

// Program Start class
public class Run {

	private static JFrame window;
	private static MainPanel MP;
	// Class for creating a window
	public static void main(String[] args) {
		
		window = new JFrame();
		
		window.setTitle("Daily Quests");
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent event) {
		        exit();
		    }
		});
		window.setResizable(false);
		
		MP = new MainPanel();
		window.add(MP);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		MP.startProgramThread();
	}
	
	private static void exit() {
		MP.saveQuests();
		window.dispose();
	    System.exit(0);
	}

}
