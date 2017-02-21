package Shape;

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
		g.drawOval(x, y, radiusX, radiusY);
	}
	@Override
	public boolean isSelected(Point point) {
		// TODO Auto-generated method stub
		return false;
	}
}
