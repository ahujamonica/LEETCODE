
# 🚀 876. Middle of the Linked List

> 📂 Topic: Linked List  
> 🎯 Pattern: Fast & Slow Pointers  
> ⭐ Difficulty: Easy  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

# 🔗 Problem Link

[LeetCode 876 — Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)

---

# 🧠 One-Line Idea

> Use two pointers where `slow` moves one node at a time and `fast` moves two nodes at a time. When `fast` reaches the end, `slow` will be at the middle.

---

# 💡 Intuition

The goal is to find the middle node of a singly linked list.

For example:

```text
1 → 2 → 3 → 4 → 5 → null
```

The middle node is:

```text
3
```

For an even number of nodes:

```text
1 → 2 → 3 → 4 → 5 → 6 → null
```

there are two middle nodes:

```text
3 and 4
```

LeetCode asks us to return the **second middle node**:

```text
4 → 5 → 6 → null
```

---

# 🐢🐇 Fast & Slow Pointer Approach

We use two pointers:

```text
slow → moves 1 step
fast → moves 2 steps
```

Initialize both pointers at the head:

```java
ListNode slow = head;
ListNode fast = head;
```

Then repeatedly move:

```java
slow = slow.next;
fast = fast.next.next;
```

Since `fast` moves twice as fast as `slow`, when `fast` reaches the end of the list, `slow` will be at the middle.

---

# 🔄 Example — Odd Number of Nodes

Consider:

```text
1 → 2 → 3 → 4 → 5 → null
```

Initially:

```text
slow
 ↓
1 → 2 → 3 → 4 → 5
 ↑
fast
```

### Iteration 1

```text
slow → 2
fast → 3
```

Now:

```text
1 → 2 → 3 → 4 → 5
    ↑   ↑
   slow fast
```

### Iteration 2

```text
slow → 3
fast → 5
```

Now:

```text
1 → 2 → 3 → 4 → 5
        ↑       ↑
       slow    fast
```

`fast` has reached the last node.

Therefore:

```text
slow = 3
```

🎯 Middle = `3`

---

# 🔄 Example — Even Number of Nodes

Consider:

```text
1 → 2 → 3 → 4 → 5 → 6 → null
```

Initially:

```text
slow = 1
fast = 1
```

### Iteration 1

```text
slow → 2
fast → 3
```

### Iteration 2

```text
slow → 3
fast → 5
```

### Iteration 3

```text
slow → 4
fast → null
```

Therefore:

```text
slow = 4
```

So the returned list is:

```text
4 → 5 → 6 → null
```

This is the **second middle**, which is what LeetCode expects.

---

# 🧠 Why Does This Work?

Suppose the linked list contains `n` nodes.

For every:

```text
1 step taken by slow
```

`fast` takes:

```text
2 steps
```

Therefore, when `fast` has travelled through the entire list, `slow` has travelled approximately half the distance.

```text
fast → end
   ↓
slow → middle
```

This lets us find the middle without calculating the length of the linked list first.

---

# ⚠️ Understanding the `while` Condition

We use:

```java
while (fast != null && fast.next != null)
```

because inside the loop we perform:

```java
fast = fast.next.next;
```

We need to make sure both:

```text
fast
```

and:

```text
fast.next
```

exist before moving `fast` two nodes forward.

Otherwise, we could get a:

```text
NullPointerException
```

So:

```java
fast != null
```

checks that `fast` exists.

And:

```java
fast.next != null
```

checks that `fast` has another node available.

---

# 🔑 Core Code

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;
}

return slow;
```

The entire solution is based on these few lines.

---

# 📝 Dry Run

For:

```text
1 → 2 → 3 → 4 → 5
```

Initial:

```text
slow = 1
fast = 1
```

### Iteration 1

```text
slow = 2
fast = 3
```

### Iteration 2

```text
slow = 3
fast = 5
```

Now:

```text
fast.next == null
```

so the loop stops.

Therefore:

```text
slow = 3
```

Return:

```text
3 → 4 → 5
```

---

# 💡 What Does `return slow` Actually Return?

This is an important linked-list concept.

Suppose:

```text
1 → 2 → 3 → 4 → 5 → null
        ↑
       slow
```

When we write:

```java
return slow;
```

we return the **node reference** pointing to `3`.

But `3` still points to:

```text
3 → 4 → 5 → null
```

Therefore, the returned linked list is:

```text
3 → 4 → 5 → null
```

We are **not** returning only the value `3`.

If we wrote:

```java
return slow.val;
```

we would return only the integer value.

But:

```java
return slow;
```

returns the node and everything connected after it.

---

# 💻 Java Solution

```java
class Solution {
    public ListNode middleNode(ListNode head) {

        // Slow moves one node at a time
        ListNode slow = head;

        // Fast moves two nodes at a time
        ListNode fast = head;

        // Continue while fast can safely move two steps
        while (fast != null && fast.next != null) {

            // Move slow by one node
            slow = slow.next;

            // Move fast by two nodes
            fast = fast.next.next;
        }

        // Slow is now pointing to the middle node
        // For even length, it points to the second middle
        return slow;
    }
}
```

---

# 🎯 Key Takeaway

Remember:

```text
slow → 1 step
fast → 2 steps
```

Then:

```text
fast reaches the end
        ↓
slow reaches the middle
```

The pattern:

```text
List:

1 → 2 → 3 → 4 → 5
        ↑
       slow


1 → 2 → 3 → 4 → 5 → 6
            ↑
           slow
```

For an even-sized list, this setup automatically gives the **second middle**.

---

# 📊 Complexity

## Time Complexity

We traverse the linked list once.

```text
O(n)
```

## Space Complexity

We only use two pointers:

```text
slow
fast
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
FAST & SLOW POINTERS

slow → 1 step
fast → 2 steps
       ↓
fast reaches end
       ↓
slow reaches middle
```

> **Use a slow pointer moving one step and a fast pointer moving two steps. When the fast pointer reaches the end, the slow pointer is at the middle of the linked list.**

Happy Coding! 🚀✨
