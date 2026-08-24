
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        // Check if at least k nodes are available
        ListNode curr = head;

        for (int i = 0; i < k; i++) {
            if (curr == null) {
                return head;
            }

            curr = curr.next;
        }

        // Reverse exactly k nodes
        ListNode prev = null;
        curr = head;

        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;

            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Recursively process the remaining groups
        head.next = reverseKGroup(curr, k);

        // prev is the new head of the reversed group
        return prev;
    }
}
