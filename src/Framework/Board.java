package Framework;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Shape.Rectangle;
import Shape.Shape;

public class Board extends JFrame {
	private JPanel panel = new JPanel();
	private ArrayList<JButton> btns = new ArrayList<>();
	private Content content = new Content();
	
	private Shape editShape; // 选中的编辑中的图形
	private Shape drawShape; // 绘制中图形 鼠标按下拖动未抬起
	
	public Board(int width,int height) {
		this.setTitle("绘图板");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.add(content);
		panel.setSize(width,100);
		
		JButton btnCircle = new JButton("圆");
		btnCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content.setDrawType("circle");
				content.setState(1);
			}
		});
		btns.add(btnCircle);
		
		JButton btnRect = new JButton("矩形");
		btnRect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content.setDrawType("rectangle");
				content.setState(1);
			}
		});
		btns.add(btnRect);
		
		JButton btnLine = new JButton("直线");
		btnRect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content.setDrawType("line");
				content.setState(1);
			}
		});
		btns.add(btnLine);
		
		
		for(JButton btn: btns){
			panel.add(btn);
		}
		this.add(panel,BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("文件");
		JMenuItem item1 = new JMenuItem("打开");
		menu.add(item1);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
	}
}
