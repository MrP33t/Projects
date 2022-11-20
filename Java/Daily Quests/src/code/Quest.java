package code;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Quest {

	// Iterator for y in quest draw()
	public static int yIterator = 0;
	
	String questName;
	ArrayList<Quest> subQuests = new ArrayList<Quest>();
	Boolean isDone;
	int questLevel;
	int progress = 0;
	int maxProgress = 1;
	
	// Drawed box of quest
	int x, y, boxHeight, boxWidth;
	
	// Delete button
	Rectangle deleteBtn = new Rectangle();
	
	// Flag for delete btn
	boolean deleteBtnOnHover = false;
	
	// Mark as Done button
	Rectangle markBtn = new Rectangle();
	
	// Flag for mark btn
	boolean markBtnOnHover = false;
	
	// Progression bar
	Rectangle progressionBar = new Rectangle();
	
	// BufferedImage of deleteBtn
	BufferedImage delete1, delete2, checked1, checked2;
	
	public Quest(String questName) {
		this.questName = questName;
		this.isDone = false;
		this.questLevel = 0;
		
		getReady();
	}
	public Quest(String questName, int questLevel) {
		this.questName = questName;
		this.isDone = false;
		this.questLevel = questLevel;
		
		getReady();
	}
	public Quest(String questName, boolean isDone, int questLevel) {
		this.questName = questName;
		this.isDone = isDone;
		this.questLevel = questLevel;
		
		getReady();
	}
	
	// Method for setting starting variables 
	private void getReady() {
		
		this.boxWidth = MainPanel.WINDOW_WIDTH / 2 - (40 * this.questLevel);
		this.boxHeight = MainPanel.WINDOW_HEIGHT / 10;
		
		this.deleteBtn.height = MainPanel.WINDOW_HEIGHT / 16;
		this.deleteBtn.width = MainPanel.WINDOW_HEIGHT / 16;
		
		this.markBtn.height = MainPanel.WINDOW_HEIGHT / 16;
		this.markBtn.width = MainPanel.WINDOW_HEIGHT / 16;
		
		this.progressionBar.height = this.boxHeight / 3;
		this.progressionBar.width = this.boxWidth / 8 * 7 - this.markBtn.width;
		
		// Load images
		try {
			delete1 = ImageIO.read(getClass().getResourceAsStream("/res/delete1.png"));
			delete2 = ImageIO.read(getClass().getResourceAsStream("/res/delete2.png"));
			
			checked1 = ImageIO.read(getClass().getResourceAsStream("/res/checked1.png"));
			checked2 = ImageIO.read(getClass().getResourceAsStream("/res/checked2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addSubQuest(String questName) {
		this.subQuests.add(new Quest(questName, this.questLevel + 1));
	}

	public void deleteSubQuest(Quest q) {
		this.subQuests.remove(q);
	}
	public void calculateProgression() {
		this.maxProgress = 1;
		this.progress = 0;
		if (this.isDone) this.progress++;
		if (!this.subQuests.isEmpty()) {
			this.maxProgress = 0;
			this.progress = 0;
			for (Quest q: this.subQuests) {
				if (q.isDone) this.progress++;
				this.maxProgress++;
				q.calculateProgression();
			}
		}
	}
	
	public void update() {
		
		// Check if mouse is on deleteBtn if yes then put enable border
		if (Debugger.MousePositionX >= this.deleteBtn.x && Debugger.MousePositionX <= (this.deleteBtn.x + this.deleteBtn.width)
				&& Debugger.MousePositionY >= this.deleteBtn.y && Debugger.MousePositionY <= (this.deleteBtn.y + this.deleteBtn.height)) {
			this.deleteBtnOnHover = true;
			MainPanel.cursorOnSomething = true;
		} else {
			this.deleteBtnOnHover = false;
		}
		if (Debugger.MousePositionX >= this.markBtn.x && Debugger.MousePositionX <= (this.markBtn.x + this.markBtn.width)
				&& Debugger.MousePositionY >= this.markBtn.y && Debugger.MousePositionY <= (this.markBtn.y + this.markBtn.height)) {
			this.markBtnOnHover = true;
			MainPanel.cursorOnSomething = true;
		} else {
			this.markBtnOnHover = false;
		}
		if (!this.subQuests.isEmpty()) {
			for (Quest q: this.subQuests) {
				q.update();
			}
		}
	}
	
	public void draw(Graphics2D g2D) {
		int scrPos = Debugger.scrollPosition;
		
		// Calculating box x and y
		this.x = MainPanel.WINDOW_WIDTH / 4 + (20 * this.questLevel);
		this.y = 100 + (100 * yIterator) - scrPos;
		
		if (this.y <= MainPanel.WINDOW_HEIGHT / 10 * 9) {
			g2D.setColor(Color.RED);
			g2D.drawRect(this.x, this.y, this.boxWidth, this.boxHeight);
			g2D.drawString(questName + " " + questLevel, MainPanel.WINDOW_WIDTH / 4 + (20 * this.questLevel) + 20, 100 + (100 * yIterator) + 30 - scrPos);
			
			// If is selected then draw this
			if (MainPanel.currentlySelected != null) {
				if (MainPanel.currentlySelected.equals(this))
				{
					g2D.setColor(Color.BLUE);
					g2D.drawRect(this.x - 6, this.y - 6, this.boxWidth + 12, this.boxHeight + 12);
				}
			}
		}
		
		// Calculating delete btn position
		this.deleteBtn.x = this.x + this.boxWidth + 20;
		this.deleteBtn.y = this.y + this.deleteBtn.height / 3;
		
		// Drawing delete btn
		g2D.drawImage(delete1, this.deleteBtn.x, this.deleteBtn.y, this.deleteBtn.width, this.deleteBtn.height, null);
		
		// If cursor is on deleteBtn draw this
		if (this.deleteBtnOnHover) {
			g2D.drawImage(delete2, this.deleteBtn.x, this.deleteBtn.y, this.deleteBtn.width, this.deleteBtn.height, null);
		}
		
		// Calculating markBtn position
		this.markBtn.x = this.x + this.boxWidth - this.markBtn.width - 10;
		this.markBtn.y = this.y + this.markBtn.height / 4;
		
		// Drawing markBtn
		g2D.drawImage(checked1, this.markBtn.x, this.markBtn.y, this.markBtn.width, this.markBtn.height, null);
		
		// Mark of completion
		if (this.isDone) {
			g2D.drawImage(checked2, this.markBtn.x, this.markBtn.y, this.markBtn.width, this.markBtn.height, null);
		}
		
		// Drawing onHover effect on markBtn
		if (this.markBtnOnHover) {
			g2D.drawImage(checked1, this.markBtn.x - 8, this.markBtn.y - 8, this.markBtn.width + 16, this.markBtn.height + 16, null);
		}
		
		// Progression bar
		if (this.subQuests.size() > 1) {
			// Calculating position of progression bar
			this.progressionBar.x = this.x + 10;
			this.progressionBar.y = this.y + 50;
			
			// Drawing progress
			g2D.setColor(new Color(209, 160, 0));
			g2D.fillRect(this.progressionBar.x, this.progressionBar.y, this.progressionBar.width / this.maxProgress * this.progress, this.progressionBar.height);
			
			// Drawing progressionBar outline
			g2D.setColor(Color.WHITE);
			g2D.draw(progressionBar);
		}
		
		yIterator++;
		
		if (!this.subQuests.isEmpty()) {
			for (Quest q: this.subQuests) {
				q.draw(g2D);
			}
		}
	}
}
