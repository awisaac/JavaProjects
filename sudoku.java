// 1. Andrew Isaac
// 2. Program 2
// 3. Due: February 13, 2014, handed in February 13, 2014
// 4. Inputs a 2-dimensional array representing a sudoku
// puzzle and outputs attempted puzzle solution.

// Square class provides Square constructor and Square 
// getter and setter for a Square's row and col
class Square {
 
   private int row, col;
   
   // Square constructor 
   public Square(int row, int col) {      
      setRow(row);
      setCol(col);   
      }
   
   // Square row setter    
   public void setRow(int row) {   
      this.row = row;
      }
   
   // Square col setter    
   public void setCol(int col) {   
      this.col = col;
      }
   
   // Square row getter 
   public int getRow() {   
      return row;
      }
   
   // Square col getter   
   public int getCol() {   
      return col;
      }
   }

// sudoku class provides the methods to input a 2-
// dimensional array, create a sudoku object, output 
// values as a string, test initial puzzle validity, 
// and solve puzzle by searching for single possibility
// constraints based on value, row, col, and box.
class sudoku {

   public static String myName() {
      return "Andrew Isaac";
      }
   
   // outputs true if puzzle is solved
   public boolean isComplete() {
      
      boolean complete = true;
      
      for (int row = 0; row < 9 && complete; row++) {
         for (int col = 0; col < 9 && complete; col++) {
            if (board[row][col] == 0) {
               complete = false;
               }
            }
         }
         
      return complete;
      }
   
   private int board[][];
   
   //default constructor
   public sudoku() {
      
      board = new int[9][9];
      
      // nested for loops to build array of 0 values
      for (int row = 0; row < 9; row++) {         
         for (int col = 0; col < 9; col++) {            
            board[row][col] = 0;
            }
         }
      }
   
   // constructor with 2D array input         
   public sudoku(int data[][]) {
      
      board = new int[9][9];
      
      //nested for loops to copy array from data
      for (int row = 0; row < 9; row++) {
         for (int col = 0; col < 9; col++) {
            board[row][col] = data[row][col];
            }
         }
      //output board values as a string   
      toString();
      }
            
   // use String.valueOf( i ) to convert ints to Strings   
   public String toString() {
      
      // result is string with 3 x 3 box grid and 
      // board values in a 9 x 9 grid
      String result = "-----------\n";
      
      for (int row = 0; row < 9; row++) {
         for (int col = 0; col < 9; col++) {
            result += String.valueOf(board[row][col]);
            if (col == 2 || col == 5) {
               result += "|";
               }
            }
         
         result += "\n";
         
         if (row == 2 || row == 5) {
               result += "-----------\n";
               }         
         }
      
      return result;    
      }
      
   // Returns true if the current board does not violate the sudoku rules
   public boolean isValid() {
         
      int[] count = new int[10]; // values in game between 0 and 10
      int row, col, ct;
      boolean valid = true;
 
      // check each row
      for (row = 0; row < 9 && valid; row++) {
         for (ct = 0; ct <= 9; ct++) {
            count[ct] = 0;
            }
         for (col = 0; col < 9; col++) {
            count[board[row][col]]++;
            }
         for (ct = 1; ct <= 9; ct++) {
            if (count[ct] > 1) {
               valid = false;
               }
            }
         }
       
       // check each col
      for (col = 0; col < 9 && valid; col++) {
         for (ct = 0; ct <= 9; ct++) {
            count[ct] = 0;
            }
         for (row = 0; row < 9; row++) {
            count[board[row][col]]++;
            }
         for (ct = 1; ct <= 9; ct++) {
            if (count[ct] > 1) {
               valid = false;
               }
            }
         }
         
      // check each box
      for (int rowbox = 0; rowbox < 3 && valid; rowbox++) {
         for (int colbox = 0; colbox < 3 && valid; colbox++) {
            for (ct = 0; ct <= 9; ct++) {
               count[ct] = 0;
               }      
            for (row = rowbox * 3; row < rowbox * 3 + 3; row++) {         
               for (col = colbox * 3; col < colbox * 3 + 3; col++) {
                  count[board[row][col]]++;
                  }
               }               
            for (ct = 1; ct <= 9; ct++) {
               if (count[ct] > 1) {
                  valid = false;
                  }        
               }
            }        
         }         
              
      return valid;    
      }
         
   // return true if val appears in the row of the puzzle
   private boolean doesRowContain(int row, int val) {
      
      boolean contains = false;
      
      for (int col = 0; col < 9 && !contains; col++) {
         
         if (board[row][col] == val) {
            contains = true;
            }
         }
         
      return contains;      
      }   
      
