package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Bloons.Bloon;
import helperMethods.LoadSave;
import objects.Monkey;
import scenes.Playing;

import static helperMethods.Constants.Monkeys.*;

public class MonkeyManager {

	private Playing playing;
	private BufferedImage[] monkeyImgs;
	private ArrayList<Monkey> monkeys = new ArrayList<>();
	private int monkeyAmount = 0;
	
	
	public MonkeyManager(Playing playing) {
		this.playing = playing;
		
		loadMonkeyImgs();
		
	}
	

	private void loadMonkeyImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		monkeyImgs = new BufferedImage[6];
		for(int i = 0; i < 6; i++) {
			monkeyImgs[i] = atlas.getSubimage(0 * 32, (0 + i ) * 32, 32, 32);
			
		}
		
	}
	
	public void addMonkey(Monkey selectedMonkey, int xPos, int yPos) {
		monkeys.add(new Monkey(xPos, yPos, monkeyAmount++, selectedMonkey.getMonkeyType()));
		
	}
	
	public void update() {
		attackBloonIfClose();
		
	}
	
	private void attackBloonIfClose() {
		for(Monkey m : monkeys) {
			for(Bloon bl : playing.getBloonManager().getBloons()) {
				if(bl.isAlive())
					if(isBloonInRange(m, bl)) {
						bl.hurt(1);
					} else {
					
					}
			}
		}
		
	}


	private boolean isBloonInRange(Monkey m, Bloon bl) {
		
		int range = helperMethods.UtilMethods.GetHypoDistance(m.getX(), m.getY(), bl.getX(), bl.getY());
		
		return range < m.getRange();
		
	}


	public void draw(Graphics g) {
		
		for(Monkey m : monkeys)
			g.drawImage(monkeyImgs[m.getMonkeyType()], m.getX(), m.getY(), null);
		
	}
	
	public Monkey getMonkeyAt(int x, int y) {
		
		for(Monkey m : monkeys)
			if(m.getX() == x)
				if(m.getY() == y)
					return m;
		
		return null;
	}
	
	public BufferedImage[] getMonkeyImgs() {
		return monkeyImgs;
	}

	
}
