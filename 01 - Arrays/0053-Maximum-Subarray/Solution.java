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

 
# Approach 2 - Better Solution :

    class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int n = nums.length;

        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = i; j < n; j++){
                sum = sum + nums[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}

Result: ❌ Time Limit Exceeded — 205 / 210 testcases passed

Notice the improvement though:

O(N³) → 200 / 210
O(N²) → 205 / 210

So your optimization worked, but we need to go one step further: O(N).

# Approach 3 - Kadane's Algorithm

    class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++){
            sum = sum + nums[i];

            if(sum > max){
                max = sum;
            }

            if(sum < 0){
                sum = 0;
            }
        }

        return max;
    }
}
