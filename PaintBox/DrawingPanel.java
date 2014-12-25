/*
* Alternate Project: Paint Box
* File: DrawingPanel.java/DrawingPanel.class 
* Section 14974
* Programmer: Andrew Isaac
* Date: 11/12/13
* Description: Drawing panel enables user to create specifically sized shapes contained with an array and drawn over
* each other to create a drawing. 
*/

import java.awt.*; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseMotionAdapter; 
import java.util.ArrayList; 
import javax.swing.*;

public class DrawingPanel extends JPanel {

   private ArrayList <Shape> items; // Array of shapes to be drawn
   private BoundedShape oval; // Shapes kept within array to remain on panel
   private BoundedShape rectangle;
   private Poly poly;
   private Line line; 
   public static enum Mode {Select, Line, Oval, Rectangle, Poly}; // Mode options
   private Mode mode; // Selected mode
   private Color stroke; 
   private Color fill;
   private Image openImage = null; // Image opened from file   
   private Image stillImage; // Image to be drawn
    
   public DrawingPanel() {
   
      super(); // Inherits JPanel
   
      setBackground(Color.white); // Default color
      this.addMouseMotionListener(new MouseMotionMonitor()); // Mouse motion listener
      this.addMouseListener(new MouseButtonListener()); // Mouse button listener
      items = new ArrayList(); // Array of drawn shapes
      stroke = Color.black; // Default stroke color
      fill = Color.white; // Default fill color
      }

   public void paint(Graphics g) { // Graphical implementation of shapes
      
      super.paint(g); 
      
      if (openImage != null) { // Clears previous drawing and sets image opened from file to be drawn 
         
         clearItems(); 
         stillImage = openImage; 
         openImage = null; 
         }
                
      g.drawImage(stillImage, 0, 0, this.getWidth(), this.getHeight(), null); // Draws file image opened from file   
      
      for (Shape a : items) { // Draws each shape in items with foreach loop         
         a.draw(g);    
         }
      }
      
   private void drawOval(Point p) { // Oval initial position, colors, and final size determination
      
      if (oval == null) {
         oval = new Oval(p, fill,stroke);
         }
      else {         
          oval = null;
         }
      }
      
   private void virtualOval(Point p) { // Method to rubberband oval to mouse cursor location
      
      items.remove(oval);   
      oval.setShape(p); 
      items.add(oval);             
      repaint();
      }
           

   private void drawRectangle(Point p) { // Same method as drawOval
   
      if (rectangle == null) {
         rectangle = new Rectangle(p, fill,stroke);
         }
      else {
         rectangle = null;
         } 
      }
      
   private void virtualRectangle(Point p) { // Same method as virtualOval
               
      items.remove(rectangle);
      rectangle.setShape(p);
      items.add(rectangle);                
      repaint();
      }
   
   private void drawLine(Point p) { // Line draw method
               
      if (line == null) {
         line = new Line(p, stroke);
         }
      else {
         line = null;
         }
      }
         
   private void virtualLine(Point p) { // Virtual line draw method
   
      items.remove(line);   
      line.setSecondPoint(p);
      items.add(line);                
      repaint();
      }
         
    private void virtualPoly(Point p) { // Poly virtual line draw method
               
       items.remove(line);
       line = new Line(poly.getNewPoint(), stroke);
       line.setSecondPoint(p);
       items.add(line);
       repaint();
       }
         
   private void drawPoly(Point p) { // Poly continues to take shape until double clicked
         
      if (poly == null) {
         poly = new Poly(p, stroke);            
         }
      else {
         items.remove(line);
         items.remove(poly);
         poly.addPoint(p);
         items.add(poly);
         repaint();            
         }
      }
        
    private void endPoly(Point p) { // Poly shape is concluded with a double click
         
      poly.addPoint(p);
      repaint();
      items.remove(line);
      line = null;            
      poly = null;
      }
         
   public void setStroke(Color newColor) { // Mutator for stroke color
      stroke = newColor;   
      }
             
   public Color getStroke() { // Accessor for stroke color
      return stroke;   
      }
       
   public void setFill(Color newColor) { // Mutator for fill color
      fill = newColor;
      }
       
   public Color getFill() { // Accessor for fill color
      return fill;
      }
       
   public void setMode(DrawingPanel.Mode newMode) { // Mutator for mode selection
      mode = newMode;      
      }
      
   public void setOpenImage(Image image) { // Mutator for image obtained from file 
      openImage = image;
      repaint();
      }
   
   public void clearItems() { // Clear items mutator for New and Open menu items
      items.clear();
      stillImage = null;
      repaint();
      }
     
   public class MouseMotionMonitor extends MouseMotionAdapter { 
      public void mouseMoved(MouseEvent e) {
      
         if (mode == Mode.Line && line != null) { // Builds rubber-banded shapes based on initial click point and mouse movement
         virtualLine(e.getPoint());
         }
         
         if (mode == Mode.Poly && poly != null) {
         virtualPoly(e.getPoint());
         }
     
         if (mode == Mode.Rectangle && rectangle != null) {
         virtualRectangle(e.getPoint());
         }
        
         if (mode == Mode.Oval && oval != null) {
         virtualOval(e.getPoint());
         }
      }      
   }
    
   public class MouseButtonListener extends MouseAdapter {
      public void mouseClicked(MouseEvent e) { 
      
         if (mode == Mode.Line) { // Build shapes based on initial click and subsequent click            
            drawLine(e.getPoint());
            }
         
         if (mode == Mode.Oval) { 
            drawOval(e.getPoint());                    
            } 
            
         if (mode == Mode.Rectangle) { 
            drawRectangle(e.getPoint());            
            } 
       
         if (mode == Mode.Poly && e.getClickCount() < 2) { // Continue poly with single click
            drawPoly(e.getPoint());
            }
         else if (mode == Mode.Poly && e.getClickCount() >= 2 && poly != null) { // Complete poly with double click
            endPoly(e.getPoint());
            }                
         } 
   }
} 