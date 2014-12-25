import java.util.*;

public class BattleShip {
   
   public static void main(String[] args) {
   
      int hits;
      int shotsFired;
      
      ShipBoard shipBoard = createBoard(); // user generated board     
      System.out.println(shipBoard);      
      int size = shipBoard.getSize();
      int[] shipTypes = shipBoard.getShipTypes();
      
      ShipBoard shipBoard2 = new ShipBoard(size, shipTypes); // computer generated board
      System.out.println("Beginning user's turn...");
      int userScore = userGame(shipBoard2); // run user's game on computer's board
      int computerScore = computerGame(shipBoard); // run computer's game on user's board
      
      if (userScore < computerScore) {
         System.out.println("You win!");
      }
      else {
         System.out.println("You lose! Better luck next time!");
      }
   }
   
   private static ShipBoard createBoard() {
   
      Scanner sc = new Scanner(System.in);
      System.out.print("Length/width of board: ");
      int size = sc.nextInt();
      ShipBoard shipBoard = new ShipBoard(size);
      
      System.out.print("Number of ships: ");
      int ships = sc.nextInt();
      
      for (int i = 1; i <= ships; i++) {
         
         System.out.print("Length of ship " + i + " (2-5): ");
         int length = sc.nextInt();
         System.out.print("Row of ship " + i + " (0-" + (size - 1) + "): ");
         int x = sc.nextInt();
         System.out.print("Column of ship " + i + " (0-" + (size - 1) + "): ");
         int y = sc.nextInt();
         System.out.print("Direction of ship " + i + " (0/1): ");
         int direction = sc.nextInt();
         
         if (shipBoard.generateShip(length, x, y, direction)) {
            System.out.println("Ship " + i + " successfully placed on board.");
            System.out.println(shipBoard); 
         }
         else {
            System.out.println("Error: Ship " + i + " can't go there.");
            System.out.println(shipBoard); 
            i--;
         } 
      }
      
      return shipBoard;
   }
   
   private static int userGame(ShipBoard shipBoard) {
      
      int ships = shipBoard.getShips();
      int size = shipBoard.getSize();
      int[][] targetBoard = new int[size][size];
      Ship lastShipSunk;
      int x = 0, y = 0;
      
      while (ships > 0) {
         
         boolean validInput = true;   
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter target coordinates separated by comma ( x, y ): ");
         String input = sc.next();
         String[] nums = input.split(",");
         
         try {
            x = Integer.valueOf(nums[0]);
            y = Integer.valueOf(nums[1]);
         }
         
         catch(Exception e) {
            System.out.println("Error: Invalid input.");
            validInput = false;
         }
         
         if (x < 0 || x >= size || y < 0 || y >= size) {
            validInput = false;
            System.out.println("Error: Invalid coordinates.");
         }
         
         if (targetBoard[x][y] != 0 && validInput) {
            validInput = false;
            System.out.println("Error: Previously targeted.");
         }
         
         if (validInput) {
            if (shipBoard.target(new Coordinates(x, y))) {
               targetBoard[x][y] = 1;
            
               if (ships != shipBoard.getShips()) {
                  lastShipSunk = shipBoard.getLastShipSunk();
                  ArrayList<Coordinates> coordinates = lastShipSunk.getShipCoordinates(); 
                  ListIterator<Coordinates> coordsList = coordinates.listIterator();                  
               
                  while (coordsList.hasNext()) {
            
                     Coordinates square = coordsList.next();
                     targetBoard[square.getX()][square.getY()] = 3;                    
                  }
                  
                  ships = shipBoard.getShips();           
               }
            }         
            else {
               targetBoard[x][y] = 2;       
            }
                  
            for (int i = 0; i < size; i++) {
               for (int j = 0; j < size; j++) {
                  System.out.print(targetBoard[i][j] + " ");
               }
               System.out.println();
            }
         }
      }
      int hits = shipBoard.getHits();
      int shotsFired = shipBoard.getShotsFired();
      
      System.out.println("Shots Fired: " + shotsFired);
      System.out.println("Hits: " + hits);
      System.out.println("Accuracy: " + (double)hits / shotsFired);
      System.out.println("Ship coverage: " + (double)hits / (size * size)); 
      System.out.println("Targeted Coverage: " + (double)shotsFired / (size * size));
      
      return shotsFired;
   }
   
   private static int computerGame(ShipBoard shipBoard) {
         
      Targeter targeter = new Targeter(shipBoard);
      targeter.attack();
      
      int hits = shipBoard.getHits();
      int shotsFired = shipBoard.getShotsFired();
      int size = shipBoard.getSize();
      
      System.out.println("Shots Fired: " + shotsFired);
      System.out.println("Hits: " + hits);
      System.out.println("Accuracy: " + (double)hits / shotsFired);
      System.out.println("Ship coverage: " + (double)hits / (size * size)); 
      System.out.println("Targeted Coverage: " + (double)shotsFired / (size * size));
      
      return shotsFired;  
   }
} 

  

   





