# 🌳 Don't Overcomplicate It | Level Order Traversal with BFS

🔗 **LeetCode:** https://leetcode.com/problems/binary-tree-level-order-traversal/

## 🧠 Intuition

We need to traverse a binary tree **level by level**, from left to right.

We use a **Queue** to process nodes in the order they are discovered and collect each level's values in a separate list.

## 💡 Approach

1. Create a queue and add the root node.
2. While the queue isn't empty, store its current size in `levelSize`.
3. Process exactly `levelSize` nodes and add their values to the current `level` list.
4. Add each node's left and right children to the queue for the next level.
5. Add the completed `level` list to `result`.

## 💻 Java Solution

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        // Handle an empty tree
        if(root == null) return result;

        // Queue processes nodes level by level
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {

            // Number of nodes at the current level
            int levelSize = queue.size();

            // Stores values for this level
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < levelSize; i++) {

                // Remove the front node from the queue
                TreeNode node = queue.poll();

                // Add its value to the current level
                level.add(node.val);

                // Add children for the next level
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }

            // Store the completed level
            result.add(level);
        }

        return result;
    }
}
```

## 🔑 Key Trick

```java
int levelSize = queue.size();
```

This captures how many nodes belong to the current level. Even when their children are added to the queue, the `for` loop processes only the original `levelSize` nodes.

## ⏱️ Complexity

- **Time:** `O(n)` — every node is processed once.
- **Space:** `O(n)` — the queue and result store nodes and values.

## 🎯 Key Takeaway

**BFS + Queue + `levelSize` = Level Order Traversal.**

⭐ If this helped, please consider giving this solution an upvote!
