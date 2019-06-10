package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {

    int decimal = 0;
    boolean isNeg = false;
    int j = 0;

    if (numAsString.charAt(0) == '-') {
      isNeg = true;
      j = 1;
    }

    for (int i = (numAsString.length() - 1), exp = 0; i >= j; i--, exp++) {
      int power = (int) Math.pow(b1, exp);
      int digit = numAsString.charAt(i);

      if (digit <= 57) {
        digit -= '0';
      } else {
        digit -= '7';
      }
      decimal += digit * power;
    }

    // System.out.println("str: " + numAsString + ", base: " + b1 + ", decimal: " + decimal + ", base2: " + b2);

    StringBuilder result = new StringBuilder();

    do {
      int digit = Math.abs(decimal % b2);

      if (digit <= 9) {
        digit += '0';
      } else {
        digit += '7';
      }
      result.append((char) digit);

      decimal /= b2;
    } while (decimal != 0);

    if (isNeg) {
      result.append('-');
    }

    result.reverse();

    return result.toString();
  }

  // Time complexity = O(n + log(base b2)). Space complexity = O(1).

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
