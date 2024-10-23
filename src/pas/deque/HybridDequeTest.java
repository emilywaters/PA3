package pas.deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Test Class for the HybridDeque Class.
 */
public class HybridDequeTest {

  private HybridDeque<String> hd = new HybridDeque<>();
  private HybridDeque<String> expected;
  private HybridDeque<String> compare1;
  private HybridDeque<String> compare2;


  @Test
  void testSize() {
    HybridDeque.setBlockSize(8);
    assertEquals(0, hd.size());
    // deque with 5 items
    hd.offerLast("A");
    hd.offerLast("B");
    hd.offerLast("C");
    hd.offerLast("D");
    hd.offerLast("E");
    assertEquals(5, hd.size());

    // deque with 2 items
    assertEquals("A", hd.pollFirst());
    assertEquals("E", hd.pollLast());
    assertEquals("D", hd.pollLast());
    assertEquals(2, hd.size());

    // deque: [B, C]
    assertEquals("B", hd.pollFirst());
    assertEquals("C", hd.pollFirst());

    assertEquals(0, hd.size());
  }

  @Test
  void testPollLastItem() {
    hd.offerLast("hi");
    assertEquals("hi", hd.pollFirst());
    assertEquals(0, hd.size());
    hd.offerFirst("hello");
    assertEquals("hello", hd.pollLast());
    assertEquals(0, hd.size());

  }

  @Test
  void testExceptionWhenInsertingNullItems() {
    assertThrows(NullPointerException.class, () -> {
      hd.offerFirst(null); // Calling remove before calling next
    });
    assertThrows(NullPointerException.class, () -> {
      hd.offerLast(null); // Calling remove before calling next
    });
  }

  @Test
  void testOfferFirst() {
    assertEquals(true, hd.offerFirst("A"));
    assertEquals(true, hd.offerFirst("A"));
    assertEquals(true, hd.offerFirst("A"));
    assertEquals(true, hd.offerFirst("A"));
    assertEquals(true, hd.offerFirst("A"));
  }

  @Test
  void testSetBlockSize() {
    HybridDeque.setBlockSize(64);
  }


  @Test
  void testPeekFirst() {
    // D, B, A, C, E
    hd.offerLast("A");
    assertEquals("A", hd.peekFirst());
    hd.offerFirst("B");
    assertEquals("B", hd.peekFirst());
    hd.offerLast("C");
    assertEquals("B", hd.peekFirst());
    hd.offerFirst("D");
    assertEquals("D", hd.peekFirst());
    hd.offerLast("E");

    assertEquals("D", hd.peekFirst());

  }

  @Test
  void testPeekLast() {
    // D, B, A, C, E
    hd.offerFirst("A");
    assertEquals("A", hd.peekLast());
    hd.offerFirst("B");
    assertEquals("A", hd.peekLast());
    hd.offerLast("C");
    assertEquals("C", hd.peekLast());
    hd.offerFirst("D");
    assertEquals("C", hd.peekLast());
    hd.offerLast("E");

    assertEquals("E", hd.peekLast());

  }

  @Test
  void testIterator() {
    hd.offerLast("A");
    hd.offerLast("B");
    hd.offerLast("C");
    hd.offerLast("D");
    hd.offerLast("E");

    Iterator<String> it = hd.iterator();
    assertEquals("A", it.next());
    assertEquals("B", it.next());
    assertEquals("C", it.next());
    assertEquals("D", it.next());
    assertEquals("E", it.next());

  }

  @Test
  void testDescendingIterator() {
    hd.offerFirst("A");
    hd.offerFirst("B");
    hd.offerFirst("C");
    hd.offerFirst("D");
    hd.offerFirst("E");

    Iterator<String> it = hd.descendingIterator();
    assertEquals("A", it.next());
    assertEquals("B", it.next());
    assertEquals("C", it.next());
    assertEquals("D", it.next());
    assertEquals("E", it.next());
  }

  @Test
  void testIteratorsWithMultipleBlocks() {
    hd.offerFirst("E");
    hd.offerFirst("D");
    hd.offerFirst("C");
    hd.offerFirst("B");
    hd.offerFirst("A");

    hd.offerLast("F");
    hd.offerLast("G");
    hd.offerLast("H");
    hd.offerLast("I");
    hd.offerLast("J");
    hd.offerLast("K");

    Iterator<String> it = hd.iterator();
    assertEquals("A", it.next());
    assertEquals("B", it.next());
    assertEquals("C", it.next());
    assertEquals("D", it.next());
    assertEquals("E", it.next());
    assertEquals("F", it.next());
    assertEquals("G", it.next());
    assertEquals("H", it.next());
    assertEquals("I", it.next());
    assertEquals("J", it.next());
    assertEquals("K", it.next());
  }

