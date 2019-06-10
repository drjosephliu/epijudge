package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {

  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {

    int i = 32;

    while (i > 0) {
      x ^= x >>> i;
      i /= 2;
    }

    return (short) (x & 1);
  }


  // @EpiTest(testDataFile = "parity.tsv")
  // public static short parity(long x) {
  //   // TODO - you fill in here.
  //   // int i = 32;
  //   //
  //   // while (i > 0) {
  //   //   x ^= x >>> i;
  //   //   i /= 2;
  //   // }
  //   //
  //   // return (short) (x & 1);

  //   int numBits = 0;

  //   while (x != 0) {
  //     numBits += (x & 1);
  //     x >>>= 1;
  //   }

  //   return (short) (numBits % 2) ;
  // }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
