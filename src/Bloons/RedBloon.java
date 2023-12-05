package Bloons;

import static helperMethods.Constants.Bloons.RED_BL;

import managers.BloonManager;

public class RedBloon extends Bloon {

	public RedBloon(float x, float y, int ID, BloonManager bM) {
		super(x, y, ID, RED_BL, bM);
	}

}
