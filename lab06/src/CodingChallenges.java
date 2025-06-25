import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class CodingChallenges {

    /**
     * Return the missing number from an array of length N containing all the
     * values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        // simply return N*(N+1)/2 - sum(values) = missing value
        int N = values.length;
        int sumOfvalues = 0;
        for (int v: values ){
            sumOfvalues += v;

        return N * (N + 1) / 2 - sumOfvalues;
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation (String s1, String s2) {
        // eg: grinding is a permutation of ggrindin
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s1.toCharArray();

        if (s1Array.length != s2Array.length) {return false; }
        // else same size of characters, create a set of seen characters
            Set<Integer> seen = new HashSet<>();
            Map<Character, Integer> characterCounts = new HashMap<>();

            for (char c: s1Array) {
                if(!seen.contains(c)) {
                    seen.add(c);
                    characterCounts.put(c, 1);
                }
                // else the character is in there so increase count by 1

            }

            for (char c: s2Array) {
                if (!seen.contains(c)) { return false; }
                // else it contains the same character set , check if it contains same count
                if (characterCounts.get(c))
            }

        }
}
