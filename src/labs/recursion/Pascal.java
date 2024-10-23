package labs.recursion;

/**
 * Pascals Triangle formula.
 */
public class Pascal {

  /**
   * pascals triangle calculations.
   *
   * @param row the row
   * @param col the col
   * @return the result
   */
  public static int pascal(int row, int col) {
    if (row > 6 || row < 0 || col > 6 || col < 0) { // if row or col is out of bounds
      return 0;
    }
    if (col == 0) { //
      return 1;
    }
    if (col == row) {
      return 1;
    }

    return pascal(row - 1, col) + pascal(row - 1, col - 1); // north + northwest

  }

}
