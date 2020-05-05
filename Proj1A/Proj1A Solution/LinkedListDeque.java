/**
 *  A LinkedListDeque.
 *
 */
public class LinkedListDeque<T> {

  private Node sentinel;
  private int size;


  /**
   * A nested class, indicates a node object of a LinkedListDeque.
   */
  private class Node {
    //Three instance variables, indicate the current data in the node and pointers to previous Node and Next Node
    private T item;
    private Node prev;
    private Node next;

    /**
     * IntNode constructor.
     * @param i an integer, indicates the data in this IntNode
     * @param n an IntNode object, indicates the reference of next IntNode
     */
    public Node(T i, Node p, Node n) {
      item = i;
      prev = p;
      next = n;
    }
  }

  /**
   * Constructor of LinkedListDeque object.
   */
  public LinkedListDeque() {
    sentinel = new Node(null, null, null);
    sentinel.prev = sentinel;
    sentinel.next = sentinel;
  }

  /**
   * Adds an item of type T to the front of the deque.
   * @param item a T type object, indicates the item which will be added
   */
  public void addFirst(T item) {
    sentinel.next = new Node(item, sentinel, sentinel.next);
    sentinel.next.next.prev = sentinel.next;
    size++;
  }

  /**
   * Adds an item of type T to the back of the deque.
   * @param item a T type object, indicates the item which will be added
   */
  public void addLast(T item) {
    sentinel.prev = new Node(item, sentinel.prev, sentinel);
    sentinel.prev.prev.next = sentinel.prev;
    size++;
  }

  /**
   * Returns true if deque is empty, false otherwise.
   * @return a boolean value, indicates whether the deque is empty or not
   */
  public boolean isEmpty() {
    if (size > 0)
      return false;
    return true;
  }

  /**
   * Returns the number of items in the deque.
   * @return an integer, indicates the number of all items
   */
  public int size() {
    return size;
  }

  /**
   * Prints the items in the deque from first to last, separated by a space.
   */
  public void printDeque() {
    Node p = sentinel.next;

    //Print each Node's value and change p to point at next Node
    while (p != sentinel) {
      System.out.print(p.item + " ");
      p = p.next;
    }

    return;
  }

  /**
   * Removes and returns the item at the front of the deque. If no such item exists, returns null.
   * @return a T object, indicates the front Node's item value
   */
  public T removeFirst() {
    if (sentinel.next == null) {
      return null;
    }

    T result = sentinel.next.item;
    sentinel.next = sentinel.next.next;
    sentinel.next.prev = sentinel;
    size--;
    return result;
  }

  /**
   * Removes and returns the item at the back of the deque. If no such item exists, returns null.
   * @return a T object, indicates the front Node's item value
   */
  public T removeLast() {
    if (sentinel.prev == null) {
      return null;
    }

    T result = sentinel.prev.item;
    sentinel.prev = sentinel.prev.prev;
    sentinel.prev.next = sentinel;
    size--;
    return result;
  }

  /**
   * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
   * If no such item exists, returns null. Must not alter the deque!
   * @param index an integer, indicates the index of the item.
   * @return a T type object, indicates the item we want to get
   */
  public T get(int index) {
    //First check if the specified item is exist
    //If not, return null
    if (index > size) {
      return null;
    }

    //Initialize a Node object to traverse the list
    Node p = sentinel;

    //Find the specified element
    while (index != -1) {
      p = p.next;
      index--;
    }

    return p.item;
  }

  /**
   * Same as get, but uses recursion.
   * @param index an integer, indicates the index of the item.
   * @return a T type object, indicates the item we want to get
   */
  public T getRecursive(int index) {
    //First check if the specified item is exist
    //If not, return null
    if (index > size) {
      return null;
    }

    return getRecursiveHelper(sentinel.next, index).item;
  }

  /**
   * A helper function for recursive get method.
   * @param current
   * @param index
   * @return
   */
  public Node getRecursiveHelper(Node current, int index) {

    //Base case
    if (index == 0) {
      return current;
    }

    //Recursive cases
    return getRecursiveHelper(current.next, index - 1);

  }
  /**
   * Main function of LinkedListDeque class, use for testing.
   * @param args
   */
  public static void main(String[] args) {
    LinkedListDeque<Integer> test = new LinkedListDeque<>();

    if (test.isEmpty()) {
      System.out.println("The list is now empty!");
    }
    else {
      System.out.println("The list is now not empty!");
    }

    test.addFirst(4);
    System.out.println("list's current size is " + test.size());
    System.out.println("The first Node's value is " + test.get(0));
    test.addLast(16);
    System.out.println("list's current size is " + test.size());
    System.out.println("The first Node's value is " + test.get(1));

    if (test.isEmpty()) {
      System.out.println("The list is now empty!");
    }
    else {
      System.out.println("The list is now not empty!");
    }

    test.printDeque();

    test.removeFirst();
    System.out.println();
    System.out.println("list's current size is " + test.size());
    System.out.println("The first Node's value is " + test.get(0));

    test.addLast(888);
    test.addLast(777);
    test.addLast(666);
    test.removeLast();

    System.out.println("list's current size is " + test.size());
    System.out.println("The first Node's value is " + test.get(2));
    System.out.println("The first Node's value is " + test.getRecursive(2));


  }
}
