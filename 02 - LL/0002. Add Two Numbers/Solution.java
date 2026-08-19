class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // Dummy node to build the result list
        ListNode result = new ListNode(0);

        // Pointer used to build the result
        ListNode ptr = result;

        // Carry from the previous digit
        int carry = 0;

        // Continue while either list has nodes
        while (l1 != null || l2 != null) {

            // Start with the carry
            int sum = carry;

            // Add digit from l1 if available
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            // Add digit from l2 if available
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // Calculate carry for the next position
            carry = sum / 10;

            // Keep only the current digit
            sum = sum % 10;

            // Add current digit to the result
            ptr.next = new ListNode(sum);

            // Move result pointer forward
            ptr = ptr.next;
        }

        // If a carry is still left, add it
        if (carry == 1) {
            ptr.next = new ListNode(1);
        }

        // Skip the dummy node
        return result.next;
    }
}
