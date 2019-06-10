package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxTrappedWater {
  @EpiTest(testDataFile = "max_trapped_water.tsv")

  public static int getMaxTrappedWater(List<Integer> heights) {
    // TODO - you fill in here.
    int left = 0, right = heights.size() - 1;
    int maxVolume = 0;

    while (left < right) {
      int volume = 0;
      int height = heights.get(left) < heights.get(right) ? heights.get(left) :
        heights.get(right);

      volume = (right - left) * height;
      maxVolume = Math.max(volume, maxVolume);
      
      if (heights.get(left) < heights.get(right)) {
        left++;
      }
      else if (heights.get(right) < heights.get(left)) {
        right--;
      }
      else {
        left++;
        right--;
      }
      
    }
    return maxVolume; // Time = O(n), space = O(1)
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxTrappedWater.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
