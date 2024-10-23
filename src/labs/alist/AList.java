package labs.alist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;

/**
 * Array-based List implementation.
 *
 */
/*
 * OpenDSA Project Distributed under the MIT License
 *
 * Copyright (c) 2011-2023 - Ville Karavirta and Cliff Shaffer
 */

class AList<E> implements List<E> {

  protected E[] listArray; // Array holding list elements
  // Protected instead of private to facilitate testing/grading.

  private static final int DEFAULT_SIZE = 10; // Default size
  private int listSize; // Current # of list items
  private int curr; // Position of current element


  /**
   * Create a new list object with maximum size "size".
   *
   * @param size Initial array size.
   */
  @SuppressWarnings("unchecked") // Generic array allocation
  public AList(int size) {
    listSize = curr = 0;
    listArray = (E[]) new Object[size];
  }

  /**
   * Create a list with the default capacity.
   */
  public AList() {
    this(DEFAULT_SIZE); // Just call the other constructor
  }

  /**
   * Reinitialize the list.
   */
  public void clear() {
    listSize = curr = 0;
  }

  /**
   * Insert item at current position.
   *
   * @param it The item to insert
   */
  public boolean insert(E it) {
    if (listSize >= listArray.length) {
      dynamicExpand();
    }
    for (int i = listSize; i > curr; i--) { // Shift elements up
      listArray[i] = listArray[i - 1]; // to make room
    }
    listArray[curr] = it;
    listSize++; // Increment list size
    return true;
  }

  /**
   * Append item to list.
   *
   * @param it The item to append
   */
  public boolean append(E it) {
    if (listSize >= listArray.length) {
      dynamicExpand();
    }
    listArray[listSize++] = it;
    return true;
  }

  // if the current array is full:
  // * Allocate a new array that is twice as large as the current array.
  // * Copy all entries from the current array to the new array.
  // * Replace the current array with the newly allocated array.
  private void dynamicExpand() {
    E[] newArray = (E[]) new Object[listArray.length * 2];
    for (int i = 0; i < listSize; i++) {
      newArray[i] = listArray[i];
    }
    listArray = newArray;


  }

  /**
   * Remove and return the current element.
   */
  public E remove() throws NoSuchElementException {
    if ((curr < 0) || (curr >= listSize)) { // No current element
      throw new NoSuchElementException("remove() in AList has current of " + curr + " and size of "
          + listSize + " that is not a a valid element");
    }
    if (listSize <= listArray.length / 4) {
      dynamicRemove();

    }
    E it = listArray[curr]; // Copy the element
    for (int i = curr; i < listSize - 1; i++) { // Shift them down
      listArray[i] = listArray[i + 1];
    }
    listSize--; // Decrement size
    return it;
  }

  private void dynamicRemove() {
    int newLength = listArray.length - (listArray.length / 4);
    E[] newArray = (E[]) new Object[newLength];
    for (int i = 0; i < listSize; i++) {
      newArray[i] = listArray[i];
    }
    listArray = newArray;
  }

  /**
   * Set position to front.
   */
  public void moveToStart() {
    curr = 0;
  }

  /**
   * Set position to end.
   */
  public void moveToEnd() {
    curr = listSize;
  }

  /**
   * Move position left.
   */
  public void prev() {
    if (curr != 0) {
      curr--;
    }
  }

  /**
   * Move position right.
   */
  public void next() {
    if (curr < listSize) {
      curr++;
    }
  }

  /**
   * Return the list size.
   */
  public int length() {
    return listSize;
  }

  /**
   * Return the current position.
   */
  public int currPos() {
    return curr;
  }

  /**
   * Set the current position to the specified value.
   *
   * @return false if the position is invalid.
   */
  public boolean moveToPos(int pos) {
    if ((pos < 0) || (pos > listSize)) {
      return false;
    }
    curr = pos;
    return true;
  }


  /**
   * Return true if the position is at the end of the list.
   */
  public boolean isAtEnd() {
    return curr == listSize;
  }

  /**
   * Return the element at the current position.
   */
  public E getValue() throws NoSuchElementException {
    if ((curr < 0) || (curr >= listSize)) { // No current element
      throw new NoSuchElementException("getvalue() in AList has current of " + curr
          + " and size of " + listSize + " that is not a a valid element");
    }
    return listArray[curr];
  }

  /**
   * Return true if the list is empty.
   */
  public boolean isEmpty() {
    return listSize == 0;
  }

}
