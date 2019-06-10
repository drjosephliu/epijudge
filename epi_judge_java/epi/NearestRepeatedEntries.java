package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
    
    // Initialise HashMap of strings and ArrayList of index where it appears
    HashMap<String, Integer> wordLoc = new HashMap<>();

    int min = Integer.MAX_VALUE;
    boolean pairsPresent = false;

    // Iterate through paragraph and record location index of word
    for (int i = 0; i < paragraph.size(); i++) {
      String word = paragraph.get(i);

      if (wordLoc.containsKey(word)) {
        pairsPresent = true;
        min = Math.min(min, i - wordLoc.get(word));
      }
      wordLoc.put(word, i);
    }
    
    return pairsPresent ? min : -1;  // Time complexity = O(n). Space complexity = O(n).
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
