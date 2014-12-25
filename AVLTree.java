// COMP 282 Project 3 AVLTree.java
// Andrew Isaac

class AVLNode<E extends Comparable<E>> {
   
   // object attributes
   private AVLNode<E> parent;
   private AVLNode<E> left;
   private AVLNode<E> right;
   private E item;
      
   // constructor with item
   public AVLNode(E itm) {
      this.item = itm;
   }   
   
   //  constructor with all attributes
   public AVLNode(E itm,AVLNode<E> p, AVLNode<E> lft, AVLNode<E> rt) {      
      this.item = itm;
      this.parent = p;
      this.left = lft;
      this.right = rt;
   }
   
   // parent mutator
   public void setParent(AVLNode<E> p) {   
      this.parent = p;
   }
   
   // left child mutator
   public void setLeft(AVLNode<E> lft) {   
      this.left = lft;
   }
   
   // right child mutator
   public void setRight(AVLNode<E> rt) {   
      this.right = rt;
   }
   
   // item mutator
   public void setItem(E itm) {   
      this.item = itm;
   }
   
   // parent accessor
   public AVLNode<E> getParent() {   
      return parent;
   }
   
   // left child accessor
   public AVLNode<E> getLeft() {   
      return left;
   }
   
   // right child accessor
   public AVLNode<E> getRight() {   
      return right;
   }
   
   // item accessor
   public E getItem() {   
      return item;
   }
   
   // item toString method
   public String toString() {   
      return item.toString();
   }
}

public class AVLTree <E extends Comparable<E>> {
   
   // data fields
   private AVLNode<E> root;
   private int size;
   
   // constructor
   public AVLTree() {      
      root = null;
      size = 0;   
   }
   
   // non-recursive search method 
   public boolean search(E item) {   
      
      AVLNode<E> n = root;
      
      while (n != null) {     
            
         // item found
         if (item.compareTo(n.getItem()) == 0) {      
            return true;
         }
      
         // search left tree
         else if (item.compareTo(n.getItem()) < 0) {      
            n = n.getLeft();
         }
      
         // search right tree
         else {      
            n = n.getRight(); 
         }
      }
      
      return false; // item not found in tree     
   }
   
   // non-recursive insert method
   public void insert(E item) {
      
      AVLNode<E> parent = null;
      AVLNode<E> n = root;
      
      while (n != null) {
            
         // item belongs in left subtree   
         if (item.compareTo(n.getItem()) < 0) {
         
            parent = n;
            n = n.getLeft();                     
         }
      
         // item belongs in right subtree
         else { 
            
            parent = n;
            n = n.getRight();         
         }
      }
      
      // null tree gets a new node
      n = new AVLNode(item,parent,null,null);
      size++;
      
      if (parent != null && item.compareTo(parent.getItem()) < 0) {      
         parent.setLeft(n);
      }      
      else if (parent != null && item.compareTo(parent.getItem()) >= 0) {      
         parent.setRight(n);
      }          
      else {      
         root = n;
      }
      
      // balancing operation - requires child, parent, and grandparent != null
      while (parent != null && parent.getParent() != null) {
      
         if (!isHeightBalanced(parent.getParent())) {         
            triNodeRestructure(n);
         }
         
         n = n.getParent();
         parent = parent.getParent();
      }                  
   }
         
   // height driver method
   public int height() {   
      return rheight(root);      
   }
   
   // recursive height method
   private int rheight(AVLNode<E> n) {
      
      // null tree has 0 height
      if (n == null) {
         return 0;
      }
      
      // find greatest height of both subtrees and add 1
      else {         
         int leftHeight = rheight(n.getLeft());
         int rightHeight = rheight(n.getRight());
         
         if (leftHeight > rightHeight) {         
            return 1 + leftHeight;
         }         
         else {         
            return 1 + rightHeight;
         } 
      }   
   }
   
   public boolean isEmpty() {   
      return size == 0;
   }
   
   public int size() {   
      return size;
   }
   
