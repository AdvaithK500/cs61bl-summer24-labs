/**
 * Represent a set of nonnegative ints from 0 to maxElement (inclusive) for some initially
 * specified maxElement.
 */
public class BooleanSet implements SimpleSet {

    private boolean[] contains; /*
    boolean array follows the invariant
    contains[i] = false by default
    contains[i] = true if the set contains i
    */
    private int size; // number of i for which contains[i] = true

    /** Initializes a set of ints from 0 to maxElement. */
    public BooleanSet(int maxElement) {
        contains = new boolean[maxElement + 1]; // range for i is [0, maxElement]
        size = 0;
    }

    /** Adds k to the set. */
    @Override
    public void add(int k) {
        // TODO: Implement this method.
        // if we want to add say 5, and 5 is not there, then simply set contains[5] = true
        if (!contains[k]) {
            contains[k] = true;
            size++;
        }

    }

    /** Removes k from the set. */
    @Override
    public void remove(int k) {
        // TODO: Implement this method.
        // if we want to remove 5, and 5 is there, then simply set it to false
        if (contains[k]) {
            contains[k] = false;
            size--;
        }

    }

    /** Return true if k is in this set, false otherwise. */
    @Override
    public boolean contains(int k) {
        return contains[k];
    }


    /** Return true if this set is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /** Returns the number of items in the set. */
    @Override
    public int size() {
        // number of i for which contains[i] = true
        return size;
    }

    /** Returns an array containing all of the elements in this collection. */
    @Override
    public int[] toIntArray() {
        // if contains[i] is true, add to an array the number i and return this array
        int[] setArray = new int[this.size()];
        int k = 0;
        for (int j = 0; j < contains.length; j++) {
            if (contains[j]) {
                setArray[k] = j;
                k++;
            }
        }
        return setArray;
    }
}
