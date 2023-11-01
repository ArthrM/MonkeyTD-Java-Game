package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods {

	private MyButton bMainMenu;
	private MyButton extra;
	private MyButton timer;
	private MyButton pearson;
	private MyButton credit;
	
	public Settings(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {
		bMainMenu = new MyButton("Save & Return", 10, 10, 150, 50);
		extra = new MyButton("Tutorial", 245, 150, 150, 50);
	 	timer = new MyButton("Tempo", 245, 250, 150, 50);
		pearson = new MyButton("Personagens", 245, 350, 150, 50);
		credit = new MyButton("Créditos", 245, 450, 150, 50);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 640, 640);
		drawButtons(g);
		
	}
	
	public void drawButtons(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		bMainMenu.draw(g);
		extra.draw(g);
		timer.draw(g);
		pearson.draw(g);
		credit.draw(g);
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
		extra.setMouseOver(false);
		timer.setMouseOver(false);
		pearson.setMouseOver(false);
		credit.setMouseOver(false);
		
		if(bMainMenu.getBounds().contains(x, y)) {
			bMainMenu.setMouseOver(true);
		} else if(extra.getBounds().contains(x, y)) {
			extra.setMouseOver(true);
		} else if(timer.getBounds().contains(x, y)) {
			timer.setMouseOver(true);
		} else if(pearson.getBounds().contains(x,y)){
			pearson.setMouseOver(true);
		} else if(credit.getBounds().contains(x,y)){
			credit.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(bMainMenu.getBounds().contains(x, y)) {
			bMainMenu.setMousePressed(true);
		} else if(extra.getBounds().contains(x,y)){
			extra.setMousePressed(true);
		} else if(timer.getBounds().contains(x,y)){
			timer.setMousePressed(true);
		} else if(pearson.getBounds().contains(x,y)){
			pearson.setMousePressed(true);
		} else if(credit.getBounds().contains(x,y)){
			credit.setMousePressed(true);
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
		extra.resetBooleans();
		timer.resetBooleans();
		pearson.resetBooleans();
		credit.resetBooleans();
	}

}
