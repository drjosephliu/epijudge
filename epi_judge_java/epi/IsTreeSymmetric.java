package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
    @EpiTest(testDataFile = "is_tree_symmetric.tsv")

    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        // TODO - you fill in here.
        if (tree == null) {
            return true;
        }
        return isSymmetricHelper(tree.left, tree.right); // Time = O(n), space = O(h)

    }

    public static boolean isSymmetricHelper(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
        if (left == null && right == null) {
            return true;
        }
        else if ((left == null && right != null) || (right == null && left != null)) {
            return false;
        }
        else if (!left.data.equals(right.data)) {
            return false;
        }
        return isSymmetricHelper(left.right, right.left) && isSymmetricHelper(left.left, right.right);

    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "IsTreeSymmetric.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
