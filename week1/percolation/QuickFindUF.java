/***************************************************************
 * QuickFind is a relatively simple algorithm, but has flaws.
 * This is because QuickFind has a quadratic (N^2) time complexity,
 * and quadratic algorithms don't scale well as the number of
 * elements increases.
 *
 * Pros: simple
 * Cons: too slow, N array accesses
 *
 * Our data structure is an array with N number of elements.
 * In this algorithm, we iterate through N elements to first
 * assigned IDs, and then once again to create a union of elements.
 ***************************************************************/

public class QuickFindUF {
    // Initializes storage array of integer IDs
    private final int[] id;
    private final int count;

    // Sets ID of each object to itself.
    // Takes N time because of need to iterate through all objects in array.
    public QuickFindUF(int n) {
        this.count = n;
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        return id[p];
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    // Check whether p and q are in same components
    // Takes constant time to determine if two elements are connected.
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // Change all entries with id[p] to id[q].
    // Matching ids indicate same component.
    // Also takes N time.
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}
