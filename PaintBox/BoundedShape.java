/*
* Alternate Project: Paint Box
* File: BoundedShape.java/BoundedShape.class 
* Section 14974
* Programmer: Andrew Isaac
* Date: 11/12/13
* Description: Bounded Shape is subclass of shape. It calculates upper left position and height width for subclasses rectangle 
* and oval.
*/

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Point; 
 
public class BoundedShape extends Shape { 
 
   protected Point upperLeft, initialPoint; // Points used to determine location and size of bounded shape
   protected int width, height; 
   protected Color fillColor;  

   public BoundedShape() { 
   } 
 
   public BoundedShape(Point firstPoint, Color fill, Color stroke) { // Bounded shape constructor
      
      initialPoint = firstPoint;
      fillColor = fill;
      strokeColor = stroke;            
      }
         
   public void setShape(Point currentPt) { // Sets shape by taking initial point and current point
      
      int x, y;
      width = Math.abs((int)(initialPoint.getX()) - (int)(currentPt.getX())); // Absolute value of width calculation
      height = Math.abs((int)(initialPoint.getY()) - (int)(currentPt.getY())); // Absolute value of height calculation
            
      if (initialPoint.getX() < currentPt.getX()) { // Determines upper left position by lowest x and y values
         x = (int)(initialPoint.getX());
         }
      else {
         x = (int)(currentPt.getX());
         }
         
      if (initialPoint.getY() < currentPt.getY()) {
         y = (int)(initialPoint.getY());
         }
      else {
         y = (int)(currentPt.getY());
         }
         
      upperLeft = new Point(x, y);
      }
     
   public void draw(Graphics g) { // Draw provides fill color for rectangle and oval draw methods
      g.setColor(fillColor);      
      }
}