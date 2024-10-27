package pas.sorting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MergeSortImporvedTest {

  @SuppressWarnings("deprecation")
  @Test
  public void testmergeSortHalfSpace() {
    Integer[] items = {4, 1, 3, 5, 2, 6, 7};
    MergeSortImproved.mergeSortHalfSpace(items);

    assertEquals(new Integer[] {1, 2, 3, 4, 5, 6, 7}, items);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testmergeSortAdaptive() {
    Integer[] items = {4, 1, 3, 5, 2, 6, 7, 9, 8, 10};
    MergeSortImproved.mergeSortAdaptive(items);

    assertEquals(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, items);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testmergeSortAdaptiveBig() {
    Integer[] items = {34, 54, 76, 86, 354, 67, 34, 76, 3, 4, 67, 467, 78, 3, 1, 3, 7, 87, 43, 54,
        65, 3, 2, 32, 4, 23};
    MergeSortImproved.mergeSortAdaptive(items);

    assertEquals(new Integer[] {1, 2, 3, 3, 3, 3, 4, 4, 7, 23, 32, 34, 34, 43, 54, 54, 65, 67, 67, 76, 76, 78, 86, 87, 354, 467
    }, items);
  }
}
