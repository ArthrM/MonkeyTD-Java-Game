package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class GameOver extends GameScene implements SceneMethods {

	private MyButton bReplay, bMenu;
	
	public GameOver(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;
		
		bReplay = new MyButton("Jogar Novamente", 245, 350, 150, 50);
		bMenu = new MyButton("Voltar ao Menu", 245, 450, 150, 50);
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 640, 780);
		
		g.setFont(new Font("Rockwell", Font.PLAIN, 15));
		bMenu.draw(g);
		bReplay.draw(g);
		
		g.setFont(new Font("LucidaSans", Font.BOLD, 50));
		if(game.getPlaying().isGameWon()) {
			g.setColor(Color.green);
			g.drawString("YOU WON", 200, 250);
		} else {
			g.setColor(Color.red);
			g.drawString("Game Over", 200, 250);
		}
		
	}
	
	private void replayGame() {
		resetAll();
		SetGameState(PLAYING);
	}
	
	private void resetAll() {
		game.getPlaying().resetAll();
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			resetAll();
			SetGameState(MENU);
		} else if(bReplay.getBounds().contains(x, y)) {
			replayGame();
		}
		
	}


	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bReplay.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		} else if(bReplay.getBounds().contains(x, y)) {
			bReplay.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		} else if(bReplay.getBounds().contains(x, y)) {
			bReplay.setMousePressed(true);
		}
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bReplay.resetBooleans();
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
