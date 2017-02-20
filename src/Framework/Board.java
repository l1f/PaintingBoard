package Framework;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
		btnLine.addActionListener(new ActionListener() {
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
		this.add(panel,BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("文件");
		JMenuItem menuItemOpen = new JMenuItem("打开");
		menuItemOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("打开啦");
				
			}
		});
		menuFile.add(menuItemOpen);
		JMenuItem menuItemSave = new JMenuItem("保存");
		menuItemSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int value = chooser.showSaveDialog(null);
				if( value == JFileChooser.APPROVE_OPTION){
					String path = chooser.getSelectedFile().getAbsolutePath();
					try{
						ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream(path));
						
						out.writeObject(content.getShapes());
						out.flush();
						out.close();
						JOptionPane.showMessageDialog(null, "保存成功");
					} catch (Exception e1) {
						System.out.println("write failed");
			            e1.printStackTrace();
			        }
				}
			}
		});
		menuFile.add(menuItemSave);
		menuBar.add(menuFile);
		this.setJMenuBar(menuBar);
		setVisible(true);
	}
}
