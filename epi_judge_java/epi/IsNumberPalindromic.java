package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
    @EpiTest(testDataFile = "is_number_palindromic.tsv")
    public static boolean isPalindromeNumber(int x) {
        // TODO - you fill in here.
        if (x < 0) return false;
        else if (x == 0) return true;

        int n = (int) Math.floor(Math.log10(x)) + 1;
        int msdMask = (int) Math.pow(10, n-1);
        for (int i = 0; i < (n/2); i++) {
            if (x / msdMask != x % 10) return false;
            x %= msdMask;
            x /= 10;
            msdMask /= 100;
        }
        return true;
    }


    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "IsNumberPalindromic.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
