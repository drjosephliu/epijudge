package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;
public class IntervalsUnion {

  public static class Interval implements Comparable<Interval> {
    public Endpoint left = new Endpoint();
    public Endpoint right = new Endpoint();

    private static class Endpoint {
      public boolean isClosed;
      public int val;
    }
    
    public int compareTo(Interval i) {
        if (Integer.compare(left.val, i.left.val) != 0) {
            return left.val - i.left.val;
        }

        if (left.isClosed && !i.left.isClosed) {
            return -1;
        }
        if (!left.isClosed && i.left.isClosed) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null | !(obj instanceof Interval)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Interval that = (Interval)obj;
        return left.val == that.left.val && left.isClosed == that.left.isClosed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left.val, left.isClosed);
    }
  }

  public static List<Interval> unionOfIntervals(List<Interval> intervals) {
    // TODO - you fill in here.
    if (intervals.isEmpty()) {
        return Collections.EMPTY_LIST;
    }
   
    Collections.sort(intervals); 
    Interval curr = intervals.get(0);
    List<Interval> result = new ArrayList<>();
    for (int i = 1; i < intervals.size(); i++) {
        if (intervals.get(i).left.val < curr.right.val
                || (intervals.get(i).left.val == curr.right.val
                && (intervals.get(i).left.isClosed || curr.right.isClosed))) {
            if (intervals.get(i).right.val > curr.right.val
                    || (intervals.get(i).right.val == curr.right.val
                        && intervals.get(i).right.isClosed)) {
                curr.right = intervals.get(i).right;
            }
        } else {
            result.add(curr);
            curr = intervals.get(i);
        }
    }
    result.add(curr);
    return result; // Time = O(nlogn), space = O(1)
  }
  @EpiUserType(
      ctorParams = {int.class, boolean.class, int.class, boolean.class})
  public static class FlatInterval {
    int leftVal;
    boolean leftIsClosed;
    int rightVal;
    boolean rightIsClosed;

    public FlatInterval(int leftVal, boolean leftIsClosed, int rightVal,
                        boolean rightIsClosed) {
      this.leftVal = leftVal;
      this.leftIsClosed = leftIsClosed;
      this.rightVal = rightVal;
      this.rightIsClosed = rightIsClosed;
    }

    public FlatInterval(Interval i) {
      if (i != null) {
        leftVal = i.left.val;
        leftIsClosed = i.left.isClosed;
        rightVal = i.right.val;
        rightIsClosed = i.right.isClosed;
      }
    }

    public Interval toInterval() {
      Interval i = new Interval();
      i.left.val = leftVal;
      i.left.isClosed = leftIsClosed;
      i.right.val = rightVal;
      i.right.isClosed = rightIsClosed;
      return i;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      FlatInterval that = (FlatInterval)o;

      if (leftVal != that.leftVal) {
        return false;
      }
      if (leftIsClosed != that.leftIsClosed) {
        return false;
      }
      if (rightVal != that.rightVal) {
        return false;
      }
      return rightIsClosed == that.rightIsClosed;
    }

    @Override
    public int hashCode() {
      int result = leftVal;
      result = 31 * result + (leftIsClosed ? 1 : 0);
      result = 31 * result + rightVal;
      result = 31 * result + (rightIsClosed ? 1 : 0);
      return result;
    }

    @Override
    public String toString() {
      return "" + (leftIsClosed ? "<" : "(") + leftVal + ", " + rightVal +
          (rightIsClosed ? ">" : ")");
    }
  }

  @EpiTest(testDataFile = "intervals_union.tsv")
  public static List<FlatInterval>
  unionIntervalWrapper(TimedExecutor executor, List<FlatInterval> intervals)
      throws Exception {
    List<Interval> casted = new ArrayList<>(intervals.size());
    for (FlatInterval in : intervals) {
      casted.add(in.toInterval());
    }

    List<Interval> result = executor.run(() -> unionOfIntervals(casted));

    intervals = new ArrayList<>(result.size());
    for (Interval i : result) {
      intervals.add(new FlatInterval(i));
    }
    return intervals;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntervalsUnion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
