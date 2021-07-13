import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/***************************************************************
 * Refer to the notes on QuickUnion.java.
 * This code is a great improvement over QuickUnion as it takes
 * into account the depth of the trees, thereby weighing them.
 *
 * Trees are weighted, such that smaller trees have their root
 * changed to the root of taller trees.
 * This prevents the creation of very tall trees.
 * (traversing taller trees when we dont need to is ineffeicient.
 *
 * Root of i is parent[parent[parent[...parent[i]...]]]
 *
 * Find: check of element p and element q have the same root.
 *
 * Union: to merge components containing p and q, set the ID of
 * p's root to the ID of q's root
 ***************************************************************/

/*
 * Initializes an empty union-find data structure with
 * N elements (0 through N-1)
 * Initially, each elements is in its own set.
 * This means each element is its own parent (points to iself).
 *
 * Param N is the number of elements.
 * Throws IllegalArgumentException if n < 0.
 */
public class WeightedQuickUnionUF {
    private int[] parent;       // parent[i] = parent of i
    private int[] size;     // size[i] = number of elements in subtree rooted at i
    private int count;      // number of components

    /*
     * Assigns a parent to each element in array.
     * Initially, each element points to itself.
     */
    public WeightedQuickUnionUF(int N) {
        count = N;
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;  // parent of element 9, will be 9 (etc.)
            size[i] = 1;    // at initialization, all elements are in trees of size 1
        }
    }

    // returns the number of sets
    public int count() {
        return count;
    }

    // Chase parent pointers until root of tree is reached
    private int root(int p) {
        validate(p);  // throws exception if p isn't a valid index
        while (p != parent[p]) {
            // Path compression - makes every other node in path point to its grandparent
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    // identical to QuickUnion.java (code is unchanged)
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;  // array method to get length of array
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Merges the set containing element {p} with the
     * the set containing element {q}.
     * <p>
     * param  p one element
     * param  q the other element
     * throws IllegalArgumentException unless
     * both {0 <= p < n} and {0 <= q < n}
     */
    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];

        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    /**
     * Reads an integer {@code n} and a sequence of pairs of integers
     * (between {@code 0} and {@code n-1}) from standard input, where each integer
     * in the pair represents some element;
     * if the elements are in different sets, merge the two sets
     * and print the pair to standard output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.root(p) == uf.root(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
