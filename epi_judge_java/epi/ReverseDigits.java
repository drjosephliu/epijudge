package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {

    boolean isNeg = false;
    if (x < 0) {
      x = -x;
      isNeg = true;
    }

    /* BRUTE FORCE */
    long result = 0;

    while (x != 0) {
      result = result * 10 + x % 10;
      x /= 10;
    }
    return isNeg ? -result : result;

    // Time complexity = O(n)
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
