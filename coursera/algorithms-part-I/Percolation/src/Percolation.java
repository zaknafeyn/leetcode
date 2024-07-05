import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private int n = 0;
    private boolean[][] grid = null;
    private int openSites = 0;
    private WeightedQuickUnionUF uf = null;
    private int virtualTop = 0;
    private int virtualBottom = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be greater than 0");
        }

        this.n = n;
        this.grid = new boolean[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                this.grid[row][col] = false;
            }
        }

        virtualBottom = n * n + 1;
        virtualTop = 0;
        uf = new WeightedQuickUnionUF(n*n+2);
    }

    private void validateArgs(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row and col should be between 1 and n");
        }
    }

    private boolean isConnected(int p, int q) {
        int pRoot = uf.find(p);
        int qRoot = uf.find(q);
        return pRoot == qRoot;
    }

    private int xyTo1D(int row, int col) {
        return (row - 1) * n + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateArgs(row, col);

        if (this.grid[row-1][col-1]) {
            return;
        }

        this.grid[row-1][col-1] = true;

        if (row == 1) {
            uf.union(virtualTop, xyTo1D(row, col));
        } 
        
        if (row == n) {
            uf.union(virtualBottom, xyTo1D(row, col));
        }

        // left
        if (col > 1 && this.grid[row-1][col-2]) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col-1));
        }

        // right
        if (col < n && this.grid[row-1][col]) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col+1));
        }

        // top
        if (row > 1 && this.grid[row-2][col-1]) {
            uf.union(xyTo1D(row, col), xyTo1D(row-1, col));
        }

        // bottom
        if (row < n && this.grid[row][col-1]) {
            uf.union(xyTo1D(row, col), xyTo1D(row+1, col));
        }

        this.openSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateArgs(row, col);

        return this.grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateArgs(row, col);

        return isConnected(virtualTop, xyTo1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return isConnected(virtualTop, virtualBottom);
    }


    private void printGrid() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                System.out.print(grid[row][col] ? "1 " : "0 ");
            }
            System.out.println();
        }
    }
    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        System.out.println(n);
        Percolation percolation = new Percolation(n);
        // percolation.open(0, 1);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.printGrid();
        var isFull21 = percolation.isFull(2, 1) ? "Yes" : "No";
        var percolates = percolation.percolates() ? "Yes" : "No";
        System.out.println("Is full 2,1: " + isFull21);
        System.out.println("Percolates: " + percolates);
        // percolation.open(2, 2);
        // percolation.printGrid();
        // percolates = percolation.percolates() ? "Yes" : "No";
        // System.out.println("Percolates: " + percolates);
    }
}
