package labs.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that allows iteration through any array while skipping to every N'th element.
 *
 * For example, iterating through the array {"A", "B", "C", "D", "E"} with a skip value of 2, would
 * return "A", then "C" then "E". Iterating with a skip value of 3 would return "A", then "D".
 *
 *
 */
public class SkipScannerGeneric<T> implements Iterable<T> {
  private T[] elements;
  private int skip;

  /**
   * Construct a SkipScanner.
   *
   * @param elements The array to iterate over
   * @param skip The scanner will visit every skip'th element
   */
  public SkipScannerGeneric(T[] elements, int skip) {
    this.elements = elements;
    this.skip = skip;
  }

  /**
   * Iterate through an array of T[]while skipping to every N'th element.
   *
   * @return An appropriate iterator object.
   */
  public Iterator<T> iterator() {

    return new SkipIterator();
  }

  private class SkipIterator implements Iterator<T> {
    int index;


    private SkipIterator() {
      index = 0;
    }

    /**
     * See if there are more elements in collection.
     *
     * @return true if we haven't gone past the beginning.
     */
    public boolean hasNext() {
      return index <= elements.length - 1;
    }

    /**
     * Return the next T[]nd update the index.
     *
     * @return next element
     */
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      T result = elements[index];
      index += skip;
      return result;
    }

  }

}
