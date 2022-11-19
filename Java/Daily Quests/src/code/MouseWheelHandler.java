package code;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelHandler implements MouseWheelListener{

	public static int maxScroll = 0;
	private int scrollSpeed = 13;
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() == 1) {
			if (Debugger.scrollPosition <= maxScroll) {
				Debugger.updateScrollPosition(scrollSpeed);
			}
		}
		if (e.getWheelRotation() == -1) {
			if (Debugger.scrollPosition > 0) {
				Debugger.updateScrollPosition(-scrollSpeed);
			}
		}
	}

}
