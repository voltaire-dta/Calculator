/**
 * Class which handles all the data interpretation and calculation for the
 * Calculator. Updates the state of the machine and it's display value can be
 * retrieved in the Calculator class
 * 
 * @author Daniel Athey
 * @version Last Modified 04/07/2020
 *
 */

public class CalcBackend {

	// The machine's current state is represented by a member of the State enum.
	private enum State {
		READY_FOR_FIRST_OPERAND, READY_FOR_SECOND_OPERAND, CONSTRUCTING_OPERAND_LEFT_OF_DECIMAL,
		CONSTRUCTING_OPERAND_RIGHT_OF_DECIMAL
	}

	// The machine's most recently pushed button is reflected in the state of it's
	// ButtonCategory
	private enum ButtonCategory {
		DIGIT, DECIMALPOINT, EQUALSSIGN, BINARYOPERATOR, UNARYOPERATOR, CLEAR
	}

	private String displayVal;
	private State state;
	private ButtonCategory buttonCategory;

	private double operand1;
	private String previousOperator;
	private double runningTotal;

	/**
	 * CalcBackend no arg constructor
	 */
	public CalcBackend() {

		// set the machine's initial state
		this.displayVal = "";
		this.state = State.READY_FOR_FIRST_OPERAND;
		this.buttonCategory = ButtonCategory.CLEAR;

		this.operand1 = 0.0;
		this.previousOperator = "+"; // an initial value is added to zero, hence the +
		this.runningTotal = 0.0;
	}

	/**
	 * Method which handles all the backend case structure for each possible input
	 * into the calculator. Performs calcs and determines what will be displayed on
	 * the screen.
	 * 
	 * @param c the button which was pressed
	 */
	public void feedChar(char c) {

		// determine what type of button category state the machine is in
		// this will determine the route of the program as it represents which
		// button was pushed
		String s = Character.toString(c);
		if (Character.isDigit(c)) {
			this.buttonCategory = ButtonCategory.DIGIT;
		} else if (s.equals(".")) {
			this.buttonCategory = ButtonCategory.DECIMALPOINT;
		} else if (s.equals("=")) {
			this.buttonCategory = ButtonCategory.EQUALSSIGN;
		} else if (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")) {
			this.buttonCategory = ButtonCategory.BINARYOPERATOR;
		}
		// ? is our key for +/-
		else if (s.equals("√") || s.equals("?")) {
			this.buttonCategory = ButtonCategory.UNARYOPERATOR;
		} else {
			this.buttonCategory = ButtonCategory.CLEAR;
		}

		// STATE SWITCH
		// this is where the heavy lifting occurs. Switch statements
		// for the various possible states and buttons pressed on
		// the machine
		switch (this.state) {

		case READY_FOR_FIRST_OPERAND:
			switch (this.buttonCategory) {
			case DIGIT:
				this.displayVal = s;
				this.state = State.CONSTRUCTING_OPERAND_LEFT_OF_DECIMAL;
				break;
			case DECIMALPOINT:
				this.displayVal = s;
				this.state = State.CONSTRUCTING_OPERAND_RIGHT_OF_DECIMAL;
				break;
			case EQUALSSIGN:
				this.displayVal = "0.0";
				break;
			case BINARYOPERATOR:
				this.displayVal = "0.0";
				break;
			case UNARYOPERATOR:
				this.displayVal = "0.0";
				break;
			// clear button
			default:
				this.displayVal = "0";
				break;
			}
			break;
		case READY_FOR_SECOND_OPERAND:
			switch (this.buttonCategory) {
			case DIGIT:
				this.displayVal = s;
				this.state = State.CONSTRUCTING_OPERAND_LEFT_OF_DECIMAL;
				break;
			case DECIMALPOINT:
				this.displayVal = s;
				this.state = State.CONSTRUCTING_OPERAND_RIGHT_OF_DECIMAL;
			case EQUALSSIGN:
				break;
			case BINARYOPERATOR:
				this.previousOperator = s;
				break;
			case UNARYOPERATOR:
				handleUnaryOperators(s);
				break;
			// clear button
			default:
				this.displayVal = "0";
				break;
			}
			break;
		case CONSTRUCTING_OPERAND_LEFT_OF_DECIMAL:
			switch (this.buttonCategory) {
			case DIGIT:
				this.displayVal += s;
				break;
			case DECIMALPOINT:
				this.displayVal += s;
				this.state = State.CONSTRUCTING_OPERAND_RIGHT_OF_DECIMAL;
				break;
			case EQUALSSIGN:
				readAndDisplayCalculation(s);
				break;
			case BINARYOPERATOR:
				readAndDisplayCalculation(s);
				break;
			case UNARYOPERATOR:
				handleUnaryOperators(s);
				break;
			// clear button
			default:
				this.displayVal = "0";
				break;
			}
			break;
		case CONSTRUCTING_OPERAND_RIGHT_OF_DECIMAL:
			switch (this.buttonCategory) {
			case DIGIT:
				this.displayVal += s;
				break;
			case DECIMALPOINT:
				// you can't do that
				break;
			case EQUALSSIGN:
				readAndDisplayCalculation(s);
				break;
			case BINARYOPERATOR:
				readAndDisplayCalculation(s);
				break;
			case UNARYOPERATOR:
				handleUnaryOperators(s);
				break;
			// clear button
			default:
				this.displayVal = "0";
				break;
			}
			break;
		default:
			break;
		}
	}

