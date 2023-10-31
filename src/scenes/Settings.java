package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods {

	private MyButton bMainMenu;
	
	public Settings(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {
		bMainMenu = new MyButton("Save & Return", 280, 500, 110, 25);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 640, 640);
		drawButtons(g);
		
	}
	
	public void drawButtons(Graphics g) {
		g.setFont(new Font("Rockwell", Font.PLAIN, 15));
		bMainMenu.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bMainMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
		}
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMainMenu.setMouseOver(false);
		
		if(bMainMenu.getBounds().contains(x, y)) {
			bMainMenu.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(bMainMenu.getBounds().contains(x, y)) {
			bMainMenu.setMousePressed(true);
		}
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
		
	}


	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	private void resetButtons() {
		bMainMenu.resetBooleans();
		
	}

}
