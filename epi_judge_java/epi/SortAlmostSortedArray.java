package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
public class SortAlmostSortedArray {

  public static List<Integer> sortApproximatelySortedData(Iterator<Integer>
      sequence, int k) {
    List<Integer> result = new ArrayList<>();
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int i = 0; i < k && sequence.hasNext(); i++) {
      minHeap.add(sequence.next());
    }

    while (sequence.hasNext()) {
      Integer smallest = minHeap.remove();
      result.add(smallest);
      minHeap.add(sequence.next());
    }

    while (!minHeap.isEmpty()) {
      Integer smallest = minHeap.remove();
      result.add(smallest);
    }

    return result; // Time = O(nlogk), space = O(k)
  
  }

  // public static List<Integer>
  //   sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
  //     // TODO - you fill in here.
  //     List<Integer> sequenceList = new ArrayList<>();

  //     while (sequence.hasNext()) {
  //       sequenceList.add(sequence.next());
  //     }

  //     int[] resultArr = new int[sequenceList.size()];

  //     for (int i = 0; i < sequenceList.size(); i++) {
  //       // Look ahead
  //       int newIdx = i;
  //       for (int j = (i + 1); j <= (i + k) && j < sequenceList.size(); j++) {
  //         if (sequenceList.get(j) < sequenceList.get(i)) {
  //           newIdx++;
  //         }
  //       } 

  //       // Look behind
  //       for (int l = (i - 1); l >= (i - k) && l >= 0; l--) {
  //         if (sequenceList.get(l) > sequenceList.get(i)) {
  //           newIdx--;
  //         }
  //       } 

  //       resultArr[newIdx] = sequenceList.get(i);
  //     }

  //     List<Integer> result = new ArrayList<>();
  //     for (int elem : resultArr) {
  //       result.add(elem);
  //     }

  //     return result; // Time = O(kn), space = O(n)
  //   }

  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
        .runFromAnnotations(args, "SortAlmostSortedArray.java",
          new Object() {}.getClass().getEnclosingClass())
        .ordinal());
  }
}
