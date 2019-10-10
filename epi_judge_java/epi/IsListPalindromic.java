package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class IsListPalindromic {
    @EpiTest(testDataFile = "is_list_palindromic.tsv")

    public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
        // TODO - you fill in here.
        if (L == null || L.size() <= 1) return true;

        ListNode<Integer> mid = L;
        int len = 0;

        while (mid != null) {
            mid = mid.next;
            len++;
        }
        
        int midIdx = len / 2;
        len = 0;
        mid = L;
        while (len < midIdx) {
            mid = mid.next;
            len++;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        while (mid != null) {
            stack.push(mid.data);
            mid = mid.next;
        }

        ListNode<Integer> head = L;
        len = 0;
        while (len < midIdx) {
            int endNodeVal = stack.pop();
            if (head.data != endNodeVal) {
                return false;
            }
            head = head.next;
            len++;
        }
        

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "IsListPalindromic.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
