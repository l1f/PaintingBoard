package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.function.Supplier;

import Framework.Content;


public class Circle extends Shape {
	private int x;
	private int y;
	private int radiusX,radiusY;
	
	public Circle(Content content){
		Point point = content.getCenterPoint();
		this.x = point.x;
		this.y = point.y;
		this.radiusX = (int) (content.getShapeWidth()/2);
		this.radiusY = (int) (content.getShapeHeight()/2);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.fillOval(x, y, radiusX, radiusY);
		g.setColor(new Color(0));
		g.drawOval(x, y, radiusX, radiusY);
	}
	@Override
	public boolean isSelected(Point point) {
		int x = point.x;
		int y = point.y;
		
		return 1.0*(x*x)/(radiusX*radiusX)+1.0*(y*y)/(radiusY*radiusY)<=1;
	}
}
