package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    // // TODO - you fill in here.

    List<List<Integer>> columnsList = new ArrayList<>();
    List<List<Integer>> squaresList = new ArrayList<>();
    for (int i = 0; i < partialAssignment.size(); i++) {
        columnsList.add(new ArrayList<>());
        squaresList.add(new ArrayList<>());
    } 
    
    for (int i = 0; i < partialAssignment.size(); i++) {

        for (int j = 0; j < partialAssignment.get(i).size(); j++) {
            List<Integer> col = columnsList.get(j);
            col.add(partialAssignment.get(i).get(j));


            if (i >= 0 && i <= 2 && j >= 0 && j <= 2) {
                List<Integer> squareOne = squaresList.get(0);
                squareOne.add(partialAssignment.get(i).get(j));
            }
            else if (i >= 0 && i <= 2 && j >= 3 && j <= 5) {
                List<Integer> squareTwo = squaresList.get(1);
                squareTwo.add(partialAssignment.get(i).get(j));
            }
            else if (i >= 0 && i <= 2 && j >= 6 && j <= 8) {
                List<Integer> squareThree = squaresList.get(2);
                squarethree.add(partialAssignment.get(i).get(j));
            }
            else if (i >= 3 && i <= 5 && j >= 0 && j <= 2) {
                List<Integer> squareFour = squaresList.get(3);
                squareFour.add(partialAssignment.get(i).get(j));
            }
            else if (i >= 3 && i <= 5 && j >= 3 && j <= 5) {
                List<Integer> squareFive = squaresList.get(4);
                squareFive.add(partialAssignment.get(i).get(j));
            }
            else if (i >= 3 && i <= 5 && j >= 6 && j <= 8) {
                List<Integer> squareSix = squaresList.get(5);
                squareSix.add(partialAssignment.get(i).get(j));
            }
            else if (i >= 6 && i <= 8 && j >= 0 && j <= 2) {
                List<Integer> squareSeven = squaresList.get(6);
                squareSeven.add(partialAssignment.get(i).get(j));
            }
            else if (i >= 6 && i <= 8 && j >= 3 && j <= 5) {
                List<Integer> squareEight = squaresList.get(7);
                squareEight.add(partialAssignment.get(i).get(j));
            }
            else if (i >= 6 && i <= 8 && j >= 6 && j <= 8) {
                List<Integer> squareNine = squaresList.get(8);
                squareNine.add(partialAssignment.get(i).get(j));
            }
        }
    }

    for (int i = 0; i < partialAssignment.size(); i++) {
        
      if (!checkValid(partialAssignment.get(i)) || !checkValid(columnsList.get(i) || !checkValid(squaresList.get(i)) {
          return false;
      }
    }

    return true;

    // List<List<Integer>> columnsList = new ArrayList<>();
    // for (int i = 0; i < partialAssignment.size(); i++) {
    //   columnsList.add(new ArrayList<>());
    // }

    // List<List<Integer>> squaresList = new ArrayList<>();
    // for (int i = 0; i < partialAssignment.size(); i++) {
    //   squaresList.add(new ArrayList<>());
    // }

    // for (int i = 0; i < partialAssignment.size(); i++) {
    //   if (checkValid(partialAssignment.get(i)) == false) {
    //     return false;
    //   }
    //   for (int j = 0; j < partialAssignment.get(i).size(); j++) {
    //     List<Integer> col = columnsList.get(j);
    //     col.add(partialAssignment.get(i).get(j));

    //     if (i >= 0 && i <= 2 && j >= 0 && j <= 2) {
    //       List<Integer> square = squaresList.get(0);
    //       square.add(partialAssignment.get(i).get(j));
    //     }
    //     else if (i >= 0 && i <= 2 && j >= 3 && j <= 5) {
    //       List<Integer> square = squaresList.get(1);
    //       square.add(partialAssignment.get(i).get(j));
    //     }
    //     else if (i >= 0 && i <= 2 && j >= 6 && j <= 8) {
    //       List<Integer> square = squaresList.get(2);
    //       square.add(partialAssignment.get(i).get(j));
    //     }
    //     else if (i >= 3 && i <= 5 && j >= 0 && j <= 2) {
    //       List<Integer> square = squaresList.get(3);
    //       square.add(partialAssignment.get(i).get(j));
    //     }
    //     else if (i >= 3 && i <= 5 && j >= 3 && j <= 5) {
    //       List<Integer> square = squaresList.get(4);
    //       square.add(partialAssignment.get(i).get(j));
    //     }
    //     else if (i >= 3 && i <= 5 && j >= 6 && j <= 8) {
    //       List<Integer> square = squaresList.get(5);
    //       square.add(partialAssignment.get(i).get(j));
    //     }
    //     else if (i >= 6 && i <= 8 && j >= 0 && j <= 2) {
    //       List<Integer> square = squaresList.get(6);
    //       square.add(partialAssignment.get(i).get(j));
    //     }
    //     else if (i >= 6 && i <= 8 && j >= 3 && j <= 5) {
    //       List<Integer> square = squaresList.get(7);
    //       square.add(partialAssignment.get(i).get(j));
    //     }
    //     else if (i >= 6 && i <= 8 && j >= 6 && j <= 8) {
    //       List<Integer> square = squaresList.get(8);
    //       square.add(partialAssignment.get(i).get(j));
    //     }

    //   }
    // }

    // for (List<Integer> columnList : columnsList) {
    //   if (checkValid(columnList) == false) {
    //     return false;
    //   }
    // }

    // for (List<Integer> squareList: squaresList) {
    //   if (checkValid(squareList) == false) {
    //     return false;
    //   }
    // }
    // return true; // time = O(n^2), space = O(n^2)
  }

  public static boolean checkValid(List<Integer> listOfNums) {
    HashSet<Integer> seenNumbers = new HashSet<>();

    for (Integer num : listOfNums) {
      if (seenNumbers.contains(num) && !num.equals(0)) {
        return false;
      }
      else  {
        seenNumbers.add(num);
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
