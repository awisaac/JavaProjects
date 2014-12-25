import javax.swing.*; 

public class ButtonBar extends JToolBar { // ButtonBar class implements buttons for ship placement

   protected JRadioButton carrier; 
   protected JRadioButton battleship; 
   protected JRadioButton destroyer; 
   protected JRadioButton submarine; 
   protected JRadioButton patrolBoat;
   protected JRadioButton horizontal;
   protected JRadioButton vertical
   protected JButton done; 

   public ButtonBar() { 
      carrier = new JRadioButton("Aircraft Carrier");
      battleship = new JRadioButton("Battleship");
      destroyer = new JRadioButton("Destroyer");
      submarine = new JRadioButton("Submarine");
      patrolBoat = new JRadioButton("Patrol Boat");
      vertical = new JRadioButton("Vertical");
      horizontal = = new JRadioButton("Horizontal");
      done = new JButton("Done");
   
      ButtonGroup shipGroup = new ButtonGroup(); // Button group allows only one selected tool at a time
      shipGroup.add(carrier); 
      shipGroup.add(battleship); 
      shipGroup.add(destroyer); 
      shipGroup.add(submarine); 
      shipGroup.add(patrolBoat);
               
      ButtonGroup directionGroup = new ButtonGroup(); // Button group allows only one selected tool at a time
      directionGroup.add(vertical); 
      directionGroup.add(horizontal); 
      
      add(carrier); // Adds tool objects to bar
      add(battleship); 
      add(destroyer); 
      add(submarine); 
      add(patrolBoat); 
      add(vertical); 
      add(horizontal); 
      add(done);
   
      setFloatable(false); // Keeps bar locked to top of frame.
      }     
   } 