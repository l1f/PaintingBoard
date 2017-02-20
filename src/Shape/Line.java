package Shape;

import java.awt.Graphics;

import Framework.Content;

public class Line extends Shape {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public Line(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
	}
	public Line(Content content){
		this.x1 = content.getStartPoint().x;
		this.y1 = content.getStartPoint().y;
		this.x2 = content.getEndPoint().x;
		this.y2 = content.getEndPoint().y;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}

}
