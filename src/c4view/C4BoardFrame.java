package c4view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import c4model.C4Game;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class C4BoardFrame extends JFrame implements MouseListener {

	private int rows, cols, x, y, squareSize;
	private JPanel content;
	private Color c4Blue = new Color(0,128,255);
	private Color c4Red = new Color(255,0,0);
	private Color c4Yellow = new Color(252,236,5);
	private Color c4White = new Color(255,255,255);
	private Color c4DarkRed = new Color(75,0,0);
	private Color c4DarkYellow = new Color(102,102,0);
	private Color c4DarkBlue = new Color(0,51,102);
	private Color slotColor;
	private C4WaitForInput wait;
	
	public C4BoardFrame(int size) {      
		squareSize = size;
		setSize(squareSize*7, squareSize*6);
		rows = 6;
		cols = 7;
		content = new JPanel(new GridLayout(rows,cols));
        addMouseListener(this);
        wait = new C4WaitForInput();
	}
	
	public void updateBoard(C4Game game, boolean lastFrame){
		content.removeAll();		// removes all of the sub panels from the panel 'content'
		for (int i=5; i >=0 ; i--) {
			for (int j=0; j<cols; j++) {
				if (game.getBoard().getSlot(i, j).equals("R")) {
					slotColor = (lastFrame && game.isGameOver()==true && !game.getBoard().isWinningSlot(i,j)) ? c4DarkRed : c4Red;
				} else if (game.getBoard().getSlot(i, j).equals("Y")) {
					slotColor = (lastFrame && game.isGameOver()==true && !game.getBoard().isWinningSlot(i,j)) ? c4DarkYellow : c4Yellow;
				} else {
					slotColor = c4White;
				}
				C4BoardPanel panel = new C4BoardPanel(squareSize);
				panel.setCircleColor(slotColor);
				panel.setBackground(lastFrame && game.isGameOver()==true ? c4DarkBlue : c4Blue);
				content.add(panel);
			}
		}
		this.add(content);
		content.repaint();		// we have to do this since we removed all the panels earlier.
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();	
		wait.resume();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
		wait.resume();
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
	
	public void waitForInput(){
		wait.pause();
		wait.run();
	}
	
	
}
