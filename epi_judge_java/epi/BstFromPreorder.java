package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BstFromPreorder {
    @EpiTest(testDataFile = "bst_from_preorder.tsv")

    public static BstNode<Integer>
        rebuildBSTFromPreorder(List<Integer> preorderSequence) {
            // TODO - you fill in here.
            return helper(preorderSequence, 0, preorderSequence.size());
        }

    private static BstNode<Integer> helper(List<Integer> preorder, int start,
            int end) {

        if (start >= end) return null;

        BstNode<Integer> node = new BstNode<>(preorder.get(start));

        int transitionPoint = start + 1;

        while (transitionPoint < end && preorder.get(transitionPoint) <
                preorder.get(start)) {
            transitionPoint++;
        }

        node.left = helper(preorder, start + 1, transitionPoint);
        node.right = helper(preorder, transitionPoint, end);

        return node;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "BstFromPreorder.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