   // non-recursive delete method
   public boolean delete(E item) {
   
      AVLNode<E> n = root;
      AVLNode<E> child = null;
      AVLNode<E> parent = null;

      while (n != null) {
             
         // item found
         if (item.compareTo(n.getItem()) == 0) {
         
            parent = n.getParent();
         
            // leaf becomes null
            if (n.getRight() == null && n.getLeft() == null) {
               
               if (parent == null) {               
                  root = null;
               }               
               else if (item.compareTo(parent.getItem()) < 0) {               
                  parent.setLeft(null);
               }               
               else {               
                  parent.setRight(null);
               }
            }
         
            // left pointing stick becomes its left child
            else if (n.getRight() == null) {
             
               n.getLeft().setParent(parent);                              
               if (parent == null) {               
                  root = n.getLeft();
               }                              
               else if (item.compareTo(parent.getItem()) < 0) {               
                  parent.setLeft(n.getLeft());
               }               
               else {               
                  parent.setRight(n.getLeft());
               }               
            }
         
            // right pointing stick becomes its right child
            else if (n.getLeft() == null) {
                     
               n.getRight().setParent(n.getParent());                              
               if (parent == null) {               
                  root = n.getRight();
               }                               
               else if (item.compareTo(parent.getItem()) < 0) {               
                  parent.setLeft(n.getRight());
               }               
               else {               
                  parent.setRight(n.getRight());
               }               
            }
         
            // node has 2 children
            else {
               
               AVLNode<E> smallest = n.getRight();               
               AVLNode<E> smallestParent = null;
            
               // smallest node in right subtree will be first left child with a
               // null left child - inorder successor
               while (smallest.getLeft() != null) {               
                  smallest = smallest.getLeft();                            
               }
            
               // if deleted node's immediate right child is smallest in right subtree,
               // meaning it has a null left child, deleted node's left child becomes 
               // that right child's left child and right child replaces deleted node
               if (smallest == n.getRight()) {                              
                  smallest.setLeft(n.getLeft()); 
                  n.getLeft().setParent(smallest);
                  smallestParent = smallest; // smallest might be first imbalanced node                                                
               }
            
               // since smallest is parent's left child, smallest's right child replaces
               // parent's left child, deleted node's children become smallest node's 
               // children and smallest replaces deleted node
               else {
               
                  child = smallest.getRight();
                  smallestParent = smallest.getParent();
                  
                  // smallest right child replaces smallest                                                            
                  if (child != null) {
                     child.setParent(smallestParent);
                  }                                    
                  smallestParent.setLeft(child);
               
                  // smallest gets n's children               
                  n.getRight().setParent(smallest);
                  smallest.setRight(n.getRight());               
                  n.getLeft().setParent(smallest);
                  smallest.setLeft(n.getLeft());               
               }
            
               // smallest replaces n
               smallest.setParent(parent);               
               if (parent == null) {               
                  root = smallest;
               }               
               else if (item.compareTo(parent.getItem()) < 0) {               
                  parent.setLeft(smallest);
               }               
               else {               
                  parent.setRight(smallest);
               }              
               
               // smallest parent might be first imbalanced node
               parent = smallestParent;            
            }
            
            // balancing operation                        
            while (parent != null) {
            
               if (!isHeightBalanced(parent)) {
                  
                  child = findHighestTree(parent);
                  parent = triNodeRestructure(child);
               }
               parent = parent.getParent();             
            }                  
                     
            size--;                                 
            return true;         
         }           
      
         // look for item in left subtree if < n
         else if (item.compareTo(n.getItem()) < 0) {         
            n = n.getLeft();
         }
      
         // look for item in right subtree if > n
         else {      
            n = n.getRight();
         }
      }
      return false; // could not find item to delete      
   }
   
   // returns n's greatest subtree's greatest subtree
   // used to find correct x for triNodeRestructure
   private AVLNode<E> findHighestTree(AVLNode<E> n) {
                 
      if (rheight(n.getLeft()) > rheight(n.getRight())) {               
         if (rheight(n.getLeft().getLeft()) < rheight(n.getLeft().getRight())) {
            return n.getLeft().getRight();   
         }                           
         else {
            return n.getLeft().getLeft();
         }
      }      
      else {         
         if (rheight(n.getRight().getLeft()) > rheight(n.getRight().getRight())) {
            return n.getRight().getLeft();
         }         
         else {
            return n.getRight().getRight();   
         }
      }   
   }
   
