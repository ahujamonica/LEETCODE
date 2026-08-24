# 🚀 138. Copy List with Random Pointer | 🧠 O(1) Space Magic

> 📂 Topic: Linked List  
> 🎯 Pattern: Linked List Manipulation  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n) | 💾 Space: O(1)

---

## 🔗 Problem

[LeetCode 138 — Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/)

---

## 🧠 Intuition

We need to create a **deep copy** of a linked list where every node contains:

- `val`
- `next`
- `random`

The `random` pointer can point to **any node in the list or `null`**.

Instead of using a `HashMap` to map original nodes to their copies, we can solve the problem using **O(1) auxiliary space** by temporarily interleaving the copied nodes with the original nodes.

---

## 🚀 Approach

The solution has **3 steps**.

### 1️⃣ Interleave Original and Copied Nodes

For every original node, create its copy and insert it immediately after the original.

Before:

```text
1 → 2 → 3 → null
```

After:

```text
1 → 1' → 2 → 2' → 3 → 3' → null
```

This means:

```text
original.next = copy
```

So the copied node of any original node can be accessed using:

```java
curr.next
```

---

### 2️⃣ Copy the Random Pointers

Suppose:

```text
1.random → 3
```

Because the copied nodes are placed immediately after their originals:

```text
1 → 1' → 2 → 2' → 3 → 3'
```

We know:

```text
3' = 3.next
```

Therefore:

```java
curr.next.random = curr.random.next;
```

This sets the copied node's `random` pointer to the copied version of the original random node.

---

### 3️⃣ Separate the Two Lists

After copying the random pointers, the lists are still interleaved:

```text
1 → 1' → 2 → 2' → 3 → 3'
```

We separate them back into:

```text
Original:
1 → 2 → 3

Copied:
1' → 2' → 3'
```

Finally, return the head of the copied list.

---

## 💻 Java Solution

```java
class Solution {
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // Step 1: Create copied nodes
        // and place them next to their originals
        Node curr = head;

        while (curr != null) {
            Node newNode = new Node(curr.val);

            newNode.next = curr.next;
            curr.next = newNode;

            curr = newNode.next;
        }

        // Step 2: Copy random pointers
        curr = head;

        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }

            curr = curr.next.next;
        }

        // Step 3: Separate original and copied lists
        curr = head;

        Node newHead = head.next;
        Node newCurr = newHead;

        while (curr != null) {

            curr.next = curr.next.next;
            curr = curr.next;

            if (curr != null) {
                newCurr.next = curr.next;
                newCurr = newCurr.next;
            }
        }

        return newHead;
    }
}
```

---

## 🔑 The Main Trick

The key idea is:

```text
Original → Copy → Original → Copy
```

For example:

```text
A → A' → B → B' → C → C'
```

Therefore:

```text
A' = A.next
B' = B.next
C' = C.next
```

If:

```text
A.random → C
```

then:

```text
A'.random → C'
```

and we can find `C'` using:

```java
A.random.next
```

So:

```java
A.next.random = A.random.next;
```

This eliminates the need for a `HashMap`.

---

## 🔄 Example

Suppose:

```text
Original:

1 → 2 → 3

1.random → 3
2.random → 1
3.random → 2
```

### After interleaving:

```text
1 → 1' → 2 → 2' → 3 → 3'
```

### Copy random pointers:

```text
1'.random → 3'
2'.random → 1'
3'.random → 2'
```

### Separate:

```text
Original:
1 → 2 → 3

Copied:
1' → 2' → 3'
```

The copied list is a completely independent deep copy.

---

## 📊 Complexity

### Time Complexity

We make three passes through the list:

```text
O(n)
```

### Auxiliary Space Complexity

No `HashMap` or other data structure is used:

```text
O(1)
```

The newly created copy nodes are part of the required output and are not considered auxiliary space.

---

## 🎯 Key Takeaway

```text
1. Interleave copied nodes
          ↓
2. Copy random pointers
          ↓
3. Separate the lists
```

The entire trick is to temporarily create:

```text
Original → Copy → Original → Copy
```

which lets every original node directly access its corresponding copy.

> ⭐ **If this O(1) space trick helped you understand the problem, please upvote!**
