package pas.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Doubly-linked-list implementation of the java.util.Deque interface. This implementation is more
 * space-efficient than Java's LinkedList class for large collections because each node contains a
 * block of elements instead of only one. This reduces the overhead required for next and previous
 * node references.
 *
 * <p>This implementation does not allow null's to be added to the collection.
 * Adding a null will result in a NullPointerException.
 *
 * @author Emily Waters
 * @version 9/19/2024
 *
 */
public class HybridDeque<E> extends AbstractDeque<E> {

  /*
   * IMPLEMENTATION NOTES ----------------------------------
   *
   * The list of blocks is never empty, so leftBlock and rightBlock are never equal to null. The
   * list is not circular.
   *
   * A deque's first element is at leftBlock.elements[leftIndex]
   *
   * and its last element is at rightBlock.elements[rightIndex].
   *
   * The indices, leftIndex and rightIndex are always in the range:
   *
   * 0 <= index < BLOCK_SIZE
   *
   * And their exact relationship is:
   *
   * (leftIndex + size - 1) % BLOCK_SIZE == rightIndex
   *
   * Whenever leftBlock == rightBlock, then:
   *
   * leftIndex + size - 1 == rightIndex
   *
   * However, when leftBlock != rightBlock, the leftIndex and rightIndex become indices into
   * distinct blocks and either may be larger than the other.
   *
   * Empty deques have:
   *
   * size == 0
   *
   * leftBlock == rightBlock
   *
   * leftIndex == CENTER + 1
   *
   * rightIndex == CENTER
   *
   * Checking for size == 0 is the intended way to see whether the Deque is empty.
   *
   *
   * (Comments above are a lightly modified version of comments in Python's deque implementation:
   * https://github.com/python/cpython/blob/v3.11.2/Modules/_collectionsmodule.c
   * https://docs.python.org/3.11/license.html)
   *
   */

  private static int BLOCK_SIZE = 8;
  private static int CENTER = (BLOCK_SIZE - 1) / 2;

  // private Block leftBlock;
  // private Block rightBlock;
  private Cursor leftCursor;
  private Cursor rightCursor;
  private int size;


  /**
   * DO NOT MODIFY THIS METHOD. This will be used in grading/testing to modify the default block
   * size..
   *
   * @param blockSize The new block size
   */
  protected static void setBlockSize(int blockSize) {
    HybridDeque.BLOCK_SIZE = blockSize;
    HybridDeque.CENTER = (blockSize - 1) / 2;
  }


  /**
   * Doubly linked list node (or block) containing an array with space for multiple elements.
   */
  private class Block {
    private E[] elements;
    private Block next;
    private Block prev;

    /**
     * Block Constructor.
     *
     * @param prev Reference to previous block, or null if this is the first
     * @param next Reference to next block, or null if this is the last
     */
    @SuppressWarnings("unchecked")
    public Block(Block prev, Block next) {
      this.elements = (E[]) (new Object[BLOCK_SIZE]);
      this.next = next;
      this.prev = prev;
    }

  }

  /**
   * Many of the complications of implementing this Deque class are related to the fact that there
   * are two pieces of information that need to be maintained to track a position in the deque: a
   * block reference and the index within that block. This class combines those two pieces of
   * information and provides the logic for moving forward and backward through the deque structure.
   *
   * <p>NOTE: The provided cursor class is *immutable*: once a Cursor object is created,
   * it cannot be modified. Incrementing forward or backward involves
   * creating new Cursor objects at the required location. Immutable objects can be
   * cumbersome to work with, but they prevent coding errors
   * caused by accidentally aliasing mutable objects.
   */
  private class Cursor {
    private final Block block;
    private final int index;

    public Cursor(HybridDeque<E>.Block block, int index) {
      this.block = block;
      this.index = index;
    }

