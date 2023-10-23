package ui;

//import static main.GameStates.SETTINGS;
//import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import helperMethods.Constants.Monkeys;
import objects.Monkey;
import scenes.Playing;

public class ActionBar extends Bar{
	
	private Playing playing;
	
	private MyButton[] monkeyButtons;
	private Monkey selectedMonkey;
	private Monkey displayedMonkey;
	
	public ActionBar(int x, int y , int width , int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		
		initButtons();
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
		
	}

	public void draw(Graphics g) {
		
		g.setColor(new Color(122,89,76));
		g.fillRect(x, y, width, height);
		
		drawButtons(g);
		
		drawDisplayedMonkey(g);
		
	}

	private void drawDisplayedMonkey(Graphics g) {
		
		if(displayedMonkey != null) {
			g.setColor(Color.gray);
			g.fillRect(410, 645, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410, 645, 220, 85);
			g.drawRect(420, 650, 50, 50);
			g.drawImage(playing.getMonkeyManager().getMonkeyImgs()[displayedMonkey.getMonkeyType()], 420, 653, 50, 50, null);
			g.setColor(Color.black);
			g.setFont(new Font("Rockwell", Font.BOLD, 15));
			g.drawString("" + Monkeys.GetName(displayedMonkey.getMonkeyType()), 490, 662);
			g.drawString("ID: " + displayedMonkey.getId(), 490, 677);

			drawDisplayedMonkeyBorder(g);
			drawDisplayedMonkeyRange(g);
		}
		
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
			g.drawImage(playing.getMonkeyManager().getMonkeyImgs()[b.getId()], b.x, b.y, b.width, b.height, null);
			drawButtonFeedbacks(g,b);
		
		}
	}
	
	public void displayMonkey(Monkey m) {
		displayedMonkey = m;
	}

	public void mouseClicked(int x, int y) {
		for(MyButton b : monkeyButtons) {
			if(b.getBounds().contains(x, y)) {
				selectedMonkey = new Monkey(0,0,-1,b.getId());
				playing.setSelectedMonkey(selectedMonkey);
				return;
			}
		}
	}

	public void mouseMoved(int x, int y) {
		for(MyButton b : monkeyButtons)
			b.setMouseOver(false);
		
		for(MyButton b : monkeyButtons) {
			if (b.getBounds().contains(x, y)) {
				b.setMouseOver(true);
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
		
	}

	public void mouseReleased(int x, int y) {
		for(MyButton b : monkeyButtons)
			b.resetBooleans();
	}
	

	}

	

