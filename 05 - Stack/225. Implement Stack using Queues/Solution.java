
class MyStack {

    // Queue used to implement the Stack
    private Queue<Integer> q;

    // Constructor
    public MyStack() {

        // Create an empty Queue
        q = new LinkedList<>();
    }

    // Push element x onto the Stack
    public void push(int x) {

        // Store the number of elements already present
        // We only want to rotate these old elements
        int size = q.size();

        // Add the new element to the back of the Queue
        q.add(x);

        // Rotate all old elements to the back
        // This brings the newly added element to the front
        for (int i = 0; i < size; i++) {

            // Remove the front element
            // and immediately add it to the back
            q.add(q.poll());
        }
    }

    // Remove and return the top element of the Stack
    public int pop() {

        // Stack top is always at the front of the Queue
        return q.poll();
    }

    // Return the top element without removing it
    public int top() {

        // peek() returns the front element
        // without removing it
        return q.peek();
    }

    // Check whether the Stack is empty
    public boolean empty() {

        return q.isEmpty();
    }
}
