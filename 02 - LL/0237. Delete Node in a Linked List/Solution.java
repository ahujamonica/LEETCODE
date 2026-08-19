class Solution {
    public void deleteNode(ListNode node) {

        // Copy the next node's value
        node.val = node.next.val;

        // Skip the next node
        node.next = node.next.next;
    }
}
