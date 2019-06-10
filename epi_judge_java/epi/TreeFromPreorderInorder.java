package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class TreeFromPreorderInorder {

  static int preIdx = 0;
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    
    preIdx = 0;
    BinaryTreeNode<Integer> root = buildTree(preorder, inorder, 0,
        inorder.size() - 1);

    return root;
  }
  
  public static BinaryTreeNode<Integer> buildTree(List<Integer> preorder,
      List<Integer> inorder, int inStart, int inEnd) {
    if (inStart > inEnd) {
      return null;
    }

    BinaryTreeNode<Integer> newNode = new BinaryTreeNode(preorder.get(preIdx++),
        null, null);

    if (inStart == inEnd) {
      return newNode;
    }
    
    int nodeIdx = search(inorder, inStart, inEnd, newNode.data);
    newNode.left = buildTree(preorder, inorder, inStart, nodeIdx - 1);
    newNode.right = buildTree(preorder, inorder, nodeIdx + 1, inEnd);
    return newNode;
  }









  // public static BinaryTreeNode<Integer>
  // binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) 
  // {
  //   // TODO - you fill in here.
  //   preIdx = 0; // reset preIdx between tests

  //   // For faster retrieval
  //   Map<Integer, Integer> nodeToInorderIdx = new HashMap<>(); 
  //   for (int i = 0; i < inorder.size(); i++) {
  //     nodeToInorderIdx.put(inorder.get(i), i);
  //   }
  //   return buildTree(preorder, inorder, 0, inorder.size() - 1,
  //       nodeToInorderIdx);
  // }

  // public static BinaryTreeNode<Integer> buildTree(List<Integer> preorder, 
  //     List<Integer> inorder, int inStart, int inEnd, Map<Integer, Integer>
  //     nodeToInorderIdx) {
  //   if (inStart > inEnd) {
  //     return null;
  //   }

  //   Integer nodeVal = preorder.get(preIdx++);
  //   BinaryTreeNode<Integer> newNode = new BinaryTreeNode<>(nodeVal, null, null);

  //   if (inStart == inEnd) {
  //     return newNode;
  //   }

  //   Integer nodeIdx = nodeToInorderIdx.get(nodeVal);
  //   newNode.left = buildTree(preorder, inorder, inStart, nodeIdx - 1,
  //       nodeToInorderIdx);

  //   newNode.right = buildTree(preorder, inorder, nodeIdx + 1, inEnd,
  //       nodeToInorderIdx);
  //   return newNode; // Time = O(n), space = O(n+h)
  //     }

  public static int search(List<Integer> inorder, int start, int end, Integer
      val) { 
    for (int i = start; i <= end; i++) { 
      if (inorder.get(i).equals(val)) { 
        return i; 
      } 
    } 
    return -1; 
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "TreeFromPreorderInorder.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
