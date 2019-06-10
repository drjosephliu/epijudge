package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Comparator;

public class SortedArraysMerge {
  

  private static class ArrayEntry {
    public Integer value;
    public Integer arrayId;

    public ArrayEntry(Integer value, Integer arrayId) {
      this.value = value;
      this.arrayId = arrayId;
    }
  }
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    
    // PriorityQueue<Integer> pq = new PriorityQueue<>();

    // for (int i = 0; i < sortedArrays.size(); i++) {
    //   for (int j = 0; j < sortedArrays.get(i).size(); j++) {
    //     pq.add(sortedArrays.get(i).get(j));
    //   }
    // }

    // List<Integer> result = new ArrayList<>();
    // Iterator itr = pq.iterator();

    // while (itr.hasNext()) {
    //   result.add(pq.poll());
    // }

    // return result; // Time complexity = O(nlogn). Space complexity = O(n).

    // Create list of iterators for each subarray
    List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());

    for (List<Integer> array : sortedArrays) {
      iters.add(array.iterator());
    }

    PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(
      ((int) sortedArrays.size()), new Comparator<ArrayEntry>() {
        
        @Override
        public int compare(ArrayEntry o1, ArrayEntry o2) {
          return Integer.compare(o1.value, o2.value);
        }
      });

    for (int i = 0; i < iters.size(); i++) {
      if (iters.get(i).hasNext()) {
        minHeap.add(new ArrayEntry(iters.get(i).next(), i));
      }
    }

    List<Integer> result = new ArrayList<>();

    while (!minHeap.isEmpty()) {
      ArrayEntry min = minHeap.poll();

      result.add(min.value);

      if (iters.get(min.arrayId).hasNext()) {
        minHeap.add(new ArrayEntry(iters.get(min.arrayId).next(), min.arrayId));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
