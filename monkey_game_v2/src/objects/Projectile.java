package objects;

import java.awt.geom.Point2D;

public class Projectile {
	
	private Point2D.Float pos;
	private int id, projectileType;
	private boolean active = true;
	
	public Projectile(float x, float y, int id, int projectileType) {
		
		pos = new Point2D.Float(x, y);
		this.id = id;
		this.projectileType = projectileType;
		
	}
	
	public void move(float x, float y) {
		pos.x += x;
		pos.y += y;
	}

	public Point2D.Float getPos() {
		return pos;
	}

	public void setPos(Point2D.Float pos) {
		this.pos = pos;
	}

	public int getId() {
		return id;
	}

	public int getProjectileType() {
		return projectileType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
