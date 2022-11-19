package code;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{

	private MainPanel MP;
	
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
				for (Quest q: MP.mainQuests) {
					clickedMouseBtn1(q);
				}
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
			return true;
		}
		return false;
	}
}
