# 🔥 155. Min Stack | Two-Stack Minimum Tracking Trick

🔗 **LeetCode:** https://leetcode.com/problems/min-stack/

## 🧠 Intuition

We need to design a stack that supports:

- `push()` → add an element
- `pop()` → remove the top element
- `top()` → get the top element
- `getMin()` → get the minimum element

The tricky part is:

> `getMin()` should work in `O(1)` time.

Normally, if we use one stack and want to find the minimum, we would have to search through the entire stack.

That would take `O(n)`.

So we use **two stacks**:

```text
stack     → stores all elements
minStack  → keeps track of minimum elements
```

The idea is that `minStack.peek()` always gives us the current minimum.

---

## 💡 Approach

### Stack 1: `stack`

This is the normal stack.

It stores every value that we push.

Example:

```text
push(5)
push(3)
push(7)
```

Then:

```text
stack:

7 ← top
3
5
```

---

### Stack 2: `minStack`

This stack stores the minimum values.

Whenever a new value is smaller than or equal to the current minimum, we push it into `minStack`.

For example:

```text
push(5)

stack:
5

minStack:
5
```

Then:

```text
push(3)
```

Since:

```text
3 <= 5
```

we push `3` into both stacks.

```text
stack:

3 ← top
5


minStack:

3 ← top
5
```

Therefore:

```java
minStack.peek()
```

always gives the current minimum.

---

# 🚶 Step 1: Push

Our code:

```java
public void push(int value) {
    stack.push(value);

    if(minStack.isEmpty() || value <= minStack.peek()){
        minStack.push(value);
    }
}
```

First, we always push the value into the normal stack:

```java
stack.push(value);
```

Then we check:

```java
if(minStack.isEmpty() || value <= minStack.peek())
```

There are two cases.

### Case 1: `minStack` is empty

```java
minStack.isEmpty()
```

If it is empty, the new value is automatically the minimum.

So we push it.

---

### Case 2: New value is smaller/equal to current minimum

```java
value <= minStack.peek()
```

Suppose:

```text
minStack.peek() = 5
value = 3
```

Then:

```text
3 <= 5
```

So `3` becomes the new minimum.

Push it into `minStack`.

---

# 🗑️ Step 2: Pop

Our code:

```java
public void pop() {
    int poppedValue = stack.pop();

    if(poppedValue == minStack.peek()){
        minStack.pop();
    }
}
```

First, remove the top element from the normal stack:

```java
int poppedValue = stack.pop();
```

Now we check:

```java
if(poppedValue == minStack.peek())
```

Why?

Because if the popped value was the current minimum, we also need to remove it from `minStack`.

For example:

```text
stack:

7
3 ← minimum
5


minStack:

3 ← minimum
5
```

If we pop `3`:

```text
poppedValue = 3
```

And:

```text
minStack.peek() = 3
```

So:

```text
3 == 3
```

Therefore we pop from `minStack` too.

Now:

```text
minStack:

5 ← minimum
```

The new minimum is correctly `5`.

---

# 👀 Step 3: Top

```java
public int top() {
    return stack.peek();
}
```

`stack.peek()` simply returns the top element without removing it.

---

# 🔎 Step 4: Get Minimum

```java
public int getMin() {
    return minStack.peek();
}
```

This is the main trick.

Because `minStack` always keeps track of the minimum:

```java
minStack.peek()
```

directly gives us the minimum element.

Therefore:

```text
getMin() → O(1)
```

---

# 🧪 Dry Run

Let's perform:

```text
push(5)
push(3)
push(7)
push(2)
getMin()
pop()
getMin()
```

---

### `push(5)`

```text
stack:

5


minStack:

5
```

Minimum = `5`

---

### `push(3)`

`3 <= 5`, so add `3` to `minStack`.

```text
stack:

3
5


minStack:

3
5
```

Minimum = `3`

---

### `push(7)`

`7` is not smaller than `3`.

So only the normal stack gets `7`.

```text
stack:

7
3
5


minStack:

3
5
```

Minimum = `3`

---

### `push(2)`

