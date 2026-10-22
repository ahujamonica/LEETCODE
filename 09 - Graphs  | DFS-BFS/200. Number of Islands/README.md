# 🏝️ Sink the Island | DFS Flood Fill in Java

🔗 **LeetCode 200:** [Number of Islands](https://leetcode.com/problems/number-of-islands/)

## 🧠 Intuition

We are given a 2D grid containing:

- `'1'` → Land
- `'0'` → Water

An island consists of connected land cells horizontally or vertically. Diagonal connections do not count.

Our goal is to count the total number of islands.

**The key idea:** Whenever we find an unvisited land cell, we have discovered a new island. We increment `count` and use DFS to visit all connected land cells, turning them into water so we don't count them again.

## 💡 Approach

1. Handle an empty grid by returning `0`.
2. Traverse every cell using nested `for` loops.
3. If `grid[i][j] == '1'`:
   - Increment the island count.
   - Call `dfs()` to explore the entire island.
4. In `dfs()`:
   - Stop if the position is outside the grid or the cell is water.
   - Mark the current land cell as water (`'0'`).
   - Explore down, up, right, and left.
5. Return the total island count.

## 💻 Java Solution

```java
class Solution {
    public int numIslands(char[][] grid) {
        // Handle an empty grid
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int count = 0;

        // Traverse every cell in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                // Found a new island
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int i, int j) {

        // Stop if outside the grid or on water
        if (i < 0 || i >= grid.length ||
            j < 0 || j >= grid[0].length ||
            grid[i][j] == '0') {
            return;
        }

        // Mark the land as visited
        grid[i][j] = '0';

        // Explore all four directions
        dfs(grid, i + 1, j); // Down
        dfs(grid, i - 1, j); // Up
        dfs(grid, i, j + 1); // Right
        dfs(grid, i, j - 1); // Left
    }
}
```

## 📝 Dry Run

**Input:**

```text
grid = [
  ['1','1','0','0'],
  ['1','0','0','1'],
  ['0','0','1','1'],
  ['0','0','0','0']
]
```

Initially, `count = 0`.

**Step 1:** Find the land at `(0,0)`.

- Increment `count` to `1`.
- DFS visits all connected land cells: `(0,0)`, `(0,1)`, and `(1,0)`.
- These cells are marked as `'0'`.

**Step 2:** Continue scanning the grid and find land at `(1,3)`.

- Increment `count` to `2`.
- DFS visits `(1,3)`, `(2,3)`, and `(2,2)`.
- These cells are marked as `'0'`.

No unvisited land remains.

**Output:** `2`

## 🔑 Key Trick

```java
grid[i][j] = '0';
```

This line is crucial!

It marks land as visited, preventing DFS from visiting the same cell repeatedly and avoiding infinite recursion between connected cells.

**Remember:** One DFS call explores one entire island, no matter how many land cells it contains.

## ⏱️ Complexity

Let \(m\) be the number of rows and \(n\) the number of columns.

- **Time:** \(O(m \times n)\) — every cell is visited at most once.
- **Space:** \(O(m \times n)\) in the worst case due to the recursive DFS call stack.

## 🎯 Key Takeaway

**Find land → increment the island count → use DFS to sink the entire island.**

This is the classic **DFS + Flood Fill** pattern, which is also useful for problems like Flood Fill and connected components in a graph.

If this helped, don't forget to **upvote!** 🚀
