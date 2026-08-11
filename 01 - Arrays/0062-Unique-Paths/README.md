# 🚀 62. Unique Paths

> 📂 Topic: Dynamic Programming, Arrays
> 🎯 Pattern: 2D Dynamic Programming
> ⭐ Difficulty: Medium
> ⏱️ Time: O(m × n) | 💾 Space: O(m × n)

---

# 🔗 Problem Link

[LeetCode 62 — Unique Paths](https://leetcode.com/problems/unique-paths/)

---

# 🧠 Dynamic Programming — One-Line Idea

> For every cell, the number of ways to reach it is the sum of the ways to reach the cell directly above and the cell directly to its left.

---

# 💡 Intuition

We are given an `m × n` grid and need to find the number of unique paths from the **top-left** corner to the **bottom-right** corner.

We can only move:

```text
→ Right
↓ Down
```

For example:

```text
m = 3
n = 3
```

The grid looks like:

```text
1  1  1
1  2  3
1  3  6
```

The bottom-right cell contains:

```text
6
```

So there are `6` unique paths.

The key observation is that to reach any cell, we can only come from:

```text
Left
   or
Above
```

Therefore:

```text
ways[i][j] =
    ways[i][j - 1] + ways[i - 1][j]
```

This gives us a **Dynamic Programming** solution.

---

# 🚀 Approach

We create a 2D DP array:

```java
int[][] grid = new int[m][n];
```

Here:

```text
grid[i][j]
```

represents:

> The number of unique paths from the top-left corner to cell `(i, j)`.

---

# 🧠 Step 1 — First Row and First Column

Consider a grid:

```text
?  ?  ?
?  ?  ?
?  ?  ?
```

For every cell in the **first row**, there is only one possible way to reach it:

```text
→ → →
```

Therefore:

```text
1  1  1
```

Similarly, for every cell in the **first column**, there is only one possible way:

```text
↓
↓
↓
```

Therefore:

```text
1
1
1
```

That's why we use:

```java
if (i == 0 || j == 0) {
    grid[i][j] = 1;
}
```

---

# 🧠 Step 2 — Fill the Remaining Cells

For every other cell, there are only two possible directions from which we can arrive:

```text
        Above
          ↓
Left → Current
```

So:

```text
Current = Left + Above
```

In code:

```java
grid[i][j] = grid[i][j - 1] + grid[i - 1][j];
```

For example:

```text
1  1  1
1  2  3
1  3  ?
```

For the last cell:

```text
? = 3 + 3
  = 6
```

---

# 📝 Dry Run

Consider:

```text
m = 3
n = 3
```

Initially:

```text
0  0  0
0  0  0
0  0  0
```

### First Row + First Column

Set them to `1`:

```text
1  1  1
1  0  0
1  0  0
```

---

### Cell `(1,1)`

Left:

```text
grid[1][0] = 1
```

Above:

```text
grid[0][1] = 1
```

Therefore:

```text
grid[1][1] = 1 + 1 = 2
```

Grid:

```text
1  1  1
1  2  0
1  0  0
```

---

### Cell `(1,2)`

Left:

```text
grid[1][1] = 2
```

Above:

```text
grid[0][2] = 1
```

Therefore:

```text
grid[1][2] = 2 + 1 = 3
```

Grid:

```text
1  1  1
1  2  3
1  0  0
```

---

### Cell `(2,1)`

Left:

```text
grid[2][0] = 1
```

Above:

```text
grid[1][1] = 2
```

Therefore:

```text
grid[2][1] = 1 + 2 = 3
```

Grid:

```text
1  1  1
1  2  3
1  3  0
```

---

### Cell `(2,2)`

Left:

```text
grid[2][1] = 3
```

Above:

```text
grid[1][2] = 3
```

Therefore:

```text
grid[2][2] = 3 + 3 = 6
```

Final DP table:

```text
1  1  1
1  2  3
1  3  6
```

The bottom-right cell contains:

```text
6
```

Therefore:

```text
Answer = 6
```
---

# 🔍 What Does Each Important Line Do?

### Create the DP table

```java
int[][] grid = new int[m][n];
```

Creates a 2D array with:

```text
m rows
n columns
```

---

### Traverse the grid

```java
for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
```

The outer loop moves through rows.

The inner loop moves through columns.

So every cell is visited exactly once.

---

### Handle the first row and column

```java
if (i == 0 || j == 0) {
    grid[i][j] = 1;
}
```

There is only one way to reach any cell in the first row or first column.

---

### Calculate the remaining cells

```java
grid[i][j] = grid[i][j - 1] + grid[i - 1][j];
```

Here:

```text
grid[i][j - 1] → cell to the LEFT
grid[i - 1][j] → cell ABOVE
```

Therefore:

```text
Current = Left + Above
```

---

### Return the answer

```java
return grid[m - 1][n - 1];
```

`m - 1` is the last row.

`n - 1` is the last column.

Therefore:

```text
grid[m - 1][n - 1]
```

is the bottom-right cell, which contains the total number of unique paths.

---

# 🎯 Key Takeaway

The entire DP pattern is:

```text
First row
    ↓
All 1s

First column
    ↓
All 1s

Every other cell
    ↓
Left + Above
```

Remember:

```text
             ABOVE
               ↓
LEFT  →     CURRENT
```

So:

```text
grid[i][j] = grid[i][j - 1] + grid[i - 1][j]
```

The overall flow is:

```text
Start at top-left
       ↓
Build the grid row by row
       ↓
Each cell stores number of ways to reach it
       ↓
Bottom-right cell
       ↓
Total unique paths
```

### Complexity

```text
Time  = O(m × n)
Space = O(m × n)
```

> **The number of ways to reach every cell is simply the sum of the ways to reach the cell from the left and from above.**

---

Happy Coding! 🚀✨
