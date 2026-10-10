# 🌳 Keep It Simple | Inorder Traversal with Recursion

🔗 **LeetCode:** https://leetcode.com/problems/binary-tree-inorder-traversal/

## 🧠 Intuition

Inorder traversal means visiting every node in this order:

**LEFT → ROOT → RIGHT**

We recursively visit the left subtree, add the current node's value to `res`, and then recursively visit the right subtree.

## 💡 Approach

- **Base case:** If `root == null`, return the result list.
- **Left:** Recursively traverse the left subtree.
- **Root:** Add `root.val` to the shared `res` list.
- **Right:** Recursively traverse the right subtree.

The same `res` list is shared across all recursive calls, so every node gets added to one list.

## 💻 Java Solution

```java
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {

        // Base case: no node to process
        if(root == null) {
            return res;
        }

        // 1. Traverse the left subtree
        inorderTraversal(root.left);

        // 2. Add the current node's value
        res.add(root.val);

        // 3. Traverse the right subtree
        inorderTraversal(root.right);

        return res;
    }
}
```

## 🔑 Key Trick

```text
LEFT → ROOT → RIGHT
```

When `root == null`, `return res` ends the current recursive call and returns control to the previous call.

## ⏱️ Complexity

- **Time:** `O(n)` — every node is visited once.
- **Space:** `O(n)` — to store the result, with up to `O(h)` recursion stack space, where `h` is the tree height.

## 🎯 Key Takeaway

**Recursion moves through the tree; the shared list stores the visited values.**

⭐ If this helped, please consider giving this solution an upvote!
