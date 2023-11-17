package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods {

	private MyButton bMainMenu;
	private MyButton bExtra;
	private MyButton bTimer;
	private MyButton bPersona;
	private MyButton bCredits;
	
	public Settings(Game game) {
		super(game);
		initButtons();
	///	creditScreen = new CreditScreen(null);
	}

	private void initButtons() {
		bMainMenu = new MyButton("Save & Return", 10, 10, 150, 50);
		bExtra = new MyButton("Tutorial", 245, 150, 150, 50);
	 	bTimer = new MyButton("Tempo", 245, 250, 150, 50);
		bPersona = new MyButton("Personagens", 245, 350, 150, 50);
		bCredits = new MyButton("Créditos", 245, 450, 150, 50);	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 640, 780);
		drawButtons(g);
		drawText(g);
	}

	public void drawText(Graphics g) {
        // Draw the credits text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Options", 285, 95);
		g.drawString("__________________", 225, 115);
	}
	
	public void drawButtons(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		bMainMenu.draw(g);
		bExtra.draw(g);
		bTimer.draw(g);
		bPersona.draw(g);
		bCredits.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bMainMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
		} else if(bCredits.getBounds().contains(x,y)){
			SetGameState(CREDITS);
			game.getCredits();
		}
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMainMenu.setMouseOver(false);
		bExtra.setMouseOver(false);
		bTimer.setMouseOver(false);
		bPersona.setMouseOver(false);
		bCredits.setMouseOver(false);
		
		if(bMainMenu.getBounds().contains(x, y)) {
			bMainMenu.setMouseOver(true);
		} else if(bExtra.getBounds().contains(x, y)) {
			bExtra.setMouseOver(true);
		} else if(bTimer.getBounds().contains(x, y)) {
			bTimer.setMouseOver(true);
		} else if(bPersona.getBounds().contains(x,y)){
			bPersona.setMouseOver(true);
		} else if(bCredits.getBounds().contains(x,y)){
			bCredits.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(bMainMenu.getBounds().contains(x, y)) {
			bMainMenu.setMousePressed(true);
		} else if(bExtra.getBounds().contains(x,y)){
			bExtra.setMousePressed(true);
		} else if(bTimer.getBounds().contains(x,y)){
			bTimer.setMousePressed(true);
		} else if(bPersona.getBounds().contains(x,y)){
			bPersona.setMousePressed(true);
		} else if(bCredits.getBounds().contains(x,y)){
			bCredits.setMousePressed(true);
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
		bExtra.resetBooleans();
		bTimer.resetBooleans();
		bPersona.resetBooleans();
		bCredits.resetBooleans();
	}

}
