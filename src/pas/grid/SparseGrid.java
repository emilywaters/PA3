package pas.grid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SparseGrid Class.
 *
 * @author Emily Waters
 * @version 9/10/2024
 * @param <E> generic.
 *
 */
public class SparseGrid<E> extends AbstractGrid<E> implements Grid<E> {
  private HashMap<Location, E> data;
  private int numRows;
  private int numCols;

  /**
   * SparseGrid Constructor.
   *
   * @param rows the number of rows to set
   * @param cols the number of cols to set
   * @throws IllegalArgumentException if the rows and col are invalid
   */
  public SparseGrid(int rows, int cols) throws IllegalArgumentException {
    if (rows < 1 || cols < 1) {
      throw new IllegalArgumentException();
    }
    this.numRows = rows;
    this.numCols = cols;
    this.data = new HashMap<>();
  }


  @Override
  public void put(Location loc, E item) throws IllegalArgumentException {
    if (item == null) {
      throw new NullPointerException();
    }
    // check if location is in bounds
    int row = loc.getRow();
    int col = loc.getCol();
    if (!(row < numRows() && col < numCols())) {
      throw new IllegalArgumentException();
    }
    data.put(loc, item);
    return;
  }

  @Override
  public E get(Location loc) throws IllegalArgumentException {
    // if location is in the grid
    int row = loc.getRow();
    int col = loc.getCol();
    if (!(row < numRows() && col < numCols())) {
      throw new IllegalArgumentException();
    }
    return data.get(loc);

  }

  @Override
  public E remove(Location loc) throws IllegalArgumentException {
    int row = loc.getRow();
    int col = loc.getCol();
    // if the location is invalid
    if (!(row < numRows() && col < numCols())) {
      throw new IllegalArgumentException();
    }

    if (!data.containsKey(loc)) { // check if spot is empty
      return null;
    }
    E removed = data.get(loc);
    data.remove(loc, removed);
    return removed;

  }

  @Override
  public int numRows() {
    return numRows;
  }

  @Override
  public int numCols() {
    return numCols;
  }

  @Override
  public int numItems() {
    return data.size();
  }



  @Override
  public Iterator<E> iterator() {
    return new SparseGridIterator();
  }



  private class SparseGridIterator implements Iterator<E> {
    private Iterator<Location> it = data.keySet().iterator();
    boolean removeOk = false;
    Location curLocation = null;


    @Override
    public boolean hasNext() {
      return it.hasNext();
    }

    @Override
    public E next() {
      curLocation = (Location) it.next();
      E item = get(curLocation);
      removeOk = true;
      return item;
    }

    @Override
    public void remove() {
      if (!removeOk) {
        throw new IllegalStateException();
      }
      // remove item
      it.remove();
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
    private Iterator<Location> keyIterator = data.keySet().iterator();


    @Override
    public boolean hasNext() {
      return keyIterator.hasNext();
    }

    @Override
    public Location next() throws NoSuchElementException {
      if (!keyIterator.hasNext()) {
        throw new NoSuchElementException();
      }
      Location curLocation = keyIterator.next();
      return curLocation;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

}
