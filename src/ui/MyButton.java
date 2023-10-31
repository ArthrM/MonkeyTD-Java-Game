package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {

	public int id, x, y, width, height;
	protected String text;
	protected Rectangle bounds;
	protected boolean mouseOver, mousePressed;
	
	//Para botões normais
	public MyButton(String text, int x , int y , int width , int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		
		initBounds();
	}
	
	//Para botões de ladrilhos
	public MyButton(String text, int x , int y , int width , int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		
		initBounds();
	}
	
	protected void initBounds() {
		this.bounds = new Rectangle(x , y , width , height);
	}
	
	public void draw(Graphics g) {
		
		//Corpo
		drawBody(g);
		//Borda
		drawBorder(g);
		//Texto
		drawText(g);
		
	}
	
	protected void drawBody(Graphics g) {
		if(mouseOver)
			g.setColor(Color.gray);
		else
			g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		
	}
	
	protected void drawBorder(Graphics g) {
		
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		if(mousePressed) {
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}
		
	}

	protected void drawText(Graphics g) {
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		g.drawString(text, x - w / 2 + width / 2, y + h / 3 + height / 2);
		
	}
	
	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}
	
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public boolean isMouseOver() {
		return mouseOver;
	}
	
	public boolean isMousePressed() {
		return mousePressed;
	}
	
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getId() {
		return id;
	}
	
}
