package code;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Quest {

	String questName;
	ArrayList<Quest> subQuests = new ArrayList<Quest>();
	Boolean isDone;
	
	public Quest(String questName) {
		this.questName = questName;
		this.isDone = false;
	}
	public Quest(String questName, boolean isDone) {
		this.questName = questName;
		this.isDone = isDone;
	}
	
	public void addSubQuest(String questName) {
		this.subQuests.add(new Quest(questName));
	}

	public void deleteSubQuest(Quest q) {
		this.subQuests.remove(q);
	}
	
	public void draw(Graphics2D g2D) {
		
		g2D.setColor(Color.RED);
		g2D.drawString(questName, 100, 100);
	}
}
