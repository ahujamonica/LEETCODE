class Solution {
    public int longestConsecutive(int[] nums) {

        // Stores the longest consecutive sequence found
        int longestLength = 0;

        // Stores each number and whether it has been explored
        Map<Integer, Boolean> checkedMap = new HashMap<>();

        // Initially mark every number as unexplored
        for (int i = 0; i < nums.length; i++) {
            checkedMap.put(nums[i], false);
        }

        // Process every number
        for (int i = 0; i < nums.length; i++) {

            // The current number itself is part of the sequence
            int currentLength = 1;

            // Mark the current number as explored
            checkedMap.put(nums[i], true);

            // Check consecutive numbers in the forward direction
            int nextNum = nums[i] + 1;

            while (checkedMap.containsKey(nextNum)
                    && checkedMap.get(nextNum) == false) {

                // Found another consecutive number
                currentLength++;

                // Mark it as explored
                checkedMap.put(nextNum, true);

                // Move to the next number
                nextNum++;
            }

            // Check consecutive numbers in the backward direction
            int prevNum = nums[i] - 1;

            while (checkedMap.containsKey(prevNum)
                    && checkedMap.get(prevNum) == false) {

                // Found another consecutive number
                currentLength++;

                // Mark it as explored
                checkedMap.put(prevNum, true);

                // Move to the previous number
                prevNum--;
            }

            // Update the longest sequence found so far
            longestLength = Math.max(longestLength, currentLength);
        }

        return longestLength;
    }
}
