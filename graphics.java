import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;

public class graphics implements ActionListener, MouseMotionListener, MouseListener, ChangeListener, MenuListener{
    //Properties
    Font theFont15 = new Font("Dialog", 1, 15);
    Font theFont30 = new Font("Dialog", 1, 30);
    int intPanelSelected = 1;
    int intCount;
    int intEntries;
    String strScores[][];

    //Panels, Frames, Timers
    JFrame theFrame = new JFrame("SOH CAH TOA SIMULATOR");
    panelgraphics thePanel = new panelgraphics();
    testingpanel theTestPanel = new testingpanel();
    JScrollPane theTestScroll = new JScrollPane(theTestPanel);
    helppanel theHelpPanel = new helppanel();
    aboutpanel theAboutPanel = new aboutpanel();
    scorepanel theScorePanel = new scorepanel();
    JScrollPane theScoreScroll = new JScrollPane(theScorePanel);
    Timer newFrame = new Timer(1000 / 48, this);

    //Menu Bar
    JMenuBar theBar = new JMenuBar();
    JMenu homeMenu = new JMenu("Home");
    JMenu quizMenu = new JMenu("Quiz");
    JMenu scoreMenu = new JMenu("Scores");
    JMenu aboutMenu = new JMenu("About");
    JMenu helpMenu = new JMenu("Help");

    //____________
    //MAIN PANEL
    //Buttons
    JButton sinButton = new JButton("Sin");
    JButton cosButton = new JButton("Cos");
    JButton tanButton = new JButton("Tan");
    JButton demoButton = new JButton("Show/Hide Demonstration");
    JLabel aLabel = new JLabel("Side A: ", JLabel.CENTER);
    JLabel bLabel = new JLabel("Side B: ", JLabel.CENTER);
    
    //Sliders
    JSlider slideSideA = new JSlider(JSlider.HORIZONTAL, 0, 32, 10);
    JSlider slideSideB = new JSlider(JSlider.HORIZONTAL, 0, 25, 10);

    //___________
    //QUIZ PANEL
    //Enter Name
	JLabel nameText = new JLabel("Enter your name:");
	JTextField nameField = new JTextField();
	
    //Question 1
	JLabel question1Text = new JLabel("Which ratio uses the sides that are the opposite and hypotenuse to the specified angle?");
	JRadioButton question1RadioA = new JRadioButton("Sin");
	JRadioButton question1RadioB = new JRadioButton("Cos");
	JRadioButton question1RadioC = new JRadioButton("Tan");
	ButtonGroup group1 = new ButtonGroup();
    
    //Question 2
    JLabel question2Text = new JLabel("What kind of shape do you usually use trigonometry for?");
	JRadioButton question2RadioA = new JRadioButton("Trapezoid");
	JRadioButton question2RadioB = new JRadioButton("Square");
	JRadioButton question2RadioC = new JRadioButton("Triangle");
	ButtonGroup group2 = new ButtonGroup();
    
    //Question 3
    JLabel question3Text = new JLabel("Which side is the longest side of a right triangle?");
	JRadioButton question3RadioA = new JRadioButton("Adjacent");
	JRadioButton question3RadioB = new JRadioButton("Opposite");
	JRadioButton question3RadioC = new JRadioButton("Hypotenuse");
	ButtonGroup group3 = new ButtonGroup();
    
    //Question 4
    JLabel question4Text = new JLabel("How can you find an angle when given the opposite and adjacent side?");
	JRadioButton question4RadioA = new JRadioButton("Inverse of tangent");
	JRadioButton question4RadioB = new JRadioButton("Square of sine");
	JRadioButton question4RadioC = new JRadioButton("Reciprocal of tangent");
	ButtonGroup group4 = new ButtonGroup();
    
    //Question 5
    JLabel question5Text = new JLabel("What is the largest possible angle in a right triangle?");
	JRadioButton question5RadioA = new JRadioButton(">90 degrees");
	JRadioButton question5RadioB = new JRadioButton("90 degrees");
	JRadioButton question5RadioC = new JRadioButton("<90 degrees");
	ButtonGroup group5 = new ButtonGroup();
	
