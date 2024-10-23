package pas.grid;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayGrid Class.
 *
 * @author Emily Waters
 * @version 09/05/2024
 * @param <E> type parameter
 */
public class ArrayGrid<E> extends AbstractGrid<E> implements Grid<E> {

  private E[][] data;
  private int numItems;

  /**
   * ArrayGrid Constructor.
   *
   * @param rows the number of rows to set
   * @param cols the number of columns to set
   * @throws IllegalArgumentException throw if row
   */
  public ArrayGrid(int rows, int cols) throws IllegalArgumentException {
    if (rows < 1 || cols < 1) {
      throw new IllegalArgumentException();
    }
    data = (E[][]) new Object[rows][cols];
    numItems = 0;


  }

  @Override
  public void put(Location loc, E item) throws IllegalArgumentException {
    if (item == null) {
      throw new NullPointerException();
    }
    try {
      int row = loc.getRow();
      int col = loc.getCol();
      if (data[row][col] == null) {
        numItems++;
      }
      data[row][col] = item;
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public E get(Location loc) throws IllegalArgumentException {
    // if location is in the grid
    int row = loc.getRow();
    int col = loc.getCol();
    if (!(row < numRows() && col < numCols())) {
      throw new IllegalArgumentException();
    }
    return data[row][col];

  }

  @Override
  public E remove(Location loc) throws IllegalArgumentException {
    try {
      int row = loc.getRow();
      int col = loc.getCol();
      if (data[row][col] == null) { // check if spot is empty
        return null;
      }
      E removed = data[row][col];
      data[row][col] = null;
      numItems--;
      return removed;
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public int numRows() {
    return data.length;
  }

  @Override
  public int numCols() {
    return data[0].length;
  }

  @Override
  public int numItems() {
    return numItems;
  }

  @Override
  public Iterator<E> iterator() {
    return new ArrayGridIterator();
  }

  private class ArrayGridIterator implements Iterator<E> {
    int row;
    int col;
    boolean removeOk;
    int currentRow;
    int currentCol;

    private ArrayGridIterator() {
      row = 0;
      col = 0;

    }

    @Override
    public boolean hasNext() {
      // while we are in the array, iterate through all the items, skipping over null elements
      while ((row < data.length) && (col < data[0].length) && data[row][col] == null) {
        col++;
        if (data[row].length <= col) {
          col = 0;
          row++;
        }
      }
      return (row < data.length) && (col < data[0].length);
    }

    @Override
    public E next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      // save this current item along with its row and col
      E item = data[row][col];
      currentRow = row;
      currentCol = col;

      col++;
      if (data[row].length <= col) {
        col = 0;
        row++;
      }
      // allow us to remove when we have called next()
      removeOk = true;
      return item;

    }

    @Override
    public void remove() {
      if (!removeOk) {
        throw new IllegalStateException();
      }
      // set this item to null
      data[currentRow][currentCol] = null;
      numItems--;
      // change removeOk status
      removeOk = false;

    }
  }

  @Override
  public Iterable<Location> itemLocations() {
    return new ItemLocationsIterable();
  }

  private class ItemLocationsIterable implements Iterable<Location> {

    @Override
    public Iterator<Location> iterator() {
      return new ItemLocationIterator();

    }
  }

  private class ItemLocationIterator implements Iterator<Location> {
    int row;
    int col;

    private ItemLocationIterator() {
      row = 0;
      col = 0;

    }

    @Override
    public boolean hasNext() {
      // if we are only iterating through items
      // while we are in the array, iterate through all the items, skipping over null elements
      while ((row < data.length) && (col < data[0].length) && data[row][col] == null) {
        col++;
        if (data[row].length <= col) {
          col = 0;
          row++;
        }
      }
      return row < data.length && col < data[row].length;
    }

    @Override
    public Location next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Location loc = new Location(row, col);

      col++;
      if (data[row].length <= col) {
        col = 0;
        row++;
      }
      return loc;

    }
  }
}
