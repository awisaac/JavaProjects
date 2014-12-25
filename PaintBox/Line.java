/*
* Alternate Project: Paint Box
* File: Line.java/Line.class 
* Section 14974
* Programmer: Andrew Isaac
* Date: 11/12/13
* Description: Line is subclass of shape. Draws shape of a line.
*/

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Point; 

public class Line extends Shape { 
 
   private Point firstPoint;
   private Point secondPoint;
 
   public Line(Point p) { 
      } 
   
   public Line(Point p, Color stroke) { // Line constructor
      firstPoint = p;
      strokeColor = stroke; 
      }
    
   public void setSecondPoint(Point p) { // Second point mutator 
      secondPoint = p;
      }
      
   public Point getFirstPoint() { // First point accessor for vitual line
      return firstPoint;
      }
   
   public void draw(Graphics g) { // Method to draw line
      
      g.setColor(strokeColor);
      g.drawLine((int)(firstPoint.getX()),(int)(firstPoint.getY()),(int)(secondPoint.getX()),(int)(secondPoint.getY()));
      
   }  
} 