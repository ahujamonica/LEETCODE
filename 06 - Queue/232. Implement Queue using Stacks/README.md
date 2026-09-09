# 🔥 232. Implement Queue using Stacks — Two Stack Approach

🔗 **LeetCode:** https://leetcode.com/problems/implement-queue-using-stacks/

---

## 🧩 Problem

Implement a **Queue** using only **Stacks**.

A Queue follows:

```text
FIFO → First In, First Out
```

A Stack follows:

```text
LIFO → Last In, First Out
```

We need to use two Stacks to make them behave like a Queue.

---

## 🧠 Intuition

We use two stacks:

```text
s1 → Used for pushing elements
s2 → Used for popping and peeking elements
```

The main trick is:

> **When `s2` is empty, move all elements from `s1` to `s2`.**

This reverses their order.

For example:

```text
s1:

[1, 2, 3]
```

Move everything to `s2`:

```text
s1          s2

[1, 2, 3]   [3, 2, 1]
                ↑
               top
```

Now `1` is at the top of `s2`.

That is exactly what we need because `1` was the **first element inserted**.

---

# 🔍 Approach

We maintain two stacks:

```java
Stack<Integer> s1;
Stack<Integer> s2;
```

### `push(x)`

Simply push into `s1`:

```java
s1.push(x);
```

### `pop()`

If `s2` is empty:

```text
Move everything from s1 → s2
```

Then:

```java
return s2.pop();
```

### `peek()`

Same logic as `pop()`, but instead of removing:

```java
return s2.peek();
```

### `empty()`

The Queue is empty only when **both stacks are empty**:

```java
return s1.isEmpty() && s2.isEmpty();
```

---

# 💻 Java Solution

```java
class MyQueue {

    // Stack 1 is used to store newly pushed elements
    Stack<Integer> s1;

    // Stack 2 is used to remove and view elements
    // in the correct Queue order
    Stack<Integer> s2;

    // Constructor
    public MyQueue() {

        // Create both empty stacks
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    // Add an element to the back of the Queue
    public void push(int x) {

        // Simply push the new element into s1
        s1.push(x);
    }

    // Remove and return the element from the front of the Queue
    public int pop() {

        // If s2 is empty, we need to transfer
        // all elements from s1 to s2
        if (s2.isEmpty()) {

            // Move every element from s1 to s2
            // This reverses their order
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        // The top of s2 is now the front of the Queue
        return s2.pop();
    }

    // Return the front element without removing it
    public int peek() {

        // If s2 is empty, transfer elements from s1
        if (s2.isEmpty()) {

            // Reverse the order by moving
            // all elements from s1 to s2
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        // The top of s2 represents the front of the Queue
        return s2.peek();
    }

    // Check whether the Queue is empty
    public boolean empty() {

        // Queue is empty only if BOTH stacks are empty
        return s1.isEmpty() && s2.isEmpty();
    }
}
```

---

# 🧪 Dry Run

Consider:

```text
push(1)
push(2)
push(3)
pop()
pop()
push(4)
pop()
```

---

## 1️⃣ `push(1)`

```text
s1 = [1]
s2 = []
```

---

## 2️⃣ `push(2)`

```text
s1 = [1, 2]
s2 = []
```

---

## 3️⃣ `push(3)`

```text
s1 = [1, 2, 3]
s2 = []
```

Notice:

```text
s1 top → 3
```

But we need to remove:

```text
1
```

So we cannot directly pop from `s1`.

---

# 🔄 First `pop()`

`s2` is empty:

```java
if (s2.isEmpty())
```

So transfer everything.

### Move `3`

```text
s1 = [1, 2]
s2 = [3]
```

### Move `2`

```text
s1 = [1]
s2 = [3, 2]
```

### Move `1`

```text
s1 = []
s2 = [3, 2, 1]
```

Now:

```text
s2 top → 1
```

So:

```java
s2.pop()
```

returns:

```text
1
```

---

# 🔥 Second `pop()`

Now:

```text
s1 = []
s2 = [3, 2]
```

`s2` is **not empty**.

So we don't transfer anything.

Simply:

```java
s2.pop()
```

returns:

```text
2
```

Now:

```text
s2 = [3]
```

---

# ➕ `push(4)`

We always push new elements into `s1`.

```text
s1 = [4]
s2 = [3]
```

Notice something important:

We **do not transfer `4` into `s2`**.

That's okay.

The existing front elements are already safely stored in `s2`.

---

# 🔥 Next `pop()`

`s2` is not empty:

```text
s2 = [3]
```

