
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
    helppanel theHelpPanel = new helppanel();
    aboutpanel theAboutPanel = new aboutpanel();
    Timer newFrame = new Timer(1000 / 48, this);

    // Button
    JButton sinButton = new JButton("Sin");
    JButton cosButton = new JButton("Cos");
    JButton tanButton = new JButton("Tan");
    JButton demoButton = new JButton("Show Demonstration");
    // JButton modeA = new JButton("Mode 1");
    // JButton modeB = new JButton("Mode 2");
    // Text Fields
    // JTextField aText = new JTextField();
    // JTextField bText = new JTextField();
    // Labels
    JLabel aLabel = new JLabel("Side A: ", JLabel.CENTER);
    JLabel bLabel = new JLabel("Side B: ", JLabel.CENTER);
    JLabel cLabel = new JLabel("Angle A", JLabel.CENTER);
    JLabel modeLabel = new JLabel("Selected mode: None", JLabel.CENTER);
    // Slider
    JSlider slideSideA = new JSlider(JSlider.HORIZONTAL, 0, 32, 10);
    JSlider slideSideB = new JSlider(JSlider.HORIZONTAL, 0, 25, 10);
    JSlider slideAngle = new JSlider(JSlider.HORIZONTAL, 0, 34, 10);
    // Menu
    JMenuBar theBar = new JMenuBar();
    JMenu aboutMenu = new JMenu("About");
    JMenu helpMenu = new JMenu("Help");

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
            thePanel.intTrigSelected = 1;
        }
        else if(evt.getSource() == cosButton){
            thePanel.intTrigSelected = 2;
        }
        else if(evt.getSource() == tanButton){
            thePanel.intTrigSelected = 3;
        }
        else if(evt.getSource() == demoButton){

        }
        // if (evt.getSource() == modeA) {
        // bLabel.setText("Side B");
        // System.out.println("mode a");
        // }
        // if (evt.getSource() == modeB) {
        // bLabel.setText("Angle A");
        // }
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
        aLabel.setText("Side A: " + thePanel.intLengthX);
        bLabel.setText("Side B: " + thePanel.intLengthY);
        cLabel.setText("Angle A: " + thePanel.dblAngle);
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
        if(evt.getSource() == helpMenu && intPanelSelected != 2){
            theFrame.setVisible(false);
            theFrame.setJMenuBar(null);
            theFrame.setContentPane(theHelpPanel);
            theFrame.setJMenuBar(theBar);
            theFrame.pack();
            theFrame.setVisible(true);
        }
        else if(evt.getSource() == aboutMenu){
            theFrame.setVisible(false);
            theFrame.setJMenuBar(null);
            theFrame.setContentPane(theAboutPanel);
            theFrame.setJMenuBar(theBar);
            theFrame.pack();
            theFrame.setVisible(true);
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
        theHelpPanel.setPreferredSize(new Dimension(960, 540));
        theHelpPanel.setLayout(null);
        theAboutPanel.setPreferredSize(new Dimension(960, 540));
        theAboutPanel.setLayout(null);

        // Menu
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
        slideSideA.addChangeListener(this);
        slideSideB.addChangeListener(this);
        aboutMenu.addMenuListener(this);
        helpMenu.addMenuListener(this);

        //Packing Frame
        theFrame.setJMenuBar(theBar);
        theFrame.setContentPane(thePanel);
        theFrame.pack();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
        newFrame.start();
    }
}