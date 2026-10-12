# 🌳 Flip the Levels | Bottom-Up BFS in Java

🔗 **LeetCode:** https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

## 🧠 Intuition

We traverse the binary tree level by level using **BFS and a Queue**, just like normal level order traversal. The only difference is that we reverse the final result so the bottommost level appears first.

## 💡 Approach

1. Add the root to the queue.
2. Process nodes level by level using `levelSize`.
3. Store each level's node values in a separate list.
4. Add each completed level to `result`.
5. Reverse `result` using `Collections.reverse(result)`.

## 💻 Java Solution

```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        // Handle an empty tree
        if(root == null) return result;

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {

            // Number of nodes at the current level
            int levelSize = queue.size();

            // Store values of the current level
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < levelSize; i++) {

                TreeNode node = queue.poll();

                level.add(node.val);

                // Add children for the next level
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }

            // Add the completed level to the result
            result.add(level);
        }

        // Reverse the order of levels, not their values
        Collections.reverse(result);

        return result;
    }
}
```

## 🧪 Example

For this tree:

```text
        3
       / \
      9   20
          / \
         15  7
```

Normal level order:

```text
[[3], [9, 20], [15, 7]]
```

After reversing the levels:

```text
[[15, 7], [9, 20], [3]]
```

## 🔑 Key Trick

```java
Collections.reverse(result);
```

This reverses the **order of the levels** while keeping the elements within each level unchanged.

## ⏱️ Complexity

- **Time:** `O(n)` — every node is visited once, and reversing the result takes `O(n)` overall.
- **Space:** `O(n)` — for the queue and result.

## 🎯 Key Takeaway

**Normal BFS + reverse the result = Bottom-Up Level Order Traversal.**

⭐ If this helped, please consider giving this solution an upvote!
