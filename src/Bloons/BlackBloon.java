package Bloons;

import static helperMethods.Constants.Bloons.BLACK_BL;

import managers.BloonManager;

public class BlackBloon extends Bloon{

	public BlackBloon(float x, float y, int ID, BloonManager bM) {
		super(x, y, ID, BLACK_BL, bM);
	}

}
