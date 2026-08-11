/*

Memoization usage --> Memoization is a programming optimization technique that speeds 
up programs by saving the results of expensive function calls. When the same inputs occur again, 
the program returns the cached result instantly instead of repeating the computation.

*/

class Solution {
    public int uniquePaths(int m, int n) {

        // Create a DP grid with m rows and n columns
        // grid[i][j] stores the number of ways to reach (i, j)
        int[][] grid = new int[m][n];

        // Traverse every cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // First row and first column have only one possible path
                if (i == 0 || j == 0) {
                    grid[i][j] = 1;
                }

                // For all other cells:
                // ways = ways from left + ways from above
                else {
                    grid[i][j] = grid[i][j - 1] + grid[i - 1][j];
                }
            }
        }

        // Bottom-right cell contains the total number of unique paths
        return grid[m - 1][n - 1];
    }
}
