package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.HashSet;
public class DoTerminatedListsOverlap {

    public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
        
        if (l0 == null || l1 == null) {
            return null;
        }
        else if (length(l0) > length(l1)) {
            int k = length(l0) - length(l1);
            l0 = advanceListByK(l0, k);
        }
        else if (length(l1) > length(l0)) {
            int k = length(l1) - length(l0);
            l1 = advanceListByK(l1, k);
        }
        
        while (l0 != null && l1 != null) {
            if (l0.equals(l1)) {
                return l0;
            }
            l0 = l0.next;
            l1 = l1.next;
        }
        return null; // Time = O(m+n), space = O(1).
    }

    public static ListNode<Integer> advanceListByK(ListNode<Integer> ln, int k) {
        while (k-- > 0) {
            ln = ln.next;
        }
        return ln;
    }

    public static int length(ListNode<Integer> ln) {
        int len = 0;
        while (ln != null) {
            ln = ln.next;
            len++;
        }
        return len;
    }

















  // public static ListNode<Integer>
  // overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    
  //   if (l0 == null || l1 == null) {
  //     return null;
  //   }

  //   int l0len = length(l0), l1len = length(l1);
  //   if (l0len < l1len) {
  //     l1 = advanceListByK(l1len - l0len, l1);
  //   }
  //   else {
  //     l0 = advanceListByK(l0len - l1len, l0);
  //   }

  //   while (l0 != null && l1 != null && l0 != l1) {
  //     l0 = l0.next;
  //     l1 = l1.next;
  //   }
  //   return l1;
  // }

  // private static ListNode<Integer> advanceListByK(int k, ListNode<Integer> list) {
  //   while (k-- > 0) {
  //     list = list.next;
  //   }
  //   return list;
  // }
  
  // private static int length(ListNode<Integer> list) {
  //   int len = 0;
  //   while (list != null) {
  //     len++;
  //     list = list.next;
  //   }
  //   return len;
  // }

  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
