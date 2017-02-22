package Framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import Shape.Text;
import Shape.Line;
import Shape.Rectangle;
import Shape.Circle;
import Shape.Shape;

public class Content extends JPanel {
	private Point startPoint,endPoint;
	private int state; // 0 无操作 1 绘制中 2 选中编辑中
	private ArrayList<Shape> shapes = new ArrayList<>();
	private String drawType;
	private int drawIndex = -1,editIndex = -1;
	private HashMap<String,Object> typeClass = new HashMap<>();
	private String textStr = "";
	public void setTextStr(String textStr){
		this.textStr = textStr;
	}
	public String getTextStr(){
		return textStr;
	}
	
	public Point getStartPoint(){
		return startPoint;
	}
	public Point getEndPoint(){
		return endPoint;
	}
	public void setDrawType(String type){
		drawType = type;
	}
	
	private Content getThis(){ //供内部类中获取
		return this;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		for ( Shape s : shapes )
		{
			s.draw(g);
		}			
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
	public void setShapes(ArrayList<Shape> shapes){
		this.shapes = shapes;
		print(getGraphics());
	}
	public int getState(){
		return state;
	}
	public void setState(int state){
		this.state = state;
	}
	public void setColor(Color color){
		if(editIndex != -1){
			shapes.get(editIndex).setColor(color);
			paint(getGraphics());
		}
	}
	public Content() {
		typeClass.put("rect",Rectangle.class);
		setBackground(new Color(255, 255, 255));
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println("----released----");
				if( state == 1 ){
					Shape shape = getOneShape(drawType);
					shapes.set(drawIndex, shape);
					paint(getGraphics());
					drawIndex = -1;
					state = 0;
				}
					
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if( state == 0 ){
					
				}else if( state == 1){
					startPoint = e.getPoint();
					endPoint = e.getPoint();
				}else if( state == 2){
					
				}
				System.out.println(endPoint);
				//System.out.println("----pressed----");
			}
			
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("----exit----");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("----entered----");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(state == 0){	
					for(int i=0; i<shapes.size(); i++){
						if(shapes.get( shapes.size()-i-1 ).isSelected(e.getPoint())){
							editIndex = shapes.size()-i-1;
							System.out.println(shapes.get(editIndex));
							state = 2;
							return ;
						}
							
					}
				}else if(state == 1){
					startPoint = e.getPoint();
				}else if(state == 2){
					
				}
				
				System.out.println("-----click-----");
				
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println("----move----");
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("----drag----");
				endPoint = e.getPoint();
				if(drawIndex == -1){
					Shape shape = getOneShape(drawType);
					shapes.add(shape);
					drawIndex=shapes.size()-1;
					
				}else{
					Shape shape = getOneShape(drawType);
					shapes.set(drawIndex, shape);
					
					paint(getGraphics());
				}
				
				
			}
		});
	}
	
	private Shape getOneShape(String type){
		switch(type){
		case "rectangle":
			return new Rectangle(this);
		case "circle":
			return new Circle(this);
		case "line":
			return new Line(this);
		case "text":
			return new Text(this,getTextStr());
		}
		return null;
			
	}
	public double getShapeWidth(){
		return Math.abs(startPoint.getX()-endPoint.getX());
	}
	public double getShapeHeight(){
		return Math.abs(startPoint.getY()-endPoint.getY());
	}
	public Point getCenterPoint(){
		return new Point((int)(startPoint.getX()+endPoint.getX())/2, (int)(startPoint.getY()+endPoint.getY())/2);
	}
	public Point getLeftTopPoint(){
		return new  Point( (int)Math.min(startPoint.getX(),endPoint.getX()), (int)Math.min(startPoint.getY(), endPoint.getY()));
	}
	
	 
}
