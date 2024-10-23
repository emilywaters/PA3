package labs.lqueue;

/*
 * OpenDSA Project Distributed under the MIT License
 *
 * Copyright (c) 2011-2019 - Ville Karavirta and Cliff Shaffer
 */

public interface Queue<E> { // Queue class ADT
  // Reinitialize queue
  public void clear();

  // Put element on rear
  public boolean enqueue(E it);

  // Remove and return element from front
  // Throws NoSuchElementException if the queue is empty.
  public E dequeue();

  // Return front element
  //Throws NoSuchElementException if the queue is empty.
  public E frontValue();

  // Return queue size
  public int length();

  //Tell if the queue is empty or not
  public boolean isEmpty();
}
