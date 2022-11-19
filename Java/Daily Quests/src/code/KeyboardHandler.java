package code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch (keyCode) {
		case KeyEvent.VK_F2:
			if (Debugger.getDebugger()) {
				Debugger.setDebugger(false);
			} else {
				Debugger.setDebugger(true);
			}
			break;
		}
		
		Debugger.setPressedKey(KeyEvent.getKeyText(keyCode));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Debugger.setPressedKey(" ");
	}

}
