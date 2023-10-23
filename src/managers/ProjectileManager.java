package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Bloons.Bloon;
import helperMethods.LoadSave;
import helperMethods.Constants.Tiles;
import objects.Monkey;
import objects.Projectile;
import scenes.Playing;
import static helperMethods.Constants.Monkeys.*;
import static helperMethods.Constants.Projectiles.*;

public class ProjectileManager {

	private Playing playing;
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private BufferedImage[] proj_imgs;
	private int proj_id = 0;
	
	
	public ProjectileManager(Playing playing) {
		this.playing = playing;
		importImgs();
	}
	
	private void importImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		proj_imgs = new BufferedImage[8];
		for(int i = 0; i < 8; i++)
			proj_imgs[i] = atlas.getSubimage(i * 32, 8 * 32, 32, 32);
	}
	
	
	public void newProjecile(Monkey m, Bloon bl) {
		int type = getProjType(m);
		
		int xDist = (int) Math.abs(m.getX() - bl.getX());
		int yDist = (int) Math.abs(m.getY() - bl.getY());
		int totDist = xDist + yDist;
		
		float xPer = (float) xDist / totDist;
		
		float xSpeed = xPer * helperMethods.Constants.Projectiles.GetSpeed(type);
		float ySpeed = helperMethods.Constants.Projectiles.GetSpeed(type) - xSpeed;
		
		if(m.getX() > bl.getX())
			xSpeed *= -1;
		if(m.getY() > bl.getY())
			ySpeed *= -1;
		
		projectiles.add(new Projectile(m.getX() + 16, m.getY() + 16, xSpeed, ySpeed, m.getDmg(), proj_id++, type));
		
	}
	

	public void update() {
		for(Projectile p : projectiles)
			if(p.isActive()) {
				p.move();
				if(isProjHittingBloon(p)) {
					p.setActive(false);
				} else {
					
				}
			}
	}
	
	private boolean isProjHittingBloon(Projectile p) {
		for(Bloon bl : playing.getBloonManager().getBloons()) {
			if(bl.getBounds().contains(p.getPos())) {
				bl.hurt(p.getDamage());
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		for(Projectile p : projectiles)
			if(p.isActive())
				g.drawImage(proj_imgs[p.getProjectileType()], (int) p.getPos().x, (int) p.getPos().y, null);
	}
	
	private int getProjType(Monkey m) {
		switch(m.getMonkeyType()) {
		case DART_M:
			return DART;
		case MAGE_M:
			return MAGIC_1;
		case ICY_M:
			return ICY_DART;
		case GUNNER_M:
			return BULLET;
		case PIRATE_M:
			return CANNON_BALL;
		case DIO_M:
			return KNIFE;
		}
		return 0;
	}
}
