class Solution {
    public double myPow(double x, int n) {

        // Handle x = 0
        if (x == 0) {
            return 0;
        }

        // Any non-zero number raised to 0 is 1
        if (n == 0) {
            return 1;
        }

        // Use long to safely handle Integer.MIN_VALUE
        long exp = n;

        // Convert negative exponent:
        // x^(-n) = (1/x)^n
        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }

        // Stores the accumulated result
        double ans = 1;

        while (exp > 0) {

            // If exponent is odd,
            // take one x and add it to the answer
            if (exp % 2 == 1) {
                ans = ans * x;
                exp = exp - 1;
            }

            // If exponent is even,
            // square the base and halve the exponent
            else {
                x = x * x;
                exp = exp / 2;
            }
        }

        return ans;
    }
}
