package deque;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T>,Iterable<T> {
    public class Node {
        public Node prev;
        public Node next;
        public T item;

        public Node(Node tprev, T t, Node tnext) {
            prev = tprev;
            next = tnext;
            item = t;
        }
    }


    public class LinkedlistDeque61BIterator implements Iterator<T> {
        int WizPos;
        Node WizPointer;

        public LinkedlistDeque61BIterator() {
            WizPos = 1;
            WizPointer = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return WizPos <= size;
        }

        @Override
        public T next() {
            T returnitem = WizPointer.item;
            WizPointer = WizPointer.next;
            WizPos += 1;
            return returnitem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedlistDeque61BIterator();
    }


    int size = 0;
    private Node sentinel;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public boolean contains(T t){
        for (T i : this){
            if (i == t) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof LinkedListDeque61B other ){
            if (other.size != this.size){
                return false;
            }
            for (T t : this){
                if (!other.contains(t)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String returnString = "{";
        for (T t : this){
            returnString += t.toString();
            returnString += ",";
        }
        returnString += "}";
        if (returnString.length() != 2) {
            StringBuilder sb = new StringBuilder(returnString);
            sb.deleteCharAt(returnString.length() - 2);
            returnString = sb.toString();
        }
        System.out.print(returnString);
        return returnString;
    }
    // sentinel.prev == last Node;
    @Override
    public void addFirst(T x) {
        size += 1;
        Node l = sentinel.next;
        sentinel.next = new Node(sentinel, x, l);
        l.prev = sentinel.next;
    }

    @Override
    public void addLast(T x) {
        size += 1;
        Node l = sentinel.prev;
        l.next = new Node(l, x, sentinel);
        sentinel.prev = l.next;
    }

    @Override
    public List<T> toList() {
        List<T> returnlist = new ArrayList<>();
        Node p = this.sentinel;
        while (p.next != sentinel) {
            p = p.next;
            returnlist.add(p.item);
        }
        return returnlist;
    }

    @Override
    public boolean isEmpty() {
        Node p = this.sentinel;
        if (p.next == sentinel) {
            return true;
        }

        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        Node p = this.sentinel.next;
        T removedone = p.item;
        p = p.next;
        sentinel.next = p;
        p.prev = sentinel;
        return removedone;
    }

    @Override
    public T removeLast() {
        Node p = this.sentinel.prev;
        T removedone = p.item;
        p = p.prev;
        p.next = sentinel;
        sentinel.prev = p;
        return removedone;
    }

    @Override
    public T get(int index) {
        if (index > this.size) {
            System.out.print("the list have not the value to the index");
            return null;
        }
        Node p = this.sentinel;
        int pindex = 0;
        while (true) {
            if (pindex == index) {
                return p.item;
            }
            p = p.next;
            pindex += 1;
        }
    }

    @Override
    public T getRecursive(int index) {
        Node p = this.sentinel;
        return getRecusivehelper(p, index);
    }

    public T getRecusivehelper(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecusivehelper(p.next, index - 1);
    }
}


