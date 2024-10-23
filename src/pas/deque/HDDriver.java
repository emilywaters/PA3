package pas.deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class HDDriver {
  static HybridDeque hd = new HybridDeque<>();
  static HybridDeque compare1 = new HybridDeque<>();
  static HybridDeque compare2 = new HybridDeque<>();
  static HybridDeque expected = new HybridDeque<>();

  public static void main(String[] args) {
    hd = new HybridDeque<>();
    hd.offerFirst("D");
    hd.offerFirst("C");
    hd.offerFirst("B");
    hd.offerFirst("A");
    hd.offerLast("E");
    hd.offerLast("F");
    hd.offerLast("G");
    hd.offerLast("H");

    Iterator<String> it = hd.iterator();

    it.next();
    it.remove();
    System.out.println(hd);

    it.next();
    it.remove();
    System.out.println(hd);

    it.next();
    it.remove();
    System.out.println(hd);

    it.next();
    it.remove();
    System.out.println(hd);

    it.next();
    it.remove();
    System.out.println(hd);

    it.next();
    it.remove();
    System.out.println(hd);

    it.next();
    it.remove();
    System.out.println(hd);

    it.next();
    it.remove();


  }

}
