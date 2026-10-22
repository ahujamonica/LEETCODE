class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();

        // Step 1: Build a monotonic stack
        for (int i = 0; i < num.length(); i++) {
            char digit = num.charAt(i);

            // Remove larger digits to minimize the number
            while (!st.isEmpty() && k > 0 && digit < st.peek()) {
                st.pop();
                k--;
            }

            st.push(digit);
        }

        // Step 2: Remove remaining digits from the end
        while (k > 0 && !st.isEmpty()) {
            st.pop();
            k--;
        }

        // Step 3: Build the result in reverse order
        StringBuilder result = new StringBuilder();

        while (!st.isEmpty()) {
            result.append(st.pop());
        }

        result.reverse();

        // Step 4: Remove leading zeros
        int index = 0;

        while (index < result.length() && result.charAt(index) == '0') {
            index++;
        }

        // Handle an empty result or all-zero result
        if (index == result.length()) return "0";

        return result.substring(index);
    }
}
