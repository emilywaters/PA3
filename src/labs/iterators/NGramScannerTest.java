package labs.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Simple Unit Tests for NGramScanner.
 *
 * @author Nathan Sprague
 *
 */
class NGramScannerTest {

  private String[] words;

  @BeforeEach
  public void setUp() throws Exception {
    words = new String[] {"I", "love", "computer", "science"};
  }

  @Test
  public void test1Grams() {
    Iterator<String> it = new NGramScanner(words, 1).iterator();
    for (String word : words) {
      assertTrue(it.hasNext());
      assertEquals(word, it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void test2Grams() {
    Iterator<String> it = new NGramScanner(words, 2).iterator();
    assertTrue(it.hasNext());
    assertEquals("I love", it.next());
    assertTrue(it.hasNext());
    assertEquals("love computer", it.next());
    assertTrue(it.hasNext());
    assertEquals("computer science", it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void test3Grams() {
    Iterator<String> it = new NGramScanner(words, 3).iterator();
    assertTrue(it.hasNext());
    assertEquals("I love computer", it.next());
    assertTrue(it.hasNext());
    assertEquals("love computer science", it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void test4Grams() {
    Iterator<String> it = new NGramScanner(words, 4).iterator();
    assertTrue(it.hasNext());
    assertEquals("I love computer science", it.next());
    assertFalse(it.hasNext());
  }
}
