import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {


    private class RandomizedQueueIterator<RQItem> implements Iterator<Item> {

        private Item[] arrayCopy;
        private int size;

        public RandomizedQueueIterator(Item[] array, int size) {
          this.size = size;
          this.arrayCopy = (Item[]) new Object[size];
          for (var i = 0; i < size; i++) {
            this.arrayCopy[i] = array[i];
          }

          StdRandom.shuffle(this.arrayCopy);
        }

        public boolean hasNext() {
            return this.size > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }

        public Item next() {
          if (this.size == 0) {
            throw new NoSuchElementException("No more elements to iterate");
          }
          this.size--;
          return this.arrayCopy[this.size];
        }
        
    }

  private Item[] array = null;
  private int size = 0;
  
  // construct an empty randomized queue
  public RandomizedQueue() {
    this.array = (Item[]) new Object[1];
  }

  private void resize(int n) {
    
    var localArray = (Item[]) new Object[n];
    for (var i = 0; i < this.array.length; i++) {
      localArray[i] = this.array[i];
    }
    this.array = localArray;
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return this.size == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return this.size;
  }

  // add the item
  public void enqueue(Item item) {
      if (item == null) {
        throw new IllegalArgumentException("Item cannot be null");
    }

    if (this.size == this.array.length) {
      resize(this.array.length * 2);
    }

    this.array[this.size++] = item;
  }

  // remove and return a random item
  public Item dequeue() {
    if (this.size == 0) {
      throw new NoSuchElementException("Queue is empty");
    }

    var random = StdRandom.uniformInt(this.size);
    var result = this.array[random];

    for (var i = random; i < this.size-1; i++) {
      this.array[i] = this.array[i+1];
    }
    this.size--;

    return result;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    var random = StdRandom.uniformInt(this.size);
    
    return this.array[random];
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator<Item>(this.array, this.size);
  }

  // unit testing (required)
  public static void main(String[] args) {
    var rq = new RandomizedQueue<Integer>();
    rq.enqueue(1);
    rq.enqueue(2);
    rq.enqueue(3);
    rq.enqueue(4);
    rq.enqueue(5);

    for (Integer integer : rq) {
      StdOut.println(integer);
    }
  }
}
