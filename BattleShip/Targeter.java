import java.util.*;

public class Targeter {
   
   private ShipBoard board;
   private int[][] targetBoard;
   private int[][] probabilities;
   private int size;
   private int ships;
   private int[] shipTypes;
   private int biggestShip;
   private Ship lastShipSunk;
   
   // constructor
   public Targeter(ShipBoard board) {
      
      this.board = board; 
      size = board.getSize();
      ships = board.getShips();
      targetBoard = new int[size][size];
      shipTypes = board.getShipTypes();      
      probabilities = new int[size][size];  
   }
   
   // attack algorithm
   public void attack() {
            
      while (ships > 0) {
     
         Coordinates target = findTarget();
         
         if (board.target(target)) {
            targetBoard[target.getX()][target.getY()] = 1; // 1 means hit 
            
            if (killTarget(target)) {
               ships = board.getShips();
               lastShipSunk = board.getLastShipSunk();               
            }  
         }
         
         else {
            targetBoard[target.getX()][target.getY()] = 2; // 2 means miss
         }

      }
   }            
   
   // finds best shot by probability ship is in a given square
   private Coordinates findTarget() {   
   
      ArrayList<Coordinates> coordinates;
      ListIterator<Coordinates> coordsList;      
      
      // mark ships as sunk
      if (lastShipSunk != null) {
         coordinates = lastShipSunk.getShipCoordinates(); 
         coordsList = coordinates.listIterator();       
      
         while (coordsList.hasNext()) {
            
            Coordinates square = coordsList.next();
            targetBoard[square.getX()][square.getY()] = 3;                    
         }      
      }

      // if hit remains after ships is sunk, target that hit's neighbors
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            
            if (targetBoard[i][j] == 1) { // a hit
               
               if (i - 1 >= 0 && targetBoard[i - 1][j] == 0) {
                  return new Coordinates(i - 1, j); // target above hit
               }
               
               if (i + 1 < size && targetBoard[i + 1][j] == 0) {
                  return new Coordinates(i + 1, j); // target below hit
               }               
               
               if (j - 1 >= 0 && targetBoard[i][j - 1] == 0) {
                  return new Coordinates(i, j - 1); // target left of hit
               }               
               
               if (j + 1 < size && targetBoard[i][j + 1] == 0) {
                  return new Coordinates(i, j + 1); // target right of hit
               }
            }
         }
      }
      
      // if no hits present, target highest probability      
      Coordinates target = new Coordinates(0,0);
      int highestProbability = probabilities[0][0];
      
      generateProbabilities();
   
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            if (probabilities[i][j] > highestProbability) {
               highestProbability = probabilities[i][j]; 
               target = new Coordinates(i, j);
            }         
         }
      }
      
      return target;   
   }
   
   // keep firing in 4 vectors from target until a dead end is reached    
   private boolean killTarget(Coordinates target) {
           
      boolean shipSunk = false;
      int totalShips = board.getShips();
      int x = target.getX();
      int y = target.getY();
      Coordinates up = new Coordinates(x - 1, y);
      Coordinates down = new Coordinates(x + 1, y);
      Coordinates left = new Coordinates(x, y - 1);
      Coordinates right = new Coordinates(x, y + 1);
               
      // go up if not an edge or previously targeted    
      while (up.getX() >= 0 && targetBoard[up.getX()][y] == 0 && !shipSunk && board.target(up)) { 
         targetBoard[up.getX()][y] = 1; // 1 means hit 
         up = new Coordinates(up.getX() - 1, y);
         shipSunk = totalShips != board.getShips();
      }
         
      // mark a miss if not at an edge or previously targeted
      if (up.getX() >= 0  && targetBoard[up.getX()][y] == 0 && !shipSunk) { 
         targetBoard[up.getX()][y] = 2; // 1 means miss 
      }    
         
      while (down.getX() < size && targetBoard[down.getX()][y] == 0  && !shipSunk && board.target(down)) { // go down
         targetBoard[down.getX()][y] = 1; // 1 means hit 
         down = new Coordinates(down.getX() + 1, y);
         shipSunk = totalShips != board.getShips();
      }
         
      if (down.getX() < size && targetBoard[down.getX()][y] == 0  && !shipSunk) {
         targetBoard[down.getX()][y] = 2; // 2 means miss 
      }
         
      while (left.getY() >= 0 && targetBoard[x][left.getY()] == 0  && !shipSunk && board.target(left)) { // go left
         targetBoard[x][left.getY()] = 1; // 1 means hit 
         left = new Coordinates(x, left.getY() - 1);
         shipSunk = totalShips != board.getShips(); 
      }
         
      if (left.getY() >= 0 && targetBoard[x][left.getY()] == 0 && !shipSunk) {                   
         targetBoard[x][left.getY()] = 2; // 2 means miss 
      }
         
      while (right.getY() < size && targetBoard[x][right.getY()] == 0  && !shipSunk && board.target(right)) { // go right
                  
         targetBoard[x][right.getY()] = 1; // 1 means hit 
         right = new Coordinates(x, right.getY() + 1);
         shipSunk = totalShips != board.getShips(); 
      }
         
      if (right.getY() < size && targetBoard[x][right.getY()] == 0  && !shipSunk) {                     
        targetBoard[x][right.getY()] = 2; // 2 means miss
      }
      
      return shipSunk;                       
   }
   
   private void generateProbabilities() {
                  
      // feed past results into new probabilities
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
               
            probabilities[i][j] = 0; // reset probabilties
                        
            if (targetBoard[i][j] == 2 || targetBoard[i][j] == 3) { //miss or sunken ship, don't shoot here again
                probabilities[i][j] = -1;                  
            }               
         }
      }            
      
      // probabilities are based on biggest ship remaining
      for (int i = 0; i < 4; i++) {
         if (shipTypes[i] > 0) {
            biggestShip = i + 2;
         }
      }  
      
      // ships are projected vertical and horizontal
      for (int i = biggestShip; i > 1; i--) {
         for (int j = 0; j < size; j++) {
            for (int k = 0; k < size - i + 1; k++) {
               
               boolean visitedVertical = false;
               boolean visitedHorizontal = false;
               
               for (int l = k; l < k + i; l++) {  
                  if (probabilities[j][l] == -1 && !visitedVertical) {
                     visitedVertical = true; // a square within that possible space was a miss already                           
                  }
                  if (probabilities[l][j] == -1 && !visitedHorizontal) {
                     visitedHorizontal = true; // a square within that possible space was a miss already                           
                  }                  
               }
               
               if (!visitedVertical) {
                  for (int l = k; l < k + i; l++) {
                     probabilities[j][l]++;
                  }                                    
               }
               
               if (!visitedHorizontal) {
                  for (int l = k; l < k + i; l++) {
                     probabilities[l][j]++;
                  }   
               }              
            }
         }
      }
      
      // a hit that didn't shink a ship, don't shoot here again
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
                        
            if (targetBoard[i][j] == 1) { // done now so it didn't affect probabilities
                probabilities[i][j] = -1;                  
            }               
         }
      }       
   }    
}