package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods {

	private MyButton bMainMenu;
	private MyButton bBack2Playing;
	private MyButton bCredits;
	
	public Settings(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {
		bBack2Playing = new MyButton("Voltar ao jogo", 245, 250, 150, 50);
		bCredits = new MyButton("Créditos", 245, 350, 150, 50);
		bMainMenu = new MyButton("Save & Return", 245, 450, 150, 50);
	}

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
		bBack2Playing.draw(g);
		bCredits.draw(g);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.getPlaying().setGamePaused(false);
			SetGameState(PLAYING);
		}
	}
	
	@Override
	public void mouseClicked(int x, int y) {
		if(bMainMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
		} else if(bCredits.getBounds().contains(x, y)){
			SetGameState(CREDITS);
		} else if(bBack2Playing.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
			game.getPlaying().setGamePaused(false);
			
		}
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMainMenu.setMouseOver(false);
		bBack2Playing.setMouseOver(false);
		bCredits.setMouseOver(false);
		
		if(bMainMenu.getBounds().contains(x, y)) {
			bMainMenu.setMouseOver(true);
		} else if(bBack2Playing.getBounds().contains(x, y)) {
			bBack2Playing.setMouseOver(true);
		} else if(bCredits.getBounds().contains(x,y)){
			bCredits.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(bMainMenu.getBounds().contains(x, y)) {
			bMainMenu.setMousePressed(true);
		} else if(bBack2Playing.getBounds().contains(x,y)){
			bBack2Playing.setMousePressed(true);
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
		bBack2Playing.resetBooleans();
		bCredits.resetBooleans();
	}

}
