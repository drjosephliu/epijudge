package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Iterator;
import java.util.List;
import java.util.*;
public class OnlineMedian {

    private static final int INIT_CAPACITY = 16;

    public static List<Double> onlineMedian(Iterator<Integer> sequence) {
        // TODO - you fill in here.
        if (!sequence.hasNext()) {
            return new ArrayList<Double>();
        }

        List<Double> medians = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(INIT_CAPACITY);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(INIT_CAPACITY,
                Collections.reverseOrder());

        while (sequence.hasNext()) {
            int next = sequence.next();

            if (maxHeap.isEmpty()) {
                maxHeap.add(next);
            }
            else {
                if (maxHeap.peek() >= next) {
                    maxHeap.add(next);
                }
                else {
                    minHeap.add(next);
                }
            }

            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.remove());
            }
            else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.remove());
            }

            medians.add(minHeap.size() == maxHeap.size() ? 0.5 * (minHeap.peek()
                        + maxHeap.peek()) : maxHeap.peek());
        }

        return medians;

            // List<Double> result = new ArrayList<>();
            // PriorityQueue<Double> minHeap = new PriorityQueue<>();
            // PriorityQueue<Double> maxHeap = new PriorityQueue<>(INIT_CAPACITY,
            //     Collections.reverseOrder());

            // while (sequence.hasNext()) {
            //   int x = sequence.next();
            //   if (minHeap.isEmpty()) {
            //     minHeap.add(x);
            //   }
            //   else {
            //     if (x >= minHeap.peek()) {
            //       minHeap.add(x);
            //     }
            //     else {
            //       maxHeap.add(x);
            //     }
            //   }

            //   if (minHeap.size() > maxHeap.size() + 1) {
            //     maxHeap.add(minHeap.remove());
            //   }
            //   else if (maxHeap.size() > minHeap.size()) {
            //     minHeap.add(maxHeap.remove());
            //   }

            //   result.add(minHeap.size() == maxHeap.size() ? 0.5 * (minHeap.peek() + 
            //         maxHeap.peek()) : minHeap.peek());
            // }
            // return result;
    }
    @EpiTest(testDataFile = "online_median.tsv")
    public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
        return onlineMedian(sequence.iterator());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "OnlineMedian.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
