# 🚀 61. Rotate List | 🔄 Smart Pointer Manipulation

> 📂 Topic: Linked List  
> 🎯 Pattern: Pointer Manipulation  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

## 🔗 Problem

[LeetCode 61 — Rotate List](https://leetcode.com/problems/rotate-list/)

---

## 🧠 Intuition

We need to rotate the linked list to the right by `k` positions.

For example:

```text
Input:
1 → 2 → 3 → 4 → 5
k = 2

Output:
4 → 5 → 1 → 2 → 3
```

Instead of moving nodes one by one, we find the point where the list needs to be split and reconnect the two parts.

The key observation is:

```text
new tail position = length - k
new head = newTail.next
```

---

## 🚀 Approach

### 1️⃣ Find Length and Tail

Traverse the list once to find the length and last node.

```java
int length = 1;
ListNode tail = head;

while (tail.next != null) {
    tail = tail.next;
    length++;
}
```

For:

```text
1 → 2 → 3 → 4 → 5
```

we get:

```text
length = 5
tail = 5
```

---

### 2️⃣ Reduce Unnecessary Rotations

Rotating by the length of the list brings it back to the original position.

Therefore:

```java
k = k % length;
```

For example:

```text
k = 7
length = 5

7 % 5 = 2
```

If `k == 0`, no rotation is required.

---

### 3️⃣ Find the New Tail

The new tail is at position:

```text
length - k
```

For:

```text
1 → 2 → 3 → 4 → 5
k = 2
```

we get:

```text
5 - 2 = 3
```

So node `3` becomes the new tail:

```text
1 → 2 → 3 | 4 → 5
        ↑
     newTail
```

Since `newTail` starts at node `1`, we move:

```text
length - k - 1
```

times.

---

### 4️⃣ Find the New Head

The node after the new tail becomes the new head:

```java
ListNode newHead = newTail.next;
```

So:

```text
1 → 2 → 3 → 4 → 5
        ↑   ↑
     newTail newHead
```

---

### 5️⃣ Reconnect and Break

Connect the old tail to the old head:

```java
tail.next = head;
```

This temporarily creates:

```text
4 → 5 → 1 → 2 → 3 → 4 → ...
```

Then break the list after `newTail`:

```java
newTail.next = null;
```

Final:

```text
4 → 5 → 1 → 2 → 3 → null
```

---

## 💻 Java Solution

```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Find length and tail
        int length = 1;
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Remove unnecessary rotations
        k = k % length;

        if (k == 0) {
            return head;
        }

        // Find the new tail
        ListNode newTail = head;

        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }

        // Node after newTail becomes the new head
        ListNode newHead = newTail.next;

        // Connect old tail to old head
        tail.next = head;

        // Break the list
        newTail.next = null;

        return newHead;
    }
}
```

---

## 🔄 Dry Run

For:

```text
1 → 2 → 3 → 4 → 5
k = 2
```

### Step 1: Find length

```text
length = 5
tail = 5
```

### Step 2: Normalize `k`

```text
k = 2 % 5
k = 2
```

### Step 3: Find new tail

```text
length - k - 1
= 5 - 2 - 1
= 2
```

Starting from node `1`, move twice:

```text
1 → 2 → 3
        ↑
     newTail
```

### Step 4: Find new head

```text
newHead = newTail.next
```

Therefore:

```text
newHead = 4
```

### Step 5: Reconnect

```text
tail.next = head
```

Temporarily:

```text
4 → 5 → 1 → 2 → 3 → 4 → ...
```

Break after node `3`:

```text
newTail.next = null
```

Final:

```text
4 → 5 → 1 → 2 → 3 → null
```

---

## 🎯 Key Takeaway

Remember:

```text
1. Find length + tail
          ↓
2. k = k % length
          ↓
3. Find new tail
          ↓
4. newHead = newTail.next
          ↓
5. tail.next = head
          ↓
6. newTail.next = null
```

The important formula is:

```text
New tail position = length - k
```

Since we start from the first node, the number of pointer movements is:

```text
length - k - 1
```

---

## 📊 Complexity

### Time Complexity

```text
O(n)
```

The list is traversed a constant number of times.

### Space Complexity

```text
O(1)
```

Only a few pointers are used.

---

## 🏆 Final Complexity

```text
Time  : O(n)
Space : O(1)
```

> ⭐ **If this explanation helped you understand the pointer manipulation, please upvote! 🙌**
