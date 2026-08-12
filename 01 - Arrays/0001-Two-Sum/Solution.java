class Solution {
    public int[] twoSum(int[] nums, int target) {

        // Stores:
        // Key   → number
        // Value → index of that number
        HashMap<Integer, Integer> mpp = new HashMap<>();

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {

            // Get the current number
            int num = nums[i];

            // Find the number required to reach the target
            int moreNeeded = target - num;

            // If we have already seen the required number,
            // we have found the pair
            if (mpp.containsKey(moreNeeded)) {

                // Return the current index and the index
                // of the required number
                return new int[]{i, mpp.get(moreNeeded)};
            }

            // Store the current number and its index
            // for future elements
            mpp.put(num, i);
        }

        // No valid pair found
        return new int[]{};
    }
}
