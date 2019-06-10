package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    // // TODO - you fill in here.
    List<Integer> result = new ArrayList<>();
    if (A.isEmpty() || B.isEmpty()) {
      return result;
    }

    // // Add unique elements of smaller list to a set
    // HashSet<Integer> smallSet = new HashSet<>();

    // boolean aIsBigger= false;
    // if (A.size() < B.size()) {
    //   for (Integer num : A) {
    //     smallSet.add(num);
        
    //   }
    // } else {
    //   for (Integer num : B) {
    //     smallSet.add(num);
    //     aIsBigger = true;
    //   }
    // }
    

    // if (aIsBigger) {
    //   for (Integer num : A){
    //     if (num > B.get(B.size() - 1)) {
    //       break;
    //     }

    //     if (smallSet.contains(num)) {
    //       if (!result.contains(num)) {
    //         result.add(num);
    //       }
    //     }
    //   }
    // } else {
    //   for (Integer num : B) {
    //     if (num > A.get(A.size() - 1)) {
    //       break;
    //     }
    //     if (smallSet.contains(num)) {
    //       if (!result.contains(num)) {
    //         result.add(num);
    //       }
    //     }
    //   }
    // }
    
    // return result; // Time complexity = O(m + n). Space complexity = O(m + m/k). (m is smaller list)
    
    int i = 0, j = 0;

    while (i < A.size() && j < B.size()) {
      if (A.get(i).equals(B.get(j))) {
        if (!result.contains(A.get(i))) {
          result.add(A.get(i));
        }
        
        i++;
        j++;
      } else if (A.get(i) < B.get(j)) {
        i++;
      } else if (B.get(j) < A.get(i)){
        j++;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
