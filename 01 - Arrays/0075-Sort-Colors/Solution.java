// Approach 1 - Sorting algorithm : BRUTE FORCE

class Solution {
    public void sortColors(int[] nums) {

        // Simply sort the array using Java's built-in sorting algorithm.
        // Java uses Dual-Pivot QuickSort for primitive arrays.
        // Time: O(n log n), Space: O(log n)

        Arrays.sort(nums);
    }
}

// Approach 2 - Counting and printing : BETTER

class Solution {
    public void sortColors(int[] nums) {

        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        // Step 1: Count the occurrences of 0s, 1s and 2s
        for (int num : nums) {
            if (num == 0)
                count0++;
            else if (num == 1)
                count1++;
            else
                count2++;
        }

        // Step 2: Overwrite the array

        // Fill all the 0s
        for (int i = 0; i < count0; i++) {
            nums[i] = 0;
        }

        // Fill all the 1s
        for (int i = count0; i < count0 + count1; i++) {
            nums[i] = 1;
        }

        // Fill the remaining positions with 2s
        for (int i = count0 + count1; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
}

// Approach 3 - DNF Algorithm : OPTIMAL

class Solution {
    public void sortColors(int[] nums) {

        // low  -> boundary of 0s
        // mid  -> current element being processed
        // high -> boundary of 2s

        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        // Traverse until the unknown region is exhausted
        while (mid <= high) {

            // Case 1: Current element is 0
            // Place it in the 0s region
            if (nums[mid] == 0) {

                swap(nums, low, mid);

                low++;
                mid++;
            }

            // Case 2: Current element is 1
            // It is already in the correct region
            else if (nums[mid] == 1) {

                mid++;
            }

            // Case 3: Current element is 2
            // Move it to the end of the array
            else {

                swap(nums, mid, high);

                // Do NOT increment mid here.
                // The element swapped from the end is still unprocessed.
                high--;
            }
        }
    }

    // Helper method to swap two elements
    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
