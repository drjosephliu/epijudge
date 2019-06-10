package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {

  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    // TODO - you fill in here.
    if (k == 0) {
      return 0;
    }

    if (k < 0) {
      return 0;
    }

    long low = 0;
    long high = k;
    int result = -1;

    while (low <= high) {
      long mid = low + ((high - low) / 2);

      if (mid*mid > k) {
        high = mid - 1;
      }
      else if (mid*mid < k) {
        result = (int)mid;
        low = mid + 1;
      }
      else if (mid*mid == k) {
        result = (int)mid;
        break;
      }
    }

    return result; // Time = O(logn), space = O(1)
  }

  // @EpiTest(testDataFile = "int_square_root.tsv")

  // public static int squareRoot(int k) {
  //   // TODO - you fill in here.
  //   if (k == 0) return 0;

  //   // /* BRUTE FORCE */
  //   // // Checks if (n+1)^2 > k by expanding out quadratic formula:
  //   // // k - n^2  <   2n + 1
  //   // for (int i = 0; i <= k; i++) {
  //   //   if ((k - (i*i)) < (2*i + 1)) {
  //   //     return i;
  //   //   }
  //   // }

  //   // return 0; // Time complexity = O(n). Space complexity = O(1)
    
  //   long low = 0;
  //   long high = k;

  //   while (low <= high) {
  //     long mid = low + ((high - low) / 2);

  //     if ((mid*mid) <= k) {
  //       low = mid + 1;
  //     }
  //     else {
  //       high = mid - 1;
  //     }
  //   }

  //   return (int)low - 1; // Time complexity = O(logn). Space complexity = O(1).
  // }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
