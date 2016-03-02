package gamesview;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class C4BoardPanel extends JPanel {
	private Color circleColor = new Color(255,255,255);;
	
	public C4BoardPanel(){
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.setColor(circleColor);
		g.fillOval(8, 8, 80, 80);
	}
	
	public void setCircleColor(Color circleColor){
		this.circleColor = circleColor;
	}
}
