package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {

    ListNode<Integer> head = null;

    if (L1 == null) {
      return L2;
    }

    if (L2 == null) {
      return L1;
    }

    if (L1.data <= L2.data) {
      head = L1;
      L1 = L1.next;
    } else {
      head = L2;
      L2 = L2.next;
    }

    ListNode<Integer> curr = head;

    while (L1 != null && L2 != null) {
      if (L1.data <= L2.data) {
        curr.next = L1;
        L1 = L1.next;
      } else {
        curr.next = L2;
        L2 = L2.next;
      }
      curr = curr.next;
    }

    curr.next = L1 == null ? L2 : L1;

    return head;
  }

  // Time complexity = O(A+B). Space complexity = O(1).

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
