# ⚡ Keep It Simple | Array Index + Divisibility Trick

🔗 **LeetCode:** https://leetcode.com/problems/sum-of-squares-of-special-elements/

## 🧠 Intuition

We need to calculate the sum of squares of elements whose **1-based index divides the array length**.

For every element at index `i`, check whether `n % (i + 1) == 0`. If true, add the square of `nums[i]` to the sum.

## 💡 Approach

- Store the array length in `n`.
- Traverse the array using a loop.
- Check whether `n` is divisible by `i + 1`.
- If it is, add `nums[i] * nums[i]` to `sum`.
- Return the final sum.

## 💻 Java Solution

```java
class Solution {
    public int sumOfSquares(int[] nums) {

        int sum = 0;
        int n = nums.length;

        for(int i = 0; i < n; i++) {

            // Check whether the 1-based index divides n
            if(n % (i + 1) == 0) {

                // Add the square of the special element
                sum += nums[i] * nums[i];
            }
        }

        return sum;
    }
}
```

## 🧪 Example

```text
nums = [1, 2, 3, 4]
n = 4
```

| Index `i` | 1-based index `i + 1` | `4 % (i + 1)` | Action |
|---:|---:|---:|---|
| 0 | 1 | 0 | Add \(1^2 = 1\) |
| 1 | 2 | 0 | Add \(2^2 = 4\) |
| 2 | 3 | 1 | Skip |
| 3 | 4 | 0 | Add \(4^2 = 16\) |

Result:

```text
1 + 4 + 16 = 21
```

## 🔑 Key Trick

Array indices start at `0`, but the problem uses **1-based positions**.

That's why we check:

```java
n % (i + 1) == 0
```

## ⏱️ Complexity

- **Time:** `O(n)` — each element is checked once.
- **Space:** `O(1)` — only a few variables are used.

## 🎯 Key Takeaway

**Check divisibility using the 1-based index, square the qualifying elements, and add them.**

⭐ If this helped, please consider giving this solution an upvote!
