package Shape;

import java.awt.Graphics;

import Framework.Content;

public class Rectangle extends Shape {
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	@Override
	public void draw(Graphics g, Content c) {
		g.drawRect(x, y, width, height);
		
	}

}
