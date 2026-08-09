# 🚀 240. Search a 2D Matrix II

> 📂 Topic: Arrays, Matrix
> 🎯 Pattern: Staircase Search, Matrix Traversal
> ⭐ Difficulty: Medium
> ⏱️ Optimal Time: O(m + n) | 💾 Space: O(1)

---

# 🔗 Problem Link

[LeetCode 240 — Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/)

---

# 🧠 Staircase Search — One-Line Idea

> Start from the top-right corner and use the matrix's sorted rows and columns to move either left or down, eliminating one complete row or column at every step.

---

# 💡 Intuition

The goal is to determine whether a target value exists in a matrix where:

- Every row is sorted from **left to right**.
- Every column is sorted from **top to bottom**.

For example:

```text
1   4   7   11  15
2   5   8   12  19
3   6   9   16  22
10  13  14  17  24
18  21  23  26  30
```

There are multiple ways to solve this problem, but the most efficient approach is **Staircase Search**.

The key idea is to start from the **top-right corner**.

At any position:

```text
current == target
        ↓
      FOUND

current > target
        ↓
      MOVE LEFT

current < target
        ↓
      MOVE DOWN
```

This works because the matrix is sorted in both directions.

---

# 🚀 Approach — Staircase Search

## 🔑 Key Observation

Consider the top-right element:

```text
1   4   7   11  [15]
2   5   8   12   19
3   6   9   16   22
10  13  14  17   24
18  21  23  26   30
```

At `15`:

### If `15 > target`

Since the column is sorted from top to bottom:

```text
15
19
22
24
30
```

Everything below `15` is even greater.

Therefore, the target **cannot be in this column**.

So we move:

```text
LEFT ←
```

```java
j--;
```

---

### If `15 < target`

Since the row is sorted from left to right:

```text
1   4   7   11   15
```

Everything to the left of `15` is even smaller.

Therefore, the target **cannot be in this row**.

So we move:

```text
DOWN ↓
```

```java
i++;
```

---

### If `15 == target`

We have found the target.

```java
return true;
```

Therefore, the three rules are:

```text
current == target → FOUND
current > target  → LEFT
current < target  → DOWN
```

---

# 📍 Step 1 — Start from the Top-Right Corner

We initialize:

```java
int i = 0;
int j = cols - 1;
```

Here:

```text
i → row
j → column
```

So we start at:

```text
1   4   7   11  [15]
2   5   8   12   19
3   6   9   16   22
10  13  14  17   24
18  21  23  26   30
```

The top-right element is the best starting point because:

```text
← values become smaller
↓ values become larger
```

This gives us a clear direction to move based on comparison with the target.

---

# 📍 Step 2 — Continue While Inside the Matrix

We use:

```java
while (i < rows && j >= 0)
```

This simply means:

> Continue searching while the current position is still inside the matrix.

We only move:

```text
LEFT  ←
DOWN  ↓
```

Therefore:

```java
i < rows
```

ensures we haven't moved below the last row.

And:

```java
j >= 0
```

ensures we haven't moved past the first column.

If either condition becomes false, the target does not exist in the matrix.

---

# 📍 Step 3 — Compare and Move

For every position:

```java
matrix[i][j]
```

we perform three checks.

### Target found

```java
if (matrix[i][j] == target)
```

Return:

```java
true
```

### Current value is too large

```java
else if (matrix[i][j] > target)
```

Move left:

```java
j--;
```

### Current value is too small

```java
else
```

Move down:

```java
i++;
```

---

# 📝 Dry Run

Consider:

```text
matrix =
[
  [1,  4,  7, 11, 15],
  [2,  5,  8, 12, 19],
  [3,  6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

target = 16
```

We start from:

```text
1   4   7   11  [15]
2   5   8   12   19
3   6   9   16   22
10  13  14   17   24
18  21  23   26   30
```

---

## Iteration 1

Current value:

```text
15
```

Compare:

```text
15 < 16
```

The current value is too small.

Everything to the left is even smaller, so we eliminate the current row.

Move down:

```java
i++;
```

Now:

```text
1   4   7   11   15
2   5   8   12  [19]
3   6   9   16   22
10  13  14   17   24
18  21  23   26   30
```

---

## Iteration 2

Current value:

```text
19
```

Compare:

```text
19 > 16
```

The current value is too large.

Everything below is even larger, so we eliminate the current column.

Move left:

```java
j--;
```

Now:

```text
1   4   7   11   15
2   5   8  [12]  19
3   6   9   16   22
10  13  14   17   24
18  21  23   26   30
```

---

## Iteration 3

Current value:

```text
12
```

Compare:

```text
12 < 16
```

The current value is too small.

Move down:

```java
i++;
```

Now:

```text
1   4   7   11   15
2   5   8   12   19
3   6   9  [16]  22
10  13  14   17   24
18  21  23   26   30
```

---

## Iteration 4

Current value:

```text
16
```

Compare:

```text
16 == 16
```

Target found! 🎯

Return:

```java
true;
```

The complete path was:

```text
15 → DOWN
19 → LEFT
12 → DOWN
16 → FOUND
```
---

# 📊 Complexity

Let:

- `m` = number of rows
- `n` = number of columns

We only move in two directions:

```text
LEFT  ←
DOWN  ↓
```

We can move at most:

```text
n times left
+
m times down
```

Therefore:

- **Time Complexity:** `O(m + n)`
- **Space Complexity:** `O(1)`

This is the **optimal approach for LeetCode 240**.

---

# 🎯 Key Takeaway

Remember the staircase pattern:

```text
Start → TOP-RIGHT

current == target
        ↓
      FOUND

current > target
        ↓
       LEFT

current < target
        ↓
       DOWN
```

The reason this works is:

```text
TOP-RIGHT
    │
    ├── ← LEFT
    │      values become smaller
    │
    └── ↓ DOWN
           values become larger
```

So every comparison eliminates an entire row or column.

```text
O(m + n) time
O(1) space
```

> **Start from the top-right, move left when the current value is too large, move down when it is too small, and stop when the target is found or we leave the matrix.**

---

Happy Coding! 🚀✨
