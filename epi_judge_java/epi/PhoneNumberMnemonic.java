package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {
  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")

  public static List<String> phoneMnemonic(String phoneNumber) {
    // TODO - you fill in here.
    List<String> result = new ArrayList<>();
    phoneMnemonicHelper(phoneNumber, 0, "", result);
    return result;
  }

  public static void phoneMnemonicHelper(String phoneNumber, int currIdx, 
      String current, List<String> result) {
    if (current.length() == phoneNumber.length()) {
      result.add(new String(current));
      return;
    }

    char digit = phoneNumber.charAt(currIdx);
    List<Character> chars = getChar(digit);

    for (int j = 0; j < chars.size(); j++) {
      current += chars.get(j);
      phoneMnemonicHelper(phoneNumber, currIdx + 1, current, result);
      current = current.substring(0, current.length() - 1);
    }
    return; // Time = O(4^n(n))
  }

  public static List<Character> getChar(char digit) {
    switch (digit) {
      case '0':
        return new ArrayList<Character>(Arrays.asList('0'));
      case '1':
        return new ArrayList<Character>(Arrays.asList('1'));
      case '2':
        return new ArrayList<Character>(Arrays.asList('A', 'B', 'C'));
      case '3':
        return new ArrayList<Character>(Arrays.asList('D', 'E', 'F'));
      case '4':
        return new ArrayList<Character>(Arrays.asList('G', 'H', 'I'));
      case '5':
        return new ArrayList<Character>(Arrays.asList('J', 'K', 'L'));
      case '6':
        return new ArrayList<Character>(Arrays.asList('M', 'N', 'O'));
      case '7':
        return new ArrayList<Character>(Arrays.asList('P', 'Q', 'R', 'S'));
      case '8':
        return new ArrayList<Character>(Arrays.asList('T', 'U', 'V'));
      case '9':
        return new ArrayList<Character>(Arrays.asList('W', 'X', 'Y', 'Z'));
      default:
        return new ArrayList<Character>();
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
