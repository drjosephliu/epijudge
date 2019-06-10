package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    // TODO - you fill in here.
    // // Move elements from A into temp array
    // List<Integer> aTmp = new ArrayList<>();

    // for (int i = 0; i < m; i++) {
    //   aTmp.add(A.get(i));
    // }

    // // Perform merge 
    // int i = 0, j = 0, curr = 0;
    // while (i < m && j < n) {
    //   if (aTmp.get(i) <= B.get(j)) {
    //     A.set(curr++, aTmp.get(i++));
    //   } else {
    //     A.set(curr++, B.get(j++));
    //   }
    // }

    // // Add remaining from B
    // while (j < n) {
    //   A.set(curr++, B.get(j++));
    // }

    // // Add remaining from A temp
    // while (i < m) { 
    //   A.set(curr++, aTmp.get(i++));
    // }

    // return; // Time complexity = O(2m + n). Space complexity = O(m)
    
    int i = m + n - 1; // last index of final array
    m--; // last index of A
    n--; // last index of B

    // Add elemments from the back
    while (m >= 0 && n >= 0) {
      if (A.get(m) >= B.get(n)) {
        A.set(i--, A.get(m--));
      } else {
        A.set(i--, B.get(n--));
      }
    }
    // Add remaining elemnts from A or B
    while (m >= 0) {
      A.set(i--, A.get(m--));
    }

    while (n >= 0) {
      A.set(i--, B.get(n--));
    }
    return;
  }
  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
