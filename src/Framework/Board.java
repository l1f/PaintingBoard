package Framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
	private String openFilePath = "";
	
	private Shape editShape; // 选中的编辑中的图形
	private Shape drawShape; // 绘制中图形 鼠标按下拖动未抬起
	
	private JMenu menuInfo; // 用于现实提示内容
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
		
		JButton btnText = new JButton("文字");
		btnText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content.setDrawType("text");
				String text = JOptionPane.showInputDialog("请输入文字：");
				if(text.trim().equals("")){
					JOptionPane.showMessageDialog(null, "未输入文字无法绘制");
					return ;
				}
				content.setTextStr(text);
				content.setState(1);
			}
		});
		btns.add(btnText);
		
		
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
				JFileChooser chooser = new JFileChooser();
				int value = chooser.showOpenDialog(null);
				if( value == JFileChooser.APPROVE_OPTION){
					try {
						ObjectInputStream in = new ObjectInputStream(
							new FileInputStream(chooser.getSelectedFile()));
						content.setShapes((ArrayList<Shape>) in.readObject());
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					chooser.getSelectedFile();
				}
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
						openFilePath = path;
						flushTitle();
					} catch (Exception e1) {
						System.out.println("write failed");
			            e1.printStackTrace();
			        }
				}
			}
		});
		menuFile.add(menuItemSave);
		
		JMenu menuColor = new JMenu("设置颜色");
		JMenuItem itemRed = new JMenuItem("红色");
		itemRed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setColor(Color.RED);
			}
		});
		menuColor.add(itemRed);
		
		menuBar.add(menuFile);
		menuBar.add(menuColor);
		
		
		menuInfo = new JMenu("");
		menuBar.add(menuInfo);
		this.setJMenuBar(menuBar);
		setVisible(true);
	}
	
	public void print(String info){
		menuInfo.setText(info);
	}
	private void setColor(Color color){
		content.setColor(color);
	}
	private void flushTitle(){
		setTitle("绘图板[" + openFilePath + "]");
	}
}
