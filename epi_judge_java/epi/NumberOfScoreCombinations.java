package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class NumberOfScoreCombinations {
  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int
  numCombinationsForFinalScore(int finalScore,
                               List<Integer> individualPlayScores) {
    // TODO - you fill in here.
    int[][] matrix = new int[individualPlayScores.size()][finalScore+1];

    for (int i = 0; i < finalScore+1; i++) {
      if (i % individualPlayScores.get(0) == 0) {
        matrix[0][i] = 1;
      }
    }

    for (int i = 1; i < individualPlayScores.size(); i++) {
      for (int j = 0; j < finalScore+1; j++) {
        int sum = 0;
        int k = j;
        while (k >= 0) {
          sum = sum + matrix[i-1][k];
          k = k - individualPlayScores.get(i);
        }
        matrix[i][j] = sum;
      }
    }
    return matrix[individualPlayScores.size() - 1][finalScore];
  }

























  // public static int
  // numCombinationsForFinalScore(int finalScore,
  //                              List<Integer> individualPlayScores) {
  //   // TODO - you fill in here.
  //   int[][] matrix = new int[individualPlayScores.size()][finalScore + 1];

  //   for (int i = 0; i < (finalScore + 1); i++) {
  //     int point = individualPlayScores.get(0);
  //     if (i % point == 0) {
  //       matrix[0][i] = 1;
  //     }
  //   }

  //   for (int i = 1; i < individualPlayScores.size(); i++) {
  //     for (int j = 0; j < (finalScore + 1); j++) {
  //       int point = individualPlayScores.get(i);
  //       int sum = 0;
  //       int k = j;
  //       while (k >= 0) {
  //         sum += matrix[i-1][k];
  //         k -= point;
  //       }

  //       matrix[i][j] = sum;
  //     }
  //   }

  //   return matrix[individualPlayScores.size() - 1][finalScore];
  // }

  // /* BRUTE FORCE */
  // private static int finalScoreHelper(int finalScore, List<Integer> individualPlayScores, int currSum, int i) {
  //   if (currSum == finalScore) {
  //     return 1;
  //   } else if (currSum > finalScore) {
  //     return 0;
  //   }

  //   int combos = 0;
  //   for (int j = i; j < individualPlayScores.size(); j++) {
  //     combos += finalScoreHelper(finalScore, individualPlayScores, currSum + individualPlayScores.get(j), j);
  //   }
  //   // System.out.println("currSum: " + currSum + ", combos: " + combos);
  //   return combos;
    
    
  // }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
