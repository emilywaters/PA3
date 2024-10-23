package pas.grid;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the basic functionality of any class implementing the Grid interface. (Iterators
 * and equals tests are not included.)
 *
 * @author Nathan Sprague
 * @version V2.1, 8/2024
 *
 */

public class MyTests {



  protected Grid<Integer> big;
  protected Grid<Integer> small;
  protected Location l00;
  protected Location l01;
  protected Location l02;
  protected Location l10;
  protected Location l11;
  protected Location l12;
  protected Location l20;
  protected Location l21;
  protected Location l22;

  @BeforeEach
  public void setup() {
    big = new ArrayGrid<>(3, 5);
    small = new ArrayGrid<>(3, 2);
    l00 = new Location(0, 0);
    l01 = new Location(0, 1);
    l02 = new Location(0, 2);
    l10 = new Location(1, 0);
    l11 = new Location(1, 1);
    l12 = new Location(1, 2);
    l20 = new Location(2, 0);
    l21 = new Location(2, 1);
    l22 = new Location(2, 2);

  }

  /**
   * Returns true if the two lists contain exactly the same elements. The elements need not be in
   * the same order.
   *
   * @param listA The first list
   * @param listB The second list
   * @return True if listA and listB contain the same elements.
   */
  protected static <T extends Comparable<? super T>> void sameItems(List<T> listA, List<T> listB) {
    Collections.sort(listA);
    Collections.sort(listB);
    assertEquals(listA, listB);

  }