    // return true if val appears in the col (column) of the puzzle
    private boolean doesColContain(int col, int val) {
         
      boolean contains = false;
      
      for (int row = 0; row < 9 && !contains; row++) {
         
         if (board[row][col] == val) {
            contains = true;
            }
         }
         
      return contains;      
      }       
          
    // return true if val appears in the 3 x 3 box
    private boolean doesBoxContain(int rowbox, int colbox, int val) {

      boolean contains = false;
           
      for (int row = rowbox * 3; row < rowbox * 3 + 3 && !contains; row++) {
         for (int col = colbox * 3; col < colbox * 3 + 3 && !contains; col++) { 
            
            if (board[row][col] == val) {
               contains = true;
               }
            }
         }
         
      return contains;
      }
   
   // return n if n is the only possible value for this square
   // return 0 otherwise
   private int fillSquare(Square square) {
      
      int n = 0, val = 0, possibilities = 0, sqVal = 0;
      
      // checks if val is possible inside of square     
      if (board[square.getRow()][square.getCol()] == 0) {
         for (val = 1; val <= 9; val++) {
            if (!doesRowContain(square.getRow(),val)
               && !doesColContain(square.getCol(),val)
               && !doesBoxContain(square.getRow() / 3, square.getCol() / 3, val)) {
                  possibilities++;
                  sqVal = val;
                  }              
               }
            }
      
      // if only 1 possibility was found, val must go in square  
      if (possibilities == 1) {
         n = sqVal;
         }   
      return n;  
      }
       
   // return a square if only one possibility for val in row
   // return null otherwise
   private Square rowFill(int row, int val) {
   
      int possibilities = 0, col = 0, sqCol = 0;
      Square sq = null;
      
      // checks each square in a row to see if val is possible
      if (!doesRowContain(row,val)) {
         for (col = 0; col < 9; col++) {
            if (board[row][col] == 0
               && !doesBoxContain(row / 3,col / 3,val)
               && !doesColContain(col,val)) { 
                  sqCol = col;
                  possibilities++;                  
                  }
               }
            }
      
      // if only 1 possibility was found, val must go in that Square  
      if (possibilities == 1) {
         sq = new Square(row,sqCol);
         }   
   
      return sq;               
      }
          
   // return a square if only one possibility for val in col
   // return null otherwise
   private Square colFill(int col, int val) {
   
      int possibilities = 0, row = 0, sqRow = 0;
      Square sq = null;
      
       // checks each square in a col to see if val is possible
      if (!doesColContain(col,val)) {
         for (row = 0; row < 9; row++) {
            if (board[row][col] == 0
               && !doesBoxContain(row / 3,col / 3,val)
               && !doesRowContain(row,val)) { 
                  sqRow = row;
                  possibilities++;                  
                  }
               }
            }
      // if only 1 possibility was found, val must go in that Square 
      if (possibilities == 1) {
         sq = new Square(sqRow,col);
         }   

      return sq;               
      }
      
   // return a square if only one possibility for val in the box
   // return null otherwise
   private Square boxFill(int rowbox, int colbox, int val) {
   
      int possibilities = 0, col = 0, row = 0, sqRow = 0, sqCol = 0;
      Square sq = null;
      
      // checks each square in a box to see if val is possible
      if (!doesBoxContain(rowbox,colbox,val)) {
         for (row = rowbox * 3; row < rowbox * 3 + 3 && possibilities < 2; row++) {
            for (col = colbox * 3; col < colbox * 3 + 3 && possibilities < 2; col++) {
               if (board[row][col] == 0
                  && !doesRowContain(row,val)
                  && !doesColContain(col,val)) { 
                  sqCol = col;
                  sqRow = row;
                  possibilities++;
                  }
               }
            }
         }
      // if only 1 possibility was found, val must go in that Square 
      if (possibilities == 1) {
         sq = new Square(sqRow,sqCol);
         }   

      return sq;               
      }                   
   
