package pas.sorting;

import java.util.Arrays;

/**
 * IntrospectiveSort class.
 */

public class IntrospectiveSort {

  /**
   * Sort the provided items using introspective sort.
   */
  public static <T extends Comparable<T>> void introspectiveSort(T[] items) {
    double depth = 2 * Math.floor(Math.log(items.length) / Math.log(2));
    introspectiveSort(items, depth);
  }

  /**
   * Performs quick sort until there is evidence that the current input
   * is pathological, if so, switch method to use merge sort.
   */
  public static <T extends Comparable<T>> void introspectiveSort(T[] items, double maxDepth) {
    // TODO
    if (maxDepth == 0) {
      MergeSortImproved.mergeSortAdaptive(items);
    } else if (items.length == 0 || items.length == 1) {
      return;
    } else {
      int pivot = QuickSort.partition(items, 0, items.length - 1,  0); //TODO

      introspectiveSort(Arrays.copyOfRange(items, 0, pivot), maxDepth - 1); // left
      introspectiveSort(Arrays.copyOfRange(items, pivot, items.length - 1), maxDepth - 1); // right
      // pivot ← partition(sub-array)
      //introspective_sort(sub-array[0 to pivot - 1], depth-limit - 1)
      //introspective_sort(sub-array[pivot + 1 to end], depth-limit - 1)

    }


  }


}
