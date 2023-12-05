package Bloons;

import java.awt.Rectangle;

import managers.BloonManager;

import static helperMethods.Constants.Direction.*;

public abstract class Bloon {

	protected BloonManager bloonManager;
	protected float x, y;
	protected Rectangle bounds;
	protected int health;
	protected int ID;
	protected int bloonType;
	protected int lastDir;
	protected boolean alive = true;
	protected int slowTickLimit = 60 * 4, slowTick = slowTickLimit;
	
	
	public Bloon(float x, float y, int ID, int bloonType, BloonManager bloonManager) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.bloonType = bloonType;
		this.bloonManager = bloonManager;
		bounds = new Rectangle((int) x , (int) y, 32 ,32);
		lastDir = -1;
		setStartHealth(bloonType);
	}
	
	private void setStartHealth(int bloonType) {
		health = helperMethods.Constants.Bloons.GetStartHealth(bloonType);
	}
	
	public void hurt(int dmg) {
		this.health -= dmg;
		if(health <= 0) {
			alive = false;
			bloonManager.rewardPlayer(bloonType);
		}
	}
	
	public void kill() {
		//Para estourar o balao QUANDO CHEGA NO FINAL
		alive = false;
		health = 0;
		
	}
	
	public void slow() {
		slowTick = 0;
	}
	
	public void move(float speed, int dir) {
		lastDir = dir;
		
		if(slowTick < slowTickLimit) {
			slowTick ++;
			speed *= 0.4f;
		}
		
		switch(dir) {
		case LEFT:
			this.x -= speed;
			break;
		case UP:
			this.y -= speed;
			break;
		case RIGHT:
			this.x += speed;
			break;
		case DOWN:
			this.y += speed;
			break;
		}
		
		updateHitbox();
	}
	
	private void updateHitbox() {
		bounds.x = (int) x;
		bounds.y = (int) y;
	}

	public void setPos(int x, int y) {
		//Não usar isso para mover, isso é para dar fix na posição
		this.x = x;
		this.y = y;
		
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getHealth() {
		return health;
	}

	public int getID() {
		return ID;
	}

	public int getBloonType() {
		return bloonType;
	}
	
	public int getLastDir() {
		return lastDir;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public boolean isSlowed() {
		return slowTick < slowTickLimit;
	}
}
