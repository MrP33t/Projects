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
	
	public Quest(String questName) {
		this.questName = questName;
		this.isDone = false;
		this.questLevel = 0;
	}
	public Quest(String questName, int questLevel) {
		this.questName = questName;
		this.isDone = false;
		this.questLevel = questLevel;
	}
	public Quest(String questName, boolean isDone) {
		this.questName = questName;
		this.isDone = isDone;
	}
	
	public void addSubQuest(String questName) {
		this.subQuests.add(new Quest(questName, this.questLevel + 1));
	}

	public void deleteSubQuest(Quest q) {
		this.subQuests.remove(q);
	}
	
	public void draw(Graphics2D g2D) {
		int scrPos = Debugger.scrollPosition;
		
		g2D.setColor(Color.RED);
		g2D.drawRect(MainPanel.WINDOW_WIDTH / 4 + (20 * this.questLevel), 100 + (100 * yIterator) - scrPos, MainPanel.WINDOW_WIDTH / 2 - (40 * this.questLevel), MainPanel.WINDOW_HEIGHT / 10);
		g2D.drawString(questName + " " + questLevel, MainPanel.WINDOW_WIDTH / 4 + (20 * this.questLevel) + 20, 100 + (100 * yIterator) + 30 - scrPos);
		
		
		yIterator++;
		
		if (!this.subQuests.isEmpty()) {
			for (Quest q: this.subQuests) {
				q.draw(g2D);
			}
		}
	}
}
