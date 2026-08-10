// Approach 1 --> HashMap with o(n) TC and SC

class Solution {
    public List<Integer> majorityElement(int[] nums) {

        int n = nums.length;

        // Stores each number as a key
        // and its frequency as the value
        Map<Integer, Integer> map = new HashMap<>();

        // Stores the elements that appear more than n/3 times
        List<Integer> result = new ArrayList<>();

        // STEP 1: Count the frequency of every element
        for (int i = 0; i < n; i++) {

            int num = nums[i];

            // If the number already exists in the map,
            // increase its frequency by 1
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }

            // If the number is appearing for the first time,
            // add it to the map with frequency 1
            else {
                map.put(num, 1);
            }
        }

        // STEP 2: Find elements whose frequency is > n/3
        for (int i = 0; i < n; i++) {

            // Check two things:
            // 1. The number is still present in the map
            // 2. Its frequency is greater than n/3
            if (map.containsKey(nums[i]) &&
                map.get(nums[i]) > n / 3) {

                // This number satisfies the majority condition
                result.add(nums[i]);

                // Remove it from the map so that we don't
                // add the same number again when we encounter it
                map.remove(nums[i]);
            }
        }

        // Return all elements appearing more than n/3 times
        return result;
    }
}
