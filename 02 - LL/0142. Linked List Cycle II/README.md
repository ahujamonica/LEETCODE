
# 🚀 142. Linked List Cycle II | Floyd's Tortoise & Hare

> 📂 Topic: Linked List  
> 🎯 Pattern: Fast & Slow Pointers  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

## 🔗 Problem

[LeetCode 142 — Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)

---

## 🧠 Intuition

The goal is to find the **node where the cycle begins** in a linked list.

For example:

```text
1 → 2 → 3 → 4 → 5
        ↑       ↓
        └───────┘
```

The cycle starts at node `3`.

We use **Floyd's Tortoise and Hare algorithm** with two pointers:

- `slow` moves one node at a time.
- `fast` moves two nodes at a time.

---

## 🚀 Approach

### 1️⃣ Detect the Cycle

Start both pointers at the head:

```java
ListNode slow = head;
ListNode fast = head;
```

Move them at different speeds:

```java
slow = slow.next;
fast = fast.next.next;
```

If a cycle exists, `slow` and `fast` will eventually meet.

If `fast` reaches `null`, there is no cycle.

---

### 2️⃣ Find the Start of the Cycle

Once `slow` and `fast` meet, move one pointer back to `head`.

```text
head → 1

slow → meeting point
```

Then move both pointers **one step at a time**.

```java
while (head != slow) {
    head = head.next;
    slow = slow.next;
}
```

They will meet exactly at the **start of the cycle**.

---

## 💡 Example

```text
1 → 2 → 3 → 4 → 5
        ↑       ↓
        └───────┘
```

Cycle starts at:

```text
3
```

After detecting the cycle:

```text
head → 1
slow → meeting point
```

Move both one step at a time until:

```text
head → 3
slow → 3
```

Therefore, node `3` is the cycle's starting node.

---

## 💻 Java Solution

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        // Detect the cycle
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {

                // Find the start of the cycle
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }

                return slow;
            }
        }

        // No cycle
        return null;
    }
}
```

---

## 🔑 Key Points

### Why `slow == fast`?

We are comparing **node references**, not node values.

```java
if (slow == fast)
```

means both pointers are pointing to the **same `ListNode` object**.

---

### Why return `slow`?

After the second phase:

```text
head → 3
slow → 3
```

Both point to the cycle's starting node.

So:

```java
return slow;
```

returns that `ListNode`.

It doesn't literally return the entire linked list. A `ListNode` is simply the entry point from which the rest of the chain can be traversed.

---

## 🎯 Algorithm

```text
slow = 1 step
fast = 2 steps
      ↓
Do they meet?
   ↓       ↓
  NO      YES
  ↓        ↓
null    Move one pointer to head
           ↓
      Move both 1 step
           ↓
      Meeting point
           ↓
       Cycle Start
```

---

## 📊 Complexity

### Time Complexity

```text
O(n)
```

The linked list is traversed a constant number of times.

### Space Complexity

```text
O(1)
```

Only two pointers are used.

---

## 🏆 Final Complexity

```text
Time  : O(n)
Space : O(1)
```

---

## ⭐ Key Takeaway

> **Use Floyd's Tortoise & Hare: detect the cycle with two pointers, reset one pointer to the head, then move both one step at a time to find the cycle's starting node.**

