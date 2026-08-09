class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Start from the top-right corner
        int i = 0;
        int j = cols - 1;

        // Continue searching while we are inside the matrix
        while (i < rows && j >= 0) {

            // Target found
            if (matrix[i][j] == target) {
                return true;
            }

            // Current value is greater than target,
            // so eliminate this column and move left
            else if (matrix[i][j] > target) {
                j--;
            }

            // Current value is smaller than target,
            // so eliminate this row and move down
            else {
                i++;
            }
        }

        // Target does not exist in the matrix
        return false;
    }
}
