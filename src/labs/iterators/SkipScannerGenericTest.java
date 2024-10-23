package labs.iterators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple Unit tests for the SkipScannerGeneric.
 *
 * @author Nathan Sprague, Kevin Molloy
 *
 */
class SkipScannerGenericTest {

  private String[] letters;
  private Integer[] numbers;
  private Point[] points;

  @BeforeEach
  public void setUp() throws Exception {
    letters = new String[] {"A", "B", "C", "D", "E"};
    numbers = new Integer[] {17, 21, 35, 17};
    points = new Point[3];
    points[0] = new Point(2, 4);
    points[1] = new Point(12, 17);
    points[2] = new Point(17, 12);
  }

  @Test
  public void testSkip1() {
    Iterator<String> it = new SkipScannerGeneric<>(letters, 1).iterator();
    for (String letter : letters) {
      assertTrue(it.hasNext());
      assertEquals(letter, it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void testSkip2() {
    Iterator<String> it = new SkipScannerGeneric<>(letters, 2).iterator();
    for (int i = 0; i < letters.length; i += 2) {
      assertTrue(it.hasNext());
      assertEquals(letters[i], it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void testSkip3() {
    Iterator<String> it = new SkipScannerGeneric<>(letters, 3).iterator();
    for (int i = 0; i < letters.length; i += 3) {
      assertTrue(it.hasNext());
      assertEquals(letters[i], it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void testSkip4() {
    Iterator<String> it = new SkipScannerGeneric<>(letters, 4).iterator();
    for (int i = 0; i < letters.length; i += 4) {
      assertTrue(it.hasNext());
      assertEquals(letters[i], it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void testSkip5() {
    Iterator<String> it = new SkipScannerGeneric(numbers, 1).iterator();
    for (Integer number : numbers) {
      assertTrue(it.hasNext());
      assertEquals(number, it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void testSkip6() {
    Iterator<Integer> it = new SkipScannerGeneric(numbers, 2).iterator();
    for (int i = 0; i < numbers.length; i += 2) {
      assertTrue(it.hasNext());
      assertEquals(numbers[i], it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void testSkip7() {
    Iterator<String> it = new SkipScannerGeneric(numbers, 3).iterator();
    for (int i = 0; i < numbers.length; i += 3) {
      assertTrue(it.hasNext());
      assertEquals(numbers[i], it.next());
    }
    assertFalse(it.hasNext());
  }

  @Test
  public void testSkip8() {
    Iterator<Point> it = new SkipScannerGeneric(points, 1).iterator();
    for (Point p : points) {
      assertTrue(it.hasNext());
      assertEquals(p, it.next());
    }
    assertFalse(it.hasNext());
  }

}