    //Submit Button
    JButton submitButton = new JButton("Submit");
    JLabel correctLabel1 = new JLabel("Correct!");
    JLabel correctLabel2 = new JLabel("Correct!");
    JLabel correctLabel3 = new JLabel("Correct!");
    JLabel correctLabel4 = new JLabel("Correct!");
    JLabel correctLabel5 = new JLabel("Correct!");
    JLabel incorrectLabel1 = new JLabel("Incorrect!");
    JLabel incorrectLabel2 = new JLabel("Incorrect!");
    JLabel incorrectLabel3 = new JLabel("Incorrect!");
    JLabel incorrectLabel4 = new JLabel("Incorrect!");
    JLabel incorrectLabel5 = new JLabel("Incorrect!");
    
    //___________
    //SCORE PANEL
    //Scores
    JLabel scoreTitleLabel = new JLabel("High Scores", JLabel.CENTER);
    JLabel scoreNamesLabel = new JLabel("");
    JLabel scoreNumbersLabel = new JLabel("");

    // Methods
    public void actionPerformed(ActionEvent evt){
        //New Frame Generation
        if(evt.getSource() == newFrame){
            if(intPanelSelected == 1){
                thePanel.repaint();
            }
            else if(intPanelSelected == 2){
                theTestScroll.repaint();
            }
            else if(intPanelSelected == 3){
                theHelpPanel.repaint();
            }
            else if(intPanelSelected == 4){
                theAboutPanel.repaint();
            }
            else if(intPanelSelected == 5){
                theScoreScroll.repaint();
            }
        }

        //SIN/COS/TAN Highlighting
        else if(evt.getSource() == sinButton){
            if(thePanel.blnShow = true){
                thePanel.blnShow = false;
            }
            thePanel.intTrigSelected = 1;
        }
        else if(evt.getSource() == cosButton){
            if(thePanel.blnShow = true){
                thePanel.blnShow = false;
            }
            thePanel.intTrigSelected = 2;
        }
        else if(evt.getSource() == tanButton){
            if(thePanel.blnShow = true){
                thePanel.blnShow = false;
            }
            thePanel.intTrigSelected = 3;
        }
        else if(evt.getSource() == demoButton){
            if(thePanel.blnShow){
                thePanel.blnShow = false;
                thePanel.blnAnimating = false;
            }
            else{
                thePanel.blnShow = true;
                thePanel.blnAnimating = true;
            }
        }
        
        //Submit Button
        if(evt.getSource() == submitButton){
            //Local Variables
            int intScore = 0;;
            String strName = "";
            //Name
            strName = nameField.getText();
            System.out.println(strName);
            //Cannot resubmit 
            this.submitButton.setEnabled(false);
            //Question 1
            if(question1RadioA.isSelected()){
                intScore++;
                correctLabel1.setBackground(thePanel.clrBackground);
                correctLabel1.setForeground(Color.green);
                correctLabel1.setSize(200,30);
                correctLabel1.setLocation(600,110);
                theTestPanel.add(correctLabel1);
            }
            else{
                incorrectLabel1.setBackground(thePanel.clrBackground);
                incorrectLabel1.setForeground(Color.red);
                incorrectLabel1.setSize(200,30);
                incorrectLabel1.setLocation(600,110);
                theTestPanel.add(incorrectLabel1);
            }
            //Question 2
            if(question2RadioC.isSelected()){
                intScore++;
                correctLabel2.setBackground(thePanel.clrBackground);
                correctLabel2.setForeground(Color.green);
                correctLabel2.setSize(200,30);
                correctLabel2.setLocation(600,260);
                theTestPanel.add(correctLabel2);
            }
            else{
                incorrectLabel2.setBackground(thePanel.clrBackground);
                incorrectLabel2.setForeground(Color.red);
                incorrectLabel2.setSize(200,30);
                incorrectLabel2.setLocation(600,260);
                theTestPanel.add(incorrectLabel2);
            }
            //Question 3
            if(question3RadioC.isSelected()){
                intScore++;
                correctLabel3.setBackground(thePanel.clrBackground);
                correctLabel3.setForeground(Color.green);
                correctLabel3.setSize(200,30);
                correctLabel3.setLocation(600,410);
                theTestPanel.add(correctLabel3);
            }
            else{
                incorrectLabel3.setBackground(thePanel.clrBackground);
                incorrectLabel3.setForeground(Color.red);
                incorrectLabel3.setSize(200,30);
                incorrectLabel3.setLocation(600,410);
                theTestPanel.add(incorrectLabel3);
            }
            //Question 4
            if(question4RadioA.isSelected()){
                intScore++;
                correctLabel4.setBackground(thePanel.clrBackground);
                correctLabel4.setForeground(Color.green);
                correctLabel4.setSize(200,30);
                correctLabel4.setLocation(600,560);
                theTestPanel.add(correctLabel4);
            }
            else{
                incorrectLabel4.setBackground(thePanel.clrBackground);
                incorrectLabel4.setForeground(Color.red);
                incorrectLabel4.setSize(200,30);
                incorrectLabel4.setLocation(600,560);
                theTestPanel.add(incorrectLabel4);
            }
            //Question 5
            if(question5RadioB.isSelected()){
                intScore++;
                correctLabel5.setBackground(thePanel.clrBackground);
                correctLabel5.setForeground(Color.green);
                correctLabel5.setSize(200,30);
                correctLabel5.setLocation(600,710);
                theTestPanel.add(correctLabel5);
            }
            else{
                incorrectLabel5.setBackground(thePanel.clrBackground);
                incorrectLabel5.setForeground(Color.red);
                incorrectLabel5.setSize(200,30);
                incorrectLabel5.setLocation(600,710);
                theTestPanel.add(incorrectLabel5);
            }
            //Print Final Score to Data File
            try{
                PrintWriter scoreFile = new PrintWriter(new FileWriter("score.txt", true));
                scoreFile.println(strName);
                scoreFile.println(intScore);
                scoreFile.close();
            }
            catch(IOException e){
                System.out.println("Unable to open file");
            }
        }
    }

