class Solution {
    public int longestValidParentheses(String s) {

        // Stack stores indices, not brackets.
        Stack<Integer> stack = new Stack<>();

        // -1 acts as a boundary before the string starts.
        // It helps us calculate the length of a valid substring.
        stack.push(-1);

        int maxLength = 0;

        for(int i = 0; i < s.length(); i++) {

            // If we see '(', store its index.
            // This '(' may be matched with a ')' later.
            if(s.charAt(i) == '(') {
                stack.push(i);
            }

            // If we see ')', try to match it with
            // the most recent unmatched '('.
            else {

                // Remove the matching '(' index.
                stack.pop();

                // If the stack becomes empty,
                // there is no '(' available to match
                // the current ')'.
                if(stack.isEmpty()) {

                    // Current ')' becomes a new boundary.
                    // Any valid substring after this index
                    // must start after this position.
                    stack.push(i);
                }

                // Stack is not empty, so we have found
                // a valid parentheses substring.
                else {

                    // The index at the top of the stack
                    // represents the position just before
                    // the current valid substring.
                    //
                    // So:
                    // current index - boundary index
                    // = length of valid substring
                    int length = i - stack.peek();

                    // Keep the longest valid length found so far.
                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }
}
