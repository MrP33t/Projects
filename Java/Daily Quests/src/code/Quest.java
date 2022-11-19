package code;

import java.util.ArrayList;

public class Quest {

	String questName;
	ArrayList<Quest> subQuests;
	Boolean isDone;
	
	public Quest(String questName) {
		this.questName = questName;
		this.isDone = false;
	}
	
	public void addSubQuest(String questName) {
		this.subQuests.add(new Quest(questName));
	}

	public void deleteSubQuest(Quest q) {
		this.subQuests.remove(q);
	}
}
