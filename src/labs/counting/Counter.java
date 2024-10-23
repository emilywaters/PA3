package labs.counting;

/**
 * Simple integer wrapper that can only be incremented by one.
 */
public class Counter {
  private int count;

  /**
   * Initialize to zero.
   */
  public Counter() {
    count = 0;
  }

  /**
   * Increase by one.
   */
  public void increment() {
    count++;
  }

  /**
   * Return the count.
  */
  public int getCount() {
    return count;
  }



}
