package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;
import main.GameStates;
import static main.GameStates.*;

public class KeyboardInputs implements KeyListener {

	private Game game;
	public KeyboardInputs(Game game) {
		this.game = game;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_1:
			GameStates.gameState = MENU;
			break;
		case KeyEvent.VK_2:
			GameStates.gameState = PLAYING;
			break;
		case KeyEvent.VK_3:
			GameStates.gameState = EDIT;
			break;
		case KeyEvent.VK_4:
			GameStates.gameState = SETTINGS;
			break;
			
		}
		
		if(GameStates.gameState == EDIT)
			game.getEditor().keyPressed(e);
		else if(GameStates.gameState == PLAYING)
			game.getPlaying().keyPressed(e);
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

}
