import java.awt.*;
import javax.swing.*;

public class testingpanel extends JPanel{
    // Properties
    Color clrBackground = new Color(37, 37, 37);

    // Methods
    public void paintComponent(Graphics g){
        g.setColor(clrBackground);
        g.fillRect(0, 0, 960, 930);
    }

    // Constructor
    public testingpanel(){
		
    }
    
}
