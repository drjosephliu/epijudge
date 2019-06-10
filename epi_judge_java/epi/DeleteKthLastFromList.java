package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")
    public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
        
        if (L == null) {
            return null;
        }

        ListNode<Integer> kthPtr = new ListNode<>(null, L);
        ListNode<Integer> endPtr = new ListNode<>(null, L);
        L = kthPtr;

        int i = 1;
        while (i++ < k) {
            endPtr = endPtr.next;
        }

        while (endPtr.next != null && endPtr.next.next != null) {
            endPtr = endPtr.next;
            kthPtr = kthPtr.next;
        }
        
        ListNode<Integer> kthNode = kthPtr.next;
        kthPtr.next = kthPtr.next.next;
        kthNode.next = null;

        return L.next;
    }




















    // Assumes L has at least k nodes, deletes the k-th last node in L.
    // public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    //   // TODO - you fill in here

    //   ListNode<Integer> endPointer = new ListNode<>(null, L);
    //   ListNode<Integer> kPointer = new ListNode<>(null, L);
    //   L = kPointer;

    //   int i = 1;

    //   while (i++ < k) {
    //     endPointer = endPointer.next;
    //   }

    //   // Stop before kth last node
    //   while (endPointer.next != null && endPointer.next.next != null) {
    //     endPointer = endPointer.next;
    //     kPointer = kPointer.next;
    //   }

    //   ListNode<Integer> kthNode = kPointer.next;
    //   kPointer.next = kPointer.next.next;
    //   kthNode.next = null;

    //   return L.next; // Time = O(n), space = O(1)
    // }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "DeleteKthLastFromList.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
