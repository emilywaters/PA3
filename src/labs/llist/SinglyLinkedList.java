package labs.llist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Singly-linked list implementation of the CS 240 List interface.
 *
 * This interface stores a head reference, but no tail. Therefore adding or
 * removing items at the end of the list will require O(n) steps.
 *
 * @author CS 240 Instructors and ???
 *
 */
public class SinglyLinkedList<E> implements List<E> {

  private Node head;
  private int size;

  /**
   * Private inner Node class.
   */
  private class Node {

    private Node next;
    private E item;

    public Node(Node next, E item) {
      this.next = next;
      this.item = item;
    }

  }

  /**
   * Construct an empty list.
   */
  public SinglyLinkedList() {
    head = null;
    size = 0;
  }

  /**
   * Helper method for obtaining the Node at a particular index. Helpful for get
   * and append.
   *
   * @throws IndexOutOfBoundsException if the provided index is out of bounds.
   */
  private Node getNode(int index) {
    // This should be implemented by creating a local variable named something like
    // "curNode" that will be initialized to the head of the list. Use a for loop to
    // step forward the desired number of times by repeatedly assigning curNode.next
    // to curNode.
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    Node curNode = head;
    for (int i = 0; i < index; i++) {
      curNode = curNode.next;
    }
    return curNode;
  }

  @Override
  public E get(int index) {
    return getNode(index).item;

  }

  @Override
  public void append(E item) {
    // You can use getNode to obtain the last node in the list and then modify it's
    // next reference. Don't forget to update the list size.
    // if size is zero, make a new head
    if (size == 0) {
      head = new Node(null, item);
    } else { // otherwise, add on from last node
      Node lastNode = getNode(size - 1);
      lastNode.next = new Node(null, item);

    }
    size++;

  }

  @Override
  public void prepend(E item) {
    head = new Node(head, item);
    size++;
  }


  @Override
  public boolean contains(E item) {

    // UNFINISHED
    //
    // Perform a sequential search over the nodes in the list starting at the head.
    // Again, you should create a local variable named curNode. This reference
    // should be updated until the item is found or until the end of the list has
    // been reached.

    Node curNode = head;
    while (curNode != null) {
      if (curNode.item == item) {
        return true;
      }
      curNode = curNode.next;
    }

    return false;
  }

  @Override
  public boolean remove(E item) {

    // UNFINISHED
    //
    // Keep in mind that removal actually requires modifying the node BEFORE the
    // node that will be removed:
    // Imagine that our list is A -> B -> C and that we want to remove B. We will
    // accomplish this by modifying A's next reference so that it points to C.
    //
    // There are three cases that need to be considered:
    //
    // 1. If the list is completely empty, we can just return false.
    //
    // 2. Removing the head is a special case since it has no predecessor.
    //
    // 3. In the general case, we must find and modify the predecessor of the node
    // that needs to be removed as described above.
    if (size == 0) { // if there are no items, return false
      return false;
    }
    if (head.item.equals(item)) { // when removing head
      head = head.next;
      size--;
      return true;
    }
    Node curNode = head;
    int index = 0;
    while (curNode != null) {
      if (curNode.item.equals(item)) {
        Node lastNode = getNode(index - 1);
        lastNode.next = curNode.next;
        size--;
        return true;

      }
      curNode = curNode.next;
      index++;
    }


    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<E> iterator() {
    return new SinglyLinkedListIterator();
  }


  private class SinglyLinkedListIterator implements Iterator<E> {

    // UNFINISHED

    // You should have a single Node as an instance variable. This reference will be
    // used to keep track of your position during iteration.
    Node curNode = head;

    @Override
    public boolean hasNext() {
      return curNode != null;
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      E item = curNode.item;
      curNode = curNode.next;
      return item;
    }

  }



}
