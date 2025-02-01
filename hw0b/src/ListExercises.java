import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum = 0;
        for (int i : L) {
            sum += i;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> N = new ArrayList<>();
        for (int i : L){
            if (i % 2 == 0){
                N.add(i);
            }
        }
        return N;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> common = new ArrayList<>();
        for (int i : L1){
                for (int j : L2){
                    if (i == j){
                        common.add(i);
                    }
            }
        }
        return common;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int n = 0;
        String cs = String.valueOf(c);
        for (String s : words){
            if (s.contains(cs)) {
                n += 1;
            }
        }
        return n;
    }
}
