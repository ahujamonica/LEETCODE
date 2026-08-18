class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // Create a dummy node before the head
        ListNode dummy = new ListNode(-1);

        // Connect dummy to the original list
        dummy.next = head;

        // Initialize both pointers at dummy
        ListNode ptr1 = dummy;
        ListNode ptr2 = dummy;

        // Move ptr2 n nodes ahead
        for (int i = 0; i < n; i++) {
            ptr2 = ptr2.next;
        }

        // Move both pointers together
        // until ptr2 reaches the last node
        while (ptr2.next != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // ptr1 is now immediately before
        // the node that needs to be removed
        ptr1.next = ptr1.next.next;

        // Return the actual head, skipping the dummy node
        return dummy.next;
    }
}
