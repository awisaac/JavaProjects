import java.util.*;

public class Ship {

   private int length;
   private int hitPoints; 
   private ArrayList<Coordinates> location; // ship's squares
      
   public Ship(int shipLength, int[] position) {
   
      length = shipLength;
      hitPoints = length;
      location = new ArrayList<Coordinates>();
      
      if (position[0] == 0) { // aligned vertically         
         for (int i = position[2]; i < position[2] + length; i++) {
            location.add(new Coordinates(position[1],i));                     
         }       
      }      
      else { // aligned horizontally         
         for (int i = position[1]; i < position[1] + length; i++) {
            location.add(new Coordinates(i,position[2]));                     
         } 
      }            
   }
   
   public boolean shipSunk() {   
      return hitPoints == 0;
   }
   
   public void shipHit() {  
      hitPoints--;
   }
   
   public int getShipLength() {
      return length;
   }
   
   public boolean atThisLocation(Coordinates coordinates) {
      
      ListIterator<Coordinates> coordsList = location.listIterator();
      
      while (coordsList.hasNext()) {      
         if (coordsList.next().compareTo(coordinates) == 0) {         
            return true;
         }           
      }
      
      return false;     
   }
   
   public ArrayList<Coordinates> getShipCoordinates() {
      return location;
   }
}