    //Updates the Selected Point Value
    public void mouseDragged(MouseEvent evt){
        if(thePanel.intPointSelected == 1){
            thePanel.intX1 = evt.getX();
            thePanel.intY1 = evt.getY();
            thePanel.pointDragged();
            slideSideA.setValue(thePanel.intLengthX);
            slideSideB.setValue(thePanel.intLengthY);
        } 
        else if(thePanel.intPointSelected == 2){
            thePanel.intX2 = evt.getX();
            thePanel.intY2 = evt.getY();
            thePanel.pointDragged();
            slideSideA.setValue(thePanel.intLengthX);
            slideSideB.setValue(thePanel.intLengthY);
        } 
        else if(thePanel.intPointSelected == 3){
            thePanel.intX1 = evt.getX();
            thePanel.intY2 = evt.getY();
            thePanel.intX2 = evt.getX() + thePanel.intLengthX * 20;
            thePanel.intY1 = evt.getY() - thePanel.intLengthY * 20;
            thePanel.baseDragged();
        }
        
        updateLabels();
    }

    // Computes the Point that is Selected
    public void mousePressed(MouseEvent evt){
        if(evt.getX() < thePanel.intX1 + 5 && evt.getX() > thePanel.intX1 - 5 && evt.getY() < thePanel.intY1 + 5 && evt.getY() > thePanel.intY1 - 5){
            thePanel.intPointSelected = 1;
        } 
        else if(evt.getX() < thePanel.intX2 + 5 && evt.getX() > thePanel.intX2 - 5 && evt.getY() < thePanel.intY2 + 5 && evt.getY() > thePanel.intY2 - 5){
            thePanel.intPointSelected = 2;
        } 
        else if(evt.getX() < thePanel.intX1 + 5 && evt.getX() > thePanel.intX1 - 5 && evt.getY() < thePanel.intY2 + 5 && evt.getY() > thePanel.intY2 - 5){
            thePanel.intPointSelected = 3;
        }
    }

