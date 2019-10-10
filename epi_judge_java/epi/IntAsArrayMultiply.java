package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class IntAsArrayMultiply {
    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
        // TODO - you fill in here.
        int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
        num1.set(0, Math.abs(num1.get(0)));
        num2.set(0, Math.abs(num2.get(0)));
        List<Integer> result = new ArrayList<>(Collections.nCopies(num1.size() +
                    num2.size(), 0));

        for (int i = num1.size() - 1; i >= 0; i--) {
            for (int j = num2.size() - 1; j >= 0; j--) {
                int product = num1.get(i) * num2.get(j);
                int newDigit = (result.get(i+j+1) + product) % 10;
                int carry = (result.get(i+j+1) + product) / 10;
                result.set(i + j + 1, newDigit);
                result.set(i + j, result.get(i+j) + carry);
            }
        }
        int first_not_zero = 0;
        while (first_not_zero < result.size() && result.get(first_not_zero) ==
                0) {
            first_not_zero++;
                }

        result = result.subList(first_not_zero, result.size());
        if (result.isEmpty()) {
            return Arrays.asList(0);
        }
        result.set(0, result.get(0) * sign);

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "IntAsArrayMultiply.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
