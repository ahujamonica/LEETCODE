class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length, k = 0;

        for (int i = k + 1; i < n; i++) {
            if (nums[i] > nums[k]) {
                nums[k + 1] = nums[i];
                k++;
            }
        }

        return k + 1;
    }
}
