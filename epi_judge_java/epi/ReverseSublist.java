package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    if (L == null) {
      return null;
    }

    if (start == finish) {
      return L;
    }

    if (finish < start) {
      return L;
    }

    ListNode<Integer> dummyHead = new ListNode<>(0, L);
    ListNode<Integer> pointer = dummyHead;
    int s = start;
    while (s-1 > 0) {
      pointer = pointer.next;
      s--;
    }

    ListNode<Integer> startNode = pointer;
    Deque<ListNode<Integer>> stack = new ArrayDeque<>();

    pointer = pointer.next;
    int diff = finish - start;

    while (diff >= 0) {
      stack.addFirst(pointer);
      pointer = pointer.next;
      diff--;
    }

    ListNode<Integer> endNode = pointer;
    while (!stack.isEmpty()) {
      ListNode<Integer> nextNode = stack.poll();
      startNode.next = nextNode;
      startNode = startNode.next;
    }
    
    startNode.next = endNode;

    return dummyHead.next;
  }


  // public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
  //                                                int finish) {

  //   // if (L == null) return null;
  //   // if (start == finish) return L;
  //   //
  //   // ListNode<Integer> dummyHead = new ListNode<>(0, L);
  //   // ListNode<Integer> sublistHead = dummyHead;
  //   //
  //   // int k = 1;
  //   // while (k++ < start) {
  //   //   sublistHead = sublistHead.next;
  //   // }
  //   //
  //   // ListNode<Integer> sublistPtr = sublistHead.next;
  //   // while (start++ < finish) {
  //   //   ListNode<Integer> temp = sublistPtr.next;
  //   //   sublistPtr.next = temp.next;
  //   //   temp.next = sublistHead.next;
  //   //   sublistHead.next = temp;
  //   // }
  //   //
  //   // return dummyHead.next;

  //   if (start == finish) return L;

  //   ListNode<Integer> dummyHead = new ListNode<>(0, L);
  //   ListNode<Integer> sublistHead = dummyHead;

  //   int k = 1;
  //   while (k++ < start) {
  //     sublistHead = sublistHead.next;
  //   }

  //   ListNode<Integer> sublistPtr = sublistHead.next;
  //   ArrayList<ListNode<Integer>> arr = new ArrayList<>();

  //   while (start++ <= finish) {
  //     arr.add(sublistPtr);
  //     sublistPtr = sublistPtr.next;
  //   }

  //   ListNode<Integer> sublistTail = sublistPtr;

  //   sublistPtr = sublistHead;
  //   for (int i = (arr.size() - 1); i >= 0; i--) {
  //     sublistPtr.next = arr.get(i);
  //     sublistPtr = sublistPtr.next;
  //   }
  //   sublistPtr.next = sublistTail;

  //   return dummyHead.next;
  // }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
