# 🚀 21. Merge Two Sorted Lists

> 📂 Topic: Linked List  
> 🎯 Pattern: Two Pointers + Dummy Node  
> ⭐ Difficulty: Easy  
> ⏱️ Time: O(n + m) | 💾 Space: O(1)

---

# 🔗 Problem Link

[LeetCode 21 — Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)

---

# 🧠 One-Line Idea

> Compare the current nodes of both sorted linked lists, attach the smaller node to the result, and move the corresponding pointer forward.

---

# 💡 Intuition

We are given two sorted linked lists.

For example:

```text
list1:

1 → 3 → 5 → null

list2:

2 → 4 → 6 → null
```

We need to merge them into:

```text
1 → 2 → 3 → 4 → 5 → 6 → null
```

Since both lists are already sorted, we don't need to sort anything again.

We simply compare the current nodes of both lists and take the smaller one.

---

# 🚀 Approach

We use:

- `list1` → pointer for the first list
- `list2` → pointer for the second list
- `resultant` → pointer used to build the merged list
- `headNode` → keeps the starting point of the result

---

# 1️⃣ Create a Dummy Node

```java
ListNode resultant = new ListNode(Integer.MIN_VALUE);
```

This creates a temporary node:

```text
[DUMMY] → null
```

The value of the dummy node doesn't matter because it will not be part of the final answer.

The dummy node makes it easier to build the result without having to separately handle the first node.

---

# 2️⃣ Keep the Dummy Node's Reference

```java
ListNode headNode = resultant;
```

Now:

```text
headNode
   ↓
[DUMMY]
   ↑
resultant
```

`headNode` will stay at the beginning.

`resultant` will move forward as we build the merged list.

Think of:

```text
headNode   → remembers where the result starts
resultant  → keeps moving to the end of the result
```

---

# 3️⃣ Compare Both Lists

We continue while both lists contain nodes:

```java
while (list1 != null && list2 != null)
```

Suppose:

```text
list1 → 1 → 3 → 5
list2 → 2 → 4 → 6
```

Compare:

```text
1 vs 2
```

Since:

```text
1 < 2
```

we take `1`.

---

# 4️⃣ Attach the Smaller Node

```java
resultant.next = list1;
```

This connects the current result node to `list1`.

Initially:

```text
[DUMMY] → null
    ↑
resultant
```

After attaching:

```text
[DUMMY] → 1 → 3 → 5
```

Then move `list1`:

```java
list1 = list1.next;
```

Now:

```text
list1 → 3 → 5
```

---

# 5️⃣ Move the Result Pointer

```java
resultant = resultant.next;
```

This moves `resultant` to the node we just added.

Before:

```text
[DUMMY] → 1
    ↑
resultant
```

After:

```text
[DUMMY] → 1
            ↑
        resultant
```

So `resultant` always points to the **last node of the merged list**.

---

# 🔄 Continue Comparing

Now:

```text
list1 → 3 → 5
list2 → 2 → 4 → 6
```

Compare:

```text
3 vs 2
```

Take `2`.

Result:

```text
DUMMY → 1 → 2
             ↑
         resultant
```

Then:

```text
list2 → 4 → 6
```

Continue:

```text
3 vs 4 → take 3

5 vs 4 → take 4

5 vs 6 → take 5
```

Result:

```text
DUMMY → 1 → 2 → 3 → 4 → 5
```

---

# 🔗 6️⃣ Attach the Remaining List

Eventually, one list becomes `null`.

For example:

```text
list1 = null

list2 → 6 → 9 → 12 → null
```

The main loop stops.

We can simply do:

```java
resultant.next = list2;
```

This connects the entire remaining list:

```text
1 → 2 → 3 → 4 → 5 → 6 → 9 → 12 → null
```

No additional loop is required because the remaining nodes are already connected.

---

# 🧠 Why Does `resultant.next = list2` Add All Remaining Nodes?

Suppose:

```text
resultant → 5

list2 → 6 → 9 → 12 → null
```

When we do:

```java
resultant.next = list2;
```

we are essentially doing:

```text
5.next → 6
```

But `6` already points to:

```text
6 → 9 → 12 → null
```

Therefore the complete result becomes:

