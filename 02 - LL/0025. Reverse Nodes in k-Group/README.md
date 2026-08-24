
# 🚀 25. Reverse Nodes in k-Group | Recursive Approach

> 📂 Topic: Linked List  
> 🎯 Pattern: Recursion + Linked List Reversal  
> ⭐ Difficulty: Hard  
> ⏱️ Time: O(n) | 💾 Space: O(n/k)

---

## 🔗 Problem

[LeetCode 25 — Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/)

---

## 🧠 Intuition

The goal is to reverse the linked list in groups of `k`.

For example:

```text
Input:
1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
k = 3

Output:
3 → 2 → 1 → 6 → 5 → 4 → 7 → 8
```

If fewer than `k` nodes remain at the end, they stay unchanged.

The problem can be broken into the same operation repeatedly:

```text
Check → Reverse k nodes → Recurse → Connect
```

---

## 🚀 Approach

### 1️⃣ Check if `k` Nodes Exist

Before reversing, check whether the current portion contains at least `k` nodes.

```java
ListNode curr = head;

for (int i = 0; i < k; i++) {
    if (curr == null) {
        return head;
    }

    curr = curr.next;
}
```

If `curr` becomes `null` before finding `k` nodes, we have an incomplete group.

So we return `head` without modifying it.

---

### 2️⃣ Reverse Exactly `k` Nodes

Use the standard linked-list reversal technique:

```java
ListNode prev = null;
curr = head;

for (int i = 0; i < k; i++) {
    ListNode next = curr.next;

    curr.next = prev;
    prev = curr;
    curr = next;
}
```

For:

```text
1 → 2 → 3
```

the links become:

```text
1 ← 2 ← 3
```

So, starting from the new head:

```text
3 → 2 → 1
```

After reversal:

- `prev` points to `3`, the **new head**
- `head` still points to `1`, which is now the **tail**
- `curr` points to the next group

---

### 3️⃣ Recursively Process the Remaining List

Now `curr` points to the first node of the remaining list.

```java
head.next = reverseKGroup(curr, k);
```

The original `head` is now the tail of the reversed group, so we connect it to whatever the recursive call returns.

---

### 4️⃣ Return `prev`

`prev` points to the new head of the current reversed group.

Therefore:

```java
return prev;
```

---

## 🔄 Example With Recursion

For:

```text
1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
k = 3
```

### Call 1

Reverse:

```text
1 → 2 → 3
```

Result:

```text
3 → 2 → 1
```

Then recursively process:

```text
4 → 5 → 6 → 7 → 8
```

### Call 2

Reverse:

```text
4 → 5 → 6
```

Result:

```text
6 → 5 → 4
```

Then recursively process:

```text
7 → 8
```

### Call 3

Only two nodes remain:

```text
7 → 8
```

Since `2 < 3`, return them unchanged.

### Recursion returns

Call 2 connects:

```text
6 → 5 → 4 → 7 → 8
```

Call 1 connects:

```text
3 → 2 → 1 → 6 → 5 → 4 → 7 → 8
```

---

## 💻 Java Solution

```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        // Check if at least k nodes are available
        ListNode curr = head;

        for (int i = 0; i < k; i++) {
            if (curr == null) {
                return head;
            }

            curr = curr.next;
        }

        // Reverse exactly k nodes
        ListNode prev = null;
        curr = head;

        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;

            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Recursively process the remaining groups
        head.next = reverseKGroup(curr, k);

        // prev is the new head of the reversed group
        return prev;
    }
}
```

---

## 🔑 Important Pointer Roles

| Pointer | Purpose |
|---|---|
| `head` | First node of the current group |
| `curr` | Current node being processed |
| `prev` | New head after reversing the group |
| `next` | Temporarily stores the next node |

After reversing:

```text
Before:

head
 ↓
1 → 2 → 3 → 4


After:

prev
 ↓
3 → 2 → 1 → 4
        ↑
       head
             ↑
            curr
```

The important observation is:

```text
head = 1
prev = 3
curr = 4
```

`head` does **not** change automatically. It still points to `1`, but `1` has now become the tail.

That's why:

```java
head.next = reverseKGroup(curr, k);
```

connects the tail of the current group to the next group.

---

## 🎯 Key Takeaway

Remember the recursive pattern:

```text
1. CHECK
      ↓
2. REVERSE k NODES
      ↓
3. RECURSE ON REMAINING LIST
      ↓
4. CONNECT USING head.next
      ↓
5. RETURN prev
```

The most important idea:

> **After reversing a group, `prev` becomes its new head, while the original `head` becomes its tail. The tail is connected to the result returned by the recursive call.**

---

## 📊 Complexity

### Time Complexity

Each node is processed a constant number of times:

```text
O(n)
```

### Space Complexity

There is one recursive call for each group of `k` nodes:

```text
O(n/k)
```

In the worst case, this can be:

```text
O(n)
```

### Final Complexity

```text
Time  : O(n)
Space : O(n/k)
```

---

## ⭐ If this explanation helped you understand the recursion, please upvote!
