package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.*;
public class SmallestSubarrayCoveringAllValues {

  public static class Subarray {
    // Represent subarray by starting and ending indices, inclusive.
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray
  findSmallestSequentiallyCoveringSubset(List<String> paragraph,
                                         List<String> keywords) {
    // TODO - you fill in here.
    HashMap<String, List<Integer>> keywordToIndices = new HashMap<>();
    for (String keyword : keywords) {
      keywordToIndices.put(keyword, new ArrayList<Integer>());
    }

    for (int i = 0; i < paragraph.size(); i++) {
      String word = paragraph.get(i);

      if (keywordToIndices.keySet().contains(word)) {
        List<Integer> indices = keywordToIndices.get(word);
        indices.add(i);
        keywordToIndices.put(word, indices); 
      }
    }

    int minDiff = Integer.MAX_VALUE;
    int minStart = 0, minEnd = paragraph.size() - 1;

    for (int i = 0; i < keywordToIndices.get(keywords.get(0)).size(); i++) {
      int startIdx = keywordToIndices.get(keywords.get(0)).get(i);
      int endIdx = startIdx;
      boolean validSequence = true;

      for (int j = 1; j < keywords.size(); j++) {
        List<Integer> indices = keywordToIndices.get(keywords.get(j));
        int k = 0;
      
        while (k < indices.size() && endIdx > indices.get(k)) {
          k++;
        }
        
        if (k >= indices.size()) {
          validSequence = false;
          break;
        }

        endIdx = indices.get(k);
      }
      
      if ((endIdx - startIdx) < minDiff && validSequence) {
        minDiff = endIdx - startIdx;
        minStart = startIdx;
        minEnd = endIdx;
      }
    }

    return new Subarray(minStart, minEnd);
  }
  @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
  public static int findSmallestSequentiallyCoveringSubsetWrapper(
      TimedExecutor executor, List<String> paragraph, List<String> keywords)
      throws Exception {
    Subarray result = executor.run(
        () -> findSmallestSequentiallyCoveringSubset(paragraph, keywords));

    int kwIdx = 0;
    if (result.start < 0) {
      throw new TestFailure("Subarray start index is negative");
    }
    int paraIdx = result.start;

    while (kwIdx < keywords.size()) {
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Not all keywords are in the generated subarray");
      }
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Subarray end index exceeds array size");
      }
      if (paragraph.get(paraIdx).equals(keywords.get(kwIdx))) {
        kwIdx++;
      }
      paraIdx++;
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringAllValues.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
