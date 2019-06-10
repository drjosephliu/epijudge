package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
import java.util.function.BiPredicate;
public class NQueens {

  @EpiTest(testDataFile = "n_queens.tsv")

  public static List<List<Integer>> nQueens(int n) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> positions = new ArrayList<>();

    nQueensHelper(0, n, positions, result);
    return result;
  }

  public static void nQueensHelper(int level, int n, List<Integer> positions, List<List<Integer>> result) {
    if (level == n) {
      List<Integer> addPositions = new ArrayList<>(positions);
      result.add(addPositions);
      return;
    }

    for (int i = 0; i < n; i++) {
      positions.add(i);
      if (checkPositions(positions, i)) {
        nQueensHelper(level+1, n, positions, result);
      }
      positions.remove(positions.size() - 1);
    }
  }

  public static boolean checkPositions(List<Integer> positions, int currX) {
    int currY = positions.size() - 1;
    for (int i = 0; i < positions.size() - 1; i++) {
      int x = positions.get(i);
      if (currX == x || currY == i || (currX+currY) == (x+i) || (currX-currY) == (x-i)) {
        return false;
      }
    }
    return true;
  }





















  // public static List<List<Integer>> nQueens(int n) {
  //   List<Integer> positions = new ArrayList<>();
  //   List<List<Integer>> result = new ArrayList<>();

  //   computeNQueens(n, 0, positions, result); // Time complexity = O(n^2), space complexity = O(n)
  //   return result;
  // }

  // private static void computeNQueens(int n, int level, List<Integer> positions, List<List<Integer>> result) {
  //   if (level == n) {
  //     List<Integer> list = new ArrayList<>(positions);
  //     result.add(list);
  //   } else {
  //     for (int j = 0; j < n; j++) {
  //       positions.add(j);
  //       if (checkPositions(positions)) {
          
  //         computeNQueens(n, level+1, positions, result);
  //       } 
  //       positions.remove(positions.size() - 1); 
  //     }
  //   }
  // }

  // private static boolean checkPositions(List<Integer> positions) {
  //   int row = positions.size() - 1;
  //   for (int i = 0; i < row; i++) {
  //     int diff = Math.abs(positions.get(i) - positions.get(row));
  //     if (diff == 0 || diff == row - i) {
  //       return false;
  //     }
  //   }
  //   return true;
  // }

  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NQueens.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
