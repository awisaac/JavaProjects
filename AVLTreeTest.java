// COMP 282 Project 3 AVLTree.java
// Andrew Isaac

public class AVLTreeTest {
   public static void main(String[] args) {
      
      int i, count = 0, height = 0;
      ANTuple tuple;
      ANTuple tupleArray[] = new ANTuple[1010];
      AVLTree tree = new AVLTree();
      
      // initial tree should be empty
      if (tree.isEmpty()) System.out.println("Tree is empty.");
      else System.out.println("Tree is not empty.");
      
      // insert 10 items for printing
      System.out.println("Inserting tuples into tree... ");
      
      for (i = 0; tree.size() < 10 && tree.isHeightBalanced(); i++) {
         
         tupleArray[i] = new ANTuple();
        
         tree.insert(tupleArray[i]);
         System.out.print(tupleArray[i] + " ");     
      }

      System.out.println("\n10 tuples inserted.");
      
      // print in order and pre order
      System.out.print("Tree printed in order: ");            
      tree.printInorder();
      System.out.print("\nTree printed pre order: ");  
      tree.printPreorder();
      
      System.out.println("\nTree size: " + tree.size());
      height = tree.height();      
      System.out.println("Tree height: " + height);
      
      if (tree.isHeightBalanced()) System.out.println("Tree is balanced.");        
      else System.out.println("Tree is not balanced.");
      
      tuple = new ANTuple();
      
      // test search method      
      while (!tree.search(tuple)) {      
         tuple = new ANTuple();
      }
      
      System.out.println("Tree contains: " + tuple);
      
      while (tree.search(tuple)) {      
         tuple = new ANTuple();
      }  
      
      System.out.println("Tree does not contain: " + tuple);
            
      // add a 1000 more to fully test insert balancing
      System.out.println("Inserting 1000 more tuples...");
      
      for (i = 10; i < 1010 && tree.isHeightBalanced(); i++) {
         
         tupleArray[i] = new ANTuple();         
         tree.insert(tupleArray[i]);       
      }
      
      // test height, isEmpty, size, and isHeightBalanced 
      System.out.println("1000 tuples inserted.");       
      System.out.println("Tree height: " + tree.height());      
            
      if (tree.isEmpty()) System.out.println("Tree is empty.");
      else System.out.println("Tree is not empty.");
      
      System.out.println("Tree size: " + tree.size());
           
      if (tree.isHeightBalanced()) System.out.println("Tree is balanced.");        
      else System.out.println("Tree is not balanced.");
      
      // test deletion balancing: all tuples get be deleted if tree remains balanced
      System.out.println("Deleting items from tree... ");
      
      count = 0;     
      while (!tree.isEmpty() && tree.isHeightBalanced()) {
 
         if (tree.delete(tupleArray[(int)(Math.random() * 1010)])) {
            count++;
         }
      }     
      
      // check that all tuples inserted were deleted
      System.out.println(count + " items deleted from tree.");
      System.out.println("Tree size: " + tree.size());
      
      if (tree.delete(new ANTuple())) { 
         System.out.println("Error: Can't delete from an empty tree.");
      }
      else {
         System.out.println("Tree really is empty!");
      }
   }    
}