import java.awt.*;
import javax.swing.*;

public class panelgraphics extends JPanel{
    //Properties
    
    //POS Values refer to grid (35x35) [0, 34]
    int intPosX1 = 15;
    int intPosY1 = 15;
    int intLengthX = 4;
    int intLengthY = 3;
    int intPosX2;
    int intPosY2;
    int intX1;
    int intY1;
    int intX2;
    int intY2;
    int intPointSelected;
    
    Color clrBackground = new Color(37, 37, 37);
    Color clrGrid = new Color(50, 50, 50);
    Color clrWhite = new Color(255, 255, 255);
    Color clrRed = new Color(255, 0, 0);

    //Methods
    public void paintComponent(Graphics g){
        g.setColor(clrBackground);        
        g.fillRect(0, 0, 1000, 1000);

        //Grid
        g.setColor(clrGrid);
        
        //Automatic Line Populating
        int intCount;
        for(intCount = 0; intCount < 36; intCount++){
            g.drawLine(280 + intCount * 20, 0, 280 + intCount * 20, 720);
        }
        for(intCount = 0; intCount < 36; intCount++){
            g.drawLine(280, intCount * 20, 1000, intCount * 20);
        }

        //Divider Line
        g.setColor(clrWhite);
        g.fillRect(275, 0, 5, 720);

        //Draw Triangle
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        
        getX2();
        restrictPos();
        getPosCoords();

        g.drawLine(intX1, intY1, intX2, intY2);
        g.drawLine(intX1, intY1, intX1, intY2);
        g.drawLine(intX1, intY2, intX2, intY2);

        //Draws the Base Dragger Points
        g.fillOval(intX1 - 5, intY1 - 5, 11, 11);
        g.fillOval(intX2 - 5, intY2 - 5, 11, 11);
        g.fillOval(intX1 - 5, intY2 - 5, 11, 11);

        //Highlights the Selected Dragger Point
        if(intPointSelected == 1){
            g.setColor(clrRed);
            g.fillOval(intX1 - 5, intY1 - 5, 11, 11);
            g.setColor(clrWhite);
        }
        else if(intPointSelected == 2){
            g.setColor(clrRed);
            g.fillOval(intX2 - 5, intY2 - 5, 11, 11);
            g.setColor(clrWhite);
        }
        else if(intPointSelected == 3){
            g.setColor(clrRed);
            g.fillOval(intX1 - 5, intY2 - 5, 11, 11);
            g.setColor(clrWhite);
        }
    }

    //Constructor
    public panelgraphics(){
      
        
    }

    //Computes the X2 values based on Length and X1 values 
    private void getX2(){
        this.intPosX2 = intPosX1 + intLengthX;
        this.intPosY2 = intPosY1 + intLengthY;
    }

    //Computes the Length values based on the X1 & X2 values
    private void getLength(){
        this.intLengthX = intPosX2 - intPosX1;
        this.intLengthY = intPosY2 - intPosY1;
    }

    //Converts Grid Coordinates to integer position values
    private void getPosCoords(){
        this.intX1 = 300 + (intPosX1 * 20);
        this.intY1 = 20 + (intPosY1 * 20);
        this.intX2 = 300 + (intPosX2 * 20);
        this.intY2 = 20 + (intPosY2 * 20);
    }

    private void getGridCoords(){
        this.intPosX1 = (this.intX1 - 300)/20;
        this.intPosY1 = (this.intY1 - 20)/20;
        this.intPosX2 = (this.intX2 - 300)/20;
        this.intPosY2 = (this.intY2 - 20)/20;
    }

    //Snaps coordinates to the grid after dragging
    private void snapTo(){
        if(this.intX1 % 20 <= 10){
            this.intX1 = this.intX1 - (this.intX1 % 20);
        }
        else{
            this.intX1 = this.intX1 - (this.intX1 % 20) + 20;
        }
        if(this.intY1 % 20 <= 10){
            this.intY1 = this.intY1 - (this.intY1 % 20);
        }
        else{
            this.intY1 = this.intY1 - (this.intY1 % 20) + 20;
        }

        if(this.intY2 % 20 <= 10){
            this.intX2 = this.intX2 - (this.intX2 % 20);
        }
        else{
            this.intX2 = this.intX2 - (this.intX2 % 20) + 20;
        }
        if(this.intY2 % 20 <= 10){
            this.intY2 = this.intY2 - (this.intY2 % 20);
        }
        else{
            this.intY2 = this.intY2 - (this.intY2 % 20) + 20;
        }
    }

    //Prevents Points from Going Out of Bounds [0, 34]
    private void restrictPos(){
        if(intPosX1 < 0){
            intPosX2 = 0 + intLengthX;
            intPosX1 = 0;
        }
        else if(intPosX1 > 34){
            intPosX2 = 34 + intLengthX;
            intPosX1 = 34;
        }
        if(intPosY1 < 0){
            intPosY2 = 0 + intLengthY;
            intPosY1 = 0;
        }
        else if(intPosY1 > 34){
            intPosY2 = 34 + intLengthY;
            intPosY1 = 34;
        }
        if(intPosX2 < 0){
            intPosX1 = 0 - intLengthX;
            intPosX2 = 0;
        }
        else if(intPosX2 > 34){
            intPosX1 = 34 - intLengthX;
            intPosX2 = 34;
        }
        if(intPosY2 < 0){
            intPosY1 = 0 - intLengthY;
            intPosY2 = 0;
        }
        else if(intPosY2 > 34){
            intPosY1 = 34 - intLengthY;
            intPosY2 = 34;
        }
    }

    //Activated after a point is dragged to recalulate length and snap to grid
    public void pointDragged(){
        snapTo();
        getGridCoords();
        restrictPos();
        getLength();
        System.out.println(intX1+" | "+intPosX1+" | "+intY1+" | "+intPosY1+" | "+intLengthX+" | "+intLengthY);
    }

    public void baseDragged(){
        snapTo(); //Rounds to nearest slot
        getGridCoords(); //Gets the 
        restrictPos();
        System.out.println(intX1+" | "+intPosX1+" | "+intY1+" | "+intPosY1+" | "+intX2+" | "+intPosX2+" | "+intY2+" | "+intPosY2+" | "+intLengthX+" | "+intLengthY);
    }
}
