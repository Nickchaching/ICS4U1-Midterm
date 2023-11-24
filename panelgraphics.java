import java.awt.*;
import javax.swing.*;
import java.awt.font.*;
import java.awt.geom.*;

public class panelgraphics extends JPanel{
    //Properties
    
    //POS Values refer to grid (33x26) [0, 32] [0, 25]
    int intPosX1 = 15;
    int intPosY1 = 15;
    int intLengthX = 4;
    int intLengthY = 3;
    int intScaleMultiplier;
    double dblLengthHyp;
    double dblAngle;
    int intPosX2;
    int intPosY2;
    int intX1;
    int intY1;
    int intX2;
    int intY2;
    int intPointSelected;
    int intTrigSelected;
    boolean blnShow = false;
    boolean blnAnimating = false;
    int intAnimationFrame = 0;
    int intAniY = 0;
    int intAniVeloY = 0;
    int intAlign[] = new int[2];

    Font theFont12 = new Font("Dialog", 1, 12);
    Font theFont16 = new Font("Dialog", 1, 16);
    Font theFont20 = new Font("Dialog", 1, 20);
    
    Color clrBackground = new Color(37, 37, 37);
    Color clrGrid = new Color(50, 50, 50);
    Color clrWhite = new Color(255, 255, 255);
    Color clrRed = new Color(255, 0, 0);
    Color clrOrange = new Color(255, 171, 61);

