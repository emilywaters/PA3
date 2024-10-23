/**
 * CS 240 Counting Exercises. Replace each TODO with appropriate code so that the predicted and
 * actual steps performed are the same for each method.
 */

package labs.counting;

import java.util.Arrays;
import java.util.Random;

/**
 * Counting class.
 *
 * @author Emily Waters
 * @version 08/30/2024
 */
public class Counting {

  private static Random gen = new Random();
  private static int binSearchCount = 0;

  // ------------------------------------
  // maxTriples
  // ------------------------------------

  public static int maxTriple(int[] nums) {
    int count = 0; // INSTRUMENT FOR EVALUATION
    int biggest = -1;

    for (int i = 0; i < nums.length - 2; i++) {
      int sum = 0;
      for (int j = 0; j < 3; j++) {
        count++; // COUNT ARRAY ACCESSES
        sum += nums[i + j];
      }
      if (sum > biggest) {
        biggest = sum;
      }
    }
    System.out.printf("maxTriple actual steps:    T(%d) = %d\n\n", nums.length, count);
    return biggest;
  }

  public static int predictMaxTripleSteps(int n) {

    return ((n - 1) * 3) - 3; // TODO
  }


  // ------------------------------------
  // countDoubles
  // ------------------------------------

  public static int binarySearch(int[] A, int K) {
    int low = 0;
    int high = A.length - 1;
    while (low <= high) { // Stop when low and high meet
      binSearchCount++;
      int mid = (low + high) / 2; // Check middle of subarray
      if (A[mid] < K) {
        low = mid + 1; // In right half
      } else if (A[mid] > K) {
        high = mid - 1; // In left half
      } else {
        return mid; // Found it
      }
    }
    return A.length; // Search value not in A
  }

  public static int countDoubles(int[] nums) {
    int count = 0;
    binSearchCount = 0; // FOR INSTRUMENTATION

    for (int i = 0; i < nums.length; i++) {
      if (binarySearch(nums, nums[i] * 2) != nums.length) {
        count++;
      }
    }
    System.out.printf("countDouble actual steps:    T(%d) = %d\n\n", nums.length, binSearchCount);
    return count;
  }

  public static int predictCountDoublesSteps(int n) {
    return (int) Math.floor((Math.log(n) / Math.log(2)) + 1) * n; // TODO
  }


  // ------------------------------------
  // tweakNumbers
  // ------------------------------------

  public static void tweakNumbers(int[] nums) {
    int count = 0; // INSTRUMENT FOR EVALUATION

    // Add three everywhere
    for (int i = 0; i < nums.length; i++) {
      count++; // COUNT ARRAY ACCESSES
      nums[i] += 3;
    }

    // Special entries get four more!
    for (int i = 1; i < nums.length; i *= 2) {
      count++; // COUNT ARRAY ACCESSES
      nums[i] += 4;
    }

    System.out.printf("tweakNumbers actual steps:    T(%d) = %d\n\n", nums.length, count);
  }

  public static int predictTweakNumbersSteps(int n) {
    return (int) (n + Math.floor(Math.log(n) / Math.log(2))); // TODO
  }


  // ------------------------------------
  // Quadratic
  // ------------------------------------

  /**
   * This method must increment the counter exactly 20n^2 + 7 times. The method must not include any
   * arithmetic calculations.
   */
  public static void doQuadraticWork(int n, Counter counter) {
    for (int i = 0; i < 20; i++) { // 20 *
      for (int k = 0; k < n; k++) { // n^1
        for (int j = 0; j < n; j++) { // n^2
          counter.increment(); // 20 * n^2
        }
      }
    }
    for (int s = 0; s < 7; s++) { // + 7
      counter.increment();
    }

    System.out.printf("doQuadraticWork actual steps:    T(%d) = %d\n\n", n, counter.getCount());
  }

  /**
   * Returns 20n^2 + 7.
   */
  public static int predictDoQuadraticWorkSteps(int n) {
    return (int) (20 * (Math.pow(n, 2)) + 7);
  }


