/* Implementation of Gale Shapley algorithm for stable matching
and verification that there does not exist a n = 3 instance where all
possible matches are stable. 
Written by Andrew Isaac on 2/1/15 */ 

import java.util.ArrayList;
import java.util.Iterator;

public class StableMatch {

   public static void main(String[] args) {
      
      int n = 3;
      int[] a = new int[n];
      
      for (int i = 0; i < n; i++) {        
         a[i] = i;
      }
      
      int[][] men = new int[n][n];
      int[][] women = new int[n][n];
      
      // pick random men and women preferences from all possible permutations
      ArrayList<Integer[]> allMatches = new ArrayList<Integer[]>();      
      allMatches(n, n, allMatches, a);
      
      for (int i = 0; i < n; i++) {
      
         Integer[] menPrefs = allMatches.get((int)(Math.random() * n));
         Integer[] womenPrefs = allMatches.get((int)(Math.random() * n));
         
         for (int j = 0; j < n; j++) {
            men[i][j] = menPrefs[j];
            women[i][j] = womenPrefs[j];
         }            
      }
      
      for (int i = 0; i < n; i++) {
         System.out.print("Man " + i + ": ");
         for (int j = 0; j < n; j++) {
            System.out.print(men[i][j] + " ");
         }
         System.out.print("Woman " + i + ": ");
         for (int j = 0; j < n; j++) {
            System.out.print(women[i][j] + " ");
         }
         System.out.println();       
      }
      
           
      System.out.println("\nGale Shapley matching:");
      int[] matches = galeShapley(n,men,women);
      for (int i = 0; i < n; i++) {      
         System.out.println("Woman " + i + " is married to man " + matches[i]);
      }   
      if (isStable(n, matches, men, women)) {
         System.out.println("Gale-Shapley solution verified to be stable.");
      }
      else {
         System.out.println("Must be a programming mistake.");
      }

      // check if there exists an instance where all possible matches are stable
      boolean instanceFound = false;
      int[] currentInstance = new int[n * 2];
      int fact = fact(n);
     
      while (!instanceFound && !finished(n, fact, currentInstance)) {
         
         // get each instance from numbered current instance       
         for (int i = 0; i < n; i++) {
            Integer[] intMen = allMatches.get(currentInstance[i]);
            Integer[] intWomen = allMatches.get(currentInstance[i + n]);
                        
            for (int j = 0; j < n; j++) {
               men[i][j] = intMen[j];
               women[i][j] = intWomen[j];
            }              
         }                 
      
         Iterator<Integer[]> matchesIterator = allMatches.iterator();
         boolean stable = true;
         
         // test for stabiliy
         while (matchesIterator.hasNext() && stable) {
            Integer[] intArray = matchesIterator.next();
            
            int[] testMatches = new int[n];
            
            for (int i = 0; i < n; i++) {
               testMatches[i] = intArray[i];
            }
            
            stable = isStable(n, testMatches, men, women);
         }
         
         if (stable) {
            instanceFound = true;
         }
         else {
            nextInstance(n, fact, currentInstance);
         }
      }
      if (instanceFound) {
         System.out.println("Fully stable instance found.");
         for (int i = 0; i < n; i++) {
            System.out.print("Man " + i + ": ");
            for (int j = 0; j < n; j++) {
               System.out.print(men[i][j] + " ");
            }
         
            System.out.print(" Woman " + i + ": ");
            for (int j = 0; j < n; j++) {
               System.out.print(women[i][j] + " ");
            }
         
            System.out.println();
         }         
      }
      else {
         System.out.println("No instances exist where all possible matches are stable.");
      }
   }
   
   // calculate n!      
   public static int fact(int n) {   
      int i = 1;
      int fact = 1;      
      while (i <= n) {
         fact = fact * i;
         i++;
      }
      return fact;
   } 
       
   // base n adder
   public static void nextInstance(int n, int fact, int[] currentInstance) {
      
      int i = 0;
      while (currentInstance[i] == fact - 1) {
         currentInstance[i] = 0;
         i++;         
      }
      
      currentInstance[i]++;
      for (i = 0; i < n * 2; i++) {
      }
   }
   
   // stop at biggest n digit number in base n   
   public static boolean finished(int n, int fact, int[] currentInstance) {
      
      int i = 0;
      while (i < n * 2 && currentInstance[i] == fact - 1) {
         i++;
      }
      
      return i == n * 2;
   }
   
