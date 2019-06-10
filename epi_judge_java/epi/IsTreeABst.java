package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;
public class IsTreeABst {
  

  public static class QueueEntry {
    public BinaryTreeNode<Integer> treeNode;
    public Integer lowerBound, upperBound;

    public QueueEntry(BinaryTreeNode<Integer> treeNode, Integer lowerBound, Integer upperBound) {
      this.treeNode = treeNode;
      this.lowerBound = lowerBound;
      this.upperBound = upperBound;
    }
  }

  // @EpiTest(testDataFile = "is_tree_a_bst.tsv")
  // public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
  //   return areKeysInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  // }

  // public static boolean areKeysInRange(BinaryTreeNode<Integer> tree, int lowerBound, int upperBound) {
  //   if (tree == null) {
  //     return true;
  //   }
  //   else if (Integer.compare(tree.data, lowerBound) < 0 || Integer.compare(tree.data, upperBound) > 0) {
  //     return false;
  //   }
  //   else {
  //     return areKeysInRange(tree.left, lowerBound, tree.data) && areKeysInRange(tree.right, tree.data, upperBound);
  //   } // Time = O(n), space = O(1)
  // }


  @EpiTest(testDataFile = "is_tree_a_bst.tsv")
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
       if (tree == null) return true;

       // Perform in-order DFS and store elements in a list
       List<Integer> values = new ArrayList<>();
       return dfs(tree, values);

      // Time = O(n), space = O(n).
  }

  public static boolean dfs(BinaryTreeNode<Integer> tree, List<Integer> values) {
    if (tree == null) return true;

    if (tree.left == null && tree.right == null) {
      return addCheck(values, tree.data);
    }

    return dfs(tree.left, values) && addCheck(values, tree.data) && dfs(tree.right, values);
  }

  public static boolean addCheck(List<Integer> values, Integer data) {
    if (values.size() > 0 && values.get(values.size() - 1) > data) {
      return false;
    }
    values.add(data);
    return true;
  }
  

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
