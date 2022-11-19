package code;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String questName;
	ArrayList<QuestData> subQuests;
	Boolean isDone;
	
	public static void saveData() {
		// Copying from Quest to QuestData
		
		// Writing to file
	}


	public static void loadData() {
		// Loading from file
		
		// Copying from QuestData to Quest
	}
}
