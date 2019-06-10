package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsMatrix {
  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")

  public static int numberOfWays(int n, int m) {
    // TODO - you fill in here.
    int[][] matrix = new int[n][m];

    for (int i = 0; i < n; i++) {
      matrix[i][m-1] = 1;
    }

    for (int j = 0; j < m; j++) {
      matrix[n-1][j] = 1;
    }


    for (int i = (n-2); i >= 0; i--) {
      for (int j = (m-2); j >= 0; j--) {
        matrix[i][j] = matrix[i][j+1] + matrix[i+1][j];
      }
    }

    return matrix[0][0]; // time = O(mn), space = O(mn)
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
