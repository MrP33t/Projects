package code;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

// Program Start class
public class Run {

	private static JFrame window;
	private static MainPanel MP;
	public static BufferedImage icon;
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
		
		window.setIconImage(icon);
		
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
