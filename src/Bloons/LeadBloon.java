package Bloons;

import static helperMethods.Constants.Bloons.LEAD_BL;

import managers.BloonManager;

public class LeadBloon extends Bloon {

	public LeadBloon(float x, float y, int ID, BloonManager bM) {
		super(x, y, ID, LEAD_BL, bM);
	}

}
