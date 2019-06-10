package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
public class RectangleIntersection {
  @EpiUserType(ctorParams = {int.class, int.class, int.class, int.class})
  public static class Rectangle {
    int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Rectangle rectangle = (Rectangle)o;

      if (x != rectangle.x) {
        return false;
      }
      if (y != rectangle.y) {
        return false;
      }
      if (width != rectangle.width) {
        return false;
      }
      return height == rectangle.height;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      result = 31 * result + width;
      result = 31 * result + height;
      return result;
    }

    @Override
    public String toString() {
      return "[" + x + ", " + y + ", " + width + ", " + height + "]";
    }
  }
  @EpiTest(testDataFile = "rectangle_intersection.tsv")
  public static Rectangle intersectRectangle(Rectangle R1, Rectangle R2) {

    int r1xbound = R1.x + R1.width;
    int r1ybound = R1.y + R1.height;

    int r2xbound = R2.x + R2.width;
    int r2ybound = R2.y + R2.height;

    int xIntersection = 0;
    int width = 0;
    int yIntersection = 0;
    int height = 0;

    // Return empty rects if intersection is empty
    if (r2xbound < R1.x || r1xbound < R2.x) {
      return new Rectangle(0, 0, -1, -1);
    } else if (r2ybound < R1.y || r1ybound < R2.y) {
      return new Rectangle(0, 0, -1, -1);
    }

    // Else
    // Find x intersection
    if (R1.x <= R2.x) {
      xIntersection = R2.x;
    } else if (R2.x <= R1.x) {
      xIntersection = R1.x;
    }

    // Find width
    if (r1xbound <= r2xbound) {
      width = r1xbound - xIntersection;
    } else if (r1xbound >= r2xbound) {
      width = r2xbound - xIntersection;
    }

    // Find y intersection
    if (R1.y <= R2.y) {
      yIntersection = R2.y;
    } else if (R2.y <= R1.y) {
      yIntersection = R1.y;
    }

    // Find height
    if (r1ybound <= r2ybound) {
      height = r1ybound - yIntersection;
    } else if (r1ybound >= r2ybound) {
      height = r2ybound - yIntersection;
    }

    return new Rectangle(xIntersection, yIntersection, width, height);


    // new Rectangle(
    //   Math.max(R1.x, R2.x),
    //   Math.max(R1.y, R2.y),
    //   Math.min(R1.x + R1.width, R2.x + R2.width) - Math.max(R1.x, R2.x),
    //   Math.min(R1.y + R1.height, R2.y + R2.height) - Math.max(R1.y, R2.y)
    // )
  }

  // Time complexity = O(1)

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RectangleIntersection.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
