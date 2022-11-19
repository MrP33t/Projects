package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;
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
	private KeyboardHandler keyH = new KeyboardHandler(this);
	private MouseHandler mouseH = new MouseHandler(this);
	private MouseWheelHandler mouseWH = new MouseWheelHandler();
	
	// Mouse position
	public Point mousePosition = new Point(0,0);
	
	// Main Quest Array List
	public ArrayList<Quest> mainQuests;
	private ArrayList<QuestData> mainQuestsData;
	
	// Path for saving Quest Data
	private final static String questDataPath = "C:\\DailyQuests\\quests";
	
	// Currently selected quest
	public static Quest currentlySelected = null;
	
	// Variables for textField and Button
	private static int boxX = WINDOW_WIDTH / 5;
	private static int boxY = WINDOW_HEIGHT / 10 * 9;
	private static int boxWidth = WINDOW_WIDTH / 5 * 3;
	private static int boxHeight = WINDOW_HEIGHT / 10;
	
	public static Rectangle textField = new Rectangle(boxX + 20, boxY + 10, boxWidth / 2, boxHeight / 2);
	public static Rectangle button = new Rectangle(textField.x + textField.width + 40, boxY + 10, boxWidth / 4, boxHeight / 2);
	
	// TextField
	public static boolean textFieldActive = false;
	public static String textFieldText = "";
	
	public MainPanel() {
		this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);

		this.addKeyListener(this.keyH);
		this.addMouseListener(this.mouseH);
		this.addMouseWheelListener(this.mouseWH);
		
		this.setFocusable(true);
		
		this.mainQuests = new ArrayList<Quest>();
		this.mainQuestsData = new ArrayList<QuestData>();
		
		// Load data if there are any to load else create empty list
		loadQuests();
	}
	
	// Method for loading Quest data
	private void loadQuests() {
		
		// Load Quest Data
		try {
			FileInputStream fis = new FileInputStream(questDataPath);
	        ObjectInputStream ois = new ObjectInputStream(fis);

	        mainQuestsData = (ArrayList<QuestData>) ois.readObject();
	        
	        ois.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Data could not be loaded");
		}
		
		// Copy Quest Data to Quest
		
		if (!mainQuestsData.equals(null)) {
			
			for (QuestData q: mainQuestsData) {
				Quest quest = new Quest(q.questName, q.isDone, q.questLevel);
				mainQuests.add(quest);
				if (!q.subQuests.isEmpty()) {
					loadSubQuests(q, quest);
				}
			}
		}
	}
	
	// Method for loading subQuests
	private void loadSubQuests(QuestData subQuest, Quest quest) {
		for (QuestData q: subQuest.subQuests) {
			Quest questt = new Quest(q.questName, q.isDone, q.questLevel);
			quest.subQuests.add(questt);
			if (!q.subQuests.isEmpty()) {
				loadSubQuests(q, questt);
			}
		}
	}
	
	// Method for saving Quest Data
	public void saveQuests() {
		
		if (!mainQuests.isEmpty()) {
			
			// Copy Quest to Quest Data
			
			if (!mainQuests.isEmpty()) {
						
				for (Quest q: mainQuests) {
					QuestData questData = new QuestData(q.questName, q.isDone, q.questLevel);
					mainQuestsData.add(questData);
					if (!q.subQuests.isEmpty()) {
						saveSubQuests(q, questData);
					}
				}
			}
			
			
			// Save Quest Data
			try {
				FileOutputStream fos = new FileOutputStream(questDataPath);
		        ObjectOutputStream oos = new ObjectOutputStream(fos);

		        oos.writeObject(mainQuestsData);
		        
		        oos.close();
			} catch (IOException e) {
				System.out.println("Saving data error");
			}
		}
	}
	
	// Method for saving subQuests
	private void saveSubQuests(Quest quest, QuestData questData) {
		for (Quest q: quest.subQuests) {
			QuestData questt = new QuestData(q.questName, q.isDone, q.questLevel);
			questData.subQuests.add(questt);
			if (!q.subQuests.isEmpty()) {
				saveSubQuests(q, questt);
			}
		}
	}
	
	// Method for handling textField 
	public void typedInTextField(char a) {
		MainPanel.textFieldText += a;
	}
	
	public void deletedInTextField() {
		MainPanel.textFieldText = deleteLastCharOfString(MainPanel.textFieldText);
	}
	
	public String deleteLastCharOfString(String str) {
	    if (str != null && str.length() > 0) {
	        str = str.substring(0, str.length() - 1);
	    }
	    return str;
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
		
		// Drawing quests
		for (Quest q: mainQuests) {
			q.draw(g2D);
		}
		
		// TODO: Draw button and text field for adding main quests (Always on bottom)
		// Box
		g2D.setColor(Color.GREEN);
		g2D.fillRect(this.boxX, this.boxY, this.boxWidth, this.boxHeight);
		
		// TextField
		g2D.setColor(Color.PINK);
		g2D.fill(textField);
		
		// Button
		g2D.setColor(Color.YELLOW);
		g2D.fill(button);
		
		// Drawing debugger
		if (Debugger.getDebugger()) {
			Debugger.draw(lastFPSCount, g2D);
		}
				
		// Calculating maximum amount of scrolling
		MouseWheelHandler.maxScroll = Quest.yIterator * 100 - WINDOW_HEIGHT + 200;
		
		Quest.yIterator = 0;
		
		g2D.dispose();
	}
}
