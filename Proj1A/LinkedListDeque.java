/* 双端链表并不是双向链表，其特点是第一个链结点与最后一个链结点直接相连 */
public class LinkedListDeque<T>{
    private Node sentinel;
    private int size;

    private class Node{
        private T item;
        private Node prev;
        private Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    
    public LinkedListDeque(){
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel.next = sentinel;
        Node p = other.sentinel.next;
        while (p != other.sentinel){
            addFirst(p.item);
            p = p.next;
        }
        size = other.size;
    }

    public void addFirst(T item){
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item){
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if (isEmpty()){
            return;
        }
        Node p = sentinel.next;
        while (p != sentinel.prev){
            System.out.print(p.item+" ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        T t = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return t;
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        T t = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return t;
    }

    public T get(int index){
        if (index > size){ // 包括了isEmpty()
            return null;
        }
        Node p = sentinel.next;
        while (index > 0){
            p = p.next;
            index--;
        }
        return p.item;
    }

    public T getRecursive(int index){
        if (index > size-1){
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node s, int index){
        if (index == 0){
            return s.item;
        }
        return getRecursive(s.next, index--);
    }

}