   // attempts to solve puzzle by filling in single possibilities based on square,
   // row, col, or box until no further possibilities can be found  
   public void solve() {
      
      int row, col, result, val;
      Square sq = new Square(0, 0), sqResult;
      boolean changed;
           
      do {
         
         changed = false;
         result = 0;         
         
         // checks each square for single possibility and fills in result if found
         for (row = 0; row < 9; row++) {
            sq.setRow(row);
            for (col = 0; col < 9; col++) {
               sq.setCol(col);
               result = fillSquare(sq);
               if (result > 0) {
                  board[row][col] = result;
                  changed = true;                                    
                  }
               }
            }
      
         // checks each val for single possibility within a row and fills in val if found 
         for (row = 0; row < 9; row++) {
            for (val = 1; val <= 9; val++) {
               sqResult = rowFill(row, val);
               if (sqResult != null) {
                  board[row][sqResult.getCol()] = val;
                  changed = true; 
                  } 
               } 
            }
            
         // checks each val for single possibility within a col and fills in val if found
         for (col = 0; col < 9; col++) {
            for (val = 1; val <= 9; val++) {
               sqResult = colFill(col, val);
               if (sqResult != null) {
                  board[sqResult.getRow()][col] = val;
                  changed = true; 
                  } 
               } 
            }            
         
         // checks each val for single possibility within a box and fills in val if found   
         for (row = 0; row < 3; row++) {
            for (col = 0; col < 3; col++) {
               for (val = 1; val <= 9; val++) {
                  sqResult = boxFill(row,col,val);
                  if (sqResult != null) {
                     board[sqResult.getRow()][sqResult.getCol()] = val;
                     changed = true; 
                     } 
                  } 
               }
            }                                      
         } while (changed);      
      }
      
   
    // constructor from an array of strings
    public sudoku(String s[]) {
        board = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                board[row][col] = (int) (s[row].charAt(col + col/3)) - 48 ;
            }
        }
    }

    // Unformatted String of the board
    public String toString2() {
        String result = new String();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                result = result + String.valueOf(board[row][col]);
            }
        }
        return result;
    }
    
    public void rotate() {
        int[][] temp = new int[9][9];
        int row, col;
        for (row = 0; row < 9; row++) {
            for (col = 0; col < 9; col++) {
                temp[col][8-row] = board[row][col];
            }
        }
        for (row = 0; row < 9; row++) {
            for (col = 0; col < 9; col++) {
                board[row][col] = temp[row][col];
            }
        }
    }
   
   // just for fun recursive algorithm for the hard ones   
   public void solveHard() {
      
      int row, col, val, zeros = 0;
      int restoreBoard[][] = new int[9][9];
      Square sq = new Square(0,0);
      
      solve();
      
      // tracks number of remaining spaces to be filled
      for (row = 0; row < 9; row++) {
         for (col = 0; col < 9; col++) {
            if (board[row][col] == 0) {
               zeros++;
               }
            }
         }
               
      // copy of board to be restored for false values
      for (row = 0; row < 9; row++) {
         for (col = 0; col < 9; col++) {
            restoreBoard[row][col] = board[row][col];
            }
         }
      
      // recursively try allowed values with best odds until puzzle can be solved   
      for (row = 0; row < 9; row++) {
         for (col = 0; col < 9; col++) {
            if (board[row][col] == 0) {
               sq = new Square(row,col);
               for (val = 1; val <=9; val++) {                  
                  if (!doesRowContain(row,val)
                     && !doesColContain(col,val)
                     && !doesBoxContain(row / 3, col / 3, val)
                     && squarePossibilities(sq) == boardPossibilities()) {                                  
                     board[row][col] = val;
                     System.out.println("Guessing that square " + row + ", " + col
                        + " is filled with " + val + ". " + squarePossibilities(sq) + " other possibilities.");
                     solve();
                     if (boardPossibilities() == 0) {
                        System.out.println("Deadend reached. Value " + val + " is not at square " + row + ", " + col);
                        for (int i = 0; i < 9; i++) {
                           for (int j = 0; j < 9; j++) {
                              board[i][j] = restoreBoard[i][j];
                              }
                           }                     
                        }
                     else if (zeros > 0) {
                        solveHard();
                        }
                     }
                  }
               }
            }
         }    
      }
      
   // finds the square with the lowest possibilities on a board   
   public int boardPossibilities() { 
      int row, col, possibilities = 0, lowestPossibilities = 9;
      Square square;  
      
      for (row = 0; row < 9 && lowestPossibilities > 0; row++ ) {
         for (col = 0; col < 9 && lowestPossibilities > 0; col++) {     
            if (board[row][col] == 0) {
               square = new Square(row,col);
               possibilities = squarePossibilities(square);
                  if (lowestPossibilities > possibilities) {
                     lowestPossibilities = possibilities;
                     }
                  }                  
               }              
            }        
      return lowestPossibilities;
      }

   // returns the possibilities for a given square
   public int squarePossibilities(Square square) { 
      
      int val, possibilities = 0;
       
      for (val = 1; val <= 9; val++) {
         if (!doesRowContain(square.getRow(),val)
            && !doesColContain(square.getCol(),val)
            && !doesBoxContain(square.getRow() / 3, square.getCol() / 3, val)) {                     
                     
            possibilities++;
            }              
         }
      return possibilities;     
      }
   }                   
       