    //Resets the Selected Point Indicator
    public void mouseReleased(MouseEvent evt){
        thePanel.intPointSelected = 0;
    }

    //Updates Parameters when Side Lengths Change (via slider)
    public void stateChanged(ChangeEvent evt){
        if(evt.getSource() == slideSideA){
            thePanel.intLengthX = slideSideA.getValue();
            thePanel.lengthAdjusted();
        }
        else if(evt.getSource() == slideSideB){
            thePanel.intLengthY = slideSideB.getValue();
            thePanel.lengthAdjusted();
        }

        updateLabels();
    }

    //Updates the Side Length Labels
    public void updateLabels(){
        aLabel.setText("Side A: " + Math.abs(thePanel.intLengthX));
        bLabel.setText("Side B: " + Math.abs(thePanel.intLengthY));
    }

    //Changing Content Pane (via menu change)
    public void menuSelected(MenuEvent evt){
        if(evt.getSource() == quizMenu && intPanelSelected != 2){
            theFrame.setContentPane(theTestScroll);
            theFrame.pack();
            intPanelSelected = 2;
        }
        else if(evt.getSource() == helpMenu && intPanelSelected != 3){
            theFrame.setContentPane(theHelpPanel);
            theFrame.pack();
            intPanelSelected = 3;
        }
        else if(evt.getSource() == aboutMenu && intPanelSelected != 4){
            theFrame.setContentPane(theAboutPanel);
            theFrame.pack();
            intPanelSelected = 4;
        }
        else if(evt.getSource() == scoreMenu && intPanelSelected != 5){
            countEntries();
            loadArray();
            sortScores();
            displayScores();
            theScorePanel.setPreferredSize(new Dimension(960, (int)(Math.max(540, 75 + intEntries * 16))));
            theScorePanel.intPanelHeight = (int)(Math.max(540, 75 + intEntries * 16));
            theFrame.setContentPane(theScoreScroll);
            theFrame.pack();
            intPanelSelected = 5;
        }
        else if(evt.getSource() == homeMenu && intPanelSelected != 1){
            theFrame.setContentPane(thePanel);
            theFrame.pack();
            intPanelSelected = 1;
        }
    }

