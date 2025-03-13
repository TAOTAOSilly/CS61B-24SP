import java.util.ArrayDeque;
import java.util.Comparator;


public class MaxArrayDeque61B<T> extends ArrayDeque<T>  {
    private Comparator<T> c;


    public MaxArrayDeque61B(Comparator<T> comparator){
        super();
        c = comparator;
    }

    public T max(){
        if (this.size() == 0){
            return null;
        }
        return max(this.c);
    }

    private T max(Comparator<T> c){
        T maxnow = this.getFirst();
        for ( T t : this){
           if(c.compare(t,maxnow) > 0){
               maxnow = t;
           }
            else {
                continue;
           }
        }
        return maxnow;
    }
}
