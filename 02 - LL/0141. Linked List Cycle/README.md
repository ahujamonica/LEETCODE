# 🚀 141. Linked List Cycle

> 📂 Topic: Linked List  
> 🎯 Pattern: Fast & Slow Pointers  
> ⭐ Difficulty: Easy  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

## 🔗 Problem Link

[LeetCode 141 — Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)

---

## 🧠 Intuition

To determine whether a linked list contains a cycle, we use two pointers:

- `slowptr` moves **1 step**
- `fastptr` moves **2 steps**

If a cycle exists, the faster pointer will eventually catch up with the slower pointer.

If there is no cycle, `fastptr` will eventually reach `null`.

This is known as **Floyd's Cycle Detection Algorithm**.

---

## 🚀 Approach

Initialize both pointers at the head:

```java
ListNode slowptr = head;
ListNode fastptr = head;
```

Then move them at different speeds:

```text
slow → 1 step
fast → 2 steps
```

Continue while `fastptr` can safely move two steps:

```java
while (fastptr != null && fastptr.next != null)
```

After moving them, check whether they point to the same node:

```java
if (slowptr == fastptr)
    return true;
```

If `fastptr` reaches `null`, there is no cycle.

---

## 💡 Example

### Cycle exists

```text
1 → 2 → 3 → 4
        ↑     ↓
        ← ← ←
```

Eventually:

```text
slowptr → 3
fastptr → 3
```

Since:

```java
slowptr == fastptr
```

we return:

```text
true
```

### No cycle

```text
1 → 2 → 3 → 4 → null
```

Eventually:

```text
fastptr → null
```

So we return:

```text
false
```

---

## ⚠️ Why `fastptr != null && fastptr.next != null`?

We move the fast pointer using:

```java
fastptr = fastptr.next.next;
```

Therefore, both `fastptr` and `fastptr.next` must exist.

The order is important:

```java
fastptr != null && fastptr.next != null
```

Java evaluates `&&` from left to right.

If:

```java
fastptr == null
```

the first condition is false, so Java stops checking and never tries:

```java
fastptr.next
```

This prevents a `NullPointerException`.

---

## 💻 Java Solution

```java
public class Solution {
    public boolean hasCycle(ListNode head) {

        ListNode slowptr = head;
        ListNode fastptr = head;

        while (fastptr != null && fastptr.next != null) {

            // Move slow pointer one step
            slowptr = slowptr.next;

            // Move fast pointer two steps
            fastptr = fastptr.next.next;

            // If both point to the same node, a cycle exists
            if (slowptr == fastptr) {
                return true;
            }
        }

        return false;
    }
}
```

---

## 🎯 Key Takeaway

```text
slow → +1
fast → +2
```

If there is a cycle:

```text
slow == fast
    ↓
  true
```

If there is no cycle:

```text
fast → null
    ↓
  false
```

> **Fast & Slow Pointers → Move → Compare → Detect Cycle**

---

## 📊 Complexity

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

### Final

```text
Time  = O(n)
Space = O(1)
```

---

## 🧠 Pattern to Remember

```text
Start both at head
       ↓
Slow moves 1 step
Fast moves 2 steps
       ↓
Do they meet?
  ↙          ↘
 YES          NO
  ↓            ↓
Cycle      Fast reaches null
```

**Floyd's Cycle Detection = Fast & Slow Pointers** 🚀
