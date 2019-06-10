package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.*;
public class SearchForMissingElement {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class DuplicateAndMissing {
    public Integer duplicate;
    public Integer missing;

    public DuplicateAndMissing(Integer duplicate, Integer missing) {
      this.duplicate = duplicate;
      this.missing = missing;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      DuplicateAndMissing that = (DuplicateAndMissing)o;

      if (!duplicate.equals(that.duplicate)) {
        return false;
      }
      return missing.equals(that.missing);
    }

    @Override
    public int hashCode() {
      int result = duplicate.hashCode();
      result = 31 * result + missing.hashCode();
      return result;
    }

    @Override
    public String toString() {
      return "duplicate: " + duplicate + ", missing: " + missing;
    }
  }

  @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")

  public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {

    int combinedXor = 0;
    for (int i = 0; i < A.size(); i++) {
      combinedXor ^= i ^ A.get(i);
    }
    
    int differBit = combinedXor & (~(combinedXor - 1));

    int missOrDup = 0;
    for (int i = 0; i < A.size(); i++) {
      if ((i & differBit) != 0) {
        missOrDup ^= i;
      }

      if ((A.get(i) & differBit) != 0) {
        missOrDup ^= A.get(i);
      }
    }

    int duplicate = 0;
    for (Integer num : A) {
      if (num == missOrDup) {
        return new DuplicateAndMissing(missOrDup, missOrDup ^ combinedXor);
      }
    }

    return new DuplicateAndMissing(missOrDup ^ combinedXor, missOrDup);
    // Time = O(n), space = O(1)
  }

  // public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
  //   // TODO - you fill in here.
  //   // Find duplicate first
  //   int n = A.size(), sum = 0;
  //   Integer duplicate = 0;
  //   HashSet<Integer> seenNumbers = new HashSet<>();
  //   for (Integer num : A) {
  //     if (!seenNumbers.contains(num)) {
  //       seenNumbers.add(num);
  //       sum += num;
  //     }
  //     else {
  //       duplicate = num;
  //     }
  //   }
    
  //   // Get missing number by subtracting sum of list from (n(n-1))/2
  //   int missing = (n * (n - 1)) / 2 - sum;

  //   return new DuplicateAndMissing(duplicate, missing); 
  //   // Time = O(n), space = O(n)
  // }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMissingElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