  // ------------------------------------
  // LogCubic
  // ------------------------------------

  /**
   * This method must increment the counter exactly n^3 log_2 n + 10n times. The method must not
   * include any arithmetic calculations.
   */
  public static void doLogCubicWork(int n, Counter counter) {

    for (int a = 0; a < n; a++) { // n^1
      for (int b = 0; b < n; b++) { // n^2
        for (int c = 0; c < n; c++) { // n^3
          for (int d = 1; d < n; d *= 2) { // log2(n)
            counter.increment();
          }
        }
      }
    }
    // + 10n section
    for (int a = 0; a < n; a++) {
      for (int b = 0; b < 10; b++) {
        counter.increment();
      }

    }


    System.out.printf("doLogCubicWork actual steps:    T(%d) = %d\n\n", n, counter.getCount());
  }

  /**
   * Returns n^3 log_2 n + 10n.
   */
  public static int predictDoLogCubicWorkSteps(int n) {
    return (int) (Math.pow(n, 3) * (Math.log(n) / Math.log(2)) + 10 * n);
  }


  // ------------------------------------
  // HalfQuadratic
  // ------------------------------------

  /**
   * This method must increment the counter exactly n (n+1) / 2 + n times. The method must not
   * include any arithmetic calculations.
   */
  public static void doHalfQuadraticWork(int n, Counter counter) {

    for (int i = 0; i < n; i++) {
      counter.increment();
      for (int j = 0; j < i; j++) {
        counter.increment();
      }
      counter.increment();
    }
    System.out.printf("doHalfQuadtraticWork actual steps:    T(%d) = %d\n\n", n,
        counter.getCount());
  }

  /**
   * Returns n (n+1) / 2 + n.
   */
  public static int predictDoHalfQuadraticSteps(int n) {
    return n * (n + 1) / 2 + n;
  }

  // TESTING CODE BELOW THIS POINT

  public static int[] randomArray(int n) {
    int[] result = new int[n];

    for (int i = 0; i < n; i++) {
      result[i] = gen.nextInt(Integer.MAX_VALUE / 4, Integer.MAX_VALUE / 2);
    }
    return result;
  }

  public static void main(String[] args) {

    // ------------------------------------
    // maxTriples
    // ------------------------------------
    int[] array = randomArray(100);
    System.out.printf("maxTriple predicted steps: T(%d) = %d\n", array.length,
        predictMaxTripleSteps(array.length));
    maxTriple(array);

    // ------------------------------------
    // countDoubles
    // ------------------------------------
    array = randomArray(16);
    Arrays.sort(array);
    System.out.printf("countDouble predicted steps: T(%d) = %d\n", array.length,
        predictCountDoublesSteps(array.length));
    countDoubles(array);

    // ------------------------------------
    // tweakNumbers
    // ------------------------------------
    array = randomArray(128);
    Arrays.sort(array);
    System.out.printf("tweakNumbers predicted steps: T(%d) = %d\n", array.length,
        predictTweakNumbersSteps(array.length));
    tweakNumbers(array);

    // ------------------------------------
    // doQuadraticWork
    // ------------------------------------
    int n = 100;
    System.out.printf("doQuadraticWork predicted steps: T(%d) = %d\n", n,
        predictDoQuadraticWorkSteps(n));
    doQuadraticWork(n, new Counter());

    // ------------------------------------
    // doLogCubicWork
    // ------------------------------------
    n = 100;
    System.out.printf("doLogCubicWork predicted steps: T(%d) = %d\n", n,
        predictDoLogCubicWorkSteps(n));
    doLogCubicWork(n, new Counter());

    // ------------------------------------
    // doHalfQuadraticWork
    // ------------------------------------
    n = 100;
    System.out.printf("doHalfQuadraticWork predicted steps: T(%d) = %d\n", n,
        predictDoHalfQuadraticSteps(n));
    doHalfQuadraticWork(n, new Counter());
  }



}
