# Sorting + First & Last String Comparison

🔗 **LeetCode:** https://leetcode.com/problems/longest-common-prefix/

## 🧠 Intuition

We need to find the longest prefix that is common to **all strings**.

Instead of comparing every string with every other string, we can use a simple trick:

```text
Sort the strings
      ↓
Take the first and last string
      ↓
Compare them character by character
      ↓
Common characters = Longest Common Prefix
```

Why does this work?

After sorting, the **first and last strings are the most different lexicographically**.

So if they have the same prefix, every string between them must also have that prefix.

## 💡 Approach

For example:

```text
["flower", "flow", "flight"]
```

After sorting:

```text
["flight", "flow", "flower"]
```

Take:

```text
first = "flight"
last  = "flower"
```

Compare character by character:

```text
f == f → add 'f'
l == l → add 'l'
i != o → stop
```

So the answer is:

```text
"fl"
```

The `StringBuilder` stores the common characters we find.

## 💻 Java Solution

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // Store the common prefix
        StringBuilder result = new StringBuilder();

        // Sort the strings
        Arrays.sort(strs);

        // Get the first and last strings
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();

        // Compare characters at the same position
        for(int i = 0; i < first.length; i++){

            // Stop when characters are different
            if(first[i] != last[i]) {
                break;
            }

            // Add matching character to the result
            result.append(first[i]);
        }

        return result.toString();
    }
}
```

## 🧪 Dry Run

Input:

```text
["flower", "flow", "flight"]
```

After sorting:

```text
["flight", "flow", "flower"]
```

So:

```text
first = "flight"
last  = "flower"
```

### `i = 0`

```text
f == f
```

Add `f`:

```text
result = "f"
```

### `i = 1`

```text
l == l
```

Add `l`:

```text
result = "fl"
```

### `i = 2`

```text
i != o
```

So:

```java
break;
```

The loop stops.

Final answer:

```text
"fl"
```

## 🔑 Key Trick

```text
Arrays.sort(strs)
       ↓
First string + Last string
       ↓
Compare characters
       ↓
Different character → break
       ↓
Matching characters → answer
```

The important idea is:

> **After sorting, only the first and last strings need to be compared.**

## ⏱️ Complexity

Let:

- `n` = number of strings
- `m` = length of the strings

- **Time:** `O(n log n + m)`
- **Space:** `O(m)` for the character arrays and result.

## 🎯 Key Takeaway

> **Sort → Compare First & Last → Stop at the First Mismatch.**

⭐ If this helped, please consider giving it an upvote!
