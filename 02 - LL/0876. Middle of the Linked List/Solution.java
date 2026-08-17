class Solution {
    public ListNode middleNode(ListNode head) {

        // Slow moves one node at a time
        ListNode slow = head;

        // Fast moves two nodes at a time
        ListNode fast = head;

        // Continue while fast can safely move two steps
        while (fast != null && fast.next != null) {

            // Move slow by one node
            slow = slow.next;

            // Move fast by two nodes
            fast = fast.next.next;
        }

        // Slow is now pointing to the middle node
        // For even length, it points to the second middle
        return slow;
    }
}
