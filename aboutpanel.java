import java.awt.*;
import javax.swing.*;

public class aboutpanel extends JPanel{
    //Properties

    //Methods
    public void paintComponent(Graphics g){
        g.drawString("About",20,20);
        g.drawString("SOH CAH TOA Simulator was programmed by Nicholas Ching and Erin Hu", 20, 50);

    }
    
    //Constructor
    public aboutpanel(){

    }
}
