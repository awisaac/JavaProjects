/*
* Alternate Project: Paint Box
* File: Rectangle.java/Rectangle.class 
* Section 14974
* Programmer: Andrew Isaac
* Date: 11/12/13
* Description: Rectangle is subclass of shape and bounded shape. Draws a rectangle shape based upon bounded shape parameters.
*/

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Point; 
 
public class Rectangle extends BoundedShape { 
 
   public Rectangle() {
      super(); // Inherits from bounded shape
   } 
 
   public Rectangle(Point firstPoint, Color fill, Color stroke) { 
      super(firstPoint,fill,stroke); // Inherits from bounded shape
      }
        
   public void draw(Graphics g) { // Method used to draw rectangle
         g.setColor(fillColor);
         g.fillRect((int)(upperLeft.getX()),(int)(upperLeft.getY()),width,height); // Rectangle fill        
         g.setColor(strokeColor);
         g.drawRect((int)(upperLeft.getX()),(int)(upperLeft.getY()),width,height); // Rectangle border
         }
}