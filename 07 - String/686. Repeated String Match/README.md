# Don't Overcomplicate the Solution ⚡ | StringBuilder + Repeated String Matching

🔗 **LeetCode:** https://leetcode.com/problems/repeated-string-match/

## 🧠 Intuition

We need to find the minimum number of times we need to repeat `A` so that `B` becomes a substring of it.

For example:

```text
A = "abcd"
B = "cdabcdab"
```

Repeat `A`:

```text
1 time → "abcd"                 ❌
2 times → "abcdabcd"            ❌
3 times → "abcdabcdabcd"        ✅
```

So the answer is `3`.

The main idea is:

> Keep repeating `A` until the constructed string is at least as long as `B`, then check whether it contains `B`.

However, `B` might start near the end of one copy of `A` and continue into the next copy. Therefore, we check **one extra repetition**.

## 💡 Approach

1. Start with one copy of `A`.
2. Keep appending `A` while `temp.length() < B.length()`.
3. Check if `temp` already contains `B`.
4. If not, append `A` one more time and check again.
5. If `B` still isn't found, return `-1`.

### Why one extra copy?

Consider:

```text
A = "abcd"
B = "dabcd"
```

One copy:

```text
abcd
```

`B` cannot fit.

After repeating:

```text
abcdabcd
```

Now:

```text
abc[dabcd]
```

So `B` crosses the boundary between two copies of `A`.

That's why we need the extra repetition.

## 💻 Java Solution

```java
class Solution {
    public int repeatedStringMatch(String A, String B) {

        // Start with one copy of A
        int count = 1;

        // temp initially contains A
        StringBuilder temp = new StringBuilder(A);

        // Keep adding A until temp is at least as long as B
        while(temp.length() < B.length()) {
            temp.append(A);
            count++;
        }

        // Check if B is already present
        if(temp.toString().contains(B)) {
            return count;
        }

        // B may cross the boundary between two copies of A
        temp.append(A);

        if(temp.toString().contains(B)) {
            return count + 1;
        }

        // B cannot be formed
        return -1;
    }
}
```

## 🧪 Dry Run

```text
A = "abcd"
B = "cdabcdab"
```

Initially:

```text
count = 1
temp = "abcd"
```

### While loop

```text
temp.length() = 4
B.length() = 8

4 < 8 → TRUE
```

Append `A`:

```text
temp = "abcdabcd"
count = 2
```

Check again:

```text
8 < 8 → FALSE
```

So we exit the `while`.

### First check

```text
"abcdabcd".contains("cdabcdab")
```

❌ False.

### Extra repetition

Append `A`:

```text
temp = "abcdabcdabcd"
```

Now:

```text
"abcdabcdabcd".contains("cdabcdab")
```

✅ True.

Therefore:

```text
return count + 1
       = 2 + 1
       = 3
```

## 🔑 Key Trick

There are **two separate places where we append `A`**:

```java
while(temp.length() < B.length()) {
    temp.append(A);
    count++;
}
```

This makes `temp` **long enough**.

Then:

```java
temp.append(A);
```

This gives us **one extra copy** to handle a substring that crosses the boundary between two repetitions.

## ⏱️ Complexity

Let `n = A.length()` and `m = B.length()`.

- **Time:** Depends on the underlying implementation of `String.contains()`, with repeated-string construction taking `O(m + n)` overall.
- **Space:** `O(m + n)` for the constructed repeated string.

## 🎯 Key Takeaway

```text
Repeat A until length >= B
            ↓
      Check contains(B)
            ↓
          Found?
       /          \
     YES           NO
      ↓             ↓
   return        Add A once
                   ↓
              Check again
                /      \
              YES       NO
               ↓         ↓
          return count+1 -1
```

> **Repeat → Check → One Extra Repeat → Check Again**

⭐ If this helped, please consider giving it an upvote!
