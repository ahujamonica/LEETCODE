# 🚀 75. Sort Colors

> 📂 Topic: Arrays  
> 🎯 Pattern: Dutch National Flag Algorithm, Three Pointers  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

# 🔗 Problem Link

https://leetcode.com/problems/sort-colors/

---

# 🧠 Pattern Recognition

Whenever the problem mentions

- Only a few distinct values
- Sort in-place
- Constant extra space

Think

```
Can I count frequencies?

↓

Can I do it in one traversal?

↓

Three Pointers (DNF)
```

This is the classic **Dutch National Flag Algorithm**.

---

# 💡 Intuition

The array contains only three possible values:

```
0
1
2
```

The most straightforward solution is to simply sort the array using any sorting algorithm.

However,

sorting is unnecessary because we already know there are only **three unique values**.

Instead,

we can first count the number of `0`s, `1`s and `2`s,

and then overwrite the array accordingly.

Can we optimize even further?

Yes.

Instead of counting,

we can directly place every element into its correct region while traversing the array only once.

This leads to the **Dutch National Flag Algorithm**, which solves the problem in a single pass using three pointers.

---

# 📈 Evolution of the Solution

```
Sort the array

↓

Brute Force

O(n log n)

↓

Count frequencies

↓

Better

O(n)

↓

Three Pointers

↓

Dutch National Flag Algorithm

O(n)
```

---

# 🐢 Approach 1 — Brute Force

## Idea

Use any efficient sorting algorithm.

For example,

```java
Arrays.sort(nums);
```

Since Java uses **Dual Pivot QuickSort** for primitive arrays,

the complexity is

### Complexity

**Time**

```
O(n log n)
```

**Space**

```
O(log n)
```

---

# ⚡ Approach 2 — Better (Counting Sort)

## Idea

Since there are only three distinct numbers,

count their frequencies.

```
count0

count1

count2
```

Then overwrite the array.

```
First count0 positions

↓

0

Next count1 positions

↓

1

Remaining positions

↓

2
```

No comparisons are required.

---

## Dry Run

Input

```
2 0 2 1 1 0
```

Frequency

```
count0 = 2

count1 = 2

count2 = 2
```

Overwrite

```
0 0

1 1

2 2
```

Final

```
0 0 1 1 2 2
```

---

### Complexity

**Time**

```
O(n)
```

**Space**

```
O(1)
```

---

# 🚀 Approach 3 — Dutch National Flag Algorithm (Optimal)

## Key Observation

Instead of counting,

maintain three regions.

Initially,

```
0 1 2 1 0 2
↑
L M
         H
```

where

```
low

↓

Boundary of 0s

mid

↓

Current element

high

↓

Boundary of 2s
```

The array is divided into four regions.

```
---------------------------------

0 ... low-1

↓

All 0s

---------------------------------

low ... mid-1

↓

All 1s

---------------------------------

mid ... high

↓

Unknown Region

---------------------------------

high+1 ... n-1

↓

All 2s
```

Initially,

the entire array belongs to the **Unknown Region**.

---

## Three Cases

### Case 1

```
nums[mid] == 0
```

Swap with

```
low
```

Both pointers move forward.

```
low++

mid++
```

because the swapped element is now correctly placed.

---

### Case 2

```
nums[mid] == 1
```

Already in the correct region.

Simply

```
mid++
```

---

### Case 3

```
nums[mid] == 2
```

Swap with

```
high
```

Move

```
high--
```

only.

Do **NOT**

increment `mid`.

Why?

Because the element swapped from the end is unknown.

It still needs to be examined.

---

# 🔍 Visual Intuition

Input

```
2 0 2 1 1 0
```

Initially

```
L
M
            H

2 0 2 1 1 0
```

Swap

```
mid

↓

high
```

```
0 0 2 1 1 2
```

Now

```
L
M
          H
```

Continue

Eventually

```
0 0 1 1 2 2
```

---

# 📝 Dry Run

```
2 0 2 1 1 0
```

| low | mid | high | Array |
|----:|----:|-----:|-------|
|0|0|5|2 0 2 1 1 0|
|0|0|4|0 0 2 1 1 2|
|1|1|4|0 0 2 1 1 2|
|2|2|4|0 0 2 1 1 2|
|2|2|3|0 0 1 1 2 2|
|2|3|3|0 0 1 1 2 2|
|2|4|3|0 0 1 1 2 2|

Finished.

---

# 🤔 Things That Confused Me

### ❓1. Why don't we increment `mid` after swapping with `high`?

Because the element that comes from

```
high
```

is still unknown.

Example

```
2 1 0
```

Swap

```
2

↓

0
```

Array

```
0 1 2
```

The newly swapped element must still be checked.

Hence,

only

```
high--
```

---

### ❓2. Why do we increment both `low` and `mid` when we find `0`?

After swapping,

the element placed at `low` is guaranteed to be `0`.

The element now at `mid` has already been processed.

Hence,

both pointers move forward.

---

### ❓3. Why is counting considered the "Better" solution?

Because it reduces the complexity from

```
O(n log n)

↓

O(n)
```

However,

it still requires two traversals.

The Dutch National Flag Algorithm performs the rearrangement in a **single traversal**.

---

# ❌ Common Mistakes

- Incrementing `mid` after swapping with `high`
- Using `<= count0` instead of `< count0`
- Forgetting that the swapped element from the end is still unprocessed
- Sorting the array when the interviewer expects the DNF approach

---

# ⏱️ Complexity Analysis

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n log n) | O(log n) |
| Better (Counting) | O(n) | O(1) |
| Dutch National Flag | O(n) | O(1) |

---

# 💼 Interview Notes

Whenever you hear

> **Sort an array containing only 0s, 1s and 2s**

immediately think

```
Three Pointers

↓

low

↓

mid

↓

high
```

Remember the three rules:

```
0

↓

Swap with low

↓

low++

mid++
```

```
1

↓

mid++
```

```
2

↓

Swap with high

↓

high--
```

This is the **Dutch National Flag Algorithm**, one of the most frequently asked array patterns in coding interviews.

---


- Understanding **why `mid` is not incremented after swapping with `high`** is the key to mastering this algorithm.