    //Counts the Number of Scores in the Data File
    public void countEntries(){
        try{
            //Opening File for Reading
            BufferedReader countFile = new BufferedReader(new FileReader("score.txt"));
            //Counts Number of Scores
            int intLines = 0;
            String strLine = "";
            strLine = countFile.readLine();
            while(strLine != null){
                strLine = countFile.readLine();
                intLines++;
            }
            //Closes the Data File and Updates Global Variable
            countFile.close();
            intEntries = intLines/2;
        }
        catch(FileNotFoundException e){
            System.out.println("Error");
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }

    //Loads the Array with Data File
    public void loadArray(){
        try{
            BufferedReader scoreFile = new BufferedReader(new FileReader("score.txt"));
            strScores = new String[intEntries][2];
            for(intCount = 0; intCount < intEntries; intCount++){
                strScores[intCount][0] = scoreFile.readLine();
                strScores[intCount][1] = scoreFile.readLine();
            }
            scoreFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println("FileNotFoundException");
        }
        catch(IOException e){
            System.out.println("IOException");
        }
    }

    //Sorts the Scores within the Array
    public void sortScores(){
        int intBelow;
        int intCurrent;
        int intCounter;
        int intCounter2;
        String strTemp = "";
        for(intCounter2 = 0; intCounter2 < intEntries - 1; intCounter2++){
            for(intCounter = 0; intCounter < intEntries - intCounter2 - 1; intCounter++){
                try{
                    // Compare Score Values
                    intBelow = Integer.parseInt(strScores[intCounter + 1][1]);
                    intCurrent = Integer.parseInt(strScores[intCounter][1]);
                    if(intBelow > intCurrent){
                        // Swap Scores
                        strTemp = strScores[intCounter + 1][1];
                        strScores[intCounter + 1][1] = strScores[intCounter][1];
                        strScores[intCounter][1] = strTemp;
                        // Swap Names
                        strTemp = strScores[intCounter + 1][0];
                        strScores[intCounter + 1][0] = strScores[intCounter][0];
                        strScores[intCounter][0] = strTemp;
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("NumberFormatException");
                }
            }
        }
    }
    
    //Prepares the Scores for Displaying
    public void displayScores(){
        int intCount;
        String strNames = "";
        String strNumbers = "";
        // Label for Title
        scoreTitleLabel.setSize(920, 60);
        scoreTitleLabel.setLocation(20,0);
        scoreTitleLabel.setFont(theFont30);
        scoreTitleLabel.setForeground(thePanel.clrWhite);
        theScorePanel.add(scoreTitleLabel);
        // Format Label for Scores: Names of Users
        scoreNamesLabel.setSize(880, (int)(intEntries * 16));
        scoreNamesLabel.setLocation(20, 60);
        scoreNamesLabel.setVerticalAlignment(SwingConstants.TOP);
        scoreNamesLabel.setForeground(thePanel.clrWhite);
        theScorePanel.add(scoreNamesLabel);
        // Format Label for Scores: Quiz Mark Number
        scoreNumbersLabel.setSize(60, (int)(intEntries * 16));
        scoreNumbersLabel.setLocation(900, 60);
        scoreNumbersLabel.setVerticalAlignment(SwingConstants.TOP);
        scoreNumbersLabel.setForeground(thePanel.clrWhite);
        theScorePanel.add(scoreNumbersLabel);
        // Score Data: Names into Label
        for(intCount = 0; intCount < intEntries; intCount++){
            strNames = strNames + strScores[intCount][0] + "<br>";
        }
        strNames = "<html>" + strNames + "</html>";
        scoreNamesLabel.setText(strNames);
        // Score Data: Numbers into Label
        for(intCount = 0; intCount < intEntries; intCount++){
            strNumbers = strNumbers + strScores[intCount][1] + "<br>";
        }
        strNumbers = "<html>" + strNumbers + "</html>";
        scoreNumbersLabel.setText(strNumbers);
    }

    //Constructor
    public graphics(){
        //Initial Calculation for Score Panel Size
        countEntries();

        //Panels
        thePanel.setPreferredSize(new Dimension(960, 540));
        thePanel.setLayout(null);
        theTestPanel.setPreferredSize(new Dimension(960, 930));
        theTestPanel.setLayout(null);
		theTestScroll.setPreferredSize(new Dimension(960, 540));
        theScorePanel.setPreferredSize(new Dimension(960, (int)(Math.max(540, 75 + intEntries * 16))));
        theScorePanel.setLayout(null);
        theScorePanel.intPanelHeight = (int)(Math.max(540, 75 + intEntries * 16));
        theScoreScroll.setPreferredSize(new Dimension(960,540));
        theHelpPanel.setPreferredSize(new Dimension(960, 540));
        theHelpPanel.setLayout(null);
        theAboutPanel.setPreferredSize(new Dimension(960, 540));
        theAboutPanel.setLayout(null);

        //Menu
        theBar.add(homeMenu);
        theBar.add(quizMenu);
        theBar.add(scoreMenu);
        theBar.add(aboutMenu);
        theBar.add(helpMenu);
        
        //____________________
        //HOME PANEL ELEMENTS
        //Sin Button
        sinButton.setSize(78, 40);
        sinButton.setLocation(20, 200);
        thePanel.add(sinButton);

        //Cos Button
        cosButton.setSize(78, 40);
        cosButton.setLocation(98, 200);
        thePanel.add(cosButton);

        //Tan Button
        tanButton.setSize(78, 40);
        tanButton.setLocation(176, 200);
        thePanel.add(tanButton);

        //Demo  
        demoButton.setSize(235,40);
        demoButton.setLocation(20,260);
        thePanel.add(demoButton);

        //Slider Side A
        slideSideA.setLocation(20, 52);
        slideSideA.setSize(235, 40);
        slideSideA.setMajorTickSpacing(5);
        slideSideA.setMinorTickSpacing(1);
        slideSideA.setPaintTicks(true);
        slideSideA.setPaintLabels(true);
        slideSideA.setBackground(thePanel.clrBackground);
        slideSideA.setForeground(thePanel.clrWhite);
        thePanel.add(slideSideA);

        //Slider Side B
        slideSideB.setLocation(20, 132);
        slideSideB.setSize(235, 40);
        slideSideB.setMajorTickSpacing(5);
        slideSideB.setMinorTickSpacing(1);
        slideSideB.setPaintTicks(true);
        slideSideB.setPaintLabels(true);
        slideSideB.setBackground(thePanel.clrBackground);
        slideSideB.setForeground(thePanel.clrWhite);
        thePanel.add(slideSideB);

        //Label for Side A
        aLabel.setSize(235, 40);
        aLabel.setLocation(20, 10);
        aLabel.setForeground(Color.white);
        aLabel.setFont(theFont15);
        thePanel.add(aLabel);

        //Label for Side B
        bLabel.setSize(235, 40);
        bLabel.setLocation(20, 92);
        bLabel.setForeground(Color.white);
        bLabel.setFont(theFont15);
        thePanel.add(bLabel);

        //____________________
        //TEST PANEL ELEMENTS
		//Name
		nameText.setSize(800, 30);
		nameText.setLocation(20, 20);
        nameText.setForeground(Color.white);
		theTestPanel.add(nameText);
		nameField.setSize(900,30);
		nameField.setLocation(20, 50);		
		theTestPanel.add(nameField);
		
        //Question 1
		question1Text.setSize(800, 30);
		question1Text.setLocation(20, 110);
        question1Text.setForeground(Color.white);
		theTestPanel.add(question1Text);
		//Question 1 Answers
		question1RadioA.setSize(800, 30);
		question1RadioB.setSize(800, 30);
		question1RadioC.setSize(800, 30);
		question1RadioA.setLocation(20, 140);
		question1RadioB.setLocation(20, 170);
		question1RadioC.setLocation(20, 200);
        question1RadioA.setForeground(Color.white);
        question1RadioB.setForeground(Color.white);
        question1RadioC.setForeground(Color.white);
        question1RadioA.setBackground(thePanel.clrBackground);
        question1RadioB.setBackground(thePanel.clrBackground);
        question1RadioC.setBackground(thePanel.clrBackground);
		theTestPanel.add(question1RadioA);
		theTestPanel.add(question1RadioB);
		theTestPanel.add(question1RadioC);
		group1.add(question1RadioA);
		group1.add(question1RadioB);
		group1.add(question1RadioC);
		
        //Question 2
        question2Text.setSize(800, 30);
		question2Text.setLocation(20, 260);
        question2Text.setForeground(Color.white);
		theTestPanel.add(question2Text);
		//Question 2 Answers
        question2RadioA.setSize(800, 30);
		question2RadioB.setSize(800, 30);
		question2RadioC.setSize(800, 30);
		question2RadioA.setLocation(20, 290);
		question2RadioB.setLocation(20, 320);
		question2RadioC.setLocation(20, 350);
        question2RadioA.setForeground(Color.white);
        question2RadioB.setForeground(Color.white);
        question2RadioC.setForeground(Color.white);
        question2RadioA.setBackground(thePanel.clrBackground);
        question2RadioB.setBackground(thePanel.clrBackground);
        question2RadioC.setBackground(thePanel.clrBackground);
		theTestPanel.add(question2RadioA);
		theTestPanel.add(question2RadioB);
		theTestPanel.add(question2RadioC);
		group2.add(question2RadioA);
		group2.add(question2RadioB);
		group2.add(question2RadioC);
        
        //Question 3
        question3Text.setSize(900, 30);
		question3Text.setLocation(20, 410);
        question3Text.setForeground(Color.white);
		theTestPanel.add(question3Text);
		//Question 3 Answers
        question3RadioA.setSize(800, 30);
		question3RadioB.setSize(800, 30);
		question3RadioC.setSize(800, 30);
		question3RadioA.setLocation(20, 440);
		question3RadioB.setLocation(20, 470);
		question3RadioC.setLocation(20, 500);
        question3RadioA.setForeground(Color.white);
        question3RadioB.setForeground(Color.white);
        question3RadioC.setForeground(Color.white);
        question3RadioA.setBackground(thePanel.clrBackground);
        question3RadioB.setBackground(thePanel.clrBackground);
        question3RadioC.setBackground(thePanel.clrBackground);
		theTestPanel.add(question3RadioA);
		theTestPanel.add(question3RadioB);
		theTestPanel.add(question3RadioC);
		group3.add(question3RadioA);
		group3.add(question3RadioB);
		group3.add(question3RadioC);

        //Question 4
        question4Text.setSize(800, 30);
		question4Text.setLocation(20, 560);
        question4Text.setForeground(Color.white);
		theTestPanel.add(question4Text);
		//Question 4 Answers
        question4RadioA.setSize(800, 30);
		question4RadioB.setSize(800, 30);
		question4RadioC.setSize(800, 30);
		question4RadioA.setLocation(20, 590);
		question4RadioB.setLocation(20, 620);
		question4RadioC.setLocation(20, 650);
        question4RadioA.setForeground(Color.white);
        question4RadioB.setForeground(Color.white);
        question4RadioC.setForeground(Color.white);
        question4RadioA.setBackground(thePanel.clrBackground);
        question4RadioB.setBackground(thePanel.clrBackground);
        question4RadioC.setBackground(thePanel.clrBackground);
		theTestPanel.add(question4RadioA);
		theTestPanel.add(question4RadioB);
		theTestPanel.add(question4RadioC);
		group4.add(question4RadioA);
		group4.add(question4RadioB);
		group4.add(question4RadioC);
        
        //Question 5
        question5Text.setSize(800, 30);
		question5Text.setLocation(20, 710);
        question5Text.setForeground(Color.white);
		theTestPanel.add(question5Text);
		//Question 5 Answers
        question5RadioA.setSize(800, 30);
		question5RadioB.setSize(800, 30);
		question5RadioC.setSize(800, 30);
		question5RadioA.setLocation(20, 740);
		question5RadioB.setLocation(20, 770);
		question5RadioC.setLocation(20, 800);
        question5RadioA.setForeground(Color.white);
        question5RadioB.setForeground(Color.white);
        question5RadioC.setForeground(Color.white);
        question5RadioA.setBackground(thePanel.clrBackground);
        question5RadioB.setBackground(thePanel.clrBackground);
        question5RadioC.setBackground(thePanel.clrBackground);
		theTestPanel.add(question5RadioA);
		theTestPanel.add(question5RadioB);
		theTestPanel.add(question5RadioC);
		group5.add(question5RadioA);
		group5.add(question5RadioB);
		group5.add(question5RadioC);
        
        //Submit Button
        submitButton.setSize(100, 30);
        submitButton.setLocation(20,860);
        theTestPanel.add(submitButton);

        //Adding Listeners
        thePanel.addMouseMotionListener(this);
        thePanel.addMouseListener(this);
        sinButton.addActionListener(this);
        cosButton.addActionListener(this);
        tanButton.addActionListener(this);
        demoButton.addActionListener(this);
        slideSideA.addChangeListener(this);
        slideSideB.addChangeListener(this);
        homeMenu.addMenuListener(this);
        quizMenu.addMenuListener(this);
        scoreMenu.addMenuListener(this);
        aboutMenu.addMenuListener(this);
        helpMenu.addMenuListener(this);
        submitButton.addActionListener(this);

        //Packing Frame
        theFrame.setJMenuBar(theBar);
        theFrame.setContentPane(thePanel);
        theFrame.pack();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setResizable(false);
        theFrame.setVisible(true);
        newFrame.start();
    }


    //FORCED OVERRIDES
    public void mouseExited(MouseEvent evt){

    }

    public void mouseClicked(MouseEvent evt){

    }

    public void mouseEntered(MouseEvent evt){

    }

    public void mouseMoved(MouseEvent evt){

    }

    public void menuDeselected(MenuEvent evt){

    }

    public void menuCanceled(MenuEvent evt){
        
    }

}