  @Test
  void testTwoBlocksEqual() {
    // Create first deque
    compare1 = new HybridDeque<>();
    compare1.offerLast("A");
    compare1.offerLast("B");
    compare1.offerLast("C");
    compare1.offerLast("D");
    compare1.offerLast("E");

    // Create second deque
    compare2 = new HybridDeque<>();
    compare2.offerLast("A");
    compare2.offerLast("B");
    compare2.offerLast("C");
    compare2.offerLast("D");
    compare2.offerLast("E");

    assertEquals(true, compare1.equals(compare2));

  }

  @Test
  void testEmptyBlocksEqual() {
    compare1 = new HybridDeque<>();
    compare2 = new HybridDeque<>();

    assertEquals(true, compare1.equals(compare2));

  }

  @Test
  void testTwoBlocksDifferentItems() {
    compare1 = new HybridDeque<>();
    compare1.offerLast("A");
    compare1.offerLast("B");
    compare1.offerLast("D");
    compare2 = new HybridDeque<>();
    compare2.offerLast("A");
    compare2.offerLast("B");
    compare2.offerLast("C");

    assertEquals(false, compare1.equals(compare2));

  }

  @Test
  void testTwoBlocksDifferentSizes() {
    compare1 = new HybridDeque<>();
    compare2 = new HybridDeque<>();
    compare2.offerLast("A");
    compare2.offerLast("B");
    compare2.offerLast("C");
    assertEquals(false, compare1.equals(compare2));
  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  void testEqualsDifferentObjects() {
    compare1 = new HybridDeque<>();
    String randomObject = "Not a HybridDeque";

    assertEquals(false, compare1.equals(randomObject));
  }


  @Test
  void testIteratorRemove() {
    hd.offerLast("A");
    hd.offerLast("B");
    hd.offerLast("C");
    hd.offerLast("D");
    hd.offerLast("E");

    Iterator<String> it = hd.iterator();
    it.next(); // gets A
    it.remove(); // removes A

    expected = new HybridDeque<>();
    expected.offerLast("B");
    expected.offerLast("C");
    expected.offerLast("D");
    expected.offerLast("E");

    assertEquals(true, hd.equals(expected));

  }

  @Test
  void testFirstOccurrence() {
    hd.offerLast("A");
    hd.offerLast("B");
    hd.offerLast("C");
    hd.offerLast("D");

    assertEquals(true, hd.removeFirstOccurrence("B"));
    Iterator<String> it = hd.iterator();
    assertEquals("A", it.next());
    assertEquals("C", it.next());

  }


  @Test
  void testFirstOccurrenceReturnsFalse() {
    hd.offerLast("A");
    hd.offerLast("B");
    hd.offerLast("C");
    hd.offerLast("D");

    assertEquals(false, hd.removeFirstOccurrence("E"));


  }

  @Test
  void testLastOccurrence() {
    hd.offerLast("A");
    hd.offerLast("B");
    hd.offerLast("A");
    hd.offerLast("B");

    assertEquals(true, hd.removeLastOccurrence("A"));

    Iterator<String> it = hd.iterator();
    assertEquals("A", it.next());
    assertEquals("B", it.next());
    assertEquals("B", it.next());



  }

  @Test
  void testLastOccurrenceReturnsFalse() {
    hd.offerFirst("A");
    hd.offerFirst("B");
    hd.offerFirst("A");
    hd.offerFirst("B");

    assertEquals(false, hd.removeLastOccurrence("E"));
  }

  @Test
  void testRemoveThrowsIllegalStateException() {
    hd.offerLast("A");
    Iterator<String> ascendingIt = hd.iterator();
    Iterator<String> descendingIt = hd.descendingIterator();

    assertThrows(IllegalStateException.class, () -> {
      ascendingIt.remove(); // Calling remove before calling next
    });
    assertThrows(IllegalStateException.class, () -> {
      descendingIt.remove(); // Calling remove before calling next
    });
  }

  @Test
  void testNextThrowsNoSuchElementException() {
    hd.offerLast("A");
    Iterator<String> ascendingIt = hd.iterator();
    Iterator<String> descendingIt = hd.descendingIterator();

    ascendingIt.next();
    assertThrows(NoSuchElementException.class, () -> {
      ascendingIt.next(); // hasNext() returns false
    });

    descendingIt.next();
    assertThrows(NoSuchElementException.class, () -> {
      descendingIt.next(); // hasNext() returns false
    });

  }

  @Test
  void testSize64() {
    // check crossing boundary works for large sizes
    compare1 = new HybridDeque<>();
    for (int i = 0; i < 65; i++) {
      compare1.offerFirst("A");
    }
    compare2 = new HybridDeque<>();
    for (int i = 0; i < 65; i++) {
      compare2.offerLast("A");
    }

  }

  @Test
  void testTimeComplexity() {
    int[] sizes = {1000, 10000, 100000, 1000000};
    for (int size : sizes) {
      HybridDeque<Integer> deque = new HybridDeque<>();
      assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
        for (int i = 0; i < size; i++) {
          deque.offerFirst(i);
        }
      }, "Adding to the front should be Theta(1) for size " + size);
    }

