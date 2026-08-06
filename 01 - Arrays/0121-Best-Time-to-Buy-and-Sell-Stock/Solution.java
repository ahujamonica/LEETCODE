// Approach 1 - Brute Force

class Solution {
    public int maxProfit(int[] prices) {

        int maxProfit = 0;

        for(int i = 0; i < prices.length; i++){

            for(int j = i + 1; j < prices.length; j++){

                int profit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }
}

// Approach 2 - Optimal (Greedy)

class Solution {
    public int maxProfit(int[] prices) {

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            // Profit if we sell on the current day
            int currentProfit = prices[i] - minPrice;

            // Update maximum profit
            maxProfit = Math.max(maxProfit, currentProfit);

            // Update minimum buying price
            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }
}
```
