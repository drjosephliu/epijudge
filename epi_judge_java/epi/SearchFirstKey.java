package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Iterator;

public class SearchFirstKey {

   @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    
    if (A.isEmpty()) {
      return -1;
    }

    int l = 0;
    int r = A.size() - 1;
    int result = -1;

    while (l <= r) {
      int mid = l + ((r-l)/ 2);

      if (A.get(mid).equals(k)) {
        result = mid;
        r = mid - 1;
      }
      else if (A.get(mid).compareTo(k) < 0) {
        l = mid + 1;
      }
      else {
        r = mid - 1;
      }
    }

    return result; // Time = O(logn), space = O(1)
  }

  // @EpiTest(testDataFile = "search_first_key.tsv")

  // public static int searchFirstOfK(List<Integer> A, int k) {
  //   // TODO - you fill in here.
  //   // If list empty, return 
  //   if (A.isEmpty()) return -1;


  //   // Initialise low and high
  //   int low = 0;
  //   int high = A.size() - 1;
  //   int result = -1;
    
  //   // Binary search
  //   while (low <= high) {
  //     int mid = low + ((high - low)/ 2);

  //     if (A.get(mid) < k) {
  //       low = mid + 1;
  //     }
  //     else if (A.get(mid) == k) {
  //       result = mid;
  //       high = mid - 1;
  //     }
  //     else {
  //       high = mid - 1;
  //     }
  //   }
  //   return result;

  //   // Time complexity = O(logn). Space complexity = O(1).
  // }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
