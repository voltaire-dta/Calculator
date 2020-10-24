import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Main class which only contains the main
 * method that creates the calculator, sizes
 * it and makes it visible to the user. 
 * @author Daniel Athey
 * @version Last Modified 04/07/20
 */
public class Calculator {

	public static void main(String[] args) {
		
		MyCalculator calculator = new MyCalculator();
		
		calculator.setSize(300, 300);
		
		calculator.setVisible(true);

	}

}
/**
 * subclass of JFrame designed to specifically create a 
 * calculator. Contains the various buttons you might expect
 * on a calculator
 * @author Daniel Athey
 * @version Last Modified 04/07/20
 */
class MyCalculator extends JFrame {

	
	private JTextField inputField;
	private JButton clearButton;
	private JButton squareRootSign;
	private JButton plusSign;
	private JButton minusSign;
	private JButton divisionSign;
	private JButton multiplicationSign;
	private JButton equalsSign;
	private JButton toggleSign;
	private JButton paperTapeButton;
	private JTextArea paperTapeSurface;
	private JFrame paperTape;
	
	private JButton oneButton,
					twoButton,
					threeButton,
					fourButton,
					fiveButton,
					sixButton,
					sevenButton,
					eightButton,
					nineButton,
					zeroButton,
					decimalButton;
	
	private CalcBackend calcBackend;
	