   // print in order driver method
   public void printInorder() {            
      printInorder(root);
   }
   
   // recursive print in order method
   private void printInorder(AVLNode<E> n) {
      
      // null tree has nothing to print
      if (n == null) {
      }      
      // print left subtree, item value, and right subtree
      else {      
         printInorder(n.getLeft());
         System.out.print(n.getItem() + " ");
         printInorder(n.getRight());      
      }
   }

   // print pre order driver method
   public void printPreorder() {   
      printPreorder(root);
   }
   
   // recursive print pre order method   
   private void printPreorder(AVLNode<E> n) {
      
      // null tree has nothing to print      
      if (n == null) {
      }      
      // print item value, left subtree, and right subtree
      else {      
         System.out.print(n.getItem() + " ");
         printPreorder(n.getLeft());
         printPreorder(n.getRight());
      }   
   }

   // heightBalanced driver method
   public boolean isHeightBalanced() {   
      return isHeightBalanced(root);
   }
   
   // recursive heightBalanced method
   private boolean isHeightBalanced(AVLNode<E> n) {

      // null tree is balanced
      if (n == null) {
         return true;
      }
      
      else {         
         // height difference > 1 is unbalanced
         if (Math.abs(rheight(n.getLeft())-rheight(n.getRight())) > 1) {         
            return false;
         }         
         // if both left and right subtrees are balanced, entire tree is balanced
         else {         
            return isHeightBalanced(n.getLeft()) && isHeightBalanced(n.getRight());
         }
      }
   }
   
   //triNodeRestructure method for Project 3
   private AVLNode<E> triNodeRestructure(AVLNode<E> x) {
   
      AVLNode<E> y = x.getParent();
      AVLNode<E> z = x.getParent().getParent();
      AVLNode<E> ggparent = z.getParent();  //may be null
      
      AVLNode<E> a, b, c;
      AVLNode<E> alpha, beta, gamma, delta;
    
      if (x == y.getLeft() && y == z.getLeft()) {
        
         // x is left of y and y is left of z
         a = x; b = y; c = z;
         alpha = x.getLeft();
         beta =  x.getRight();
         gamma = y.getRight();
         delta = z.getRight();            
      }
            
      else if (x == y.getRight() && y == z.getLeft()) {
      
         // x right of y and y left of z
         a = y; b = x; c = z;
         alpha = y.getLeft();
         beta = x.getLeft();
         gamma = x.getRight();
         delta = z.getRight();            
      }
      
      else if (x == y.getRight() && y == z.getRight()) {
         
         // x right of y and y right of z
         a = z; b = y; c = x;
         alpha = z.getLeft(); 
         beta = y.getLeft();
         gamma = x.getLeft();
         delta = x.getRight();
      }
      
      else if (x == y.getLeft() && y == z.getRight()) {
      
         // x left of y and y right of z
         a = z; b = x; c = y;
         alpha = z.getLeft();
         beta = x.getLeft();
         gamma = x.getRight();
         delta = y.getRight();
      }
      
      else {  //should not get here
      
         a = b = c = null;
         alpha = beta = gamma = delta = null;
      }
           
      if (ggparent == null) {
      
         root = b;
         b.setParent(null);
      }
      
      else if (z == ggparent.getLeft()) {
      
         ggparent.setLeft(b);
         b.setParent(ggparent);
      }
      
      else {
         ggparent.setRight(b);
         b.setParent(ggparent);
      }
   
      b.setLeft(a);
      a.setParent(b);
      b.setRight(c);
      c.setParent(b);
      
      a.setLeft(alpha);
      if (alpha != null) alpha.setParent(a);      
      
      a.setRight(beta);
      if (beta!= null) beta.setParent(a);
      
      c.setLeft( gamma);      
      if ( gamma != null) gamma.setParent(c);
      
      c.setRight(delta);
      if ( delta != null) delta.setParent(c);
                   
      return b;
   }// triNodeRestructure
}   