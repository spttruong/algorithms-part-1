import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;                      // stores grid size
    private final boolean[] siteStatus;       // true = open, false = closed
    private final WeightedQuickUnionUF sites; // data structure for each site
    private int totalOpenSites;
    private final int maxSiteIndex;           // sites IDs cannot exceed this

    // connected() only has to be called once for Percolates() method
    private final int virtualTopSiteID;       // all top row nodes are connected to this
    private final int virtualBottomSiteID;    // all bottom row nodes are connected to this


    // creates n-by-n grid
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be an integer greater than 0");

        this.n = n;

        // this array keeps track of status of open/closed site
        this.siteStatus = new boolean[(n * n) + 2];

        // initialize n-by-n grid as nodes, +2 for the virtual top and bottom sites
        this.sites = new WeightedQuickUnionUF((n * n) + 2);

        // all sites start off blocked or "closed"
        this.totalOpenSites = 0;
        this.maxSiteIndex = n * n; // e.g. if n=3, sites range from 1 to 9

        // initialize virtual sites (can be arbitrary so long as they are outside the n-by-n grid)
        this.virtualTopSiteID = 0;
        this.virtualBottomSiteID = (n * n) + 1;

        siteStatus[virtualTopSiteID] = true;
        siteStatus[virtualBottomSiteID] = true;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // check for valid index
        if (row <= 0 || row > this.n) {
            throw new IllegalArgumentException("index " + row + " is not between 0 and " + (n - 1));
        }
        if (col <= 0 || col > this.n) {
            throw new IllegalArgumentException("index " + col + " is not between 0 and " + (n - 1));
        }

        // convert the site from 2d to 1d to index
        int i = this.xyTo1d(row, col);

        // Only open a site if it was previously closed
        if (!this.siteStatus[i]) {
            this.siteStatus[i] = true;

            // opening a top row site connects it to virtual top site
            // all indices less than n are top row sites
            if (row == 1) {
                sites.union(i, this.virtualTopSiteID);
            }

            // opening a bottom row site connects it to virtual bottom site
            if (row == n) {
                sites.union(i, this.virtualBottomSiteID);
            }

            /**
             *  When opening site, connect site to each open neighboring site
             *  Neighboring site must exist within the idices of the n-by-n matrix.
             *  The neighboring site must also be open.
             */

            // defining neighbor sites (top, bottom, left, right)
            int[] neighborSites = {i - this.n, i + this.n, i - 1, i + 1};

            for (int neighborSite : neighborSites) {
                // Does neighbor site exist within bounds of matrix?
                if (neighborSite <= maxSiteIndex && neighborSite > 0) {
                    // if site is open, connect the sites
                    if (this.siteStatus[neighborSite]) {
                        sites.union(i, neighborSite);
                    }
                }
            }

            // increment total open sites by 1 each time new site is opened}
            this.totalOpenSites += 1;
        }

    }

    /* Helper method to translate 2D array coordinates to 1D.
     * By convention, row and column indices are integers between 1 and n.
     */
    private int xyTo1d(int x, int y) {
        if (x <= 0 || x > n) throw new IllegalArgumentException(x + "is not a valid row index");
        if (y <= 0 || y > n) throw new IllegalArgumentException(y + "is not a valid col index");

        // formula for converting 2d array indices to 1d
        // also take into account that row and col indices don't start at 0
        return (this.n * (x - 1)) + y;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return this.siteStatus[this.xyTo1d(row, col)];
    }

    /**
     * is the site (row, col) full?
     * in other words, is the site an open site
     * that is connected to virtual top site?
     * Or, "is there water in this open site?"
     */
    public boolean isFull(int row, int col) {
        int i = xyTo1d(row, col);
        return (this.sites.find(i) == this.sites.find(virtualTopSiteID));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.totalOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return (this.sites.find(this.virtualBottomSiteID) == this.sites.find(this.virtualTopSiteID));
    }

    // test client (optional)
    /* public static void main(String[] args) {

    } */
}
