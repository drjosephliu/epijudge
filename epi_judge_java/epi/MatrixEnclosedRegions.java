package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MatrixEnclosedRegions {

  public static void fillSurroundedRegions(List<List<Character>> board) {
    // TODO - you fill in here.

    for (int j = 0; j < board.get(0).size(); j++) {
        if (board.get(0).get(j) == 'W') {
            dfs(board,  0, j);
        }
        if (board.get(board.size() - 1).get(j) == 'W') {
            dfs(board, board.size() - 1, j);
        }
    }

    for (int i = 0; i < board.size(); i++) {
        if (board.get(i).get(0) == 'W') {
            dfs(board, i, 0);
        }

        if (board.get(i).get(board.get(0).size() - 1) == 'W') {
            dfs(board, i, board.get(0).size() - 1);
        }
    }

    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board.get(0).size(); j++) {
            if (board.get(i).get(j) == 'V') {
                board.get(i).set(j, 'W');
            }
            else if (board.get(i).get(j) == 'W') {
                board.get(i).set(j, 'B');
            }
        }
    }

    
    return;
  }

  private static void dfs(List<List<Character>> board, int i, int j) {
      if (board.get(i).get(j) != 'W') return;

      board.get(i).set(j, 'V');
      int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

      for (int[] move : moves) {
        int neighbourX = i + move[0];
        int neighbourY = j + move[1];

        if (neighbourX >= 0 && neighbourX < board.size() && 
            neighbourY >= 0 && neighbourY < board.get(0).size()) {
           dfs(board, neighbourX, neighbourY); 
        }
      }
    return;
  }

  @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
  public static List<List<Character>>
  fillSurroundedRegionsWrapper(List<List<Character>> board) {
    fillSurroundedRegions(board);
    return board;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixEnclosedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
