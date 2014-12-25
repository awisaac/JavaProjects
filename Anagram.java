import java.io.*;
import java.util.*;

public class Anagram {

   public static void main(String[] args) throws IOException {
   
      Scanner sc = null;
      ArrayList<String> list = new ArrayList<String>();
      String input = null;
      String output = "";
      
      // load words into ArrayList
      try {      
         sc = new Scanner(new BufferedReader(new FileReader("wordlist.txt")));
         
         while (sc.hasNext()) {
            list.add(sc.next());         
         }
      }
            
      finally {
         if (sc != null) {
            sc.close();
         }      
      }
      
      // read in string to be anagrammed - removing spaces
      try {
         sc = new Scanner(System.in);
         System.out.print("Enter a word or phrase: ");
         input = sc.nextLine();
         
         StringTokenizer st = new StringTokenizer(input);
         
         input = "";
         
         while (st.hasMoreTokens()) {
            input = input + st.nextToken();            
         }
         
      }
      finally {
         sc.close();
      }
      
      String checkInput = new String(input);
      ArrayList<String> checkList = (ArrayList)list.clone();
      int tries = 0; // don't try more than 500 times - for words with no anagrams
      
      // keep trying until all letters are used or 500 tries completed
      while (!checkInput.equals("") && tries < 500) {
      
         checkInput = new String(input);
         checkList = (ArrayList)list.clone();
         boolean wordFound = true;
         output = "";
      
         // find random words in input string until no more words left to check 
         while (wordFound) {
         
            wordFound = false;
            String word = null;
         
            while (!wordFound && !checkList.isEmpty()) {
            
               int random = (int)(Math.random() * checkList.size());
               word = checkList.get(random);
               
               // take word out of input string                    
               if (contains(checkInput,word)) {
                  wordFound = true;
                  output = output + word + " ";                              
                  checkInput = remove(checkInput,word); 
               }
               // remove word from wordlist since it can't be spelt
               else {
                  checkList.remove(random);
               }           
            }                
         }
         tries++;     
      }
      
      if (tries < 500 && !output.equals(input + " ")) {
         System.out.println(output);
      }
      else {
         System.out.println("Anagram could not be found.");
      }
   }
   
   // checks if input string contains letters of a word
   private static boolean contains(String input, String word) {
 
      char[] wordLetters = word.toCharArray();

      // go through word
      for (int i = 0; i < wordLetters.length; i++) {
         
         // if char isn't found, string does not contain
         if (input.indexOf(wordLetters[i]) == -1) {
            return false;
         }
         
         // remove it and check next letter - needed for multiple occurances of same letter
         else {                     
            StringBuilder mutable = new StringBuilder(input);  
            mutable = mutable.deleteCharAt(input.indexOf(wordLetters[i]));
            input = mutable.toString();
         } 
      }      
      
      // contains all letters - set for removal
      return true;
   }
   
   // removes letters of word from input string
   private static String remove(String input, String word) {
      
      char[] wordLetters = word.toCharArray();
      
      // go through word and remove letters
      for (int i = 0; i < wordLetters.length; i++) {
                   
         StringBuilder mutable = new StringBuilder(input);  
         mutable = mutable.deleteCharAt(input.indexOf(wordLetters[i]));
         input = mutable.toString();
      }
      
      return input;         
   }
}