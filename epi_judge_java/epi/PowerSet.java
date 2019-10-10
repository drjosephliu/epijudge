package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.*;
import java.util.function.BiPredicate;
public class PowerSet {
    @EpiTest(testDataFile = "power_set.tsv")

    public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
        // TODO - you fill in here.
        return helper(inputSet, inputSet.size() - 1);
    }

    private static List<List<Integer>> helper(List<Integer> inputSet, int idx) {
        List<List<Integer>> allSubsets;

        if (idx < 0) {
            allSubsets = new ArrayList<>();
            allSubsets.add(new ArrayList<>());
        }
        else {
            allSubsets = helper(inputSet, idx - 1);
            int elem = inputSet.get(idx);

            List<List<Integer>> subsets = new ArrayList<>();

            for (List<Integer> subset : allSubsets) {
                List<Integer> clone = new ArrayList<>();
                clone.addAll(subset);
                clone.add(elem);
                subsets.add(clone);
            }
            allSubsets.addAll(subsets);
        }
        return allSubsets;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
    (expected, result) -> {
        if (result == null) {
            return false;
        }
        for (List<Integer> l : expected) {
            Collections.sort(l);
        }
        expected.sort(new LexicographicalListComparator<>());
        for (List<Integer> l : result) {
            Collections.sort(l);
        }
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    };

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "PowerSet.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
