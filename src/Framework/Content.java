package Framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Shape.Rectangle;
import Shape.Shape;

public class Content extends JPanel {
	private Point startPoint,endPoint;
	private int state; // 0 无操作 1 绘制中 2 选中编辑中
	private ArrayList<Shape> shapes = new ArrayList<>();
	private String drawType;
	private int drawIndex = -1;
	
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
	public int getState(){
		return state;
	}
	public void setState(int state){
		this.state = state;
	}
	public Content() {
		this.setBackground(new Color(255, 255, 255));
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("----released----");
				if( state == 1){
					Rectangle r = new Rectangle(getThis());
					shapes.set(drawIndex, r);
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
				System.out.println("----pressed----");
			}
			
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("----exit----");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("----entered----");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(state == 0){	
					
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
					if(drawType == "rectangle"){
						Rectangle r = new Rectangle(Content.this);
						shapes.add(r);
						drawIndex=shapes.size()-1;
					}
				}else{
					if(drawType == "rectangle"){
						Rectangle r = new Rectangle(getThis());
						shapes.set(drawIndex, r);
						
					}
					paint(getGraphics());
				}
				
			}
		});
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
