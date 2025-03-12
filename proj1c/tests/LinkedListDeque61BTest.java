import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public class LinkedListDeque61BTest{

    @Test
    //test iterator
    public void IteratorTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        for (int i : lld1){
            System.out.println(i);
        }
    }

    @Test
    public void testequals(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);

        Deque61B<Integer> ad2 = new ArrayDeque61B<>();
        ad2.addLast(1);
        ad2.addLast(2);
        ad2.addLast(3);
        assertThat(ad1.equals(ad2)).isTrue();
    }

    @Test
    public void testtoString(){
        Deque61B<Integer> ad1 = new LinkedListDeque61B();
        assertThat(ad1.toString()).isEqualTo("{}");
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        assertThat(ad1.toString()).isEqualTo("{1,2,3}");
    }
}
