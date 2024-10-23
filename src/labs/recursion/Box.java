package labs.recursion;

import java.util.LinkedList;
import java.util.List;

/**
 * Box Class, boxes have a color and contain a list of boxes inside of them with other colors.
 *
 * @author Emily Waters
 * @version 9/27/2024
 */
public class Box {

  String color;
  List<Box> boxes;
  LinkedList<String> result;

  /**
   * Box Constructor, sets the color and boxes inside.
   *
   * @param color the color
   * @param boxes the boxes inside
   */
  public Box(String color, List<Box> boxes) {
    this.color = color;
    this.boxes = boxes;
    result = new LinkedList<>();

  }

  /**
   * Searches through the box to find a color.
   *
   * @param color the color searching for
   * @return the path to get to the color
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public LinkedList search(String color) {
    // BASE

    if (this.color.equals(color)) {
      LinkedList<String> list = new LinkedList<>();
      list.add(this.color);
      return list;
    }

    for (Box box : boxes) {
      LinkedList list = box.search(color);
      if (list != null) { // if we found the color, add the one before it
        list.addFirst(this.color);
        return list;
      }
    }

    return null;

  }
}
