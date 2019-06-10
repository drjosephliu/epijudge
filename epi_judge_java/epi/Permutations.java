package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.ArrayList;
public class Permutations {
  @EpiTest(testDataFile = "permutations.tsv")

  public static List<List<Integer>> permutations(List<Integer> A) {
    // TODO - you fill in here.
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> currentPermutation = new ArrayList<>();
    permutationsHelper(result, currentPermutation, 0, A);
    return result;
  }

  public static void permutationsHelper(List<List<Integer>> result,
      List<Integer> currentPermutation, int n, List<Integer> A) {
    if (n == A.size()) {
      List<Integer> newPermutation = new ArrayList<>(currentPermutation);
      result.add(newPermutation);
      return;
    }

    for (int i = 0; i < A.size(); i++) {
      if (!currentPermutation.contains(A.get(i))) {
        currentPermutation.add(A.get(i));
        permutationsHelper(result, currentPermutation, n + 1, A);
        currentPermutation.remove(currentPermutation.size() - 1);
      }
    }
    return; // Time = O(n x n!), space = O(n)
  }

  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    for (List<Integer> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<Integer> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Permutations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