```text
5 → 6 → 9 → 12 → null
```

We don't need to manually add `9` and `12`.

---

# 🔑 Why Do We Need `resultant = resultant.next`?

These two lines do different things:

```java
resultant.next = list1;
```

and:

```java
resultant = resultant.next;
```

### `resultant.next = list1`

Means:

> Connect the current result node to `list1`.

### `resultant = resultant.next`

Means:

> Move `resultant` to the newly added node.

Think of `resultant` as the **tail pointer**:

```text
DUMMY → 1 → 2 → 3
                  ↑
              resultant
```

Every time we add a node, we move the tail forward.

---

# 🎯 Why Return `headNode.next`?

At the end:

```text
headNode
   ↓
DUMMY → 1 → 2 → 3 → 4 → 5 → 6
```

We don't want the dummy node.

We want the actual first node:

```text
1
```

Therefore:

```java
return headNode.next;
```

returns:

```text
1 → 2 → 3 → 4 → 5 → 6 → null
```

---

# 💻 Java Solution

```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Create a dummy node to simplify building the result
        ListNode resultant = new ListNode(Integer.MIN_VALUE);

        // Keep a reference to the dummy node
        ListNode headNode = resultant;

        // Continue while both lists have nodes
        while (list1 != null && list2 != null) {

            // Take the smaller node
            if (list1.val <= list2.val) {

                // Attach list1's current node
                resultant.next = list1;

                // Move list1 forward
                list1 = list1.next;

            } else {

                // Attach list2's current node
                resultant.next = list2;

                // Move list2 forward
                list2 = list2.next;
            }

            // Move resultant to the newly added node
            resultant = resultant.next;
        }

        // Attach whichever list still has remaining nodes
        if (list1 == null) {
            resultant.next = list2;
        } else {
            resultant.next = list1;
        }

        // Skip the dummy node
        return headNode.next;
    }
}
```

---

# 📝 Dry Run

Input:

```text
list1: 1 → 3 → 5
list2: 2 → 4 → 6
```

Initially:

```text
DUMMY
  ↑
resultant
```

### Comparison 1

```text
1 vs 2
```

Take `1`:

```text
DUMMY → 1
         ↑
     resultant
```

### Comparison 2

```text
3 vs 2
```

Take `2`:

```text
DUMMY → 1 → 2
             ↑
         resultant
```

### Comparison 3

```text
3 vs 4
```

Take `3`.

```text
DUMMY → 1 → 2 → 3
```

### Comparison 4

```text
5 vs 4
```

Take `4`.

```text
DUMMY → 1 → 2 → 3 → 4
```

### Comparison 5

```text
5 vs 6
```

Take `5`.

```text
DUMMY → 1 → 2 → 3 → 4 → 5
```

Now:

```text
list1 = null
list2 → 6
```

Attach the remaining list:

```text
DUMMY → 1 → 2 → 3 → 4 → 5 → 6
```

Finally:

```java
return headNode.next;
```

returns:

```text
1 → 2 → 3 → 4 → 5 → 6
```

---

# 📊 Complexity

Let:

```text
n = length of list1
m = length of list2
```

## Time Complexity

Every node from both lists is processed at most once:

```text
O(n + m)
```

## Space Complexity

We don't create new nodes for the merged list.

We only use a few pointers:

```text
list1
list2
resultant
headNode
```

Therefore:

```text
O(1)
```

### Final Complexity

```text
Time  = O(n + m)
Space = O(1)
```

---

# 🧠 Pattern to Remember

```text
Create DUMMY
     ↓
Keep HEAD reference
     ↓
Compare list1 & list2
     ↓
Take smaller node
     ↓
Attach to resultant
     ↓
Move selected list
     ↓
Move resultant
     ↓
Repeat
     ↓
Attach remaining list
     ↓
Return dummy.next
```

The core pattern is:

```java
resultant.next = list1;
list1 = list1.next;
resultant = resultant.next;
```

or:

```java
resultant.next = list2;
list2 = list2.next;
resultant = resultant.next;
```

> **Use a dummy node to simplify construction, compare the current nodes of both sorted lists, attach the smaller node, and move the tail pointer forward.**

Happy Coding! 🚀✨
