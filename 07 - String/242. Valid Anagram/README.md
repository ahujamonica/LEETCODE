# Don't Overcomplicate the Solution ⚡ | Frequency Array + Character Counting

🔗 **LeetCode:** https://leetcode.com/problems/valid-anagram/

## 🧠 Intuition

Two strings are anagrams if they contain the **same characters with the same frequency**.

Instead of sorting both strings, we can use an integer array of size `26`.

- For every character in `s` → **increase** its count.
- For every character in `t` → **decrease** its count.
- If both strings are anagrams, every count will finally become `0`.

Example:

```text
s = "anagram"
t = "nagaram"

'a' → +1 from s, -1 from t → 0
'n' → +1 from s, -1 from t → 0
...
```

So if all buckets are `0`, they are anagrams.

## 💡 Approach

### 1. Convert both strings to lowercase

```java
s = s.toLowerCase();
t = t.toLowerCase();
```

This makes the comparison case-insensitive.

### 2. Remove whitespace

```java
s = s.replaceAll("\\s", "");
t = t.replaceAll("\\s", "");
```

### 3. Create a frequency array

```java
int[] count = new int[26];
```

Each index represents a letter:

```text
0 → a
1 → b
2 → c
...
25 → z
```

### 4. Count characters of `s`

```java
count[s.charAt(i) - 'a']++;
```

For example:

```text
'a' - 'a' = 0
'b' - 'a' = 1
'c' - 'a' = 2
```

So each character directly maps to its bucket.

### 5. Remove characters of `t`

```java
count[t.charAt(i) - 'a']--;
```

If `s` and `t` have exactly the same frequencies, every bucket becomes `0`.

### 6. Check all buckets

```java
for(int i = 0; i < count.length; i++){
    if(count[i] != 0) return false;
}
```

If even one count is non-zero, the strings are not anagrams.

## 💻 Java Solution

```java
class Solution {
    public boolean isAnagram(String s, String t) {

        // Convert both strings to lowercase
        s = s.toLowerCase();
        t = t.toLowerCase();

        // Remove whitespace
        s = s.replaceAll("\\s", "");
        t = t.replaceAll("\\s", "");

        // Frequency array for 26 lowercase letters
        int[] count = new int[26];

        // Add frequency of characters in s
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i) - 'a']++;
        }

        // Subtract frequency of characters in t
        for(int i = 0; i < t.length(); i++){
            count[t.charAt(i) - 'a']--;
        }

        // Check if all frequencies became zero
        for(int i = 0; i < count.length; i++){
            if(count[i] != 0) {
                return false;
            }
        }

        return true;
    }
}
```

## 🧪 Dry Run

```text
s = "anagram"
t = "nagaram"
```

After processing `s`:

```text
a → 3
g → 1
m → 1
n → 1
r → 1
```

Then processing `t` subtracts exactly the same frequencies:

```text
a → 0
g → 0
m → 0
n → 0
r → 0
```

All buckets are `0`.

Therefore:

```text
return true
```

## 🔑 Key Trick

```text
First string  →  +1
Second string →  -1
                   ↓
             All counts = 0
                   ↓
               Anagram
```

Think:

> **Count → Cancel → Check**

## ⏱️ Complexity

- **Time:** `O(n + m)`
- **Space:** `O(1)`

The frequency array always has only `26` elements.

## 🎯 Key Takeaway

Instead of sorting:

```text
Sort + Compare
```

we can simply:

```text
Frequency Array
      ↓
   + for s
   - for t
      ↓
 Check all 0
```

This is a very common **character frequency / hashing pattern** for anagram problems.

> ⭐ If this helped, please consider giving it an upvote!
