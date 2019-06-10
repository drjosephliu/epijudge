package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.HashMap;
public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    
    // /* BRUTE FORCE */
    // // Convert both texts to char arrays
    // char[] letterCharArray = letterText.toCharArray();
    // char[] magazineCharArray = magazineText.toCharArray();

    // // Initialise hashmap of magazine char frequency
    // HashMap<Character, Integer> magazineCharFreq = new HashMap<>();

    // // Traverse through magazine array and count freq of char
    // for (int i = 0; i < magazineCharArray.length; i++) {
    //   char letter = magazineCharArray[i];

    //   if (!magazineCharFreq.containsKey(letter)) {
    //     magazineCharFreq.put(letter, 1);
    //   } else {
    //     magazineCharFreq.put(letter, magazineCharFreq.get(letter)  +1);
    //   }
    // }

    // // Initialise hashmap of letter char frequency
    // HashMap<Character, Integer> letterCharFreq = new HashMap<>();

    // // Traverse through letter array and count freq of char
    // for (int i = 0; i < letterCharArray.length; i++) {
    //   char letter = letterCharArray[i];

    //   if (!letterCharFreq.containsKey(letter)) {
    //     letterCharFreq.put(letter, 1);
    //   } else {
    //     letterCharFreq.put(letter, letterCharFreq.get(letter) + 1);
    //   }

    //   // If freq in letter text exceeds freq in magazine text then return early
    //   if (!magazineCharFreq.containsKey(letter)) {
    //     return false;
    //   }
    //   else if (letterCharFreq.get(letter) > magazineCharFreq.get(letter)) {
    //     return false;
    //   }
      
    // }

    // return true; // Time complexity = O(n) where n is size of magazine text. Space complexity = O(k)
    
    // Initialise char arrays
    char[] letterCharArray = letterText.toCharArray();
    char[] magazineCharArray = magazineText.toCharArray();

    // Initialise map of char freq in letter
    HashMap<Character, Integer> letterCharFreq = new HashMap<>();

    // Traverse through letter text and count freq of each letter
    for (int i = 0; i < letterCharArray.length; i++) {
      char character = letterCharArray[i];

      if (!letterCharFreq.containsKey(character)) {
        letterCharFreq.put(character, 1);
      } else {
        letterCharFreq.put(character, letterCharFreq.get(character) + 1);
      }
    }

    // Traverse through magazine text and reduce freq in letter freq map
    for (int i = 0; i < magazineCharArray.length; i++) {
      char character = magazineCharArray[i];

      if (letterCharFreq.containsKey(character)) {
        letterCharFreq.put(character, letterCharFreq.get(character) - 1);

        if (letterCharFreq.get(character) == 0) {
          letterCharFreq.remove(character);
        }
      }
    }

    return letterCharFreq.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
