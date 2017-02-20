package Shape;

import java.awt.Graphics;
import java.awt.Point;

import Framework.Content;


public class Circle extends Shape {
	private int x;
	private int y;
	private int radius;
	
	public Circle(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	public Circle(Content content){
		Point point = content.getCenterPoint();
		this.x = point.x;
		this.y = point.y;
		int x1 = content.getStartPoint().x;
		int y1 = content.getStartPoint().y;
		int x2 = content.getEndPoint().x;
		int y2 = content.getEndPoint().y;
		this.radius = (int) Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	@Override
	public void draw(Graphics g) {
		g.drawOval(x-radius, y-radius, radius*2, radius*2);
	}
}
