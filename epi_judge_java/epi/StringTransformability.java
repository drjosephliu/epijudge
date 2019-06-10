package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.HashSet;
public class StringTransformability {

  @EpiTest(testDataFile = "string_transformability.tsv")

  // Uses BFS to find the least steps of transformation.
  public static int transformString(Set<String> D, String s, String t) {
    // TODO - you fill in here.
    // Make adj list
    HashMap<String, ArrayList<String>> adjList = new HashMap<>();
    for (String word : D) {
      adjList.put(word, new ArrayList<String>());
    }

    for (String mapWord : adjList.keySet()) {
      for (String setWord : D) {
        if (!mapWord.equals(setWord) && isTransformable(mapWord, setWord)) {
          ArrayList<String> list = adjList.get(mapWord);
          list.add(setWord);
          adjList.put(mapWord, list);
        }
      }
    }

    return calculateShortestSequence(s, t, adjList, new HashSet<String>());
  }

  public static int calculateShortestSequence(String s, String t, HashMap<String, ArrayList<String>> adjList, HashSet<String> visited) {

    visited.add(s);
    Queue<String> nextToVisit = new LinkedList<>(adjList.get(s));
    int count = 0;
    while (!nextToVisit.isEmpty()) {
      count++;
      Queue<String> tmpQueue = new LinkedList<>();

      while (!nextToVisit.isEmpty()) {
        String next = nextToVisit.poll();

        if (next.equals(t)) {
          return count;
        }

        if (!visited.contains(next)) {
          visited.add(next);
          tmpQueue.addAll(adjList.get(next));
        }
      }
      
      nextToVisit = tmpQueue;
    }
    return -1;

  }

  public static boolean isTransformable(String s, String t) {
    if (s.length() != t.length()) return false;

    int diff = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != t.charAt(i)) {
        diff++;
      }
    }

    return diff == 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
