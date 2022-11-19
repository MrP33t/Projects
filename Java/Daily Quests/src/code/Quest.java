package code;

import java.awt.Color;
import java.awt.Graphics2D;
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
	
	public Quest(String questName) {
		this.questName = questName;
		this.isDone = false;
		this.questLevel = 0;
		
		this.boxWidth = MainPanel.WINDOW_WIDTH / 2 - (40 * this.questLevel);
		this.boxHeight = MainPanel.WINDOW_HEIGHT / 10;
	}
	public Quest(String questName, int questLevel) {
		this.questName = questName;
		this.isDone = false;
		this.questLevel = questLevel;
		
		this.boxWidth = MainPanel.WINDOW_WIDTH / 2 - (40 * this.questLevel);
		this.boxHeight = MainPanel.WINDOW_HEIGHT / 10;
	}
	public Quest(String questName, boolean isDone, int questLevel) {
		this.questName = questName;
		this.isDone = isDone;
		this.questLevel = questLevel;
		
		this.boxWidth = MainPanel.WINDOW_WIDTH / 2 - (40 * this.questLevel);
		this.boxHeight = MainPanel.WINDOW_HEIGHT / 10;
	}
	
	public void addSubQuest(String questName) {
		this.subQuests.add(new Quest(questName, this.questLevel + 1));
	}

	public void deleteSubQuest(Quest q) {
		this.subQuests.remove(q);
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
		
		yIterator++;
		
		if (!this.subQuests.isEmpty()) {
			for (Quest q: this.subQuests) {
				q.draw(g2D);
			}
		}
	}
}
