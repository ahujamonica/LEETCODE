
# ⚡ Don't Overcomplicate the Solution | Stack Trick

🔗 **LeetCode:** https://leetcode.com/problems/longest-valid-parentheses/

## 🧠 Intuition

We need to find the **length of the longest valid parentheses substring**.

The trick is to use a **Stack of indices**.

Instead of storing the brackets, we store their **indices**.

- `'('` → push its index.
- `')'` → pop the previous index.
- If the stack becomes empty, the current `')'` is invalid, so we store its index as a new boundary.
- Otherwise, we calculate the valid length using:

```java
i - stack.peek()
```

We initially push `-1` because it acts as a boundary before the string starts.

---

## 💡 Approach

For every character:

### If it is `'('`

Store its index:

```java
stack.push(i);
```

This `(` may be matched later.

### If it is `')'`

First remove the matching `(`:

```java
stack.pop();
```

If the stack becomes empty, there is no matching `(`, so this `)` becomes a new boundary:

```java
stack.push(i);
```

Otherwise, we have a valid substring.

Its length is:

```java
i - stack.peek()
```

The stack top represents the index just before the current valid substring.

---

## 🧪 Example

```text
s = ")()())"

index:  0 1 2 3 4 5
char:   ) ( ) ( ) )
```

Execution:

```text
i = 0 → ')' → invalid → push 0

i = 1 → '(' → push 1

i = 2 → ')' → pop 1
         length = 2 - 0 = 2

i = 3 → '(' → push 3

i = 4 → ')' → pop 3
         length = 4 - 0 = 4

i = 5 → ')' → invalid → push 5
```

The longest valid substring is:

```text
()()
```

So the answer is:

```text
4
```

---

## 💻 Java Solution

```java
class Solution {
    public int longestValidParentheses(String s) {

        // Stack stores indices, not brackets.
        Stack<Integer> stack = new Stack<>();

        // -1 acts as a boundary before the string starts.
        stack.push(-1);

        int maxLength = 0;

        for(int i = 0; i < s.length(); i++) {

            // Opening bracket → store its index
            if(s.charAt(i) == '(') {
                stack.push(i);
            }

            // Closing bracket
            else {

                // Remove the matching '('
                stack.pop();

                // No matching '(' exists
                if(stack.isEmpty()) {

                    // Current ')' becomes a new boundary
                    stack.push(i);
                }

                // We have a valid parentheses substring
                else {

                    // Calculate its length
                    int length = i - stack.peek();

                    // Keep the maximum length
                    maxLength = Math.max(length, maxLength);
                }
            }
        }

        return maxLength;
    }
}
```

## 🔑 Key Trick

The most important line is:

```java
int length = i - stack.peek();
```

Think of it as:

```text
Current index
      -
Boundary index
      =
Valid substring length
```

And remember:

```text
'(' → PUSH

')' → POP

Stack empty → new invalid boundary

Stack not empty → calculate valid length
```

## ⏱️ Complexity

- **Time:** `O(n)`
- **Space:** `O(n)`

## 🎯 Key Takeaway

> **Store indices in the stack, use invalid `)` as boundaries, and calculate valid length using `i - stack.peek()`.**

⭐ If this helped, please consider giving this solution an upvote!
