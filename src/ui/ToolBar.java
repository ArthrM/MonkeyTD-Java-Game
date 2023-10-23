package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helperMethods.LoadSave;
import objects.Tile;
import scenes.Editing;

public class ToolBar extends Bar {

	private Editing editing;
	private MyButton bSave;
	private MyButton bPathStart, bPathEnd;
	private BufferedImage pathStart, pathEnd;
	private Tile selectedTile;
	
//	private ArrayList<MyButton> tileButtons = new ArrayList<>();
	
	private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton, ArrayList<Tile>>();
	
	private MyButton bGrass, bWater, bRoadS1, bRoadS2, bRoadC1, bRoadC2, bWaterC, bWaterB, bWaterI;
	private MyButton currentButton;
	private int currentIndex = 0;
	
	public ToolBar(int x, int y, int width, int height, Editing editing) {
		super(x, y, width, height);
		this.editing = editing;
		initPathImgs();
		initButtons();
		
	}
	
	private void initPathImgs() {
		pathStart = LoadSave.getSpriteAtlas().getSubimage(0 * 32, 11 * 32, 32, 32);
		pathEnd = LoadSave.getSpriteAtlas().getSubimage(1 * 32, 11 * 32, 32, 32);
	}
	
	private void initButtons() {
		
		bSave = new MyButton("Save", 12, 710, 50, 20);
		
		int w = 50;
		int h = 50;
		int xStart = 12;
		int yStart = 650;
		int xOffset = (int) (w * 1.12f);
		int i = 0;
		
		bGrass = new MyButton("Grass", xStart, yStart, w, h, i++);
		bWater = new MyButton("Water", xStart + xOffset, yStart, w, h, i++);

		
		
		initMapButton(bRoadS1, editing.getGame().getTileManager().getRoadsS1(), xStart, yStart, xOffset, w, h ,i++);
		initMapButton(bRoadC1, editing.getGame().getTileManager().getRoadsC1(), xStart, yStart, xOffset, w, h ,i++);
		initMapButton(bRoadS2, editing.getGame().getTileManager().getRoadsS2(), xStart, yStart, xOffset, w, h ,i++);
		initMapButton(bRoadC2, editing.getGame().getTileManager().getRoadsC2(), xStart, yStart, xOffset, w, h ,i++);
		initMapButton(bWaterC, editing.getGame().getTileManager().getWaterC(), xStart, yStart, xOffset, w, h ,i++);
		initMapButton(bWaterB, editing.getGame().getTileManager().getBeaches(), xStart, yStart, xOffset, w, h ,i++);
		initMapButton(bWaterI, editing.getGame().getTileManager().getIslands(), xStart, yStart, xOffset, w, h ,i++);
		
		bPathStart = new MyButton("PathStart", xStart + xOffset, yStart + xOffset, w, h, i++);
		bPathEnd = new MyButton("PathEnd", xStart + 2 * xOffset, yStart + xOffset, w, h, i++);
		
	}
	
