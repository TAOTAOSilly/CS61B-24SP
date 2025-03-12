import deque.Deque61B;
import deque.ArrayDeque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public class ArrayDeque61BTest {
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
    public void testtoString_nullandsmall(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        assertThat(ad1.toString()).isEqualTo("{}");
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.toString();
        assertThat(ad1.toString()).isEqualTo("{1,2,3}");
    }
    @Test
    public void testHugeone(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        for (int i = 0;i <= 10000; i++){
            ad1.addLast(i);
        }
        ad1.toString();
    }
}
