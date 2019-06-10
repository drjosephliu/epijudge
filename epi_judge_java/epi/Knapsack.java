package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.List;
public class Knapsack {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }

  @EpiTest(testDataFile = "knapsack.tsv")

  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
    // TODO - you fill in here.
    int n = items.size() + 1;
    int w = capacity + 1;
    int[][] knapsack = new int[n][w];

    for (int i = 0; i < n; i++) {
      knapsack[i][0] = 0;
    }

    for (int j = 0; j < w; j++) {
      knapsack[0][j] = 0;
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < w; j++) {
        Item ithItem = items.get(i-1);
        if (j - ithItem.weight >= 0) {
          knapsack[i][j] = Math.max(
            knapsack[i-1][j],
            knapsack[i-1][j-ithItem.weight] + ithItem.value
            );
        }
        else {
          knapsack[i][j] = knapsack[i-1][j];
        }
        
      }
    }
    return knapsack[n-1][capacity]; // Time = O(nw). Space = O(nw)
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Knapsack.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
