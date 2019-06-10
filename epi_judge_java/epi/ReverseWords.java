package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
public class ReverseWords {

    public static void reverseWords(char[] input) {

        reverse(input, 0, input.length);

        int start = 0;
        int end = 0;
        for (end = 0; end < input.length; end++) {
            if (input[end] == ' ') {
                reverse(input, start, end);
                start = end + 1;
            }
        }
        reverse(input, start, end);
        return;
    }

    public static void reverse(char[] input, int startIdx, int endIdx) {
        endIdx--;
        while (startIdx < endIdx) {
            char temp = input[startIdx];
            input[startIdx] = input[endIdx];
            input[endIdx] = temp;
            startIdx++;
            endIdx--;
        }
        return; // Time = O(n), space = O(1)
    }




    // public static void reverseWords(char[] input) {

    //   reverse(input, 0, input.length);
    //   int start = 0, end;
    //   while ((end = find(input, ' ', start)) != -1) {
    //     reverse(input, start, end);
    //     start = end + 1;
    //   }
    //   reverse(input, start, input.length);
    // }

    // private static void reverse(char[] input, int start, int stop) {
    //   if (start >= stop) {
    //     return;
    //   }
    //   int last = stop - 1;
    //   for (int i = start; i <= start + (last - start) / 2; i++) {
    //     char tmp = input[i];
    //     input[i] = input[last - i + start];
    //     input[last - i + start] = tmp;
    //   }
    // }

    // private static int find(char[] input, char c, int start) {
    //   for (int i = start; i < input.length; i++) {
    //     if (input[i] == c) {
    //       return i;
    //     }
    //   }
    //   return -1; // Time = O(n). Space = O(1)
    // }


    @EpiTest(testDataFile = "reverse_words.tsv")
    public static String reverseWordsWrapper(TimedExecutor executor, String s)
        throws Exception {
        char[] sCopy = s.toCharArray();

        executor.run(() -> reverseWords(sCopy));

        return String.valueOf(sCopy);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "ReverseWords.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
