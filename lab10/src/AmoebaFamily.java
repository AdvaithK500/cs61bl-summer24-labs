package src;

import java.util.*;

/* An src.AmoebaFamily is a tree, where nodes are Amoebas, each of which can have
   any number of children. */
public class AmoebaFamily implements Iterable<AmoebaFamily.Amoeba> {

    /* ROOT is the root amoeba of this src.AmoebaFamily */
    private Amoeba root = null;

    /* Creates an src.AmoebaFamily, where the first Amoeba's name is NAME. */
    public AmoebaFamily(String name) {
        root = new Amoeba(name, null);
    }

    /* Adds a new Amoeba with CHILDNAME to this src.AmoebaFamily as the youngest
       child of the Amoeba named PARENTNAME. This src.AmoebaFamily must contain an
       Amoeba named PARENTNAME. */
    public void addChild(String parentName, String childName) {
        if (root != null) {
            root.addChildHelper(parentName, childName);
        }
    }

    /* Returns the length of the longest name in this src.AmoebaFamily. */
    public int longestNameLength() {
        if (root != null) {
            return root.longestNameLengthHelper();
        }
        return 0;
    }

    /* Returns the longest name in this src.AmoebaFamily. */
    public String longestName() {
        // the below iteration works if the iterator() does a DFS or BFS on the amoeba tree
//        if (root != null) {
//            String maxString = root.name;
//            for (Amoeba a: this) {
//                // go through all a.name and store the largest name
//                if(a.name.compareTo(maxString) > 0) {
//                    maxString = a.name;
//                }
//            }
//            return maxString;
//        }
        if (root != null) {
            return root.longestNameHelper();
        }

        return "";
    }

    /* Returns an Iterator for this src.AmoebaFamily. */
    public Iterator<Amoeba> iterator() {
       // return new AmoebaDFSIterator();
        return new AmoebaBFSIterator();
    }

    /* Creates a new src.AmoebaFamily and prints it out. */
    public static void main(String[] args) {
        AmoebaFamily family = new AmoebaFamily("Amos McCoy");
        family.addChild("Amos McCoy", "mom/dad");
        family.addChild("Amos McCoy", "auntie");
        family.addChild("mom/dad", "me");
        family.addChild("mom/dad", "Fred");
        family.addChild("mom/dad", "Wilma");
        family.addChild("me", "Mike");
        family.addChild("me", "Homer");
        family.addChild("me", "Marge");
        family.addChild("Mike", "Bart");
        family.addChild("Mike", "Lisa");
        family.addChild("Marge", "Bill");
        family.addChild("Marge", "Hilary");
        System.out.println("Here's the family!");
        // Optional TODO: use the iterator to print out the family!
        for (Amoeba a: family) {
            System.out.println(a.name);
        }
    }

    /* An Amoeba is a node of an src.AmoebaFamily. */
    public static class Amoeba {

        private String name;
        private Amoeba parent;
        private ArrayList<Amoeba> children;

        public Amoeba(String name, Amoeba parent) {
            this.name = name;
            this.parent = parent;
            this.children = new ArrayList<Amoeba>();
        }

        public String toString() {
            return name;
        }

        public Amoeba getParent() {
            return parent;
        }

        public ArrayList<Amoeba> getChildren() {
            return children;
        }

        /* Adds child with name CHILDNAME to an Amoeba with name PARENTNAME. */
        public void addChildHelper(String parentName, String childName) {
            if (name.equals(parentName)) {
                Amoeba child = new Amoeba(childName, this);
                children.add(child);
            } else {
                for (Amoeba a : children) {
                    a.addChildHelper(parentName, childName);
                }
            }
        }

        /* Returns the length of the longest name between this Amoeba and its
           children. */
        public int longestNameLengthHelper() {
            int maxLengthSeen = name.length();
            for (Amoeba a : children) {
                maxLengthSeen = Math.max(maxLengthSeen,
                                         a.longestNameLengthHelper());
            }
            return maxLengthSeen;
        }

        // POSSIBLE HELPER FUNCTIONS HERE

        public String longestNameHelper() {
            // base case: current amoeba has no children i.e leaf
            if (this.children.isEmpty()) {
                return "";
            }
            String maxNameSeen = this.name;

            for (Amoeba a: children) {
                String currentName = a.longestNameHelper();
                if (currentName.length() > maxNameSeen.length()) {
                    maxNameSeen = currentName;
                }
            }
            return maxNameSeen;
        }

    }

    /* An Iterator class for the src.AmoebaFamily, running a DFS traversal on the
       src.AmoebaFamily. Complete enumeration of a family of N Amoebas should take
       O(N) operations. */
    public class AmoebaDFSIterator implements Iterator<Amoeba> {

        // DFS pre order - root, left , right
        // right child being pushed before the left child. ( to maintain root, left, right order )

        // fringe set of amoebas
        private Stack<Amoeba> fringe = new Stack<>();

        /* AmoebaDFSIterator constructor. Sets up all of the initial information
           for the AmoebaDFSIterator. */
        public AmoebaDFSIterator() {
            if (root != null) {
                fringe.push(root);
            }
        }
        /* Returns true if there is a next element to return. */
        public boolean hasNext() {
            return !fringe.isEmpty();

        }

        /* Returns the next element. */
        public Amoeba next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Amoeba current = fringe.pop();

            // there is no node.left and node.right, each amoeba has an array of children
            // get the children and then push them onto fringe in reverse order
            List<Amoeba> children = current.getChildren();

            for (int i = children.size() - 1; i >= 0; i--) {
                fringe.push(children.get(i));
            }
            return current;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* An Iterator class for the src.AmoebaFamily, running a BFS traversal on the
       src.AmoebaFamily. Complete enumeration of a family of N Amoebas should take
       O(N) operations. */
    public class AmoebaBFSIterator implements Iterator<Amoeba> {

        // Optional TODO: IMPLEMENT THE CLASS HERE
        private Queue<Amoeba> fringe;
        /* AmoebaBFSIterator constructor. Sets up all of the initial information
           for the AmoebaBFSIterator. */
        public AmoebaBFSIterator() {
            fringe = new LinkedList<>();
            if (root != null) {
                fringe.add(root);
            }
        }

        /* Returns true if there is a next element to return. */
        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        /* Returns the next element. */
        public Amoeba next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            //dequeue the top element of queue
            Amoeba current = fringe.poll();

            //  push all of its children in teh correct ascending order onto the queue
            //  and then return the top element
            List<Amoeba> children = current.getChildren();

            for (Amoeba c: children) {
                fringe.add(c);
            }

            return current;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
