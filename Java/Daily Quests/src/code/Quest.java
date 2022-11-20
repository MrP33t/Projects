package code;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Quest {

	// Iterator for y in quest draw()
	public static int yIterator = 0;
	
	String questName;
	ArrayList<Quest> subQuests = new ArrayList<Quest>();
	Boolean isDone;
	int questLevel;
	
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
		
		this.deleteBtn.height = MainPanel.WINDOW_HEIGHT / 10;
		this.deleteBtn.width = MainPanel.WINDOW_HEIGHT / 10;
		
		this.markBtn.height = MainPanel.WINDOW_HEIGHT / 12;
		this.markBtn.width = MainPanel.WINDOW_HEIGHT / 12;
	}
	
	public void addSubQuest(String questName) {
		this.subQuests.add(new Quest(questName, this.questLevel + 1));
	}

	public void deleteSubQuest(Quest q) {
		this.subQuests.remove(q);
	}
	
	public void update() {
		// Check if mouse is on deleteBtn if yes then put enable border
		if (Debugger.MousePositionX >= this.deleteBtn.x && Debugger.MousePositionX <= (this.deleteBtn.x + this.deleteBtn.width)
				&& Debugger.MousePositionY >= this.deleteBtn.y && Debugger.MousePositionY <= (this.deleteBtn.y + this.deleteBtn.height)) {
			this.deleteBtnOnHover = true;
		} else {
			this.deleteBtnOnHover = false;
		}
		if (Debugger.MousePositionX >= this.markBtn.x && Debugger.MousePositionX <= (this.markBtn.x + this.markBtn.width)
				&& Debugger.MousePositionY >= this.markBtn.y && Debugger.MousePositionY <= (this.markBtn.y + this.markBtn.height)) {
			this.markBtnOnHover = true;
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
		this.deleteBtn.y = this.y;
		
		// Drawing delete btn
		g2D.setColor(Color.RED);
		g2D.draw(deleteBtn);
		
		// If cursor is on deleteBtn draw this
		if (this.deleteBtnOnHover) {
			g2D.setColor(Color.BLUE);
			g2D.drawRect(this.deleteBtn.x - 5, this.deleteBtn.y - 5, this.deleteBtn.width + 10, this.deleteBtn.height + 10);
		}
		
		// Calculating markBtn position
		this.markBtn.x = this.x + this.boxWidth - this.markBtn.width - 10;
		this.markBtn.y = this.y + 7;
		
		// Drawing markBtn
		g2D.setColor(Color.RED);
		g2D.draw(markBtn);
		
		// Mark of completion
		if (this.isDone) {
			g2D.setColor(Color.ORANGE);
			g2D.fillRect(this.markBtn.x + 6, this.markBtn.y + 6, this.markBtn.width - 13, this.markBtn.height - 13);
		}
		
		// Drawing onHover effect on markBtn
		if (this.markBtnOnHover) {
			g2D.setColor(Color.BLUE);
			g2D.drawRect(this.markBtn.x + 5, this.markBtn.y + 5, this.markBtn.width - 10, this.markBtn.height - 10);
		}
		
		yIterator++;
		
		if (!this.subQuests.isEmpty()) {
			for (Quest q: this.subQuests) {
				q.draw(g2D);
			}
		}
	}
}
