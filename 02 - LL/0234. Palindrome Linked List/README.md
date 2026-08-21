# 🚀 234. Palindrome Linked List

> 📂 Topic: Linked List  
> 🎯 Pattern: Fast & Slow Pointers + Reverse Linked List  
> ⭐ Difficulty: Easy  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

## 🔗 Problem

[LeetCode 234 — Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/)

---

## 🧠 Intuition

A linked list is a palindrome if it reads the same forward and backward.

Example:

```text
1 → 2 → 3 → 2 → 1
```

To check this in **O(n) time and O(1) extra space**, we:

1. Find the middle using **fast & slow pointers**
2. Skip the middle if the list has odd length
3. Reverse the second half
4. Compare the first half with the reversed second half

This is the standard O(n)/O(1) approach for the problem. :contentReference[oaicite:0]{index=0}

---

## 🚀 Approach

### 1️⃣ Find the Middle

```java
ListNode fast = head;
ListNode slow = head;

while (fast != null && fast.next != null) {
    fast = fast.next.next;
    slow = slow.next;
}
```

- `slow` moves **1 step**
- `fast` moves **2 steps**

When `fast` reaches the end, `slow` is around the middle.

---

### 2️⃣ Skip the Middle for Odd-Length Lists

```java
if (fast != null) {
    slow = slow.next;
}
```

For:

```text
1 → 2 → 3 → 2 → 1
        ↑
      middle
```

we don't need to compare the middle node, so we move `slow` forward.

---

### 3️⃣ Reverse the Second Half

```java
slow = reverseList(slow);
```

Example:

```text
1 → 2 → 3 → 2 → 1
```

Second half:

```text
2 → 1
```

After reversing:

```text
1 → 2
```

Now both halves can be compared in the same direction.

---

### 4️⃣ Compare Both Halves

```java
fast = head;

while (slow != null) {
    if (fast.val != slow.val) {
        return false;
    }

    fast = fast.next;
    slow = slow.next;
}
```

If any values differ:

```text
false
```

If every value matches:

```text
true
```

---

## 💡 Example

```text
Original:

1 → 2 → 3 → 2 → 1

First half:

1 → 2

Second half:

2 → 1

Reverse second half:

1 → 2

Compare:

1 == 1 ✅
2 == 2 ✅

Result:

true
```

---

## 💻 Java Solution

```java
class Solution {
    public boolean isPalindrome(ListNode head) {

        // Find the middle
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Skip middle for odd-length lists
        if (fast != null) {
            slow = slow.next;
        }

        // Reverse the second half
        slow = reverseList(slow);

        // Start comparison from the head
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }

            fast = fast.next;
            slow = slow.next;
        }

        return true;
    }

    private ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;

            current.next = prev;

            prev = current;
            current = next;
        }

        return prev;
    }
}
```

---

## 🎯 Key Takeaway

Remember the pattern:

```text
Find Middle
     ↓
Skip Middle if Odd
     ↓
Reverse Second Half
     ↓
Compare Both Halves
     ↓
Mismatch → false
All Match → true
```

### Core Techniques

```text
Fast & Slow Pointers
        +
Reverse Linked List
        =
Palindrome Check
```

---

## 📊 Complexity

### Time

The list is traversed a constant number of times:

```text
O(n)
```

### Space

The second half is reversed **in-place**, using only a few pointers:

```text
O(1)
```

### Final Complexity

```text
Time  : O(n)
Space : O(1)
```

> **Find the middle → reverse the second half → compare both halves.** 🚀
