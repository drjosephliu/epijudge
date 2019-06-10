package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
public class SortedArrayRemoveDups {
  // Returns the number of valid entries after deletion.
  
  public static int deleteDuplicates(List<Integer> A) {

    if (A.isEmpty()) { 
      return 0; 
    }

    int distinct = 1;
    int writeIdx = 1;
    for (int i = 1; i < A.size(); i++) {
      if (!A.get(i).equals(A.get(i-1))) {
        A.set(writeIdx++, A.get(i));
        distinct++;
      }
    }
  
    return distinct;
  }

  // Time complexity = O(n). Space complexity = O(1)

  @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
