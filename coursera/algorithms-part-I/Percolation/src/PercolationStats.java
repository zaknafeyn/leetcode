import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private final double CONFIDENCE_95 = 1.96;
  private double[] thresholds = null;


  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n < 1 || trials < 1) {
      throw new IllegalArgumentException("n and trials should be greater than 0");
    }

    this.thresholds = new double[trials];
    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);
      while (!percolation.percolates()) {
        int row = StdRandom.uniformInt(n) + 1;
        int col = StdRandom.uniformInt(n) + 1;

        if (!percolation.isOpen(row, col)) {
          percolation.open(row, col);
        }
          
      }
      thresholds[i] = (double) percolation.numberOfOpenSites() / (n * n);
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(this.thresholds);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(this.thresholds);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return this.mean() - CONFIDENCE_95 * this.stddev() / Math.sqrt(this.thresholds.length);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return this.mean() + CONFIDENCE_95 * this.stddev() / Math.sqrt(this.thresholds.length);
  }

  // test client (see below)
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    
    PercolationStats stats = new PercolationStats(n, trials);
    StdOut.println("mean                    = " + stats.mean());
    StdOut.println("stddev                  = " + stats.stddev());
    StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
  }
}
