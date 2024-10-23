package labs.recursion;

/**
 * Collatz Conjecture is the claim that any integer greater than 1 will be reduced to 1 if you apply
 * the following transformation: halve the number if it’s even, or triple the number and add 1 if
 * it’s odd. Keep repeating the transformation on the resulting number until you arrive at 1.
 */
public class Collatz {

  /**
   * Performs collatz calculations.
   *
   * @param num the number
   * @return the number of steps
   */
  public static int collatzCount(int num) {
    if (num == 1) { // BASE CASE
      return 0;
    }
    if (num % 2 == 0) { // if the number is even
      return collatzCount(num / 2) + 1;
    } else {
      return collatzCount((num * 3) + 1) + 1;
    }

  }

}
