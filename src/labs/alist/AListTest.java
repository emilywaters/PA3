package labs.alist;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * Test for the dynamic array lab. These tests aren't comprehensive. They are
 * only designed to ensure that array resizing is handled correctly.
 * 
 * @author Nathan Sprague
 * @version V1.3, 9/9/2021
 *
 */
class AListTest {


  /**
   * This method is a hack that allows grab the length of the array for the
   * purposes of testing.
   * 
   * @param list - An AList object
   * @return The length of the underlying array
   */
  @SuppressWarnings("rawtypes")
  private static int getArraySize(AList list) {
    return ((Object[]) list.listArray).length;
  }

  // -------------------------
  // TEST METHODS FOR APPPEND
  // -------------------------
  @Test
  public void testAppendNoResizeNeeded() {
    AList<String> list = new AList<>(2);
    list.append("A");
    list.append("B");

    assertEquals(0, list.currPos());
    assertEquals(2, list.length());

    assertEquals("A", list.getValue());
    list.moveToPos(1);
    assertEquals("B", list.getValue());

    assertEquals(2, getArraySize(list));

  }

  @Test
  public void testAppendOneResizeNeeded() throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException {
    AList<String> list = new AList<>(2);
    list.append("A");
    list.append("B");
    list.append("C");

    assertEquals(0, list.currPos());
    assertEquals(3, list.length());

    assertEquals("A", list.getValue());
    list.moveToPos(1);
    assertEquals("B", list.getValue());
    list.moveToPos(2);
    assertEquals("C", list.getValue());

    assertEquals(4, getArraySize(list));
  }

  @Test
  public void testAppendTwoResizesNeeded() throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException {
    AList<String> list = new AList<>(3);
    for (int i = 0; i < 7; i++) {
      list.append("A");
    }

    assertEquals(0, list.currPos());
    assertEquals(7, list.length());

    for (list.moveToStart(); !list.isAtEnd(); list.next()) {
      assertEquals("A", list.getValue());
    }

    assertEquals(12, getArraySize(list));
  }

  // -------------------------
  // TEST METHODS FOR INSERT
  // -------------------------

  @Test
  public void testInsertNoResizeNeeded() throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException {
    AList<String> list = new AList<>(2);
    list.insert("A");
    list.insert("B");

    assertEquals(0, list.currPos());
    assertEquals(2, list.length());

    assertEquals("B", list.getValue());
    list.moveToPos(1);
    assertEquals("A", list.getValue());

    assertEquals(2, getArraySize(list));


  }

  @Test
  public void testInsertOneResizeNeeded() throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException {
    AList<String> list = new AList<>(2);
    list.insert("A");
    list.insert("B");
    list.insert("C");

    assertEquals(0, list.currPos());
    assertEquals(3, list.length());

    assertEquals("C", list.getValue());
    list.moveToPos(1);
    assertEquals("B", list.getValue());
    list.moveToPos(2);
    assertEquals("A", list.getValue());

    assertEquals(4, getArraySize(list));
  }

  @Test
  public void testInsertTwoResizesNeeded() throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException {
    AList<String> list = new AList<>(3);
    for (int i = 0; i < 7; i++) {
      list.insert("A");
    }

    assertEquals(0, list.currPos());
    assertEquals(7, list.length());

    for (list.moveToStart(); !list.isAtEnd(); list.next()) {
      assertEquals("A", list.getValue());
    }

    assertEquals(12, getArraySize(list));
  }

  // -------------------------
  // TEST METHODS FOR REMOVE
  // -------------------------
  @Test
  public void testRemoveResizeNeeded() throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException {
    AList<String> list = new AList<>(3);
    for (int i = 0; i < 13; i++) {
      list.insert("A");
    }
    assertEquals(24, getArraySize(list));

    for (int i = 0; i < 11; i++) {
      list.remove();
    }

    assertEquals(0, list.currPos());
    assertEquals(2, list.length());

    for (list.moveToStart(); !list.isAtEnd(); list.next()) {
      assertEquals("A", list.getValue());
    }

    assertTrue(getArraySize(list) < 24);
  }

}
