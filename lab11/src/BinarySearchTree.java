public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    /* Creates an empty BST. Super() calls the constructor for BinaryTree (not in scope). */
    public BinarySearchTree() {
        super();
    }

    /* Creates a BST with root as ROOT. */
    public BinarySearchTree(TreeNode root) {
        super(root);
    }

    /* Returns true if the BST contains the given KEY. */
    public boolean contains(T key) {
        // TODO: YOUR CODE HERE: an extra helper method might be useful
        return contains(key, getRoot());
    }
    private boolean contains(T key, BinaryTree.TreeNode<T> root) {
        // base case: empty tree, return false
        if (root == null) {
            return false;
        }
        T rootKey = root.getItem();
        boolean isFoundLeft = false;
        boolean isFoundRight = false;
        // if key == single rooted tree key, return true
        if (key.equals(rootKey)) {
            return true;
        } else if ( key.compareTo(rootKey) < 0) {
            isFoundLeft = contains(key, root.getLeft());
        } else {
            isFoundRight = contains(key, root.getRight());
        }

        return isFoundLeft || isFoundRight;
    }

    /* Adds a node for KEY iff KEY isn't in the BST already. */
    public void add(T key) {
        // TODO: YOUR CODE HERE: an extra helper method might be useful
        root = add(key, getRoot());
    }

    private TreeNode<T> add(T key, BinaryTree.TreeNode<T> root) {
        // base case: root is null, so key is the leaf of the original tree
        if (root == null) {
            return new TreeNode<>(key);
        }
        T rootKey = root.getItem();
        if ( key.compareTo(rootKey) < 0) {
            root.left = add(key, root.left);
        } else {
            root.right = add(key, root.right);
        }

        return root;
    }

    /* Deletes a node from the BST. 
     * Even though you do not have to implement delete, you 
     * should read through and understand the basic steps.
    */
    public T delete(T key) {
        TreeNode<T> parent = null;
        TreeNode<T> curr = root;
        TreeNode<T> delNode = null;
        TreeNode<T> replacement = null;
        boolean rightSide = false;

        while (curr != null && !curr.item.equals(key)) {
            if (curr.item.compareTo(key) > 0) {
                parent = curr;
                curr = curr.left;
                rightSide = false;
            } else {
                parent = curr;
                curr = curr.right;
                rightSide = true;
            }
        }
        delNode = curr;
        if (curr == null) {
            return null;
        }

        if (delNode.right == null) {
            if (root == delNode) {
                root = root.left;
            } else {
                if (rightSide) {
                    parent.right = delNode.left;
                } else {
                    parent.left = delNode.left;
                }
            }
        } else {
            curr = delNode.right;
            replacement = curr.left;
            if (replacement == null) {
                replacement = curr;
            } else {
                while (replacement.left != null) {
                    curr = replacement;
                    replacement = replacement.left;
                }
                curr.left = replacement.right;
                replacement.right = delNode.right;
            }
            replacement.left = delNode.left;
            if (root == delNode) {
                root = replacement;
            } else {
                if (rightSide) {
                    parent.right = replacement;
                } else {
                    parent.left = replacement;
                }
            }
        }
        return delNode.item;
    }
}
