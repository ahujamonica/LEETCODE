class Solution {
    public int sumOfSquares(int[] nums) {

        int sum = 0;
        int n = nums.length;

        for(int i = 0; i < n; i++) {

            // Check whether the 1-based index divides n
            if(n % (i + 1) == 0) {

                // Add the square of the special element
                sum += nums[i] * nums[i];
            }
        }

        return sum;
    }
}
