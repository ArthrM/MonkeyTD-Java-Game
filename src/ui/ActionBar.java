package ui;

import static main.GameStates.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import helperMethods.Constants.Monkeys;
import objects.Monkey;
import scenes.Playing;

public class ActionBar extends Bar{
	
	private Playing playing;
	
	private MyButton[] monkeyButtons;
	private MyButton sellMonkey, upgradeMonkey;
	private Monkey selectedMonkey;
	private Monkey displayedMonkey;
	
	private DecimalFormat formater;
	
	private int gold = 5000;
	private boolean showMonkeyCost;
	private int monkeyCostType;
	
	private int lives = 25;
	
	public ActionBar(int x, int y , int width , int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		formater = new DecimalFormat("0.0");
		
		initButtons();
	}
	
	public void resetAll() {
		lives = 25;
		monkeyCostType = 0;
		showMonkeyCost = false;
		gold = 125;
		selectedMonkey = null;
		displayedMonkey = null;
	}
	
	private void initButtons() {
			
		monkeyButtons = new MyButton[6];
		int w = 50;
		int h = 50;
		int xStart = 12;
		int yStart = 650;
		int xOffset = (int) (w * 1.12f);
		
		for(int i = 0; i < monkeyButtons.length; i++) {
			monkeyButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);
		}
		
