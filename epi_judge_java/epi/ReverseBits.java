package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    // TODO - you fill in here.

    /* BRUTE FORCE */
    // int i = 0;
    // long result = 0;
    // while (i < 63) {
    //   if (x > 0) {
    //     result += (x & 1);
    //     x >>= 1;
    //   } else {
    //     result += 0;
    //   }
    //   result <<= 1;
    //   i++;
    //
    // }
    // return result;

     /* LOOKUP TABLE SOLUTION */
     // Create lookup array for 2 bit words
     int[] twoBitReverse = new int[4];
     twoBitReverse[0] = 0;
     twoBitReverse[1] = 2;
     twoBitReverse[2] = 1;
     twoBitReverse[3] = 3;
    
     // Create lookup table array for 8 bit words
     int[] eightBitReverse = new int[256];
     for (int i = 0; i < 256; i++) {
       int reverseNum = 0;
       for (int j = 0; j < 4; j++) {
         int n = i >>> (j*2);
         reverseNum = reverseNum << 2 | twoBitReverse[n & 3];
       }
       eightBitReverse[i] = reverseNum;
     }
    
     // Use 8 bit word lookup table to compute 64 bit words
     final int WORD_SIZE = 8;
     final long BIT_MASK = 0xFF;
     long result = 0;
    
     for (int i = 0; i < WORD_SIZE; i++) {
       long y = x >>> (i * WORD_SIZE);
       result = result << WORD_SIZE | eightBitReverse[(int) (y & BIT_MASK)];
     }
     return result;
    // int i = 31;
    // return reverseBits2(x, i);

  }

  public static long reverseBits2(long x, int y) {
    /* RECURSIVE SOLUTION */
    // Create 2 bit array lookup table
    long[] twoBitLookup = new long[4];
    twoBitLookup[0] = 0;
    twoBitLookup[1] = 2;
    twoBitLookup[2] = 1;
    twoBitLookup[3] = 3;

    long result = 0;
    // Base case
    if (y == 0) {
      return 0;
    }

    result += twoBitLookup[(int) (x & 3)]; // Get reverse of first low 2 bits
    result <<= (2 * y); // Shift to the end of 64 bit word

    return result + reverseBits2(x>>>2, y--);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
