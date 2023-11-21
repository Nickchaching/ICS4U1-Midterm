import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class graphics implements ActionListener {
    // Properties
    JFrame theFrame = new JFrame("SOH CAH TOA Simulator");
    panelgraphics thePanel = new panelgraphics();
    Timer newFrame = new Timer(1000 / 48, this);
    // Buttons
    JButton sinButton = new JButton("Sin");
    JButton cosButton = new JButton("Cos");
    JButton tanButton = new JButton("Tan");
    // Text Fields
    JTextField angleText = new JTextField();
    JTextField sideAText = new JTextField();
    JTextField sideBText = new JTextField();
    // Labels
    JLabel sideALabel = new JLabel("Side A");
    JLabel sideBLabel = new JLabel("Side B");
    JLabel angleALabel = new JLabel("Angle A");

    // Methods
    public void actionPerformed(ActionEvent evt) {

    }

    // Constructor
    public graphics() {
        thePanel.setPreferredSize(new Dimension(1000, 720));
        theFrame.setContentPane(thePanel);
        theFrame.pack();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
        newFrame.start();
    }
}