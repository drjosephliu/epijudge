package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class IsStringInMatrix {
    
    static class Attempt {
        public int x;
        public int y;
        public int offset;

        public Attempt(int x, int y, int offset) {
            this.x = x;
            this.y = y;
            this.offset = offset;
        }
    }

    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
            List<Integer> pattern) {
        // TODO - you fill in here.
        Set<Attempt> previousAttempts = new HashSet<>();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (isPattern(grid, i, j, pattern, 0, previousAttempts)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isPattern(List<List<Integer>> grid, int x, int y,
            List<Integer> pattern, int offset, Set<Attempt> previousAttempts) {
        if (pattern.size() == offset) {
            return true;
        }
        
        if (x < 0 || x >= grid.size() || y < 0 || y >= grid.get(x).size() ||
                previousAttempts.contains(new Attempt(x, y, offset))) {
            return false;
        }

        if (grid.get(x).get(y).equals(pattern.get(offset)) &&
            (isPattern(grid, x-1, y, pattern, offset+1, previousAttempts) ||
             isPattern(grid, x+1, y, pattern, offset+1, previousAttempts) ||
             isPattern(grid, x, y-1, pattern, offset+1, previousAttempts) ||
             isPattern(grid, x, y+1, pattern, offset+1, previousAttempts))) {
            return true;
         }

         previousAttempts.add(new Attempt(x, y, offset));
         return false; // time = O(mn|S|)
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "IsStringInMatrix.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
