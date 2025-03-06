import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    public class Node{
        public Node prev;
        public Node next;
        public T item;

        public Node(Node tprev ,T t,Node tnext){
                prev = tprev;
                next = tnext;
                item = t;
        }
    }

    int size = 0;
    private Node sentinel;

    public LinkedListDeque61B() {
        sentinel = new Node(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    // sentinel.prev == last Node;
    @Override
    public void addFirst(T x) {
        size += 1;
        Node l = sentinel.next;

        sentinel.next = new Node(sentinel,x,l);
        l.prev = sentinel.next;
    }

    @Override
    public void addLast(T x) {
        size += 1;
        Node l = sentinel.prev;
        l.next = new Node(l,x,sentinel);
        sentinel.prev = l.next;
    }

    @Override
    public List<T> toList() {
        List<T> returnlist = new ArrayList<>();
        Node p = this.sentinel;
        while (p.next != sentinel){
            p = p.next;
            returnlist.add(p.item);
        }
        return returnlist;
    }

    @Override
    public boolean isEmpty() {
        Node p = this.sentinel;
        if (p.next == sentinel){
            return true;
        }

        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
