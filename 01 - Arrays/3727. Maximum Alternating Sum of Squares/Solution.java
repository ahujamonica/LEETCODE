class Solution {
    public long maxAlternatingSum(int[] nums) {

        // Step 1: Square every number using long
        // to avoid integer overflow
        long[] a = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            a[i] = (long) nums[i] * nums[i];
        }

        // Step 2: Sort squares in ascending order
        Arrays.sort(a);

        long ans = 0;

        // Number of negative positions
        int k = nums.length / 2;

        // Step 3: Subtract the k smallest squares
        for (int i = 0; i < k; i++) {
            ans -= a[i];
        }

        // Step 4: Add the remaining larger squares
        for (int i = k; i < nums.length; i++) {
            ans += a[i];
        }

        return ans;
    }
}
