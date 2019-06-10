package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {

    // Collections.reverse(A);
    // int carry = 1;
    // for (int i = 0; i < A.size(); i++) {
    //   int digit = A.get(i);
    //   A.set(i, ((digit + carry) % 10));
    //   carry = (digit + carry) / 10;
    //   if (carry == 0) {
    //     break;
    //   }
    // }
    //
    //
    // if (carry == 1) {
    //   A.add(1);
    // }
    //
    // Collections.reverse(A);

    int carry = 1;
    for (int i = (A.size() - 1); i >= 0; i--) {
      int digit = A.get(i);
      A.set(i, ((digit + carry) % 10));
      carry = (digit + carry) / 10;
      if (carry == 0) break;
    }

    if (carry == 1) {
      A.add(0, 1);
    }

    return A;
  }

  // Time complexity = O(n)

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
