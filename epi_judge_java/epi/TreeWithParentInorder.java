package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;

public class TreeWithParentInorder {
    @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

    public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
        // TODO - you fill in here.

        List<Integer> traversal = new ArrayList<>();
        BinaryTree<Integer> curr = tree;
        BinaryTree<Integer> last = new BinaryTree<Integer>(0);

        while (curr != null) {
            if (curr.left == null && curr.right == null) {
                traversal.add(curr.data);
                last = curr;
                curr = curr.parent;
            }
            else if (curr.left == null && last != curr.right) {
                traversal.add(curr.data);
                curr = curr.right;
                
            }
            else if (curr.right == null && last != curr.left) {
                curr = curr.left;
            }
            else if (last == curr.right) {
                last = curr;
                curr = curr.parent;
            }
            else if (last == curr.left) {
                traversal.add(curr.data);
                if (curr.right != null) {
                    curr = curr.right;
                }
                else {
                    last = curr;
                    curr = curr.parent;
                }
            }
            else {
                curr = curr.left;
            }
        }

        return traversal;

        // List<Integer> traversal = new ArrayList<>();

        // BinaryTree<Integer> curr = tree, prev = null;

        // while (curr != null) {
        //   BinaryTree<Integer> next;
        //   if (curr.parent == prev) {
        //     if (curr.left != null) {
        //       next = curr.left;
        //     }
        //     else {
        //       traversal.add(curr.data);
        //       next = (curr.right != null) ? curr.right : curr.parent;
        //     }
        //   }
        //   else if (curr.left == prev) {
        //     traversal.add(curr.data);
        //     next = (curr.right != null) ? curr.right : curr.parent;
        //   }
        //   else {
        //     next = curr.parent;
        //   }

        //   prev = curr;
        //   curr = next;
        // }

        // return traversal; // Time = O(n), space = O(1)
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "TreeWithParentInorder.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
