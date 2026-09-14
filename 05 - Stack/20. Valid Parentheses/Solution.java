
class Solution {

    public boolean isValid(String s) {

        // Stack stores the closing bracket
        // that we expect to see next
        Stack<Character> stack = new Stack<>();

        // Traverse through every character of the string
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // If we see an opening parenthesis '(',
            // push its expected closing bracket ')'
            if (c == '(') {
                stack.push(')');
            }

            // If we see an opening curly bracket '{',
            // push its expected closing bracket '}'
            else if (c == '{') {
                stack.push('}');
            }

            // If we see an opening square bracket '[',
            // push its expected closing bracket ']'
            else if (c == '[') {
                stack.push(']');
            }

            // Otherwise, we have encountered a closing bracket
            else {

                // If the stack is empty, there is no
                // opening bracket available to match it
                //
                // OR
                //
                // If the current closing bracket does not
                // match the expected bracket at the top,
                // the parentheses are invalid
                if (stack.isEmpty() || stack.peek() != c) {
                    return false;
                }

                // The current closing bracket matched
                // the expected bracket, so remove it
                stack.pop();
            }
        }

        // The string is valid only if all opening brackets
        // have been matched and the stack is completely empty
        return stack.isEmpty();
    }
}