  // ---------------------------------------------------------------
  // DEFAULT ITERATOR TESTS
  // ---------------------------------------------------------------
  @Test
  void testDefaultIteratorForLoopAllCellsFull() {
    int val = 17;
    ArrayList<Integer> correct = new ArrayList<>();

    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 5; col++) {
        big.put(new Location(row, col), val);
        correct.add(val);
        val += 1;
      }
    }

    ArrayList<Integer> iterated = new ArrayList<>();

    for (Integer cur : big) {
      iterated.add(cur);
    }

    sameItems(correct, iterated);
  }

  @Test
  void testDefaultIteratorForLoopSomeCellsFull() {
    int val = 17;
    ArrayList<Integer> correct = new ArrayList<>();

    // Skip some entries...
    for (int row = 2; row < 3; row++) {
      for (int col = 2; col < 5; col++) {
        big.put(new Location(row, col), val);
        correct.add(val);
        val += 1;
      }
    }

    ArrayList<Integer> iterated = new ArrayList<>();

    for (Integer cur : big) {
      iterated.add(cur);
    }

    sameItems(correct, iterated);
  }

  @Test
  void testDefaultIteratorThrowsNoSuchElementException() {
    big.put(l00, 15);
    Iterator<Integer> it = big.iterator();
    it.next();
    assertThrows(NoSuchElementException.class, () -> it.next());
  }

  @Test
  void testDefaultIteratorHasNextAndNext() {
    big.put(l00, 15);
    big.put(l10, 16);

    Iterator<Integer> it = big.iterator();
    assertTrue(it.hasNext());
    assertEquals(Integer.valueOf(15), it.next());
    assertTrue(it.hasNext());
    assertEquals(Integer.valueOf(16), it.next());
  }

  @Test
  void testDefaultIteratorRemove() {
    big.put(new Location(0, 0), 15);
    big.put(new Location(1, 0), 16);
    big.put(new Location(1, 1), 17);
    big.put(new Location(1, 2), 18);
    big.put(new Location(1, 3), 19);

    Iterator<Integer> it = big.iterator();
    while (it.hasNext()) {
      int val = it.next();
      if (val > 15 && val < 18) {
        it.remove();
      }
    }

    ArrayList<Integer> correct = new ArrayList<>();
    correct.add(15);
    correct.add(18);
    correct.add(19);

    ArrayList<Integer> iterated = new ArrayList<>();
    for (Integer cur : big) {
      iterated.add(cur);
    }

    sameItems(correct, iterated);
    assertEquals(3, big.numItems());
  }

  @Test
  void testDefaultIteratorRemoveIllegalStateException() {
    big.put(l00, 15);
    Iterator<Integer> it = big.iterator();
    it.next();
    it.remove();
    assertThrows(IllegalStateException.class, () -> it.remove());
  }

  // ---------------------------------------------------------------
  // ALL LOCATIONS TESTS
  // ---------------------------------------------------------------
  @Test
  void testAllLocationIteratorThrowsNoSuchElementException() {
    Grid<String> grid = new ArrayGrid<>(1, 1);
    Iterator<Location> it = grid.allLocations().iterator();
    it.next();
    assertThrows(NoSuchElementException.class, () -> it.next());
  }

  @Test
  void testAllLocationIteratorDoesNotReturnCollection() {
    assertFalse(big.allLocations() instanceof Collection);
  }

  @Test
  void testAllLocationIteratorRemoveThrowsException() {
    Iterator<Location> it = big.allLocations().iterator();
    it.next();
    assertThrows(UnsupportedOperationException.class, () -> it.remove());
  }

  @Test
  void testAllLocationsEmptyOneByOneGrid() {
    Grid<String> grid = new ArrayGrid<>(1, 1);

    List<Location> correct;
    ArrayList<Location> iterated = new ArrayList<>();

    for (Location loc : grid.allLocations()) {
      iterated.add(loc);
    }
    correct = new ArrayList<Location>(List.of(l00));
    sameItems(correct, iterated);
  }

  @Test
  void testAllLocationsFullOneByOneGrid() {
    Grid<String> grid = new ArrayGrid<>(1, 1);
    grid.put(l00, "A");

    List<Location> correct;
    ArrayList<Location> iterated = new ArrayList<>();

    for (Location loc : grid.allLocations()) {
      iterated.add(loc);
    }
    correct = new ArrayList<Location>(List.of(l00));
    sameItems(correct, iterated);
  }

  @Test
  void testAllLocationsTwoByThreeGrid() {
    Grid<String> grid = new ArrayGrid<>(2, 3);
    grid.put(l00, "A");

    List<Location> correct;
    ArrayList<Location> iterated = new ArrayList<>();

    for (Location loc : grid.allLocations()) {
      iterated.add(loc);
    }
    correct = new ArrayList<Location>(List.of(l00, l01, l02, l10, l11, l12));
    assertEquals(correct, iterated);
  }

  // ---------------------------------------------------------------
  // ITEM LOCATIONS TESTS
  // ---------------------------------------------------------------
  @Test
  void testItemLocationIteratorThrowsNoSuchElementException() {
    big.put(l11, 7);
    Iterator<Location> it = big.itemLocations().iterator();
    it.next();
    assertThrows(NoSuchElementException.class, () -> it.next());
  }

  @Test
  void testItemLocationIteratorThrowsNoSuchElementExceptionEmptyGrid() {
    Iterator<Location> it = big.itemLocations().iterator();
    assertThrows(NoSuchElementException.class, () -> it.next());
  }

  @Test
  void testItemLocationIteratorDoesNotReturnCollection() {
    big.put(new Location(1, 1), 7);
    assertFalse(big.itemLocations() instanceof Collection);
  }

  @Test
  void testItemLocationIteratorRemoveThrowsException() {
    big.put(l11, 7);
    Iterator<Location> it = big.itemLocations().iterator();
    it.next();
    assertThrows(UnsupportedOperationException.class, () -> it.remove());
  }

  @Test
  void testItemLocationIteratorFullOneByOneGrid() {
    Grid<String> grid = new ArrayGrid<>(1, 1);
    grid.put(l00, "B");
    Iterator<Location> it = grid.itemLocations().iterator();
    assertEquals(l00, it.next());
  }

  @Test
  void testItemLocationIteratorFullGrid() {
    Grid<String> grid = new ArrayGrid<>(2, 3);
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 3; col++) {
        grid.put(new Location(row, col), "B");
      }
    }

    List<Location> correct;
    ArrayList<Location> iterated = new ArrayList<>();
    for (Location loc : grid.itemLocations()) {
      iterated.add(loc);
    }
    correct = new ArrayList<Location>(List.of(l00, l01, l02, l10, l11, l12));
    sameItems(correct, iterated);
  }

  @Test
  void testItemLocationIteratorPartiallyFullGrid() {
    Grid<String> grid = new ArrayGrid<>(3, 3);
    grid.put(l00, "A");
    grid.put(l22, "A");
    grid.put(l02, "A");
    grid.put(l20, "A");
    grid.put(l11, "A");

    List<Location> correct;
    ArrayList<Location> iterated = new ArrayList<>();
    for (Location loc : grid.itemLocations()) {
      iterated.add(loc);
    }
    correct = new ArrayList<Location>(List.of(l00, l22, l02, l20, l11));
    sameItems(correct, iterated);
  }


  // -------------
  // TESTS FOR NESTING ITERATORS... THESE REVEAL BUGS THAT INVOLVE SHARING STATE
  // BETWEEN ITERATOR OBJECTS.
  // ---------------

  @Test
  void testDefaultIteratorNested() {

    ArrayList<Integer> correct = new ArrayList<>();
    ArrayList<Integer> iterated = new ArrayList<>();

    int val = 1;
    for (int row = 0; row < big.numRows(); row++) {
      for (int col = 0; col < big.numCols(); col++) {
        big.put(new Location(row, col), val);
        val++;
      }
    }

    for (int row1 = 0; row1 < big.numRows(); row1++) {
      for (int col1 = 0; col1 < big.numCols(); col1++) {
        for (int row2 = 0; row2 < big.numRows(); row2++) {
          for (int col2 = 0; col2 < big.numCols(); col2++) {
            correct.add(big.get(new Location(row2, col2)));
          }
        }
      }
    }

    for (Integer cur1 : big) {
      for (Integer cur2 : big) {
        iterated.add(cur2);
      }
    }
    sameItems(correct, iterated);

  }

  // ---------------------------------------------------------------
  // EQUALS TESTS
  // ---------------------------------------------------------------

  @Test
  void testEqualsChecksDimensions() {
    Grid<String> grid1 = new ArrayGrid<>(3, 4);
    Grid<String> grid2 = new ArrayGrid<>(3, 4);
    Grid<String> grid3 = new ArrayGrid<>(4, 3);

    assertTrue(grid1.equals(grid2));
    assertTrue(grid1.equals(grid1));
    assertFalse(grid1.equals(grid3));
    assertFalse(grid3.equals(grid1));

    Grid<String> grid4 = new ArrayGrid<>(3, 5);
    Grid<String> grid5 = new ArrayGrid<>(2, 4);
    assertFalse(grid1.equals(grid4));
    assertFalse(grid4.equals(grid1));
    assertFalse(grid1.equals(grid5));
    assertFalse(grid5.equals(grid1));

  }

  @Test
  void testEqualsUsesEqualsCorrectly() {
    Grid<Integer> grid1 = new ArrayGrid<>(3, 4);
    Grid<Integer> grid2 = new ArrayGrid<>(3, 4);

    // These should be different objects.
    Integer i1 = 123456;
    Integer i2 = 123455 + 1;

    grid1.put(l12, i1);
    grid2.put(l12, i2);

    assertTrue(grid1.equals(grid2));
    assertTrue(grid2.equals(grid1));

    grid1.put(l11, i1);
    assertFalse(grid1.equals(grid2));
    assertFalse(grid2.equals(grid1));
  }


  protected  Grid<Integer> copyGrid(Grid<Integer> grid) {
    Grid<Integer> newGrid = new ArrayGrid<>(grid.numRows(), grid.numCols());
    for (int i = 0; i < grid.numRows(); i++) {
      for (int j = 0; j < grid.numCols(); j++) {
        newGrid.put(new Location(i, j), grid.get(new Location(i, j)));
      }
    }
    return newGrid;
  }

  @Test
  void testEquals() {
    Grid<Integer> grid1 = new ArrayGrid<>(3, 2);
    Grid<Integer> grid2 = new ArrayGrid<>(3, 2);

    for (int i = 0; i < grid1.numRows(); i++) {
      for (int j = 0; j < grid1.numCols(); j++) {
        grid1.put(new Location(i, j), i * 10 + j);
      }
    }
    grid2 = copyGrid(grid1);
    assertTrue(grid1.equals(grid2));
    assertTrue(grid2.equals(grid1));

    // check that grids differing at each location are correcly
    // considered non-equal
    for (int i = 0; i < grid1.numRows(); i++) {
      for (int j = 0; j < grid1.numCols(); j++) {
        grid2 = copyGrid(grid1);
        grid2.put(new Location(i, j), i * 10 + j + 1);
        assertFalse(grid1.equals(grid2));
        assertFalse(grid2.equals(grid1));
      }
    }
  }

  @Test
  void testEqualsNonGrid() {
    Grid<Integer> grid1 = new ArrayGrid<>(3, 2);
    assertFalse(grid1.equals("HELLO"));
  }



}
