// Approach 1 - Brute force : taking a new nxn matrix to store the result and copying back

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // New Rotated matrix
        int[][] rotated = new int[n][n];

        // matrix[0][1] = rotated[1][3]
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                rotated[j][n - i - 1] = matrix[i][j];
            }
        }
        // Copying the rotated matrix back to the og
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = rotated[i][j];
            }
        }
        
    }
}

// Approach 2 - Optimized : Transpose + reverse of matrix in place

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 1. Transpose of matrix in place
        for(int i = 0; i <= n-2; i++){
            for(int j = i+1; j <= n-1; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 2. Reverse of the transposed matrix using 2 pointer approach
        for(int i = 0; i < n; i++){ // Taking ith row in each iteration
            int left = 0;
            int right = n-1;

            while(left < right){
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left++;
                right--;
            }
        }
    }
}
