class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Create a dummy node to simplify building the result
        ListNode resultant = new ListNode(Integer.MIN_VALUE);

        // Keep a reference to the dummy node
        ListNode headNode = resultant;

        // Continue while both lists have nodes
        while (list1 != null && list2 != null) {

            // Take the smaller node
            if (list1.val <= list2.val) {

                // Attach list1's current node
                resultant.next = list1;

                // Move list1 forward
                list1 = list1.next;

            } else {

                // Attach list2's current node
                resultant.next = list2;

                // Move list2 forward
                list2 = list2.next;
            }

            // Move resultant to the newly added node
            resultant = resultant.next;
        }

        // Attach whichever list still has remaining nodes
        if (list1 == null) {
            resultant.next = list2;
        } else {
            resultant.next = list1;
        }

        // Skip the dummy node
        return headNode.next;
    }
}
