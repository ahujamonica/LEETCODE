# ⚡ Keep It Simple | Count the Missing Brackets

🔗 **LeetCode:** https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/

## 🧠 Intuition

We simply keep track of how many `'('` are currently **waiting for a matching `')'`**.

- `'('` → increase `open`
- `')'` with an available `'('` → match it, so decrease `open`
- `')'` with no available `'('` → we must **add a `'('`**, so increase `additions`

After processing the entire string, if some `'('` are still unmatched, we need to add one `')'` for each of them.

---

## 💡 Approach

```text
'('
 ↓
open++

')'
 ↓
Is open > 0?
 ├── YES → open--
 └── NO  → additions++

After the loop:
remaining '(' → need ')' for each
                ↓
          additions += open
```

---

## 💻 Java Solution

```java
class Solution {
    public int minAddToMakeValid(String s) {

        // Number of unmatched '('
        int open = 0;

        // Number of brackets we need to add
        int additions = 0;

        for(int i = 0; i < s.length(); i++) {

            // Opening bracket → it needs a ')' later
            if(s.charAt(i) == '(') {
                open++;
            }

            // Closing bracket
            else if(open > 0) {

                // Match it with an existing '('
                open--;
            }

            else {

                // No '(' available to match this ')'
                // So we need to add a '('
                additions++;
            }
        }

        // Any remaining '(' need a ')' each
        return additions += open;
    }
}
```

## 🧪 Quick Example

For:

```text
s = "())"
```

```text
( → open = 1
) → open = 0
) → no '(' → additions = 1
```

At the end:

```text
open = 0
additions = 1
```

So:

```text
answer = 1
```

We can make the string valid by adding one `'('`:

```text
()) → ()()
```

## 🔑 Key Trick

> **`open` = unmatched `'('` and `additions` = brackets we need to create.**

The entire solution is just:

```text
Match whenever possible.
Add when matching is impossible.
Fix remaining '(' at the end.
```

## ⏱️ Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

## 🎯 Key Takeaway

No stack is needed here.

Just count:

```text
'(' → open++
')' → match if possible, otherwise additions++
```

Then:

```java
additions += open;
```

handles all the remaining unmatched opening brackets.

⭐ If this helped, please consider giving this solution an upvote!
