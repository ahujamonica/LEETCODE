# 👀 See the Right Side | Binary Tree BFS in Java

🔗 **LeetCode 199:** [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)

## 🧠 Intuition

Imagine looking at a binary tree from its right side. At every level, you can see only the **rightmost node**.

We can solve this using **Breadth-First Search (BFS)** with a queue. We process the tree level by level and store the last node's value at each level.

## 💡 Approach

1. Create a `result` list to store the right-side view.
2. If `root == null`, return the empty list.
3. Add the root to a queue.
4. While the queue is not empty:
   - Store the current level's node count in `levelSize`.
   - Process exactly `levelSize` nodes.
   - Update `lastValue` with each node's value.
   - Add the node's left and right children to the queue.
   - After processing the entire level, add `lastValue` to `result`.
5. Return `result`.

**Why does `lastValue` work?** We enqueue left children before right children, so BFS processes each level from left to right. Therefore, the last node processed at each level is the rightmost node.

## 💻 Java Solution

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // Handle an empty tree
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            // Number of nodes at the current level
            int levelSize = queue.size();
            int lastValue = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // Keep updating; the final value is rightmost
                lastValue = node.val;

                // Add children for the next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // Store the rightmost node of this level
            result.add(lastValue);
        }

        return result;
    }
}
```

## 📝 Dry Run

Consider this tree:

```text
        1
       / \
      2   3
       \   \
        5   4
```

| Level | Nodes processed | `lastValue` | Added to result |
|---|---|---:|---|
| 0 | 1 | 1 | `[1]` |
| 1 | 2, 3 | 3 | `[1, 3]` |
| 2 | 5, 4 | 4 | `[1, 3, 4]` |

**Output:** `[1, 3, 4]`

## 🔑 Key Trick

`levelSize = queue.size()` ensures that you process only the nodes belonging to the current level. Once the inner loop finishes, `lastValue` holds the rightmost node's value.

## ⏱️ Complexity

- **Time:** \(O(n)\) — each node is processed once.
- **Space:** \(O(n)\) — the queue can hold up to \(O(n)\) nodes, and the result also stores the visible nodes.

## 🎯 Key Takeaway

**BFS + `levelSize` + the last node of each level = Right Side View.**

If you found this helpful, don't forget to **upvote!** 🚀
