package ui;

import java.awt.Color;
import java.awt.Graphics;

public class Bar {

	protected int x , y , width , height;

	public Bar(int x, int y , int width , int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	
	}

	protected void drawButtonFeedbacks(Graphics g, MyButton b) {
		
		//Mouse passa por cima
		if(b.isMouseOver())
			g.setColor(Color.white);
		else
			g.setColor(Color.DARK_GRAY);
		
		//Borda
		g.drawRect(b.x, b.y, b.width, b.height);
		
		
		//Mouse pressionado
		if(b.isMousePressed()) {
			g.drawRect(b.x + 1, b.y	+ 1, b.width - 2, b.height - 2);
			g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
		}
		
	}
	
}
