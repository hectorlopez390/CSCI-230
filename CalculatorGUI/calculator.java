/*
*  Hector Lopez 
*  CSCI 230 Assignment 2
*  Purpose: Create functional calculator
*
*/
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.*;
public class calculator extends JFrame
{
	private static JButton[] numbers = new JButton[11]; //array for number buttons
	private static JButton[] operands = new JButton[4]; //array for operands buttons
	private static JButton equals = new JButton("="); //button for equals
	private static JButton clear = new JButton("C"); //button for clearing
	private static JButton settings = new JButton("Settings"); //button for calculator settings
	private static JTextField calcInput=new JTextField("", 20);
	private static String decimalSet = "";
	private static DecimalFormat df1 = new DecimalFormat("#.#");
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	private static DecimalFormat df3 = new DecimalFormat("#.###");
	private static DecimalFormat df4 = new DecimalFormat("#.####");
	int counter;
	char operation; //tells which kind of operation
	double num1, num2; //for holding values to operate on
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static JPanel thePanel = new JPanel();
    static JDialog settingsFrame = new JDialog();
    static JComboBox backgroundSelection;
    static JComboBox foregroundSelection;
    static JComboBox keypadColorSelection;
    static JComboBox decimalSelection;
    static JComboBox fontSelection;
	//function for calculating
    
    
	public static double calculate(char op, double number1, double number2)
	{
		double answer = 0;
		if(op == 'A') //addition
		{
			answer = number1 + number2;
		}
		
		if(op == 'S') //subtraction
		{
			answer = number1 - number2;
		}
		if(op == 'M') //multiplication
		{
			answer = number1 * number2;
		}
		if(op == 'D') //division
		{
			answer = number1 / number2;
		}
		return answer;
	}
	public static String formatAns(double answer)
	{
		String finalAnswer = "";
		if(decimalSet == "1")
			finalAnswer = df1.format(answer);
		else if(decimalSet == "2")
			finalAnswer  = df2.format(answer); 
		else if(decimalSet == "3")
			finalAnswer  = df2.format(answer); 
		else if(decimalSet == "4")
			finalAnswer  = df4.format(answer); 
		return finalAnswer;
	}
	
