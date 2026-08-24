class Solution {
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // Step 1: Create copied nodes
        // and place them next to their originals
        Node curr = head;

        while (curr != null) {
            Node newNode = new Node(curr.val);

            newNode.next = curr.next;
            curr.next = newNode;

            curr = newNode.next;
        }

        // Step 2: Copy random pointers
        curr = head;

        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }

            curr = curr.next.next;
        }

        // Step 3: Separate original and copied lists
        curr = head;

        Node newHead = head.next;
        Node newCurr = newHead;

        while (curr != null) {

            curr.next = curr.next.next;
            curr = curr.next;

            if (curr != null) {
                newCurr.next = curr.next;
                newCurr = newCurr.next;
            }
        }

        return newHead;
    }
}
