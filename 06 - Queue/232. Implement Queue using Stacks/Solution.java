class MyQueue {

    // Stack 1 is used to store newly pushed elements
    Stack<Integer> s1;

    // Stack 2 is used to remove and view elements
    // in the correct Queue order
    Stack<Integer> s2;

    // Constructor
    public MyQueue() {

        // Create both empty stacks
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    // Add an element to the back of the Queue
    public void push(int x) {

        // Simply push the new element into s1
        s1.push(x);
    }

    // Remove and return the element from the front of the Queue
    public int pop() {

        // If s2 is empty, we need to transfer
        // all elements from s1 to s2
        if (s2.isEmpty()) {

            // Move every element from s1 to s2
            // This reverses their order
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        // The top of s2 is now the front of the Queue
        return s2.pop();
    }

    // Return the front element without removing it
    public int peek() {

        // If s2 is empty, transfer elements from s1
        if (s2.isEmpty()) {

            // Reverse the order by moving
            // all elements from s1 to s2
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        // The top of s2 represents the front of the Queue
        return s2.peek();
    }

    // Check whether the Queue is empty
    public boolean empty() {

        // Queue is empty only if BOTH stacks are empty
        return s1.isEmpty() && s2.isEmpty();
    }
}
