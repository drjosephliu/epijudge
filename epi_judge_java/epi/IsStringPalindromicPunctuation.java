package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.lang.StringBuilder;
public class IsStringPalindromicPunctuation {

    @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")
    public static boolean isPalindrome(String s) {
    
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while  (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;






















        // // TODO - you fill in here.
        // int left = 0;
        // int right = s.length() - 1;

        // while (left < right) {

        //   while (!Character.isLetterOrDigit(s.charAt(left))) {
        //     left++;
        //   }

        //   while (!Character.isLetterOrDigit(s.charAt(right))) {
        //     right--;
        //   }

        //   if (Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
        //     return false;
        //   }
        // }
        // return true;
    }


    // @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

    // public static boolean isPalindrome(String s) {
    //   // TODO - you fill in here.
    //   if (s.isEmpty()) {
    //     return true;
    //   }

    //   StringBuilder str = new StringBuilder();

    //   for (int i = 0; i < s.length(); i++) {
    //     char c = s.charAt(i);
    //     if (Character.isDigit(c) || Character.isLetter(c)) {
    //       str.append(Character.toLowerCase(c));
    //     }
    //   }

    //   if (str.toString().compareTo(str.reverse().toString()) == 0) {
    //     return true;
    //   }

    //   return false; // Time = O(n), Space = O(n)
    // }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
