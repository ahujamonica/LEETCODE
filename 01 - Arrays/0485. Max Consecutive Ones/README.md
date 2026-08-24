# 🚀 485. Max Consecutive Ones | 🔥 One-Pass O(n)

> 📂 Topic: Array  
> 🎯 Pattern: Linear Traversal  
> ⭐ Difficulty: Easy  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

## 🔗 Problem

[LeetCode 485 — Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/)

---

## 🧠 Intuition

We need to find the maximum number of consecutive `1`s in the array.

We maintain two variables:

- `count` → current streak of consecutive `1`s
- `max` → longest streak found so far

Whenever we encounter:

```text
1 → increase count
0 → reset count to 0
```

After every `1`, update the maximum streak.

---

## 🚀 Approach

Traverse the array from left to right.

### If the current element is `1`

Increase the current streak:

```java
count++;
```

Then update the maximum:

```java
max = Math.max(max, count);
```

### If the current element is `0`

The consecutive sequence is broken:

```java
count = 0;
```

---

## 💡 Example

Given:

```text
[1, 1, 0, 1, 1, 1]
```

Track the consecutive `1`s:

```text
1 → count = 1
1 → count = 2
0 → count = 0
1 → count = 1
1 → count = 2
1 → count = 3
```

Therefore:

```text
Maximum = 3
```

---

## 💻 Java Solution

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {

        int count = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }

        return max;
    }
}
```

---

## 🎯 Key Idea

```text
1 → count++
0 → count = 0

max = longest streak found so far
```

The important part is that `count` represents the **current streak**, while `max` stores the **best streak seen so far**.

---

## 📊 Complexity

### Time Complexity

```text
O(n)
```

The array is traversed only once.

### Space Complexity

```text
O(1)
```

Only two variables are used.

---

## 🏆 Final Complexity

```text
Time  : O(n)
Space : O(1)
```

---

