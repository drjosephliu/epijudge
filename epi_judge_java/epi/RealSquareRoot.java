package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RealSquareRoot {
    @EpiTest(testDataFile = "real_square_root.tsv")

    public static double squareRoot(double x) {
        // TODO - you fill in here.

        final double EP = 0.000001;
        double left, right;
        if (x < 1.0) {
            left = x;
            right = 1.0;
        }
        else {
            left = 1.0;
            right = x;
        }
        
        while (((left - right) / right) < -EP) {
            double mid = left + (right - left) * 0.5;
            double midSquared = mid * mid;
            double diff = (midSquared - x) / x;

            if (diff > EP) {
                right = mid;
            }
            else if (diff < -EP) {
                left = mid;
            }
            else {
                return mid;
            }
        }

        // while (compare(left, right) == Ordering.SMALLER) {
        //     double mid = left + (right - left) * 0.5;
        //     double midSquared = mid * mid;

        //     if (compare(midSquared, x) == Ordering.LARGER) {
        //         right = mid;
        //     }
        //     else if (compare(midSquared, x) == Ordering.SMALLER) {
        //         left = mid;
        //     }
        //     else {
        //         return mid;
        //     }
        // }
        return left; // Time = O(log (x/s)) (s = tolerance)
    }

    private static enum Ordering { SMALLER, EQUAL, LARGER }

    private static Ordering compare(double a, double b) {
        final double EPSILON = 0.000001;

        double diff = (a - b) / b;
        return diff < -EPSILON ?
            Ordering.SMALLER :
            (diff > EPSILON ? Ordering.LARGER : Ordering.EQUAL);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "RealSquareRoot.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
