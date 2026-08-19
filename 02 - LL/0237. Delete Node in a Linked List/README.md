
# 🚀 237. Delete Node in a Linked List | Copy & Skip | O(1) Time | O(1) Space | Java

## 🔗 Problem

[LeetCode 237 — Delete Node in a Linked List](https://leetcode.com/problems/delete-node-in-a-linked-list/)

---

## 🧠 Approach

We are given the node that needs to be deleted, but **not its previous node**.

Normally, to delete a node, we would need:

```text
previous → node → next
```

But since we don't have `previous`, we use a simple trick:

### 1. Copy the next node's value

```java
node.val = node.next.val;
```

### 2. Skip the next node

```java
node.next = node.next.next;
```

So the approach is:

```text
COPY → SKIP
```

---

## 💡 Example

Before:

```text
1 → 2 → 3 → 4
        ↑
       node
```

Copy the next node's value:

```text
1 → 2 → 4 → 4
        ↑
       node
```

Skip the next node:

```text
1 → 2 → 4
```

The original `3` is effectively removed.

---

## 💻 Java Solution

```java
class Solution {
    public void deleteNode(ListNode node) {

        // Copy the next node's value
        node.val = node.next.val;

        // Skip the next node
        node.next = node.next.next;
    }
}
```

---

## ⚠️ Important

The problem guarantees that the given node is **not the last node**.

Therefore:

```java
node.next
```

will always exist.

---

## 📊 Complexity

### Time Complexity

Only two operations are performed:

```text
O(1)
```

### Space Complexity

No extra data structures are used:

```text
O(1)
```

### Final Complexity

```text
Time  : O(1)
Space : O(1)
```

---

## 🎯 Key Takeaway

Since we don't have access to the previous node:

```text
Copy the next node's value
          ↓
Skip the next node
```

Remember:

> **COPY → SKIP** 🚀
