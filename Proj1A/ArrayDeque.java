public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.size);
    }

    @Override
    public void addFirst(T item){
        if (size == items.length){
            resize();
        }
        System.arraycopy(items, 0, items, 1, size);
        items[0] = item;
        size++;
    }

    @Override
    public void addLast(T item){
        if (size == items.length){
            resize();
        }
        items[size] = item;
        size++;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.print(items[i]+" ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        T item = items[0];
        System.arraycopy(items, 1, items, 0, size-1);
        items[size-1] = null;
        size--;
        return item;
    }

    @Override
    public T removeLast(){
        if (size == 0){
            return null;
        }
        T item = items[size-1];
        items[size-1] = null;
        size--;
        return item;
    }

    @Override
    public T get(int index){
        if (index > size-1){
            return null;
        }
        return items[index];
    }

    public void resize(){
        T[] newArray = (T[]) new Object[2 * items.length];
        System.arraycopy(items, 0, newArray, 0, size);
        items = newArray;
    }
}
