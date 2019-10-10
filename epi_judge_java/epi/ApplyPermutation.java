package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class ApplyPermutation {
    public static void applyPermutation(List<Integer> perm, List<Integer> A) {
        // TODO - you fill in here.
        for (int i = 0; i < perm.size(); i++) {
            int cycle = i;
            int temp = A.get(cycle);
            while (perm.get(cycle) >= 0) {
                int newIdx = perm.get(cycle);
                int temp2 = A.get(newIdx);
                A.set(newIdx, temp);
                perm.set(cycle, -1);
                cycle = newIdx; 
                temp = temp2;
            }
        }

        return;
    }
    @EpiTest(testDataFile = "apply_permutation.tsv")
    public static List<Integer> applyPermutationWrapper(List<Integer> perm,
            List<Integer> A) {
        applyPermutation(perm, A);
        return A;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "ApplyPermutation.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