	public static void showSettings(Container pane) {
        String[] fonts = {"Default","Serif", "TimesRoman", "Helvitica", "Courier"};
        Font Dialog = new Font("DIALOG", Font.PLAIN, 13);
        Font Serif = new Font("SERIF", Font.PLAIN, 13);
        Font TimesRoman = new Font("TIMESROMAN", Font.PLAIN, 13);
        Font Helvitica = new Font("HELVITICA", Font.PLAIN, 13);
        Font Courier = new Font("COURIER", Font.PLAIN, 13);
        
        String[] decimals = {"1", "2", "3", "4"};
        String[] colors = {"Default", "Black", "Red", "Blue", "Yellow"};
        String[] colors2 = {"Black", "Red", "Blue", "Yellow"};
        
        JLabel label;
        JButton ok;

	pane.setLayout(new GridBagLayout());
	GridBagConstraints s = new GridBagConstraints();

	s.weightx = 50;
	s.weighty = 100; 
	
	s.insets = new Insets(5,5,5,5); //Defines padding top, left, bottom, right
	s.anchor = GridBagConstraints.CENTER;
	//s.fill = GridBagConstraints.BOTH;
	
	//s.anchor = GridBagConstraints.CENTER;
	s.gridx = 0;
	s.gridy = 0;
	label = new JLabel("Background");
	pane.add(label, s);
	
	
	s.gridx = 2;
	backgroundSelection = new JComboBox(colors);
	pane.add(backgroundSelection, s);

	s.gridx = 0;
	s.gridy = 1;
	label = new JLabel("Foreground");
	pane.add(label, s);

	s.gridx = 2;
	foregroundSelection = new JComboBox(colors2);
	pane.add(foregroundSelection, s);

	s.gridx = 0;
	s.gridy = 2;
	label = new JLabel("Keypad Color");
	pane.add(label, s);

	s.gridx = 2;
	keypadColorSelection = new JComboBox(colors2);
	pane.add(keypadColorSelection, s);
	
	s.gridx = 0;
	s.gridy = 3;
	label = new JLabel("Decimal");
	pane.add(label, s);
	
	s.gridx = 2;
	decimalSelection = new JComboBox(decimals);
	pane.add(decimalSelection, s);
	
	s.gridx = 0;
	s.gridy = 4;
	label = new JLabel("Font Type");
	pane.add(label, s);
	
	s.gridx = 2;     
	fontSelection = new JComboBox(fonts);
	pane.add(fontSelection, s);
	
	s.gridx = 1;
	s.gridy = 5;
	ok = new JButton("OK");
	pane.add(ok, s);
	
	ok.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String backgroundColor = (String) backgroundSelection.getSelectedItem();
			if(backgroundColor == "Default")
			{
				Color nothing = thePanel.getBackground();
				thePanel.setBackground(nothing);
			}
			else if(backgroundColor == "Black")
				thePanel.setBackground(Color.BLACK);
			else if(backgroundColor == "Red")
				thePanel.setBackground(Color.RED);
			else if(backgroundColor == "Blue")
				thePanel.setBackground(Color.BLUE);
			else
				thePanel.setBackground(Color.YELLOW);
			
			String foregroundColor = (String) foregroundSelection.getSelectedItem();
			if(foregroundColor == "Black")
			{
				calcInput.setForeground(Color.BLACK);
					
			}
			else if(foregroundColor == "Red")
			{
				calcInput.setForeground(Color.RED);
			}
			else if(foregroundColor == "Blue")
			{
				calcInput.setForeground(Color.BLUE);
			}
			else
			{
				calcInput.setForeground(Color.YELLOW);
			}

			String keypadColor = (String) keypadColorSelection.getSelectedItem();
			for(int i = 0; i < 11; i++)
			{
			
				if(keypadColor == "Black")
				{
					numbers[i].setForeground(Color.BLACK);
					for(int j = 0; j < 4; j++)
						operands[j].setForeground(Color.BLACK);
					equals.setForeground(Color.BLACK);
					clear.setForeground(Color.BLACK);
					settings.setForeground(Color.BLACK);	
				}
					else if(keypadColor == "Red")
					{
					numbers[i].setForeground(Color.RED);
					for(int j = 0; j < 4; j++)
						operands[j].setForeground(Color.RED);
					equals.setForeground(Color.RED);
					clear.setForeground(Color.RED);
					settings.setForeground(Color.RED);
				}
				else if(keypadColor == "Blue")
				{
					numbers[i].setForeground(Color.BLUE);
						for(int j = 0; j < 4; j++)
					operands[j].setForeground(Color.BLUE);
					equals.setForeground(Color.BLUE);
					clear.setForeground(Color.BLUE);
					settings.setForeground(Color.BLUE);
				}
				else
				{
					numbers[i].setForeground(Color.YELLOW);
						for(int j = 0; j < 4; j++)
					operands[j].setForeground(Color.YELLOW);
					equals.setForeground(Color.YELLOW);
					clear.setForeground(Color.YELLOW);
					settings.setForeground(Color.YELLOW);
				}
					
			}
			
			String decimalPoint = (String) decimalSelection.getSelectedItem();
			if(decimalPoint == "1")
				decimalSet = "1";
			else if(decimalPoint == "2")
				decimalSet = "2";
			else if(decimalPoint == "3")
				decimalSet = "3";
			else
				decimalSet = "4";
			
			String theFont = (String) fontSelection.getSelectedItem();
			for(int i = 0; i < 11; i++)
			{
				if(theFont == "Default")
				{
					numbers[i].setFont(Dialog);
					for(int j = 0; j < 4; j++)
						operands[j].setFont(Dialog);
					equals.setFont(Dialog);
					clear.setFont(Dialog);
					settings.setFont(Dialog);	
				}
				else if(theFont == "Serif")
				{
					numbers[i].setFont(Serif);
					for(int j = 0; j < 4; j++)
						operands[j].setFont(Serif);
					equals.setFont(Serif);
					clear.setFont(Serif);
					settings.setFont(Serif);	
				}
				else if(theFont == "TimesRoman")
				{
					numbers[i].setFont(TimesRoman);
					for(int j = 0; j < 4; j++)
						operands[j].setFont(TimesRoman);
					equals.setFont(TimesRoman);
					clear.setFont(TimesRoman);
					settings.setFont(TimesRoman);	
				}
				else if(theFont == "Helvitica")
				{
					numbers[i].setFont(Helvitica);
					for(int j = 0; j < 4; j++)
						operands[j].setFont(Helvitica);
					equals.setFont(Helvitica);
					clear.setFont(Helvitica);
					settings.setFont(Helvitica);	
				}
				else
				{
					numbers[i].setFont(Courier);
					for(int j = 0; j < 4; j++)
						operands[j].setFont(Courier);
					equals.setFont(Courier);
					clear.setFont(Courier);
					settings.setFont(Courier);	
				}
					
			}
			
			settingsFrame.dispose();
		}
	});
	
    }
	
	public calculator()
	{
		
		super("Calculator");
		
		operands[0] = new JButton("+");
		operands[1] = new JButton("-");
		operands[2] = new JButton("*");
		operands[3] = new JButton("/");
	    
		//calculator interface using gridbaglayout and jpanel

		thePanel.setLayout(new GridBagLayout());
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1; //x position of component
		gridConstraints.gridy = 1; //y position of component
		gridConstraints.gridwidth = 1; //number of columns for component
		gridConstraints.gridheight = 1;//number of rows for component
		gridConstraints.weightx = 50;
		gridConstraints.weighty = 100; 
		gridConstraints.insets = new Insets(5,5,5,5); //Defines padding top, left, bottom, right
		gridConstraints.anchor = GridBagConstraints.CENTER;
		gridConstraints.fill = GridBagConstraints.BOTH;
		
		//for loop to set what goes in each button
		for (counter = 0; counter < 11; counter++) //buttons 0 to 10
		{
				if (counter == 9) // if it is 10th button
				{
					numbers[counter] = new JButton("0"); //places #0 in 10th button
				}
				else if(counter == 10)
				{
				    numbers[counter] = new JButton(".");
				}
				else
				{
				
					numbers[counter] = new JButton(Integer.toString(counter + 1)); //else place #1-#9 in button
				}
				
				int i = counter;
				
				numbers[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (i == 9)
						{
							calcInput.setText(calcInput.getText()+"0");
						}
						else if(i == 10)
						{
							calcInput.setText(calcInput.getText()+".");
						}
						else
						{
							calcInput.setText(calcInput.getText()+(i+1));
						}
					}
				});
					
		}// end for loop
		
		//for loop for actions performed when pressing operand button
		for (counter = 0; counter < 4; counter++)
		{
				int j = counter;
				operands[j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						{
						if (j == 0) // addition
						{
							operation = 'A';
							num1 = Double.parseDouble(calcInput.getText());
							calcInput.setText("");
						}
						else if(j == 1) // subtraction
						{
							operation = 'S';
							num1 = Double.parseDouble(calcInput.getText());
							calcInput.setText("");
						}
						else if(j == 2) // multiplication
						{
							operation = 'M';
							num1 = Double.parseDouble(calcInput.getText());
							calcInput.setText("");
							
						}
						else if(j == 3) // division
						{
							operation = 'D';
							num1 = Double.parseDouble(calcInput.getText());
							calcInput.setText("");
						}
					}
					}
				});
		}// end for loop
		
		//equals button
		equals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				num2 = Double.parseDouble(calcInput.getText());
				double ans = calculate(operation, num1, num2);
				String finalAns = formatAns(ans); //calls calculating function and returns answer
				calcInput.setText(finalAns); //displays answer
			}
		});
		
		//clear calculator
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			calcInput.setText("");
			}
		});
		
		settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		        //Set up the content pane.
		        showSettings(settingsFrame.getContentPane());

		        //Display the window.
		        settingsFrame.pack();
				settingsFrame.setSize(500,480);
		        settingsFrame.setVisible(true);

			}
		});
		
		//button layout
		gridConstraints.gridwidth = 20;
		gridConstraints.gridx = 0;
		thePanel.add(calcInput, gridConstraints); //text field
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 2;
		thePanel.add(numbers[0], gridConstraints); //1
        gridConstraints.gridx = 5;
        thePanel.add(numbers[1], gridConstraints); //2
        gridConstraints.gridx = 9;
        thePanel.add(numbers[2], gridConstraints); //3
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 3;
        thePanel.add(numbers[3], gridConstraints); //4
        gridConstraints.gridx = 5;
        thePanel.add(numbers[4], gridConstraints); //5
        gridConstraints.gridx = 9;
        thePanel.add(numbers[5], gridConstraints); //6
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 4;
        thePanel.add(numbers[6], gridConstraints); //7
        gridConstraints.gridx = 5;
        thePanel.add(numbers[7], gridConstraints); //8
        gridConstraints.gridx = 9;
        thePanel.add(numbers[8], gridConstraints); //9
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 5;
        thePanel.add(numbers[9], gridConstraints); //0
        gridConstraints.gridx = 5;
        thePanel.add(numbers[10], gridConstraints); //decimal button
        gridConstraints.gridx = 9;
        thePanel.add(operands[0], gridConstraints); //addition button
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 6;
        thePanel.add(operands[1], gridConstraints); //subtraction button
        gridConstraints.gridx = 5;
        thePanel.add(operands[2], gridConstraints); //multiplication button
        gridConstraints.gridx = 9; 
        thePanel.add(operands[3], gridConstraints); //division button
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 7;
        //gridConstraints.gridwidth = 5;
        thePanel.add(equals, gridConstraints);
        gridConstraints.gridx = 5;
        thePanel.add(settings, gridConstraints);
        gridConstraints.gridx = 9;
        thePanel.add(clear, gridConstraints);
       
		this.add(thePanel);
		this.setVisible(true);
	}
 
	public static void main(String args[])
	{
		calculator frame = new calculator();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,480);
		frame.setVisible(true);
		
	}
}

