import java.awt.*;
import javax.swing.*;

public class scorepanel extends JPanel{
    // Properties
    Color clrBackground = new Color(37, 37, 37);
    int intPanelHeight;

    // Methods
    public void paintComponent(Graphics g){
        g.setColor(clrBackground);
        g.fillRect(0, 0, 960, intPanelHeight);
    }

    // Constructor
    public scorepanel(){
		
    }
    
}
