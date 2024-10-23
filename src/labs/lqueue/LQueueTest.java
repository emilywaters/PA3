package labs.lqueue;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for an unbounded linke-list-based queue.
 *
 * @author CS 240 Instructions
 *
 */
class LQueueTest {

  @Test
  public void testDequeueFromEmpty() {
    Queue<String> queue = new LQueue<>();
    assertThrows(NoSuchElementException.class, () -> {queue.dequeue();});
    assertEquals(0, queue.length());
  }

  @Test
  public void testFrontValueEmpty() {
    Queue<String> queue = new LQueue<>();
    assertThrows(NoSuchElementException.class, () -> {queue.frontValue();});
  }

  @Test
  public void testClear() {
    Queue<String> queue = new LQueue<>();
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    queue.clear();
    assertEquals(0, queue.length());
    assertThrows(NoSuchElementException.class, () -> {queue.dequeue();});
    assertTrue(queue.isEmpty());
  }


  @Test
  void testQueue() {

    /* It would be better style to break this up into multiple smaller tests. */

    Queue<String> queue = new LQueue<>();

    assertTrue(queue.isEmpty());

    queue.enqueue("A");
    assertFalse(queue.isEmpty());
    assertEquals("A", queue.frontValue());

    queue.enqueue("B");
    assertEquals("A", queue.frontValue());

    queue.enqueue("C");
    assertEquals("A", queue.frontValue());

    assertEquals(3, queue.length());

    assertEquals("A", queue.dequeue());
    assertEquals(2, queue.length());

    assertEquals("B", queue.frontValue());
    assertEquals("B", queue.dequeue());
    assertEquals(1, queue.length());

    assertEquals("C", queue.frontValue());
    assertEquals("C", queue.dequeue());
    assertEquals(0, queue.length());

    assertTrue(queue.isEmpty());


    queue.enqueue("X");
    queue.enqueue("Y");
    queue.enqueue("Z");

    assertEquals(3, queue.length());

    assertEquals("X", queue.dequeue());
    assertEquals(2, queue.length());

    assertEquals("Y", queue.dequeue());
    assertEquals(1, queue.length());

    assertEquals("Z", queue.dequeue());
    assertEquals(0, queue.length());
  }

  @Test
  void testQueueLarge() {

    Queue<Integer> queue = new LQueue<>();

    for (int i = 0; i < 1000; i++) {
      queue.enqueue(i);
    }

    for (int i = 0; i < 500; i++) {
      assertEquals(i, queue.dequeue());
    }

    for (int i = 0; i < 1000; i++) {
      queue.enqueue(i * 10);
    }

    for (int i = 0; i < 500; i++) {
      assertEquals(i + 500, queue.dequeue());
    }

    for (int i = 0; i < 1000; i++) {
      assertEquals(i * 10, queue.dequeue());
    }

  }

}
