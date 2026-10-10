# 🌳 Keep It Simple | Postorder Traversal with Recursion

🔗 **LeetCode:** https://leetcode.com/problems/binary-tree-postorder-traversal/

## 🧠 Intuition

Postorder traversal follows **LEFT → RIGHT → ROOT**. We recursively traverse the left subtree, then the right subtree, and finally add the current node's value to the shared `list`.

## 💡 Approach

- **Base case:** If `root == null`, return the list.
- **Left:** Traverse the left subtree recursively.
- **Right:** Traverse the right subtree recursively.
- **Root:** Add `root.val` to the list only after both subtrees are processed.

## 💻 Java Solution

```java
class Solution {
    List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {

        // Base case: no node to process
        if(root == null) {
            return list;
        }

        // 1. Traverse the left subtree
        postorderTraversal(root.left);

        // 2. Traverse the right subtree
        postorderTraversal(root.right);

        // 3. Visit the current node last
        list.add(root.val);

        return list;
    }
}
```

## 🔑 Key Trick

```text
LEFT → RIGHT → ROOT
```

The root is added **last**, after both its left and right subtrees have been traversed.

## ⏱️ Complexity

- **Time:** `O(n)` — every node is visited once.
- **Space:** `O(n)` for the result list, with up to `O(h)` recursion stack space, where `h` is the tree height.

## 🎯 Key Takeaway

**Postorder = Process both children first, then the root.**

⭐ If this helped, please consider giving this solution an upvote!
