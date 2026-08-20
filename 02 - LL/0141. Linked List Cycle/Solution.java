public class Solution {
    public boolean hasCycle(ListNode head) {

        ListNode slowptr = head;
        ListNode fastptr = head;

        while (fastptr != null && fastptr.next != null) {

            // Move slow pointer one step
            slowptr = slowptr.next;

            // Move fast pointer two steps
            fastptr = fastptr.next.next;

            // If both point to the same node, a cycle exists
            if (slowptr == fastptr) {
                return true;
            }
        }

        return false;
    }
}
