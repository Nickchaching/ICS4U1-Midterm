import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class testingpanel implements ActionListener{

    // Properties

    // Panel and Scroll
    JFrame theFrame = new JFrame();
    JPanel testPanel = new JPanel();
    JScrollPane theScroll = new JScrollPane(testPanel);
    // Enter Name
	JLabel nameText = new JLabel("Enter your name:");
	JTextField nameField = new JTextField();
	// Question 1
	JLabel question1Text = new JLabel("Which ratio uses the sides that are the opposite and hypotenuse to the specified angle?");
	JRadioButton question1RadioA = new JRadioButton("Sin");
	JRadioButton question1RadioB = new JRadioButton("Cos");
	JRadioButton question1RadioC = new JRadioButton("Tan");
	ButtonGroup group1 = new ButtonGroup();
	// Submit Button
    JButton submitButton = new JButton("Submit");
	
	


    // Methods
    public void paintComponent(Graphics g){
        // Variables
        // Enter Name
        

        // Load Question Text File into Array
        // Show Question on Screen
        // User Inputs Answer
        // Add Points to Score
    }
    public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == submitButton){
            System.out.println("Submit");
        }
	}
    
    // Main Method
    public static void main(String[] args){
			new testingpanel();
	}

    // Constructor
    public testingpanel(){
		testPanel.setLayout(null);
		testPanel.setPreferredSize(new Dimension(960, 1000));
		theScroll.setPreferredSize(new Dimension(960, 540));
		
		// Name
		nameText.setSize(900, 30);
		nameText.setLocation(20, 60);
		testPanel.add(nameText);
		nameField.setSize(900,30);
		nameField.setLocation(20, 120);		
		testPanel.add(nameField);
		
		// Question 1
		question1Text.setSize(900, 30);
		question1Text.setLocation(20, 180);
		testPanel.add(question1Text);
		// Question 1 Answers
		question1RadioA.setSize(900, 30);
		question1RadioB.setSize(900, 30);
		question1RadioC.setSize(900, 30);
		question1RadioA.setLocation(20, 210);
		question1RadioB.setLocation(20, 240);
		question1RadioC.setLocation(20, 270);
		testPanel.add(question1RadioA);
		testPanel.add(question1RadioB);
		testPanel.add(question1RadioC);
		group1.add(question1RadioA);
		group1.add(question1RadioB);
		group1.add(question1RadioC);
		// Question 2
		// Question 2 Answers

        // Submit Button
        submitButton.setSize(100, 30);
        submitButton.setLocation(20,330);
        testPanel.add(submitButton);
		
        // Panel and Scroll		
		theFrame.setContentPane(theScroll);
        theFrame.pack();
        theFrame.setVisible(true);
    }
    
}
