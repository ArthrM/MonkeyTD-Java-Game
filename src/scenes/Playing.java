package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Bloons.Bloon;
import helperMethods.LoadSave;

import main.Game;
import managers.BloonManager;
import managers.MonkeyManager;
import managers.ProjectileManager;
import managers.WaveManager;
import objects.Monkey;
import objects.PathPoint;
import ui.MyButton;
import ui.ActionBar;
import static main.GameStates.*;
import static helperMethods.Constants.Tiles.*;

public class Playing extends GameScene implements SceneMethods{

	private int[][] lvl;
	private MyButton bMenu;
	private ActionBar actionBar;
	private int mouseX, mouseY;
	private BloonManager bloonManager;
	private MonkeyManager monkeyManager;
	private ProjectileManager projManager;
	private WaveManager waveManager;
	
	private boolean drawSelect = false;
	private boolean isMouseDragging = false;
	private boolean isMousePressed = false;
	
	private PathPoint start, end;
	private Monkey selectedMonkey;
	
	public Playing(Game game) {
		super(game);
		
		loadDefaultLevel();
		initButtons();
		actionBar = new ActionBar(0 , 640 , 640 , 140, this);
		bloonManager = new BloonManager(this, start, end);
		monkeyManager = new MonkeyManager(this);
		projManager = new ProjectileManager(this);
		waveManager = new WaveManager(this);
	}
	
	private void loadDefaultLevel() {
		
		lvl = LoadSave.GetLevelData("new_level");
		ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);
		
	}
	
	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}
	
	private void initButtons() {
		
		bMenu = new MyButton("", 597 , 10 , 30 , 26);
	}
	
	public void update() {
		
		updateTick();
		waveManager.update();
		
		if(areAllEnemiesDead()) {
			if(areThereMoreWaves()) {
				waveManager.startWaveTimer();
				if(isWaveTimeOver()) {
					waveManager.increaseWaveIndex();
					bloonManager.getBloons().clear();
					waveManager.resetBloonIndex();
				}
				
			}
		}
		
		if(isTimeForNewBloon()) {
			spawnBloon();
		}
		
		bloonManager.update();
		monkeyManager.update();
		projManager.update();
	}
	
	private boolean isWaveTimeOver() {
		
		return waveManager.isWaveTimerOver();
	}

	private boolean areThereMoreWaves() {
		
		return waveManager.areThereMoreWaves();
	}

	private boolean areAllEnemiesDead() {
		
		if(waveManager.isThereMoreBloonsInWave()) {
			return false;
		}
		
		for(Bloon bl : bloonManager.getBloons())
			if(bl.isAlive())
				return false;
		
		return true;
	}

	private void spawnBloon() {
		bloonManager.spawnBloon(waveManager.getNextBloon());
	}

	private boolean isTimeForNewBloon() {
		if(waveManager.isTimeForNewBloon()) {
			if(waveManager.isThereMoreBloonsInWave())
				return true;
		}
		
		return false;
	}
	
	public void setSelectedMonkey(Monkey selectedMonkey) {
		
		this.selectedMonkey = selectedMonkey;
		
	}
	
	@Override
	public void render(Graphics g) {
		
		drawLevel(g);
		drawButtons(g);
		
		drawHighLight(g);
		drawSelectedMonkey(g);
		
		bloonManager.draw(g);
		actionBar.draw(g);
		
		drawWaveInfos(g);
		
		monkeyManager.draw(g);
		projManager.draw(g);
		

	}
	
	private void drawWaveInfos(Graphics g) {
		
		
	}

	private void drawHighLight(Graphics g) {
		
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(mouseX, mouseY, 32, 32);	
	}

	private void drawSelectedMonkey(Graphics g) {
		
		if(selectedMonkey != null)
			g.drawImage(monkeyManager.getMonkeyImgs()[selectedMonkey.getMonkeyType()], mouseX, mouseY, null);
		
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

	
	public int getTileType(int x, int y) {
		
		int xCord = x / 32;
		int yCord = y / 32;
		
		if(xCord < 0 || xCord > 19)
			return 0;
		if(yCord < 0 || yCord > 19)
			return 0;
			
		int id = lvl[y / 32][x / 32];
		return game.getTileManager().getTile(id).getTileType();
	}
	
	public MonkeyManager getMonkeyManager() {
		return monkeyManager;
	}

	public BloonManager getBloonManager() {
		return bloonManager;
	}
	
	public WaveManager getWaveManager() {
		return waveManager;
	}
	
	private Monkey getMonkeyAt(int x, int y) {
		return monkeyManager.getMonkeyAt(x, y);
	}
	
	private boolean isTilePlaceable(int x, int y) {
		
		int id = lvl[y / 32][x / 32];
		int tileType = game.getTileManager().getTile(id).getTileType();
		
		return tileType == helperMethods.Constants.Monkeys.GetPlaceableTile(selectedMonkey.getMonkeyType());
	}
	
	public void shootBloon(Monkey m, Bloon bl) {
		projManager.newProjecile(m, bl);
		
	}
	
	@Override
	public void mouseClicked(int x, int y) {

		if(y >= 640) {
			actionBar.mouseClicked(x, y);
		} else if(bMenu.getBounds().contains(x, y))
			SetGameState(SETTINGS);
		else {
			if(selectedMonkey != null) {
				if(isTilePlaceable(mouseX, mouseY)) {
					if(getMonkeyAt(mouseX, mouseY) == null) {
					monkeyManager.addMonkey(selectedMonkey, mouseX, mouseY);
					selectedMonkey = null;
					}
				}
			} else {
				Monkey m = getMonkeyAt(mouseX , mouseY);
				actionBar.displayMonkey(m);
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			selectedMonkey = null;
		}
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		
		bMenu.setMouseOver(false);
		
		if(y >= 640)
			actionBar.mouseMoved(x, y);
		else {
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		} if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
	
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(y >= 640)
			actionBar.mousePressed(x, y);
		else if (bMenu.getBounds().contains(x,y))
			bMenu.setMousePressed(true);
		else
			isMousePressed = true;
	}

	@Override
	public void mouseReleased(int x, int y) {
		
		actionBar.mouseReleased(x, y);
		resetButtons();
		
	}
	
	@Override
	public void mouseDragged(int x, int y) {
		
		if(y >= 640) {
			
		} else
			isMouseDragging = true;
		
	}

	private void resetButtons() {
		
		isMousePressed = false;
		isMouseDragging = false;
		bMenu.resetBooleans();
		
	}





}