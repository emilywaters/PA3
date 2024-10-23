package pas.grid;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * AbstractGrid.
 *
 * @param <E> Generic Type
 */
public abstract class AbstractGrid<E> implements Grid<E> {

  @Override
  public String toString() {
    String retString = "";
    int maxWidth = 0;
    // searching for the longest item in the array
    // use the regular iterator
    for (Location loc : itemLocations()) {
      String item = get(loc).toString();
      maxWidth = Math.max(maxWidth, item.length());
    }


    // create a string representation for each row
    for (int row = 0; row < numRows(); row++) {
      retString += stringHelper(maxWidth + 2, row);

    }

    // border on the bottom
    String dashedBorder = "+" + "-".repeat(maxWidth + 2);
    retString += dashedBorder.repeat(numCols()) + "+\n";

    return retString;

  }

  /**
   * Helper method for toString creates the top border and elements in a box.
   *
   * @param width the width for each box, dependent on the largest item
   * @return the string
   */
  public String stringHelper(int width, int row) {
    String retString = "";
    // Border for the top of each row
    String dashedBorder = "+" + "-".repeat(width);
    retString += dashedBorder.repeat(numCols()) + "+\n";

    for (int col = 0; col < numCols(); col++) {
      if (get(new Location(row, col)) == null) { // empty box
        retString += "|" + " ".repeat(width);
      } else { // box with item
        String item = get(new Location(row, col)).toString(); // changed
        retString += "| " + item + " ".repeat(width - item.length() - 1);
      }
    }
    // place line at the end of each row
    return retString + "|\n";
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Grid) {
      Grid that = (Grid) other;
      // if the grids are the same size
      if (that.numCols() == this.numCols() && that.numRows() == this.numRows()) {
        // if the grids are the same size but have nothing in them return true
        if (that.numItems() == 0 && this.numItems() == 0) {
          return true;
        }
        // if the grids do no have the same amount of items return false
        if (!(this.numItems() == that.numItems())) {
          return false;
        }
        // otherwise check all items
        for (Location loc : this.itemLocations()) {
          // get each item for this and that
          E thisItem = this.get(loc);
          E thatItem = (E) that.get(loc);
          // if two items are not equal, return false
          if (!(thisItem.equals(thatItem))) {
            return false;
          }
        }
        // if all items are the same, return true
        return true;

      }
    }
    // if the grids are not the same size, return false
    return false;

  }

  @Override
  public Iterable<Location> allLocations() {
    return new AllLocationsIterable();
  }


  private class AllLocationsIterable implements Iterable<Location> {

    @Override
    public Iterator<Location> iterator() {
      return new AllLocationIterator();

    }
  }


  private class AllLocationIterator implements Iterator<Location> {
    int row;
    int col;

    private AllLocationIterator() {
      row = 0;
      col = 0;

    }

    @Override
    public boolean hasNext() {
      return row < numRows() && col < numCols();
    }

    @Override
    public Location next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Location loc = new Location(row, col);

      col++;
      if (col >= numCols()) {
        col = 0;
        row++;
      }
      return loc;

    }

  }
}
