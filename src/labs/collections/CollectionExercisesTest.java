package labs.collections;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Submission tests for Collections lab.
 *
 * @author Nathan Sprague
 * @version 4/23/21
 *
 */
@TestMethodOrder(OrderAnnotation.class)
class CollectionExercisesTest {

  private LinkedList<Integer> list1;
  private LinkedList<Integer> list2;
  private LinkedList<Integer> emptyList;

  @BeforeEach
  void setUp() throws Exception {
    Integer[] array1 = {8, 8, 1, 3, 3, 7, 0, 3, 3, 7};
    Integer[] array2 = {4, 4, 7, 9, 4, 8, 4, 1, 8, 4};

    list1 = new LinkedList<>(Arrays.asList(array1));
    list2 = new LinkedList<>(Arrays.asList(array2));
    emptyList = new LinkedList<>();
  }

  @Test
  @Order(1)
  public void testRemoveSmallInts() {

    Integer[] arrayExpected = {8, 8, 3, 3, 7, 3, 3, 7};
    LinkedList<Integer> expected = new LinkedList<Integer>(Arrays.asList(arrayExpected));

    CollectionExercises.removeSmallInts(list1, 3);
    assertEquals(expected, list1);

    arrayExpected = new Integer[] {9, 8, 8};
    expected = new LinkedList<Integer>(Arrays.asList(arrayExpected));

    CollectionExercises.removeSmallInts(list2, 8);
    assertEquals(expected, list2);
  }

  @Test
  @Order(2)
  public void testContainsDuplicates() {

    Integer[] array = {0, 1, 3, 7, 8};
    LinkedList<Integer> ints = new LinkedList<Integer>(Arrays.asList(array));

    assertFalse(CollectionExercises.containsDuplicates(ints));

    assertFalse(CollectionExercises.containsDuplicates(emptyList));

    assertTrue(CollectionExercises.containsDuplicates(list1));
    assertTrue(CollectionExercises.containsDuplicates(list2));
  }

  @Test
  @Order(4)
  public void testInEither() {

    Integer[] arrayExpected = {0, 1, 3, 4, 7, 8, 9};
    LinkedList<Integer> expected = new LinkedList<Integer>(Arrays.asList(arrayExpected));
    ArrayList<Integer> result = CollectionExercises.inEither(list1, list2);

    Collections.sort(result);
    assertEquals(expected, result);
  }

  @Test
  @Order(5)
  public void testInBoth() {
    Integer[] arrayExpected = {1, 7, 8};
    LinkedList<Integer> expected = new LinkedList<Integer>(Arrays.asList(arrayExpected));
    ArrayList<Integer> result = CollectionExercises.inBoth(list1, list2);

    Collections.sort(result);
    assertEquals(expected, result);
  }

  @Test
  @Order(6)
  public void testMostFrequent() {

    // Most frequent is at the start.
    String[] array = {"A", "B", "C", "A", "A", "B", "C", "D", "E"};
    LinkedList<String> list = new LinkedList<String>(Arrays.asList(array));
    assertEquals("A", CollectionExercises.mostFrequent(list));

    // Most frequent is at the end.
    array = new String[] {"A", "B", "C", "A", "A", "B", "C", "D", "E", "E", "E", "E"};
    list = new LinkedList<String>(Arrays.asList(array));
    assertEquals("E", CollectionExercises.mostFrequent(list));

    // Most frequent is in the middle.
    array = new String[] {"A", "B", "C", "A", "A", "B", "C", "B", "B", "D"};
    list = new LinkedList<String>(Arrays.asList(array));
    assertEquals("B", CollectionExercises.mostFrequent(list));

  }

}
