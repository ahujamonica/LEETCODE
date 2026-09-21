class Solution {
    public int myAtoi(String s) {

        // Remove leading and trailing spaces
        s = s.trim();

        // sign stores +1 or -1
        // i tells us which character we are currently reading
        int i = 0, sign = 1;

        // Store the number while building it
        // long helps us handle overflow safely
        long res = 0;

        // Empty string after trim
        if(s.length() == 0) {
            return 0;
        }

        // Check for negative sign
        if(s.charAt(0) == '-') {
            sign = -1;
            i++;
        }
        // Check for positive sign
        else if(s.charAt(0) == '+') {
            i++;
        }

        // Read digits
        while(i < s.length()) {

            char ch = s.charAt(i);

            // Stop at the first non-digit character
            if(ch < '0' || ch > '9') {
                break;
            }

            // Convert character to digit and add it to res
            res = res * 10 + (ch - '0');

            // Handle positive overflow
            if(sign * res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            // Handle negative overflow
            if(sign * res < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            // Move to the next character
            i++;
        }

        // Apply the sign and return the result
        return (int) (res * sign);
    }
}
