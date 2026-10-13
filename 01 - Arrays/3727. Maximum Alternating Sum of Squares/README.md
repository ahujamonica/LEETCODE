# ⚡ Sort the Squares, Maximize the Score | Greedy + Sorting in Java

🔗 **LeetCode 2897:** [Apply Operations on Array to Maximize Sum of Squares](https://leetcode.com/problems/apply-operations-on-array-to-maximize-sum-of-squares/)

> **Note:** Your method is named `maxAlternatingSum`, which doesn't match LeetCode 2897's required method signature. The code below follows your exact algorithm, but verify the problem statement before submitting it.

## 🧠 Intuition

We want to maximize an alternating sum of squared numbers:

\[
\text{Sum} = -a_0^2-a_1^2+\cdots
\]

Your code follows this idea:

- Square every number.
- Sort the squares in ascending order.
- Subtract the smallest squares.
- Add the remaining, larger squares.

**Why?** To maximize the result, we want smaller values in the negative positions and larger values in the positive positions.

## 💡 Approach

1. Create a `long` array to store the squares of the input numbers.
2. Sort the squared values.
3. Calculate `k = nums.length / 2`, representing the number of negative positions.
4. Subtract the first `k` smallest squares from `ans`.
5. Add all the remaining squares.
6. Return the final answer.

## 💻 Java Solution

```java
class Solution {
    public long maxAlternatingSum(int[] nums) {

        // Step 1: Square every number using long
        // to avoid integer overflow
        long[] a = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            a[i] = (long) nums[i] * nums[i];
        }

        // Step 2: Sort squares in ascending order
        Arrays.sort(a);

        long ans = 0;

        // Number of negative positions
        int k = nums.length / 2;

        // Step 3: Subtract the k smallest squares
        for (int i = 0; i < k; i++) {
            ans -= a[i];
        }

        // Step 4: Add the remaining larger squares
        for (int i = k; i < nums.length; i++) {
            ans += a[i];
        }

        return ans;
    }
}
```

## 📝 Dry Run

Suppose:

```text
nums = [1, 2, 3, 4]
```

**Step 1: Square each number**

```text
[1, 4, 9, 16]
```

**Step 2: Sort the squares**

```text
[1, 4, 9, 16]
```

**Step 3: Calculate `k`**

```text
k = 4 / 2 = 2
```

Subtract the two smallest squares:

\[
ans = 0-1-4=-5
\]

Add the remaining squares:

\[
ans=-5+9+16=20
\]

**Final result:** `20`

## 🔑 Key Trick

The key idea is **sorting before assigning positive and negative signs**:

- Smaller squares go into negative positions.
- Larger squares go into positive positions.
- Casting to `long` before multiplication helps prevent overflow.

## ⏱️ Complexity

- **Time:** \(O(n\log n)\) — sorting dominates.
- **Space:** \(O(n)\) — the squared array stores all \(n\) values.

## 🎯 Key Takeaway

**Sort the squares, subtract the smallest values, and add the largest values.**

If this helped, don't forget to **upvote!** 🚀
