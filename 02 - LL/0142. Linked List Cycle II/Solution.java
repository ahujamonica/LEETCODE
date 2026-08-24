
public class Solution {
    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        // Detect the cycle
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {

                // Find the start of the cycle
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }

                return slow;
            }
        }

        // No cycle
        return null;
    }
}
