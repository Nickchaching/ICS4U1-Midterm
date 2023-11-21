import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class graphics implements ActionListener{
    //Properties
    JFrame theFrame = new JFrame("SOH CAH TOA Simulator");
    panelgraphics thePanel = new panelgraphics();
    Timer newFrame = new Timer(1000/48, this);

    //Methods
    public void actionPerformed(ActionEvent evt){

    }

    //Constructor
    public graphics(){
        thePanel.setPreferredSize(new Dimension(1280, 720));
        theFrame.setContentPane(thePanel);
        theFrame.pack();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
        newFrame.start();
    }
}