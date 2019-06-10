package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    // TODO - you fill in here.

    List<Integer> primeSieve = new ArrayList<>();
    boolean[] marked = new boolean[n+1];
    Arrays.fill(marked, true);
    marked[0] = false;
    marked[1] = false;

    for (int i = 2; i <= n; i++) {
      if (marked[i]) {
        primeSieve.add(i);
        for (int j = 2; j < marked.length; j++) {
          if (i*j > n) {
            break;
          }
          else {
            marked[i*j] = false;
          }
        }
      }
    }

    return primeSieve; // Time = O(n ^ 3/2), space = O(n)
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
