package handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Engine;
import main.UtilityTool;
import states.CharacterCreator;

public class MouseHandler implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (Engine.gameState == Engine.characterCreatorState) {
				// Check collisions on characterCreator
				if (UtilityTool.mouseOnRectangle(CharacterCreator.hairLeftBtn)) {
					CharacterCreator.moveHairLeft();
				}
				if (UtilityTool.mouseOnRectangle(CharacterCreator.hairRightBtn)) {
					CharacterCreator.moveHairRight();
				}
				if (UtilityTool.mouseOnRectangle(CharacterCreator.eyesLeftBtn)) {
					CharacterCreator.moveEyesLeft();
				}
				if (UtilityTool.mouseOnRectangle(CharacterCreator.eyesRightBtn)) {
					CharacterCreator.moveEyesRight();
				}
				if (UtilityTool.mouseOnRectangle(CharacterCreator.bodyLeftBtn)) {
					CharacterCreator.moveBodyLeft();
				}
				if (UtilityTool.mouseOnRectangle(CharacterCreator.bodyRightBtn)) {
					CharacterCreator.moveBodyRight();
				}
				if (UtilityTool.mouseOnRectangle(CharacterCreator.bottomLeftBtn)) {
					CharacterCreator.moveBottomLeft();
				}
				if (UtilityTool.mouseOnRectangle(CharacterCreator.bottomRightBtn)) {
					CharacterCreator.moveBottomRight();
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
