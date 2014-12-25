/*
* Alternate Project: Paint Box
* File: PaintFrame.java/PaintFrame.class 
* Section 14974
* Programmer: Andrew Isaac
* Date: 11/12/13
* Description: Paint frame is base frame of program. It instantiates a menu bar, a button bar, and a drawing panel.
* and handles file save and open operations.  Select tool and cut/copy/paste options not yet implemented.
*/

import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.filechooser.*;

public class PaintFrame extends JFrame { 

   private JMenuBar paintBoxMenuBar; 
   private DrawingPanel paintBoxDrawingPanel; 
   private ButtonBar paintBoxButtonBar; 
   private JFileChooser fc = new JFileChooser(); 

   public PaintFrame(String title) {
   
      super(title); // Uses inheritance of superclass to set title bar        
   
      paintBoxMenuBar = new JMenuBar(); // Menu implementation
      JMenu file = new JMenu("File");
      JMenu edit = new JMenu("Edit");
      
      paintBoxMenuBar.add(file); //JMenu declarations
      paintBoxMenuBar.add(edit);      
      
      JMenuItem newItem = new JMenuItem("New"); // JMenuItem declarations
      JMenuItem saveItem = new JMenuItem("Save");
      JMenuItem openItem = new JMenuItem("Open");
      JMenuItem exitItem = new JMenuItem("Exit");
      JMenuItem cutItem = new JMenuItem("Cut");
      JMenuItem copyItem = new JMenuItem("Copy");
      JMenuItem pasteItem = new JMenuItem("Paste");
      
      file.add(newItem); // Adds menuItems to each menu 
      file.add(saveItem);
      file.add(openItem);
      file.add(exitItem);      
      edit.add(cutItem);
      edit.add(copyItem);
      edit.add(pasteItem);
                      
      setJMenuBar(paintBoxMenuBar); // Add menubar to frame      
      
      paintBoxButtonBar = new ButtonBar(); // Instantiates new ButtonBar     
      
      RadioButtonListener rButtonListener = new RadioButtonListener(); // Action listeners for buttons and menu items.
      ButtonListener buttonListener = new ButtonListener();
      ItemListener itemListener = new ItemListener();      
      
      paintBoxButtonBar.selectTool.addActionListener(rButtonListener); // Adds action listeners for all tool buttons
      paintBoxButtonBar.lineTool.addActionListener(rButtonListener);
      paintBoxButtonBar.ovalTool.addActionListener(rButtonListener);
      paintBoxButtonBar.rectangleTool.addActionListener(rButtonListener);
      paintBoxButtonBar.polyTool.addActionListener(rButtonListener);
      paintBoxButtonBar.strokeTool.addActionListener(buttonListener);
      paintBoxButtonBar.fillTool.addActionListener(buttonListener);
      
      newItem.addActionListener(itemListener);  // Adds action listeners for all menu items
      saveItem.addActionListener(itemListener);
      openItem.addActionListener(itemListener);
      exitItem.addActionListener(itemListener);
      cutItem.addActionListener(itemListener);
      copyItem.addActionListener(itemListener);
      pasteItem.addActionListener(itemListener);      
      
      paintBoxDrawingPanel = new DrawingPanel(); // Instantiates new DrawingPanel
      add(paintBoxButtonBar, BorderLayout.NORTH); // Positions buttonbar on top
      add(paintBoxDrawingPanel); // Adds paintBoxDrawningPanel to frame.

      setPreferredSize(new Dimension(800,600)); // Default frame size.
      setDefaultCloseOperation(EXIT_ON_CLOSE);  // Default program exit.
      } 
 
   public class ButtonListener implements ActionListener { // Action events for stroke and fill buttons
      public void actionPerformed(ActionEvent e) {
       
         Color newColor = Color.BLACK; // Initial color of stroke
         newColor = JColorChooser.showDialog(null, "Choose a color: ", newColor); // User can select color from chooser dialog
       
         if (e.getSource() == paintBoxButtonBar.strokeTool) { // Sets button and stroke color by button pressed
            paintBoxButtonBar.setStrokeButtonColor(newColor);
            paintBoxDrawingPanel.setStroke(newColor);
            }
         else {
            paintBoxButtonBar.setFillButtonColor(newColor); // Sets button and fill color by button pressed
            paintBoxDrawingPanel.setFill(newColor);       
            } 
      } 
   }  

   public class RadioButtonListener implements ActionListener { // Action events for tool selection mode
      public void actionPerformed(ActionEvent e) {
       
         if (paintBoxButtonBar.selectTool.isSelected()) {
            paintBoxDrawingPanel.setMode(DrawingPanel.Mode.Select);                    
            }
         
         if (paintBoxButtonBar.lineTool.isSelected()) {
            paintBoxDrawingPanel.setMode(DrawingPanel.Mode.Line);
            }
            
         if (paintBoxButtonBar.ovalTool.isSelected()) {
            paintBoxDrawingPanel.setMode(DrawingPanel.Mode.Oval);
            }
         
         if (paintBoxButtonBar.rectangleTool.isSelected()) {
            paintBoxDrawingPanel.setMode(DrawingPanel.Mode.Rectangle);
            }
         
         if (paintBoxButtonBar.polyTool.isSelected()) {
            paintBoxDrawingPanel.setMode(DrawingPanel.Mode.Poly);
            }
      } 
   }
   
   public class ItemListener implements ActionListener { // Action events for menu item selections
         public void actionPerformed(ActionEvent e) {
         
         if ("New".equals(e.getActionCommand())) {
            paintBoxDrawingPanel.clearItems();
            }            
            
         if ("Save".equals(e.getActionCommand())) { 
            int saveConfirm = fc.showSaveDialog(PaintFrame.this);
            if (saveConfirm == JFileChooser.APPROVE_OPTION) {
               save(fc.getSelectedFile());
               }
            }
            
         if ("Open".equals(e.getActionCommand())) { 
            int openConfirm = fc.showOpenDialog(PaintFrame.this);
               if (openConfirm == JFileChooser.APPROVE_OPTION) {
               open(fc.getSelectedFile());
               }
            }
            
         if ("Exit".equals(e.getActionCommand())) {
            System.exit(0);
            }
         }
      }      
      
   public void save(File fileName) { // Save drawing to jpeg file method
      
      BufferedImage image = new BufferedImage(paintBoxDrawingPanel.getWidth(), paintBoxDrawingPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
      Graphics2D graphics2D = image.createGraphics(); 
      paintBoxDrawingPanel.paint(graphics2D);
      
      try{
         ImageIO.write(image,"jpg", fileName); // Writes buffered image to file
         }
      catch(Exception ex){
         ex.printStackTrace();
         }
      }
      
   public void open(File fileName) { // Open jpeg file onto paintBoxDrawingPanel

      try{
         paintBoxDrawingPanel.setOpenImage(ImageIO.read(fileName)); // Sets image based on file read        
         }
      catch(Exception ex){
         ex.printStackTrace();
         }     
      }   
}