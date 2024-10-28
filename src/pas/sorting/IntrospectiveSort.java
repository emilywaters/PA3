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
    if (maxDepth == 0) {
      MergeSortImproved.mergeSubsortAdaptive(items, 0, items.length - 1);;
    } else if (items.length == 0 || items.length == 1) {
      return;
    } else {
      int pivot = QuickSort.findPivot(items, 0, items.length - 1);
      int partition = QuickSort.partition(items, 0, items.length - 1,  pivot);

      introspectiveSort(Arrays.copyOfRange(items, 0, partition), maxDepth - 1);
      introspectiveSort(Arrays.copyOfRange(items, partition + 1, items.length), maxDepth - 1);
    }


  }


}
