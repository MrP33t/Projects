package code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener{

	private MainPanel MP;
	
	public KeyboardHandler(MainPanel MP) {
		this.MP = MP;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (!MainPanel.textFieldActive) {
			switch (keyCode) {
			case KeyEvent.VK_F2:
				if (Debugger.getDebugger()) {
					Debugger.setDebugger(false);
				} else {
					Debugger.setDebugger(true);
				}
				break;
			case KeyEvent.VK_R:
				MP.mainQuests.add(new Quest("Test"));
				break;
			}
		} else {
			if (keyCode == KeyEvent.VK_BACK_SPACE) {
				MP.deletedInTextField();
			} else if (keyCode == KeyEvent.VK_SHIFT || keyCode == KeyEvent.VK_CAPS_LOCK || keyCode == KeyEvent.VK_CONTROL || keyCode == KeyEvent.VK_ALT) {
				// Somewhere there should be filtering chars or something similar
			} else {
				MP.typedInTextField(e.getKeyChar());
			}
		}
		
		Debugger.setPressedKey(KeyEvent.getKeyText(keyCode));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Debugger.setPressedKey(" ");
	}

}
