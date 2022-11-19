package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

// Everything inside a window
public class MainPanel extends JPanel implements Runnable{
	
	// Getting screen Size
	public final static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	// Calculating Window Size
	public final static int WINDOW_WIDTH = SCREEN_WIDTH - 100;
	public final static int WINDOW_HEIGHT = SCREEN_HEIGHT - 100;
	
	// Program Frames Per Second
	public final static int FPS = 60;
	// Variables for calculating delta (for run() method)
	public final static double drawInterval = 1000000000 / FPS;
	private double delta;
	private long lastTime, currentTime, timer;
	private int drawCount;
	
	// Program thread
	private Thread programThread;
	
	public MainPanel() {
		this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
	}
	
	// Method for starting thread of program
	public void startProgramThread() {
		programThread = new Thread(this);
		programThread.start();
	}

	// Loop of whole program
	@Override
	public void run() {
		
		delta = 0;
		lastTime = System.nanoTime();
		timer = 0;
		drawCount = 0;
		
		while(programThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			// Update
			update();
			// Draw
			repaint();
		}
		
	}
	
	// Method for updating calculations and variables
	public void update() {
		
	}
	
	// Method for drawing, that is called when repaint() is used
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		// Drawing everything in MainPanel
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setColor(Color.WHITE);
		
		g2D.fillRect(100, 100, 200, 200);
		
		g2D.dispose();
	}
}
