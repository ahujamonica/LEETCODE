class Solution {
    public int findDuplicate(int[] nums) {

        // Initialize both pointers at index 0
        int slow = 0;
        int fast = 0;

        // Phase 1: Detect the cycle and find the meeting point
        do {
            // Slow moves one step
            slow = nums[slow];

            // Fast moves two steps
            fast = nums[nums[fast]];

        } while (slow != fast);

        // Phase 2: Find the entrance of the cycle
        // Reset slow to the starting point
        slow = 0;

        // Move both pointers one step at a time
        // They will meet at the cycle entrance
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        // The cycle entrance is the duplicate number
        return slow;
    }
}
