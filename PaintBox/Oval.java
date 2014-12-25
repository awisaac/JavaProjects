/*
* Alternate Project: Paint Box
* File: Oval.java/Oval.class 
* Section 14974
* Programmer: Andrew Isaac
* Date: 11/12/13
* Description: Oval is subclass of shape and bounded shape. Draws shape of Oval based upon BoundedShape values.
*/

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Point; 
 
public class Oval extends BoundedShape { 
 
   public Oval() {
      super(); // Inherits from bounded shape
   } 
 
   public Oval(Point firstPoint, Color fill, Color stroke) { 
      super(firstPoint,fill,stroke); // Inherits from bounded shape
      }
         
   public void draw(Graphics g) { // Method to draw oval
         g.setColor(fillColor);
         g.fillOval((int)(upperLeft.getX()),(int)(upperLeft.getY()),width,height); // Oval fill         
         g.setColor(strokeColor);
         g.drawOval((int)(upperLeft.getX()),(int)(upperLeft.getY()),width,height); // Oval border  
         }
}