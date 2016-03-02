package gamesview;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import gamesmodel.C4Model;

@SuppressWarnings("serial")
public class C4BoardFrame extends JFrame implements MouseListener {
	private int rows, cols, x, y;
	private JPanel content;
	private double[] lineCoords;
	private Color c4Blue = new Color(0,128,255);
	private Color c4Red = new Color(255,0,0);
	private Color c4Yellow = new Color(252,236,5);
	private Color c4White = new Color(255,255,255);
	private Color c4DarkRed = new Color(102,0,0);
	private Color c4DarkYellow = new Color(153,153,0);
	private Color c4DarkBlue = new Color(0,90,180);
	private Color slotColor;
	
	public C4BoardFrame() {           
		this.setSize(700, 600);
		this.rows = 6;
		this.cols = 7;
		this.content = new JPanel(new GridLayout(rows,cols));
        addMouseListener(this);
        this.lineCoords = new double[] {0.0,0.0,0.0,0.0};
	}
	
	public void updateBoard(C4Model model){
		content.removeAll();		// removes all of the sub panels from the panel 'content'
		for (int i=5; i >=0 ; i--) {
			for (int j=0; j<cols; j++) {
				if (model.getBoard().getSlot(i, j).equals("R")) {
					slotColor = (model.getBoard().isGameOver()==true && !model.getBoard().isWinningSlot(i,j)) ? c4DarkRed : c4Red;
				} else if (model.getBoard().getSlot(i, j).equals("Y")) {
					slotColor = (model.getBoard().isGameOver()==true && !model.getBoard().isWinningSlot(i,j)) ? c4DarkYellow : c4Yellow;
				} else {
					slotColor = c4White;
				}
				C4BoardPanel panel = new C4BoardPanel();
				panel.setCircleColor(slotColor);
				panel.setBackground(model.getBoard().isGameOver()==true ? c4DarkBlue : c4Blue);
				content.add(panel);
			}
		}
		this.add(content);
		content.repaint();		// we have to do this since we removed all the panels earlier.
	}
	
	// may get rid of this code if don't print the line
	public void winningBoard(C4Model model) {
		// translate slot coordinates like (0,0) to pixels coordinates like (550,50)
		lineCoords[0] = (double) ((6-model.getBoard().getWinCoords()[0])*100) - 50 + (5*model.getBoard().getWinCoords()[0]);
		lineCoords[1] = (double) (model.getBoard().getWinCoords()[1]*100)+50;		//	ok
		lineCoords[2] = (double) ((6-model.getBoard().getWinCoords()[2])*100) - 50 + (5*model.getBoard().getWinCoords()[2]);
		lineCoords[3] = (double) (model.getBoard().getWinCoords()[3]*100)+50;		//	ok
	}
	
	// may get rid of this code if don't print the line
	public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Double(lineCoords[1], lineCoords[0], lineCoords[3], lineCoords[2]);
        g2.setStroke(new BasicStroke(5));
        g2.draw(lin);
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
