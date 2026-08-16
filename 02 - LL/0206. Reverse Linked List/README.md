# 🚀 206. Reverse Linked List

> 📂 Topic: Linked List  
> 🎯 Pattern: Iterative Pointer Reversal  
> ⭐ Difficulty: Easy  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

# 🔗 Problem Link

[LeetCode 206 — Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)

---

# 🧠 One-Line Idea

> Reverse each node's `next` pointer one by one using three pointers: `preNode`, `currentNode`, and `nextNode`.

---

# 💡 Intuition

We are given a singly linked list:

```text
1 → 2 → 3 → 4 → 5 → null
```

We need to reverse it:

```text
5 → 4 → 3 → 2 → 1 → null
```

The important thing to remember is:

```text
Original:

1 → 2

Reversed:

1 ← 2
```

So for every node, we need to change:

```java
currentNode.next
```

from pointing to the **next node** to pointing to the **previous node**.

---

# 🚀 Approach — Iterative

We use three pointers:

```text
preNode
currentNode
nextNode
```

Their purpose is:

| Pointer | Purpose |
|---|---|
| `preNode` | Keeps track of the previous node |
| `currentNode` | Node currently being processed |
| `nextNode` | Temporarily stores the next node |

---

# 1️⃣ Initialize `preNode`

```java
ListNode preNode = null;
```

Initially, the first node will become the **last node** after reversal.

Therefore, its new `next` should be:

```text
null
```

So:

```text
preNode = null
```

---

# 2️⃣ Initialize `currentNode`

```java
ListNode currentNode = head;
```

We start processing from the head.

For:

```text
1 → 2 → 3 → 4
```

we have:

```text
currentNode
     ↓
     1 → 2 → 3 → 4
```

---

# 3️⃣ Save the Next Node

Inside the loop:

```java
ListNode nextNode = currentNode.next;
```

This is extremely important.

Suppose:

```text
currentNode = 1
```

Then:

```text
nextNode = 2
```

We save `2` before changing `1`'s pointer.

Otherwise, after reversing:

```java
currentNode.next = preNode;
```

we would lose our connection to the remaining list.

---

# 4️⃣ Reverse the Pointer

```java
currentNode.next = preNode;
```

Initially:

```text
preNode = null
currentNode = 1
```

So:

```text
1 → 2
```

becomes:

```text
1 → null
```

We have successfully reversed the first pointer.

---

# 5️⃣ Move `preNode`

```java
preNode = currentNode;
```

Now:

```text
preNode = 1
```

So the reversed portion is:

```text
1 → null
```

---

# 6️⃣ Move `currentNode`

```java
currentNode = nextNode;
```

Since:

```text
nextNode = 2
```

we move:

```text
currentNode = 2
```

Now we can process the next node.

---

# 🔄 The Four Important Lines

The entire reversal process is based on these four lines:

```java
ListNode nextNode = currentNode.next;
currentNode.next = preNode;
preNode = currentNode;
currentNode = nextNode;
```

Think of them as:

```text
1️⃣ SAVE
   ↓
2️⃣ REVERSE
   ↓
3️⃣ MOVE PREVIOUS
   ↓
4️⃣ MOVE CURRENT
```

---

# 📝 Dry Run

Consider:

```text
1 → 2 → 3 → null
```

Initially:

```text
preNode = null
currentNode = 1
```

### Iteration 1

Save:

```text
nextNode = 2
```

Reverse:

```text
1 → null
```

Move:

```text
preNode = 1
currentNode = 2
```

---

### Iteration 2

Save:

```text
nextNode = 3
```

Reverse:

```text
2 → 1 → null
```

Move:

```text
preNode = 2
currentNode = 3
```

---

### Iteration 3

Save:

```text
nextNode = null
```

Reverse:

```text
3 → 2 → 1 → null
```

Move:

```text
preNode = 3
currentNode = null
```

The loop stops because:

```java
currentNode == null
```

Now:

```text
preNode
   ↓
3 → 2 → 1 → null
```

So `preNode` is the new head.

---

# ⚠️ Why Do We Need `nextNode`?

This is one of the most important concepts.

Suppose:

```text
1 → 2 → 3 → 4
```

If we directly do:

```java
currentNode.next = preNode;
```

without first saving:

```java
ListNode nextNode = currentNode.next;
```

we change:

```text
1 → 2
```

into:

```text
1 → null
```

and lose our reference to:

```text
2 → 3 → 4
```

Therefore, always follow this order:

```text
Save next
    ↓
Reverse pointer
    ↓
Move previous
    ↓
Move current
```
---

# 🎯 Key Takeaway

The original list:

```text
1 → 2 → 3 → 4 → 5 → null
```

becomes:

```text
5 → 4 → 3 → 2 → 1 → null
```

by reversing one pointer at a time.

The mental model:

```text
        current
           ↓
1 → 2 → 3 → 4

Save next
    ↓
Reverse current.next
    ↓
Move preNode
    ↓
Move currentNode
    ↓
Repeat
```

At the end:

```text
currentNode = null
preNode = new head
```

Therefore:

```java
return preNode;
```

---

# 📊 Complexity

## Time Complexity

We visit every node exactly once.

```text
O(n)
```

## Space Complexity

We only use three pointers:

```text
preNode
currentNode
nextNode
```

No extra data structure is required.

```text
O(1)
```

Therefore:

```text
Time  = O(n)
Space = O(1)
```

---

# 🧠 Pattern to Remember

```text
SAVE
 ↓
REVERSE
 ↓
MOVE PREVIOUS
 ↓
MOVE CURRENT
 ↓
REPEAT
```

The core code:

```java
ListNode nextNode = currentNode.next;
currentNode.next = preNode;
preNode = currentNode;
currentNode = nextNode;
```

> **Save the next node, reverse the current pointer, then move both pointers forward. When `currentNode` becomes `null`, `preNode` is the new head.**

Happy Coding! 🚀✨
