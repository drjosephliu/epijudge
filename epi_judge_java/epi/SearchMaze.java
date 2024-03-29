package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SearchMaze {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Coordinate {
    public int x, y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Coordinate that = (Coordinate)o;
      if (x != that.x || y != that.y) {
        return false;
      }
      return true;
    }
  }

  public enum Color { WHITE, BLACK }

   public static List<Coordinate> searchMaze(List<List<Color>> maze,
                                            Coordinate s, Coordinate e) {
    // TODO - you fill in here.
    List<Coordinate> result = new ArrayList<>();
    result.add(s);
    maze.get(s.x).set(s.y, Color.BLACK);
    if (!searchMazeHelper(maze, s, e, result)){
      result.remove(result.size() - 1);
    } 
    return result;
  }


  public static boolean searchMazeHelper(List<List<Color>> maze, Coordinate s, Coordinate e, List<Coordinate> path) {
    if (s.x == e.x && s.y == e.y) {
      return true;
    }
    
    

    Coordinate lastPosition = path.get(path.size() - 1);
    Coordinate up = new Coordinate(s.x, s.y - 1);
    Coordinate down = new Coordinate(s.x, s.y + 1);
    Coordinate left = new Coordinate(s.x - 1, s.y);
    Coordinate right = new Coordinate(s.x + 1, s.y);

    path.add(up);
    boolean successUp = false;
    if (pathValid(maze, up)) {
      successUp = searchMazeHelper(maze, up, e, path);
    }
    if (!successUp) path.remove(path.size() - 1);

    path.add(right);
    boolean successRight = false; 
    if (pathValid(maze, right)) {

      successRight = searchMazeHelper(maze, right, e, path);
    }
    if (!successRight) path.remove(path.size() - 1);

    path.add(left);
    boolean successLeft = false;
    if (pathValid(maze, left)) {
      successLeft = searchMazeHelper(maze, left, e, path);
    }
    if (!successLeft) path.remove(path.size() - 1);

    
    path.add(down);
    boolean successDown = false;
    if (pathValid(maze, down)) {
      successDown = searchMazeHelper(maze, down, e, path);
    }
    if (!successDown) path.remove(path.size() - 1);

    return successUp || successDown || successLeft || successRight;
  }

  public static boolean pathValid(List<List<Color>> maze, Coordinate p) {
    int mazeX = maze.size();
    int mazeY = maze.get(0).size();
    if (p.x < 0 || p.x >= maze.size() || p.y < 0 || p.y >= maze.get(0).size()) {
      return false;
    }

    if (maze.get(p.x).get(p.y) == Color.BLACK) {
      return false;
    }

    maze.get(p.x).set(p.y, Color.BLACK);
    return true;
  }


  // public static List<Coordinate> searchMaze(List<List<Color>> maze,
  //                                           Coordinate s, Coordinate e) {
  //   // TODO - you fill in here.
  //   List<Coordinate> path = new ArrayList<>();

  //   maze.get(s.x).set(s.y, Color.BLACK);
  //   path.add(s);
  //   if(!searchMazeHelper(maze, s, e, path)) {
  //     path.remove(path.size() - 1);
  //   }
  //   return path;
  // }

  // private static boolean searchMazeHelper(List<List<Color>> maze, Coordinate cur, Coordinate e, List<Coordinate> path) {
  //   if (cur.equals(e)) {
  //     return true;
  //   }

  //   final int[][] SHIFT = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  //   for (int[] s : SHIFT) {
  //     Coordinate next = new Coordinate(cur.x + s[0], cur.y + s[1]);
  //     if (isFeasible(next, maze)) {
  //       maze.get(next.x).set(next.y, Color.BLACK);
  //       path.add(next);
  //       if (searchMazeHelper(maze, next, e, path)) {
  //         return true;
  //       }
  //       path.remove(path.size() - 1);
  //     }
  //   }
  //   return false;
  // }

  private static boolean isFeasible(Coordinate cur, List<List<Color>> maze) {
    return cur.x >= 0 && cur.x < maze.size() && cur.y >= 0 
        && cur.y < maze.get(cur.x).size()
        && maze.get(cur.x).get(cur.y) == Color.WHITE;
  }

  public static boolean pathElementIsFeasible(List<List<Integer>> maze,
                                              Coordinate prev, Coordinate cur) {
    if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y &&
          cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == 0)) {
      return false;
    }
    return cur.x == prev.x + 1 && cur.y == prev.y ||
        cur.x == prev.x - 1 && cur.y == prev.y ||
        cur.x == prev.x && cur.y == prev.y + 1 ||
        cur.x == prev.x && cur.y == prev.y - 1;
  }

  @EpiTest(testDataFile = "search_maze.tsv")
  public static boolean searchMazeWrapper(List<List<Integer>> maze,
                                          Coordinate s, Coordinate e)
      throws TestFailure {
    List<List<Color>> colored = new ArrayList<>();
    for (List<Integer> col : maze) {
      List<Color> tmp = new ArrayList<>();
      for (Integer i : col) {
        tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
      }
      colored.add(tmp);
    }
    List<Coordinate> path = searchMaze(colored, s, e);
    if (path.isEmpty()) {
      return s.equals(e);
    }

    if (!path.get(0).equals(s) || !path.get(path.size() - 1).equals(e)) {
      throw new TestFailure("Path doesn't lay between start and end points");
    }

    for (int i = 1; i < path.size(); i++) {
      if (!pathElementIsFeasible(maze, path.get(i - 1), path.get(i))) {
        throw new TestFailure("Path contains invalid segments");
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchMaze.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
