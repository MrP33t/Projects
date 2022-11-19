package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// Everything inside a window
public class MainPanel extends JPanel implements Runnable{
	
	// Getting screen Size
	public final static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	// Calculating Window Size
	public final static int WINDOW_WIDTH = SCREEN_WIDTH / 5 * 4;
	public final static int WINDOW_HEIGHT = SCREEN_HEIGHT / 5 * 4;
	
	// Program Frames Per Second
	public final static int FPS = 60;
	// Variables for calculating delta (for run() method)
	public final static double drawInterval = 1000000000 / FPS;
	private double delta;
	private long lastTime, currentTime, timer;
	private int drawCount;
	private int lastFPSCount = 0;
	
	// Program thread
	private Thread programThread;
	
	// Program Keyboard Handler
	private KeyboardHandler keyH = new KeyboardHandler();
	private MouseHandler mouseH = new MouseHandler();
	
	// Mouse position
	public Point mousePosition = new Point(0,0);
	
	public MainPanel() {
		this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);

		this.addKeyListener(this.keyH);
		this.addMouseListener(this.mouseH);
		
		this.setFocusable(true);
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
				lastFPSCount = drawCount;
				drawCount = 0;
				timer = 0;
			}
		}
		
	}
	
	// Method for updating calculations and variables
	public void update() {
		// Calculating position of mouse
		mousePosition = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(mousePosition, this);
		Debugger.setMousePosition(mousePosition.x, mousePosition.y);
		
	}
	
	// Method for drawing, that is called when repaint() is used
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		// Drawing everything in MainPanel
		if (Debugger.getDebugger()) {
			Debugger.draw(lastFPSCount, g2D);
		}
		
		g2D.dispose();
		
	}
}
