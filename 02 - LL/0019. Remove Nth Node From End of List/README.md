# 🚀 19. Remove Nth Node From End of List

> 📂 Topic: Linked List  
> 🎯 Pattern: Two Pointers + Dummy Node  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

# 🔗 Problem Link

[LeetCode 19 — Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

---

# 🧠 One-Line Idea

> Maintain an `n`-node gap between two pointers. When the fast pointer reaches the end, the slow pointer will be positioned immediately before the node that needs to be removed.

---

# 💡 Intuition

We are given a linked list and an integer `n`.

We need to remove the `nth` node from the **end** of the list.

For example:

```text
Input:

1 → 2 → 3 → 4 → 5
n = 2
```

The 2nd node from the end is:

```text
4
```

So the result is:

```text
1 → 2 → 3 → 5
```

The challenge is to find the node from the end without first calculating the length of the linked list.

We can solve this in one traversal using **two pointers**.

---

# 🚀 Approach

We use:

```text
ptr1 → slow pointer
ptr2 → fast pointer
```

and maintain a gap of exactly `n` nodes between them.

We also use a **dummy node** before the head.

```text
dummy → 1 → 2 → 3 → 4 → 5 → null
```

Initially:

```text
ptr1 = dummy
ptr2 = dummy
```

---

# 1️⃣ Create a Dummy Node

```java
ListNode dummy = new ListNode(-1);
```

Then:

```java
dummy.next = head;
```

Now:

```text
dummy → 1 → 2 → 3 → 4 → 5 → null
```

### Why use a dummy node?

The node we need to remove could be the **head itself**.

For example:

```text
1 → 2 → 3
n = 3
```

We need to remove `1`.

With a dummy node:

```text
dummy → 1 → 2 → 3
```

we can remove `1` using the same logic without needing a special case.

---

# 2️⃣ Initialize Both Pointers

```java
ListNode ptr1 = dummy;
ListNode ptr2 = dummy;
```

Initially:

```text
dummy → 1 → 2 → 3 → 4 → 5
  ↑
ptr1
  ↑
ptr2
```

Both pointers start at the dummy node.

---

# 3️⃣ Move `ptr2` N Steps Ahead

```java
for (int i = 0; i < n; i++) {
    ptr2 = ptr2.next;
}
```

For:

```text
n = 2
```

`ptr2` moves two nodes:

```text
dummy → 1 → 2 → 3 → 4 → 5
  ↑         ↑
ptr1       ptr2
```

Now there is a fixed gap between the two pointers.

---

# 🧠 Why Maintain an N-Node Gap?

Suppose:

```text
1 → 2 → 3 → 4 → 5
n = 2
```

We want to remove:

```text
4
```

Therefore, we want `ptr1` to eventually point to:

```text
3
```

which is immediately before `4`.

By keeping `ptr2` `n` nodes ahead:

```text
ptr1 ←──── n-node gap ────→ ptr2
```

when `ptr2` reaches the end:

```text
ptr1 → node before target
```

---

# 4️⃣ Move Both Pointers Together

Now:

```java
while (ptr2.next != null) {
    ptr1 = ptr1.next;
    ptr2 = ptr2.next;
}
```

Both pointers move one node at a time.

Because the gap between them is always `n`, `ptr1` will eventually reach the node immediately before the target.

---

# 📝 Dry Run

Consider:

```text
1 → 2 → 3 → 4 → 5
n = 2
```

After moving `ptr2` two steps:

```text
dummy → 1 → 2 → 3 → 4 → 5
  ↑         ↑
ptr1       ptr2
```

### Move 1

```text
ptr1 → 1
ptr2 → 3
```

### Move 2

```text
ptr1 → 2
ptr2 → 4
```

### Move 3

```text
ptr1 → 3
ptr2 → 5
```

Now:

```text
ptr2.next == null
```

so the loop stops.

Therefore:

```text
ptr1 → 3
ptr1.next → 4
```

The node we need to remove is `4`.

---

# 5️⃣ Remove the Target Node

We use:

```java
ptr1.next = ptr1.next.next;
```

Before:

```text
3 → 4 → 5
```

Here:

```text
ptr1 = 3
ptr1.next = 4
ptr1.next.next = 5
```

So:

```java
ptr1.next = ptr1.next.next;
```

is equivalent to:

```text
3.next = 5
```

The result becomes:

```text
3 → 5
```

Therefore, the complete list becomes:

```text
1 → 2 → 3 → 5 → null
```

---

# 🔑 Understanding `ptr1.next = ptr1.next.next`

This line is important.

Suppose:

```text
ptr1
 ↓
3 → 4 → 5
```

We want to skip `4`.

Currently:

```text
ptr1.next = 4
```

and:

```text
ptr1.next.next = 5
```

Therefore:

```java
ptr1.next = ptr1.next.next;
```

changes:

```text
3 → 4 → 5
```

into:

```text
3 → 5
```

The node `4` is no longer connected to the list.

---

# 🔗 Why We Don't Need to Delete the Node Manually

We don't need:

```java
delete(...)
```

or anything similar.

In Java, once the node is no longer reachable from the head of the linked list, it can eventually be handled by garbage collection.

We simply remove its connection:

```text
3.next = 5
```

---

# 💡 What If the Head Needs to Be Removed?

Consider:

```text
1 → 2 → 3
n = 3
```

The target is `1`.

Because we created:

```text
dummy → 1 → 2 → 3
```

`ptr1` eventually points to:

```text
dummy
```

Then:

```java
ptr1.next = ptr1.next.next;
```

becomes:

```text
dummy.next = 1.next;
```

So:

```text
dummy → 2 → 3
```

Finally:

```java
return dummy.next;
```

returns:

```text
2 → 3
```

No special case is required.

---

# 6️⃣ Attach the Remaining List

In this problem, unlike merging two lists, we don't need to manually attach anything after the removal.

Once:

```java
ptr1.next = ptr1.next.next;
```

is executed, the list is automatically connected correctly.

---

# 7️⃣ Return `dummy.next`

At the end:

```text
dummy → 1 → 2 → 3 → 5
```

We don't return `dummy`.

Instead:

```java
return dummy.next;
```

returns the actual head:

```text
1 → 2 → 3 → 5
```

---

# 💻 Java Solution

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // Create a dummy node before the head
        ListNode dummy = new ListNode(-1);

        // Connect dummy to the original list
        dummy.next = head;

        // Initialize both pointers at dummy
        ListNode ptr1 = dummy;
        ListNode ptr2 = dummy;

        // Move ptr2 n nodes ahead
        for (int i = 0; i < n; i++) {
            ptr2 = ptr2.next;
        }

        // Move both pointers together
        // until ptr2 reaches the last node
        while (ptr2.next != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // ptr1 is now immediately before
        // the node that needs to be removed
        ptr1.next = ptr1.next.next;

        // Return the actual head, skipping the dummy node
        return dummy.next;
    }
}
```

---

# 🎯 Key Takeaway

The entire approach can be remembered as:

```text
Create dummy
     ↓
