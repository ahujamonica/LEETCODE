class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int n = matrix.length;
        int m = matrix[0].length;

        // Treat the matrix as a virtual sorted 1D array
        int low = 0;
        int high = (n * m) - 1;

        while (low <= high) {

            // Calculate the middle index using the overflow-safe formula
            int mid = low + (high - low) / 2;

            // Convert the virtual 1D index into row and column
            int row = mid / m;
            int col = mid % m;

            // Target found
            if (matrix[row][col] == target) {
                return true;
            }

            // Current value is smaller than target,
            // so search in the right half
            else if (matrix[row][col] < target) {
                low = mid + 1;
            }

            // Current value is greater than target,
            // so search in the left half
            else {
                high = mid - 1;
            }
        }

        // Target does not exist in the matrix
        return false;
    }
}
