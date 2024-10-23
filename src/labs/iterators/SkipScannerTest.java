package labs.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Simple Unit tests for the SkipScanner.
 *
 * @author Nathan Sprague
 *
 */
class SkipScannerTest {

  private String[] letters;

  @BeforeEach
  public void setUp() throws Exception {
    letters = new String[] {"A", "B", "C", "D", "E"};
  }

  @Test
  public void testSkip1() {
    Iterator<String> it = new SkipScanner(letters, 1).iterator();
    for (String letter : letters) {
      assertTrue(it.hasNext());
      assertEquals(letter, it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void testSkip2() {
    Iterator<String> it = new SkipScanner(letters, 2).iterator();
    for (int i = 0; i < letters.length; i += 2) {
      assertTrue(it.hasNext());
      assertEquals(letters[i], it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void testSkip3() {
    Iterator<String> it = new SkipScanner(letters, 3).iterator();
    for (int i = 0; i < letters.length; i += 3) {
      assertTrue(it.hasNext());
      assertEquals(letters[i], it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void testSkip4() {
    Iterator<String> it = new SkipScanner(letters, 4).iterator();
    for (int i = 0; i < letters.length; i += 4) {
      assertTrue(it.hasNext());
      assertEquals(letters[i], it.next());
    }
    assertFalse(it.hasNext());
  }


}
