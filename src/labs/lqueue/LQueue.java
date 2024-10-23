package labs.lqueue;

import java.util.NoSuchElementException;

/**
 * linked-Queue class.
 *
 * This implementation differs from the OpenDSA version in that it does
 * not use a sentinel node.
 *
 * @author CS240 instructors and Emily Waters
 *
 */
public class LQueue<E> implements Queue<E> {

  private Node head;
  private Node tail;
  private int size;

  private class Node {
    E item;
    Node next;

    public Node(E item, Node next) {
      this.item = item;
      this.next = next;
    }
  }

  /**
   * Initialize an empty queue with unbounded capacity.
   */
  public LQueue() {
    clear();
  }

  @Override
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  @Override
  public boolean enqueue(E item) {
    // adding
    if (size != 0) {
      Node node = new Node(item, null);
      tail.next = node;
      tail = node;
    } else {
      // adding to an empty queue
      Node node = new Node(item, null);
      head = node;
      tail = node;
    }
    size++;
    return true;
  }

  @Override
  public E dequeue() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    // removing
    E removed = head.item;
    if (size > 1) {
      head = head.next;
      size--;
    } else { //removing the last element
      clear();
    }
    // Note that dequeuing the last element must be handled as a special case.

    return removed;
  }

  @Override
  public E frontValue() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    return head.item;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int length() {
    return size;
  }


}