    //Methods
    public void paintComponent(Graphics g){
        g.setFont(theFont12);
        g.setColor(clrBackground);        
        g.fillRect(0, 0, 960, 540);

        //Grid
        g.setColor(clrGrid);
        
        //Automatic Line Populating
        int intCount;
        for(intCount = 0; intCount < 34; intCount++){
            g.drawLine(280 + intCount * 20, 0, 280 + intCount * 20, 540);
        }
        for(intCount = 0; intCount < 27; intCount++){
            g.drawLine(280, intCount * 20, 960, intCount * 20);
        }

        //Divider Line
        g.setColor(clrWhite);
        g.fillRect(275, 0, 5, 540);

        //Draw Triangle
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        
        getX2();
        getPosCoords();

        //Drawing the ARC
        getTrig();
        g.setColor(clrRed);
        intScaleMultiplier = Math.min(Math.abs(intLengthX), Math.abs(intLengthY));
        if(intScaleMultiplier < 7){
            intScaleMultiplier = 7;
        }
        if(intLengthY > 0){
            g.drawArc(intX1 - (int)((intScaleMultiplier * 5)/2), intY1 - (int)((intScaleMultiplier * 5)/2), intScaleMultiplier * 5, intScaleMultiplier * 5, 270, (int)dblAngle);
        }
        else if(intLengthY < 0){
            g.drawArc(intX1 - (int)((intScaleMultiplier * 5)/2), intY1 - (int)((intScaleMultiplier * 5)/2), intScaleMultiplier * 5, intScaleMultiplier * 5, 90 - (int)dblAngle, (int)dblAngle);
        }
        g.setColor(clrWhite);
        
        //Drawing Triangle Edge Lines
        g.drawLine(intX1, intY1, intX2, intY2);
        g.drawLine(intX1, intY1, intX1, intY2);
        g.drawLine(intX1, intY2, intX2, intY2);

        //Highlights the Relavent Triangle Sides
        if(intTrigSelected == 1){
            g.setColor(clrOrange);
            g.drawLine(intX1, intY1, intX2, intY2);
            g.drawLine(intX1, intY2, intX2, intY2);
            g.setColor(clrWhite);
        }
        else if(intTrigSelected == 2){
            g.setColor(clrOrange);
            g.drawLine(intX1, intY1, intX2, intY2);
            g.drawLine(intX1, intY1, intX1, intY2);
            g.setColor(clrWhite);
        }
        else if(intTrigSelected == 3){
            g.setColor(clrOrange);
            g.drawLine(intX1, intY1, intX1, intY2);
            g.drawLine(intX1, intY2, intX2, intY2);
            g.setColor(clrWhite);
        }

        //Draws the Base Dragger Points
        g.fillOval(intX1 - 5, intY1 - 5, 11, 11);
        g.fillOval(intX2 - 5, intY2 - 5, 11, 11);
        g.fillOval(intX1 - 5, intY2 - 5, 11, 11);

        //Draws the Length of the Sides
        if(intTrigSelected == 1 || intTrigSelected == 3){
            g.setColor(clrRed);
        }
        if(intLengthY > 0 && intLengthX != 0){
            g.drawString(""+Math.abs(intLengthX), intX1 + (intLengthX * 20 / 2) - 3, intY2 + 15);
        }
        else if(intLengthY < 0 && intLengthX != 0){
            g.drawString(""+Math.abs(intLengthX), intX1 + (intLengthX * 20 / 2) - 3, intY2 - 7);
        }
        if(intTrigSelected == 1 || intTrigSelected == 3){
            g.setColor(clrWhite);
        }

        if(intTrigSelected == 2 || intTrigSelected == 3){
            g.setColor(clrRed);
        }
        if(intLengthX > 0 && intLengthY > 9){
            g.drawString(""+Math.abs(intLengthY), intX1 - 20, intY1 + (intLengthY * 20 / 2) + 5);
        }
        else if(intLengthX > 0 && intLengthY < -9){
            g.drawString(""+Math.abs(intLengthY), intX1 - 25, intY1 + (intLengthY * 20 / 2) + 5);
        }
        else if(intLengthX > 0 && intLengthY < 0){
            g.drawString(""+Math.abs(intLengthY), intX1 - 20, intY1 + (intLengthY * 20 / 2) + 5);
        } 
        else if(intLengthX > 0 && intLengthY != 0){
            g.drawString(""+Math.abs(intLengthY), intX1 - 15, intY1 + (intLengthY * 20 / 2) + 5);
        }
        else if(intLengthX < 0 && intLengthY != 0){
            g.drawString(""+Math.abs(intLengthY), intX1 + 10, intY1 + (intLengthY * 20 / 2) + 5);
        }
        if(intTrigSelected == 2 || intTrigSelected == 3){
            g.setColor(clrWhite);
        }

        if(intTrigSelected == 1 || intTrigSelected == 2){
            g.setColor(clrRed);
        }
        if(intLengthX >= 0 && intLengthY >= 0){
            g.drawString(""+Math.round(dblLengthHyp * 10.0)/10.0, (intX1 + intX2)/2, (intY1 + intY2)/2 - 10);
        }
        else if(intLengthX >= 0 && intLengthY <= 0){
            g.drawString(""+Math.round(dblLengthHyp * 10.0)/10.0, (intX1 + intX2)/2, (intY1 + intY2)/2 + 20);
        }
        else if(intLengthX <= 0 && intLengthY >= 0){
            g.drawString(""+Math.round(dblLengthHyp * 10.0)/10.0, (intX1 + intX2)/2 - 20, (intY1 + intY2)/2 - 10);
        }
        else if(intLengthX <= 0 && intLengthY <= 0){
            g.drawString(""+Math.round(dblLengthHyp * 10.0)/10.0, (intX1 + intX2)/2 - 20, (intY1 + intY2)/2 + 20);
        }
        if(intTrigSelected == 1 || intTrigSelected == 2){
            g.setColor(clrWhite);
        }
        

        //Drawing ABC Vertex Points
        g.setFont(theFont16);
        if(intLengthX >= 0 && intLengthY >= 0){
            g.drawString("A", intX1 - 5, intY1 - 10);
            g.drawString("B", intX2 + 10, intY2 + 5);
            g.drawString("C", intX1 - 20, intY2 + 5);
        }
        else if(intLengthX >= 0 && intLengthY <= 0){
            g.drawString("A", intX1 - 5, intY1 + 22);
            g.drawString("B", intX2 + 10, intY2 + 5);
            g.drawString("C", intX1 - 20, intY2 + 5);
        }
        else if(intLengthX <= 0 && intLengthY >= 0){
            g.drawString("A", intX1 - 5, intY1 - 10);
            g.drawString("B", intX2 - 20, intY2 + 5);
            g.drawString("C", intX1 + 10, intY2 + 5);
        }
        else if(intLengthX <= 0 && intLengthY <= 0){
            g.drawString("A", intX1 - 5, intY1 + 22);
            g.drawString("B", intX2 - 20, intY2 + 5);
            g.drawString("C", intX1 + 10, intY2 + 5);
        }


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
        
        //Demonstration Animations
        if(blnAnimating){
            //Determines if it is the first animation frame
            if(intAnimationFrame == 0){
                intAniY = 250;
                intAniVeloY = -10;
            }
            else if(intAniY <= 45){
                intAniVeloY = intAniVeloY + 1;
            }

            if(intAniY > 0){
                intAniY = intAniY + intAniVeloY;
                intAnimationFrame++;
            }
            else{
                intAniY = 0;
                intAnimationFrame = 0;
                blnAnimating = false;
            }
        }
        if(blnShow){
            g.setFont(theFont20);
            
            if(intTrigSelected == 1){
                intAlign = CAlign(theFont20, "SINθ = OPP/HYP");
                g.drawString("SINθ = OPP/HYP", 140 - intAlign[0]/2, 360 - intAlign[1]/2 + intAniY);
                
                intAlign = CAlign(theFont20, "SINθ = "+Math.abs(intLengthX)+"/"+Math.round(dblLengthHyp*10.0)/10.0);
                g.drawString("SINθ = "+Math.abs(intLengthX)+"/"+Math.round(dblLengthHyp*10.0)/10.0, 140 - intAlign[0]/2, 410 - intAlign[1]/2 + intAniY);
                
                intAlign = CAlign(theFont20, "θ = sin⁻¹("+Math.abs(intLengthX)+"/"+Math.round(dblLengthHyp*10.0)/10.0+")");
                g.drawString("θ = sin⁻¹("+Math.abs(intLengthX)+"/"+Math.round(dblLengthHyp*10.0)/10.0+")", 140 - intAlign[0]/2, 460 - intAlign[1]/2 + intAniY);
                
                intAlign = CAlign(theFont20, "θ = "+Math.abs(Math.round(dblAngle*10.0)/10.0));
                g.drawString("θ = "+Math.abs(Math.round(dblAngle*10.0)/10.0), 140 - intAlign[0]/2, 510 - intAlign[1]/2 + intAniY);
            }

            if(intTrigSelected == 2){
                intAlign = CAlign(theFont20, "COSθ = ADJ/HYP");
                g.drawString("COSθ = ADJ/HYP", 140 - intAlign[0]/2, 360 - intAlign[1]/2 + intAniY);
                
                intAlign = CAlign(theFont20, "COSθ = "+Math.abs(intLengthY)+"/"+Math.round(dblLengthHyp*10.0)/10.0);
                g.drawString("COSθ = "+Math.abs(intLengthY)+"/"+Math.round(dblLengthHyp*10.0)/10.0, 140 - intAlign[0]/2, 410 - intAlign[1]/2 + intAniY);
                
                intAlign = CAlign(theFont20, "θ = cos⁻¹("+Math.abs(intLengthY)+"/"+Math.round(dblLengthHyp*10.0)/10.0+")");
                g.drawString("θ = cos⁻¹("+Math.abs(intLengthY)+"/"+Math.round(dblLengthHyp*10.0)/10.0+")", 140 - intAlign[0]/2, 460 - intAlign[1]/2 + intAniY);
                
                intAlign = CAlign(theFont20, "θ = "+Math.abs(Math.round(dblAngle*10.0)/10.0));
                g.drawString("θ = "+Math.abs(Math.round(dblAngle*10.0)/10.0), 140 - intAlign[0]/2, 510 - intAlign[1]/2 + intAniY);
            }
            
            if(intTrigSelected == 3){
                intAlign = CAlign(theFont20, "TANθ = OPP/ADJ");
                g.drawString("TANθ = OPP/ADJ", 140 - intAlign[0]/2, 360 - intAlign[1]/2 + intAniY);
                
                intAlign = CAlign(theFont20, "TANθ = "+Math.abs(intLengthX)+"/"+Math.abs(intLengthY));
                g.drawString("TANθ = "+Math.abs(intLengthX)+"/"+Math.abs(intLengthY), 140 - intAlign[0]/2, 410 - intAlign[1]/2 + intAniY);
                
                intAlign = CAlign(theFont20, "θ = tan⁻¹("+Math.abs(intLengthX)+"/"+Math.abs(intLengthY)+")");
                g.drawString("θ = tan⁻¹("+Math.abs(intLengthX)+"/"+Math.abs(intLengthY)+")", 140 - intAlign[0]/2, 460 - intAlign[1]/2 + intAniY);
                
                intAlign = CAlign(theFont20, "θ = "+Math.abs(Math.round(dblAngle*10.0)/10.0));
                g.drawString("θ = "+Math.abs(Math.round(dblAngle*10.0)/10.0), 140 - intAlign[0]/2, 510 - intAlign[1]/2 + intAniY);
            }
        }

    }

