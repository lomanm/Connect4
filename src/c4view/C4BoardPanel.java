package c4view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class C4BoardPanel extends JPanel {
	private Color circleColor = new Color(255,255,255);
	private int squareSize;
	
	public C4BoardPanel(int size){
		squareSize = size;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.setColor(circleColor);
		g.fillOval((squareSize*8)/100, (squareSize*8)/100, (squareSize*4)/5, (squareSize*4)/5);
	}
	
	public void setCircleColor(Color circleColor){
		this.circleColor = circleColor;
	}
}
