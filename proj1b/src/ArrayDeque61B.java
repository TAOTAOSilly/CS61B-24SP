import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    T[] base;
    int currentsize = 8;
    int nextfirst = 0;
    int nextlast = 0;
    int size = 0;

    public ArrayDeque61B(){
        base = (T[]) new Object[currentsize];
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