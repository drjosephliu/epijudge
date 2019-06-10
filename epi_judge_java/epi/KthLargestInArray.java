package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Random;
public class KthLargestInArray {

  private static class Compare {
    private static class GreaterThan implements Comparator<Integer> {
      public int compare(Integer a, Integer b) {
        return (a > b) ? -1 : (a.equals(b)) ? 0 : 1;
      }
    }

    public static final GreaterThan GREATER_THAN = new GreaterThan();
  }
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
  // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    return findKth(A, k, Compare.GREATER_THAN);
    
  }

  public static int findKth(List<Integer> A, int k, Comparator<Integer> cmp) {

    Random r = new Random(0);
    int low = 0;
    int high = A.size() - 1;

    while (low <= high) {
      int pivotIndex = r.nextInt(high - low + 1) + low;
      int partitionIndex = partitionAroundPivot(A, low, high, pivotIndex, cmp);

      if (partitionIndex == (k - 1)) {
        return A.get(partitionIndex);
      } 
      else if (partitionIndex < (k - 1)) {
        low = partitionIndex + 1;
      }
      else {
        high = partitionIndex - 1;
      }
    }
    return 0;

  }

  public static int partitionAroundPivot(List<Integer> A, int low, int high, int pivotIndex, Comparator<Integer> cmp) {

    int pivotValue = A.get(pivotIndex);
    int writeIdx = low;

    Collections.swap(A, pivotIndex, high);
    for (int i = writeIdx; i < high; i++) {
      if (cmp.compare(A.get(i), pivotValue) < 0) {
        Collections.swap(A, i, writeIdx++);
      }
    }
    Collections.swap(A, writeIdx, high);
    return writeIdx;
  }



  // public static int findKth(List<Integer> A, int k, Comparator<Integer> cmp) {
  //   int left = 0, right = A.size() - 1;
  //   Random r = new Random(0);
  //   while (left <= right) {

  //     int pivotIdx = r.nextInt(right - left + 1) + left;
  //     int newPivotIdx = partitionAroundPivot(left, right, pivotIdx, A, cmp);
  //     if (newPivotIdx == k - 1) {
  //       return A.get(newPivotIdx);
  //     } else if (newPivotIdx > k - 1) {
  //       right = newPivotIdx - 1;
  //     } else {
  //       left = newPivotIdx + 1;
  //     }
  //   }
  //   return 0;
  // }

  // private static int partitionAroundPivot(int left, int right, int pivotIdx, List<Integer> A, Comparator<Integer> cmp) {
  //   int pivotValue = A.get(pivotIdx);
  //   int newPivotIdx = left;

  //   Collections.swap(A, pivotIdx, right);
  //   for (int i = left; i < right; i++) {
  //     if (cmp.compare(A.get(i), pivotValue) < 0) {
  //       Collections.swap(A, i, newPivotIdx++);
  //     }
  //   }
  //   Collections.swap(A, right, newPivotIdx);
  //   return newPivotIdx;
  // }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
