package pas.grid;


/**
 * Grid Interface.
 *
 * @author Emily Waters
 * @version 8/29/2024
 */
public interface Grid<E> extends Iterable<E> {

  /**
   * Places an item at the indicated location in the Grid.
   * If the location occupied, the previous element is replaced.
   * Storing null elements with cause a NullPointerException.
   *
   * @param loc the specified location
   * @param item item being put in grid
   */
  public void put(Location loc, E item) throws IllegalArgumentException;

  /**
   * Returns the element stored at the indicated location, or null if the location is empty.
   *
   * @param loc location being accessed
   * @return the item at that location
   */
  public E get(Location loc);

  /**
   * Removes and returns the element stored at the indicated location,
   * or null if the location is empty.
   *
   * @param loc location removing element from
   * @return the element being removed
   */
  public E remove(Location loc);

  /**
   * Get the number of rums in the grid.
   *
   * @return number of rows
   */
  public int numRows();

  /**
   * Get the number of columns in the grid.
   *
   * @return number of columns
   */
  public int numCols();

  /**
   * Gets the number of items in the grid.
   *
   * @return the number of items
   */
  public int numItems();

  /**
   * Returns an Iterable that provides access to all grid cells.
   * Implementations must ensure that locations are accessed in row-major order.
   *
   * @return all locations
   */
  public Iterable<Location> allLocations();

  /**
   * Returns an Iterable that provides access to all occupied grid cells.
   *
   * @return locations with items
   */
  public Iterable<Location> itemLocations();

  /**
   * Two grids are considered equal if they have the same shape,
   *  and if the elements at corresponding locations in the grids are equal.
   *
   * @param other another grid object
   * @return true/false if two grids are equal
   */
  public boolean equals(Object other);

  /**
   * Must make a a grid.
   * Example:
   * +--------+--------+--------+--------+
   * |        |        |        |        |
   * +--------+--------+--------+--------+
   * | Joyful |        |        |        |
   * +--------+--------+--------+--------+
   * |        |        | Tree   |        |
   * +--------+--------+--------+--------+
   *
   * @return grid
   */
  public String toString();



}
