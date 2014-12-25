import java.lang.Math;

public class sorts {

   public static void main(String[] args) {
      
      int n = 100000;
      int tests = 20;
      
      int[] unsorted = new int[n];
      double insSortavg = 0,selSortavg = 0, quiSortavg = 0, heaSortavg = 0, merSortavg = 0;
      
      for (int count = 1; count <= tests; count++) {
                        
      for (int i = 0; i < n; i++) {
         
         unsorted[i] = (int)(Math.random() * 100);
         }
         
      double start = System.nanoTime();
      insertionSort(unsorted);
      double stop = System.nanoTime();
      System.out.println("Insertion Sort: Complexity: O(n^2). Total time: " + ((stop - start) / 1000) + " ns.");
      insSortavg = insSortavg + (stop - start) / 1000;
      
      for (int i = 0; i < n; i++) {
         
         unsorted[i] = (int)(Math.random() * 100);
         }
      
      start = System.nanoTime();
      selectionSort(unsorted);
      stop = System.nanoTime();
      System.out.println("Selection Sort: Complexity: O(n^2). Total time: " + ((stop - start) / 1000) + " ns.");
      selSortavg = selSortavg + (stop - start) / 1000;
      
      for (int i = 0; i < n; i++) {
         
         unsorted[i] = (int)(Math.random() * 100);
         }
      
      start = System.nanoTime();
      quickSort(unsorted);
      stop = System.nanoTime();
      System.out.println("Quick Sort: Complexity: Between O(n*log n) and O(n^2). Total time: " + ((stop - start) / 1000) + " ns.");
      quiSortavg = quiSortavg + (stop - start) / 1000;
      
      for (int i = 0; i < n; i++) {
         
         unsorted[i] = (int)(Math.random() * 100);
         }
      
      start = System.nanoTime();
      heapSort(unsorted);
      stop = System.nanoTime();
      System.out.println("Heap Sort: Complexity: O(n*log n). Total time: " + ((stop - start) / 1000) + " ns.");
      heaSortavg = heaSortavg + (stop - start) / 1000;
      
      for (int i = 0; i < n; i++) {
         
         unsorted[i] = (int)(Math.random() * 100);
         }
      
      start = System.nanoTime();
      mergeSort(unsorted);
      stop = System.nanoTime();
      System.out.println("Merge Sort: Complexity: O(n*log n). Total time: " + ((stop - start) / 1000) + " ns.");
      merSortavg = merSortavg + (stop - start) / 1000;
      }
   
   System.out.println("Insertion Sort Average Time: " + insSortavg/tests);
   System.out.println("Selection Sort Average Time: " + selSortavg/tests);
   System.out.println("Quick Sort Average Time: " + quiSortavg/tests);
   System.out.println("Heap Sort Average Time: " + heaSortavg/tests);
   System.out.println("Merge Sort Average Time: " + merSortavg/tests);
   }
   
   public static void insertionSort(int[] unsorted) {
      
      for (int i = 0; i < unsorted.length; i++) {        
         int val = unsorted[i];
         int shift = i;         
         
         while (shift > 0 && val < unsorted[shift - 1]) {
            unsorted[shift] = unsorted[shift - 1];
            shift--;
            }          
         
         unsorted[shift] = val;

         }
      }
   
   public static void selectionSort(int[] unsorted) {
         
      for (int j = 0; j < unsorted.length; j++) {
         
         int smallest = j;
         
         for (int i = j; i < unsorted.length; i++) {
            if (unsorted[i] < unsorted[smallest]) {
               smallest = i;
               }
            }
                 
         // swap smallest with first element
         swap(unsorted, j, smallest);
         }
      }
   
   public static void quickSort(int[] unsorted) {
      
      quickSort(unsorted, 0, unsorted.length - 1);
      }
   
