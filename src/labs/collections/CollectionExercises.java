package labs.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Exercises for practicing with Java collection types.
 *
 * @author Emily Waters
 * @version 8/23/2024
 *
 */
public class CollectionExercises {

  /**
   * Removes all values from the provided list that are smaller than the indicated integer. The
   * remaining elements retain their original ordering.
   *
   * @param list - the list of integers
   * @param minVal the minimum value to retain
   */
  public static void removeSmallInts(List<Integer> list, int minVal) {
    // Your solution must use an iterator. Conveniently, the list iterator
    // has a remove method.
    Iterator<Integer> it = list.iterator();

    while (it.hasNext()) {
      Integer i = it.next();
      if (i < minVal) {
        it.remove();
      }
    }
  }

  /**
   * Returns true if the provided collection contains any duplicate elements.
   *
   * @param ints - a collection of integers
   * @return true if ints contains duplicates, false otherwise
   */
  public static boolean containsDuplicates(Collection<Integer> ints) {
    // Your solution must not use any loops.
    HashSet<Integer> hs = new HashSet<>();
    hs.addAll(ints);

    return hs.size() != ints.size();
  }

  /**
   * Returns an ArrayList containing all elements that appear in either of the two collection
   * arguments. There will be no duplicate values in the resulting ArrayList. The values in the
   * returned ArrayList may be in any order.
   *
   * <p>For example, if the two arguments contain {2, 1, 2, 3} and {3, 4, 4, 5}, the returned
   * ArrayList will contain {1, 2, 3, 4, 5}. The original collections will not be modified.
   *
   * @param ints1 - the first collection
   * @param ints2 - the second collection
   * @return An ArrayList containing the integers that appear in either collection.
   */
  public static ArrayList<Integer> inEither(Collection<Integer> ints1,
          Collection<Integer> ints2) {
    // This must be done with no loops.
    HashSet<Integer> i1 = new HashSet<>(ints1);
    HashSet<Integer> i2 = new HashSet<>(ints2);
    i1.addAll(i2);
    ArrayList<Integer> arrayList = new ArrayList<>(i1);

    return arrayList;
  }

  /**
   * Returns an ArrayList containing all elements that appear in both of the two collection
   * arguments. There will be no duplicate values in the resulting ArrayList. The values in the
   * returned ArrayList may be in any order. For example, if the two arguments contain {2, 1, 2,
   * 3} and {3, 4, 4, 5}, the returned ArrayList will contain {3}. The original collections will
   * not be modified.
   *
   * @param ints1 - the first collection
   * @param ints2 - the second collection
   * @return An ArrayList containing the integers that appear in both collections.
   */
  public static ArrayList<Integer> inBoth(Collection<Integer> ints1, Collection<Integer> ints2) {
    // This must be done with no loops.
    HashSet<Integer> i1 = new HashSet<>(ints1);
    HashSet<Integer> i2 = new HashSet<>(ints2);

    i1.retainAll(i2);
    ArrayList<Integer> both = new ArrayList<>(i1);

    return both;
  }

  /**
   * Returns the String that appears most frequently in the provided list. For example, if the
   * input list contains the elements {"Bob", "Alice", "Bob"}, this method will return "Bob". If
   * there are ties, any of the most frequently occurring elements may be returned.
   *
   * @param list - a list of Strings
   * @return the most frequently occurring String
   */
  public static String mostFrequent(List<String> list) {
    // You should solve this problem in two stages: First iterate through
    // the list to count occurrences of each String. Then iterate through
    // your counts to find the largest. You'll need a collection that allows
    // you to store a mapping from Strings to counts.
    // Remember: no nested for-loops are allowed.
    HashMap<String, Integer> frequencies = new HashMap<>();

    for (String word : list) {
      if (!frequencies.containsKey(word)) {
        frequencies.put(word, 1);
      }
      frequencies.put(word, frequencies.get(word) + 1);
    }
    int mostFrequent = 0;
    String mostFreString = "";
    for (String key : frequencies.keySet()) {
      if (frequencies.get(key) > mostFrequent) {
        mostFrequent = frequencies.get(key);
        mostFreString = key;

      }
    }
    return mostFreString;
  }

}
