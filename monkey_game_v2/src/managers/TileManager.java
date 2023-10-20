package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helperMethods.ImgFix;
import helperMethods.LoadSave;
import objects.Tile;
import static helperMethods.Constants.Tiles.*;

public class TileManager {

	public Tile GRASS, WATER, BL_WATER, TL_WATER, TR_WATER, BR_WATER, DIRTROAD, DIRTROAD_1, COBBLEROAD, COBBLEROAD_1, 
				T_WATER, R_WATER, B_WATER, L_WATER, TLC_WATER, TRC_WATER, BRC_WATER, BLC_WATER, DC_1, DC_2, DC_3, DC_4,
				CC_1, CC_2, CC_3, CC_4;
	public BufferedImage atlas;
	public ArrayList<Tile> tiles = new ArrayList<>();
	public ArrayList<Tile> roadsS1 = new ArrayList<>();
	public ArrayList<Tile> roadsS2 = new ArrayList<>();
	public ArrayList<Tile> roadsC1 = new ArrayList<>();
	public ArrayList<Tile> roadsC2 = new ArrayList<>();
	public ArrayList<Tile> waterC = new ArrayList<>();
	public ArrayList<Tile> beaches = new ArrayList<>();
	public ArrayList<Tile> islands = new ArrayList<>();
	
	public TileManager() {
		
		loadAtlas();
		createTiles();
	}

	private void createTiles() {
		
		int id = 0;
		
		tiles.add(GRASS = new Tile(getSprite(0,10),id++, GRASS_TILE));
		tiles.add(WATER = new Tile(getAniSprites(1,10,3),id++,WATER_TILE));
		
		roadsS1.add(DIRTROAD = new Tile(getSprite(4,10),id++,ROAD_TILE));
		roadsS1.add(DIRTROAD_1 = new Tile(ImgFix.getRotImg(getSprite(4,10), 90),id++,ROAD_TILE));
		roadsC1.add(DC_1 = new Tile(getSprite(5,10),id++,ROAD_TILE));
		roadsC1.add(DC_2 = new Tile(ImgFix.getRotImg(getSprite(5,10), 90),id++,ROAD_TILE));
		roadsC1.add(DC_3 = new Tile(ImgFix.getRotImg(getSprite(5,10), 180),id++,ROAD_TILE));
		roadsC1.add(DC_4 = new Tile(ImgFix.getRotImg(getSprite(5,10), 270),id++,ROAD_TILE));
		
		roadsS2.add(COBBLEROAD = new Tile(getSprite(4,11),id++,ROAD_TILE));
		roadsS2.add(COBBLEROAD_1 = new Tile(ImgFix.getRotImg(getSprite(4,11), 90),id++,ROAD_TILE));
		roadsC2.add(CC_1 = new Tile(getSprite(5,11),id++,ROAD_TILE));
		roadsC2.add(CC_2 = new Tile(ImgFix.getRotImg(getSprite(5,11), 90),id++,ROAD_TILE));
		roadsC2.add(CC_3 = new Tile(ImgFix.getRotImg(getSprite(5,11), 180),id++,ROAD_TILE));
		roadsC2.add(CC_4 = new Tile(ImgFix.getRotImg(getSprite(5,11), 270),id++,ROAD_TILE));
		
		waterC.add(BL_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(7,10), 0),id++,WATER_TILE));
		waterC.add(TL_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(7,10), 90),id++,WATER_TILE));
		waterC.add(TR_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(7,10), 180),id++,WATER_TILE));
		waterC.add(BR_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(7,10), 270),id++,WATER_TILE));
		
		beaches.add(T_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(8,10), 0),id++,WATER_TILE));
		beaches.add(R_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(8,10), 90),id++,WATER_TILE));
		beaches.add(B_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(8,10), 180),id++,WATER_TILE));
		beaches.add(L_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(8,10), 270),id++,WATER_TILE));
		
		islands.add(TLC_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(6,10), 0),id++,WATER_TILE));
		islands.add(TRC_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(6,10), 90),id++,WATER_TILE));
		islands.add(BRC_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(6,10), 180),id++,WATER_TILE));
		islands.add(BLC_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(1,10, 3),getSprite(6,10), 270),id++,WATER_TILE));
		
		tiles.addAll(roadsS1);
		tiles.addAll(roadsC1);
		tiles.addAll(roadsS2);
		tiles.addAll(roadsC2);
		tiles.addAll(waterC);
		tiles.addAll(beaches);
		tiles.addAll(islands);
		
	}

	private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {
		
		
		return new BufferedImage[]{getSprite(firstX,firstY),getSprite(secondX, secondY)};
	}
	
	private void loadAtlas() {
		
		atlas = LoadSave.getSpriteAtlas();
		
	}
	
	public Tile getTile(int id) {
		return tiles.get(id);
	}
	
	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}
	
	public BufferedImage getAniSprite(int id, int animationIndex) {
		return tiles.get(id).getSprite(animationIndex);
	}
	
	private BufferedImage[] getAniSprites(int xCord, int yCord, int index) {
		
		BufferedImage[] arr = new BufferedImage[index];
		for(int i = 0; i < index; i++) {
			arr[i] = getSprite(xCord + i, yCord);
		}
		
		return arr;
	}
	
	private BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord*32, yCord*32, 32, 32);
	}

	public boolean isSpriteAnimation(int spriteID) {
		return tiles.get(spriteID).isAnimation();
	}
	
	public ArrayList<Tile> getRoadsS1() {
		return roadsS1;
	}


	public ArrayList<Tile> getRoadsC1() {
		return roadsC1;
	}
	
	
	public ArrayList<Tile> getRoadsS2() {
		return roadsS2;
	}
	
	
	public ArrayList<Tile> getRoadsC2() {
		return roadsC2;
	}


	public ArrayList<Tile> getWaterC() {
		return waterC;
	}


	public ArrayList<Tile> getBeaches() {
		return beaches;
	}


	public ArrayList<Tile> getIslands() {
		return islands;
	}

	
}
