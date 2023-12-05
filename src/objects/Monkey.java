package objects;

import static helperMethods.Constants.Monkeys.*;

public class Monkey {
	
	private int x, y, id, monkeyType, dmg, cdTick, tier;
	private float range, cooldown;
	
	public Monkey(int x, int y, int id, int monkeyType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.monkeyType = monkeyType;
		tier = 1;
		setDefaultDmg();
		setDefaultRange();
		setDefaultCooldown();
	}
	
	public void update() {
		cdTick++;
	}
	
	public void upgradeMonkey() {
		this.tier++;
		
		switch(monkeyType) {
		
		case DART_M:
			dmg += 5;
			range += 25;
			break;
		case MAGE_M:
			range += 30;
			cooldown -= 10;
			break;
		case ICY_M:
			range += 12;
			cooldown -= 7;
			break;
		case GUNNER_M:
			range += 75;
			cooldown /= 2;
			break;
		case PIRATE_M:
			range += 15;
			cooldown -= 7;
			break;
		case DIO_M:
			range += 30;
			dmg += 10;
			cooldown -= 4.5f;
			break;
		}
		
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

	public int getTier() {
		return tier;
	}

}
