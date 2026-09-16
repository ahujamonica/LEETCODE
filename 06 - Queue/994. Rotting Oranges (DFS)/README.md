# // ★ 994. Rotting Oranges ★ \\
### `DFS` • `Minimum Time` • `Flood Fill`

🔗 **LeetCode:** https://leetcode.com/problems/rotting-oranges/

> ⚡ **Beats 99.9%** — based on the runtime shown by the submission.

## 🧠 Intuition

We need to find the minimum time required for every fresh orange to become rotten.

Instead of simulating minute-by-minute using BFS, we can start a **DFS from every rotten orange** and calculate the time required to reach every cell.

We maintain a separate `time[][]` array:

```text
time[i][j] = minimum time needed to reach cell (i,j)
```

Initially every cell is set to `Integer.MAX_VALUE`, meaning:

```text
"We haven't reached this cell yet."
```

Whenever DFS reaches a cell in a smaller amount of time, we update its value.

---

## 💡 Approach

For every rotten orange:

```java
dfs(grid, time, i, j, 0);
```

The DFS explores:

```text
        UP
         ↑
LEFT ← CELL → RIGHT
         ↓
       DOWN
```

Every move takes `1` minute, so we pass:

```java
currentTime + 1
```

### 🛑 When do we stop DFS?

```java
if(i < 0 || j < 0 ||
   i >= grid.length || j >= grid[0].length ||
   grid[i][j] == 0 ||
   currentTime >= time[i][j]) {
    return;
}
```

We stop when:

- We move outside the grid.
- The cell is empty (`0`).
- We have already reached the cell in an equal or shorter time.

That last condition is important:

```java
currentTime >= time[i][j]
```

means:

> "I already have an equally fast or faster path to this cell, so this DFS path is useless."

Otherwise, we record:

```java
time[i][j] = currentTime;
```

---

## 🧪 Example

```text
grid =

2 1 1
1 1 0
0 1 1
```

Starting from `(0,0)`:

```text
(0,0) → time 0
(0,1) → time 1
(0,2) → time 2
(1,0) → time 1
(1,1) → time 2
...
```

The `time[][]` array keeps the best/shortest time found for each reachable cell.

After all DFS calls finish, we look at every fresh orange.

If:

```java
time[i][j] == Integer.MAX_VALUE
```

then that orange was never reached:

```text
→ return -1
```

Otherwise, we take the maximum time because **all** oranges must become rotten.

---

## 💻 Java Solution

```java
class Solution {
    public int orangesRotting(int[][] grid) {

        if(grid == null || grid.length == 0)
            return -1;

        int rows = grid.length;
        int cols = grid[0].length;

        // time[i][j] = minimum time needed to reach this cell
        int[][] time = new int[rows][cols];

        // Initially, every cell is unreachable
        for(int i = 0; i < rows; i++){
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }

        // Start DFS from every rotten orange
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){

                if(grid[i][j] == 2){
                    dfs(grid, time, i, j, 0);
                }
            }
        }

        int timeRequired = 0;

        // Find the time required for all fresh oranges
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){

                if(grid[i][j] == 1){

                    // Fresh orange was never reached
                    if(time[i][j] == Integer.MAX_VALUE){
                        return -1;
                    }

                    // We need the maximum time
                    timeRequired = Math.max(
                        timeRequired,
                        time[i][j]
                    );
                }
            }
        }

        return timeRequired;
    }


    private void dfs(int[][] grid, int[][] time,
                     int i, int j, int currentTime){

        // Stop if:
        // 1. Outside the grid
        // 2. Cell is empty
        // 3. We already reached this cell
        //    in an equal or shorter time
        if(i < 0 || j < 0 ||
           i >= grid.length || j >= grid[0].length ||
           grid[i][j] == 0 ||
           currentTime >= time[i][j]) {

            return;
        }

        // Store the best time found so far
        time[i][j] = currentTime;

        // Explore all 4 directions
        dfs(grid, time, i - 1, j, currentTime + 1);
        dfs(grid, time, i + 1, j, currentTime + 1);
        dfs(grid, time, i, j - 1, currentTime + 1);
        dfs(grid, time, i, j + 1, currentTime + 1);
    }
}
```

---

## 🔑 Key Trick

The entire DFS logic can be remembered as:

```text
Start from every rotten orange
          ↓
     DFS in 4 directions
          ↓
   Store minimum time per cell
          ↓
Unreachable fresh orange → -1
          ↓
Otherwise take maximum time
```

The most important line is:

```java
currentTime >= time[i][j]
```

It prevents a slower DFS path from replacing a faster path.

---

## ⏱️ Complexity

- **Time:** `O(R × C)` in the amortized/intended analysis of this minimum-time DFS approach
- **Space:** `O(R × C)` for the `time` array and recursion stack

---

## 🎯 Key Takeaway

```text
~// ★ DFS + Time Array ★ \\~
```

Instead of simply marking cells as visited, we store the **minimum time** at which each cell can be reached.

> ⭐ If this helped, please consider giving it an upvote! 🚀
