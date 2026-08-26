# 🚀 26. Remove Duplicates from Sorted Array | ⚡ Two Pointers | O(n) / O(1)

> 📂 Topic: Array  
> 🎯 Pattern: Two Pointers  
> ⭐ Difficulty: Easy  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

## 🔗 Problem

[LeetCode 26 — Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

---

## 🧠 Intuition

Since the array is **sorted**, all duplicate values appear next to each other.

We use two pointers:

- `i` → scans through the array
- `k` → points to the last unique element

Whenever `nums[i] > nums[k]`, we have found a new unique value.

We place it at `k + 1` and move `k` forward.

---

## 💡 Example

```text
Input:
[1, 1, 2, 2, 3, 3]

Unique elements:
[1, 2, 3]
```

Initially:

```text
[1, 1, 2, 2, 3, 3]
 ↑
 k
```

When `i` finds `2`:

```text
[1, 2, 2, 2, 3, 3]
    ↑
    k
```

When `i` finds `3`:

```text
[1, 2, 3, 2, 3, 3]
       ↑
       k
```

The first `k + 1` positions now contain the unique elements:

```text
[1, 2, 3]
```

---

## 🚀 Approach

### Step 1: Start `k` at 0

The first element is always unique.

```java
int k = 0;
```

### Step 2: Scan the array

```java
for (int i = k + 1; i < n; i++)
```

`i` searches for a new unique value.

### Step 3: Check for a new value

Because the array is sorted:

```java
if (nums[i] > nums[k])
```

means `nums[i]` is different from the current unique value.

### Step 4: Place the new unique value

```java
nums[k + 1] = nums[i];
k++;
```

This modifies the array **in-place**, so no extra array is required.

---

## 💻 Java Solution

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length, k = 0;

        for (int i = k + 1; i < n; i++) {
            if (nums[i] > nums[k]) {
                nums[k + 1] = nums[i];
                k++;
            }
        }

        return k + 1;
    }
}
```

---

## 🔑 Key Idea

Think of the pointers as:

```text
i → searches for new unique elements
k → builds the unique portion
```

For example:

```text
[1, 1, 2, 2, 3, 3]
 ↑        ↑
 k        i
```

When `i` finds a new value:

```text
nums[k + 1] = nums[i]
k++
```

So the unique portion keeps growing from the left.

---

## ❓ Why `return k + 1`?

`k` represents an **index**, not the number of unique elements.

If:

```text
[1, 2, 3, ...]
       ↑
       k = 2
```

then the number of unique elements is:

```text
k + 1 = 3
```

So we return:

```java
return k + 1;
```

Only the first `k + 1` elements matter for the final answer.

---

## 📊 Complexity

### Time Complexity

```text
O(n)
```

The array is traversed once.

### Space Complexity

```text
O(1)
```

The array is modified in-place and no additional data structure is used.

---

## 🏆 Final Complexity

```text
Time  : O(n)
Space : O(1)
```

---

## 🎯 Key Takeaway

> **Because the array is sorted, we only need to compare each element with the last unique element and overwrite duplicates in-place.**

```text
i → Find unique
k → Place unique
```

> ⭐ **If this clean two-pointer approach helped you, please upvote! 🙌**
