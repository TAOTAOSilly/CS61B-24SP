import java.util.List;
import java.util.Objects;
import java.lang.Math;

    public class ArrayDeque61B<T> implements Deque61B<T> {
        T[] base;
        int orisize = 8;
        int nextfirst = 0;
        int nextlast = 0;
        int size = 0;

        public ArrayDeque61B(){
            base = (T[]) new Object[orisize];
        }


        public void addFirst(T x) {
            size += 1;
            this.base[nextfirst] = x;
            if (nextfirst == 0){
                nextlast += 1;
                nextlast = Math.floorMod(nextlast,orisize);
            }
            nextfirst -= 1;
            nextfirst = Math.floorMod(nextfirst,orisize);
        }

        @Override
        public void addLast(T x) {
            size += 1;
            this.base[nextlast] = x;
            if (nextlast == 0){
                nextfirst -= 1;
                nextfirst = Math.floorMod(nextfirst,orisize);
            }
            nextlast += 1;
            nextlast = Math.floorMod(nextlast,orisize);
        }

        @Override
    public List<T> toList() {
        return List.of();
    }

        @Override
        public boolean isEmpty() {
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