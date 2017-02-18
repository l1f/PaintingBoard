package Shape;

import java.awt.Color;
import java.awt.Graphics;

import Framework.Content;

public abstract class Shape {
	private Color color = new Color(0);
	
	public abstract void draw(Graphics g);

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	

	
	
}
