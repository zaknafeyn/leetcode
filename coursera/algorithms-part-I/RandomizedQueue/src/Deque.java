import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class DequeIterator<DequeItem> implements Iterator<Item> {

        private Node firstNode = null;
        public DequeIterator(Node firstNode) {
            this.firstNode = firstNode;
        }

        public boolean hasNext() {
            return this.firstNode != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }

        public Item next() {
            if (this.firstNode == null) {
                throw new NoSuchElementException("No more elements to iterate");
            }

            var item = this.firstNode.item;
            this.firstNode = this.firstNode.next;
            return item;
        }
        
    }

    private Node firstNode = null;
    private Node lastNode = null;
    private int size = 0;

    // construct an empty deque
    public Deque() { }

    // is the deque empty?
    public boolean isEmpty() {
        return this.firstNode == null;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        var oldFirst = this.firstNode;
        this.firstNode = new Node(item, null, oldFirst);
        if (oldFirst == null) {
            this.lastNode = this.firstNode;
        } else {
            oldFirst.prev = this.firstNode;
        }
        
        this.size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        var oldLast = this.lastNode;
        this.lastNode = new Node(item, oldLast, null);
        if (oldLast == null) {
            this.firstNode = this.lastNode;
        } else {
            oldLast.next = this.lastNode;
        }

        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.firstNode == null) {
            throw new NoSuchElementException("Deque is empty");
        }

        this.size--;
        var item = this.firstNode.item;

        if (this.firstNode.next == null) {
            this.firstNode = null;
            this.lastNode = null;
        } else {
            this.firstNode = this.firstNode.next;
            this.firstNode.prev = null;
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.firstNode == null) {
            throw new NoSuchElementException("Deque is empty");
        }

        this.size--;
        var item = this.lastNode.item;

        if (this.lastNode.prev == null) {
            this.firstNode = null;
            this.lastNode = null;
        } else {
            this.lastNode = this.lastNode.prev;
            this.lastNode.next = null;
        }

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(this.firstNode);
    }

    // unit testing (required)
    public static void main(String[] args) {
        var deque = new Deque<Integer>();
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addLast(4);
        deque.addLast(5);
        deque.addFirst(1);

        for (Integer integer : deque) {
            StdOut.println(integer);
        }
        

        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        // deque.removeFirst();
        // deque.removeFirst();
        var iterator = deque.iterator();
        iterator.next();
        iterator.next();

        for (Integer integer : deque) {
            StdOut.println(integer);
        }
    }

}
