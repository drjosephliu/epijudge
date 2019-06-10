package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    // TODO - you fill in here.
    String prev = "1";
    String next = "";

    int count = 0;
    int start = 0;
    for (int i = 0; i < (n-1); i++) {
      for (int j = 0; j < prev.length(); j++) {
        if (prev.charAt(start) == prev.charAt(j)) {
          count++;
        }
        else {
          next += Integer.toString(count) + prev.charAt(start);
          count = 1;
          start = j;
        }
      }
      next += Integer.toString(count) + prev.charAt(start);
      prev = next;
      next = "";
      count = 0;
      start = 0;
    }
    return prev; // Time = O(n2^n), space = O(1)
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