So simply:

```java
s2.pop()
```

returns:

```text
3
```

Now:

```text
s1 = [4]
s2 = []
```

If we call `pop()` again, `s2` is empty.

So we transfer:

```text
s1 → s2
```

```text
s1 = []
s2 = [4]
```

Then:

```text
pop() → 4
```

Everything remains in correct Queue order.

---

# ⭐ The Most Important Trick

Remember this:

```java
if (s2.isEmpty()) {

    while (!s1.isEmpty()) {
        s2.push(s1.pop());
    }
}
```

We **only transfer when `s2` is empty**.

Why?

Because `s2` already contains the elements that should be removed next.

For example:

```text
s1 = [4, 5]
s2 = [3]
```

The next element should be:

```text
3
```

So we must NOT move `4` and `5` into `s2` yet.

After removing `3`:

```text
s1 = [4, 5]
s2 = []
```

Now we can transfer:

```text
s1 → s2
```

Result:

```text
s2 = [5, 4]
 ↑
top
```

So:

```text
pop() → 4
```

Correct Queue behaviour.

---

# 🧠 Why Does Moving Between Stacks Work?

Suppose:

```text
s1 = [1, 2, 3]
```

The top is:

```text
3
```

When we move elements to `s2`:

```text
3 → s2
2 → s2
1 → s2
```

We get:

```text
s2 = [3, 2, 1]
          ↑
         top
```

So the order gets reversed:

```text
1 → 2 → 3

becomes

3 → 2 → 1
```

Therefore:

```text
s2 top = 1
```

which is exactly the **front of the Queue**.

---

# 🔄 Visual Representation

Think of it like this:

```text
             PUSH
               ↓
              s1
        [1, 2, 3]
               ↓
        when s2 is empty
               ↓
        reverse the order
               ↓
              s2
        [3, 2, 1]
             ↑
           FRONT
```

So:

```text
s1 → Incoming elements

s2 → Outgoing elements
```

---

# ⚠️ Common Mistakes

### ❌ Mistake 1: Always transferring `s1` to `s2`

Don't do this on every operation.

Only transfer when:

```java
s2.isEmpty()
```

---

### ❌ Mistake 2: Pushing directly into `s2`

We always push new elements into:

```java
s1
```

```java
public void push(int x) {
    s1.push(x);
}
```

---

### ❌ Mistake 3: Checking only `s1` in `empty()`

Wrong:

```java
return s1.isEmpty();
```

Because `s2` may still contain elements.

Correct:

```java
return s1.isEmpty() && s2.isEmpty();
```

---

### ❌ Mistake 4: Using `pop()` for `peek()`

`pop()` removes the element.

For `peek()`, we only want to see it.

So:

```java
s2.peek();
```

---

# ⏱️ Complexity

At first glance, it looks like:

```text
push() → O(1)
pop()  → O(n)
peek() → O(n)
```

But that's not the complete picture.

An element is moved from `s1` to `s2` **at most once** before being removed.

Therefore, over many operations, the total work is:

```text
Amortized Time → O(1)
```

### Final Complexity

| Operation | Amortized Time |
|---|---:|
| `push()` | O(1) |
| `pop()` | O(1) |
| `peek()` | O(1) |
| `empty()` | O(1) |

Worst case for a single `pop()` or `peek()`:

```text
O(n)
```

because we may need to transfer all elements.

### Space

```text
O(n)
```

because we store all elements across the two stacks.

---

# 🧩 Pattern Recognition

This problem is a great example of using **two stacks to reverse order**.

Whenever you see:

```text
Need FIFO
but only have LIFO
```

think:

```text
Stack 1
   ↓
Reverse order
   ↓
Stack 2
   ↓
FIFO behaviour
```

The two reversals effectively give us the order we need.

---

# 📝 What I Learned

The main idea is:

```text
s1 → Store incoming elements
s2 → Serve outgoing elements
```

When `s2` is empty:

```text
Move everything:

s1 → s2
```

This reverses the order.

Therefore:

```text
s2 top = Queue front
```

And then:

```text
pop()  → s2.pop()
peek() → s2.peek()
```

---

# 🎯 Final Takeaway

Remember the simplest version:

```text
PUSH:
    put into s1

POP / PEEK:
    if s2 is empty
        move s1 → s2

    use s2
```

The most important condition:

```java
if (s2.isEmpty())
```

Because we only move elements when the current outgoing stack has been completely used.

> ⭐ If this helped you understand the two-stack Queue approach, an upvote would be appreciated! ❤️
