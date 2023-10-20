package helperMethods;

public class Constants {

	public static class Projectiles {
		public static final int DART = 0;
		public static final int MAGIC_1 = 1;
		public static final int MAGIC_2 = 2;
		public static final int MAGIC_3 = 3;
		public static final int ICY_DART = 4;
		public static final int KNIFE = 5;
		public static final int CANNON_BALL = 6;
		
		public static float GetSpeed(int type) {
			switch(type) {
			case DART:
				return 3f;
			case MAGIC_1:
				return 3f;
			case MAGIC_2:
				return 3f;
			case MAGIC_3:
				return 3f;
			case ICY_DART:
				return 3f;
			case KNIFE:
				return 4.2f;
			case CANNON_BALL:
				return 3f;
				
			}
			
			return 0f;
		}
	}
	
	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class Monkeys {
		public static final int DART_M = 0;
		public static final int MAGE_M = 1;
		public static final int ICY_M = 2;
		public static final int GUNNER_M = 3;
		public static final int PIRATE_M = 4;
		public static final int DIO_M = 5;
		
		public static String GetName(int monkeyType) {
			
			switch(monkeyType) {
			case DART_M:
				return "Dardocaco";
			case MAGE_M:
				return "Bruxocaco";
			case ICY_M:
				return "Gelocaco";
			case GUNNER_M:
				return "Armacaco";
			case PIRATE_M:
				return "Piratacaco";
			case DIO_M:
				return "DIOcaco";
			}
			
			return "";
			
		}
		
		public static float GetDefaultDmg(int monkeyType) {
			switch(monkeyType) {
			case DART_M:
				return 10;
			case MAGE_M:
				return 15;
			case ICY_M:
				return 5;
			case GUNNER_M:
				return 20;
			case PIRATE_M:
				return 15;
			case DIO_M:
				return 15;
			}
			
			return 0;
		}
		
		public static float GetDefaultRange(int monkeyType) {
			switch(monkeyType) {
			case DART_M:
				return 80;
			case MAGE_M:
				return 75;
			case ICY_M:
				return 80;
			case GUNNER_M:
				return 105;
			case PIRATE_M:
				return 85;
			case DIO_M:
				return 90;
			}
			
			return 0;
		}

		public static float GetDefaultCooldown(int monkeyType) {
			switch(monkeyType) {
			case DART_M:
				return 10;
			case MAGE_M:
				return 15;
			case ICY_M:
				return 10;
			case GUNNER_M:
				return 20;
			case PIRATE_M:
				return 15;
			case DIO_M:
				return 10;
			}
			
			return 0;
		}
		
		public static int GetPlaceableTile(int monkeyType) {
			switch(monkeyType) {
			case DART_M:
				return Tiles.GRASS_TILE;
			case MAGE_M:
				return Tiles.GRASS_TILE;
			case ICY_M:
				return Tiles.GRASS_TILE;
			case GUNNER_M:
				return Tiles.GRASS_TILE;
			case PIRATE_M:
				return Tiles.WATER_TILE;
			case DIO_M:
				return Tiles.GRASS_TILE;
			}
			
			return 0;
		}
		
	}
	
	public static class Bloons {
		public static final int RED_BL = 0;
		public static final int BLUE_BL = 1;
		public static final int GREEN_BL = 2;
		public static final int YELLOW_BL = 3;
		
		public static float GetSpeed(int bloonType) {
			switch(bloonType) {
			case RED_BL:
				return 0.5f;
			case BLUE_BL:
				return 0.6f;
			case GREEN_BL:
				return 0.7f;
			case YELLOW_BL:
				return 0.8f;
			}
			
			return 0;
		}
		
		public static int GetStartHealth(int bloonType) {
			switch(bloonType) {
			case RED_BL:
				return 10;
			case BLUE_BL:
				return 15;
			case GREEN_BL:
				return 20;
			case YELLOW_BL:
				return 25;
			}
			
			return 0;
			
		}
	}
	
	
	public static class Tiles {
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}
	
}
