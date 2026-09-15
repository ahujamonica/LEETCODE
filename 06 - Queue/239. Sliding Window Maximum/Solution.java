
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        Deque<Integer> deque = new ArrayDeque<>();

        int[] result = new int[n-k+1];

        // Build the first window
        for(int i = 0; i < k; i++){

            // Remove smaller/equal elements from the back
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }

            // Add current index
            deque.offerLast(i);
        }

        // Store maximum of the first window
        result[0] = nums[deque.peekFirst()];

        // Process remaining windows
        for(int i = k; i < n; i++){

            // Remove expired index
            if(deque.peekFirst() <= i-k){
                deque.pollFirst();
            }

            // Remove smaller/equal elements from the back
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }

            // Add current index
            deque.offerLast(i);

            // Store current maximum
            result[i-k+1] = nums[deque.peekFirst()];
        }

        return result;
    }
}
