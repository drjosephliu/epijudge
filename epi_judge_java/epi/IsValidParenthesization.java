package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Deque;
import java.util.ArrayDeque;
public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    // TODO - you fill in here.
    Deque<Character> stack = new ArrayDeque<>();
    
    for (int i = 0; i < s.length(); i++) {
      if (!isClosingParens(s.charAt(i))) {
        stack.push(s.charAt(i));
      }
      else {
        if (stack.isEmpty()) {
          return false;
        }

        char lastParen = stack.pop();
        if (getMatchingParens(s.charAt(i)) != lastParen) {
          return false;
        }
      }
    }
    return stack.isEmpty(); // Time = O(n), space = O(1)
  }

  public static boolean isClosingParens(char paren) {
    return paren == '}' || paren == ')' || paren == ']';
  }

  public static char getMatchingParens(char paren) {
    switch (paren) {
      case '{':
        return '}';
      case '}':
        return '{';
      case '(':
        return ')';
      case ')':
        return '(';
      case '[':
        return ']';
      case ']':
        return '[';
      default: 
        return ' ';
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