    /**
     * Increment the cursor, crossing a block boundary if necessary.
     *
     * @return A new cursor at the next position, or null if there are no more valid positions.
     */
    private Cursor next() {
      if (index == BLOCK_SIZE - 1) { // We need to cross a block boundary
        if (block.next == null) {
          return null;
        } else {
          return new Cursor(block.next, 0);
        }
      } else { // Just move one spot forward in the current block
        return new Cursor(block, index + 1);
      }
    }

    /**
     * Decrement the cursor, crossing a block boundary if necessary.
     *
     * @return A new cursor at the previous position, or null if there is no previous position.
     */
    private Cursor prev() {
      if (index == 0) { // We need to cross a block boundary
        if (block.prev == null) {
          return null;
        } else {
          return new Cursor(block.prev, BLOCK_SIZE - 1);
        }
      } else { // Just move one spot back in the current block.
        return new Cursor(block, index - 1);
      }
    }

    /**
     * Two cursors are equal if they refer to the same index in the same block.
     */
    public boolean equals(Object other) {
      if (!(other instanceof HybridDeque.Cursor)) {
        return false;
      }
      @SuppressWarnings("unchecked")
      Cursor otherCursor = (Cursor) other;
      return otherCursor.block == block && otherCursor.index == index;
    }

    /**
     * Return the element stored at this cursor.
     */
    public E get() {
      return block.elements[index];
    }

    /**
     * Set the element at this cursor.
     */
    public void set(E item) {
      block.elements[index] = item;
    }

  }

  /**
   * Constructor for HybridDeque.
   *
   */
  public HybridDeque() {
    clear();
  }

  /**
   * How many items are in the Deque.
   */
  public int size() {
    return size;
  }

  /**
   * Creating an empty Deque.
   */
  public void clear() {
    // Block leftBlock = new Block(null, null);
    // rightBlock = leftBlock;
    leftCursor = new Cursor(new Block(null, null), CENTER + 1);
    rightCursor = new Cursor(leftCursor.block, CENTER);
    size = 0;
  }

  /**
   * Adds to the right of the last element. if the cursor is at the end of a block, create a new one
   * 1. change the rightBlock to the new one 2. set the previous block's next to the new block 3.
   * put the right cursor in new block 4. insert item otherwise, if there is space in the block, go
   * to the next index
   *
   */
  public boolean offerLast(E item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (rightCursor.index == BLOCK_SIZE - 1) { // if the back is full, go back a block
      Block prevBlock = rightCursor.block;
      rightCursor = new Cursor(new Block(prevBlock, null), 0);
      prevBlock.next = rightCursor.block;
      // update cursor
      rightCursor.set(item);
    } else { // otherwise, go to the next spot
      rightCursor = rightCursor.next();
      rightCursor.set(item);
    }
    size++;
    return true;
  }

  /**
   * Adds to the left of the first element. if the cursor is at the front of a block, create a new
   * one 1. change the leftBlock to the new one 2. put the left cursor in new block 3. insert item
   * otherwise, if there is space in the block, go to the next index (going backwards)
   *
   */
  public boolean offerFirst(E item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (leftCursor.index == 0) { // if the front is full, go back a block
      Block prevBlock = leftCursor.block;
      leftCursor = new Cursor(new Block(null, prevBlock), BLOCK_SIZE - 1);
      prevBlock.prev = leftCursor.block;
      // update cursor
      leftCursor.set(item);
    } else { // otherwise, go to the previous spot
      leftCursor = leftCursor.prev();
      leftCursor.set(item);

    }
    size++;
    return true;
  }

  /**
   * Get the first element.
   */
  public E peekFirst() {
    return leftCursor.get();
  }

  /**
   * Get last element.
   */
  public E peekLast() {
    return rightCursor.get();
  }

  /**
   * Removes the leftmost item.
   */
  public E pollFirst() {
    E removed = leftCursor.get();
    if (leftCursor.equals(rightCursor)) { // if removing the last item, clear the deque
      this.clear();
    } else {
      leftCursor.set(null);
      leftCursor = leftCursor.next();
      size--;
    }
    return removed;
  }

