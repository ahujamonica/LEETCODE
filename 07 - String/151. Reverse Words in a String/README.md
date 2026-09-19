# ~// ★ 151. Reverse Words in a String ★ \\

🔗 **LeetCode:** https://leetcode.com/problems/reverse-words-in-a-string/

## 🧠 Intuition

We need to reverse the **order of the words**, not the characters.

For example:

```text
"the sky is blue"
        ↓
"blue is sky the"
```

The simplest approach is:

1. Remove extra spaces using `trim()`.
2. Split the string into individual words.
3. Traverse the words from right to left.
4. Build the answer using `StringBuilder`.

---

## 💡 Approach

First:

```java
String[] words = s.trim().split("\\s+");
```

For:

```text
"  the   sky is blue  "
```

we get:

```text
["the", "sky", "is", "blue"]
```

Then start from the last index:

```java
for(int i = words.length - 1; i >= 0; i--)
```

So the order becomes:

```text
blue → is → sky → the
```

We append each word to `StringBuilder` and add a space between words.

---

## 🧪 Dry Run

```text
s = "the sky is blue"

words = ["the", "sky", "is", "blue"]
```

Traverse backwards:

```text
i = 3 → "blue"
i = 2 → "is"
i = 1 → "sky"
i = 0 → "the"
```

Result:

```text
"blue is sky the"
```

---

## 💻 Java Solution

```java
class Solution {
    public String reverseWords(String s) {

        // Remove leading/trailing spaces
        // and split into individual words
        String[] words = s.trim().split("\\s+");

        // Used to build the final answer
        StringBuilder ans = new StringBuilder();

        // Traverse words from right to left
        for(int i = words.length - 1; i >= 0; i--) {

            // Add current word
            ans.append(words[i]);

            // Add space between words
            // but not after the last word
            if(i != 0) {
                ans.append(" ");
            }
        }

        return ans.toString();
    }
}
```

---

## 🔑 Key Trick

```text
String
  ↓
trim()
  ↓
split into words
  ↓
traverse array backwards
  ↓
StringBuilder
  ↓
reversed word order
```

The main idea:

> **Don't reverse the characters — just traverse the words from right to left.**

---

## ⏱️ Complexity

- **Time:** `O(n)`
- **Space:** `O(n)`

`n` = length of the input string.

---

## 🎯 Key Takeaway

Whenever the problem says:

> **Reverse the words in a sentence**

Think:

```text
~// ★ Split → Reverse Traversal → Build ★ \\~
```

⭐ If this helped, please consider giving it an upvote!
