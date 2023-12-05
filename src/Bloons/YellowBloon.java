package Bloons;

import static helperMethods.Constants.Bloons.YELLOW_BL;

import managers.BloonManager;

public class YellowBloon extends Bloon{

	public YellowBloon(float x, float y, int ID, BloonManager bM) {
		super(x, y, ID, YELLOW_BL, bM);
	}

}
