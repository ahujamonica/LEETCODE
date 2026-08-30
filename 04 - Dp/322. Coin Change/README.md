# 🪙 322. Coin Change | 🚀 1D Dynamic Programming

> 📂 Topic: Dynamic Programming  
> 🎯 Pattern: 1D DP / Unbounded Knapsack  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n × amount) | 💾 Space: O(amount)

---

## 🔗 Problem

[LeetCode 322 — Coin Change](https://leetcode.com/problems/coin-change/)

---

## 🧠 Intuition

We need to find the **minimum number of coins** required to make the given `amount`.

We define:

```text
dp[i] = minimum number of coins needed to make amount i
```

For example:

```text
coins = [1, 2, 5]
amount = 6
```

The optimal answer is:

```text
6 = 5 + 1
```

So:

```text
dp[6] = 2
```

---

## 🚀 Approach

### 1️⃣ Initialize the DP Array

```java
int[] dp = new int[amount + 1];
Arrays.fill(dp, amount + 1);
```

We initially assume every amount is impossible.

Why `amount + 1`?

Because we can never need more than `amount` coins when the smallest useful coin is `1`.

So `amount + 1` acts as an **infinity/impossible value**.

---

### 2️⃣ Base Case

```java
dp[0] = 0;
```

Making amount `0` requires `0` coins.

This is the starting point for calculating all other amounts.

---

### 3️⃣ Try Every Coin

```java
for (int j = 0; j < coins.length; j++) {
    int coin = coins[j];
```

We consider each available coin one by one.

For example:

```text
coins = [1, 2, 5]

coin = 1
coin = 2
coin = 5
```

---

### 4️⃣ Try Every Possible Amount

```java
for (int i = coin; i <= amount; i++)
```

We start from `coin` because a coin cannot be used to make an amount smaller than itself.

For example, if:

```text
coin = 5
```

we start with:

```text
i = 5
```

and try:

```text
5, 6, 7, 8, ..., amount
```

---

### 5️⃣ DP Transition

```java
dp[i] = Math.min(dp[i], dp[i - coin] + 1);
```

This means:

```text
Current best answer
        OR
Best way to make (i - coin) + current coin
```

The `+1` represents the **coin we are currently using**.

For example:

```text
i = 6
coin = 5

remaining = 6 - 5 = 1
```

If:

```text
dp[1] = 1
```

then:

```text
dp[6] = dp[1] + 1
      = 1 + 1
      = 2
```

So:

```text
6 = 5 + 1
```

---

## 💻 Java Solution

```java
class Solution {
    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int j = 0; j < coins.length; j++) {
            int coin = coins[j];

            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        if (dp[amount] > amount) {
            return -1;
        } else {
            return dp[amount];
        }
    }
}
```

---

## 🔍 Example

```text
coins = [1, 2, 5]
amount = 6
```

After processing the coins:

```text
Amount:  0  1  2  3  4  5  6
dp:      0  1  1  2  2  1  2
```

Therefore:

```text
dp[6] = 2
```

The answer is:

```text
5 + 1 = 6
```

---

## ❌ Impossible Case

```text
coins = [2]
amount = 3
```

There is no combination of `2`s that makes `3`.

Therefore:

```text
dp[3] = amount + 1
```

and:

```java
if (dp[amount] > amount) {
    return -1;
}
```

returns:

```text
-1
```

---

## 🎯 Key Takeaway

Remember the core formula:

```text
dp[i] = min(
    current best,
    dp[i - coin] + 1
)
```

Think:

```text
Amount i
   ↓
Take a coin
   ↓
Remaining = i - coin
   ↓
Best way to make remaining amount
   ↓
+ 1 for the coin we just used
```

---

## 📊 Complexity

### Time Complexity

```text
O(n × amount)
```

where `n = coins.length`.

We process every coin for every possible amount.

### Space Complexity

```text
O(amount)
```

We use a single DP array of size `amount + 1`.

---

## 🏆 Final Complexity

```text
Time  : O(n × amount)
Space : O(amount)
```

> ⭐ **If this explanation helped you understand the 1D DP approach, please upvote! 🙌**
