# 🚀 3. Longest Substring Without Repeating Characters

> 📂 Topic: Strings, HashSet, Sliding Window  
> 🎯 Pattern: Sliding Window + Two Pointers  
> ⭐ Difficulty: Medium  
> ⏱️ Time: O(n) | 💾 Space: O(n)

---

# 🔗 Problem Link

[LeetCode 3 — Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

---

# 🧠 One-Line Idea

> Maintain a sliding window containing only unique characters using a `HashSet`, and move the `left` pointer whenever a duplicate character is found.

---

# 💡 Intuition

The goal is to find the length of the longest substring that contains **no repeating characters**.

For example:

```text
s = "abcabcbb"
```

The longest substring without repeating characters is:

```text
"abc"
```

Therefore:

```text
Answer = 3
```

---

# 🚫 Brute Force

A simple approach would be to generate every possible substring and check whether it contains duplicate characters.

This can take:

```text
O(n²)
```

or more depending on how duplicates are checked.

We can do better using the **Sliding Window** technique.

---

# 🪟 Sliding Window

We maintain a window containing only unique characters.

The window is represented by:

```text
left
  ↓
[a b c]
      ↑
     right
```

- `left` → beginning of the current window
- `right` → end of the current window

The `HashSet` stores the characters currently inside this window.

For example:

```text
Window = "abc"

HashSet:
{a, b, c}
```

---

# 🔑 Why Use a HashSet?

We need to quickly check:

> Does the current character already exist in the current window?

We can do:

```java
sTrack.contains(character)
```

HashSet lookup is approximately:

```text
O(1)
```

We also use:

```java
sTrack.add(character);
```

to add a character and:

```java
sTrack.remove(character);
```

to remove a character when the window shrinks.

---

# 🚀 Approach

## Step 1 — Create the HashSet

```java
Set<Character> sTrack = new HashSet<>();
```

This stores all characters currently inside the sliding window.

Initially:

```text
{}
```

---

## Step 2 — Initialize the Pointers

```java
int left = 0;
```

The `left` pointer represents the beginning of our window.

The `right` pointer will move through the string using a `for` loop:

```java
for (int right = 0; right < s.length(); right++)
```

So:

```text
left  → beginning of window
right → end of window
```

---

# 3️⃣ Move `right`

Suppose:

```text
s = "abcabcbb"
```

Initially:

```text
[a]
 ↑
L/R
```

Add `a`:

```text
HashSet = {a}
```

Then move `right`:

```text
[ab]
 ↑ ↑
 L R
```

HashSet:

```text
{a, b}
```

Then:

```text
[abc]
 ↑   ↑
 L   R
```

HashSet:

```text
{a, b, c}
```

Current length:

```text
3
```

---

# 🚨 4. What Happens When We Find a Duplicate?

The next character is:

```text
a
```

The current window would become:

```text
[abca]
```

But `a` is already inside the HashSet.

We check:

```java
sTrack.contains(s.charAt(right))
```

which returns:

```text
true
```

So we have to shrink the window.

---

# ⬅️ Move `left`

We use:

```java
while (sTrack.contains(s.charAt(right))) {
    sTrack.remove(s.charAt(left));
    left++;
}
```

Suppose the current window is:

```text
[a b c a]
 ↑     ↑
 L     R
```

The character at `left` is:

```text
a
```

Remove it:

```text
[b c a]
 ↑   ↑
 L   R
```

Now the old `a` has been removed from the HashSet.

Then we can add the new `a`.

The window becomes:

```text
"bca"
```

which contains no duplicates.

---

# 🔄 Why Use `while`?

We use:

```java
while
```

instead of:

```java
if
```

because we need to keep removing characters until the duplicate disappears.

The general process is:

```text
Duplicate found
      ↓
Move left
      ↓
Remove character
      ↓
Still duplicate?
      ↓
Move left again
      ↓
Repeat
      ↓
Duplicate removed
```

---

# ➕ Add the Current Character

After the duplicate has been removed:

```java
sTrack.add(s.charAt(right));
```

Now the current window once again contains only unique characters.

---

# 📏 Calculate Window Length

The current window goes from:

```text
left → right
```

Therefore its length is:

```java
right - left + 1
```

For example:

```text
left = 2
right = 4
```

Indices:

```text
2, 3, 4
```

Number of characters:

```text
4 - 2 + 1 = 3
```

So we update:

```java
maxLength = Math.max(maxLength, right - left + 1);
```

---

# 📝 Dry Run

Consider:

```text
s = "abcabcbb"
```

The window changes approximately like this:

```text
right    Window    Length
--------------------------------
0        "a"          1
1        "ab"         2
2        "abc"        3
3        "bca"        3
4        "cab"        3
5        "abc"        3
6        "cb"         2
7        "b"          1
```

The maximum length found is:

```text
3
```

Therefore:

```text
Answer = 3
```

---

# 💻 Java Solution

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {

        // Stores characters currently present in the window
        Set<Character> sTrack = new HashSet<>();

        // Stores the maximum length found
        int maxLength = 0;

        // Left pointer of the sliding window
        int left = 0;

        // Move right pointer through the string
        for (int right = 0; right < s.length(); right++) {

            // If the current character already exists,
            // shrink the window from the left
            while (sTrack.contains(s.charAt(right))) {

                // Remove the character at the left side
                sTrack.remove(s.charAt(left));

                // Move left forward
                left++;
            }

            // Add the current character to the window
            sTrack.add(s.charAt(right));

            // Update the maximum window length
            maxLength = Math.max(
                maxLength,
                right - left + 1
            );
        }

        return maxLength;
    }
}
```

---

# 🎯 Key Takeaway

The entire solution follows this pattern:

```text
Create HashSet
      ↓
left = 0
      ↓
Move right →
      ↓
Is current character already present?
      ↓
     YES
      ↓
Move left →
Remove characters
      ↓
Until duplicate disappears
      ↓
Add current character
      ↓
Calculate window length
      ↓
Update maximum
```

The most important part is:

```java
while (sTrack.contains(s.charAt(right))) {
    sTrack.remove(s.charAt(left));
    left++;
}
```

This maintains the rule:

> **The current sliding window always contains unique characters.**

Then:

```java
sTrack.add(s.charAt(right));
```

adds the new character.

Finally:

```java
maxLength = Math.max(maxLength, right - left + 1);
```

keeps track of the longest valid substring.

---

# 📊 Complexity

## Time Complexity

Even though there is a `while` loop inside the `for` loop, the overall complexity is still:

```text
O(n)
```

Why?

Each character can be:

```text
Added to the HashSet at most once
Removed from the HashSet at most once
```

Both `left` and `right` only move forward.

Therefore:

```text
Time = O(n)
```

## Space Complexity

The HashSet can contain up to `n` characters in the worst case.

Therefore:

```text
Space = O(n)
```

---

# 🧠 Pattern to Remember

```text
Sliding Window
      +
HashSet
      ↓
Unique characters in current window
      ↓
Duplicate?
      ↓
Move left
      ↓
Remove characters
      ↓
Continue
```

> **Use a HashSet to maintain a sliding window of unique characters. Expand the window using `right`, shrink it using `left` whenever a duplicate appears, and keep track of the maximum window length.**

Happy Coding! 🚀✨
