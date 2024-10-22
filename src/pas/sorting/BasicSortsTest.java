package pas.sorting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BasicSortsTest {

  @SuppressWarnings("deprecation")
  @Test
  public void testInsertionSubsort() {
    Integer[] nums = {3, 2, 1, 9, 8};

    BasicSorts.insertionSubsort(nums, 0, 2);

    assertEquals(new Integer[] {1, 2, 3, 9, 8}, nums);
  }
}
