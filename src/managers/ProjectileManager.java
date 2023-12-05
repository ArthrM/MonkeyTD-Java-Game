package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
		
		int xDist = (int) (m.getX() - bl.getX());
		int yDist = (int) (m.getY() - bl.getY());
		int totDist = Math.abs(xDist) + Math.abs(yDist);
		
		float xPer = (float) Math.abs(xDist) / totDist;
		
		float xSpeed = xPer * helperMethods.Constants.Projectiles.GetSpeed(type);
		float ySpeed = helperMethods.Constants.Projectiles.GetSpeed(type) - xSpeed;
		
		if(m.getX() > bl.getX())
			xSpeed *= -1;
		if(m.getY() > bl.getY())
			ySpeed *= -1;
		
		float arcValue = (float) Math.atan(yDist / (float)xDist);
		float rotate = (float) Math.toDegrees(arcValue);
		
		if(xDist < 0)
			rotate += 180;
		
		for(Projectile p : projectiles)
			if(!p.isActive())
				if(p.getProjectileType() == type) {
					p.reuse(m.getX() + 16, m.getY() + 16, xSpeed, ySpeed, m.getDmg(), rotate);
					return;
				}
			
		
		projectiles.add(new Projectile(m.getX() + 16, m.getY() + 16, xSpeed, ySpeed, m.getDmg(), rotate, proj_id++, type));
		
	}
	

	public void update() {
		for(Projectile p : projectiles)
			if(p.isActive()) {
				p.move();
				if(isProjHittingBloon(p)) {
					p.setActive(false);
				} else if(isProjOutsideBounds(p)){
					p.setActive(false);
				}
			}
	}
	
	private boolean isProjOutsideBounds(Projectile p) {
		if(p.getPos().x >= 0)
			if(p.getPos().x <= 640)
				if(p.getPos().y >= 0)
					if(p.getPos().y <= 800)
						return false;
		return true;
	}

	private boolean isProjHittingBloon(Projectile p) {
		for(Bloon bl : playing.getBloonManager().getBloons()) {
			if(bl.isAlive()) {
				if(bl.getBounds().contains(p.getPos())) {
					bl.hurt(p.getDamage());
					if(p.getProjectileType() == ICY_DART)
						bl.slow();
				return true;
				}
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		for(Projectile p : projectiles)
			if(p.isActive()) {
				g2d.translate(p.getPos().x, p.getPos().y);
				g2d.rotate(Math.toRadians(p.getRotation() + 90));
				g2d.scale(1.25, 1.25);
				g2d.drawImage(proj_imgs[p.getProjectileType()], -16, -16, null);
				g2d.scale(1 / 1.25, 1 / 1.25);
				g2d.rotate(-Math.toRadians(p.getRotation() + 90));
				g2d.translate(-p.getPos().x, -p.getPos().y);
			}
	}
	
	private int getProjType(Monkey m) {
		switch(m.getMonkeyType()) {
		case DART_M:
			return DART;
		case MAGE_M:
			if(m.getTier() == 0)
				return MAGIC_1;
			if(m.getTier() == 1)
				return MAGIC_2;
			if(m.getTier() == 2)
				return MAGIC_3;
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
	
	public void reset() {
		projectiles.clear();
		proj_id = 0;
	}
}
