package gamesview;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class C4OptionFrame extends JFrame implements MouseListener {
	private JPanel content2;
	private String[] options;
	boolean keepWaiting;
	
	public C4OptionFrame() {           
		this.setSize(700, 600);
		this.content2 = new JPanel(new GridLayout(1,1));
        addMouseListener(this);
	}
	
	public String[] gameOptions(){
		options = new String[] {"R", "1", "1"};	// color, move choice, difficulty level
		keepWaiting = true;
		//JPanel optionPanel = new JPanel();			// create the panel of options
		//optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.PAGE_AXIS));
		
		JLabel ColorText = new JLabel("Choose red or yellow:");		// add a text label
		//optionPanel.add(ColorText);
		this.add(ColorText);
		ButtonGroup buttonColorGroup = new ButtonGroup();			// radio buttons for color choice
		JRadioButton b1 = new JRadioButton("Red");
		JRadioButton b2 = new JRadioButton("Yellow");
		b1.setSelected(true);
		ActionListener colorListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Red") {
		    	    options[0] = "R";
		        } else {
		        	options[0] = "Y";
		        }
				
			}
		};
		buttonColorGroup.add(b1);
		buttonColorGroup.add(b2);
		//optionPanel.add(b1);
		//optionPanel.add(b2);
		this.add(b1);
		this.add(b2);
        b1.addActionListener(colorListener);
		b2.addActionListener(colorListener);
		
		JLabel MoveText = new JLabel("Choose 1st or 2nd move:");	// add a text label
		//optionPanel.add(MoveText);
		this.add(MoveText);
		ButtonGroup buttonMoveGroup = new ButtonGroup();			// radio buttons for move choice
		JRadioButton b3 = new JRadioButton("1st");
		JRadioButton b4 = new JRadioButton("2nd");
		b3.setSelected(true);
		ActionListener moveListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "1st") {
		    	    options[1] = "1";
		        } else {
		        	options[1] = "2";
		        }
			}
		};
		buttonMoveGroup.add(b3);
		buttonMoveGroup.add(b4);
		//optionPanel.add(b3);
		//optionPanel.add(b4);
		this.add(b3);
		this.add(b4);
		b3.addActionListener(moveListener);
		b4.addActionListener(moveListener);
		
		JLabel hardText = new JLabel("Choose difficulty level:");	// add a text label
		//optionPanel.add(hardText);
		this.add(hardText);
		ButtonGroup buttonHardGroup = new ButtonGroup();			// radio buttons for difficulty level
		JRadioButton b5 = new JRadioButton("Easy");
		JRadioButton b6 = new JRadioButton("Difficult");
		b5.setSelected(true);
		ActionListener hardListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Easy") {
		    	    options[2] = "1";
		        } else {
		        	options[2] = "2";
		        }
			}
		};
		buttonHardGroup.add(b5);
		buttonHardGroup.add(b6);
		//optionPanel.add(b5);
		//optionPanel.add(b6);
		this.add(b5);
		this.add(b6);
		b5.addActionListener(hardListener);
		b6.addActionListener(hardListener);
		
		JButton OKButton = new JButton("Start");					// this button starts the game
		//optionPanel.add(OKButton,BorderLayout.CENTER);
		this.add(OKButton,BorderLayout.CENTER);
		ActionListener okListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keepWaiting = false;		// button had been pressed
			}
		};
		OKButton.addActionListener(okListener);
		
		//content2.add(optionPanel);
		//this.add(content2);
		optionDelay();				// this is necessary to prevent the screen from not painting (about ne tenth of the time)
		content2.repaint();
		while (keepWaiting){		// this infinite loop waits for the users input
			optionDelay();			// but the loop doesn't work without this slow-down mechanism
		}
		return options;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
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
	
	public void optionDelay(){
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
