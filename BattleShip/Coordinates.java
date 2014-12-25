public class Coordinates implements Comparable<Coordinates> {
   
   private int x;
   private int y;
   
   public Coordinates(int x, int y) {
      this.x = x;
      this.y = y;
   }
   
   public int getX() {
      return x;
   }
   
   public int getY() {
      return y;
   }
   
   public int compareTo(Coordinates coordinates) {
      
      if (coordinates.getX() == x && coordinates.getY() == y) {
         return 0;
      }
      
      else {      
         return -1;
      }    
   }
   
   public String toString() {
   
      return "(" + x + ", " + y + ")";
   }   
}
