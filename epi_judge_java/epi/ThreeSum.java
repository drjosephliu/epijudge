package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {
    // TODO - you fill in here.
    Collections.sort(A);

    int sum = 0, n = 0;

    for (int i = 0; i < A.size(); i++) {
      int remainder = t - A.get(i);
      int start = i;
      int end = A.size() - 1;

      while (start <= end) {
        if (A.get(start) + A.get(end) == remainder) {
          return true;
        }
        else if (A.get(start) + A.get(end) > remainder) {
          end = end - 1;
        }
        else {
          start = start + 1;
        }
      }
      
    }

    return false; // time = O(n^2), space = O(1)
  }
  
  public static boolean checkThreeSum(Integer sum, int n, int t) {
  
    if (sum.equals(t) && n == 3) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
