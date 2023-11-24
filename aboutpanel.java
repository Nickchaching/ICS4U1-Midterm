import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class aboutpanel extends JPanel{
    //Properties
    BufferedImage imgAbout = null;

    //Methods
    public void paintComponent(Graphics g){
       g.drawImage(imgAbout, 0, 0, null);

    }
    
    //Constructor
    public aboutpanel(){
        try{
            imgAbout = ImageIO.read(new File("about.png"));
        }
        catch(IOException e){
            System.out.println("Unable to load image");
        }
    }
}
