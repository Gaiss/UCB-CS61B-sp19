/* 双端队列是指允许两端都可以进行入队和出队操作的队列，其元素的逻辑结构仍是线性结构 */
public class ArrayDeque<T> implements Deque<T>{
 /*   private T[] items;
    private int size;
    private int nextFirst; // 指向该队列的下一个头插位置
    private int nextLast; // 指向该队列的下一个尾插位置

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.items.length];
        //System.arraycopy(other.items, other.nextFirst, items, 0, other.size);
        for (int i = 0; i < other.size; i++){
            items[i] = (T) other.items[(i+other.nextFirst+1) % items.length];
        }
        size = other.size;
        nextFirst = 0;
        nextLast = size + 1;
    }

    @Override
    public void addFirst(T item){
        if (size == items.length){
            resize();
        }
        nextFirst = (nextFirst - 1) % items.length;
        items[nextFirst] = item;
        size++;
    }

    @Override
    public void addLast(T item){
        if (size == items.length){
            resize();
        }
        nextLast = (nextLast + 1) % items.length;
        items[nextLast] = item;
        size++;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for (int i = 1; i != size; i++){
            System.out.print(items[(i+nextFirst) % items.length]+" ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        nextFirst = (++nextFirst) % items.length;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return item;
    }

    @Override
    public T removeLast(){
        if (size == 0){
            return null;
        }
        nextLast = (--nextLast) % items.length;
        T item = items[nextLast];
        items[nextLast] = null;
        size--;
        return item;
    }

    @Override
    public T get(int index){
        if (index > nextLast-1 && index < nextFirst+1){
            return null;
        }
        return items[(nextFirst+1+index) % items.length];
    }

    public void resize(){
        T[] newArray = (T[]) new Object[2 * items.length];
        for (int i = 1; i != size; i++){
            newArray[(nextFirst+i) % newArray.length] = items[(nextFirst+i) % items.length];
        }
        nextLast = (nextFirst + size + 1) % items.length;
        items = newArray;
    }
}
  */
 //public class ArrayDeque<T> implements Deque<T> {

     private T[] items;
     private int nextFirst;
     private int nextLast;
     private int size;

     /**
      * Create an empty ArrayDeque.
      */
     public ArrayDeque() {
         // Java does not allow to create new generic array directly. So need cast.
         items = (T[]) new Object[8];
         nextFirst = 0;
         nextLast = 1;
         size = 0;
     }

     /**
      * Return true if deque is full, false otherwise.
      */
     private boolean isFull() {
         return size == items.length;
     }

     /**
      * Whether to downsize the deque.
      */
     private boolean isSparse() {
         return items.length >= 16 && size < (items.length / 4);
     }

     /**
      * Add one circularly.
      */
     private int plusOne(int index) {
         return (index + 1) % items.length;
     }

     /**
      * Minus one circularly.
      */
     private int minusOne(int index) {
         // unlike Python, in Java, the % symbol represents "remainder" rather than "modulus",
         // therefore, it may give negative value, so + items.length is necessary,
         // or to use Math.floorMod(x, y)
         return (index - 1 + items.length) % items.length;
     }

     /**
      * Resize the deque.
      */
     private void resize(int capacity) {
         T[] newDeque = (T[]) new Object[capacity];
         int oldIndex = plusOne(nextFirst); // the index of the first item in original deque
         for (int newIndex = 0; newIndex < size; newIndex++) {
             newDeque[newIndex] = items[oldIndex];
             oldIndex = plusOne(oldIndex);
         }
         items = newDeque;
         nextFirst = capacity - 1; // since the new deque is starting from true 0 index.
         nextLast = size;

     }

     /**
      * Upsize the deque.
      */
     private void upSize() {
         resize(size * 2);
     }

     /**
      * Downsize the deque
      */
     private void downSize() {
         resize(items.length / 2);
     }

     /**
      * Return true if deque is empty, false otherwise.
      */
     @Override
     public boolean isEmpty() {
         return size == 0;
     }

     /**
      * Return the number of items in the deque.
      */
     @Override
     public int size() {
         return size;
     }

     /**
      * Print the items in the deque from first to last, separated by a space.
      * Once all the items have been printed, print out a new line.
      */
     @Override
     public void printDeque() {
         for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
             System.out.print(items[i] + " ");
         }
         System.out.println();
     }

     /**
      * Add an item of type Item to the front of the deque.
      */
     @Override
     public void addFirst(T x) {
         if (isFull()) {
             upSize();
         }
         items[nextFirst] = x;
         nextFirst = minusOne(nextFirst);
         size += 1;
     }

     /**
      * Add an item of type Item to the back of deque.
      */
     @Override
     public void addLast(T x) {
         if (isFull()) {
             upSize();
         }
         items[nextLast] = x;
         nextLast = plusOne(nextLast);
         size += 1;
     }

     /**
      * Remove and return the item at the front of the deque.
      * If no such item exist, return null.
      */
     @Override
     public T removeFirst() {
         if (isSparse()) {
             downSize();
         }
         nextFirst = plusOne(nextFirst);
         T toRemove = items[nextFirst];
         items[nextFirst] = null;
         if (!isEmpty()) {
             size -= 1;
         }
         return toRemove;
     }

     /**
      * Remove and return the item at the back oc the deque.
      * If no such item exist, return null.
      */
     @Override
     public T removeLast() {
         if (isSparse()) {
             downSize();
         }
         nextLast = minusOne(nextLast);
         T toRemove = items[nextLast];
         items[nextLast] = null;
         if (!isEmpty()) {
             size -= 1;
         }
         return toRemove;
     }

     /**
      * Get the item at the given index, where 0 is the front,
      * 1 is the next item, and so forth. If no such item exists,
      * returns null. Must not alter the deque.
      */
     @Override
     public T get(int index) {
         if (index >= size) {
             return null;
         }
         int start = plusOne(nextFirst);
         return items[(start + index) % items.length];
     }

     /**
      * Create a deep copy of other.
      */
     public ArrayDeque(ArrayDeque other) {
         items = (T[]) new Object[other.size()];
         nextFirst = other.nextFirst;
         nextLast = other.nextLast;
         size = other.size;

         System.arraycopy((T[]) other.items, 0, items, 0, other.size);
     }




















 }
