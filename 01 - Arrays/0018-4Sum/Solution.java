
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        // A quadruplet requires at least 4 elements
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }

        // Sort the array so we can use two pointers
        Arrays.sort(nums);

        // HashSet stores only unique quadruplets
        Set<List<Integer>> result = new HashSet<>();

        // Fix the first element
        for (int first = 0; first <= nums.length - 4; first++) {

            // Fix the second element
            for (int second = first + 1;
                 second <= nums.length - 3;
                 second++) {

                // Left pointer starts after second
                int left = second + 1;

                // Right pointer starts at the last element
                int right = nums.length - 1;

                // Search for the remaining two elements
                while (left < right) {

                    // Use long to prevent integer overflow
                    long sum = (long) nums[first]
                             + nums[second]
                             + nums[left]
                             + nums[right];

                    if (sum == target) {

                        // Found a valid quadruplet
                        result.add(
                            Arrays.asList(
                                nums[first],
                                nums[second],
                                nums[left],
                                nums[right]
                            )
                        );

                        // Move both pointers to continue searching
                        left++;
                        right--;

                    } else if (sum < target) {

                        // Sum is too small.
                        // Move left forward to get a larger value.
                        left++;

                    } else {

                        // Sum is too large.
                        // Move right backward to get a smaller value.
                        right--;
                    }
                }
            }
        }

        // Convert Set of unique quadruplets into List
        return new ArrayList<>(result);
    }
}
