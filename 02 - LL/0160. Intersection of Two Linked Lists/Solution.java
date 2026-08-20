public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        // Find the length of both lists
        int lenA = getListLength(headA);
        int lenB = getListLength(headB);

        // Move the longer list forward
        while (lenA > lenB) {
            lenA--;
            headA = headA.next;
        }

        while (lenB > lenA) {
            lenB--;
            headB = headB.next;
        }

        // Move both pointers together
        // until they point to the same node
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        // Intersection node or null
        return headA;
    }

    private int getListLength(ListNode head) {

        int len = 0;

        while (head != null) {
            len++;
            head = head.next;
        }

        return len;
    }
}
