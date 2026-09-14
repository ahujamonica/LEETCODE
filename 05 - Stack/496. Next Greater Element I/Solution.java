
class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // nextGreater[value] stores the
        // Next Greater Element of that value
        int[] nextGreater = new int[10001];

        // Stack stores possible greater elements
        Stack<Integer> stack = new Stack<>();

        // Traverse nums2 from right to left
        for (int i = nums2.length - 1; i >= 0; i--) {

            // Remove elements that are smaller than
            // or equal to the current element.
            // They cannot be the Next Greater Element.
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }

            // If stack is empty, there is no greater
            // element on the right
            if (stack.isEmpty()) {
                nextGreater[nums2[i]] = -1;
            } 
            
            // Otherwise, the top of the stack is
            // the Next Greater Element
            else {
                nextGreater[nums2[i]] = stack.peek();
            }

            // Add current element to the stack
            // so it can be a candidate for elements
            // to its left
            stack.push(nums2[i]);
        }

        // Replace every element in nums1 with
        // its precomputed Next Greater Element
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = nextGreater[nums1[i]];
        }

        // Return the final answer
        return nums1;
    }
}
