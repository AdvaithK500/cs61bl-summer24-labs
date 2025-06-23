public class UnionFind {
    // the nodes array called id and the size array sz which isnt strictly needed
    int[] id;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // initialize all items to -1, the -ve represents its a root element
        // the magnitude 1 represents size of the component(root) = 1
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        int root = find(v); // first find the root of the v, could be itself
        return -parent(root); // returns the size
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return id[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        int root_v1 = find(v1);
        int root_v2 = find(v2);

        return root_v1 == root_v2;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // edge case: v < 0 or v >= id.length
        if (v < 0 || v >= id.length) {
            throw new IllegalArgumentException("Index " + v + " out of bounds");
        }

        // find parent by calling id[...id[id[x]]...] until < 0
        // how to simultaneously do path compression?
        int originalV = v;
        while (id[v] >= 0) {
            v = id[v];
        }
        // now v is the root, we can path compress?
        int rootOfV = v;

        while (id[originalV] != rootOfV) {
            int prev = id[originalV];
            id[originalV] = rootOfV;
            originalV = prev;
        }
        return v;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing a item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // if v1 and v2 are connected just return
        if (connected(v1, v2)) {return; }
        // find the roots of v1 and v2 and the sizes
        int root_v1 = find(v1);
        int root_v2 = find(v2);
        int size_v1 = -id[root_v1];
        int size_v2 = -id[root_v2];
        // if sizes are equal, tie break as mentioned
        if (size_v1 <= size_v2) {
            id[root_v2] = -(size_v1 + size_v2);
            id[root_v1] = root_v2;
        } else {
            id[root_v1] = -(size_v1 + size_v2);
            id[root_v2] = root_v1;
        }
    }
}
