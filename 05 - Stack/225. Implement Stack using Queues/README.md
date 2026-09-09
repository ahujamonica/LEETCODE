# 🔥 225. Implement Stack using Queues — One Queue Trick

🔗 **LeetCode:** https://leetcode.com/problems/implement-stack-using-queues/

## 🧩 Problem

Implement a **Stack** using only a **Queue**.

A Stack follows:

```text
LIFO → Last In, First Out
```

A Queue follows:

```text
FIFO → First In, First Out
```

The challenge is to make the Queue behave like a Stack.

---

## 🧠 Pattern

This is a **Data Structure Simulation** problem.

The main idea is:

> Instead of making `pop()` expensive, make `push()` expensive.

After every `push()`:

```text
Queue Front = Stack Top
```

Then:

```text
pop() → remove front
top() → see front
```

Both become `O(1)`.

---

## 💡 Intuition

Suppose we push:

```text
1
2
3
```

A normal Queue would look like:

```text
[1, 2, 3]
 ↑
front
```

But a Stack needs:

```text
3
2
1
```

So after adding a new element, we rotate all the **old elements** behind it.

This makes the newest element reach the front.

---

## 🔍 Approach

For every `push(x)`:

1. Store the current Queue size.
2. Add `x`.
3. Rotate all the old elements to the back.

Example:

```text
Before push(3):

[2, 1]

size = 2
```

Add `3`:

```text
[2, 1, 3]
```

Rotate twice:

```text
[1, 3, 2]
[3, 2, 1]
```

Now:

```text
[3, 2, 1]
 ↑
front
```

So the Queue front behaves like the Stack top.

---

## 💻 Java Solution

```java
class MyStack {

    private Queue<Integer> q;

    public MyStack() {
        q = new LinkedList<>();
    }

    public void push(int x) {

        int size = q.size();

        q.add(x);

        for (int i = 0; i < size; i++) {
            q.add(q.poll());
        }
    }

    public int pop() {
        return q.poll();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
```

---

## 🧪 Dry Run

Operations:

```text
push(1)
push(2)
push(3)
pop()
top()
```

### push(1)

```text
[1]
 ↑
top
```

### push(2)

```text
Add 2:

[1, 2]

Rotate 1:

[2, 1]
 ↑
top
```

### push(3)

```text
Add 3:

[2, 1, 3]

Rotate 2:

[1, 3, 2]

Rotate 1:

[3, 2, 1]
 ↑
top
```

### pop()

```text
3
```

Queue becomes:

```text
[2, 1]
```

### top()

```text
2
```

---

## ⚠️ Important Part

```java
int size = q.size();

q.add(x);

for (int i = 0; i < size; i++) {
    q.add(q.poll());
}
```

We **must save the old size** before adding `x`.

Don't do:

```java
q.add(x);

for (int i = 0; i < q.size(); i++) {
    q.add(q.poll());
}
```

We only want to rotate the elements that existed **before** `x`.

---

## 🔄 Why `poll()` and `peek()`?

### `poll()`

```java
q.poll();
```

Removes and returns the front element.

```text
[3, 2, 1]
 ↑

poll() → 3

[2, 1]
```

So it is perfect for:

```java
pop()
```

---

### `peek()`

```java
q.peek();
```

Returns the front element **without removing it**.

So it is perfect for:

```java
top()
```

---

## ⏱️ Complexity

| Operation | Time |
|---|---:|
| `push()` | O(n) |
| `pop()` | O(1) |
| `top()` | O(1) |
| `empty()` | O(1) |

```text
Space → O(n)
```

---

## 🧠 Key Takeaway

The whole trick is:

```text
New element
     ↓
Add to Queue
     ↓
Rotate old elements
     ↓
New element reaches front
     ↓
Front = Stack Top
```

So:

```text
push()  → O(n)
pop()   → O(1)
top()   → O(1)
empty() → O(1)
```

> ⭐ If this helped you understand the one-Queue approach, an upvote would be appreciated! ❤️
