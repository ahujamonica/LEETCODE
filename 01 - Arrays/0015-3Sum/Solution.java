class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        // If the array has fewer than 3 elements,
        // a triplet cannot be formed
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        // Sort the array so we can use two pointers
        Arrays.sort(nums);

        // HashSet stores only unique triplets
        Set<List<Integer>> result = new HashSet<>();

        // Fix the first element of the triplet
        for (int i = 0; i < nums.length - 2; i++) {

            // Left pointer starts after i
            int left = i + 1;

            // Right pointer starts at the end
            int right = nums.length - 1;

            // Search for the other two elements
            while (left < right) {

                // Calculate the sum of the three numbers
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    // Found a valid triplet
                    result.add(
                        Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                        )
                    );

                    // Move both pointers to continue searching
                    left++;
                    right--;

                } else if (sum < 0) {

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

        // Convert the Set of unique triplets into a List
        return new ArrayList<>(result);
    }
}
