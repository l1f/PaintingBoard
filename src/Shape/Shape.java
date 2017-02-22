package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import Framework.Content;

public abstract class Shape implements java.io.Serializable{
	private Color color = new Color(0xffffff);
	
	public abstract void draw(Graphics g);
	public abstract boolean isSelected(Point point);
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public String toString(){
		return getClass().getName();
	}
	
	

	
	
}