	/**
	 * This method handles the initial reading and display of the result of the
	 * calculation. It calls runningCalculation() to handle the actual calculations
	 * for it
	 * 
	 * @param operator the operator which will be used in the calculation
	 */
	private void readAndDisplayCalculation(String operator) {
		String s = operator;
		this.operand1 = Double.parseDouble(this.displayVal);
		runningCalculation(s, this.operand1);
		this.displayVal = runningTotal + ""; // prepare the screen for next operand
		this.state = State.READY_FOR_SECOND_OPERAND;
	}

	/**
	 * one of the challenges of the calculator is that when a new operator is
	 * pressed, the previous calculation's total becomes the new first operator and
	 * we need to be ready for a NEW, second operator. This method handles that
	 * storage / calculation for us.
	 * 
	 * @param operator the operator which will be used in the next iteration of this
	 *                 method
	 * @param value    the value which will be added to the old first operand to
	 *                 make the new first operand
	 */
	private void runningCalculation(String operator, double value) {
		switch (this.previousOperator) {
		case "+":
			runningTotal += value;
			break;
		case "-":
			runningTotal -= value;
			break;
		case "*":
			runningTotal *= value;
			break;
		case "/":
			runningTotal /= value;
			break;
		//in the following three cases, we do nothing since the calculation 
		//is already done
		case "=":
			break;
		case "?":
			break;
		case "√":
			break;
		}
		this.previousOperator = operator; // set operator for next calculation
	}

	/**
	 * We handle the unary operator the same way every time, so in order to prevent
	 * duplication of code we just outsource the work to this method
	 * 
	 * @param operator the unary operator we are working with
	 */
	private void handleUnaryOperators(String operator) {
		String s = operator;
		switch (s) {
		case "?":
			double toggleValue = -(Double.parseDouble(this.displayVal));
			this.displayVal = toggleValue + "";
			// this is necessary since the toggle button turns the output field
			// into double form. Unless we change state, additional decimal point would be
			// allowed
			this.state = State.CONSTRUCTING_OPERAND_RIGHT_OF_DECIMAL;
			break;
		case "√":
			double squareRootValue = Math.sqrt(Double.parseDouble(this.displayVal));
			if ((squareRootValue + "").equals("NaN")) {
				this.displayVal = "ERROR";
				break;
			}
			this.displayVal = squareRootValue + "";
			break;
		}
	}

	// returns what the calculator should show
	// quickly convert display to double and back
	// to string to enforce char limits 
	public String getDisplayVal() {
		if (this.displayVal.equals("ERROR")) {
			return this.displayVal;
		}
		double x = Double.parseDouble(this.displayVal);
		return x + "";
	}
}
