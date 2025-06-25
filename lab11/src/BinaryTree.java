import java.util.ArrayList;

public class BinaryTree<T> {

    // Do not modify the TreeNode class.
    static class TreeNode<T> {
        T item;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T item) {
            this.item = item; left = right = null;
        }

        public TreeNode(T item, TreeNode<T> left, TreeNode<T> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }

        public T getItem() {
            return item;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public TreeNode<T> getRight() {
            return right;
        }
    }

    // protected gives subclasses the ability to access this instance variable,
    // but not other classes.
    TreeNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode<T> t) {
        root = t;
    }

    public BinaryTree(ArrayList<T> pre, ArrayList<T> in) {
        
    }


    public TreeNode<T> getRoot() {
        return root;
    }

    /** Optional constructor, see optional exercise in lab (or last week's theoretical lab). */
    public BinaryTree(ArrayList<T> pre, ArrayList<T> in) { }

    /* Print the values in the tree in preorder. */
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            printPreorderHelper(root);
            System.out.println();
        }
    }

    private void printPreorderHelper(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.item + " ");
        printPreorderHelper(node.left);
        printPreorderHelper(node.right);
    }

    /* Print the values in the tree in inorder: values in the left subtree
       first (in inorder), then the root value, then values in the first
       subtree (in inorder). */
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            printInorderHelper(root);
            System.out.println();
        }
    }

    /* Prints the nodes of the BinaryTree in inorder. Used for your testing. */
    private void printInorderHelper(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        printInorderHelper(node.left);
        System.out.print(node.item + " ");
        printInorderHelper(node.right);
    }

    /* Prints out the contents of a BinaryTree with a description in both
       preorder and inorder. */
    static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    /* Fills this BinaryTree with values a, b, and c. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree1() {
        TreeNode<String> root = new TreeNode("a",
                new TreeNode("b"),
                new TreeNode("c"));
        return new BinaryTree<>(root);
    }

    /* Fills this BinaryTree with values a, b, and c, d, e, f. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree2() {
        TreeNode root = new TreeNode("a",
                new TreeNode("b",
                        new TreeNode("d",
                                new TreeNode("e"),
                                new TreeNode("f")),
                        null),
                new TreeNode("c"));
        return new BinaryTree<>(root);
    }

    /* Fills this BinaryTree with the values a, b, c, d, e, f. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree3() {
        TreeNode<String> root = new TreeNode("a",
                new TreeNode("b"),
                new TreeNode("c",
                        new TreeNode("d",
                                new TreeNode("e"),
                                new TreeNode("f")),
                        null));
        return new BinaryTree<>(root);
    }

    /* Fills this BinaryTree with the same leaf TreeNode. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree4() {
        TreeNode<String> leafNode = new TreeNode("c");
        TreeNode<String> root = new TreeNode("a", new TreeNode("b", leafNode, leafNode),
                new TreeNode("d", leafNode, leafNode));
        return new BinaryTree<>(root);
    }

    /* Creates two BinaryTrees and prints them out in inorder. */
    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<>();
        print(t, "the empty tree");
        t = BinaryTree.sampleTree1();
        print(t, "sample tree 1");
        t = BinaryTree.sampleTree2();
        print(t, "sample tree 2");
        t = BinaryTree.sampleTree3();
        print(t, "sample tree 3");
        t = BinaryTree.sampleTree4();
        print(t, "sample tree 4");
    }

    /* Returns the height of the tree. */
    public int height() {
        return height(root);
    }

    private int height(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    /* Returns true if the tree's left and right children are the same height
       and are themselves completely balanced. */
    public boolean isCompletelyBalanced() {
       return isCompletelyBalanced(root);
    }

    private boolean isCompletelyBalanced(TreeNode<T> node) {
        //A tree with no nodes and a tree with one node are both completely balanced
        if (node == null || (node.getLeft() == null && node.getRight() == null) ) {
            return true;
        }
        /*
         * any other tree is completely balanced if and only if
         * 1) the height of its left child is equal to the height of its right child
         * 2) its left and right children are also completely balanced
         * */
        int heightLeftChild = height(node.getLeft());
        int heightRightChild = height(node.getRight());

        if ((heightLeftChild == heightRightChild) && isCompletelyBalanced(node.getLeft())
                && isCompletelyBalanced(node.getRight())) {
            return true;
        } else {
            return false;
        }
    }

    /* Returns a BinaryTree representing the Fibonacci calculation for N. */
    public static BinaryTree<Integer> fibTree(int N) {
        // base case: when we pass in 0 or 1 OR result is empty?
        if (N == 0 || N  == 1) {
            return  new BinaryTree<Integer> (new TreeNode<Integer> (N));
        }
        // say N = 3
        BinaryTree<Integer> leftSubTree = fibTree(N - 1); // Tree(1)
        BinaryTree<Integer> rightSubTree = fibTree(N - 2); // Tree(0)

        int leftVal = leftSubTree.getRoot().getItem(); // 1
        int rightVal = rightSubTree.getRoot().getItem(); // 0

        //how do we attatch both the subtrees? root is now Node(1, .
        TreeNode<Integer> root = new TreeNode<>(leftVal + rightVal, leftSubTree.getRoot(), rightSubTree.getRoot());


        return new BinaryTree<Integer>(root);
    }
}
