package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    // TODO - you fill in here.
    
    /* Iterative method */
    // Stack<BstNode<Integer>> stack = new Stack<>();
    // BstNode<Integer> curr = tree;
    // List<Integer> result = new ArrayList<>();

    // while (curr != null || stack.size() > 0) {

    //   while (curr != null) {
    //     stack.push(curr);
    //     curr = curr.right;
    //   }

    //   curr = stack.pop();
    //   if (curr != null) {
    //     result.add(curr.data);
    //     if (result.size() == k) {
    //       return result;
    //     }
    //   }

    //   curr = curr.left;
    // }
    // return null; Time complexity = O(h + k), space = O(k)
    
    /* Recursive method */
    List<Integer> result = new ArrayList<>();
    helper(tree, k, result);
    return result;
  }

  private static void helper(BstNode<Integer> tree, int k, List<Integer> result) {
    if (tree != null && result.size() < k) {
      helper(tree.right, k, result);
      result.add(tree.data);
      helper(tree.left, k, result);
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
