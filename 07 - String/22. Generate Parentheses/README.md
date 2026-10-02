# Don't Overcomplicate the Solution ⚡ | Backtracking + Two Valid Choices

🔗 **LeetCode:** https://leetcode.com/problems/generate-parentheses/

## 🧠 Intuition

We need to generate all valid combinations of `n` pairs of parentheses.

At every step, we have only two possible choices:

```text
Add '('
Add ')'
```

But we cannot add them randomly.

### Choice 1 — Add `(`

```java
open < n
```

We can add `(` as long as we haven't used all `n` opening brackets.

### Choice 2 — Add `)`

```java
closed < open
```

We can add `)` only when we have an unmatched `(`.

This prevents invalid combinations such as:

```text
")("
"())("
```

The recursion explores one choice completely, comes back, and then explores the other choice.

That's the **backtracking** part.

---

## 💡 Approach

We keep track of:

```text
open   = number of '(' already used
closed = number of ')' already used
s      = current string
```

At every recursive call:

```text
open < n
    ↓
add '('
```

and:

```text
closed < open
    ↓
add ')'
```

When:

```text
open == n && closed == n
```

we have used all brackets, so `s` is a complete valid answer.

---

## 🧪 How is the string actually built?

This is the important part:

```java
s + "("
```

actually creates the new string with an opening bracket.

For example:

```text
s = ""

s + "("  → "("

s = "("

s + "("  → "(("

s = "(("

s + ")"  → "(()"
```

Similarly:

```java
s + ")"
```

adds a closing bracket.

Once the string is complete:

```java
res.add(s);
```

stores it in the result.

---

## 🔄 Backtracking Idea

For `n = 2`, think of the recursion as:

```text
                 ""
                  |
                 "("
                /   \
              "(("  "()"
               |      |
             "(()"   "()("
               |      |
             "(())"  "()()"
```

The algorithm doesn't calculate both branches simultaneously.

It:

```text
Explore "((" completely
        ↓
Come back
        ↓
Explore "()" completely
```

That is backtracking.

---

## 💻 Java Solution

```java
class Solution {

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {

        // Start with no brackets used
        dfs(n, 0, 0, "");

        return res;
    }

    private void dfs(int n, int open, int closed, String s) {

        // All n opening and n closing brackets are used
        if(open == n && closed == n) {
            res.add(s);
            return;
        }

        // We can add '(' if we still have opening brackets left
        if(open < n) {
            dfs(n, open + 1, closed, s + "(");
        }

        // We can add ')' only if there is an unmatched '('
        if(closed < open) {
            dfs(n, open, closed + 1, s + ")");
        }
    }
}
```

## 🔑 Key Trick

Remember these two conditions:

```java
if(open < n)
```

→ **Can I add `(`?**

```java
if(closed < open)
```

→ **Can I add `)`?**

And:

```java
if(open == n && closed == n)
```

→ **Is the combination complete?**

### The whole idea:

```text
             Current String
                   ↓
          ┌────────┴────────┐
          ↓                 ↓
      Add '('           Add ')'
    if open < n       if closed < open
          ↓                 ↓
       Recurse           Recurse
          └────────┬────────┘
                   ↓
          open == closed == n
                   ↓
              Save answer
```

## ⏱️ Complexity

Let `Cₙ` be the number of valid parentheses combinations (the `n`th Catalan number).

- **Time:** `O(Cₙ × n)`
- **Space:** `O(Cₙ × n)` including the output.

Each valid answer has length `2n`, and we must generate all valid answers.

## 🎯 Key Takeaway

Don't think of recursion as "magic."

Think:

```text
Make a choice
     ↓
Build the string
     ↓
Explore that choice completely
     ↓
Come back
     ↓
Try the other choice
```

For this problem:

> **Backtracking = choose `(` or `)` → recurse → come back → try the other valid choice.**

> ⭐ If this helped, please consider giving it an upvote!
