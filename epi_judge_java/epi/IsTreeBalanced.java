package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {

    if (tree == null) {
      return true;
    }

    int lh = height(tree.left);
    int rh = height(tree.right);

    if (Math.abs(lh - rh) <= 1) {
      return isBalanced(tree.left) && isBalanced(tree.right);
    }
    return false;
  }


  public static int height(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return 0;
    }

    return Math.max(height(tree.left) + 1, height(tree.right) + 1); // Time = O(n), space = O(h)
  }


  // @EpiTest(testDataFile = "is_tree_balanced.tsv")
  // public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
  //   // TODO - you fill in here.
  //   if (tree == null) {
  //     return true;
  //   }

  //   int lh = height(tree.left);
  //   int rh = height(tree.right);

  //   // Check tree and all its subtrees are height balanced
  //   if (Math.abs(lh - rh) <= 1 && isBalanced(tree.left) && isBalanced(tree.right)) {
  //     return true;
  //   }

  //   return false;  // Time complexity = O(n). Space complexity = O(h).
  // }

  // public static int height(BinaryTreeNode<Integer> node) {
  //   if (node == null) {
  //     return 0;
  //   }
  //   return 1 + Math.max(height(node.left), height(node.right));
  // }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
