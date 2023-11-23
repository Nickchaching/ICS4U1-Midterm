
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class graphics implements ActionListener, MouseMotionListener, MouseListener, ChangeListener{
    // Properties
    JFrame theFrame = new JFrame("SOH CAH TOA Simulator");
    panelgraphics thePanel = new panelgraphics();
    Timer newFrame = new Timer(1000 / 48, this);

    // Toggle Button
    // ButtonGroup group = new ButtonGroup();
    // JToggleButton modeA = new JToggleButton("Side A, Side B");
    // JToggleButton modeB = new JToggleButton("Side A, Angle A");
    // Button
    JButton sinButton = new JButton("Sin");
    JButton cosButton = new JButton("Cos");
    JButton tanButton = new JButton("Tan");
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
    JLabel resultLabel = new JLabel("Result: ", JLabel.CENTER);
    // Slider
    JSlider slideSideA = new JSlider(JSlider.HORIZONTAL, 0, 34, 10);
    JSlider slideSideB = new JSlider(JSlider.HORIZONTAL, 0, 34, 10);
    JSlider slideAngle = new JSlider(JSlider.HORIZONTAL, 0, 34, 10);

    // Methods
    public void actionPerformed(ActionEvent evt){
        //New Frame Gen
        if(evt.getSource() == newFrame){
            thePanel.repaint();
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

    // Constructor
    public graphics(){
        // Panel
        thePanel.setPreferredSize(new Dimension(1000, 720));
        thePanel.setLayout(null);
        // Sin Button
        sinButton.setSize(235, 40);
        sinButton.setLocation(20, 350);
        thePanel.add(sinButton);
        // Cos Button
        cosButton.setSize(235, 40);
        cosButton.setLocation(20, 436);
        thePanel.add(cosButton);
        // Tan Button
        tanButton.setSize(235, 40);
        tanButton.setLocation(20, 522);
        thePanel.add(tanButton);
        // Mode A Buttons
        // modeA.setSize(107, 40);
        // modeA.setLocation(20, 50);
        // thePanel.add(modeA);
        // Mode B Button
        // modeB.setSize(107, 40);
        // modeB.setLocation(147, 50);
        // thePanel.add(modeB);
        // note: set up ActionListener for sin/cos/tan buttons
        //
        // group.add(modeA);
        // group.add(modeB);
        // thePanel.add(group);
        // Text Field A
        // aText.setSize(235, 40);
        // aText.setLocation(20, 446);
        // thePanel.add(aText);
        // Text Field B
        // bText.setSize(235, 40);
        // bText.setLocation(20, 532);
        // thePanel.add(bText);
        // Label for Selected Mode
        // modeLabel.setSize(235, 40);
        // modeLabel.setLocation(20, 10);
        // modeLabel.setForeground(Color.white);
        // thePanel.add(modeLabel);

        //Slider Side A
        slideSideA.setLocation(20, 110);
        slideSideA.setSize(235, 40);
        slideSideA.setMajorTickSpacing(5);
        slideSideA.setMinorTickSpacing(1);
        slideSideA.setPaintTicks(true);
        slideSideA.setPaintLabels(true);
        slideSideA.setBackground(thePanel.clrBackground);
        slideSideA.setForeground(thePanel.clrWhite);
        thePanel.add(slideSideA);

        //Slider Side B
        slideSideB.setLocation(20, 195);
        slideSideB.setSize(235, 40);
        slideSideB.setMajorTickSpacing(5);
        slideSideB.setMinorTickSpacing(1);
        slideSideB.setPaintTicks(true);
        slideSideB.setPaintLabels(true);
        slideSideB.setBackground(thePanel.clrBackground);
        slideSideB.setForeground(thePanel.clrWhite);
        thePanel.add(slideSideB);

        //Slider Angle A
        slideAngle.setLocation(20, 260);
        thePanel.add(slideAngle);
        
        //Label for Side A
        aLabel.setSize(235, 40);
        aLabel.setLocation(20, 68);
        aLabel.setForeground(Color.white);
        thePanel.add(aLabel);
        //Label for Side B
        bLabel.setSize(235, 40);
        bLabel.setLocation(20, 154);
        bLabel.setForeground(Color.white);
        thePanel.add(bLabel);
        //Label for Angle A
        cLabel.setSize(235, 40);
        cLabel.setLocation(20, 240);
        cLabel.setForeground(Color.white);
        thePanel.add(cLabel);
        //Label for Result
        resultLabel.setSize(235, 40);
        resultLabel.setLocation(20, 600);
        resultLabel.setForeground(Color.white);
        thePanel.add(resultLabel);

        //Adding Listeners
        thePanel.addMouseMotionListener(this);
        thePanel.addMouseListener(this);
        sinButton.addActionListener(this);
        cosButton.addActionListener(this);
        tanButton.addActionListener(this);
        slideSideA.addChangeListener(this);
        slideSideB.addChangeListener(this);

        //Packing Frame
        theFrame.setContentPane(thePanel);
        theFrame.pack();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
        newFrame.start();
    }
}