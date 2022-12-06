package states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Engine;

public class CharacterCreator {
	
	private static String userName;
	
	// Colors
	public static Color[] hairColors = {new Color(0, 0, 0), new Color(255,255,255), new Color(255, 0, 0)};
	public static Color[] eyeColors = {new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 0, 0)};
	public static Color[] bodyColors = {new Color(227, 209, 163), new Color(255,255,255), new Color(255, 0, 0)};
	public static Color[] bottomColors = {new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 0, 0)};
	
	// Character colors
	private static Color hairColor = hairColors[0];
	private static Color eyesColor = eyeColors[0];
	private static Color bodyColor = bodyColors[0];
	private static Color bottomColor = bottomColors[0];
	private static int characterActualHair = 0;
	private static int characterActualEyes = 0;
	private static int characterActualBody = 0;
	private static int characterActualBottom = 0;
	
	// Body
	private static int characterHeight = Engine.TILE_HEIGHT * 4;
	private static int characterWidth = Engine.TILE_WIDTH * 4;
	private static int characterX = (Engine.SCREEN_WIDTH / 2) - (Engine.TILE_WIDTH * 2);
	private static int characterY = (Engine.SCREEN_HEIGHT / 2) - (Engine.TILE_HEIGHT * 2);
	
	// Hair
	private static int hairHeight = Engine.TILE_HEIGHT;
	
	// Eyes
	private static int eyeHeight = Engine.TILE_HEIGHT;
	private static int eyeWidth = Engine.TILE_WIDTH;
	private static int eye1X = characterX + (Engine.TILE_WIDTH / 2);
	private static int eye2X = eye1X + (Engine.TILE_WIDTH * 2);
	private static int eyeY = characterY + (Engine.TILE_HEIGHT + (Engine.TILE_HEIGHT / 2));
	
	// Bottom
	private static int bottomY = characterY + (Engine.TILE_HEIGHT * 3);
	
	// Menu buttons
	public static Rectangle hairLeftBtn = new Rectangle(characterX - Engine.TILE_WIDTH, characterY, Engine.TILE_WIDTH, Engine.TILE_HEIGHT);
	public static BufferedImage LeftBtnOffImg, LeftBtnOnImg;
	public static boolean hairLeftBtnOnHover = false;
	
	public static Rectangle hairRightBtn = new Rectangle(characterX + characterWidth, characterY, Engine.TILE_WIDTH, Engine.TILE_HEIGHT);
	public static BufferedImage RightBtnOffImg, RightBtnOnImg;
	public static boolean hairRightBtnOnHover = false;
	
	public static Rectangle eyesLeftBtn = new Rectangle(characterX - Engine.TILE_WIDTH, characterY + Engine.TILE_HEIGHT, Engine.TILE_WIDTH, Engine.TILE_HEIGHT);
	public static boolean eyesLeftBtnOnHover = false;
	
	public static Rectangle eyesRightBtn = new Rectangle(characterX + characterWidth, characterY + Engine.TILE_HEIGHT, Engine.TILE_WIDTH, Engine.TILE_HEIGHT);
	public static boolean eyesRightBtnOnHover = false;
	
	public static Rectangle bodyLeftBtn = new Rectangle(characterX - Engine.TILE_WIDTH, characterY + (Engine.TILE_HEIGHT * 2), Engine.TILE_WIDTH, Engine.TILE_HEIGHT);
	public static boolean bodyLeftBtnOnHover = false;
	
	public static Rectangle bodyRightBtn = new Rectangle(characterX + characterWidth, characterY + (Engine.TILE_HEIGHT * 2), Engine.TILE_WIDTH, Engine.TILE_HEIGHT);
	public static boolean bodyRightBtnOnHover = false;
	
	public static Rectangle bottomLeftBtn = new Rectangle(characterX - Engine.TILE_WIDTH, characterY + (Engine.TILE_HEIGHT * 3), Engine.TILE_WIDTH, Engine.TILE_HEIGHT);
	public static boolean bottomLeftBtnOnHover = false;
	
	public static Rectangle bottomRightBtn = new Rectangle(characterX + characterWidth, characterY + (Engine.TILE_HEIGHT * 3), Engine.TILE_WIDTH, Engine.TILE_HEIGHT);
	public static boolean bottomRightBtnOnHover = false;
	
	// TextField
	public static Rectangle textField = new Rectangle(Engine.TILE_WIDTH * 8, Engine.SCREEN_HEIGHT - (Engine.TILE_HEIGHT * 2), Engine.SCREEN_WIDTH - (Engine.TILE_WIDTH * 16), Engine.TILE_HEIGHT);
	
	private static boolean isUp = false;
	
	public CharacterCreator() {
		try {
			LeftBtnOffImg = ImageIO.read(getClass().getResourceAsStream("/res/leftArrowOff.png"));
			LeftBtnOnImg = ImageIO.read(getClass().getResourceAsStream("/res/leftArrowOn.png"));
			RightBtnOffImg = ImageIO.read(getClass().getResourceAsStream("/res/rightArrowOff.png"));
			RightBtnOnImg = ImageIO.read(getClass().getResourceAsStream("/res/rightArrowOn.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isUp = true;
	}
	
	public static void moveHairLeft() {
		if (characterActualHair == 0) {
			characterActualHair = hairColors.length - 1;
		} else {
			characterActualHair--;
		}
		hairColor = hairColors[characterActualHair];
	}
	public static void moveHairRight() {
		if (characterActualHair == (hairColors.length - 1)) {
			characterActualHair = 0;
		} else {
			characterActualHair++;
		}
		hairColor = hairColors[characterActualHair];
	}
	public static void moveEyesLeft() {
		if (characterActualEyes == 0) {
			characterActualEyes = eyeColors.length - 1;
		} else {
			characterActualEyes--;
		}
		eyesColor = eyeColors[characterActualEyes];
	}
	public static void moveEyesRight() {
		if (characterActualEyes == (eyeColors.length - 1)) {
			characterActualEyes = 0;
		} else {
			characterActualEyes++;
		}
		eyesColor = eyeColors[characterActualEyes];
	}
	public static void moveBodyLeft() {
		if (characterActualBody == 0) {
			characterActualBody = bodyColors.length - 1;
		} else {
			characterActualBody--;
		}
		bodyColor = bodyColors[characterActualBody];
	}
	public static void moveBodyRight() {
		if (characterActualBody == (bodyColors.length - 1)) {
			characterActualBody = 0;
		} else {
			characterActualBody++;
		}
		bodyColor = bodyColors[characterActualBody];
	}
	public static void moveBottomLeft() {
		if (characterActualBottom == 0) {
			characterActualBottom = bottomColors.length - 1;
		} else {
			characterActualBottom--;
		}
		bottomColor = bottomColors[characterActualBottom];
	}
	public static void moveBottomRight() {
		if (characterActualBottom == (bottomColors.length - 1)) {
			characterActualBottom = 0;
		} else {
			characterActualBottom++;
		}
		bottomColor = bottomColors[characterActualBottom];
	}
	
	public static void draw(Graphics2D g2D) {
		if (!isUp) return;
		// TODO: Function for creating centered string for screen
		g2D.setColor(Color.WHITE);
		g2D.drawString("Character Creator", 100, 100);
		// Draw character
		g2D.setColor(new Color(44, 44, 44));
		g2D.fillOval(characterX - (Engine.TILE_WIDTH * 2), characterY - (Engine.TILE_HEIGHT * 2), characterWidth + (Engine.TILE_WIDTH * 4), characterHeight + (Engine.TILE_HEIGHT * 4));
		
		g2D.setStroke(new BasicStroke(4));
		// Body
		g2D.setColor(bodyColor);
		g2D.fillRect(characterX, characterY, characterWidth, characterHeight);
		g2D.setColor(Color.BLACK);
		g2D.drawRect(characterX, characterY, characterWidth, characterHeight);
		// Hair
		g2D.setColor(hairColor);
		g2D.fillRect(characterX, characterY, characterWidth, hairHeight);
		g2D.setColor(Color.BLACK);
		g2D.drawRect(characterX, characterY, characterWidth, hairHeight);
		// Eyes
		g2D.setColor(eyesColor);
		g2D.fillRect(eye1X, eyeY, eyeWidth, eyeHeight);
		g2D.fillRect(eye2X, eyeY, eyeWidth, eyeHeight);
		g2D.setColor(Color.BLACK);
		g2D.drawRect(eye1X, eyeY, eyeWidth, eyeHeight);
		g2D.drawRect(eye2X, eyeY, eyeWidth, eyeHeight);
		// Bottom
		g2D.setColor(bottomColor);
		g2D.fillRect(characterX, bottomY, characterWidth, hairHeight);
		g2D.setColor(Color.BLACK);
		g2D.drawRect(characterX, bottomY, characterWidth, hairHeight);
		g2D.drawLine(characterX + (Engine.TILE_WIDTH *2), characterY + (Engine.TILE_HEIGHT * 3) + (Engine.TILE_HEIGHT / 4), characterX +(Engine.TILE_WIDTH *2), characterY + (Engine.TILE_HEIGHT * 4));
		
		// Draw buttons
		if (hairLeftBtnOnHover) {
			g2D.drawImage(LeftBtnOnImg, hairLeftBtn.x, hairLeftBtn.y, hairLeftBtn.width, hairLeftBtn.height, null);
		} else {
			g2D.drawImage(LeftBtnOffImg, hairLeftBtn.x, hairLeftBtn.y, hairLeftBtn.width, hairLeftBtn.height, null);
		}
		if (hairRightBtnOnHover) {
			g2D.drawImage(RightBtnOnImg, hairRightBtn.x, hairRightBtn.y, hairRightBtn.width, hairRightBtn.height, null);
		} else {
			g2D.drawImage(RightBtnOffImg, hairRightBtn.x, hairRightBtn.y, hairRightBtn.width, hairRightBtn.height, null);
		}
		
		if (eyesLeftBtnOnHover) {
			g2D.drawImage(LeftBtnOnImg, eyesLeftBtn.x, eyesLeftBtn.y, eyesLeftBtn.width, eyesLeftBtn.height, null);
		} else {
			g2D.drawImage(LeftBtnOffImg, eyesLeftBtn.x, eyesLeftBtn.y, eyesLeftBtn.width, eyesLeftBtn.height, null);
		}
		if (eyesRightBtnOnHover) {
			g2D.drawImage(RightBtnOnImg, eyesRightBtn.x, eyesRightBtn.y, eyesRightBtn.width, eyesRightBtn.height, null);
		} else {
			g2D.drawImage(RightBtnOffImg, eyesRightBtn.x, eyesRightBtn.y, eyesRightBtn.width, eyesRightBtn.height, null);
		}
		
		if (bodyLeftBtnOnHover) {
			g2D.drawImage(LeftBtnOnImg, bodyLeftBtn.x, bodyLeftBtn.y, bodyLeftBtn.width, bodyLeftBtn.height, null);
		} else {
			g2D.drawImage(LeftBtnOffImg, bodyLeftBtn.x, bodyLeftBtn.y, bodyLeftBtn.width, bodyLeftBtn.height, null);
		}
		if (bodyRightBtnOnHover) {
			g2D.drawImage(RightBtnOnImg, bodyRightBtn.x, bodyRightBtn.y, bodyRightBtn.width, bodyRightBtn.height, null);
		} else {
			g2D.drawImage(RightBtnOffImg, bodyRightBtn.x, bodyRightBtn.y, bodyRightBtn.width, bodyRightBtn.height, null);
		}
		
		if (bottomLeftBtnOnHover) {
			g2D.drawImage(LeftBtnOnImg, bottomLeftBtn.x, bottomLeftBtn.y, bottomLeftBtn.width, bottomLeftBtn.height, null);
		} else {
			g2D.drawImage(LeftBtnOffImg, bottomLeftBtn.x, bottomLeftBtn.y, bottomLeftBtn.width, bottomLeftBtn.height, null);
		}
		if (bottomRightBtnOnHover) {
			g2D.drawImage(RightBtnOnImg, bottomRightBtn.x, bottomRightBtn.y, bottomRightBtn.width, bottomRightBtn.height, null);
		} else {
			g2D.drawImage(RightBtnOffImg, bottomRightBtn.x, bottomRightBtn.y, bottomRightBtn.width, bottomRightBtn.height, null);
		}
		// Draw textField
		
		g2D.setColor(new Color(44, 44, 44)); 
		g2D.fillOval(textField.x - (Engine.TILE_WIDTH * 4), textField.y - (Engine.TILE_HEIGHT / 2), textField.width + (Engine.TILE_WIDTH * 8), textField.height * 2);
		g2D.setColor(Color.WHITE);
		g2D.fillRect(textField.x, textField.y, textField.width, textField.height);
		g2D.setColor(Color.BLACK);
		g2D.drawRect(textField.x, textField.y, textField.width, textField.height);
	}
}
