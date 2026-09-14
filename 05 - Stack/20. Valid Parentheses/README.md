# 🔥 20. Valid Parentheses | Stack Pattern

🔗 **LeetCode:** https://leetcode.com/problems/valid-parentheses/

## 🧠 Intuition

We use a **Stack** to keep track of the closing bracket we expect.

Instead of storing opening brackets:

```text
(
{
[
```

we directly push their corresponding closing brackets:

```text
( → )
{ → }
[ → ]
```

Then whenever we encounter a closing bracket, we check:

```text
Does it match the bracket at the top of the Stack?
```

If not → invalid.

At the end, the Stack must be empty.

---

## 💡 Approach

For every character:

### If it is an opening bracket

Push its expected closing bracket:

```text
'(' → push ')'
'{' → push '}'
'[' → push ']'
```

### If it is a closing bracket

Check two things:

1. Is the Stack empty?
2. Does the current character match `stack.peek()`?

If either condition fails:

```text
return false
```

Otherwise:

```text
stack.pop()
```

At the end:

```text
return stack.isEmpty()
```

---

## 💻 Java Solution

```java
class Solution {

    public boolean isValid(String s) {

        // Stack stores the closing bracket
        // that we expect to see next
        Stack<Character> stack = new Stack<>();

        // Traverse through every character of the string
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // If we see an opening parenthesis '(',
            // push its expected closing bracket ')'
            if (c == '(') {
                stack.push(')');
            }

            // If we see an opening curly bracket '{',
            // push its expected closing bracket '}'
            else if (c == '{') {
                stack.push('}');
            }

            // If we see an opening square bracket '[',
            // push its expected closing bracket ']'
            else if (c == '[') {
                stack.push(']');
            }

            // Otherwise, we have encountered a closing bracket
            else {

                // If the stack is empty, there is no
                // opening bracket available to match it
                //
                // OR
                //
                // If the current closing bracket does not
                // match the expected bracket at the top,
                // the parentheses are invalid
                if (stack.isEmpty() || stack.peek() != c) {
                    return false;
                }

                // The current closing bracket matched
                // the expected bracket, so remove it
                stack.pop();
            }
        }

        // The string is valid only if all opening brackets
        // have been matched and the stack is completely empty
        return stack.isEmpty();
    }
}
```

---

## 🧪 Dry Run

Consider:

```text
s = "({[]})"
```

### Character: `(`

Expected closing bracket is `)`:

```text
Stack:

[)]
```

---

### Character: `{`

Expected closing bracket is `}`:

```text
Stack:

[), }]
```

Top:

```text
}
```

---

### Character: `[` 

Expected closing bracket is `]`:

```text
Stack:

[), }, ]]
```

Top:

```text
]
```

---

### Character: `]`

Current character:

```text
]
```

Stack top:

```text
]
```

They match.

So:

```text
pop()
```

Stack:

```text
[), }]
```

---

### Character: `}`

Stack top:

```text
}
```

Current character:

```text
}
```

Match → `pop()`

```text
[)]
```

---

### Character: `)`

Stack top:

```text
)
```

Current character:

```text
)
```

Match → `pop()`

Stack:

```text
[]
```

At the end:

```java
stack.isEmpty() → true
```

Therefore:

```text
Answer → true
```

---

## 🔥 Why Push Closing Brackets?

This is the main trick of this solution.

Instead of doing:

```text
Opening bracket
      ↓
Remember it
      ↓
Later check which closing bracket matches it
```

we directly store what we **expect**:

```text
'(' → push ')'
'{' → push '}'
'[' → push ']'
```

For example:

```text
Input:

({[

Stack:

[), }, ]]
```

Now when `]` comes:

```text
stack.peek() = ']'
current = ']'
```

Match!

This makes the checking very simple.

---

## ⚠️ Important Condition

```java
if (stack.isEmpty() || stack.peek() != c) {
    return false;
}
```

Both checks are important.

### Case 1: Stack is empty

Input:

```text
")"
```

There is no opening bracket.

```text
stack.isEmpty() → true
```

So:

```text
return false
```

---

### Case 2: Brackets don't match

Input:

```text
"(]"
```

After `(`:

```text
Stack = [)]
```

Now current character is:

```text
]
```

But:

```text
stack.peek() = )
```

They don't match.

Therefore:

```text
return false
```

---

## 🎯 Why Check `stack.isEmpty()` First?

We write:

```java
stack.isEmpty() || stack.peek() != c
```

because if the Stack is empty, we should **not** try to access:

```java
stack.peek()
```

The `||` condition stops as soon as the first condition is true.

So for:

```text
")"
```

we immediately return `false`.

---

## ⏱️ Complexity

Let `n` be the length of the string.

```text
Time → O(n)
Space → O(n)
```

Each character is pushed and popped at most once.

---

## 🧠 Key Takeaway

The entire trick can be remembered as:

```text
Opening bracket
      ↓
Push expected closing bracket
      ↓
Closing bracket arrives
      ↓
Compare with stack.peek()
      ↓
Match → pop()
Mismatch → false
      ↓
End → stack must be empty
```

### One-Line Memory Trick

> **Don't store what you saw — store what you expect.** 🔥

```text
( → expect )
{ → expect }
[ → expect ]
```

> ⭐ If this helped you understand the Stack approach, an upvote would be appreciated! ❤️
