package Bloons;

import static helperMethods.Constants.Bloons.GREEN_BL;

import managers.BloonManager;

public class GreenBloon extends Bloon{

	public GreenBloon(float x, float y, int ID, BloonManager bM) {
		super(x, y, ID, GREEN_BL, bM);
	}

}
