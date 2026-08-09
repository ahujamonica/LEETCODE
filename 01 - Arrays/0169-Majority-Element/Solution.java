class Solution {
    public int majorityElement(int[] nums) {

        int candidate = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            // If there are no active votes,
            // choose the current element as the candidate
            if (count == 0) {
                candidate = nums[i];
            }

            // Same element supports the candidate
            if (nums[i] == candidate) {
                count++;
            }

            // Different element cancels one vote
            // of the current candidate
            else {
                count--;
            }
        }

        // The problem guarantees that a majority element exists
        return candidate;
    }
}
