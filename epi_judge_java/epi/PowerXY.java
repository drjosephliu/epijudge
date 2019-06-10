package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {

@EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    // TODO - you fill in here.
    boolean yIsNeg = false;
    if (y < 0) {
      y = -y;
      yIsNeg = true;
    }
    double result = 1;
    int power = 0;

    while (power < y / 2) {
      result = result * x;
      power++;
    }

    result = result * result;

    if (y % 2 == 1) {
      result = result * x;
    }

    return yIsNeg ? 1/result : result;
  }




  // @EpiTest(testDataFile = "power_x_y.tsv")
  // public static double power(double x, int y) {
  //   // TODO - you fill in here.
  //   // /* BRUTE FORCE */
  //   double result = 1;
  //   int p;
  //   if (y >= 0) {
  //     p = y;
  //   } else {
  //     p = 0 - y;
  //   }

  //   while (p > 0) {
  //     result *= x;
  //     p -= 1;
  //   }

  //   return y >= 0 ? result : 1 / result;

  //   // /* CALCULATE ONLY UP TO N/2 */
  //   // int q = p / 2;
  //   // while (q > 0) {
  //   //   result *= x;
  //   //   q--;
  //   // }
  //   // result *= result;
  //   // // If p is odd multiply again by x before, else return square of result
  //   // if (p % 2 == 1) {
  //   //   result *= x;
  //   // }
  //   //
  //   // return y >= 0? result : 1 / result;

  //   // return y >= 0 ? recursivePower(x, p) : 1 / recursivePower(x, p);


  // }

  // public static double recursivePower(double x, int y) {
  //   /* RECURSIVE */
  //   if (y == 0) {
  //     return 1;
  //   } else if (y == 1) {
  //     return x;
  //   }

  //   if (y % 2 == 0) {
  //     return recursivePower(x * x, y / 2);
  //   } else {
  //     return x * recursivePower(x * x, y / 2);
  //   }
  // }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
