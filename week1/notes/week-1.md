# Algorithms, Part 1

## Week 1: The Dynamic Connectivity Problem

### QuickFind.java

```java
public class QuickFindUF {
    // Initializes storage array of integer IDs
    private int[] id;

    // Sets ID of each object to itself.
    // Takes N time because of need to iterate through all objects in array.
    public QuickfindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // Check whether p and q are in same components
    // Takes constant time to determin if two elements are connected.
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // Change all entries with id[p] to id[q].
    // Matching ids indicate same component.
    // Also takes N time.
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid
            }
        }
    }
}

```

 * QuickFind is a relatively simple algorithm, but has flaws.
 * This is because QuickFind has a quadratic (N^2) time complexity, and quadratic algorithms don't scale well as the number of elements increases.
 * Pros: 
     * simple
 * Cons: 
  * too slow, N array accesses
 * Our data structure is an array with N number of elements.
 * In this algorithm, we iterate through N elements to first assigned IDs, and then once again to create a union of elements.
 * Trees are flat

### QuickUnion.java

```java

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

```

 * QuickUnion is a 'lazy' approach to the union problem.
 * Pros: 
     * the union part of this alg only requires changing one id
     * less code, more elegant
     * faster than Quickfind
 * Cons: 
     * still too slow (potentially N array accesses for find operation)
     * trees can get very tall
     * union of a tall tree (large height) to a short tree is inefficient
         * in contrast, changing the root of a short tree to that of a taller tree is more efficient
 * Data Structure: array id[] of size N
 * Each element's relation to one another can be depicted as a forest (a set of trees), where each element is a node on a tree and references a parent node. A node that references itself is a root.
 * Root of i is id[id[id[...id[i]...]]]
 * Find: check of element p and element q have the same root.
 * Union: to merge components containing p and q, set the ID of p's root to the ID of q's root

### WeightedQuickUnion.java w/ Path Compression (WQUPC)

```java
```

- Avoids the inefficiency of creating increasingly taller trees by keeping track of of the height of trees
- when you join a smaller tree lower to the taller tree, the overall depth has not changed;
  - trees are kept mostly flat

![image-20210705133920535](/Users/steventruong/Library/Application Support/typora-user-images/image-20210705133920535.png)

![image-20210705134237595](/Users/steventruong/Library/Application Support/typora-user-images/image-20210705134237595.png)

- both images represent the same union operations
  - the top indescriminantly joins components regardless of size
  - the bottom only joins smaller trees to the larger trees

#### Time Complexity with Weights

![image-20210705221644814](/Users/steventruong/Library/Application Support/typora-user-images/image-20210705221644814.png)

#### Improvement 2: Path Compression

![image-20210705214659649](/Users/steventruong/Library/Application Support/typora-user-images/image-20210705214659649.png)

![image-20210705214727764](/Users/steventruong/Library/Application Support/typora-user-images/image-20210705214727764.png)



![image-20210705214410573](/Users/steventruong/Library/Application Support/typora-user-images/image-20210705214410573.png)

![image-20210705215819192](/Users/steventruong/Library/Application Support/typora-user-images/image-20210705215819192.png)



### Union-Find Applications

- Network connectivity.

- Percolation.
- Image processing.
- Least common ancestor.
- Equivalence of finite state automata.
- Hinley-Milner polymorphic type inference. 
- Kruskal's minimum spanning tree algorithm.
- Games (Go, Hex)
- Compiling equivalence statements in Fortran.
- Morphological attribute openings and closings
- matlab's `bwlabel()` function in image processing

