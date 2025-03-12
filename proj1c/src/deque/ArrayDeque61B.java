package deque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T>,Iterable<T> {

    T[] base;
    int currentsize = 8;
    int nextfirst = 0;
    int nextlast = 0;
    int size = 0;

    public ArrayDeque61B(){
        base = (T[]) new Object[currentsize];
    }

    private class ArrayDeque61BIterator implements Iterator<T>{
        int WizPos;

        public ArrayDeque61BIterator(){
            WizPos = 0;
        }

        @Override
        public boolean hasNext() {
            if (size == 0){
                return false;
            }
            return WizPos <= size - 1;
        }

        @Override
        public T next() {
            T returnitem = base[WizPos];
            WizPos += 1;
            return returnitem;
        }
    }
    @Override
    public Iterator<T> iterator(){
        return new ArrayDeque61BIterator();
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
        if (o instanceof ArrayDeque61B other ){
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

    public void addFirst(T x) {
        this.size += 1;
        this.resize_helperbigger();
        this.base[nextfirst] = x;
        if (nextfirst == 0){
            nextlast += 1;
            nextlast = Math.floorMod(nextlast,currentsize);
        }
        nextfirst -= 1;
        nextfirst = Math.floorMod(nextfirst,currentsize);
    }

    @Override
    public void addLast(T x) {
        size += 1;
        this.resize_helperbigger();
        this.base[nextlast] = x;
        if (nextlast == 0){
            nextfirst -= 1;
            nextfirst = Math.floorMod(nextfirst,currentsize);
        }
        nextlast += 1;
        nextlast = Math.floorMod(nextlast,currentsize);
    }

    public void resize_helperbigger(){
        if (this.size > currentsize) {
            T[] thecopy = (T[]) new Object[currentsize * 2];

            for (int i = 0;i < currentsize ;i ++){
                int flag = nextfirst + 1 + i;
                flag = Math.floorMod(flag,currentsize);
                thecopy[i] = this.base[flag];
            }
            this.currentsize = currentsize * 2;
            this.nextfirst = Math.floorMod( - 1,currentsize);
            this.nextlast = this.size() - 1;
            this.base = thecopy;
        }
    }
    public void resize_helpersmaller(){
        if (this.size < currentsize / 4) {
            T[] thecopy = (T[]) new Object[currentsize / 2];

            for (int i = 0;i <= this.size ;i ++){
                int flag = nextfirst + 1 + i;
                flag = Math.floorMod(flag,currentsize);
                thecopy[i] = this.base[flag];
            }
            this.currentsize = currentsize / 2;
            this.nextfirst = Math.floorMod( - 1,currentsize);
            this.nextlast = this.size() + 1 ;
            this.base = thecopy;
        }
    }
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < currentsize; i++) {
            int flag = nextfirst + 1 + i;
            flag = Math.floorMod(flag,currentsize);
            returnList.add(this.get(flag));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0)
            return false;
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (this.size == 0){
            return null;
        }
        size -= 1;
        resize_helpersmaller();
        nextfirst += 1;
        nextfirst = Math.floorMod(nextfirst,currentsize);
        T removed_value = this.base[nextfirst];
        this.base[nextfirst] = null;

        return removed_value;
    }

    @Override
    public T removeLast() {
        if (this.size == 0){
            return null;
        }
        size -= 1;
        resize_helpersmaller();
        nextlast -= 1;
        nextlast = Math.floorMod(nextlast,currentsize);
        T removed_value = this.base[nextlast];
        this.base[nextlast] = null;

        return removed_value;
    }

    @Override
    public T get(int index) {
        if (index >= 0 &&index < currentsize)
            return this.base[index];
        else {
            return null;
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

}
