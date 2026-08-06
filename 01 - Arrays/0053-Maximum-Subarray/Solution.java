#Approach 1 - Brute Force :

class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int n = nums.length;

        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int sum = 0;

                for(int k = i; k <= j; k++){
                    sum = sum + nums[k];
                }
                max = Math.max(max, sum);
            }
        }

        return max;
    }
}


Result: ❌ Time Limit Exceeded (200 / 210 test cases passed)

Although this brute-force approach correctly generates every possible subarray and computes its sum, its O(N³) time complexity makes it impractical for large inputs. The repeated recalculation of overlapping subarray sums causes the solution to exceed the time limit.

 
