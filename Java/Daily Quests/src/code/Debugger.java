package code;

import java.awt.Color;
import java.awt.Graphics2D;

public class Debugger {
	
	public static boolean debugger = false;
	
	public static void setDebugger(boolean var) {
		debugger = var;
	}
	
	public static boolean getDebugger() {
		return debugger;
	}
	
	public static void draw(int FPS, Graphics2D g2D) {
		g2D.setColor(Color.WHITE);
		g2D.drawString(FPS + " FPS", 10, 20);
	}
}
