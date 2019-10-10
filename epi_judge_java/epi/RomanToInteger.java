package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class RomanToInteger {
    @EpiTest(testDataFile = "roman_to_integer.tsv")

    public static int romanToInteger(String s) {
        // TODO - you fill in here.
        Map<Character, Integer> rom2dec = new HashMap<>();
        rom2dec.put('I', 1);
        rom2dec.put('V', 5);
        rom2dec.put('X', 10);
        rom2dec.put('L', 50);
        rom2dec.put('C', 100);
        rom2dec.put('D', 500);
        rom2dec.put('M', 1000);
        
        int result = rom2dec.get(s.charAt(s.length() - 1));

        for (int i = s.length() - 2; i >= 0; i--) {
            char curr = s.charAt(i);
            char r = s.charAt(i+1);

            if (rom2dec.get(curr) < rom2dec.get(r)) {
                result -= rom2dec.get(curr);
            } else {
                result += rom2dec.get(curr);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "RomanToInteger.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