`2 <= 3`, so push `2` into both stacks.

```text
stack:

2
7
3
5


minStack:

2
3
5
```

Minimum = `2`

---

### `getMin()`

```java
minStack.peek()
```

returns:

```text
2
```

---

### `pop()`

We remove `2` from `stack`.

```text
poppedValue = 2
```

Current minimum is also:

```text
minStack.peek() = 2
```

Therefore, remove `2` from `minStack`.

Now:

```text
stack:

7
3
5


minStack:

3
5
```

New minimum = `3`.

---

### `getMin()`

```java
minStack.peek()
```

returns:

```text
3
```

---

# 🔥 Why Do We Use `<=` Instead of `<`?

This is an important detail.

We use:

```java
value <= minStack.peek()
```

instead of:

```java
value < minStack.peek()
```

because duplicate minimum values need to be tracked.

Example:

```text
push(2)
push(2)
push(3)
```

After pushing:

```text
stack:

3
2
2


minStack:

2
2
```

There are two `2`s in `minStack`.

Now if we pop one `2`:

```text
stack:

3
2
```

One `2` still remains.

Therefore:

```text
getMin() = 2
```

This works because we used:

```java
value <= minStack.peek()
```

If we only used `<`, duplicate minimum values would not both be stored.

---

# 💻 Java Solution

```java
class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);

        if(minStack.isEmpty() || value <= minStack.peek()){
            minStack.push(value);
        }
    }

    public void pop() {
        int poppedValue = stack.pop();

        if(poppedValue == minStack.peek()){
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```

---

# 🔍 Understanding the Two Stacks

Think of them like this:

```text
                STACK
                  ↓
        stores every element


              MIN STACK
                  ↓
       stores minimum history
```

Example:

```text
Operations:

push(5)
push(3)
push(7)
push(2)
```

Normal stack:

```text
7
3
2
5
```

Actually, with `2` on top:

```text
2 ← top
7
3
5
```

Minimum stack:

```text
2 ← top/minimum
3
5
```

Notice:

```text
7
```

doesn't need to be stored in `minStack`.

Why?

Because `7` was never the minimum.

---

# 🧠 The Main Concept

`minStack` is basically keeping a **history of minimum values**.

For:

```text
5 → minimum = 5
3 → minimum = 3
7 → minimum = 3
2 → minimum = 2
```

The minimum history is:

```text
5
3
2
```

So:

```text
minStack.peek()
```

always tells us the current minimum.

---

# 🎯 Key Takeaway

The entire trick is:

```text
Normal Stack
     ↓
stores everything


Min Stack
     ↓
stores only minimum values
     ↓
peek() = current minimum
```

Remember:

```text
push:
    Always push into stack
    Push into minStack if value <= current minimum

pop:
    Pop from stack
    If popped value == current minimum
        Pop from minStack

top:
    stack.peek()

getMin:
    minStack.peek()
```

The key idea is:

> **Use a second stack to remember the minimum values so that `getMin()` becomes `O(1)`.**

---

## ⏱️ Complexity

| Operation | Time | Space |
|-----------|------|-------|
| `push()` | `O(1)` | `O(n)` |
| `pop()` | `O(1)` | `O(n)` |
| `top()` | `O(1)` | `O(n)` |
| `getMin()` | `O(1)` | `O(n)` |

Overall:

```text
Time per operation → O(1)
Space → O(n)
```

---

## 🚀 Pattern Recognition

Whenever you see:

> "Design a stack that supports getting the minimum/maximum in `O(1)`"

Think:

```text
🔥 Stack + Auxiliary Stack
```

For minimum:

```text
stack + minStack
```

For maximum:

```text
stack + maxStack
```

The auxiliary stack keeps track of the values needed to answer the extra query in `O(1)`.

---

## ⭐ Final Takeaway

```text
Min Stack
   ↓
Two Stacks
   ↓
stack     → all elements
minStack  → minimum history
   ↓
minStack.peek() → current minimum
```

> ⭐ If this helped you understand the Two-Stack Min Stack trick, please consider giving it an upvote! 🚀
