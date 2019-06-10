package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {

    StringBuilder result = new StringBuilder();
    boolean isNeg = false;

    if (x < 0) {
      isNeg = true;
    }

    do {
      result.append((char) ('0' + Math.abs(x % 10)));
      x /= 10;
    } while (x != 0);

    if (isNeg) {
      result.append('-');
    }

    result.reverse();
    return result.toString();
  }

  public static int stringToInt(String s) {
    int result = 0;

    for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); i++) {
      int digitInt = s.charAt(i) - '0';

      result = result * 10 + digitInt;
    }

    return s.charAt(0) == '-' ? -result : result;
  }

  // Time complexity = O(n). Space complexity = O(1).

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