   private static void quickSort(int[] unsorted, int left, int right) {
   
      if (left >= right) {
         }
      
      else {
         
         boolean equal = true;
         int part = partition(unsorted, left, right);
         
         // an array of all equal elements can not be partitioned
         for (int i = left; i < part && equal; i++) {
            if (unsorted[i] != unsorted[i + 1]) {
               equal = false;
               }
            }
         
         if (!equal) {
            quickSort(unsorted, left, part);
            }
         
         equal = true;
         
         for (int i = part + 1; i < right && equal; i++) {
            if (unsorted[i] != unsorted[i + 1]) {
               equal = false;
               }
            }
         
         if (!equal) {
            quickSort(unsorted, part + 1, right);
            }
         }
      }
    
   private static int partition(int [] unsorted, int left, int right) {
      
      int pivot = unsorted[(int)(Math.random() * (right - left + 1) + left)];
      int smPtr = left;
      int bigPtr = right;
      
      while (bigPtr > smPtr) {
         
         while (unsorted[smPtr] <= pivot && smPtr < right) {
            // move small ptr right if element belongs son small side
            smPtr++;
            }
      
         //swap element to small side
         if (unsorted[bigPtr] <= pivot) {
            
            swap(unsorted, smPtr, bigPtr);
            }
      
         // else go to next element
         else {
            bigPtr--;
            }
         }
    
      // last elements may have small and swapped past big ptr
      while (smPtr <= right && unsorted[smPtr] <= pivot) {
         smPtr++;
         }
      
      return smPtr - 1;
      }
   
   public static void heapSort(int[] unsorted) {
      
      // buildHeap      
      for (int i = (unsorted.length - 2)/2; i >= 0; i--) {
         trickleDown(unsorted, i, unsorted.length);
         }
      
      // swap each element from bottom up for root and trickle root down
      for (int i = unsorted.length - 1; i >= 0; i--) {
         
         swap(unsorted, i, 0);
         trickleDown(unsorted, 0, i);
         }
      }
   
   private static void trickleDown(int[] heap, int parent, int size) {
      
      while (parent * 2 + 1 < size) {
         
         int swap = parent;
         int leftChild = parent * 2 + 1;
         int rightChild = leftChild + 1;
         
          // left child
         if (heap[parent] < heap[leftChild]) {
            swap = leftChild;
            }
         
          // right child
         if (rightChild < size && heap[leftChild] < heap[rightChild] && heap[parent] < heap[rightChild]) {
            swap = rightChild;
            }
          
         if (swap != parent) {
            swap(heap, parent, swap);
            parent = swap;
            }
         
         else {
            parent = size; /// ends while loop when parent is larger than children
            }
         }
      }                
   
   public static void mergeSort(int[] unsorted) {
      
      mergeSort(unsorted, 0, unsorted.length - 1);
      }
   
   private static void mergeSort(int[] unsorted, int left, int right) {
   
      if (left >= right) {
         }
      
      else {
         
         int mid = (right + left) / 2;
         int[] tempArray = new int[unsorted.length]; 
         int leftPtr = left, rightPtr = mid + 1;
                 
         mergeSort(unsorted, left, mid);
         mergeSort(unsorted, mid + 1, right);
         
         // merge
         for (int i = left; i <= right; i++) {
            
            if (leftPtr <= mid && rightPtr <= right && unsorted[leftPtr] <= unsorted[rightPtr]) {
               tempArray[i] = unsorted[leftPtr];
               leftPtr++;
               }
            
            else if (leftPtr <= mid && rightPtr <= right && unsorted[leftPtr] > unsorted[rightPtr]) { 
               tempArray[i] = unsorted[rightPtr];
               rightPtr++;
               }
            
            else if (leftPtr > mid && rightPtr <= right) {
               tempArray[i] = unsorted[rightPtr];
               rightPtr++;
               }
            
            else if (rightPtr > right && leftPtr <= mid) {
               tempArray[i] = unsorted[leftPtr];
               leftPtr++;
               }
            }
         
         for (int i = left; i <= right; i++) {
            unsorted[i] = tempArray[i];
            }         
         }
      }
   
   private static void swap(int[] array, int a, int b) {
      
      int temp = array[a];
      array[a] = array[b];
      array[b] = temp;
      }
   }