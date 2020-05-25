public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        UnionFind uf = new UnionFind(grid.length*grid[0].length+1); // 0 position represents the ceiling
        //Label all the bubbles which would be hit to 2
        for (int[] dart: darts) {
            if (grid[dart[0]][dart[1]] == 1) {
                grid[dart[0]][dart[1]] = 2;
            }
        }
        // Union the bubbles which will not be hit.
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    unionNeighbors(row, col, grid, uf);
                }
            }
        }
        // Count how many bubbles will be connected to the ceiling.
        int count = uf.sizeOf(0);
        int[] fallNum = new int[darts.length];
        // Reversely change the position that will be hit back to 1.
        for (int i = darts.length - 1; i >= 0; i--) { // Start from the last hit to the first hit
            int row = darts[i][0];
            int col = darts[i][1];
            if (grid[row][col] == 2) {
                unionNeighbors(row, col, grid, uf); // Restore the unions that are destroyed by the hits.
                grid[row][col] = 1;
            }
            int newCount = uf.sizeOf(0);
            // If newCount > count, it means that there are some bubbles connecting
            // to the ceiling through the bubble that will hit by dart. So when that
            // bubble be hit, those "newly" connected bubbles will fall.
            // newCount - count - 1 because the bubble be hit does not count.
            fallNum[i] = newCount > count ? newCount - count - 1 : 0;
            // Update the count, because the put-back bubble might
            // connects some bubbles to the ceiling.
            count = newCount;
        }
        return fallNum;
    }

    private void unionNeighbors(int row, int col, int[][] grid, UnionFind uf) {
        //A bubble is stuck if it is in the topmost row of the grid
        if (row == 0) {
            uf.union(sub2ind(row, col), 0);
        }
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}}; //directions:up,down,left,right
        for (int[] dir: dirs) {
            int adjRow = row + dir[0];
            int adjCol = col + dir[1];
            if (adjRow < 0 || adjRow == grid.length || adjCol < 0 || adjCol == grid[0].length || grid[adjRow][adjCol] != 1) {
                continue;
            }
            uf.union(sub2ind(adjRow, adjCol), sub2ind(row, col));
        }

    }

    private int sub2ind(int row, int col) {
        return (col+1) * grid.length + row + 1;
    }

}
