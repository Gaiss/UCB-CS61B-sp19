package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final boolean[][] grid;
    private int OpenSiteNum = 0;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufExcludeBottom;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer!");
        }
        n = N;
        grid = new boolean[n][n];
        /*for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                grid[i][j] = false;
            }
        }*/
        /* The virtual top node is connected to all open site in the first row.
         * The virtual bottom node is connected to all open site in the last row.
         * If the top node and the bottom node is connected, then the system percolate.
         * Backwash: Some open sites cannot be connected to the top but can be connected to the bottom,
         * since the top and the bottom are connected, those sites can still be connected to the top indirectly.
         * Solution: maintain two WeightedQuickUnionUF, use the one which only has top node to check if is full,
         * and use the other which have both to check if percolate
         */
        uf = new WeightedQuickUnionUF(n * n + 2); // 0 represents virtual top node, n*n+1 represent virtual bottom node
        ufExcludeBottom = new WeightedQuickUnionUF(n * n + 1); // 0 represents virtual top node, no bottom node
    }

    // check if any argument is outside its prescribed range 0~N-1
    public void validate(int row, int col) {
        if (row < 0 || row > n-1 || col < 0 || col > n-1) {
            throw new IndexOutOfBoundsException("Site out of range!");
        }
    }

    public int sub2ind(int row, int col) {
        return col * n + row + 1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        OpenSiteNum++;
        if (row == 0) { // top sites
            uf.union(sub2ind(row, col), 0);
            ufExcludeBottom.union(sub2ind(row, col), 0);
        } else if (row == n-1) { // bottom sites
            uf.union(sub2ind(row, col), n*n+1);
        }
        // if neighbor could be connected?
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}}; // up, down, left, right sites
        for (int[] dir: dirs) {
            int adjacentRow = row + dir[0];
            int adjacentCol = col + dir[1];
            if (adjacentRow >= 0 && adjacentRow < n && adjacentCol >= 0 && adjacentCol < n) {
                if (isOpen(adjacentRow, adjacentCol)) {
                    uf.union(sub2ind(row, col), sub2ind(adjacentRow, adjacentCol));
                    ufExcludeBottom.union(sub2ind(row, col), sub2ind(adjacentRow, adjacentCol));
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufExcludeBottom.connected(sub2ind(row, col), 0); // if (row, col) is connected to the top
    }

    // number of open sites
    public int numberOfOpenSites() {
        return OpenSiteNum;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, n*n+1); // if the top is connected to the bottom
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {}

}
