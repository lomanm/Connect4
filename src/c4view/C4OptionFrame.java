package c4view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class C4OptionFrame extends JFrame implements MouseListener {
	private String[] options;
	
	public C4OptionFrame(int size) {           
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setTitle("Connect 4 - Options");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setSize(size*7, size*6);
        addMouseListener(this);
        this.setVisible(true);
	}
	
	public String[] gameOptions(){
		options = new String[] {"R", "1", "1"};	// color, move choice, difficulty level
		Color bgColor = new Color(230,243,255);
		Font bigFont = new Font("Serif", Font.BOLD, 24);
		Font medFont = new Font("Serif", Font.BOLD, 14);
		Font smallFont = new Font("Serif", Font.BOLD, 12);
		
		// CREATE A JPANEL OF OPTIONS
		JPanel optPanel = new JPanel(new GridLayout(8,1));
		optPanel.setBackground(bgColor);
		
		// ADD HEADER TEXT LABEL
		JPanel row1 = new JPanel();
		row1.setBackground(bgColor);
		JTextArea optionText = new JTextArea("Connect 4 Options:");		// add a text label
		optionText.setBackground(bgColor);
		optionText.setFont(bigFont);
		row1.add(optionText);
		optPanel.add(row1);
		
		// ADD 1ST SET OF RADIO BUTTONS
		JPanel row2 = new JPanel();
		row2.setBackground(bgColor);
		JLabel colorText = new JLabel("Choose red or yellow:");		// add a text label
		colorText.setFont(medFont);
		ButtonGroup buttonColorGroup = new ButtonGroup();			// radio buttons for color choice
		JRadioButton b1 = new JRadioButton("Red");
		JRadioButton b2 = new JRadioButton("Yellow");
		b1.setFont(medFont);
		b2.setFont(medFont);
		//b1.setBackground(bgColor);
		//b2.setBackground(bgColor);
		b1.setBackground(Color.RED);
		b2.setBackground(Color.YELLOW);
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
        b1.addActionListener(colorListener);
		b2.addActionListener(colorListener);
		row2.add(colorText);
		row2.add(b1);
		row2.add(b2);
		optPanel.add(row2);
		
		// ADD 2ND SET OF RADIO BUTTONS
		JPanel row3 = new JPanel();
		row3.setBackground(bgColor);
		JLabel moveText = new JLabel("Choose 1st or 2nd move:");	// add a text label
		moveText.setFont(medFont);
		ButtonGroup buttonMoveGroup = new ButtonGroup();			// radio buttons for move choice
		JRadioButton b3 = new JRadioButton("1st");
		JRadioButton b4 = new JRadioButton("2nd");
		b3.setFont(medFont);
		b4.setFont(medFont);
		b3.setBackground(bgColor);
		b4.setBackground(bgColor);
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
		b3.addActionListener(moveListener);
		b4.addActionListener(moveListener);
		row3.add(moveText);
		row3.add(b3);
		row3.add(b4);
		optPanel.add(row3);
		
		// ADD 3RD SET OF RADIO BUTTONS
		JPanel row4 = new JPanel();
		row4.setBackground(bgColor);
		JLabel hardText = new JLabel("Choose difficulty level:");	// add a text label
		hardText.setFont(medFont);
		ButtonGroup buttonHardGroup = new ButtonGroup();			// radio buttons for difficulty level
		JRadioButton b5 = new JRadioButton("Easy");
		JRadioButton b6 = new JRadioButton("Difficult");
		b5.setFont(medFont);
		b6.setFont(medFont);
		b5.setBackground(bgColor);
		b6.setBackground(bgColor);
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
		b5.addActionListener(hardListener);
		b6.addActionListener(hardListener);
		row4.add(hardText);
		row4.add(b5);
		row4.add(b6);
		optPanel.add(row4);
		
		// ADD OBJECT OF GAME TEXT LABEL
		JPanel row5 = new JPanel();
		row5.setBackground(bgColor);
		JTextArea objectText = new JTextArea("OBJECT OF GAME:  Connect 4 of your chips before your opponent.");		// add a text label
		objectText.setBackground(bgColor);
		objectText.setFont(smallFont);
		row5.add(objectText);
		optPanel.add(row5);

		// ADD MORE TEXT LABEL
		JPanel row6 = new JPanel();
		row6.setBackground(bgColor);
		JTextArea object2Text = new JTextArea("The chips may be arranged horizontally, vertically, or diagonally.");		// add a text label
		object2Text.setBackground(bgColor);
		object2Text.setFont(smallFont);
		row6.add(object2Text);
		optPanel.add(row6);

		// ADD INSTRUCTIONS TEXT LABEL
		JPanel row7 = new JPanel();
		row7.setBackground(bgColor);
		JTextArea instText = new JTextArea("HOW TO PLAY:  On your turn, click on a column to drop a chip into that column.");		// add a text label
		instText.setBackground(bgColor);
		instText.setFont(smallFont);
		row7.add(instText);
		optPanel.add(row7);
				
		// ADD START BUTTON
		JPanel row8 = new JPanel();
		row8.setBackground(bgColor);
		C4WaitForInput wait = new C4WaitForInput();				// this object allows waiting for user input
		JButton OKButton = new JButton("Start");				// this button starts the game
		row8.add(OKButton,BorderLayout.CENTER);
		ActionListener okListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wait.resume();
			}
		};
		OKButton.addActionListener(okListener);
		optPanel.add(row8);
		
		// NOW PUT IT ALL TOGETHER AND WAIT FOR INPUT
		this.add(optPanel);
		optPanel.repaint();
		optionDelay();				// this is necessary to prevent the screen from not painting (about one tenth of the time)
		this.setVisible(true);
		wait.pause();
		wait.run();					// waits for user input
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
