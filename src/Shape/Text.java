package Shape;

import java.awt.Graphics;
import java.awt.Point;

import Framework.Content;

public class Text extends Shape {
	private String text = "";
	private int x,y;
	public Text(Content content,String text){
		this.text = text;
		this.x = content.getLeftTopPoint().x;
		this.y = content.getLeftTopPoint().y;
	}
	@Override
	public void draw(Graphics g) {
		g.drawString(text, x, y);
	}
	@Override
	public boolean isSelected(Point point) {
		return false;
	}

}
