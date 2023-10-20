package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;


public class GamePanel extends JPanel {
	
	private Game game;
	private Dimension size;
	
	private MouseInputs MouseInputs;
	private KeyboardInputs KeyboardInputs;
	
	public GamePanel(Game game) {
		
		this.game = game;
		setPanelSize();
		
	}
	
	public void initInputs() {
		MouseInputs = new MouseInputs(game);
		KeyboardInputs = new KeyboardInputs(game);
		
		addMouseListener(MouseInputs);
		addMouseMotionListener(MouseInputs);
		addKeyListener(KeyboardInputs);
		
		requestFocus();
		
	}
	
	private void setPanelSize() {
		size = new Dimension(640,780);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.getRender().render(g);
		
//		g.drawImage(sprites.get(31), 0, 0, null);
		
//		BufferedImage i = img.getSubimage(32, 32*3, 32, 32);
//		g.drawImage(i, 0, 0, null);
		
		
		
	}
	

	

	

}
