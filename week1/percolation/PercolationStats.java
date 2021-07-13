import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // 1.96 represents the 97.5 percentile in a normal distribution
    // 95% of hte area under a normal curve lies within 1.96 stddevs
    private static final double CONFIDENCE_LVL = 1.96;

    // instance variables
    private final double mean;
    private final double stddev;
    private final double confidenceLow;
    private final double confidenceHigh;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("both n and trials should be greater than 0");
        }

        double[] results = new double[trials];

        // run percolation trails for T number of times
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);

            // run a trial until percolation is reached
            while (!perc.percolates()) {
                // randomize selection of sites
                int row = StdRandom.uniform(1, n + 1);  // Returns random int from 1 to n
                int col = StdRandom.uniform(1, n + 1);
                perc.open(row, col);
            }

            results[i] = (double) perc.numberOfOpenSites() / (n * n);
        }

        // Statistics
        this.mean = StdStats.mean(results);
        this.stddev = StdStats.stddev(results);
        this.confidenceLow = this.mean - PercolationStats.CONFIDENCE_LVL * (this.stddev / Math.sqrt(trials));
        this.confidenceHigh = this.mean + PercolationStats.CONFIDENCE_LVL * (this.stddev / Math.sqrt(trials));
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHigh;
    }

    // test client
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, t);
        double mean = stats.mean();
        double stddev = stats.stddev();
        double confLo = stats.confidenceLo();
        double confHi = stats.confidenceHi();

        System.out.printf("%-20s= %f%n", "mean", mean);
        System.out.printf("%-20s= %f%n", "stddev", stddev);
        System.out.printf("%-20s= [%f, %f]%n", "95% confidence interval", confLo, confHi);

    }

}
