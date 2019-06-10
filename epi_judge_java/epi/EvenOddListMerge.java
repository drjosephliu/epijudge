package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    // TODO - you fill in here.
    if (L == null) {
      return null;
    }

    ListNode<Integer> even = L;
    ListNode<Integer> odd = L.next;
    ListNode<Integer> evenPtr = even;
    ListNode<Integer> oddPtr = odd;

    while (evenPtr.next != null && oddPtr.next != null) {
      evenPtr.next = oddPtr.next;

      if (oddPtr.next != null) {
        oddPtr.next = oddPtr.next.next;
      }
      else {
        oddPtr.next = null;
      }
      evenPtr = evenPtr.next;
      oddPtr = oddPtr.next;
    }
    evenPtr.next = odd;

    return even; // Time = O(n), space = O(1)
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