  /**
   * Removes the rightmost item.
   */
  public E pollLast() {
    E removed = rightCursor.get();
    if (leftCursor.equals(rightCursor)) { // if removing the last item, clear the deque
      this.clear();
    } else {
      rightCursor.set(null);
      rightCursor = rightCursor.prev();
      size--;
    }
    return removed;
  }

  /**
   * Check if two Dequeues are equal if they have the same size and the same items.
   *
   * @return boolean if equal
   */
  public boolean equals(Object obj) {
    if (obj instanceof HybridDeque) {
      @SuppressWarnings("unchecked")
      HybridDeque<E> other = (HybridDeque<E>) obj; // Both Deque

      if (this.size() == other.size()) {
        // check elements with iterator
        Iterator<E> thisIt = this.iterator();
        Iterator<E> otherIt = other.iterator();
        while (thisIt.hasNext()) {
          Object thisItem = thisIt.next();
          Object otherItem = otherIt.next();
          if (!thisItem.equals(otherItem)) {
            return false;
          }
        }
        return true;
      }
    }
    return false;
  }

  public Iterator<E> iterator() {
    return new HybridDequeIterator();
  }

  private class HybridDequeIterator implements Iterator<E> {
    // the cursor of the item previously called by next(),
    // prevents from using a null cursor to get the item from the left.
    private Cursor prevSpot;
    private Cursor cursor = leftCursor; // start at the leftmost spot
    boolean removeOk = false;


    @Override
    public boolean hasNext() {
      // want to check if after right cursor
      return cursor != null && !cursor.equals(rightCursor.next());
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      prevSpot = cursor;
      E item = cursor.get();
      cursor = cursor.next();
      removeOk = true;
      return item;
    }

    @Override
    public void remove() {
      if (!removeOk) {
        throw new IllegalStateException();
      }
      removeHelper(prevSpot); // remove the element we just received from next()
      cursor = prevSpot;
      removeOk = false;
    }


  }

  @Override
  public Iterator<E> descendingIterator() {
    return new DescendingIterator();
  }

  private class DescendingIterator implements Iterator<E> {
    // the cursor of the item previously called by next(),
    // prevents from using a null cursor to get the item from the right.
    private Cursor prevSpot;
    private Cursor cursor = rightCursor;
    boolean removeOk = false;

    @Override
    public boolean hasNext() {
      return cursor != null && !cursor.equals(leftCursor.prev());
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      prevSpot = cursor;
      E item = cursor.get();
      cursor = cursor.prev();
      removeOk = true;
      return item;
    }

    @Override
    public void remove() {
      if (!removeOk) {
        throw new IllegalStateException();
      }
      removeHelper(prevSpot); // remove the element we just received from next()
      removeOk = false;
    }

  }

  @Override
  public boolean removeFirstOccurrence(Object o) {
    HybridDequeIterator it = new HybridDequeIterator();
    while (it.hasNext()) {
      if (it.next().equals(o)) {
        it.remove();
        return true;
      }
    }
    return false;

  }

  @Override
  public boolean removeLastOccurrence(Object o) {
    DescendingIterator it = new DescendingIterator();
    while (it.hasNext()) {
      if (it.next().equals(o)) {
        it.remove();
        return true;
      }
    }
    return false;
  }

  /**
   * Helper method for all remove methods. Decrement size at the end.
   *
   * @param cursor where we are at in the HybridDeque
   */
  public void removeHelper(Cursor cursor) {
    if (size == 1) {
      cursor.set(null);
    } else {
      while (cursor.next() != null) {
        E nextItem = cursor.next().get();
        cursor.set(nextItem);
        cursor = cursor.next();
      }
    }
    rightCursor = rightCursor.prev();
    size--;
  }

}
