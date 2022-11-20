package code;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{

	private MainPanel MP;
	public static boolean foundOne = false;
	
	public MouseHandler(MainPanel MP) {
		this.MP = MP;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int keyCode = e.getButton();
		
		switch(e.getButton()) {
		case MouseEvent.BUTTON1: 
			if(!MP.mainQuests.isEmpty()) {
				foundOne = false;
				for (Quest q: MP.mainQuests) {
					clickedMouseBtn1(q);
				}
				if (Debugger.MousePositionY <= MainPanel.WINDOW_WIDTH / 10) {
					if (!foundOne) {
						MainPanel.currentlySelected = null;
					}
				}
			}
			checkCollisionWithButton();
			if (checkCollisionWithTextField()) {
				MainPanel.textFieldActive = true;
			} else {
				MainPanel.textFieldActive = false;
			}
		}
		
		Debugger.setPressedMouseButton("Button "+keyCode);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
		
		Debugger.setPressedMouseButton(" ");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	// Method handling clicking Btn1 on mouse
	private void clickedMouseBtn1(Quest quest) {
		
		// For all quests check if cursor is in its bounds
		if (Debugger.MousePositionY >= MainPanel.WINDOW_HEIGHT / 10 * 9) return;
		if (checkCollision(quest)) return;
		for (Quest q: quest.subQuests) {
			if (checkCollision(q)) return;
			if (!q.subQuests.isEmpty()) {
				for (Quest qq: q.subQuests) {
					clickedMouseBtn1(qq);
				}
			}
		}
	}
	
	// Method for checking collision 
	private boolean checkCollision(Quest q) {
		if (Debugger.MousePositionX >= q.x && Debugger.MousePositionX <= (q.x + q.boxWidth)
				&& Debugger.MousePositionY >= q.y && Debugger.MousePositionY <= (q.y + q.boxHeight)) {
			MainPanel.currentlySelected = q;
			foundOne = true;
			return true;
		}
		return false;
	}
	
	// Method for handling button Add clicked
	private void checkCollisionWithButton() {
		if (Debugger.MousePositionX >= MainPanel.button.x && Debugger.MousePositionX <= (MainPanel.button.x + MainPanel.button.width)
				&& Debugger.MousePositionY >= MainPanel.button.y && Debugger.MousePositionY <= (MainPanel.button.y + MainPanel.button.height)) {
			if(!MainPanel.textFieldText.isEmpty()) {
				if (MainPanel.currentlySelected != null) {
					System.out.println("Adding subquest");
				} else {
					MP.mainQuests.add(new Quest(MainPanel.textFieldText));
					System.out.println("Adding main quest");
				}
				MainPanel.textFieldText = "";
			}
		}
	}
	
	// Method for checking collision with TextField
	private boolean checkCollisionWithTextField() {
		if (Debugger.MousePositionX >= MainPanel.textField.x && Debugger.MousePositionX <= (MainPanel.textField.x + MainPanel.textField.width)
				&& Debugger.MousePositionY >= MainPanel.textField.y && Debugger.MousePositionY <= (MainPanel.textField.y + MainPanel.textField.height)) {
			return true;
		}
		return false;
	}
}
