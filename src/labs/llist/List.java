package labs.llist;



/**
 * Minimalist List Interface for CS 240.
 *
 * @author CS 240 Instructors
 * @version 3/2023
 */
public interface List<E> extends Iterable<E> {

  /**
   * Append an item to the end of the list.
   */
  void append(E item);

  /**
   * Prepend an item at position 0 in the list.
   */
  void prepend(E item);

  /**
   * Return the item at the provided index.
   *
   * @throws IndexOutOfBoundsException if the provided index is out of bounds.
   */
  E get(int index);

  /**
   * Return true if the provided item is in the list, false otherwise.
   */
  boolean contains(E item);

  /**
   * Remove the provided item.
   *
   * @return true if the item was removed, false if it was not in the list.
   */
  boolean remove(E item);

  /**
   * Return the number of elements stored in the list.
   */
  int size();

}
