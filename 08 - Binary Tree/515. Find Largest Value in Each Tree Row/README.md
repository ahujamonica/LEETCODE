# 🌳 Track the Maximum | Largest Value in Each Tree Row

🔗 **LeetCode 515:** [Find Largest Value in Each Tree Row](https://leetcode.com/problems/find-largest-value-in-each-tree-row/)

## 🧠 Intuition

We need to find the **largest value at every level** of a binary tree.

We can use **Breadth-First Search (BFS)** with a queue to process one level at a time. For each level, we keep track of the maximum value using `Math.max()`.

## 💡 Approach

1. Create a `result` list to store the maximum value at each level.
2. If `root == null`, return the empty list.
3. Add the root to the queue.
4. While the queue is not empty:
   - Store the number of nodes at the current level in `levelSize`.
   - Initialize `largestValue` to `Integer.MIN_VALUE` so negative values are handled correctly.
   - Process exactly `levelSize` nodes and update the maximum.
   - Add each node's children to the queue for the next level.
   - Add `largestValue` to the result after processing the level.
5. Return the result.

## 💻 Java Solution

```java
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // Handle an empty tree
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            // Number of nodes at the current level
            int levelSize = queue.size();

            // Supports levels containing only negative values
            int largestValue = Integer.MIN_VALUE;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // Update the maximum value at this level
                largestValue = Math.max(largestValue, node.val);

                // Add children for the next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // Store the maximum value of this level
            result.add(largestValue);
        }

        return result;
    }
}
```

## 📝 Dry Run

**Input:** `root = [0, -1]`

```text
    0
   /
 -1
```

- **Level 0:** Nodes = `[0]`, maximum = `0`
- **Level 1:** Nodes = `[-1]`, maximum = `-1`

**Output:** `[0, -1]`

## 🔑 Key Trick

- `levelSize` ensures we process one level at a time.
- `Math.max()` finds the largest value at that level.
- `Integer.MIN_VALUE` handles negative values correctly.

## ⏱️ Complexity

- **Time:** \(O(n)\) — each node is visited once.
- **Space:** \(O(n)\) — the queue and result list can require linear space.

## 🎯 Key Takeaway

**BFS + `levelSize` + `Math.max()` = Maximum value at every tree level.**

If this helped, don't forget to **upvote!** 🚀
