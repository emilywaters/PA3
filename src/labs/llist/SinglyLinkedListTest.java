package labs.llist;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;


/**
 * Submission tests for Singly-Linked List lab.
 *
 * (Note that the organization of these tests doesn't follow unit-testing best
 * practices. Each test should be smaller and test only a limited condition.)
 *
 * @author CS 240 Instructors
 *
 */
class SinglyLinkedListTest {

  @Test
  void testGet() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.prepend("D");
    list.prepend("C");
    list.prepend("B");
    list.prepend("A");

    assertEquals("A", list.get(0));
    assertEquals("B", list.get(1));
    assertEquals("C", list.get(2));
    assertEquals("D", list.get(3));

    assertEquals(4, list.size()); // make sure get didn't somehow break the size.

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.get(-1);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.get(4);
    });

    // Make sure generics are set up correctly.
    SinglyLinkedList<Integer> intList = new SinglyLinkedList<>();
    intList.prepend(7);
    assertEquals(7, intList.get(0));
  }

  @Test
  void testAppend() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.append("A");
    list.append("B");
    list.append("C");
    list.append("D");

    assertEquals("A", list.get(0));
    assertEquals("B", list.get(1));
    assertEquals("C", list.get(2));
    assertEquals("D", list.get(3));

    assertEquals(4, list.size());
  }

  @Test
  void testContains() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();

    assertFalse(list.contains("Z"));

    list.prepend("C");

    assertTrue(list.contains("C"));
    assertFalse(list.contains("Z"));

    list.prepend("B");
    list.prepend("A");

    assertFalse(list.contains("Z"));
    assertTrue(list.contains("A"));
    assertTrue(list.contains("B"));
    assertTrue(list.contains("C"));

    // test efficiency
    SinglyLinkedList<Integer> bigList = new SinglyLinkedList<>();
    for (int i = 0; i < 100000; i++) {
      bigList.prepend(i);
    }

    assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
      assertFalse(bigList.contains(-1));
    });
  }

  @Test
  void testRemove() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();

    list.prepend("C");
    list.prepend("B");
    list.prepend("A");

    assertFalse(list.remove("E"));


    assertTrue(list.remove("A"));
    assertEquals(2, list.size());
    assertEquals("B", list.get(0));
    assertEquals("C", list.get(1));

    assertTrue(list.remove("C"));
    assertEquals(1, list.size());
    assertEquals("B", list.get(0));

    assertTrue(list.remove("B"));
    assertEquals(0, list.size());

    assertFalse(list.remove("A"));
    assertEquals(0, list.size());

    // test efficiency
    SinglyLinkedList<Integer> bigList = new SinglyLinkedList<>();
    for (int i = 0; i < 100000; i++) {
      bigList.prepend(i);
    }

    assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
      assertFalse(bigList.remove(-1));
    });
  }

  @Test
  void testIterator() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();

    // Iterator for an empty collection...
    Iterator<String> it = list.iterator();
    assertFalse(it.hasNext());

    assertThrows(NoSuchElementException.class, () -> {
      it.next();
    });

    // Check that it works when elements have been added
    list.prepend("C");
    list.prepend("B");
    list.prepend("A");

    Iterator<String> it1 = list.iterator();

    assertTrue(it1.hasNext());
    assertEquals("A", it1.next());
    assertTrue(it1.hasNext());
    assertEquals("B", it1.next());
    assertTrue(it1.hasNext());
    assertEquals("C", it1.next());
    assertFalse(it1.hasNext());

    assertThrows(NoSuchElementException.class, () -> {
      it1.next();
    });


    // test efficiency
    SinglyLinkedList<Integer> bigList = new SinglyLinkedList<>();
    for (int i = 0; i < 100000; i++) {
      bigList.prepend(i);
    }

    assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
      long sum = 0;
      for (int cur : bigList) {
        sum += cur;
      }
      assertEquals(4999950000L, sum);
    });
  }

}
