package data;

import java.awt.Color;
import java.io.Serializable;

public class PlayerSkin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4754269264251500404L;
	
	private Color hairColor;
	private Color eyesColor;
	private Color bodyColor;
	private Color bottomColor;
	
	public PlayerSkin(Color hairColor, Color eyesColor, Color bodyColor, Color bottomColor) {
		this.hairColor = hairColor;
		this.eyesColor = eyesColor;
		this.bodyColor = bodyColor;
		this.bottomColor = bottomColor;
	}

}
