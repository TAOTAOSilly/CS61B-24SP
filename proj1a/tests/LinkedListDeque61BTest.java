import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

     @Test
     public void hugeaddfirstandlasttest(){
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         long starttime = System.currentTimeMillis();
         for (int i = 0; i < 100000; i++){
             lld1.addFirst(i);
         }
         long endtime = System.currentTimeMillis();
         assertWithMessage("time is too long").that(endtime - starttime).isLessThan(1000);
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    // to test .isempty()
    public void isEmptytest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B();
        assertWithMessage("the list is empty").that(lld1.isEmpty()).isTrue();

        lld1.addFirst(6);
        assertWithMessage("the list is not empty").that(lld1.isEmpty()).isFalse();
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++){
            lld1.addFirst(i);
        }
        assertWithMessage("the list is not empty").that(lld1.isEmpty()).isFalse();
        long endtime = System.currentTimeMillis();
        assertWithMessage("the time is too long").that(starttime - endtime).isLessThan(1000);
    }

    @Test
    //the size
    public void Sizetest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);
        for (int i = 0; i < 100000; i++){
            lld1.addFirst(i);
        }
        assertThat(lld1.size()).isEqualTo(100000);
    }

    @Test
    //test remove first and last
    public void Removetest()
    {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("the return first value should be null").that(lld1.removeFirst()).isEqualTo(null);
        assertWithMessage("the return last value should be null").that(lld1.removeLast()).isEqualTo(null);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4); // [4,3,2]
        lld1.addLast(5); //[4,3,2,5]
        assertWithMessage("the return first value should be 4").that(lld1.removeFirst()).isEqualTo(4);
        assertWithMessage("the return first value should be 5").that(lld1.removeLast()).isEqualTo(5);
        assertWithMessage("the struct be borken").that(lld1.toList()).containsExactly(3,2).inOrder();

    }

    @Test
    // test get();
    public void  Get_test(){
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4); // [4,3,2]
        lld1.addLast(5); //[4,3,2,5]
        assertWithMessage("the index is too big").that(lld1.get(5)).isEqualTo(null);
        assertWithMessage("the value should be null").that(lld1.get(0)).isEqualTo(null);
        assertWithMessage("the value should be 4").that(lld1.get(1)).isEqualTo(4);
        assertWithMessage("the value should be 5").that(lld1.get(4)).isEqualTo(5);
    }

    @Test
    //the time to get value;
    public void Get_efficient_test(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        for (int i = 1; i <= 10000;i++){
            lld1.addLast(i);
        }
        long starttime = System.currentTimeMillis();
        lld1.get(9999);
        long endtime = System.currentTimeMillis();
        assertWithMessage("too long time").that(endtime - starttime).isLessThan(1000);
    }

    @Test
    // test GetRecursive_test();
    public void  GetRecursive_test(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4); // [4,3,2]
        lld1.addLast(5); //[4,3,2,5]
        assertWithMessage("the index is too big").that(lld1.getRecursive(5)).isEqualTo(null);
        assertWithMessage("the value should be null").that(lld1.getRecursive(0)).isEqualTo(null);
        assertWithMessage("the value should be 4").that(lld1.getRecursive(1)).isEqualTo(4);
        assertWithMessage("the value should be 5").that(lld1.getRecursive(4)).isEqualTo(5);
    }
}