/*
* Alternate Project: Paint Box
* File: Poly.java/Poly.class 
* Section 14974
* Programmer: Andrew Isaac
* Date: 11/12/13
* Description: Poly is subclass of shape. Draws a polyline based on points makde by an initial and subsequent clicks.
*/

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Point; 
import java.util.ArrayList; 
 
public class Poly extends Shape { 
 
   private ArrayList <Point> pointList; // Array of points to draw lines between
   private Point firstPoint; // Initial point
   private Point newPoint; // Each subsequent point
 
   public Poly() { // Default constructor
      pointList = new ArrayList <Point> (); 
      }
       
   public Poly(Point p, Color stroke) { // Constructor takes in initial point and color and adds to pointList ArrayList. 
      pointList = new ArrayList <Point> ();
      firstPoint = p;
      newPoint = p;
      pointList.add(p);
      strokeColor = stroke;
      }
       
   public void addPoint(Point p) { // Adds subsequent points to pointList
      newPoint = p;
      pointList.add(newPoint); 
      }
      
   public Point getNewPoint() { // newPoint accessor for rubber band effect
      return newPoint;
      }
       
   public void draw(Graphics g) { // Poly draw method
 
      g.setColor(strokeColor); // Color of poly
 
      for(int i = 0 ; i < pointList.size() - 1 ; i++) { // Takes each point in array and draws line to next point  
         g.drawLine((int) pointList.get(i).getX(), (int) pointList.get(i).getY(), (int) pointList.get(i + 1).getX(), 
            (int) pointList.get(i + 1).getY()); 
         } 
      } 
}
