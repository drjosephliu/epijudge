package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {

    int left = 0, right = A.size() - 1;

    while (left < right) {
      int mid = left + ((right - left) / 2);

      if (A.get(mid) < A.get(right)) {
        right = mid;
      }
      else {
        left = mid + 1;
      }
    }
    return left;

  }














  // public static int searchSmallest(List<Integer> A) {
  //   // TODO - you fill in here.
  //   int left = 0, right = A.size() - 1;

  //   while (left < right) {
  //     int mid = left + ((right - left) / 2);
  //     if (A.get(mid) < A.get(right)) {
  //       right = mid;
  //     }
  //     else {
  //       left = mid + 1;
  //     }
  //   }
  //   return left; // Time = O(logn), space = O(1)
  // }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
