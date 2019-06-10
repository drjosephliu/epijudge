package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Arrays;
public class LevenshteinDistance {
  @EpiTest(testDataFile = "levenshtein_distance.tsv")



  public static int levenshteinDistance(String A, String B) {
    // TODO - you fill in here.
    int[][] editDistanceMatrix = new int[A.length() + 1][B.length() + 1];

    // Fill first row
    for (int i = 0; i < A.length() + 1; i++) {
      editDistanceMatrix[i][0] = i;
    }

    // Fill first column
    for (int i = 0; i < B.length() + 1; i++) {
      editDistanceMatrix[0][i] = i;
    }

    for (int j = 1; j < B.length() + 1; j++) {
      for (int i = 1; i < A.length() + 1; i++) {
        if (A.charAt(i-1) == B.charAt(j-1)) {
          editDistanceMatrix[i][j] = editDistanceMatrix[i-1][j-1];
        }
        else {
          int insert = editDistanceMatrix[i-1][j];
          int delete = editDistanceMatrix[i][j-1];
          int sub    = editDistanceMatrix[i-1][j-1];
          editDistanceMatrix[i][j] = Math.min(insert, Math.min(delete, sub)) + 1;
        }
      }
    }
    
    return editDistanceMatrix[A.length()][B.length()];
  }
























  // public static int levenshteinDistance(String A, String B) {
  //   // TODO - you fill in here.
  //   // int m = A.length() + 1;
  //   // int n = B.length() + 1;
  //   // int[][] matrix = new int[n][m];
    
  //   // for (int i = 0; i < m; i++) {
  //   //   matrix[0][i] = i;
  //   // }

  //   // for (int j = 0; j < n; j++) {
  //   //   matrix[j][0] = j;
  //   // }

  //   // for (int y = 1; y < n; y++) {
  //   //   for (int x = 1; x < m; x++) {
  //   //     int min;
  //   //     if (A.charAt(x-1) == B.charAt(y-1)) {
  //   //       min = matrix[y-1][x-1];
  //   //     } else {
  //   //       min = Math.min(matrix[y-1][x-1], Math.min(matrix[y-1][x], matrix[y][x-1])) + 1;
  //   //     }
  //   //     matrix[y][x] = min;
  //   //   }
  //   // }
  //   // return matrix[n-1][m-1]; // Time = O(mn), space = O(mn).
    
  //   int[][] matrix = new int[A.length()][B.length()];

  //   for (int[] row : matrix) {
  //     Arrays.fill(row, -1);
  //   }

  //   return computeEditDistance(A, A.length() - 1, B, B.length() - 1, matrix);
    
  // }



  // private static int computeEditDistance(String A, int A_idx, String B, int B_idx, int[][] matrix) {
  //   if (A_idx < 0) {
  //     return B_idx + 1;
  //   } else if (B_idx < 0) {
  //     return A_idx + 1;
  //   }

  //   if (matrix[A_idx][B_idx] == -1) {
  //     if (A.charAt(A_idx) == B.charAt(B_idx)) {
  //       matrix[A_idx][B_idx] = computeEditDistance(A, A_idx - 1, B, B_idx - 1, matrix);
  //     } else {
  //       int subLast = computeEditDistance(A, A_idx - 1, B, B_idx - 1, matrix);
  //       int addLast = computeEditDistance(A, A_idx, B, B_idx - 1, matrix);
  //       int delLast = computeEditDistance(A, A_idx - 1, B, B_idx, matrix);
  //       matrix[A_idx][B_idx] = Math.min(subLast, Math.min(addLast, delLast)) + 1;
  //     }
  //   }

  //   return matrix[A_idx][B_idx];
  // }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
