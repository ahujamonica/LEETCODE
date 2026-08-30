class Solution {
    public int coinChange(int[] coins, int amount) {

        // dp[i] = minimum number of coins needed to make amount i
        int[] dp = new int[amount + 1];

        // Initially assume every amount is impossible
        Arrays.fill(dp, amount + 1);

        // Base case: 0 coins are needed to make amount 0
        dp[0] = 0;

        // Try each coin
        for (int j = 0; j < coins.length; j++) {

            int coin = coins[j];

            // Try using this coin for every possible amount
            for (int i = coin; i <= amount; i++) {

                // Either keep the current answer,
                // or use this coin + best answer for the remaining amount
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        // If amount is still impossible, return -1
        if (dp[amount] > amount) {
            return -1;
        } else {
            return dp[amount];
        }
    }
}
