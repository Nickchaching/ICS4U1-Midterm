
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class graphics implements ActionListener, MouseMotionListener, MouseListener, ChangeListener, MenuListener{
    // Properties
    Font theFont15 = new Font("Dialog", 1, 15);
    int intPanelSelected = 1;

    JFrame theFrame = new JFrame("SOH CAH TOA Simulator");
    panelgraphics thePanel = new panelgraphics();
    testingpanel theTestPanel = new testingpanel();
    helppanel theHelpPanel = new helppanel();
    aboutpanel theAboutPanel = new aboutpanel();
    Timer newFrame = new Timer(1000 / 48, this);

    // Button
    JButton sinButton = new JButton("Sin");
    JButton cosButton = new JButton("Cos");
    JButton tanButton = new JButton("Tan");
    JButton demoButton = new JButton("Show/Hide Demonstration");
    JLabel aLabel = new JLabel("Side A: ", JLabel.CENTER);
    JLabel bLabel = new JLabel("Side B: ", JLabel.CENTER);
    // Slider
    JSlider slideSideA = new JSlider(JSlider.HORIZONTAL, 0, 32, 10);
    JSlider slideSideB = new JSlider(JSlider.HORIZONTAL, 0, 25, 10);
    // Menu
    JMenuBar theBar = new JMenuBar();
    JMenu homeMenu = new JMenu("Home");
    JMenu quizMenu = new JMenu("Quiz");
    JMenu aboutMenu = new JMenu("About");
    JMenu helpMenu = new JMenu("Help");


    //RESORT LATER
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
    public void actionPerformed(ActionEvent evt){
        //New Frame Gen
        if(evt.getSource() == newFrame){
            if(intPanelSelected == 1){
                thePanel.repaint();
            }
            newFrame.start();
        }

        //SIN/COS/TAN Highlight
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
        
        if(evt.getSource() == submitButton){
            System.out.println("Submit");
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

    public void mouseReleased(MouseEvent evt){
        thePanel.intPointSelected = 0;
    }

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

    public void updateLabels(){
        aLabel.setText("Side A: " + Math.abs(thePanel.intLengthX));
        bLabel.setText("Side B: " + Math.abs(thePanel.intLengthY));
    }

    public void mouseExited(MouseEvent evt){

    }

    public void mouseClicked(MouseEvent evt){

    }

    public void mouseEntered(MouseEvent evt){

    }

    public void mouseMoved(MouseEvent evt){

    }

    public void menuSelected(MenuEvent evt){
        if(evt.getSource() == quizMenu && intPanelSelected != 2){
            theFrame.setContentPane(theScroll);
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
        else if(evt.getSource() == homeMenu && intPanelSelected != 1){
            theFrame.setContentPane(thePanel);
            theFrame.pack();
            intPanelSelected = 1;
        }
    }

    public void menuDeselected(MenuEvent evt){
        if(evt.getSource() == helpMenu){
            intPanelSelected = 2;
        }
        else if(evt.getSource() == aboutMenu){
            intPanelSelected = 3;
        }
    }

    public void menuCanceled(MenuEvent evt){
        
    }

    // Constructor
    public graphics(){
        // Panel
        thePanel.setPreferredSize(new Dimension(960, 540));
        thePanel.setLayout(null);
        theTestPanel.setPreferredSize(new Dimension(960, 540));
        theTestPanel.setLayout(null);
        theHelpPanel.setPreferredSize(new Dimension(960, 540));
        theHelpPanel.setLayout(null);
        theAboutPanel.setPreferredSize(new Dimension(960, 540));
        theAboutPanel.setLayout(null);

        // Menu
        theBar.add(homeMenu);
        theBar.add(quizMenu);
        theBar.add(aboutMenu);
        theBar.add(helpMenu);

        // Sin Button
        sinButton.setSize(78, 40);
        sinButton.setLocation(20, 200);
        thePanel.add(sinButton);
        // Cos Button
        cosButton.setSize(78, 40);
        cosButton.setLocation(98, 200);
        thePanel.add(cosButton);
        // Tan Button
        tanButton.setSize(78, 40);
        tanButton.setLocation(176, 200);
        thePanel.add(tanButton);
        // Demo  
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
        aboutMenu.addMenuListener(this);
        helpMenu.addMenuListener(this);

        //RESORT LATER
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


        //Packing Frame
        theFrame.setJMenuBar(theBar);
        theFrame.setContentPane(thePanel);
        theFrame.pack();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
        newFrame.start();
    }
}