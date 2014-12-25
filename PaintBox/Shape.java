/*
* Alternate Project: Paint Box
* File: Shape.java/Shape.class 
* Section 14974
* Programmer: Andrew Isaac
* Date: 11/12/13
* Description: Shape is superclass for line, poly, and bounded shapes.
*/

import java.awt.Color; 
import java.awt.Graphics;
 
public abstract class Shape { 
 
   protected Color strokeColor;
   
   public Shape() { 
      strokeColor = Color.black; // Provides stroke color to subclass shapes
      } 
      
   public abstract void draw (Graphics g); // Draw method used by subclasses 
}