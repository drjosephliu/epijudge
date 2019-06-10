package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class MajorityElement {

  public static String majoritySearch(Iterator<String> stream) {
    String candidate = "";
    int candidateCount = 0;

    while (stream.hasNext()) {

      String next = stream.next();

      if (candidateCount == 0) {
        candidate = next;
        candidateCount++;
      }
      else if (candidateCount > 0 && candidate.equals(next)) {
        candidateCount++;
      }
      else if (candidateCount > 0 && !candidate.equals(next)) {
        candidateCount--;
      }
    }

    return candidate; // Time = O(n), space = O(1)
  }

  // public static String majoritySearch(Iterator<String> stream) {
  //   // TODO - you fill in here.
  //   HashMap<String, Integer> strCount = new HashMap<>();

  //   while (stream.hasNext()) {
  //     String next = stream.next();
  //     if (!strCount.containsKey(next)) {
  //       strCount.put(next, 1);
  //     }
  //     else {
  //       strCount.put(next, strCount.get(next) + 1);
  //     }
  //   }

  //   Map.Entry<String, Integer> maxEntry = null;

  //   for (Map.Entry<String, Integer> entry : strCount.entrySet()) {
  //     if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) >
  //         0) {
  //       maxEntry = entry;
  //         }
  //   }

  //   return maxEntry.getKey(); // Time  = O(n), space = O(n)
  // }
  
  @EpiTest(testDataFile = "majority_element.tsv")
  public static String majoritySearchWrapper(List<String> stream) {
    return majoritySearch(stream.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MajorityElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
