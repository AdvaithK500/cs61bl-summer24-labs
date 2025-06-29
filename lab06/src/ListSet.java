import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of ints. A simple implementation of a set using a list.
 */
public class ListSet implements SimpleSet {

    List<Integer> elems;

    public ListSet() {
        elems = new ArrayList<Integer>();
    }

    /** Adds k to the set. */
    @Override
    public void add(int k) {
        if(!elems.contains(k)) {
            elems.add(k);
        }
    }

    /** Removes k from the set. */
    @Override
    public void remove(int k) {
        Integer toRemove = k;
        // TODO - use the above variable with an appropriate List method.
        // The reason is beyond the scope of this lab, but involves
        // method resolution.

        if (elems.contains(toRemove)) {
            elems.remove(toRemove);
        }
    }

    /** Return true if k is in this set, false otherwise. */
    @Override
    public boolean contains(int k) {
        // TODO: Implement this method.
        for (int e: elems) {
            if (e == k) {
                return true;
            }
        }
        return false;
    }

    /** Return true if this set is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
      return this.size() == 0;
    }

    /** Returns the number of items in the set. */
    @Override
    public int size() {
        // TODO: Implement this method.
        return elems.size();
    }

    /** Returns an array containing all of the elements in this collection. */
    @Override
    public int[] toIntArray() {
        // TODO - use a for loop!
        int[] setArray = new int[this.size()];
        int i = 0;
        for (int e: elems) {
            setArray[i] = e;
            i++;
        }
        return setArray;
    }
}
