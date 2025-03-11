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
}