   // heap's algorithm for generating all permutations
   public static void allMatches(int n, int size, ArrayList<Integer[]> allMatches, int[] array) {
    
      if (size == 1) {
      
         Integer[] b = new Integer[n];
                  
         for (int i = 0; i < n; i++) {
            b[i] = array[i];
         }                  
         allMatches.add(b);
      }      
      else {
         for (int i = 0; i < size; i++) {
            allMatches(n, size - 1, allMatches, array);
            int j;
            if ((size - 1) % 2 == 0) j = 1;
            else j = i;
         
            int temp = array[j];
            array[j] = array[size - 1];
            array[size - 1] = temp;  
         }
      }            
   }
   
   // verifies no woman wants a better man that wants her also and no man wants a better woman that wants him also
   public static boolean isStable(int size, int[] matches, int[][] men, int[][] women) {
      
      boolean stable = true;
       
      for (int i = 0; i < size && stable; i++) { // stable women
         
         int currentMan = matches[i];
         int woman = i;
         int potentialMan;
         int j = size - 1;
         
         while (women[i][j] != currentMan) { // get currentManRank
            j--;
         }

         while (j > 0) { // for all better men                          
            j--;
            
            potentialMan = women[i][j];

            // will potential man leave his current wife for woman?
            int k = 0;
            while (potentialMan != matches[k]) {
               k++;
            }
            int currentWife = k;

            k = 0;
            while (currentWife != men[potentialMan][k]) {
               k++;
            }
            int currentWifeRank = k;

            k = 0; 
            while (woman != men[potentialMan][k]) {
               k++;
            }
            
            if (k < currentWifeRank) {
               stable = false;
            }       
         }
      }
      
      for (int i = 0; i < size && stable; i++) {  // stable men
         int man = matches[i];
         int currentWife = i;
         int potentialWoman;
         int j = size - 1;
         
         while (men[man][j] != currentWife) { // get currentWifeRank
            j--;
         }

         while (j > 0) { // for all better women
                                   
            j--;
            potentialWoman = men[man][j];

            // will potential woman leave current husband for man?
            int currentHusband = matches[potentialWoman];

            int k = 0;            
            while (currentHusband != women[potentialWoman][k]) {
               k++;
            }            
            int currentRank = k;

            k = 0;            
            while (man != women[potentialWoman][k]) {
               k++;
            }            
            int potentialRank = k;

            if (potentialRank < currentRank) {
               stable = false;
            }            
         }
      }     
      
      return stable;
   } 
   
   public static int[] galeShapley(int size, int[][] men, int[][] women) {
   
      int[] fiance = new int[size];
      boolean[] mEngaged = new boolean[size];    
      boolean[] wEngaged = new boolean[size];
      boolean allEngaged = false;
      
      for (int i = 0; i < size; i++) {
         mEngaged[i] = false;      
         wEngaged[i] = false;
         fiance[i] = -1;
      }
      
      int man = 0;
      int[] desiredWoman = new int[size];      
      int[] currentManRank  = new int[size];
      int[] potentialManRank = new int[size];
            
      while (!allEngaged) {
               
         while (!mEngaged[man]) {
            
            int cDW = men[man][desiredWoman[man]]; // current desired woman           
                     
            System.out.println("Man " + man + " proposes to woman " +cDW);
            
            if (!wEngaged[cDW]) { // all the single ladies
               System.out.println("Woman " + cDW + " accepts man " + man);
               mEngaged[man] = true;
               wEngaged[cDW] = true;
               fiance[cDW] = man;
               
               int i = 0;               
               while (women[cDW][i] != man) { // determine how good this man is
                  i++;
               }               
               currentManRank[cDW] = i; 
            }
            else { // who does the woman want?
               
               int i = 0;               
               while (women[cDW][i] != man) { // determine how good this man is
                  i++;
               }               
               potentialManRank[cDW] = i; 
               
               if (potentialManRank[cDW] < currentManRank[cDW]) { // she wants him instead of the shlub she's with
                  System.out.println("Woman " + cDW + " rejects her old fiance, man " + fiance[cDW] + ", for man " + man);
                  mEngaged[man] = true;
                  mEngaged[fiance[cDW]] = false; // this guy is now single - loser
                  fiance[cDW] = man;
                  
                  i = 0;               
                  while (women[cDW][i] != man) { // determine how good this man is
                     i++;
                  }               
                  currentManRank[cDW] = i;                 
               }
               else { // or she doesn't want him so he looks for his next most desirable woman
                  System.out.println("Woman " + cDW + " keeps her old fiance, man " + fiance[cDW] + ", and rejects man " + man);
                  desiredWoman[man]++;
               }                
            }        
         }
         man = (man + 1) % size;
         int count = 0;
         for (int i = 0; i < size; i++) {
            if (mEngaged[i]) {
               count++;
            }         
         }
         
         if (count == size) {
            allEngaged = true;
         }                        
      }
      
      return fiance;  
   }
}