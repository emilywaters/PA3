package pas.grid;

/**
 * JUnit tests for the basic functionality of the ArrayGrid class.
 *
 * @author Nathan Sprague
 *
 */
class ArrayGridBasicsTest extends GridBasicsTest {

  @Override
  public <T> Grid<T> makeGrid(int rows, int cols) {

    return new ArrayGrid<T>(rows, cols);
  }



}
