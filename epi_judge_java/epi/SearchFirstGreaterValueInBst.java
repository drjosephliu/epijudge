package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Stack;
public class SearchFirstGreaterValueInBst {

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {
    // TODO - you fill in here.
    // /* Iterative method */
    // Stack<BstNode<Integer>> stack = new Stack<>();
    // BstNode<Integer> curr = tree;

    // while (curr != null || stack.size() > 0) {

    //   while (curr != null) {
    //     stack.push(curr);
    //     curr = curr.left;
    //   }

    //   curr = stack.pop();
    //   if (curr != null) {
    //     if (curr.data > k) {
    //       return curr;
    //     }
    //   }

    //   curr = curr.right;
    // }
    // return null;
    
    BstNode<Integer> subtree = tree, first = null;

    while (subtree != null) {
      if (subtree.data > k) {
        first = subtree;
        subtree = subtree.left;
      } else {
        subtree = subtree.right;
      }
    }
    return first;
    
  }
  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
