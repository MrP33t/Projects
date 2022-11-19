package code;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int keyCode = e.getButton();
		
		
		
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

}
