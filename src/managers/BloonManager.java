package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Bloons.Bloon;
import Bloons.BlueBloon;
import Bloons.GreenBloon;
import Bloons.RedBloon;
import Bloons.YellowBloon;
import helperMethods.LoadSave;
import objects.Monkey;
import objects.PathPoint;
import scenes.Playing;
import static helperMethods.Constants.Direction.*;
import static helperMethods.Constants.Tiles.*;
import static helperMethods.Constants.Bloons.*;

public class BloonManager {

	private Playing playing;
	private BufferedImage[] bloonImgs;
	private ArrayList<Bloon> bloons = new ArrayList<>();
	private PathPoint start, end;
	private BufferedImage slowEffect;
	
	public BloonManager(Playing playing, PathPoint start, PathPoint end) {
		this.playing = playing;
		bloonImgs = new BufferedImage[10];
		this.start = start;
		this.end = end;
		loadEffectImg();
		loadBloonImgs();
	}
	
	private void loadEffectImg() {
		slowEffect = LoadSave.getSpriteAtlas().getSubimage(32 * 2, 32 * 7, 32, 32);
	}

	private void loadBloonImgs() {
		
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		for (int i = 0; i < 10; i++) {
		bloonImgs[i] = atlas.getSubimage(32 * i, 32 * 6, 32, 32);
		}
	}

	public void update() {
	
		for (Bloon bl : bloons) {
			if(bl.isAlive())
				updateBloonMove(bl);	
		}

	}
	

	public void updateBloonMove(Bloon bl) {
		//Posição do bloon
		//Direção do bloon
		//Tile na próxima possível posição
		if(bl.getLastDir() == -1)
			setNewDirectionAndMove(bl);
		
		int newX = (int)(bl.getX() + getSpeedAndWidth(bl.getLastDir(), bl.getBloonType()));
		int newY = (int)(bl.getY() + getSpeedAndHeight(bl.getLastDir(), bl.getBloonType()));
		
		if(getTileType(newX,newY) == ROAD_TILE) {
			//Continua movendo na mesma direção
			bl.move(GetSpeed(bl.getBloonType()), bl.getLastDir());
		}else if(isAtEnd(bl)) {
			bl.kill();
			playing.removeOneLife();
		}else {
			//Acha nova direção
			setNewDirectionAndMove(bl);
		}
		
	}
	
	private void setNewDirectionAndMove(Bloon bl) {
		int dir = bl.getLastDir();
		
		//Mover TOTALMENTE para o tile atual
		int xCord = (int)(bl.getX() / 32);
		int yCord = (int)(bl.getY() / 32);
		
		fixBloonOffsetTile(bl, dir, xCord, yCord);
		
		if(isAtEnd(bl))
			return;
		
		if(dir == LEFT || dir == RIGHT) {
			int newY = (int)(bl.getY() + getSpeedAndHeight(UP, bl.getBloonType()));
			if(getTileType((int)bl.getX(), newY) == ROAD_TILE)
				bl.move(GetSpeed(bl.getBloonType()), UP);
			else
				bl.move(GetSpeed(bl.getBloonType()), DOWN);
		} else {
			int newX = (int)(bl.getX() + getSpeedAndWidth(RIGHT, bl.getBloonType()));
			if(getTileType(newX, (int)bl.getY()) == ROAD_TILE) 
				bl.move(GetSpeed(bl.getBloonType()), RIGHT);
			else
				bl.move(GetSpeed(bl.getBloonType()), LEFT);
		}
		
	}

	private void fixBloonOffsetTile(Bloon bl, int dir, int xCord, int yCord) {
		
		switch(dir) {

		case RIGHT:
			if(xCord < 19)
				xCord++;
			break;
		case DOWN:
			if(yCord < 19)
				yCord++;
			break;
		}
		
		bl.setPos(xCord * 32, yCord * 32);
		
	}

	private boolean isAtEnd(Bloon bl) {
		if(bl.getX() == end.getxCord() * 32)
			if(bl.getY() == end.getyCord() * 32)
				return true;
		
		return false;
	}

	private int getTileType(int x, int y) {
		return playing.getTileType(x,y);
	}
	
	private float getSpeedAndHeight(int dir, int bloonType) {
		
		if(dir == UP)
			return - GetSpeed(bloonType);
		else if(dir == DOWN)
			return GetSpeed(bloonType) + 32; //tamanho do sprite do bloon
		
		return 0;
	}

	private float getSpeedAndWidth(int dir, int bloonType) {
		
		if(dir == LEFT)
			return -GetSpeed(bloonType);
		else if(dir == RIGHT)
			return GetSpeed(bloonType) + 32; //tamanho do sprite do bloon
		
		return 0;
	}

	public void spawnBloon(int nextBloon) {
		addBloon(nextBloon);
	}
	
	
	public void addBloon(int bloonType) {
		
		int x = start.getxCord() * 32;
		int y = start.getyCord() * 32;
		
		switch(bloonType) {
		case RED_BL:
			bloons.add(new RedBloon(x, y, 0, this));
			break;
		case BLUE_BL:
			bloons.add(new BlueBloon(x, y, 0, this));
			break;
		case GREEN_BL:
			bloons.add(new GreenBloon(x, y, 0, this));
			break;
		case YELLOW_BL:
			bloons.add(new YellowBloon(x, y, 0, this));
			break;
		}
	}
	
	public void draw(Graphics g) {
		for(Bloon bl : bloons)
			if(bl.isAlive()) {
				drawBloon(bl, g);
				drawEffects(bl, g);
			}
	}

	private void drawEffects(Bloon bl, Graphics g) {
		if(bl.isSlowed())
			g.drawImage(slowEffect, (int) bl.getX(), (int) bl.getY(), null);
		
	}

	private void drawBloon(Bloon bl, Graphics g) {
		g.drawImage(bloonImgs[bl.getBloonType()], (int)bl.getX(), (int)bl.getY(), null);
		
	}
	
	public ArrayList<Bloon> getBloons() {
		return bloons;
	}

	public void rewardPlayer(int bloonType) {
		playing.rewardPlayer(bloonType);
		
	}

	public void reset() {
		bloons.clear();
	}

}
