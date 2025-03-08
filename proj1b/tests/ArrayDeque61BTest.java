import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {
    int i = 8;

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    //test add first and last
    public void testaddand_isEmpty_size(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addFirst(7);
        lld1.addLast(3);//1,2,3,null,null,null,null,7
        assertThat(lld1.isEmpty()).isFalse();
        assertThat(lld1.size()).isEqualTo(4);
        assertThat(lld1.toList()).containsExactly(7,1,2,3,null,null,null,null).inOrder();
    }

    @Test
    //remove
    public  void testremove(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.removeLast()).isEqualTo(null);
        assertThat(lld1.removeFirst()).isEqualTo(null);
        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addFirst(7);
        lld1.addLast(3);//1,2,3,null,null,null,null,7
        lld1.removeFirst();
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(null,1,2,null,null,null,null,null);
    }

    @Test
    //test resize
    public void test_resize(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        for (int i = 0; i <= 8; i++){
            lld1.addLast(i);
        }
        assertThat(lld1.toList()).containsExactly(0,1,2,3,4,5,6,7,8,null,null,null,null,null,null,null).inOrder();
    }

    @Test
    public void test_resizesmall(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        for (int i = 0; i <= 8; i++){
            lld1.addLast(i);
        }
        for (int i = 8;i >= 3; i--){
            lld1.removeLast();
        }
        assertThat(lld1.toList()).containsExactly(0,1,2,null,null,null,null,null).inOrder();
    }
}