class Solution {
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Find length and tail
        int length = 1;
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Remove unnecessary rotations
        k = k % length;

        if (k == 0) {
            return head;
        }

        // Find the new tail
        ListNode newTail = head;

        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }

        // Node after newTail becomes the new head
        ListNode newHead = newTail.next;

        // Connect old tail to old head
        tail.next = head;

        // Break the list
        newTail.next = null;

        return newHead;
    }
}
