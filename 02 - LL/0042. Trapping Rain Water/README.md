# 🚀 42. Trapping Rain Water | 🌧️ Prefix & Suffix Maximums

> 📂 Topic: Array  
> 🎯 Pattern: Prefix/Suffix Maximum  
> ⭐ Difficulty: Hard  
> ⏱️ Time: O(n) | 💾 Space: O(n)

---

## 🔗 Problem

[LeetCode 42 — Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

---

## 🧠 Intuition

For water to be trapped at a particular index, there must be a taller bar on both its left and right.

The amount of water at index `i` is:

```text
water[i] = min(leftMax[i], rightMax[i]) - height[i]
```

Where:

- `leftMax[i]` → tallest bar from the left up to index `i`
- `rightMax[i]` → tallest bar from the right up to index `i`

The shorter boundary determines how high the water can rise.

---

## 🚀 Approach

We solve the problem in three passes.

### 1️⃣ Build `leftmax[]`

`leftmax[i]` stores the maximum height from index `0` to `i`.

We initialize:

```java
leftmax[0] = height[0];
```

because index `0` has no element before it.

Then:

```java
for (int i = 1; i < n; i++) {
    leftmax[i] = Math.max(leftmax[i - 1], height[i]);
}
```

For:

```text
height  = [0, 1, 0, 2, 1]
leftmax = [0, 1, 1, 2, 2]
```

---

### 2️⃣ Build `rightmax[]`

`rightmax[i]` stores the maximum height from index `i` to the end.

We initialize:

```java
rightmax[n - 1] = height[n - 1];
```

because the last index has no element after it.

Then move from right to left:

```java
for (int i = n - 2; i >= 0; i--) {
    rightmax[i] = Math.max(rightmax[i + 1], height[i]);
}
```

For:

```text
height   = [0, 1, 0, 2, 1]
rightmax = [2, 2, 2, 2, 1]
```

---

### 3️⃣ Calculate Trapped Water

For every index:

```java
totalWater += Math.min(leftmax[i], rightmax[i]) - height[i];
```

The shorter of the two maximum boundaries determines the water level.

For example:

```text
leftMax  = 2
rightMax = 3
height   = 1
```

Then:

```text
water = min(2, 3) - 1
      = 1
```

---

## 💡 Example

```text
Input:

height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
```

At a position where:

```text
leftMax  = 2
rightMax = 3
height   = 0
```

we can trap:

```text
min(2, 3) - 0 = 2
```

The total trapped water for the complete example is:

```text
6
```

---

## 💻 Java Solution

```java
class Solution {
    public int trap(int[] height) {

        int n = height.length;

        if (n == 0) return 0;
        if (n == 1) return 0;

        // Maximum height from the left
        int[] leftmax = new int[n];
        leftmax[0] = height[0];

        for (int i = 1; i < n; i++) {
            leftmax[i] = Math.max(leftmax[i - 1], height[i]);
        }

        // Maximum height from the right
        int[] rightmax = new int[n];
        rightmax[n - 1] = height[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            rightmax[i] = Math.max(rightmax[i + 1], height[i]);
        }

        // Calculate trapped water
        int totalWater = 0;

        for (int i = 0; i < n; i++) {
            totalWater += Math.min(leftmax[i], rightmax[i])
                        - height[i];
        }

        return totalWater;
    }
}
```

---

## 🔑 Important Concept

The initialization of the maximum arrays is important:

```java
leftmax[0] = height[0];
```

because we build `leftmax` from **left → right**.

Similarly:

```java
rightmax[n - 1] = height[n - 1];
```

because we build `rightmax` from **right → left**.

Think of it as:

```text
Prefix Maximum:

[0] → [1] → [2] → [3]
 ↑
Start here


Suffix Maximum:

[0] ← [1] ← [2] ← [3]
                         ↑
                      Start here
```

---

## 🎯 Key Takeaway

```text
1. Find tallest bar on the left
              ↓
2. Find tallest bar on the right
              ↓
3. Take the shorter boundary
              ↓
4. Subtract current height
              ↓
5. Add the trapped water
```

The core formula is:

```text
Water at i = min(leftMax[i], rightMax[i]) - height[i]
```

---

## 📊 Complexity

### Time Complexity

```text
O(n)
```

We make three linear passes through the array.

### Space Complexity

```text
O(n)
```

We use two auxiliary arrays:

```text
leftmax[]
rightmax[]
```

---

## 🏆 Final Complexity

```text
Time  : O(n)
Space : O(n)
```

> ⭐ **If this explanation helped you understand Prefix & Suffix Maximums, please upvote! 🙌**
