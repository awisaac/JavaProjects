import java.util.*;

public class ShipBoard {
   
   private int[][] shipBoard;
   private int size;
   private int ships;
   private ArrayList<Ship> shipList;
   private int[] shipTypes;
   private Ship lastShipSunk;
   private int shotsFired;
   private int hits;
   
   // default constructor 10 x 10 with 5 ships
   public ShipBoard() {     
      
      shipBoard = new int[10][10];
      size = 10;
      shipList = new ArrayList<Ship>();
      shipTypes = new int[4];
            
      generateShip(5); // carrier
      generateShip(4); // battleShip
      generateShip(3); // submarine
      generateShip(3); // destroyer
      generateShip(2); // patrol boat
   }
   
   // custom constructor 1 - manually placed ships
   public ShipBoard(int size) {
   
      shipBoard = new int[size][size];
      this.size = size;
      shipList = new ArrayList<Ship>();
      shipTypes = new int[4];  
   }
   
   // custom constructor 2 - randomly placed ships 
   public ShipBoard(int size, int[] types) {
   
      shipBoard = new int[size][size];
      this.size = size;
      shipList = new ArrayList<Ship>();
      shipTypes = new int[4];            
      
      // generate random ships
      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < types[i]; j++) {
            System.out.println("Generating ships...");                 
            generateShip(i + 2);
         }
      }   
   }
   
   public boolean target(Coordinates coordinates) {
      
      shotsFired++;
      System.out.print("Target: " + coordinates + " ");      
      int x = coordinates.getX();
      int y = coordinates.getY();
      
      if (shipBoard[x][y] != 0) {
      
         ListIterator<Ship> shipIterator = shipList.listIterator();
         boolean shipFound = false;   
         Ship ship = null;
         
         while (shipIterator.hasNext() && !shipFound) {
            
            ship = shipIterator.next();
            
            if (ship.atThisLocation(new Coordinates(x,y))) {
               ship.shipHit();               
               shipFound = true;
               hits++;
               System.out.println("Hit!");                                         
            }
         }         
                        
         if (ship.shipSunk()) {                  
            shipTypes[ship.getShipLength() - 2]--;   
            ships--;
            lastShipSunk = ship;
            System.out.println("Enemy ship sunk! Ships remaining: " + ships);                                   
         }          
                                 
         return true;
      }
      
      else {
         System.out.println("Miss!");
         return false;
      }
   }   
            
   public String toString() {
   
      String board = "";      
         
      for (int i = 0; i < size; i++) {      
         for (int j = 0; j < size; j++) {      
            board = board + shipBoard[i][j] + " ";      
         }
               
         board = board + "\n";
      }
   
      return board;
   }
   
   public int getSize() {   
      return size;
   }
   
   public int getShips() {   
      return ships;
   }
   
   public int[] getShipTypes() {   
      return shipTypes;
   }
   
   public Ship getLastShipSunk() {
      return lastShipSunk;
   }
   
   public int getShotsFired() {
      return shotsFired;
   }
   
   public int getHits() {
      return hits;
   }
   
   // custom placed ships
   public boolean generateShip(int length, int x, int y, int direction) {
      
      // meets length requirements
      if (length < 2 || length > 5) {
         return false;
      }
            
      // verify ship fits on board      
      if (direction == 0) {
         if (x < 0 || x >= size || y < 0 || y + length > size) {
            return false;
         }
      }
      else if (direction == 1) {
         if (x < 0 || x > length + size || y < 0 || y >= size) {
            return false;
         }
      }      
      else {
         return false;
      }
      
      // verify no other ships are in the way      
      // vertical alignment
      if (direction == 0) {
         for (int i = y; i < y + length; i++) {
            if (shipBoard[x][i] != 0) {
               return false;        
            }         
         }
      }
      // horizontal alignment
      else {      
         for (int i = x; i < x + length; i++) {
            if (shipBoard[i][y] != 0) {
               return false;       
            }         
         }      
      }
            
      // place ship in valid location on board      
      int[] position = {direction,x,y};         
      shipList.add(new Ship(length, position));
      shipTypes[length - 2]++;      
      ships++;
      
      // vertical alignment         
      if (direction == 0) {      
         for (int i = y; i < y + length; i++) {
            shipBoard[x][i] = length;
         }           
      }      
      // horizontal alignment      
      else {                  
         for (int i = x; i < x + length; i++) {
            shipBoard[i][y] = length;
         }      
      }      
      return true;     
   }  
   
   // randomly placed ships
   private void generateShip(int length) {
    
      int x = (int)(Math.random() * (size - length + 1));
      int y = (int)(Math.random() * (size - length + 1));      
      int direction = (int)(Math.random() * 2);
      boolean locationFound = false;  
      
      while (!locationFound) {
      
         boolean shipInTheWay = false;   
      
         // vertical alignment
         if (direction == 0) {         
            // verify no other ships are in the way
            for (int i = y; i < y + length && !shipInTheWay; i++) {
               if (shipBoard[x][i] != 0) {
                  shipInTheWay = true;        
               }         
            }
         }
      
         // horizontal alignment
         else {      
            // verify no other ships are in the way
            for (int i = x; i < x + length && !shipInTheWay; i++) {
               if (shipBoard[i][y] != 0) {
                  shipInTheWay = true;        
               }         
            }      
         }
         
         // find new location
         if (shipInTheWay) {      
            x = (int)(Math.random() * (size - length + 1));
            y = (int)(Math.random() * (size - length + 1));      
            direction = (int)(Math.random() * 2);
            shipInTheWay = false;        
         }
         
         // location is good
         else {      
            locationFound = true;
         }    
      }
      
      // place ship in valid location on board      
      int[] position = {direction,x,y};         
      shipList.add(new Ship(length, position));
      shipTypes[length - 2]++;      
      ships++;
      
      // vertical alignment
      if (direction == 0) {
         for (int i = y; i < y + length; i++) {
            shipBoard[x][i] = length;
         }           
      }      
      // horizontal alignment      
      else {
         for (int i = x; i < x + length; i++) {
            shipBoard[i][y] = length;
         }      
      }     
   }      
}
