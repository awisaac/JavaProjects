/*
* Alternate Project: Paint Box
* File: PaintBox.java/PaintBox.class
* Section 14974
* Programmer: Andrew Isaac
* Date: 11/12/13
* Description: GUI program that enables user to draw lines, rectangles, ovals, and polylines of different colors on panel and 
" save drawings to jpeg file. This class instantiates a paint frame object called pf that forms the base frame of the program.
*/


import javax.swing.*;

public class PaintBox {
   public static void main(String[] args) {
   
      PaintFrame pf = new PaintFrame("Paint Box"); // Instantiate a paint frame object.
      
      pf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pf.pack();
      pf.setVisible(true);
  }
}