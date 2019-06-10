package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.HashMap;
public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    // TODO - you fill in here.
    HashMap<Character, Integer> charCount = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      if (charCount.containsKey(s.charAt(i))) {
        charCount.put(s.charAt(i), charCount.get(s.charAt(i)) + 1);
      }
      else {
        charCount.put(s.charAt(i), 1);
      }
    }

    int numOdd = 0;
    for (Character c : charCount.keySet()) {
      if (charCount.get(c) % 2 == 1) {
        numOdd++;
        if (numOdd > 1) {
          return false;
        }
      }
    }
    return true; // Time = O(n), space = O(n)
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