		sellMonkey = new MyButton("Vender", 421, 702, 80, 25);
		upgradeMonkey = new MyButton("Upgrade", 543, 702, 80, 25);
	}

	public void draw(Graphics g) {
		
		if(displayedMonkey != null)
			drawDisplayedMonkeyRange(g);
		
		g.setColor(new Color(122,89,76));
		g.fillRect(x, y, width, height);
		
		drawDisplayedMonkey(g);
		
		drawButtons(g);
		
		drawWaveInfo(g);
		
		if(showMonkeyCost)
			drawMonkeyCost(g);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString("$ " + gold, 96, 25);
		g.drawString("♥ " + lives, 10, 25);
	}

	public void removeOneLife() {
		lives--;
		if(lives <= 0) {
			playing.setGameWinState(false);
			SetGameState(GAMEOVER);
		}
	}
	
	private void drawMonkeyCost(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(280, 710, 120, 50);
		g.setColor(Color.black);
		g.drawRect(280, 710, 120, 50);
		g.drawString("" + getMonkeyCostName(), 285, 725);
		g.drawString("Cost: " + getMonkeyCostGold() + "g", 285, 745);
		
	}

	private int getMonkeyCostGold() {
		return helperMethods.Constants.Monkeys.GetMonkeyCost(monkeyCostType);
	}

	private String getMonkeyCostName() {
		return helperMethods.Constants.Monkeys.GetName(monkeyCostType);
	}

	private void drawWaveInfo(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Rockwell", Font.BOLD, 13));
		
		drawWaveTimerInfo(g);
		drawWavesLeftInfo(g);
		
	}
	
	private void drawWavesLeftInfo(Graphics g) {
		int current = playing.getWaveManager().getWaveIndex();
		int size = playing.getWaveManager().getWaves().size();
		g.drawString("Wave " + (current + 1) + " / " + size, 475, 762);
		
	}

	private void drawWaveTimerInfo(Graphics g) {
		if(playing.getWaveManager().isWaveTimerStarted()) {
			float timeLeft = playing.getWaveManager().getTimeLeft();
			String formatedText = formater.format(timeLeft);
			g.drawString("Próxima ronda em " + formatedText + " segundos", 415, 745);
		}
	}

	private void drawDisplayedMonkey(Graphics g) {
		
		if(displayedMonkey != null) {
			g.setColor(Color.gray);
			g.fillRect(410, 645, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410, 645, 220, 85);
			g.drawRect(420, 650, 50, 50);
			g.drawImage(playing.getMonkeyManager().getMonkeyImgs()[displayedMonkey.getMonkeyType()][displayedMonkey.getTier()], 420, 653, 50, 50, null);
			g.setColor(Color.black);
			g.setFont(new Font("Rockwell", Font.BOLD, 15));
			g.drawString("" + Monkeys.GetName(displayedMonkey.getMonkeyType()), 475, 662);
			g.drawString("ID " + displayedMonkey.getId(), 475, 677);
			g.drawString("LvL " + (displayedMonkey.getTier() + 1), 575, 662);
			drawDisplayedMonkeyBorder(g);
			
			sellMonkey.draw(g);
			if (displayedMonkey.getTier() < 2)
				upgradeMonkey.draw(g);
			
			if(sellMonkey.isMouseOver()) {
				g.drawString("Vender por " + getSellAmount(displayedMonkey) + "$" , 490, 695);
			} else if(upgradeMonkey.isMouseOver() && displayedMonkey.getTier() < 2) {
				g.drawString("Upgrade por " + getUpgradeAmount(displayedMonkey) + "$" , 490, 695);

			}
		}
		
	}

	private int getUpgradeAmount(Monkey displayedMonkey) {
		return (int)(helperMethods.Constants.Monkeys.GetMonkeyCost(displayedMonkey.getMonkeyType()) * 1.30f);
	}

	private int getSellAmount(Monkey displayedMonkey) {
		
		int anyUpgradeCost = (int)((displayedMonkey.getTier() * getUpgradeAmount(displayedMonkey) ) * 0.4f);
		int monkeySellCost = (int)(helperMethods.Constants.Monkeys.GetMonkeyCost(displayedMonkey.getMonkeyType()) * 0.4f);
		
		return monkeySellCost + anyUpgradeCost;
	}

	private void drawDisplayedMonkeyRange(Graphics g) {
		g.setColor(Color.lightGray);
		g.drawOval(displayedMonkey.getX() + 16 - (int)(displayedMonkey.getRange() * 2) / 2,
				displayedMonkey.getY() + 16 - (int)(displayedMonkey.getRange() * 2) / 2, 
				(int)displayedMonkey.getRange() * 2, 
				(int)displayedMonkey.getRange() * 2);
		
	}

	private void drawDisplayedMonkeyBorder(Graphics g) {
		g.setColor(Color.cyan);
		g.drawRect(displayedMonkey.getX(), displayedMonkey.getY(), 32, 32);
		
	}

	private void drawButtons(Graphics g) {
		
		for(MyButton b : monkeyButtons) {
			g.setColor(Color.GRAY);
			g.fillRect(b.x, b.y, b.width, b.height);
			g.drawImage(playing.getMonkeyManager().getMonkeyImgs()[b.getId()][0], b.x, b.y, b.width, b.height, null);
			drawButtonFeedbacks(g,b);
		
		}
	}
	
	public void displayMonkey(Monkey m) {
		displayedMonkey = m;
	}
	
	private boolean isGoldEnoughForMonkey(int monkeyType) {
		return gold >= helperMethods.Constants.Monkeys.GetMonkeyCost(monkeyType);
	}
	
	private void sellMonkeyClicked() {
		playing.removeMonkey(displayedMonkey);
		gold += getSellAmount(displayedMonkey);
		displayedMonkey = null;
		
	}
	
	private void upgradeMonkeyClicked() {
		playing.upgradeMonkey(displayedMonkey);
		gold -= getUpgradeAmount(displayedMonkey);
		
	}

	public void mouseClicked(int x, int y) {
		
		if(displayedMonkey != null) {
			if(sellMonkey.getBounds().contains(x, y)) {
				sellMonkeyClicked();
				return;
			} else if (upgradeMonkey.getBounds().contains(x, y) && displayedMonkey.getTier() < 2 && gold >= getUpgradeAmount(displayedMonkey)) {
				upgradeMonkeyClicked();
				return;
			}
		}
		
		for(MyButton b : monkeyButtons) {
			if(b.getBounds().contains(x, y)) {
				if(!isGoldEnoughForMonkey(b.getId()))
					return;
				selectedMonkey = new Monkey(0,0,-1,b.getId());
				playing.setSelectedMonkey(selectedMonkey);
				return;
			}
		}
	}



	public void mouseMoved(int x, int y) {
		showMonkeyCost = false;
		sellMonkey.setMouseOver(false);
		upgradeMonkey.setMouseOver(false);
		
		for(MyButton b : monkeyButtons)
			b.setMouseOver(false);
		
		if(displayedMonkey != null) {
			if(sellMonkey.getBounds().contains(x, y)) {
				sellMonkey.setMouseOver(true);
				return;
			} else if (upgradeMonkey.getBounds().contains(x, y) && displayedMonkey.getTier() < 3) {
				upgradeMonkey.setMouseOver(true);
				return;
			}
		}
		
		for(MyButton b : monkeyButtons) {
			if (b.getBounds().contains(x, y)) {
				b.setMouseOver(true);
				showMonkeyCost = true;
				monkeyCostType = b.getId();
				return;
			}
		}
		
	}

	public void mousePressed(int x, int y) {
		for(MyButton b : monkeyButtons) {
			if(b.getBounds().contains(x, y)) {
				b.setMousePressed(true);
				return;
			}
		}
		
		if(displayedMonkey != null) {
			if(sellMonkey.getBounds().contains(x, y)) {
				sellMonkey.setMousePressed(true);
				return;
			} else if (upgradeMonkey.getBounds().contains(x, y) && displayedMonkey.getTier() < 3) {
				upgradeMonkey.setMousePressed(true);
				return;
			}
			
		}
		
	}

	public void mouseReleased(int x, int y) {
		for(MyButton b : monkeyButtons)
			b.resetBooleans();
		sellMonkey.resetBooleans();
		upgradeMonkey.resetBooleans();
	}

	public void payForMonkey(int monkeyType) {
		int monkeyCost = helperMethods.Constants.Monkeys.GetMonkeyCost(monkeyType);
		if(this.gold >= monkeyCost)
			this.gold -= monkeyCost;
		
	}

	public void addGold(int getReward) {
		this.gold += getReward;
		
	}
	
	public int getLives() {
		return lives;
	}

}

	

