package objects;

public class Monkey {
	
	private int x, y, id, monkeyType, dmg, cdTick;
	private float range, cooldown;
	
	public Monkey(int x, int y, int id, int monkeyType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.monkeyType = monkeyType;
		setDefaultDmg();
		setDefaultRange();
		setDefaultCooldown();
	}
	
	public void update() {
		cdTick++;
	}
	
	public boolean isCooldownOver() {
		
		return cdTick >= cooldown;
	}
	
	public void resetCooldown() {
		cdTick = 0;
		
	}

	private void setDefaultDmg() {
		dmg = helperMethods.Constants.Monkeys.GetDefaultDmg(monkeyType);
		
	}

	private void setDefaultRange() {
		range = helperMethods.Constants.Monkeys.GetDefaultRange(monkeyType);
		
	}

	private void setDefaultCooldown() {
		cooldown = helperMethods.Constants.Monkeys.GetDefaultCooldown(monkeyType);
		
	}

	public int getDmg() {
		return dmg;
	}

	public float getRange() {
		return range;
	}

	public float getCooldown() {
		return cooldown;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMonkeyType() {
		return monkeyType;
	}

	public void setMonkeyType(int monkeyType) {
		this.monkeyType = monkeyType;
	}


}
