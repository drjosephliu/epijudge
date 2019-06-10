package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Iterator;
import java.util.HashMap;
public class AbsentValueArray {

  private static final int NUM_BUCKET = 1 << 16;

  @EpiTest(testDataFile = "absent_value_array.tsv")
  public static int findMissingElement(Iterable<Integer> stream) {
    int[] counter = new int[NUM_BUCKET];
    Iterator<Integer> s = stream.iterator();
    while (s.hasNext()) {
      int idx = s.next() >>> 16;
      counter[idx]++;
    }

    for (int i = 0; i < counter.length; i++) {
      if (counter[i] == 0) {

      }
    }

    for (int i = 0; i < counter.length; i++) {
   
      HashMap<Integer, Integer> bitVec = new HashMap<>();
      s = stream.iterator();
      
      // This bucket is missing addresses
      if (counter[i] < NUM_BUCKET) {

        while (s.hasNext()) {
          int x = s.next();

          if (i == (x >>> 16)) {
            // Found address and store lower 16 bits in hashmap
            bitVec.put((NUM_BUCKET - 1) & x, 1);
          }
        }

        // Loop through hashmap and find address with missing lower 16 bits,
        // then return full address with upper 16 bits
        for (int j = 0; j < (1 << 16); j++) {
          if (!bitVec.containsKey(j)) {
            return (i << 16) | j;
          }
        }

      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "AbsentValueArray.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
