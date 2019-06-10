package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {

    int pivotValue = A.get(pivotIndex).ordinal();
    int l = 0;
    int r = A.size() - 1;

    for (int i = 0; i < A.size(); i++) {
      if (A.get(i).ordinal() < pivotValue) {
        swap(A, i, l++);
      }
    }

    for (int j = r; j >= 0 && A.get(j).ordinal() >= pivotValue; j--) {
      if (A.get(j).ordinal() > pivotValue) {
        swap(A, j, r--);
      }
    }
  }


  /* Brute force */
  // public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
  //   int pivot = A.get(pivotIndex).ordinal();
  //
  //   for (int i = 0; i < A.size(); i++) {
  //     for (int j = i + 1; j < A.size(); j++) {
  //       if (A.get(j).ordinal() < pivot) {
  //         swap(A, i, j);
  //         break;
  //       }
  //     }
  //   }
  //
  //   for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot; i--) {
  //     for (int j = i - 1; j >= 0 && A.get(j).ordinal() >= pivot; j--) {
  //       if (A.get(j).ordinal() > pivot) {
  //         swap(A, i, j);
  //         break;
  //       }
  //     }
  //   }
  //
  // }

  // public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
  //   int pivot = A.get(pivotIndex).ordinal();

  //   int i = 0;
  //   for (int j = i; j < A.size(); j++) {
  //     if (A.get(j).ordinal() < pivot) {
  //       swap(A, i++, j);
  //     }
  //   }

  //   i = A.size() - 1;
  //   for (int j = i; j >= 0 && A.get(j).ordinal() >= pivot; j--) {
  //     if (A.get(j).ordinal() > pivot) {
  //       swap(A, i--, j);
  //     }
  //   }
  // }

  public static void swap(List<Color> list, int i, int j) {
    Color temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }

  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