ptr1 = dummy
ptr2 = dummy
     ↓
Move ptr2 N steps ahead
     ↓
Move both pointers together
     ↓
ptr2 reaches the end
     ↓
ptr1 is before the target node
     ↓
Skip ptr1.next
     ↓
Return dummy.next
```

The key relationship is:

```text
ptr1 ←──── N nodes ────→ ptr2
```

When:

```text
ptr2 → end
```

we get:

```text
ptr1 → node BEFORE target
```

Then:

```java
ptr1.next = ptr1.next.next;
```

removes the target node.

---

# 📊 Complexity

Let `n` be the number of nodes.

## Time Complexity

`ptr2` moves `n` steps initially, and then both pointers move through the list once.

```text
O(n)
```

## Space Complexity

Only a few pointers are used:

```text
dummy
ptr1
ptr2
```

No additional data structure is required.

```text
O(1)
```

### Final Complexity

```text
Time  = O(n)
Space = O(1)
```

---

# 🧠 Pattern to Remember

```text
        N-node gap
ptr1 ←────────────→ ptr2

        ↓

Move ptr2 N steps

        ↓

Move both together

        ↓

ptr2 reaches end

        ↓

ptr1 is BEFORE target

        ↓

ptr1.next = ptr1.next.next

        ↓

Target removed
```

> **Use two pointers with an `n`-node gap. When the fast pointer reaches the end, the slow pointer is positioned immediately before the node that needs to be removed.**

Happy Coding! 🚀✨
