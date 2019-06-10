package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
public class SpiralOrderingSegments {
  @EpiTest(testDataFile = "spiral_ordering_segments.tsv")
   public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    
    List<Integer> spiralOrder = new ArrayList<>();
      
    int n = squareMatrix.size();

    for (int i = 0; i < Math.ceil(0.5 * squareMatrix.size(); i++) {
      List<Integer> levelOrdering = spiralOrderingHelper(squareMatrix, i);
      spiralOrder.addAll(levelOrdering);
    }
    
   









    // List<Integer> spiralOrdering = new ArrayList<>();
    // for (int offset = 0; offset < Math.ceil(0.5 * squareMatrix.size()); offset++) {
    //   spiralOrderingHelper(squareMatrix, offset, spiralOrdering);
    // }
    // return spiralOrdering;
  }

  private static List<Integer> spiralOrderingHelper(List<List<Integer>> squareMatrix, int offset) {

       List<Integer> spiralOrdering = new ArrayList<>();

      if (offset == (squareMatrix.size() - offset - 1)) {
          spiralOrdering.add(squareMatrix.get(offset).get(offset));
          return spiralOrdering;    
      }
      
      for (int i = offset; i < (squareMatrix.size() - offset - 1); i++) {
          spiralOrdering.add(squareMatrix.get(offset).get(i);
      }

      for (int j = offset; j < (squareMatrix.size() - offset - 1); j++) {
          spiralOrdering.add(squareMatrix.get(j).get(squareMatrix.size() - offset - 1));
      }

      for (int k = (squareMatrix.size() - offset - 1); k > offset; k--) {
          spiralOrdering.add(squareMatrix.get(squareMatrix.size() - offset - 1).get(k));
      }

      for (int l = (squareMatrix.size() - offset - 1); l > offset; l--) {
          spiralOrdering.add(squareMatrix.get(l).get(offset)); 
      }

      return spiralOrdering;

  }

  // private static void spiralOrderingHelper(List<List<Integer>> squareMatrix, int offset, List<Integer> spiralOrdering) {

  //   if (offset == (squareMatrix.size() - offset - 1)) {
  //     spiralOrdering.add(squareMatrix.get(offset).get(offset));
  //     return;
  //   }

  //   for (int y = offset; y < (squareMatrix.size() - 1 - offset); y++) {
  //     spiralOrdering.add(squareMatrix.get(offset).get(y));
  //   }

  //   for (int x = offset; x < (squareMatrix.size() - 1 - offset); x++) {
  //     spiralOrdering.add(squareMatrix.get(x).get(squareMatrix.size() - 1 - offset));
  //   }

  //   for (int y = (squareMatrix.size() - 1 - offset); y > offset; y--) {
  //     spiralOrdering.add(squareMatrix.get(squareMatrix.size() - 1 - offset).get(y));
  //   }

  //   for (int x = (squareMatrix.size() - 1 - offset); x > offset; x--) {
  //     spiralOrdering.add(squareMatrix.get(x).get(offset));
  //   }
  // }

  // @EpiTest(testDataFile = "spiral_ordering_segments.tsv")
  // public static List<Integer>
  // matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
  //   // TODO - you fill in here.
  //   int n = squareMatrix.size();
  //   int sides = n * 2;
  //   List<Integer> result = new ArrayList<>();

  //   int start = 0;
  //   int[] startCoord = {0, 0};
  //   boolean horizontal = true;
  //   boolean reverse = false;

  //   if (n == 1) {
  //     result.add(squareMatrix.get(0).get(0));
  //     return result;
  //   }

  //   while (start <= (n - 1)) {
  
  //     for (int j = 0; j < (n - 1 - start); j++){
  //       if (horizontal && !reverse) {
  //         result.add(squareMatrix.get(startCoord[0]).get(startCoord[1] + j));
  //       }
  //       else if (!horizontal && !reverse) {
  //         result.add(squareMatrix.get(startCoord[0] + j).get(startCoord[1]));       
  //       }
  //       else if (horizontal && reverse) {
  //         result.add(squareMatrix.get(startCoord[0]).get(startCoord[1] - j));
  //       }
  //       else {
  //         result.add(squareMatrix.get(startCoord[0] - j).get(startCoord[1]));
  //       }
  //     }

  //     if (horizontal && !reverse) {
  //       horizontal = false;
  //       reverse = false;
  //       startCoord = new int[] {start, n - 1};
  //     }
  //     else if (!horizontal && !reverse) {
  //       horizontal = true;
  //       reverse = true;
  //       startCoord = new int[] {n - 1, n - 1};
  //     }
  //     else if (horizontal && reverse) {
  //       horizontal = false;
  //       reverse = true;
  //       startCoord = new int[] {n - 1, start};
  //     }
  //     else {
  //       horizontal = true;
  //       reverse = false;
  //       start++;
  //       startCoord = new int[] {start, start};
  //       n = n - 1;

  //       if ((n - 1 - start) == 0) {
  //         result.add(squareMatrix.get(start).get(start));
  //         break;
  //       }
  //     }
  //   }

  //   return result; 
  // }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
