package Bloons;

import static helperMethods.Constants.Bloons.BLUE_BL;

import managers.BloonManager;

public class BlueBloon extends Bloon {

	public BlueBloon(float x, float y, int ID, BloonManager bM) {
		super(x, y, ID, BLUE_BL, bM);
	}

}
