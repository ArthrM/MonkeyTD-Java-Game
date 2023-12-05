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
	private BufferedImage[][] monkeyImgs;
	private ArrayList<Monkey> monkeys = new ArrayList<>();
	private int monkeyAmount = 0;
	
	
	public MonkeyManager(Playing playing) {
		this.playing = playing;
		
		loadMonkeyImgs();
		
	}
	

	private void loadMonkeyImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		monkeyImgs = new BufferedImage[6][3];
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 3; j++) {
				monkeyImgs[i][j] = atlas.getSubimage(j * 32, (0 + i) * 32, 32, 32);
			}
		}
		
	}
	
	public void addMonkey(Monkey selectedMonkey, int xPos, int yPos) {
		monkeys.add(new Monkey(xPos, yPos, monkeyAmount++, selectedMonkey.getMonkeyType()));
		
	}
	
	
	public void upgradeMonkey(Monkey displayedMonkey) {
		for(Monkey m : monkeys)
			if(m.getId() == displayedMonkey.getId())
				m.upgradeMonkey();
		
	}
	
	public void removeMonkey(Monkey displayedMonkey) {
		for(int i = 0; i < monkeys.size(); i++)
			if(monkeys.get(i).getId() == displayedMonkey.getId())
				monkeys.remove(i);
	}

	
	public void update() {
		for(Monkey m : monkeys) {
			m.update();
			attackBloonIfClose(m);
		}
	}
	
	private void attackBloonIfClose(Monkey m) {
		
			for(Bloon bl : playing.getBloonManager().getBloons()) {
				if(bl.isAlive()) {
					if(isBloonInRange(m, bl)) {
						if(m.isCooldownOver()) {
							playing.shootBloon(m, bl);
							m.resetCooldown();
						}
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
			g.drawImage(monkeyImgs[m.getMonkeyType()][m.getTier()], m.getX(), m.getY(), null);
		
	}
	
	public Monkey getMonkeyAt(int x, int y) {
		
		for(Monkey m : monkeys)
			if(m.getX() == x)
				if(m.getY() == y)
					return m;
		
		return null;
	}
	
	public BufferedImage[][] getMonkeyImgs() {
		return monkeyImgs;
	}

	public void reset() {
		monkeys.clear();
		monkeyAmount = 0;
	}
	
}
