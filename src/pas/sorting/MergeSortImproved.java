package pas.sorting;

import java.util.Arrays;

/**
 * Improved MergeSort class.
 */

public class MergeSortImproved {

  private static final int MERGE_SORT_THRESHOLD = 10; // TODO find threshold

  /**
   * Merge sort the provided array using an improved merge operation.
   */
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items) {
    T[] temp = (T[]) new Comparable[(items.length + 1) / 2];
    mergeSortHalfSpace(items, temp, 0, items.length - 1);
  }

  /**
   * Recursively split the array then merge back up to sort.
   */
  private static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items, T[] temp, int left,
      int right) {

    if (left >= right) { // List has one record
      return;
    }
    int mid = (left + right) / 2; // get the middle
    mergeSortHalfSpace(items, temp, left, mid); // Mergesort first half
    mergeSortHalfSpace(items, temp, mid + 1, right); // Mergesort second half

    merge(items, temp, left, mid, right);
  }

  /**
   * 1. Copy the values from the first pre-sorted half to a temporary array. 2. Merge the values
   * from the temporary array and the second merge-sorted half into the original array.
   */
  private static <T extends Comparable<T>> void merge(T[] items, T[] temp, int left, int mid,
      int right) {
    // Step 1
    int tempIndex = 0; // add elements to beginning to temp
    for (int i = left; i <= mid; i++) {
      temp[tempIndex++] = items[i]; // Copy subarray to temp
    }

    int i1 = 0; // tracking the index in the temp array
    int i2 = mid + 1; // tracking the index in the right side of the original array
    int curr; // where we are placing the values in the array

    // Step 2
    for (curr = left; curr <= right; curr++) {
      if (i1 == tempIndex) { // If all elements from temp are merged
        items[curr] = items[i2++];
      } else if (i2 > right) { // if the right side is exhausted
        items[curr] = temp[i1++];
      } else if (temp[i1].compareTo(items[i2]) <= 0) { // get the smaller value
        items[curr] = temp[i1++];
      } else {
        items[curr] = items[i2++];
      }
    }
  }

  /**
   * Merge sort the provided array by using an improved merge operation and switching to insertion
   * sort for small sub-arrays.
   */
  public static <T extends Comparable<T>> void mergeSortAdaptive(T[] items) {
    mergeSubsortAdaptive(items, 0, items.length - 1);
  }

  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the fallback method
   * used by introspective sort. Branches: if the array is small, insertion sort if the array is
   * large, merge sort
   */
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void mergeSubsortAdaptive(T[] items, int start, int end) {
    int mid = (start + end) / 2;
    T[] temp = (T[]) new Comparable[(items.length + 1) / 2];
    mergeSubsortAdaptive(items, temp, start, mid, end);
  }

  private static <T extends Comparable<T>> void mergeSubsortAdaptive(T[] items, T[] temp, int start,
      int mid, int end) {
    if (((end - start) + 1) < MERGE_SORT_THRESHOLD) { // insertion sort
      BasicSorts.insertionSubsort(items, start, end);
    } else { // merge sort
      mergeSubsortAdaptive(items, start, mid); // MergeSort left half
      mergeSubsortAdaptive(items, mid + 1, end); // MergeSort right half
      merge(items, temp, start, mid, end); // Merge the two sorted halves.
    }
  }
}
