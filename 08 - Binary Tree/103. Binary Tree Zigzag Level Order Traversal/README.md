# 🌗 Switch Sides, Flip Directions | Zigzag Level Order Traversal in Java

🔗 **LeetCode 103:** [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)

## 🧠 Intuition

We need to traverse a binary tree level by level, but alternate the direction at each level.

- **Level 1:** Left → Right
- **Level 2:** Right → Left
- **Level 3:** Left → Right
- **Level 4:** Right → Left

We can solve this using **BFS with a Deque** (double-ended queue), which allows us to remove nodes from either end and insert their children at either end.

## 💡 Approach

1. Create a `result` list to store all levels.
2. If `root == null`, return the empty result.
3. Add the root to the front of the deque.
4. Use a boolean variable `reverse` to track the direction:
   - `false`: process from the front and add children from left to right.
   - `true`: process from the back and add children from right to left.
5. Process exactly `levelSize` nodes at each level.
6. Add the completed level to `result` and toggle `reverse`.
7. Return the result.

## 💻 Java Solution

```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null) return result;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);

        // false = left to right, true = right to left
        boolean reverse = false;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {

                if (!reverse) {
                    // Process nodes from front to back
                    TreeNode node = queue.pollFirst();
                    level.add(node.val);

                    // Add children from left to right
                    if (node.left != null) queue.addLast(node.left);
                    if (node.right != null) queue.addLast(node.right);

                } else {
                    // Process nodes from back to front
                    TreeNode node = queue.pollLast();
                    level.add(node.val);

                    // Add children at the front in reverse order
                    if (node.right != null) queue.addFirst(node.right);
                    if (node.left != null) queue.addFirst(node.left);
                }
            }

            // Store the current level
            result.add(level);

            // Switch direction for the next level
            reverse = !reverse;
        }

        return result;
    }
}
```

## 📝 Dry Run

Consider this tree:

```text
        3
       / \
      9   20
          / \
         15  7
```

| Level | Direction | Output |
|---|---|---|
| 1 | Left → Right | `[3]` |
| 2 | Right → Left | `[20, 9]` |
| 3 | Left → Right | `[15, 7]` |

**Output:**

```text
[[3], [20, 9], [15, 7]]
```

## 🔑 Key Trick

The most important part is **using both ends of the deque**.

- `pollFirst()` removes from the front.
- `pollLast()` removes from the back.
- `addFirst()` inserts at the front.
- `addLast()` inserts at the back.
- `reverse = !reverse` switches the traversal direction after each level.

**Why add children in different orders?**

When processing backward, we add the right child first and then the left child at the front. This ensures the next level is processed in the correct order.

## ⏱️ Complexity

- **Time:** \(O(n)\) — each node is processed once.
- **Space:** \(O(n)\) — the deque and result store nodes and values.

## 🎯 Key Takeaway

**Normal BFS + a Deque + a direction flag = Zigzag traversal.**

If this helped, don't forget to **upvote!** 🚀
