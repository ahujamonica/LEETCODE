class Solution {
    public boolean isPalindrome(ListNode head) {

        // Find the middle
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Skip middle for odd-length lists
        if (fast != null) {
            slow = slow.next;
        }

        // Reverse the second half
        slow = reverseList(slow);

        // Start comparison from the head
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }

            fast = fast.next;
            slow = slow.next;
        }

        return true;
    }

    private ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;

            current.next = prev;

            prev = current;
            current = next;
        }

        return prev;
    }
}
