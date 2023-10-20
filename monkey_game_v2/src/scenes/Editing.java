package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helperMethods.LoadSave;

import main.Game;
import objects.PathPoint;
import objects.Tile;
import ui.MyButton;
import ui.ToolBar;
import static main.GameStates.*;

import static helperMethods.Constants.Tiles.ROAD_TILE;

public class Editing extends GameScene implements SceneMethods{
	private int[][] lvl;
	private MyButton bMenu;
	private ToolBar toolbar;
	private Tile selectedTile;
	private int mouseX, mouseY;
	private int lastTileX, lastTileY, lastTileId;
	private boolean drawSelect = false;
	private boolean isMouseDragging = false;
	private boolean isMousePressed = false;
	private PathPoint start, end;
	
	public Editing(Game game) {
		super(game);
		loadDefaultLevel();
		initButtons();
		toolbar = new ToolBar(0 , 640 , 640 , 140, this);
	}
	
	private void loadDefaultLevel() {
		lvl = LoadSave.GetLevelData("new_level");
		ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);
	}
	
	private void initButtons() {
		
		bMenu = new MyButton("", 597 , 10 , 30 , 26);
	}

	public void update() {
		updateTick();
	}
	
	@Override
	public void render(Graphics g) {
		drawLevel(g);
		drawSelectedTile(g);
		drawButtons(g);
		toolbar.draw(g);
		drawPathPoints(g);
	}
	

	private void drawPathPoints(Graphics g) {
		if(start != null) 
			g.drawImage(toolbar.getStartPathImg(), start.getxCord()*32, start.getyCord()*32, 32, 32, null);
		
		if(end != null)
			g.drawImage(toolbar.getEndPathImg(), end.getxCord()*32, end.getyCord()*32, 32, 32, null);
	}

	private void drawLevel(Graphics g) {
		for (int y = 0 ; y < lvl.length ; y++) {
			for(int x = 0 ; x < lvl[y].length ; x++) {
				int id = lvl[y][x];
				if(isAnimation(id)) {
					g.drawImage(getSprite(id, animationIndex), x*32, y*32, null);
				} else
					g.drawImage(getSprite(id), x*32, y*32, null);
			}
		}
	}

	
	private void drawSelectedTile(Graphics g) {
		
		if (selectedTile != null && drawSelect) {
			if(isMouseDragging || isMousePressed)
				g.setColor(Color.white);
			else
				g.setColor(Color.black);
			g.drawRect((mouseX / 32) * 32, (mouseY / 32) * 32, 32, 32);
			g.drawImage(selectedTile.getSprite(), mouseX - 12, mouseY - 12, 25, 25,null);
			g.drawRect(mouseX  -12, mouseY - 12, 25, 25);
			
		}
	}
	
	private void drawButtons(Graphics g) {
		drawMenuButton(g);
	}
	
	private void drawMenuButton(Graphics g) {
		
		if(!bMenu.isMouseOver()) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(bMenu.x +2, bMenu.y +3 , bMenu.width -4, 4);
			g.fillRect(bMenu.x +2, bMenu.y +12, bMenu.width -4, 4);
			g.fillRect(bMenu.x +2, bMenu.y +21, bMenu.width -4, 4);
		} else if(bMenu.isMousePressed()) {
			g.setColor(Color.BLACK);
			g.fillRect(bMenu.x +2, bMenu.y +3 , bMenu.width -4, 4);
			g.fillRect(bMenu.x +2, bMenu.y +12, bMenu.width -4, 4);
			g.fillRect(bMenu.x +2, bMenu.y +21, bMenu.width -4, 4);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(bMenu.x    , bMenu.y +2 , bMenu.width   , 4);
			g.fillRect(bMenu.x -1 , bMenu.y +12, bMenu.width +2, 4);
			g.fillRect(bMenu.x    , bMenu.y +23, bMenu.width   , 4);
		}
		
	}

	
	public void saveLevel() {
		
		LoadSave.SaveLevel("new_level", lvl, start, end);
		game.getPlaying().setLevel(lvl);
		
	}
	
	public void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
		
	}
	
	private void changeTile(int x, int y) {
		
		if(selectedTile != null) {
			int tileX = x / 32;
			int tileY = y / 32;
			
			if(selectedTile.getId() >= 0) {
			
				if(lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
					return;
			
				lastTileX = tileX;
				lastTileY = tileY;
				lastTileId = selectedTile.getId();				
			
				lvl[tileY][tileX] = selectedTile.getId();
			} else {
				int id = lvl[tileY][tileX];
				if(game.getTileManager().getTile(id).getTileType() == ROAD_TILE) {
					if(selectedTile.getId() == -1)
						start = new PathPoint(tileX, tileY);
					else
						end = new PathPoint(tileX, tileY);
				}
			}
		}
	}
	
	
	@Override
	public void mouseClicked(int x, int y) {
		if(y>=640)
			toolbar.mouseClicked(x, y);
		else if(bMenu.getBounds().contains(x, y)) {
			SetGameState(SETTINGS);
		}
		else if(!bMenu.getBounds().contains(mouseX, mouseY))
			changeTile(x,y);
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		
		if(y>=640) {
			toolbar.mouseMoved(x, y);
			drawSelect = false;
		} else if (selectedTile != null){
			drawSelect = true;
			mouseX = x;
			mouseY = y;
		} if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if(y>=640)
			toolbar.mousePressed(x, y);
		else if (bMenu.getBounds().contains(x,y))
			bMenu.setMousePressed(true);
		else 
			isMousePressed = true;
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		toolbar.mouseReleased(x, y);
		resetButtons();
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		if(y >= 640) {
			
		} else {
			isMouseDragging = true;
			mouseX = x;
			mouseY = y;
			if(!bMenu.getBounds().contains(x, y))
			changeTile(x, y);
		}
		
	}
	
	private void resetButtons() {
		isMousePressed = false;
		isMouseDragging = false;
		bMenu.resetBooleans();
		
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_R)
			toolbar.rotateSprite();
	}

}
