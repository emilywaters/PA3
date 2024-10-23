package pas.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayGridDriver {
  protected static Grid<Integer> big = new ArrayGrid<>(3, 5);
  protected Grid<Integer> small = new ArrayGrid<>(3, 2);
  protected static Location l00 = new Location(0, 0);
  protected static Location l01 = new Location(0, 1);
  protected static Location l02 = new Location(0, 2);
  protected static Location l10 = new Location(1, 0);
  protected static Location l11 = new Location(1, 1);
  protected static Location l12 = new Location(1, 2);
  protected static Location l20 = new Location(2, 0);
  protected static Location l21 = new Location(2, 1);
  protected static Location l22 = new Location(2, 2);

  public static void main(String[] args) {
    Grid<Integer> sparseGrid = new SparseGrid<Integer>(3, 2);
    Grid<Integer> arrayGrid = new ArrayGrid<Integer>(3, 2);

    for (int i = 0; i < sparseGrid.numRows(); i++) {
      for (int j = 0; j < sparseGrid.numCols(); j++) {
        sparseGrid.put(new Location(i, j), i * 10 + j);
        arrayGrid.put(new Location(i, j), i * 10 + j);
      }
    }

    System.out.println(sparseGrid.equals(arrayGrid));
    System.out.println(arrayGrid.equals(sparseGrid));

    arrayGrid.put(new Location(2, 1), -1);
    System.out.println(sparseGrid.equals(arrayGrid));
    System.out.println(arrayGrid.equals(sparseGrid));
  }

}
