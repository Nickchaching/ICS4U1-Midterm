// Tasks for later
//     - Mode selection
//     - Select the trig ratio
//     - 

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class graphics implements ActionListener {
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
    JButton modeA = new JButton("Mode 1");
    JButton modeB = new JButton("Mode 2");
    // Text Fields
    JTextField aText = new JTextField();
    JTextField bText = new JTextField();
    // Labels
    JLabel aLabel = new JLabel("Side A", JLabel.CENTER);
    JLabel bLabel = new JLabel("Side B/Angle A", JLabel.CENTER);
    JLabel modeLabel = new JLabel("Selected mode: None", JLabel.CENTER);
    JLabel resultLabel = new JLabel("Result: ", JLabel.CENTER);

    // Methods
    public void actionPerformed(ActionEvent evt) {

    }

    // Constructor
    public graphics() {
        // Panel
        thePanel.setPreferredSize(new Dimension(1000, 720));
        thePanel.setPreferredSize(new Dimension(1280, 720));
        thePanel.setLayout(null);
        // Sin Button
        sinButton.setSize(235, 40);
        sinButton.setLocation(20, 148);
        thePanel.add(sinButton);
        // Cos Button
        cosButton.setSize(235, 40);
        cosButton.setLocation(20, 234);
        thePanel.add(cosButton);
        // Tan Button
        tanButton.setSize(235, 40);
        tanButton.setLocation(20, 320);
        thePanel.add(tanButton);
        // Mode A Buttons
        modeA.setSize(107, 40);
        modeA.setLocation(20, 50);
        thePanel.add(modeA);
        // Mode B Button
        modeB.setSize(107, 40);
        modeB.setLocation(147, 50);
        thePanel.add(modeB);
        // note: set up ActionListener for sin/cos/tan buttons
        //
        // Toggle Buttons (too complicated, will take too much time)
        // group.add(modeA);
        // group.add(modeB);
        // thePanel.add(group);
        // Text Field A
        aText.setSize(235, 40);
        aText.setLocation(20, 446);
        thePanel.add(aText);
        // Text Field B
        bText.setSize(235, 40);
        bText.setLocation(20, 532);
        thePanel.add(bText);
        // Label for Selected Mode
        modeLabel.setSize(235, 40);
        modeLabel.setLocation(20, 10);
        modeLabel.setForeground(Color.white);
        thePanel.add(modeLabel);
        // Label for Field A
        aLabel.setSize(235, 40);
        aLabel.setLocation(20, 406);
        aLabel.setForeground(Color.white);
        thePanel.add(aLabel);
        // Label for Field B
        bLabel.setSize(235, 40);
        bLabel.setLocation(20, 492);
        bLabel.setForeground(Color.white);
        thePanel.add(bLabel);
        // Label for Result
        resultLabel.setSize(235, 40);
        resultLabel.setLocation(20, 650);
        resultLabel.setForeground(Color.white);
        thePanel.add(resultLabel);

        theFrame.setContentPane(thePanel);
        theFrame.pack();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
        newFrame.start();
    }
}