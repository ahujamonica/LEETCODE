class Solution {
    public int orangesRotting(int[][] grid) {

        if(grid == null || grid.length == 0)
            return -1;

        int rows = grid.length;
        int cols = grid[0].length;

        // time[i][j] = minimum time needed to reach this cell
        int[][] time = new int[rows][cols];

        // Initially, every cell is unreachable
        for(int i = 0; i < rows; i++){
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }

        // Start DFS from every rotten orange
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){

                if(grid[i][j] == 2){
                    dfs(grid, time, i, j, 0);
                }
            }
        }

        int timeRequired = 0;

        // Find the time required for all fresh oranges
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){

                if(grid[i][j] == 1){

                    // Fresh orange was never reached
                    if(time[i][j] == Integer.MAX_VALUE){
                        return -1;
                    }

                    // We need the maximum time
                    timeRequired = Math.max(
                        timeRequired,
                        time[i][j]
                    );
                }
            }
        }

        return timeRequired;
    }


    private void dfs(int[][] grid, int[][] time,
                     int i, int j, int currentTime){

        // Stop if:
        // 1. Outside the grid
        // 2. Cell is empty
        // 3. We already reached this cell
        //    in an equal or shorter time
        if(i < 0 || j < 0 ||
           i >= grid.length || j >= grid[0].length ||
           grid[i][j] == 0 ||
           currentTime >= time[i][j]) {

            return;
        }

        // Store the best time found so far
        time[i][j] = currentTime;

        // Explore all 4 directions
        dfs(grid, time, i - 1, j, currentTime + 1);
        dfs(grid, time, i + 1, j, currentTime + 1);
        dfs(grid, time, i, j - 1, currentTime + 1);
        dfs(grid, time, i, j + 1, currentTime + 1);
    }
}
