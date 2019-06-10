package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
public class MatrixConnectedRegions {
  static class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  public static void flipColor(int x, int y, List<List<Boolean>> image) {
    // TODO - you fill in here.
    Boolean color = image.get(x).get(y);

    Queue<Point> queue = new LinkedList<>();
    Point start = new Point(x, y);

    queue.add(start);

    while (!queue.isEmpty()) {
      Point point = queue.poll();
      image.get(point.x).set(point.y, !color); 

      if ((point.x - 1) >= 0 && image.get(point.x-1).get(point.y) == color) {
        Point up = new Point(point.x-1, point.y);
        queue.add(up);
      }
      
      if ((point.x + 1) <= (image.size() - 1) && 
          image.get(point.x+1).get(point.y) == color) {
        Point down = new Point(point.x+1, point.y);
        queue.add(down);
      } 
      
      if ((point.y - 1) >= 0 && image.get(point.x).get(point.y-1) == color) {
        Point left = new Point(point.x, point.y-1);
        queue.add(left);
      }
      
      if ((point.y + 1) <= (image.get(0).size() - 1) && 
          image.get(point.x).get(point.y+1) == color) {
          Point right = new Point(point.x, point.y+1);
          queue.add(right);
      }
    }
  
    return; // Time = O(mn), space = O(m + n)
  }
  @EpiTest(testDataFile = "painting.tsv")
  public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                     int x, int y,
                                                     List<List<Integer>> image)
      throws Exception {
    List<List<Boolean>> B = new ArrayList<>();
    for (int i = 0; i < image.size(); i++) {
      B.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        B.get(i).add(image.get(i).get(j) == 1);
      }
    }

    executor.run(() -> flipColor(x, y, B));

    image = new ArrayList<>();
    for (int i = 0; i < B.size(); i++) {
      image.add(new ArrayList<>());
      for (int j = 0; j < B.get(i).size(); j++) {
        image.get(i).add(B.get(i).get(j) ? 1 : 0);
      }
    }

    return image;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
