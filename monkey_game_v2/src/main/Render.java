package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Render {

	private Game game;
	
	
	public Render(Game game) {
		this.game = game;
		
		
	}
	
	public void render(Graphics g) {
		
		switch(GameStates.gameState) {
		
		case MENU:
			game.getMenu().render(g);
			
			break;
		case PLAYING:
			game.getPlaying().render(g);
			
			break;
		case SETTINGS:
			game.getSettings().render(g);
			
			break;
		case EDIT:
			game.getEditor().render(g);
			
			break;
		default:
			break;
		
		}
		
	}
	
	
	
	
	
	
}
