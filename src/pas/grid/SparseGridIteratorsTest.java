package pas.grid;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * JUnit tests for the iterator and equals functionality of the SparseGrid class.
 *
 * @author Nathan Sprague
 * @version V2.0, 8/2024
 *
 */
class SparseGridIteratorsTest extends GridIteratorsTest {

  @Override
  public <T> Grid<T> makeGrid(int rows, int cols) {
    return new SparseGrid<T>(rows, cols);
  }

  // -------------------------------------------------------------
  // Add some additional tests that only make sense for SparseGrids
  // -------------------------------------------------------------

  @Test
  @Timeout(value = 1, unit = TimeUnit.SECONDS)
  void testDefaultIteratorIsEfficient() {
    Grid<Integer> grid = new SparseGrid<>(1000000, 1000000);

    grid.put(new Location(999999, 999999), 10);

    for (int item : grid) {
      assertEquals(10, item);
    }
  }

  @Test
  @Timeout(value = 1, unit = TimeUnit.SECONDS)
  void testItemLocationIteratorIsEfficient() {
    Grid<Integer> grid = new SparseGrid<>(1000000, 1000000);

    grid.put(new Location(999999, 999999), 10);

    for (Location loc : grid.itemLocations()) {
      assertEquals(new Location(999999, 999999), loc);
    }
  }

  @Test
  @Timeout(value = 1, unit = TimeUnit.SECONDS)
  void testEqualsIsEfficient() {

    Grid<Integer> grid1 = new SparseGrid<>(1000000, 1000000);
    Grid<Integer> grid2 = new SparseGrid<>(1000000, 1000000);

    grid1.put(new Location(999999, 999999), 10);
    grid2.put(new Location(999999, 999999), 10);

    assertTrue(grid1.equals(grid2));

    grid1.put(new Location(999999, 999999), 11);

    assertFalse(grid1.equals(grid2));
  }

  @Test
  void testEqualsArrayGridAndSparseGrid() {
    Grid<Integer> sparseGrid = new SparseGrid<Integer>(3, 2);
    Grid<Integer> arrayGrid = new ArrayGrid<Integer>(3, 2);

    for (int i = 0; i < sparseGrid.numRows(); i++) {
      for (int j = 0; j < sparseGrid.numCols(); j++) {
        sparseGrid.put(new Location(i, j), i * 10 + j);
        arrayGrid.put(new Location(i, j), i * 10 + j);
      }
    }

    assertTrue(sparseGrid.equals(arrayGrid));
    assertTrue(arrayGrid.equals(sparseGrid));

    arrayGrid.put(new Location(2, 1), -1);
    assertFalse(sparseGrid.equals(arrayGrid));
    assertFalse(arrayGrid.equals(sparseGrid));
  }

}
