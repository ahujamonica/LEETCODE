# 🚀 121. Best Time to Buy and Sell Stock

> 📂 Topic: Arrays  
> 🎯 Pattern: Greedy, One Pass Traversal  
> 🏷️ Named Algorithm: Minimum Price Tracking (Greedy)  
> ⭐ Difficulty: Easy  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

# 🔗 Problem Link

https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

---

# 🧠 Pattern Recognition

Whenever the problem mentions:

- Buy once, Sell once
- Maximum Profit
- Stock Prices
- Best transaction

Think:

```
Can I keep track of the
minimum value seen so far?

↓

Can I calculate the profit
for today's selling price?

↓

Greedy
```

---

# 💡 Intuition

The objective is to maximize the profit by choosing **exactly one buying day** and **one later selling day**.

The brute-force idea is straightforward:

- Buy on every possible day.
- Sell on every valid day after buying.
- Compute the profit.
- Keep track of the maximum.

Although correct, this repeatedly compares every buying day with every selling day, resulting in unnecessary work.

Can we avoid checking every previous buying day?

Yes.

Notice that for any selling day, the only buying day that matters is the one with the **minimum price seen so far**.

Therefore, while traversing the array once:

- Keep track of the minimum buying price.
- Assume today's price is the selling price.
- Compute today's profit.
- Update the maximum profit if today's transaction is better.

This greedy observation allows us to solve the problem in a single traversal.

---

# 📈 Evolution of the Solution

```
Try every buying day

↓

Try every selling day

↓

Brute Force

O(n²)

↓

Observe that only the
minimum buying price matters

↓

Greedy

↓

One Pass

O(n)
```

---

# 🐢 Approach 1 — Brute Force

## Idea

Try every possible buying day.

For each buying day,

try every valid selling day.

Calculate the profit and keep the maximum.

---

### Dry Run

```
Prices

7 1 5 3 6 4
```

Buy at

```
7
```

Possible profits

```
-6 -2 -4 -1 -3
```

Maximum

```
0
```

---

Buy at

```
1
```

Possible profits

```
4 2 5 3
```

Maximum

```
5
```

Continue for every buying day.

---

### Complexity

**Time**

```
O(n²)
```

**Space**

```
O(1)
```

---

# 🚀 Approach 2 — Greedy (Optimal)

## Key Observation

Suppose the prices are

```
7 1 5 3 6 4
```

If today is

```
6
```

Should we compare it with every previous day?

No.

The only buying price that matters is

```
1
```

because buying at any higher price can never produce a better profit.

Therefore,

for every day,

we only need to know

```
Minimum Price Seen So Far
```

Then,

```
Today's Profit

=

Current Price

-

Minimum Price
```

Update the answer,

and continue.

---

# 🔍 Visual Intuition

```
Prices

7   1   5   3   6   4
```

```
Minimum Price

7

↓

1

↓

1

↓

1

↓

1

↓

1
```

```
Profit

0

↓

0

↓

4

↓

2

↓

5

↓

3
```

Maximum

```
5
```

---

# 📝 Dry Run

Input

```
[7,1,5,3,6,4]
```

| Day | Price | Minimum Price | Profit Today | Maximum Profit |
|----:|------:|--------------:|-------------:|---------------:|
|1|7|7|0|0|
|2|1|1|0|0|
|3|5|1|4|4|
|4|3|1|2|4|
|5|6|1|5|5|
|6|4|1|3|5|

Final Answer

```
5
```

---

# 🤔 Things That Confused Me

### ❓1. Why don't we compare every previous buying day?

Because only the **lowest buying price** can maximize the profit.

Every higher buying price will always produce an equal or smaller profit.

Hence,

tracking just the minimum price is sufficient.

---

### ❓2. Why do we update the profit before updating the minimum price?

For the current day,

we first calculate the profit assuming we sell today.

Only after that do we update the minimum buying price for future days.

This ensures we never buy and sell on the same day in the calculation.

---

### ❓3. Why is the profit initialized as `0`?

If the stock price keeps decreasing,

no profitable transaction exists.

Example

```
7 6 5 4 3
```

The best choice is

```
Do not make any transaction.
```

Hence,

the answer remains

```
0
```

---

### ❓4. My Mistakes

❌ Initially, I wrote

```java
prices[minPrice]
```

instead of

```java
minPrice
```

`minPrice` stores the **minimum value**, not the index.

---

❌ Initially, I wrote

```java
for(int i = 1; i < prices.length - 1; i++)
```

which skipped the last day.

The correct loop is

```java
for(int i = 1; i < prices.length; i++)
```

---

# ❌ Common Mistakes

- Treating `minPrice` as an index instead of a value.
- Forgetting to process the last element.
- Updating the minimum price before computing today's profit.
- Trying to compare every previous buying day instead of tracking only the minimum.

---

# ⏱️ Complexity Analysis

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(1) |
| Greedy (Optimal) | O(n) | O(1) |

---

# 💼 Interview Notes

Whenever you hear

> **Maximum Profit with One Buy and One Sell**

immediately think

```
Track Minimum Price

↓

Assume today's price is
the selling price

↓

Calculate Profit

↓

Update Answer
```

This is a classic **Greedy** problem where we make the best local decision (keeping the minimum buying price) to obtain the global optimum.

---

# 🎯 Takeaway

- Brute force helps us understand the problem but performs unnecessary comparisons.
- For every selling day, only the **minimum buying price seen so far** matters.
- Instead of remembering every previous price, maintain just one variable: **`minPrice`**.
- This simple greedy observation reduces the complexity from **O(n²)** to **O(n)** while using constant extra space.

---

### ⭐ if you enjoyed this explanation!

Happy Coding! 🚀✨
