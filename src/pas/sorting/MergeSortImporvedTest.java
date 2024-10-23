package pas.sorting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MergeSortImporvedTest {

  @SuppressWarnings("deprecation")
  @Test
  public void testmergeSortHalfSpace() {
    Integer[] items = {4, 1, 3, 5, 2, 6, 7};
    MergeSortImproved.mergeSortHalfSpace(items);

    assertEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7}, items);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testmergeSortAdaptive() {
    Integer[] items = {4, 1, 3, 5, 2, 6, 7, 9, 8, 10};
    MergeSortImproved.mergeSortAdaptive(items);

    assertEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, items);
  }
}
