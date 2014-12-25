// Implements RSA style encryption/decryption on a given long integer using Extended Euclidean Algorithm

import java.util.Scanner;

public class Encryption {
   public static void main(String[] args) {
      
      // generate all primes between 2 and 10,000
      int[] primes = primeGen();
      //System.out.println("Primes generated.");
      
      int value = (int)(Math.random() * 1229); // 1229 primes between 2 and 10,000
            
      long randomPrime1 = primes[value];
      //System.out.println("The first random prime number is: " + randomPrime1);
                  
      value = (int)(Math.random() * 1229);
               
      long randomPrime2 = primes[value];
      //System.out.println("The second random prime number is: " + randomPrime2);
      
      long max = randomPrime1 * randomPrime2;
      //System.out.println("The max is: " + max);
      
      long totiant = (randomPrime1 - 1) * (randomPrime2 - 1);
      //System.out.println("The totiant is: " + totiant);
      
      long pub = getRelPrime(totiant);
      //System.out.println("The public key is: " + pub);
      
      long priv[] = getPriv(totiant,pub);
      
      if (priv[2] < 0) {
         priv[2] = totiant + priv[2];
         }
      
      //System.out.println("The private key is: " + priv[2]);
      //System.out.println("The totiant multiplier is: " + priv[1]);
      //System.out.println("The gcd between " + totiant + " and " + pub + " is: " + priv[0]);
      
      Scanner input = new Scanner(System.in);
            
      System.out.print("What is the integer between 2 and " + (max - 2) + " you wish to encrypt? ");
      long secret = encrypt(input.nextLong(), pub, max);
      System.out.println("The encrypted secret value is: " + secret);
      secret = encrypt(secret, priv[2], max);
      System.out.println("The decrypted secret value was: " + secret);           
      }
      
   public static long getRelPrime(long totiant) {
      
      long value = (long)(Math.random() * (totiant - 2) + 2);
      
      while (gcd(value,totiant) != 1) {
            value = (long)(Math.random() * (totiant - 2) + 2);
            }
      return value;
      }
      
   public static long gcd(long value, long relValue) {
      
      while (value != relValue) {
         if (value > relValue) {
            value = value - relValue;
            }
         else {
            relValue = relValue - value;
            }
         }

      return value;
      }      
      
   // Sieve of Atkin
   public static int[] primeGen() {

      // primes between 2 to 10,000
      int limit_sqrt = 100;
      int limit = limit_sqrt * limit_sqrt;
      boolean[] isPrime = new boolean[limit];

      // initialize the sieve
      for (int i = 5; i < limit; i++) {
         isPrime[i] = false;
         }

      // put in candidate primes: 
      // integers which have an odd number of
      // representations by certain quadratic forms
      for (int x = 1; x <= limit_sqrt; x++) {
         for (int y = 1; y <= limit_sqrt; y++) {
      
            int n = 4 * x * x + y * y;
      
            if ((n <= limit) && ((n % 12 == 1) || (n % 12 == 5))) {
               isPrime[n] = !(isPrime[n]);
               }
      
            n = 3 * x * x + y * y;
      
            if ((n <= limit) && (n % 12 == 7)) {
               isPrime[n] = !(isPrime[n]);
               }
        
            n = 3 * x * x - y * y;
    
            if ((x > y) && (n <= limit) && (n % 12 == 11)) {
               isPrime[n] = !(isPrime[n]);
               }
            }
         }
  
      // eliminate composites by sieving
      for (int n = 5; n <= limit_sqrt; n++) {
      
         if (isPrime[n]) {
            // n is prime, omit multiples of its square; this is
            // sufficient because composites which managed to get
            // on the list cannot be square-free
            for (int k = n * n; k < limit; k = k + n * n) {
               isPrime[k] = false;
               }
            }
         }

      isPrime[2] = true;
      isPrime[3] = true;
      
      int[] primes = new int[1229];
      int j = 0;
      
      for (int i = 0; i < limit; i++) {
         if (isPrime[i]) {
            primes[j] = i;
            j++;
            }
         } 

      return primes;   
      }
   
   public static long[] getPriv(long totiant, long pub) {

        long[] priv = new long[3];
        long quotient;
        
        if (pub == 0)  { 
            priv[0] = totiant;
            priv[1] = 1;
            priv[2] = 0;
            }
            
        else {
            quotient = totiant / pub;
            priv = getPriv(pub, totiant % pub);
            long temp = priv[1] - priv[2] * quotient;
            priv[1] = priv[2];
            priv[2] = temp;
            }
        
        //  System.out.println(priv[0] + " = " + totiant + "(" + priv[1] + ") + (" + priv[2] + ")" + pub); // Algorithm internals
        
        return priv;
        }
    
   public static long encrypt(long secret, long key, long max) {
          
      long encrypted = secret;
            
      for (long i = 1; i < key; i++) {
         encrypted = encrypted * secret;
         if (encrypted > max) {
            encrypted = encrypted % max;
            }
         }
      
      return encrypted;
      }  
}        
         
    

