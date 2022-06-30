package view.components;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelBackground extends JPanel {

	private static final long serialVersionUID = -5191786165702815643L;
	private String imagePath;
	private int xMargin = 0;
	private int yMargin = 0;
	
	public JPanelBackground() {
		super();
		initProperties();
	}
	
	public JPanelBackground(String imagePath) {
		initProperties();
		setBackgroundImage(imagePath);
	}
	
	private void initProperties(){
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon(imagePath).getImage(), xMargin, yMargin, getWidth(), getHeight(), null);
	}
	
	public void setBackgroundImage(String imagePath){
		this.imagePath = imagePath;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public int getxMargin() {
		return xMargin;
	}
	
	public void setxMargin(int xMargin) {
		this.xMargin = xMargin;
	}
	
	public int getyMargin() {
		return yMargin;
	}
	
	public void setyMargin(int yMargin) {
		this.yMargin = yMargin;
	}
}