	/**
	 * constructor for the MyCalculator object
	 * which extends JFrame
	 */
	public MyCalculator() {
		
		assembleComponents();
		
		//add listeners for each button
		addActionListeners();
		
		//center the calculator
		this.pack();
		this.setLocationRelativeTo(null);
		
		// Tell the jvm to kill the program when the window closes.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	/**
	 * creates and displays the actual front end display
	 * for the calculator. 
	 */
	private void assembleComponents() {		

		this.setTitle("Calculator");
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//paper tape
		this.paperTape = new JFrame("Paper Tape");
		this.paperTapeSurface = new JTextArea();
		paperTapeSurface.setEditable(false); //we can't type in here
		JScrollPane jsp = new JScrollPane(paperTapeSurface);
		this.paperTape.getContentPane().add(jsp);
		this.paperTape.setSize(300, 400);
		this.paperTape.setVisible(true);
		
		//row one
		this.inputField = new JTextField("0.0");
		this.inputField.setBackground(Color.LIGHT_GRAY);
		this.inputField.setEditable(false);
		this.inputField.setHorizontalAlignment(SwingConstants.RIGHT);
		Font f = new Font("Times", Font.PLAIN, 24);
		this.inputField.setFont(f);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.ipady = 20;
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = .25;
		gbc.weighty = .20;
		this.add(inputField, gbc);
		
		//row two
		this.clearButton = new JButton("C");
		this.clearButton.setForeground(Color.WHITE);
		this.clearButton.setBackground(Color.BLACK);
		f = new Font("Times", Font.PLAIN, 18);
		this.clearButton.setFont(f);
		gbc.ipady = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(clearButton,gbc);
		
		this.squareRootSign = new JButton("√");
		this.squareRootSign.setForeground(Color.WHITE);
		this.squareRootSign.setBackground(Color.BLACK);
		this.squareRootSign.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(squareRootSign,gbc);
		
		this.divisionSign = new JButton("/");
		this.divisionSign.setForeground(Color.WHITE);
		this.divisionSign.setBackground(Color.BLACK);
		this.divisionSign.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 1;
		this.add(divisionSign,gbc);
		
		this.multiplicationSign = new JButton("*");
		this.multiplicationSign.setForeground(Color.WHITE);
		this.multiplicationSign.setBackground(Color.BLACK);
		this.multiplicationSign.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 1;
		this.add(multiplicationSign,gbc);
		
		//row three
		this.sevenButton = new JButton("7");
		this.sevenButton.setBackground(Color.WHITE);
		this.sevenButton.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(sevenButton,gbc);
		
		this.eightButton = new JButton("8");
		this.eightButton.setBackground(Color.WHITE);
		this.eightButton.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(eightButton,gbc);	
		
		this.nineButton = new JButton("9");
		this.nineButton.setBackground(Color.WHITE);
		this.nineButton.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 2;
		this.add(nineButton,gbc);
		
		this.minusSign = new JButton("-");
		this.minusSign.setForeground(Color.WHITE);
		this.minusSign.setBackground(Color.BLACK);	
		this.minusSign.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 2;
		this.add(minusSign,gbc);
		
		//row four
		this.fourButton = new JButton("4");
		this.fourButton.setBackground(Color.WHITE);	
		this.fourButton.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(fourButton,gbc);
		
		this.fiveButton = new JButton("5");
		this.fiveButton.setBackground(Color.WHITE);	
		this.fiveButton.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(fiveButton,gbc);
		
		this.sixButton = new JButton("6");
		this.sixButton.setBackground(Color.WHITE);	
		this.sixButton.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 3;
		this.add(sixButton,gbc);
		
		this.plusSign = new JButton("+");
		this.plusSign.setForeground(Color.WHITE);
		this.plusSign.setBackground(Color.BLACK);
		this.plusSign.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 3;
		this.add(plusSign,gbc);
		
		//row five
		this.oneButton = new JButton("1");
		this.oneButton.setBackground(Color.WHITE);
		this.oneButton.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(oneButton,gbc);
		
		this.twoButton = new JButton("2");
		this.twoButton.setBackground(Color.WHITE);
		this.twoButton.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.add(twoButton,gbc);
		
		this.threeButton = new JButton("3");
		this.threeButton.setBackground(Color.WHITE);
		this.threeButton.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 4;
		this.add(threeButton,gbc);
		
		this.equalsSign = new JButton("=");
		this.equalsSign.setForeground(Color.WHITE);
		this.equalsSign.setBackground(Color.BLACK);	
		this.equalsSign.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridheight = 2;
		this.add(equalsSign,gbc);
		
		//row six
		this.zeroButton = new JButton("0");
		this.zeroButton.setBackground(Color.WHITE);
		this.zeroButton.setFont(f);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(zeroButton,gbc);
		
		this.decimalButton = new JButton(".");
		this.decimalButton.setForeground(Color.WHITE);
		this.decimalButton.setBackground(Color.BLACK);	
		this.decimalButton.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 5;
		this.add(decimalButton,gbc);
		
		this.toggleSign = new JButton("+/-");
		this.toggleSign.setForeground(Color.WHITE);
		this.toggleSign.setBackground(Color.BLACK);	
		this.toggleSign.setFont(f);
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 5;
		this.add(toggleSign,gbc);	
		
		//row 7
		this.paperTapeButton = new JButton("Paper Tape (ON / OFF)");
		this.paperTapeButton.setForeground(Color.WHITE);
		this.paperTapeButton.setBackground(Color.BLACK);
		this.paperTapeButton.setFont(f);
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 7;
		this.add(paperTapeButton,gbc);
		

	}
	/**
	 * adds action listeners to the various
	 * buttons on our calculator which sends
	 * the button pressed to the backend and
	 * retrieves the updated display value 
	 * after any calculations or transformations
	 * are performed 
	 */
	private void addActionListeners() {
		
		calcBackend = new CalcBackend();
		
		JButton[] buttons  = {oneButton,
								twoButton,
								threeButton,
								fourButton,
								fiveButton,
								sixButton,
								sevenButton,
								eightButton,
								nineButton,
								zeroButton,
								decimalButton,
								plusSign,
								minusSign,
								divisionSign,
								multiplicationSign,
								squareRootSign,
								clearButton,
								equalsSign,
								toggleSign,
								paperTapeButton};
		
		//add listeners for the buttons
		for (JButton j : buttons) {
			j.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					paperTapeSurface.append("You Typed: " + j.getText() + "\n");
					//we need a special case for buttons with >1 chars (i.e. our toggle button)
					if (j.getText().equals("+/-")) {
						calcBackend.feedChar('?');
					} 
					//and for the paper tape button
					else if (j.getText().equals("Paper Tape (ON / OFF)")) {
						togglePaperTapeVisibility();
					}
					//everything else is only one char
					else {
						calcBackend.feedChar(j.getText().charAt(0));
					}
					inputField.setText(calcBackend.getDisplayVal());
				}
			});
		}
	}
	/**
	 * helper method to handle the visibility of the paper tape
	 */
	private void togglePaperTapeVisibility() {
		if (this.paperTape.isVisible()) {
			this.paperTape.setVisible(false);
		}
		else {
			this.paperTape.setVisible(true);
		}
	}
}
