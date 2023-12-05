package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;
import main.GameStates;

public class MouseInputs implements MouseListener,MouseMotionListener {

	private Game game;
	
	public MouseInputs(Game game) {
		this.game = game;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		switch(GameStates.gameState) {
		case MENU:
			game.getMenu().mouseDragged(e.getX(),e.getY());
			break;
		case PLAYING:
			game.getPlaying().mouseDragged(e.getX(),e.getY());
			break;
		case SETTINGS:
			game.getSettings().mouseDragged(e.getX(),e.getY());
			break;
		case EDIT:
			game.getEditor().mouseDragged(e.getX(),e.getY());
			break;
		case CREDITS: 
			game.getCredits().mouseDragged(e.getX(),e.getY());
			break;
		case GAMEOVER:
			game.getGameOver().mouseDragged(e.getX(), e.getY());
			break;
		default:
			break;
		
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		switch(GameStates.gameState) {
		case MENU:
			game.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlaying().mouseMoved(e.getX(), e.getY());
			break;
		case EDIT:
			game.getEditor().mouseMoved(e.getX(), e.getY());
			break;
		case SETTINGS:
			game.getSettings().mouseMoved(e.getX(), e.getY());
			break;
		case CREDITS: 
			game.getCredits().mouseMoved(e.getX(), e.getY());
			break;
		case GAMEOVER:
			game.getGameOver().mouseMoved(e.getX(), e.getY());
			break;
		default:
			break;
		
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			
			switch(GameStates.gameState) {
			case MENU:
				game.getMenu().mouseClicked(e.getX(), e.getY());
				break;
			case PLAYING:
			   	game.getPlaying().mouseClicked(e.getX(), e.getY());
				break;
			case EDIT:
				game.getEditor().mouseClicked(e.getX(), e.getY());
				break;
			case SETTINGS:
				game.getSettings().mouseClicked(e.getX(), e.getY());
				break;
			case CREDITS:
				game.getCredits().mouseClicked(e.getX(), e.getY());
				break;
			case GAMEOVER:
				game.getGameOver().mouseClicked(e.getX(), e.getY());
				break;
			default:
				break;
			
			}
		}
		
		if(e.getButton() == MouseEvent.BUTTON3) {
			game.getPlaying().mouse2Clicked(e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		switch(GameStates.gameState) {
		case MENU:
			game.getMenu().mousePressed(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlaying().mousePressed(e.getX(), e.getY());
			break;
		case EDIT:
			game.getEditor().mousePressed(e.getX(), e.getY());
			break;
		case SETTINGS:
			game.getSettings().mousePressed(e.getX(), e.getY());
			break;
		case CREDITS:
			game.getCredits().mousePressed(e.getX(), e.getY());
			break;
		case GAMEOVER:
			game.getGameOver().mousePressed(e.getX(), e.getY());
			break;
		default:
			break;
		
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch(GameStates.gameState) {
		case MENU:
			game.getMenu().mouseReleased(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlaying().mouseReleased(e.getX(), e.getY());
			break;
		case EDIT:
			game.getEditor().mouseReleased(e.getX(), e.getY());
			break;
		case SETTINGS:
			game.getSettings().mouseReleased(e.getX(), e.getY());
			break;
		case CREDITS:
			game.getCredits().mouseReleased(e.getX(), e.getY());
			break;
		case GAMEOVER:
			game.getGameOver().mouseReleased(e.getX(), e.getY());
			break;
		default:
			break;
		
		}
		
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
