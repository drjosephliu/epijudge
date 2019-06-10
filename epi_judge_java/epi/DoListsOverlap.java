package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.HashSet;
import java.util.Set;
public class DoListsOverlap {

    public static ListNode<Integer> overlappingLists(ListNode<Integer> l0, ListNode<Integer> l1) {
        
        if (l0 == null || l1 == null) {
            return null;
        }
        
        HashSet<ListNode<Integer>> seenL0Nodes = new HashSet<>();
        HashSet<ListNode<Integer>> seenL1Nodes = new HashSet<>();

        while (!seenL0Nodes.contains(l0) && l0 != null) {
            if (!seenL0Nodes.contains(l0)) {
                seenL0Nodes.add(l0);
            }
            l0 = l0.next;
        }

        while (!seenL1Nodes.contains(l1) && l1 != null) {
            if (!seenL1Nodes.contains(l1)) {
                seenL1Nodes.add(l1);
            }
            if (seenL0Nodes.contains(l1)) {
                return l1;
            }
            l1 = l1.next;
        }
        return null;
        
    }


    // public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
    //                                                  ListNode<Integer> l1) {
    //   // TODO - you fill in here.
    //   HashSet<ListNode<Integer>> seenL0Nodes = new HashSet<>();
    //   HashSet<ListNode<Integer>> seenL1Nodes = new HashSet<>();

    //   ListNode<Integer> root0 = l0;
    //   ListNode<Integer> root1 = l1;

    //   while (root0 != null) {
    //     if (!seenL0Nodes.contains(root0)) {
    //       seenL0Nodes.add(root0);
    //     }
    //     else {
    //       break;
    //     }
    //     root0 = root0.next;
    //   }

    //   while (root1 != null) {
    //     if (!seenL1Nodes.contains(root1)) {
    //       seenL1Nodes.add(root1);
    //     }
    //     else {
    //       break;
    //     }
    //     root1 = root1.next;
    //   }

    //   // Both lists have no cycle
    //   if (root0 == null && root1 == null) {
    //     return overlappingNoCycleLists(l0, l1);
    //   }

    //   // One list has cycle, the other doesn't;
    //   // therefore not overlapping
    //   if ((root0 == null && root1 != null) || (root0 != null && root1 == null)) {
    //     return null;
    //   }

    //   // Both lists have cycles
    //   ListNode<Integer> temp = root1;
    //   do {
    //     temp = temp.next;
    //   } while (temp != root0 && temp != root1);

    //   // Both lists don't end in same cycle
    //   if (temp != root0) {
    //     return null;
    //   }

    //   int l0stemLen = distance(l0, root0), l1stemLen = distance(l1, root1);
    //   if (l0stemLen > l1stemLen) {
    //     l0 = advanceListByK(l0stemLen - l1stemLen, l0);
    //   }
    //   else {
    //     l1 = advanceListByK(l1stemLen - l0stemLen, l1);
    //   }

    //   while (l0 != root0 && l1 != root1 && l0 != l1) {
    //     l0 = l0.next;
    //     l1 = l1.next;
    //   }

    //   return l0 == l1 ? l0 : root0;
    // }

    // public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
    //                                                  ListNode<Integer> l1) {
    //   // TODO - you fill in here.

    //   if (l0 == null || l1 == null) {
    //     return null;
    //   }

    //   HashSet<ListNode<Integer>> seenNodes = new HashSet<>();
    //   HashSet<ListNode<Integer>> seenNodes2 = new HashSet<>();

    //   while (l0 != null && !seenNodes.contains(l0)) {
    //     seenNodes.add(l0);
    //     l0 = l0.next;
    //   }

    //   while (l1 != null && !seenNodes.contains(l1) && !seenNodes2.contains(l1)) {
    //     seenNodes2.add(l1);
    //     l1 = l1.next;
    //   }

    //   if (seenNodes.contains(l1)) {
    //     return l1;
    //   }
    //   return null;
    // }

    private static int distance(ListNode<Integer> a, ListNode<Integer> b) {
        int dist = 0;
        while (a != b) {
            a = a.next;
            dist++;
        }
        return dist;
    }

    private static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
        if (l0 == null || l1 == null) {
            return null;
        }

        int l0len = length(l0), l1len = length(l1);
        if (l0len < l1len) {
            l1 = advanceListByK(l1len - l0len, l1);
        }
        else {
            l0 = advanceListByK(l0len - l1len, l0);
        }

        while (l0 != null && l1 != null && l0 != l1) {
            l0 = l0.next;
            l1 = l1.next;
        }

        return l1;
    }


    private static ListNode<Integer> advanceListByK(int k, ListNode<Integer> L) {
        while (k-- > 0) {
            L = L.next;
        }
        return L;
    }

    private static int length(ListNode<Integer> L) {
        int len = 0;
        while (L != null) {
            len++;
            L = L.next;
        }
        return len;
    }


    @EpiTest(testDataFile = "do_lists_overlap.tsv")
    public static void
    overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
            ListNode<Integer> l1, ListNode<Integer> common,
            int cycle0, int cycle1) throws Exception {
        if (common != null) {
            if (l0 == null) {
                l0 = common;
            } else {
                ListNode<Integer> it = l0;
                while (it.next != null) {
                    it = it.next;
                }
                it.next = common;
            }

            if (l1 == null) {
                l1 = common;
            } else {
                ListNode<Integer> it = l1;
                while (it.next != null) {
                    it = it.next;
                }
                it.next = common;
            }
        }

        if (cycle0 != -1 && l0 != null) {
            ListNode<Integer> last = l0;
            while (last.next != null) {
                last = last.next;
            }
            ListNode<Integer> it = l0;
            while (cycle0-- > 0) {
                if (it == null) {
                    throw new RuntimeException("Invalid input data");
                }
                it = it.next;
            }
            last.next = it;
        }

        if (cycle1 != -1 && l1 != null) {
            ListNode<Integer> last = l1;
            while (last.next != null) {
                last = last.next;
            }
            ListNode<Integer> it = l1;
            while (cycle1-- > 0) {
                if (it == null) {
                    throw new RuntimeException("Invalid input data");
                }
                it = it.next;
            }
            last.next = it;
        }

        Set<Integer> commonNodes = new HashSet<>();
        ListNode<Integer> it = common;
        while (it != null && !commonNodes.contains(it.data)) {
            commonNodes.add(it.data);
            it = it.next;
        }

        final ListNode<Integer> finalL0 = l0;
        final ListNode<Integer> finalL1 = l1;
        ListNode<Integer> result =
            executor.run(() -> overlappingLists(finalL0, finalL1));

        if (!((commonNodes.isEmpty() && result == null) ||
                    (result != null && commonNodes.contains(result.data)))) {
            throw new TestFailure("Invalid result");
                    }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "DoListsOverlap.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
