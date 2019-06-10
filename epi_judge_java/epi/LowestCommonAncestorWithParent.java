package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.HashSet;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {

    HashSet<BinaryTree<Integer>> n0Ancestors = new HashSet<BinaryTree<Integer>>();
    int n0Depth = 0, n1Depth = 0;

    if (node1 == node0) {
      return node1;
    }
    if (node0 == null || node1 == null) return null;

    do {
      n0Ancestors.add(node0);
      node0 = node0.parent;
      n0Depth++;
    } while (node0 != null);

    do {
      if (n0Ancestors.contains(node1)) {
        return node1;
      }
      node1 = node1.parent;
      n1Depth++;
    } while (node1 != null);

    return null; // Time = O(n1 + n2), space = O(n1)
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
