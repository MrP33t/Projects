package code;

import java.awt.Color;
import java.awt.Graphics2D;

public class Debugger {
	
	private static boolean debugger = false;
	private static String pressedKey = " ";
	private static String pressedMouseButton = " ";
	public static int MousePositionX = 0;
	public static int MousePositionY = 0;
	public static int scrollPosition = 0;
	
	
	public static void setDebugger(boolean var) {
		debugger = var;
	}
	
	public static boolean getDebugger() {
		return debugger;
	}
	
	public static void setPressedKey(String pK) {
		pressedKey = pK;
	}
	
	public static void setMousePosition(int x, int y) {
		MousePositionX = x;
		MousePositionY = y;
	}
	
	public static void setPressedMouseButton(String mB) {
		pressedMouseButton = mB;
	}
	
	public static void updateScrollPosition(int x) {
		scrollPosition = scrollPosition + x;
	}
	
	// Method for drawing debug UI
	public static void draw(int FPS, Graphics2D g2D) {
		g2D.setColor(Color.WHITE);
		g2D.drawString(FPS + " FPS", 10, 20);
		g2D.drawString("Pressed Key: " + pressedKey, 10, 50);
		g2D.drawString("Pressed Mouse Button: " + pressedMouseButton, 10, 80);
		g2D.drawString("Mouse position: " + MousePositionX + ", " + MousePositionY, 10, 110);
		g2D.drawString("Scroll position: " + scrollPosition, 10, 140);
	}
}
