
# 🚀 160. Intersection of Two Linked Lists

> 📂 Topic: Linked List  
> 🎯 Pattern: Two Pointers + Length Alignment  
> ⭐ Difficulty: Easy  
> ⏱️ Time: O(m + n) | 💾 Space: O(1)

---

## 🔗 Problem Link

[LeetCode 160 — Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/)

---

## 🧠 Intuition

Two linked lists can share the same nodes after a certain point.

For example:

```text
List A: 1 → 2 → 3 ┐
                  ├→ 7 → 8 → 9
List B:    4 → 5 ┘
```

The intersection node is:

```text
7
```

The important point is that an intersection means the **same node/reference**, not just the same value.

---

## 🚀 Approach

The lists may have different lengths, so starting both pointers from their heads would not necessarily make them reach the intersection together.

We solve this in 3 steps:

### 1. Find the lengths

```java
int lenA = getListLength(headA);
int lenB = getListLength(headB);
```

### 2. Align both lists

If one list is longer, move its pointer forward by the difference in lengths.

For example:

```text
A = 5 nodes
B = 3 nodes
```

Move `headA` forward by:

```text
5 - 3 = 2 nodes
```

Now both pointers have the same number of nodes remaining.

### 3. Move both pointers together

```java
while (headA != headB) {
    headA = headA.next;
    headB = headB.next;
}
```

They will either meet at the intersection node or both become `null`.

---

## 🔍 Why Compare `headA != headB`?

We compare the **actual nodes**, not their values.

This:

```java
headA.val == headB.val
```

doesn't necessarily mean the lists intersect.

We need:

```java
headA == headB
```

which means both pointers refer to the **same `ListNode` object**.

---

## 📝 Example

```text
A: 1 → 2 → 3 → 7 → 8
B:     4 → 5 → 7 → 8
```

Lengths:

```text
A = 5
B = 4
```

Move A one step:

```text
A:     2 → 3 → 7 → 8
       ↑
     headA

B: 4 → 5 → 7 → 8
     ↑
   headB
```

Now both pointers have the same distance to the end.

Move together:

```text
3 vs 5
7 vs 7  ← intersection
```

So:

```text
return headA;
```

returns the node `7`.

---

## 💻 Java Solution

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        // Find the length of both lists
        int lenA = getListLength(headA);
        int lenB = getListLength(headB);

        // Move the longer list forward
        while (lenA > lenB) {
            lenA--;
            headA = headA.next;
        }

        while (lenB > lenA) {
            lenB--;
            headB = headB.next;
        }

        // Move both pointers together
        // until they point to the same node
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        // Intersection node or null
        return headA;
    }

    private int getListLength(ListNode head) {

        int len = 0;

        while (head != null) {
            len++;
            head = head.next;
        }

        return len;
    }
}
```

---

## 🎯 Key Takeaway

```text
Find lengths
     ↓
Align both lists
     ↓
Move both pointers together
     ↓
Meet at intersection
```

Think:

> **Align → Move Together → Meet**

---

## 📊 Complexity

Let:

```text
m = length of List A
n = length of List B
```

### Time Complexity

We traverse both lists to calculate their lengths and then traverse them again:

```text
O(m + n)
```

### Space Complexity

Only a few variables and pointers are used:

```text
O(1)
```

### Final Complexity

```text
Time  = O(m + n)
Space = O(1)
```

---

## 🧠 Pattern to Remember

The main idea is:

```text
Longer List
    ↓
Move ahead by length difference
    ↓
       A ──────────┐
                   ↓
                   7 → 8 → 9
                   ↑
       B ──────────┘
    ↓
Move both at same speed
    ↓
Find same node
```

> **When two linked lists have different lengths, align their starting points by removing the extra distance from the longer list. Then move both pointers together until they meet.** 🚀
