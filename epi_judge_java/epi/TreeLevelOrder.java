package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.

    List<List<Integer>> result = new LinkedList<>();

    if (tree == null) {
      return result;
    }

    Queue<BinaryTreeNode<Integer>> nextToVisit = new LinkedList<>();

    nextToVisit.add(tree);

    while (!nextToVisit.isEmpty()) {

      List<Integer> nodeValues = new LinkedList<>();
      Queue<BinaryTreeNode<Integer>> temp = new LinkedList<>();

      while(!nextToVisit.isEmpty()) {
        BinaryTreeNode<Integer> node = nextToVisit.poll();

        nodeValues.add(node.data);

        if (node.left != null) {
          temp.add(node.left);
        }

        if (node.right != null) {
          temp.add(node.right);
        }
      }

      result.add(nodeValues);
      nextToVisit = temp;
    }

    return result;  // Time complexity = O(n). Space complexity = O(m) (m = max nodes at any depth)
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
