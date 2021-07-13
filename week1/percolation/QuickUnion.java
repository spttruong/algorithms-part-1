/***************************************************************
 * QuickUnion is a 'lazy' approach to the union problem.
 * Pros:
 * - the union part of this alg only requires changing one id
 * - less code, more elegant
 * - faster than Quickfind
 * Cons:
 * - still too slow (potentially N array accesses for find operation)
 * - trees can get tall
 *
 * Data Structure: array id[] of size N
 *
 * Each element's relation to one another can be depicted
 * as a forest (a set of trees), where each element is a node on a tree
 * and references a parent node. A node that references itself is a root.
 *
 * Root of i is id[id[id[...id[i]...]]]
 *
 * Find: check of element p and element q have the same root.
 *
 * Union: to merge components containing p and q, set the ID of
 * p's root to the ID of q's root
 ***************************************************************/

public class QuickUnionUF {
    private int[] id;

    // Assigns IDs to each element in array
    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // Chase parent pointers until root is reached
    private int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
    

}
