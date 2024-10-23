package labs.lqueue;

import java.util.NoSuchElementException;

/*
 * OpenDSA Project Distributed under the MIT License
 *
 * Copyright (c) 2011-2019 - Ville Karavirta and Cliff Shaffer
 */

class AQueue<E> implements Queue<E> {

  // Keep this non-private for testing purposes!
  E queueArray[]; // Array holding queue elements

  private static final int defaultSize = 10;
  private int front; // Index of front element
  private int size; // Number of elements stored.

  // Constructors
  @SuppressWarnings("unchecked") // Generic array allocation
  AQueue(int capacity) {
    front = 0;
    size = 0;
    queueArray = (E[]) new Object[capacity]; // Create queueArray
  }

  AQueue() {
    clear();
  }

  // Reinitialize
  @SuppressWarnings("unchecked")
  public void clear() {
    front = 0;
    size = 0;
    queueArray = (E[]) new Object[defaultSize];
  }

  // Put "it" in queue
  public boolean enqueue(E it) {
    // if the array is full, return false
    if (size == queueArray.length) {
      return false;
    }
    // if at the end or rear is behind front, add from the front
    if (front + size > queueArray.length - 1) {
      int rear = (front + size) % queueArray.length;
      queueArray[rear] = it;
    } else { // otherwise, add to the end
      queueArray[front + size] = it;
    }
    size++;
    return true;
  }

  // Remove and return front value
  public E dequeue() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    E removed = frontValue();
    if (front == queueArray.length - 1) {
      queueArray[front] = null;
      front = 0;
    } else {
      queueArray[front] = null;
      front++;
    }
    size--;
    return removed;

  }

  // Return front value
  public E frontValue() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    return queueArray[front];
  }

  // Return queue size
  public int length() {
    return size;
  }

  //Check if the queue is empty
  public boolean isEmpty() {
    return size == 0;
  }
}