    for (int size : sizes) {
      HybridDeque<Integer> deque = new HybridDeque<>();
      assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
        for (int i = 0; i < size; i++) {
          deque.offerLast(i);
        }
      }, "Appending should be Theta(1) for size " + size);
    }

    for (int size : sizes) {
      HybridDeque<Integer> deque = new HybridDeque<>();
      assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
        for (int i = 0; i < size; i++) {
          deque.peekFirst();
        }
      }, "Getting first item should be Theta(1) for size " + size);
    }

    for (int size : sizes) {
      HybridDeque<Integer> deque = new HybridDeque<>();
      assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
        for (int i = 0; i < size; i++) {
          deque.peekLast();
        }
      }, "Getting last item should be Theta(1) for size " + size);
    }
  }

  @Test
  void testRemoveAllItemsFromSingleBlock() {
    hd.offerFirst("D");
    hd.offerFirst("C");
    hd.offerFirst("B");
    hd.offerFirst("A");
    hd.offerLast("E");
    hd.offerLast("F");
    hd.offerLast("G");
    hd.offerLast("H");

    Iterator<String> ascendIterator = hd.iterator();

    while (ascendIterator.hasNext()) {
      ascendIterator.next();
      ascendIterator.remove();
    }
  }

  @Test
  void testRemoveLastOccurrenceFirstElementInSingleBlock() {
    hd.offerFirst("D");
    hd.offerFirst("C");
    hd.offerFirst("B");
    hd.offerFirst("A");
    assertEquals(true, hd.removeLastOccurrence("A"));
  }

  @Test
  void testRemoveFirstOccurrenceFirstElementInSingleBlock() {
    hd.offerLast("A");
    hd.offerLast("B");
    hd.offerLast("C");
    hd.offerLast("D");
    assertEquals(true, hd.removeFirstOccurrence("D"));
  }

  @Test
  void testIteratorHasNext() {
    hd.offerLast("A");
    hd.offerLast("B");
    hd.offerLast("C");
    hd.offerLast("D");

    Iterator<String> it = hd.iterator();
    assertEquals("A", it.next());
    assertEquals("B", it.next());
    assertEquals("C", it.next());
    assertEquals("D", it.next());
    // the cursor is null
    assertEquals(false, it.hasNext());
  }

  @Test
  void testCursorNullAtEnd() {
    hd.offerFirst("D");
    hd.offerFirst("C");
    hd.offerFirst("B");
    hd.offerFirst("A");

    Iterator<String> it = hd.descendingIterator();
    assertEquals("D", it.next());
    assertEquals("C", it.next());
    assertEquals("B", it.next());
    assertEquals("A", it.next());
    // the cursor is null
    assertThrows(NoSuchElementException.class, () -> {
      it.next(); // Calling remove before calling next
    });
  }

  @Test
  void testCursorNullAtFront() {
    hd.offerFirst("D");
    hd.offerFirst("C");
    hd.offerFirst("B");
    hd.offerFirst("A");

    Iterator<String> it = hd.iterator();
    assertEquals("A", it.next());
    assertEquals("B", it.next());
    assertEquals("C", it.next());
    assertEquals("D", it.next());
    // the cursor is null
    assertThrows(NoSuchElementException.class, () -> {
      it.next(); // Calling remove before calling next
    });
  }


}
