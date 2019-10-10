package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class LargestRectangleUnderSkyline {
  @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")

  public static int calculateLargestRectangle(List<Integer> heights) {
    // TODO - you fill in here.
    int maxArea = 0;

    Deque<Integer> pillarIndices = new LinkedList<>();

    for (int i = 0; i <= heights.size(); i++) {
        if (!pillarIndices.isEmpty() && i < heights.size() &&
                heights.get(i).equals(heights.get(pillarIndices.peekFirst()))) {
            pillarIndices.removeFirst();
            pillarIndices.addFirst(i);
                }

        while (!pillarIndices.isEmpty() && isNewPillarOrReachedEnd(heights, i,
                    pillarIndices.peekFirst())) {
            int height = heights.get(pillarIndices.removeFirst());
            int width = pillarIndices.isEmpty() ? i: i - pillarIndices.peekFirst() - 1;
            maxArea = Math.max(maxArea, height*width);
        }
        pillarIndices.addFirst(i);
    }

    return maxArea;
  }

  private static boolean isNewPillarOrReachedEnd(List<Integer> heights, int
          currIdx, int lastPillarIdx) {
      return currIdx < heights.size() ? heights.get(currIdx) <
          heights.get(lastPillarIdx) : true;
          }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
