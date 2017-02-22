package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import Framework.Content;

public class Rectangle extends Shape {
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Rectangle(Content content){
		Point point = content.getLeftTopPoint();
		int width = (int)content.getShapeWidth();
		int height = (int)content.getShapeHeight();
		this.x = point.x;
		this.y = point.y;
		this.width = width;
		this.height = height;
	}
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(x, y, width, height);
		g.setColor(new Color(0));
		g.drawRect(x, y, width, height);
	}
	@Override
	public boolean isSelected(Point point) {
		if(point.x >= x 
			&& point.x <= x+width
			&& point.y >= y
			&& point.y <= y+height){
				return true;
		}
		return false;
	}

}
