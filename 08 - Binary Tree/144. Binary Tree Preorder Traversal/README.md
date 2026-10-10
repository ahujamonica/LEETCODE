# 🌳 Keep It Simple | Preorder Traversal with Recursion

🔗 **LeetCode:** https://leetcode.com/problems/binary-tree-preorder-traversal/

## 🧠 Intuition

Preorder traversal means visiting every node in this order:

**ROOT → LEFT → RIGHT**

We first add the current node's value to `res`, then recursively traverse the left subtree, followed by the right subtree.

## 💡 Approach

- **Base case:** If `root == null`, return the result list.
- **Root:** Add `root.val` to the shared `res` list.
- **Left:** Recursively traverse the left subtree.
- **Right:** Recursively traverse the right subtree.

The same `res` list is shared across all recursive calls, so every node's value is stored in one list.

## 💻 Java Solution

```java
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {

        // Base case: no node to process
        if(root == null) {
            return res;
        }

        // 1. Visit the current node first
        res.add(root.val);

        // 2. Traverse the left subtree
        preorderTraversal(root.left);

        // 3. Traverse the right subtree
        preorderTraversal(root.right);

        return res;
    }
}
```

## 🔑 Key Trick

```text
ROOT → LEFT → RIGHT
```

Unlike inorder traversal, preorder adds the current node's value **before** exploring its children.

## ⏱️ Complexity

- **Time:** `O(n)` — every node is visited once.
- **Space:** `O(n)` — to store the result, with up to `O(h)` recursion stack space, where `h` is the tree height.

## 🎯 Key Takeaway

**Preorder = Visit the root first, then explore left and right.**

⭐ If this helped, please consider giving this solution an upvote!
