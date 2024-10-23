package labs.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class allows us to scan through the "n-grams" that are represented by an array of Strings.
 * An n-gram is a sequence of n words appearing in order. For example, the sentence:
 * {"I", "love", "computer", "science"}
 * Contains the 2-grams (also called bigrams): "I love", "love computer", and "computer science".
 * It contains the 3-grams (also called trigrams): "I love computer", and "love computer science".
 *
 *
 */
public class NGramScanner implements Iterable<String> {
  private String[] elements;
  private int nValue;

  /**
   * Construct an NGramScanner.
   *
   * @param elements The array of words
   * @param nValue The value of n to use in constructing n-grams.
   */
  public NGramScanner(String[] elements, int nValue) {
    this.elements = elements;
    this.nValue = nValue;
  }

  @Override
  public Iterator<String> iterator() {
    return new NGramIterator();
  }

  private class NGramIterator implements Iterator<String> {
    int index;


    private NGramIterator() {
      index = 0;
    }

    /**
     * See if there are more elements in collection.
     *
     * @return true if we haven't gone past the beginning.
     */
    public boolean hasNext() {
      return index <= elements.length - nValue;
    }

    /**
    * Return the next T[]nd update the index.
     *
     * @return next element
     */
    public String next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      String result = "";
      for (int i = 0; i < nValue; i++) {
        if (i > 0) {
          result += " ";

        }
        result += elements[index + i];
      }
      // {"I", "love", "computer", "science"}
      index++; // create a for loop
      return result;
    }

  }


}
