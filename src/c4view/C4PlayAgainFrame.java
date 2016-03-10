package c4view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class C4PlayAgainFrame extends JWindow {
//public class C4PlayAgainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int quitChoice;
	private JPanel againPanel;

	public C4PlayAgainFrame(){
		//this.setUndecorated(true);
		this.setSize(225, 35);
		this.setLocation(150, 225);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		//this.setTitle("Play Again?");
		//this.setResizable(false);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		//this.repaint();
		againPanel = new JPanel(new GridLayout(1,1));
	}
	
	
	public boolean playAgain() {
		quitChoice = 0;
		
		C4WaitForInput wait = new C4WaitForInput();
		
		JButton button1 = new JButton("Play Again");
		JButton button2 = new JButton("Quit");
		againPanel.add(button1);
		againPanel.add(button2);
		ActionListener againListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitChoice = 1;
				wait.resume();
			}
		};
		ActionListener quitListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitChoice = 2;
				wait.resume();
			}
		};
		button1.addActionListener(againListener);
		button2.addActionListener(quitListener);
		againPanel.setVisible(true);
		againPanel.repaint();
		
		this.add(againPanel);
		playAgainDelay();				// this is necessary to prevent the screen from not painting (about one tenth of the time)
		this.repaint();
		this.setVisible(true);
		
		wait.pause();
		wait.run();
		return quitChoice==1 ? true : false;
	}
	
	public void playAgainDelay(){
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
