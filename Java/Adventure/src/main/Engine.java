package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import handlers.KeyboardHandler;
import handlers.MouseHandler;
import states.CharacterCreator;
import states.MainMenu;

public class Engine extends JPanel implements Runnable{

	// Get user width and height of screen
	public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	// Calculating tile size
	public static final int TILE_HEIGHT = SCREEN_HEIGHT / 18;
	public static final int TILE_WIDTH = SCREEN_WIDTH / 32;
	
	// Handlers
	private KeyboardHandler keyH = new KeyboardHandler();
	private MouseHandler mouseH = new MouseHandler();
	public static Point mouseLocation;
	
	// Engine thread
	private Thread engineThread;
	
	// FPS
	private static final int FPS = 60;
	private static final double drawInterval = 1000000000 / FPS;
	public static int lastFPSCount = 0;
	
	// Game states
	public static int gameState = 0;
	public static final int characterCreatorState = 0;
	public static final int mainMenuState = 1;
	public static final int selectWorldState = 2;
	public static final int createWorldState = 3;
	public static final int joinWorldState = 4;
	public static final int inGameState = 5;
	
	// Game state classes
	private static CharacterCreator characterCreator;
	
	// Constructor
	public Engine() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		this.addKeyListener(keyH);
		this.addMouseListener(mouseH);
		
		this.engineThread = new Thread(this);
		this.engineThread.start();
		
		this.characterCreator = new CharacterCreator();
		
		mouseTrack();
	}

	@Override
	public void run() {
		
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		//TODO: Check if character created if yes then change gameState to 1
		
		while (this.engineThread != null) {
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
	public void mouseTrack() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					mouseLocation = MouseInfo.getPointerInfo().getLocation();
					
					if(UtilityTool.mouseOnRectangle(CharacterCreator.hairLeftBtn)) {
						CharacterCreator.hairLeftBtnOnHover = true;
					} else {
						CharacterCreator.hairLeftBtnOnHover = false;
					}
					if(UtilityTool.mouseOnRectangle(CharacterCreator.hairRightBtn)) {
						CharacterCreator.hairRightBtnOnHover = true;
					} else {
						CharacterCreator.hairRightBtnOnHover = false;
					}
					
					if(UtilityTool.mouseOnRectangle(CharacterCreator.eyesLeftBtn)) {
						CharacterCreator.eyesLeftBtnOnHover = true;
					} else {
						CharacterCreator.eyesLeftBtnOnHover = false;
					}
					if(UtilityTool.mouseOnRectangle(CharacterCreator.eyesRightBtn)) {
						CharacterCreator.eyesRightBtnOnHover = true;
					} else {
						CharacterCreator.eyesRightBtnOnHover = false;
					}
					
					if(UtilityTool.mouseOnRectangle(CharacterCreator.bodyLeftBtn)) {
						CharacterCreator.bodyLeftBtnOnHover = true;
					} else {
						CharacterCreator.bodyLeftBtnOnHover = false;
					}
					if(UtilityTool.mouseOnRectangle(CharacterCreator.bodyRightBtn)) {
						CharacterCreator.bodyRightBtnOnHover = true;
					} else {
						CharacterCreator.bodyRightBtnOnHover = false;
					}
					
					if(UtilityTool.mouseOnRectangle(CharacterCreator.bottomLeftBtn)) {
						CharacterCreator.bottomLeftBtnOnHover = true;
					} else {
						CharacterCreator.bottomLeftBtnOnHover = false;
					}
					if(UtilityTool.mouseOnRectangle(CharacterCreator.bottomRightBtn)) {
						CharacterCreator.bottomRightBtnOnHover = true;
					} else {
						CharacterCreator.bottomRightBtnOnHover = false;
					}
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}
	public void update() {
		// All the updates
		// Get mouse location
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		// All the drawing
		
		// Creator state
		if(gameState == characterCreatorState) {
			CharacterCreator.draw(g2D);
		}
		// Main Menu state
		if(gameState == mainMenuState) {
			MainMenu.draw(g2D);
		}
		// World Selection state
		if(gameState == selectWorldState) {
			
		}
		// World Creation state
		if(gameState == createWorldState) {
			
		}
		// World Join state
		if(gameState == joinWorldState) {
			
		}
		// inGame state
		if(gameState == inGameState) {
			
		}
		
		g2D.dispose();
	}
}
