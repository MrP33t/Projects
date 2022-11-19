package code;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String questName;
	ArrayList<QuestData> subQuests = new ArrayList<QuestData>();
	Boolean isDone;
	
	public QuestData (String questName, boolean isDone) {
		this.questName = questName;
		this.isDone = isDone;
	}
}
