package labs.lqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueDriver {
  public static void main(String[] args) {
    AQueue<String> queue = new AQueue<>(3);

    System.out.println(queue.enqueue("A"));
    System.out.println(queue.enqueue("B"));
    System.out.println(queue.enqueue("C"));

    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
  }
}