    //Constructor
    public panelgraphics(){
      
        
    }

    //Text Display Width Calculator
	public int[] CAlign(Font fntin, String strtext){
		//Font Alignment Variable (Coordinate Based)
		int intCAlign[] = new int[2];
		//Creates the variables needed for alignment
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		int intX = (int)(fntin.getStringBounds(strtext, frc).getWidth());
		int intY = (int)(fntin.getStringBounds(strtext, frc).getHeight());
		intCAlign[0] = intX;
		intCAlign[1] = intY;
		return intCAlign;
	}

    //Retrieves the Trig Data
    private void getTrig(){
        dblLengthHyp = Math.sqrt(Math.pow(intLengthX, 2) + Math.pow(intLengthY, 2));
        dblAngle = Math.toDegrees(Math.asin(intLengthX/dblLengthHyp));
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

        if(this.intX2 % 20 <= 10){
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

    //Prevents Points from Going Out of Bounds [0, 32] [0, 25]
    private void restrictPos(){
        if(intPosX1 < 0){
            intPosX2 = 0 + intLengthX;
            intPosX1 = 0;
        }
        else if(intPosX1 > 32){
            intPosX2 = 32 + intLengthX;
            intPosX1 = 32;
        }
        if(intPosY1 < 0){
            intPosY2 = 0 + intLengthY;
            intPosY1 = 0;
        }
        else if(intPosY1 > 25){
            intPosY2 = 25 + intLengthY;
            intPosY1 = 25;
        }
        if(intPosX2 < 0){
            intPosX1 = 0 - intLengthX;
            intPosX2 = 0;
        }
        else if(intPosX2 > 32){
            intPosX1 = 32 - intLengthX;
            intPosX2 = 32;
        }
        if(intPosY2 < 0){
            intPosY1 = 0 - intLengthY;
            intPosY2 = 0;
        }
        else if(intPosY2 > 25){
            intPosY1 = 25 - intLengthY;
            intPosY2 = 25;
        }
    }

    //Activated after a point is dragged to recalulate length and snap to grid
    public void pointDragged(){
        snapTo();
        getGridCoords();
        restrictPos();
        getLength();
        System.out.println(intX1+" | "+intPosX1+" | "+intY1+" | "+intPosY1+" | "+intX2+" | "+intPosX2+" | "+intY2+" | "+intPosY2+" | "+intLengthX+" | "+intLengthY);
    }

    public void baseDragged(){
        snapTo();
        getGridCoords();
        restrictPos();
        System.out.println(intX1+" | "+intPosX1+" | "+intY1+" | "+intPosY1+" | "+intX2+" | "+intPosX2+" | "+intY2+" | "+intPosY2+" | "+intLengthX+" | "+intLengthY);
    }

    public void lengthAdjusted(){
        getX2();
        restrictPos();
        getLength();
    }

    public void angleAdjusted(){
        
    }
}