	private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id) {
		
		b = new MyButton("", x + xOff * id, y, w, h, id);
		map.put(b, list);
		
	}
	
	
	private void saveLevel() {
		editing.saveLevel();
		
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(122,89,76));
		g.fillRect(x, y, width, height);
		drawButtons(g);
	}
	
	private void drawButtons(Graphics g) {
		
		bSave.draw(g);
		
		drawPathButton(g,bPathStart, pathStart);
		drawPathButton(g,bPathEnd, pathEnd);
		
//		bPathStart.draw(g);
//		bPathEnd.draw(g);
		
		drawRegularButtons(g, bGrass);
		drawRegularButtons(g, bWater);
		drawSelectedTile(g);
		drawMapButtons(g);
		
	}

	private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
		
		g.drawImage(img, b.x, b.y, b.width, b.height, null);
		drawButtonFeedbacks(g, b);
		
	}

	private void drawRegularButtons(Graphics g, MyButton b) {
		
		//Sprite do botão
		g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height,null);
		drawButtonFeedbacks(g, b);
	}

	private void drawMapButtons(Graphics g) {
		
		for(Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()) {
			MyButton b = entry.getKey();
			BufferedImage img = entry.getValue().get(0).getSprite();
			
			g.drawImage(img, b.x, b.y, b.width, b.height, null);
			drawButtonFeedbacks(g, b);
			
		}
		
	}
	
	public void rotateSprite() {
		
		currentIndex++;
		if(currentIndex >= map.get(currentButton).size())
			currentIndex = 0;
		selectedTile = map.get(currentButton).get(currentIndex);
		editing.setSelectedTile(selectedTile);
	}
	
	

	private void drawSelectedTile(Graphics g) {
		
		if(selectedTile != null) {
			g.drawImage(selectedTile.getSprite(), 590, 650, 28, 28, null);
		}
		
	}
	
	public BufferedImage getButtImg(int id) {
		return editing.getGame().getTileManager().getSprite(id);
	}
	
	public BufferedImage getStartPathImg() {
		return pathStart;
	}
	
	public BufferedImage getEndPathImg() {
		return pathEnd;
	}

	public void mouseClicked(int x, int y) {
		if(bSave.getBounds().contains(x, y)) {
			saveLevel();
		} else if(bGrass.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bGrass.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if(bWater.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bWater.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if(bPathStart.getBounds().contains(x, y)) {
			selectedTile = new Tile(pathStart, -1,-1);
			editing.setSelectedTile(selectedTile);
			
		} else if(bPathEnd.getBounds().contains(x, y)) {
			selectedTile = new Tile(pathEnd, -2,-2);
			editing.setSelectedTile(selectedTile);
			
		} else {
		
			for(MyButton b : map.keySet()) {
				if(b.getBounds().contains(x, y)) {
					selectedTile = map.get(b).get(0);
					editing.setSelectedTile(selectedTile);
					currentButton = b;
					currentIndex = 0;
					return;
				}
			}
	    }
	}

	public void mouseMoved(int x, int y) {
		
		bSave.setMouseOver(false);
		bGrass.setMouseOver(false);
		bWater.setMouseOver(false);
		bPathStart.setMouseOver(false);
		bPathEnd.setMouseOver(false);
		
		for(MyButton b : map.keySet())
			b.setMouseOver(false);
		
		if(bSave.getBounds().contains(x, y))
			bSave.setMouseOver(true);
		else if(bGrass.getBounds().contains(x, y))
			bGrass.setMouseOver(true);
		else if(bWater.getBounds().contains(x, y))
			bWater.setMouseOver(true);
		else if (bPathStart.getBounds().contains(x, y))
			bPathStart.setMouseOver(true);
		else if (bPathEnd.getBounds().contains(x, y))
			bPathEnd.setMouseOver(true);
		else {
			for(MyButton b : map.keySet()) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}
		}
	}


	public void mousePressed(int x, int y) {
		if(bSave.getBounds().contains(x, y))
			bSave.setMousePressed(true);
		else if(bGrass.getBounds().contains(x, y))
			bGrass.setMousePressed(true);
		else if(bWater.getBounds().contains(x, y))
			bWater.setMousePressed(true);
		else if(bPathStart.getBounds().contains(x, y))
			bPathStart.setMousePressed(true);
		else if(bPathEnd.getBounds().contains(x, y))
			bPathEnd.setMousePressed(true);
		for(MyButton b : map.keySet()) {
			if(b.getBounds().contains(x, y)) {
				b.setMousePressed(true);
				return;
			}
		}
	}


	public void mouseReleased(int x, int y) {
		resetRegularButtons();
		for(MyButton b : map.keySet())
			b.resetBooleans();
	}


	private void resetRegularButtons() {
		bSave.resetBooleans();
		bGrass.resetBooleans();
		bWater.resetBooleans();
		bPathStart.resetBooleans();
		bPathEnd.resetBooleans();
		return;
	}
}
