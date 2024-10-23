package pas.grid;

/**
 * JUnit tests for the iterator functionality of the ArrayGrid class.
 *
 * @author Nathan Sprague
 * @version V2.0, 8/2024
 *
 */
class ArrayGridIteratorsTest extends GridIteratorsTest {

  @Override
  public <T> Grid<T> makeGrid(int rows, int cols) {
    return new ArrayGrid<T>(rows, cols);
  }

}
