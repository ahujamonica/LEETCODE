# 🚀 2. Add Two Numbers

> 📂 Topic: Linked List  
> 🎯 Pattern: Linked List + Carry  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(max(n, m)) | 💾 Space: O(max(n, m))

---

## 🔗 Problem Link

[LeetCode 2 — Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)

---

## 🧠 Intuition

We are given two numbers represented as **reversed linked lists**.

For example:

```text
l1 = 2 → 4 → 3
l2 = 5 → 6 → 4
```

These represent:

```text
342 + 465 = 807
```

So the result should be:

```text
7 → 0 → 8
```

Since the digits are already stored in reverse order, we can add them directly from left to right.

---

## 🚀 Approach

At every position, calculate:

```text
sum = digit from l1 + digit from l2 + carry
```

Then:

```text
current digit = sum % 10
carry = sum / 10
```

We create a new node for the current digit and move to the next nodes.

A **dummy node** is used to make building the result list easier.

---

## 💡 Example

```text
l1 = 2 → 4 → 3
l2 = 5 → 6 → 4
```

### Step 1

```text
2 + 5 + 0 = 7
```

```text
digit = 7
carry = 0
```

Result:

```text
7
```

### Step 2

```text
4 + 6 + 0 = 10
```

```text
digit = 0
carry = 1
```

Result:

```text
7 → 0
```

### Step 3

```text
3 + 4 + 1 = 8
```

```text
digit = 8
carry = 0
```

Final result:

```text
7 → 0 → 8
```

---

## 🔑 Why Do We Need `carry`?

Consider:

```text
8 + 7 = 15
```

We can only store one digit in the current node.

So:

```text
15
```

becomes:

```text
digit = 5
carry = 1
```

The `1` is then added to the next digit.

---

## 🔑 Why Is `ptr` a `ListNode`?

```java
ListNode ptr = result;
```

`ptr` points to the current node where we are building the result.

Since it needs to access:

```java
ptr.next
```

it must be a `ListNode`.

An `int` can store only a number and cannot have a `next` pointer.

---

## 🔑 Why Start With `sum = carry`?

Instead of:

```java
int sum = 0 + carry;
```

we can simply write:

```java
int sum = carry;
```

Then add the available digits:

```java
sum += l1.val;
sum += l2.val;
```

So effectively:

```text
sum = carry + l1 digit + l2 digit
```

---

## 🔢 `% 10` and `/ 10`

Suppose:

```text
sum = 17
```

The digit we store is:

```java
sum % 10
```

which gives:

```text
7
```

The carry is:

```java
sum / 10
```

which gives:

```text
1
```

Therefore:

```text
17 → digit = 7, carry = 1
```

---

## 💻 Java Solution

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // Dummy node to build the result list
        ListNode result = new ListNode(0);

        // Pointer used to build the result
        ListNode ptr = result;

        // Carry from the previous digit
        int carry = 0;

        // Continue while either list has nodes
        while (l1 != null || l2 != null) {

            // Start with the carry
            int sum = carry;

            // Add digit from l1 if available
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            // Add digit from l2 if available
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // Calculate carry for the next position
            carry = sum / 10;

            // Keep only the current digit
            sum = sum % 10;

            // Add current digit to the result
            ptr.next = new ListNode(sum);

            // Move result pointer forward
            ptr = ptr.next;
        }

        // If a carry is still left, add it
        if (carry == 1) {
            ptr.next = new ListNode(1);
        }

        // Skip the dummy node
        return result.next;
    }
}
```

---

## 📝 Dry Run

Input:

```text
l1 = 2 → 4 → 3
l2 = 5 → 6 → 4
```

Start:

```text
result → 0
          ↑
         ptr
```

### Iteration 1

```text
2 + 5 + 0 = 7
```

```text
result → 0 → 7
              ↑
             ptr
```

### Iteration 2

```text
4 + 6 + 0 = 10
```

```text
digit = 0
carry = 1
```

```text
result → 0 → 7 → 0
                  ↑
                 ptr
```

### Iteration 3

```text
3 + 4 + 1 = 8
```

```text
digit = 8
carry = 0
```

Final:

```text
result → 0 → 7 → 0 → 8
```

We skip the dummy node:

```java
return result.next;
```

Answer:

```text
7 → 0 → 8
```

---

## 🎯 Key Takeaway

At every node:

```text
       carry
         ↓
l1 digit + l2 digit
         ↓
       SUM
      /   \
     ↓     ↓
  digit   carry
```

Remember:

```text
Add → Store → Carry → Move
```

Or more specifically:

```java
sum = carry + l1.val + l2.val;

digit = sum % 10;
carry = sum / 10;
```

> **Since the numbers are stored in reverse order, add corresponding digits directly while carrying the overflow to the next position.**

---

## 📊 Complexity

Let:

```text
n = length of l1
m = length of l2
```

We process each node at most once.

### Time Complexity

```text
O(max(n, m))
```

### Space Complexity

The result list contains up to:

```text
max(n, m) + 1
```

nodes.

Therefore, including the output:

```text
O(max(n, m))
```

Auxiliary space excluding the output is:

```text
O(1)
```

### Final

```text
Time  = O(max(n, m))
Space = O(max(n, m))   // including output
```

---

## 🧠 Pattern to Remember

```text
Create dummy node
       ↓
Set carry = 0
       ↓
Add l1 digit + l2 digit + carry
       ↓
Store sum % 10
       ↓
Update carry = sum / 10
       ↓
Move pointers
       ↓
Repeat
       ↓
Add final carry if needed
       ↓
Return dummy.next
```

**Key formula:**

```text
digit = sum % 10
carry = sum / 10
```

🚀 **Add → Carry → Create Node → Move Forward**
