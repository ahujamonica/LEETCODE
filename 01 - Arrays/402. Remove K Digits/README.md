# 🧹 Pop the Bigger Digits | Greedy Monotonic Stack in Java

🔗 **LeetCode 402:** [Remove K Digits](https://leetcode.com/problems/remove-k-digits/)

## 🧠 Intuition

We are given a number as a string `num` and an integer `k`. Our goal is to remove exactly `k` digits to obtain the **smallest possible number**.

The key idea is **Greedy + Monotonic Stack**.

Whenever the current digit is smaller than the digit at the top of the stack, we remove the larger digit to make the number smaller.

For example:

```text
num = "1432", k = 1

Stack: 1 → 14
Current digit = 3

3 < 4, so remove 4.

Result = "132"
```

## 💡 Approach

1. **Build a monotonic stack:** Traverse the digits from left to right.
2. **Remove larger digits:** While the stack is not empty, `k > 0`, and the current digit is smaller than the stack's top, pop the top digit and decrement `k`.
3. **Push the current digit:** Add it to the stack.
4. **Remove remaining digits:** If `k > 0` after traversal, remove digits from the end of the stack.
5. **Build the result:** Pop the stack into a `StringBuilder` and reverse it.
6. **Remove leading zeros:** Skip zeros at the beginning. If nothing remains, return `"0"`.

## 💻 Java Solution

```java
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
```

## 📝 Dry Run

**Input:** `num = "1432219", k = 3`

| Current digit | Action | Stack |
|---|---|---|
| `1` | Push | `1` |
| `4` | Push | `14` |
| `3` | Pop `4`, push `3` | `13` |
| `2` | Pop `3`, push `2` | `12` |
| `2` | Push | `122` |
| `1` | Pop `2`, push `1` | `121` |
| `9` | Push | `1219` |

Three digits have been removed: `4`, `3`, and one `2`.

**Output:** `"1219"`

## 🔑 Key Trick

```java
while (!st.isEmpty() && k > 0 && digit < st.peek())
```

This is the heart of the solution!

- A smaller incoming digit removes a larger previous digit.
- If `k` remains after traversing the string, remove digits from the end.
- Always remove leading zeros before returning the answer.

## ⏱️ Complexity

- **Time:** \(O(n)\) — each digit is pushed and popped at most once.
- **Space:** \(O(n)\) — the stack and result can hold up to `n` digits.

## 🎯 Key Takeaway

**Smaller digit arrives → pop larger digits → push the current digit.**

This greedy strategy ensures we keep the most valuable digits in the leftmost positions.

If this helped, don't forget to **upvote!** 🚀
