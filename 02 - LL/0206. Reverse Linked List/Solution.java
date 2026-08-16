
class Solution {
    public ListNode reverseList(ListNode head) {

        // If the list is empty, there is nothing to reverse
        if (head == null) {
            return null;
        }

        // If the list contains only one node,
        // it is already reversed
        if (head.next == null) {
            return head;
        }

        // Initially, there is no previous node
        ListNode preNode = null;

        // Start from the head of the original list
        ListNode currentNode = head;

        // Process every node
        while (currentNode != null) {

            // Save the next node before changing the pointer
            ListNode nextNode = currentNode.next;

            // Reverse the current node's pointer
            currentNode.next = preNode;

            // Move preNode to the current node
            preNode = currentNode;

            // Move currentNode to the saved next node
            currentNode = nextNode;
        }

        // preNode is now the new head of the reversed list
        head = preNode;

        return head;
    }
}
