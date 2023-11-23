package main;

import javax.swing.JFrame;

import helperMethods.LoadSave;
import managers.TileManager;
import scenes.CreditScreen;
import scenes.Editing;
import scenes.Menu;
import scenes.Playing;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import javax.sound.sampled.*;
import scenes.Settings;

public class Game extends JFrame implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	
	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;
	
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;
	private Editing editing;
	private CreditScreen creditScreen;
	
	private TileManager tileManager;

	public Game() {
		
		initClasses();
		createDefaultLevel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
	}
	
	private void initClasses() {
		tileManager = new TileManager();
		render = new Render(this);	
		gamePanel = new GamePanel(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
		editing = new Editing(this);
		creditScreen = new CreditScreen(this);
		
	}

	public void showCreditsScreen() {
        JFrame frame = new JFrame("Tela de Créditos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(frame);
        frame.pack();
        frame.setVisible(true);
    }

	private void start() {
		
		gameThread = new Thread(this) {};
		gameThread.start();
	}
	

	private void updateGame() {
		
		switch(GameStates.gameState) {
		case EDIT:
			editing.update();
			break;
		case MENU:
			break;
		case PLAYING:
			playing.update();
			break;
		case SETTINGS:
			break;
		case CreditScreen:
			break;
		default:
			break;
		}
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.gamePanel.initInputs();
		game.start();

	}

	private void createDefaultLevel() {
		int[] arr = new int[400];
		for(int i = 0 ; i < arr.length ; i++)
			arr[i] = 0;
		
		LoadSave.CreateLevel("new_level", arr);
		
	}

	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		
		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();
		
		int frames = 0;
		int updates = 0;
		
		long now;
		
		while(true) {
		
			now = System.nanoTime();
		// Render
		if(now - lastFrame >= timePerFrame) {
			gamePanel.repaint();
			lastFrame = now;
			frames++;
		}
		
		// Update
		if(now - lastUpdate >= timePerUpdate) {
			updateGame();
			lastUpdate = now;
			updates++;
		}
		
		if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
			System.out.println("FPS: " + frames + " | UPS: " + updates);
			frames = 0;
			updates = 0;
			lastTimeCheck = System.currentTimeMillis();
		}
		
	  }
		
	}
	
	//Getters e Setters
	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}


	public Settings getSettings() {
		return settings;
	}
	
	public Editing getEditor() {
		return editing;
	}

	public TileManager getTileManager() {
		return tileManager;
	}

    public CreditScreen getcreditscreen() {
		return creditScreen;
    }
	
	
}