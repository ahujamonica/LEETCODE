# 🚀 74. Search a 2D Matrix

> 📂 Topic: Arrays, Matrix, Binary Search
> 🎯 Pattern: Binary Search on a Flattened Matrix
> ⭐ Difficulty: Medium
> ⏱️ Optimal Time: O(log(m × n)) | 💾 Space: O(1)

---

# 🔗 Problem Link

[LeetCode 74 — Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/)

---

# 🧠 Binary Search — One-Line Idea

> Treat the 2D matrix like a sorted 1D array and use binary search, converting each virtual 1D index back into its corresponding row and column using division and modulo.

---

# 💡 Intuition

The matrix has two important properties:

- Each row is sorted in **ascending order**.
- The first element of every row is greater than the last element of the previous row.

For example:

```text
1   3   5   7
10  11  16  20
23  30  34  60
```

Because of the second property, we can imagine the matrix as one completely sorted 1D array:

```text
1  3  5  7  10  11  16  20  23  30  34  60
```

So instead of searching every element, we can apply **binary search**.

The only challenge is that the matrix is still stored as a 2D array.

We solve this by treating every element as if it has a virtual 1D index.

For example:

```text
1   3   5   7
10  11  16  20
23  30  34  60
```

Virtual indices:

```text
0   1   2   3
4   5   6   7
8   9   10  11
```

Then we can perform normal binary search on:

```text
low = 0
high = rows * cols - 1
```

---

# 🚀 Approach — Binary Search on a Flattened Matrix

## Step 1: Find the number of rows and columns

We use:

```java
int n = matrix.length;
int m = matrix[0].length;
```

Here:

```text
n → number of rows
m → number of columns
```

For:

```text
1   3   5   7
10  11  16  20
23  30  34  60
```

we have:

```text
n = 3
m = 4
```

Therefore, the matrix contains:

```text
n × m = 3 × 4 = 12
```

elements.

---

# Step 2: Create the Binary Search Range

Since we are treating the matrix as a 1D array, the virtual indices are:

```text
0 → first element
1
2
...
n × m - 1 → last element
```

Therefore:

```java
int low = 0;
int high = (n * m) - 1;
```

For a `3 × 4` matrix:

```text
low = 0
high = 11
```

---

# Step 3: Calculate the Middle Index

We calculate:

```java
int mid = low + (high - low) / 2;
```

This is the overflow-safe version of:

```java
int mid = (low + high) / 2;
```

Both produce the same middle index.

Using:

```java
low + (high - low) / 2
```

is preferred because it avoids integer overflow when `low + high` becomes too large.

---

# Step 4: Convert the 1D Index Back to 2D

This is the most important part.

We have:

```java
mid
```

which represents a virtual 1D index.

To find its corresponding row:

```java
mid / m
```

To find its corresponding column:

```java
mid % m
```

Therefore:

```java
matrix[mid / m][mid % m]
```

---

## Why does this work?

Consider:

```text
1   3   5   7
10  11  16  20
23  30  34  60
```

There are `4` columns.

So:

```text
Index → Matrix position

0 → [0][0]
1 → [0][1]
2 → [0][2]
3 → [0][3]

4 → [1][0]
5 → [1][1]
6 → [1][2]
7 → [1][3]

8 → [2][0]
9 → [2][1]
10 → [2][2]
11 → [2][3]
```

For example, if:

```text
mid = 6
```

Then:

```text
row = mid / m
    = 6 / 4
    = 1
```

and:

```text
col = mid % m
    = 6 % 4
    = 2
```

Therefore:

```java
matrix[1][2]
```

which is:

```text
16
```

So:

```java
matrix[mid / m][mid % m]
```

lets us perform binary search without actually creating a new 1D array.

---

# Step 5: Compare With the Target

Once we get:

```java
matrix[mid / m][mid % m]
```

we perform the normal binary search comparison.

### Case 1: Target Found

```java
if (matrix[mid / m][mid % m] == target)
```

Return:

```java
true
```

### Case 2: Current Value Is Smaller

If:

```java
matrix[mid / m][mid % m] < target
```

the target must be on the right side.

Move:

```java
low = mid + 1;
```

### Case 3: Current Value Is Greater

Otherwise, the target must be on the left side.

Move:

```java
high = mid - 1;
```

---

# 📝 Dry Run

Consider:

```text
matrix =
[
  [1,  3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 60]
]

target = 16
```

There are:

```text
n = 3 rows
m = 4 columns
```

Therefore:

```text
low = 0
high = 3 × 4 - 1
high = 11
```

---

## Iteration 1

```text
low = 0
high = 11
```

Calculate:

```text
mid = 0 + (11 - 0) / 2
mid = 5
```

Convert index `5`:

```text
row = 5 / 4 = 1
col = 5 % 4 = 1
```

So:

```text
matrix[1][1] = 11
```

Compare:

```text
11 < 16
```

Target is on the right.

Therefore:

```text
low = mid + 1
low = 6
```

---

## Iteration 2

```text
low = 6
high = 11
```

Calculate:

```text
mid = 6 + (11 - 6) / 2
mid = 8
```

Convert index `8`:

```text
row = 8 / 4 = 2
col = 8 % 4 = 0
```

So:

```text
matrix[2][0] = 23
```

Compare:

```text
23 > 16
```

Target is on the left.

Therefore:

```text
high = mid - 1
high = 7
```

---

## Iteration 3

```text
low = 6
high = 7
```

Calculate:

```text
mid = 6 + (7 - 6) / 2
mid = 6
```

Convert index `6`:

```text
row = 6 / 4 = 1
col = 6 % 4 = 2
```

So:

```text
matrix[1][2] = 16
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
---

# 📊 Complexity

Let:

- `m` = number of rows
- `n` = number of columns

The matrix contains:

```text
m × n
```

elements.

Binary search reduces the search space by half after every comparison.

Therefore:

- **Time Complexity:** `O(log(m × n))`
- **Space Complexity:** `O(1)`

This is the **optimal approach for LeetCode 74**.

---

# 🎯 Key Takeaway

The main trick is to **pretend the 2D matrix is a sorted 1D array**.

Remember:

```text
2D Matrix
    ↓
Virtual 1D Array
    ↓
Binary Search
```

For a virtual index `mid`:

```text
row = mid / columns
col = mid % columns
```

So:

```java
matrix[mid / m][mid % m]
```

gives us the actual matrix element corresponding to the virtual 1D index.

The complete pattern is:

```text
low = 0
high = rows × columns - 1

        ↓

mid = low + (high - low) / 2

        ↓

row = mid / columns
col = mid % columns

        ↓

Compare matrix[row][col] with target

        ↓

smaller → low = mid + 1
greater → high = mid - 1
equal   → FOUND
```

> **When a matrix is globally sorted row-by-row, flatten it conceptually and apply binary search without actually creating a new array.**

---

Happy Coding! 🚀✨
