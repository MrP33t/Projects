package main;

import java.awt.Rectangle;

public class UtilityTool {
	
	public static boolean mouseOnRectangle(Rectangle r) {
		if (Engine.mouseLocation != null) {
			if (Engine.mouseLocation.x >= r.x && Engine.mouseLocation.x <= (r.x + r.width)
				&& Engine.mouseLocation.y >= r.y && Engine.mouseLocation.y <= (r.y + r.height)) {
				return true;
			}
		}
		return false;
	}
}
